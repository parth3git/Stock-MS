package com.example.Order.Service;

import com.example.Order.Model.*;
import com.example.Order.Repositary.adminRepo;
import com.example.Order.Repositary.billRepo;
import com.example.Order.Repositary.customerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class customerService {
    @Autowired
    customerRepo customerRepo;

    @Autowired
    billRepo billRepo;

    @Autowired
    adminRepo adminRepo;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WhatsAppSenderService whatsAppSenderService;

    public String addcust(Customer customer) {
        if (!Objects.equals(customer.getcName(), "")) {
            customerRepo.save(customer);
            return "✅ New Customer Added";
        } else {
            return "❌ Error on creating customer ";
        }
    }

    public String addadmin(Admin admin) {
        adminRepo.save(admin);
        return "✅ Admin created";
    }

    public String orderProduct(OrderDTO orderDTO) {
        //Get Stock Details
        String stockurl = "http://localhost:8083/stocking/checkStock/" + orderDTO.getProduct();
        ResponseDTO responseDTO = restTemplate.getForObject(stockurl, ResponseDTO.class);
        int Availableqty = responseDTO.getQuantity();

        //Check Availability
        if (Availableqty <= 0) {
            whatsAppSenderService.sendAdminAlert("❗ ALERT ❗" + "\n" +
                    "Customer Wants To Buy " + orderDTO.getProduct() + " But Stock Is 0 Please Re-Stock");
            return "❌ Stock Not Available";

        } else if (Availableqty < orderDTO.getQuantity()) {
            return "⚠ Insufficiant Stock";
        } else {
            //Get Product Details
            String producturl = "http://localhost:8083/stocking/getProduct/" + orderDTO.getProduct();
            productDTO productDTO = restTemplate.getForObject(producturl, productDTO.class);

            //Get Customer Details
            Customer customer = customerRepo.getById(orderDTO.getCid());

            //Bill Ganerate
            Bill newbill = new Bill();

            int customerQty = orderDTO.getQuantity();
            int gst = productDTO.getPgst();
            int productPrice = productDTO.getPprice();
            int netprice = productPrice * customerQty;
            double gstAmo = (double) (netprice * gst) / 100;
            int totalAmo = (int) (netprice + gstAmo);

            newbill.setcName(customer.getcName());
            newbill.setBillAmount(totalAmo);
            newbill.setCustomer(customer);
            billRepo.save(newbill);
            billRepo.setStatus(newbill.getBid());

//          Fetch updated bill status
            Bill updatedBill = billRepo.findById(newbill.getBid()).orElse(null);

            if (updatedBill == null) {
                return "❌ Bill Not Found!";
            }

            if (!updatedBill.isStatus()) {
                //WhatsApp Failed SMS Send
                whatsAppSenderService.sendWhatsAppMs(
                        "+91" + customer.getcMobileNo(),
                        "Payment Failed");

                return "❌ Payment Failed | Bill ID: " + updatedBill.getBid();
            }

            //WhatsApp Sucsses SMS Send
            String sms = "Bill Id: " + updatedBill.getBid() + "\n" +
                    "Product Name: " + orderDTO.getProduct() + "\n" +
                    "Quantity: " + orderDTO.getQuantity() + "\n" +
                    "Total Bill Paid: र." + updatedBill.getBillAmount();

            whatsAppSenderService.sendWhatsAppMs(
                    "+91" + customer.getcMobileNo(),
                    sms);

            // Update Stock
            String updateUrl = "http://localhost:8083/stocking/update/" + orderDTO.getProduct() + "/" + orderDTO.getQuantity();
            restTemplate.put(updateUrl, null);

            //Check Threshold And Send Alert To Admin
//            String stockcheckurl="http://localhost:8083/stocking/checkStock/"+orderDTO.getProduct();
            responseDTO = restTemplate.getForObject(stockurl, ResponseDTO.class);
            int remainQty = responseDTO.getQuantity();
            int threshOld = responseDTO.getThreshold();

            if (remainQty <= threshOld) {
                whatsAppSenderService.sendAdminAlert(
                        "⚠ ALERT ⚠" + "\n" +
                                "Product Name: " + orderDTO.getProduct() + "\n" +
                                "Quantity Left: " + remainQty + "\n" +
                                "Sould Be: " + threshOld + "\n" +
                                "Add New Stock"
                );
            }

            return "✅ Order Placed Successfully | Bill ID: " + updatedBill.getBid() +
                    " | Total: ₹" + updatedBill.getBillAmount();
        }
    }
}

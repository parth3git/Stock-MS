package com.example.Order.Controller;

import com.example.Order.Model.Admin;
import com.example.Order.Model.Customer;
import com.example.Order.Model.OrderDTO;
import com.example.Order.Service.customerService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ordering")
public class orderCont {
    @Autowired
    customerService customerService;

    @PostMapping(value = "newcust")
    public String addcust(@RequestBody Customer customer)
    {
        return customerService.addcust(customer);
    }

    @PostMapping(value = "newadmin")
    public String addadmin(@RequestBody Admin admin)
    {
        return customerService.addadmin(admin);
    }

    @PostMapping(value="orderProduct")
    public String orderProduct(@RequestBody OrderDTO orderDTO)
    {
        return customerService.orderProduct(orderDTO);
    }

}

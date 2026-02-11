package com.example.Stock.Service;

import com.example.Stock.Model.*;
import com.example.Stock.Model.ResponseDTO;
import com.example.Stock.Repositary.ProductRepo;
import com.example.Stock.Repositary.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ProductServ {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    StockRepo stockRepo;

    @Autowired
    EmailSer emailSer;

    @Autowired
    csvService csvService;

    public String saveproduct(Product product) {

        if(productRepo.checkduplicate(product.getpName())>0){
            return "❌ Product Already Exists";
        } else {
            productRepo.save(product);
            return "✅ Product Saved";
        }
    }

    public String addstock(addStockDTO addStockDTO) {
        if(!stockRepo.existsById(addStockDTO.getsId())){
            return "❌ Sid Not available";
        }
        else {
            Stock stock=stockRepo.findById(addStockDTO.getsId()).get();
            stock.setInStock(stock.getInStock()+addStockDTO.getAddStock());
            stockRepo.save(stock);

            return "✅ Stock Added";
        }
    }

    public ResponseDTO checkstock(String product) {
        ResponseDTO newresponsdto=stockRepo.getstockdetails(product);
        newresponsdto.setProduct(product);
        return newresponsdto;
    }

    public String updatestock(String pname, int qty) {
        int sid=productRepo.findByProductName(pname);
        Stock stock=stockRepo.findById(sid).orElse(null);

        if(stock==null)
        {
           return  "Product Not found";
        }
        if(stock.getInStock()<qty)
        {
            return "Insufficiant Stock";
        }
        stock.setInStock(stock.getInStock()-qty);
        stockRepo.save(stock);

        return "Stock Updated";
    }

    public ProductDTO getproduct(String pname) {
        return productRepo.getByPname(pname);
    }

    public void getstockreport() throws Exception {

        List<Stock> stocks = stockRepo.findAll();
        File csv = csvService.generateStockCSV(stocks);

        emailSer.sendStockReport(csv);
    }
}

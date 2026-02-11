package com.example.Stock.Controller;

import com.example.Stock.Model.*;
import com.example.Stock.Model.ResponseDTO;
import com.example.Stock.Service.ProductServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stocking")
public class ProductCon {
    @Autowired
    ProductServ productServ;

    @PostMapping(value = "saveProduct")
    public String saveproduct(@RequestBody Product product) {
        return productServ.saveproduct(product);
    }

    @PostMapping(value = "addStock")
    public String addstock(@RequestBody addStockDTO addStockDTO) {
        return productServ.addstock(addStockDTO);
    }

    @GetMapping(value = "checkStock/{product}")
    public ResponseDTO checkstock(@PathVariable String product) {
        return productServ.checkstock(product);
    }

    @PutMapping(value = "update/{pname}/{qty}")
    public String updateStock(@PathVariable String pname, @PathVariable int qty) {
        return productServ.updatestock(pname, qty);
    }

    @GetMapping(value = "getProduct/{pname}")
    public ProductDTO getproduct(@PathVariable String pname) {
        return productServ.getproduct(pname);
    }

    @GetMapping(value = "getStockReport")
    public void getStockReport() throws Exception {
        productServ.getstockreport();
    }
}

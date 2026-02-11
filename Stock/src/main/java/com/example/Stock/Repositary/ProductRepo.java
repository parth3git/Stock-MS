package com.example.Stock.Repositary;

import com.example.Stock.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query(value = "SELECT COUNT(p_name) FROM Product WHERE LOWER(product.p_name) =LOWER(:s)",nativeQuery = true)
    int checkduplicate(String s);

    @Query(value = "SELECT stock_id FROM product WHERE p_name=:pname",nativeQuery = true)
    int findByProductName(String pname);

    @Query(value = "SELECT p_id,p_name,p_price,p_gst from product WHERE p_name=:pname",nativeQuery = true)
    ProductDTO getByPname(String pname);
}

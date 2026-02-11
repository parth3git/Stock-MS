package com.example.Stock.Repositary;

import com.example.Stock.Model.ResponseDTO;
import com.example.Stock.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepo extends JpaRepository<Stock,Integer> {

    @Query(value = "SELECT in_stock AS inStock, threshold AS threshold FROM stock WHERE s_id = (SELECT stock_id FROM product WHERE p_name=:product)",nativeQuery = true)
    ResponseDTO getstockdetails(String product);
}

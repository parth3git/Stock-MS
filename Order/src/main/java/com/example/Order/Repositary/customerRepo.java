package com.example.Order.Repositary;

import com.example.Order.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepo extends JpaRepository<Customer,Integer> {


}

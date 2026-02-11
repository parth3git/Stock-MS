package com.example.Order.Repositary;

import com.example.Order.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface billRepo extends JpaRepository<Bill,Integer> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE bill SET b_status=CASE WHEN (bid % 5)=0 THEN false ELSE true END WHERE bid=:bid",nativeQuery = true)
    int setStatus(int bid);
}

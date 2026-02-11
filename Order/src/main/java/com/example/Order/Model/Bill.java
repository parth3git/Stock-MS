package com.example.Order.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int bid;

    @Column
    private String cName;

    @Column
    private int billAmount;

    @Column
    private boolean bStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id")
    private Customer customer;

    public Bill() {
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }

    public boolean isStatus() {
        return bStatus;
    }

    public void setStatus(boolean bStatus) {
        this.bStatus = bStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}

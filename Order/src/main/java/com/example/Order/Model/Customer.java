package com.example.Order.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int cid;

    @Column
    private String cName;

    @Column
    private long cMobileNo;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Bill> billList;

    public Customer() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public long getcMobileNo() {
        return cMobileNo;
    }

    public void setcMobileNo(long cMobileNo) {
        this.cMobileNo = cMobileNo;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }
}

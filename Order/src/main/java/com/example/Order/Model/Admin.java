package com.example.Order.Model;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int aId;

    @Column
    private String aName;

    @Column
    private long aPhone;

    @Column
    private String aEmail;

    public Admin() {
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public long getaPhone() {
        return aPhone;
    }

    public void setaPhone(long aPhone) {
        this.aPhone = aPhone;
    }

    public String getaEmail() {
        return aEmail;
    }

    public void setaEmail(String aEmail) {
        this.aEmail = aEmail;
    }
}

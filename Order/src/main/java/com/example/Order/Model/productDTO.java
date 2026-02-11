package com.example.Order.Model;

public class productDTO {
    private int pid;
    private String pname;
    private int pprice;
    private int pgst;

    public productDTO() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPprice() {
        return pprice;
    }

    public void setPprice(int pprice) {
        this.pprice = pprice;
    }

    public int getPgst() {
        return pgst;
    }

    public void setPgst(int pgst) {
        this.pgst = pgst;
    }
}

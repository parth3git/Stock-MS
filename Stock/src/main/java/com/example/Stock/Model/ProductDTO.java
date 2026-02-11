package com.example.Stock.Model;

public class ProductDTO {
    private int pid;
    private String pname;
    private int pprice;
    private int pgst;

    public ProductDTO() {
    }

    public ProductDTO(int pid, String pname, int pprice, int pgst) {
        this.pid = pid;
        this.pname = pname;
        this.pprice = pprice;
        this.pgst = pgst;
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

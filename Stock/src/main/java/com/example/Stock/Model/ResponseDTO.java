package com.example.Stock.Model;

public class ResponseDTO {
    private String product;
    private int quantity;
    private int threshold;

    public ResponseDTO(int quantity, int threshold) {
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public ResponseDTO() {}

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}

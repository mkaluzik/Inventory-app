package com.example.android.inventoryapp.data;

/**
 * Created by martin on 24.7.17.
 */

public class Commodity {

    private final String productName;
    private final String price;
    private final int quantity;
    private final String supplierName;
    private final String supplierPhone;
    private final String supplierEmail;
    private final String image;

    public Commodity(String productName, String price, int quantity, String supplierName, String supplierPhone, String supplierEmail, String image) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.supplierPhone = supplierPhone;
        this.supplierEmail = supplierEmail;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public String getImage() {
        return image;
    }
    @Override
    public String toString() {
        return "Commodity{" +
                "productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                ", supplierName='" + supplierName + '\'' +
                ", supplierPhone='" + supplierPhone + '\'' +
                ", supplierEmail='" + supplierEmail + '\'' +
                '}';
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import util.MyUtility;

/**
 *
 * @author User
 */
public abstract class TransactionDetails implements Serializable {
    protected String id;           // ID của đơn hàng hoặc phiếu nhập hàng
    protected String productID; // Mã sản phẩm
    protected double unitPrice; // Đơn giá
    protected int quantity;     // Số lượng
    
    public TransactionDetails(){}
    public TransactionDetails(String id, String productID, double unitPrice, int quantity) {
        this.id = id;
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void input(){
        unitPrice = MyUtility.getADouble("Nhap don gia: ", "Vui long nhap so!!");
        quantity = MyUtility.getAnInteger("Nhap so luong: ", "Vui long nhap so nguyen!!");
        
    }

    @Override
    public String toString() {
        return "TransactionDetails{" + "id=" + id + ", productID=" + productID + ", unitPrice=" + unitPrice + ", quantity=" + quantity + '}';
    }
    
    public abstract double totalAmount();
    public abstract void display();
}

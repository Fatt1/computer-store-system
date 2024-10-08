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
public class OrderDetails extends TransactionDetails implements Serializable {
    private double discount;
    
    public OrderDetails(){}
    
    public OrderDetails(String orderID, String productID, double unitPice, int quantity, double discount) {
        super(orderID, productID, unitPice, quantity);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public void input() {
        super.input(); 
        discount = MyUtility.getADouble("Nhap giam gia: ", "Vui long nhap so!!");
    }
    
    
    @Override
    public double totalAmount(){
        return unitPrice * quantity * (1 - discount);
    }
    @Override
    public String toString() {
        return "OrderDetails{" + "orderID=" + id + ", productID=" + productID + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", discount=" + discount + '}';
    }
    
    @Override
    public void display() {
        System.out.printf("|%-12s|%-13s|%-8.2f|%-5d|%-9.2f|%-11.2f|\n",id, productID, unitPrice, quantity, discount, totalAmount());
    }
    
}

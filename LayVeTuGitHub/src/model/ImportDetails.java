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
public class ImportDetails extends TransactionDetails implements Serializable{

    public ImportDetails() {}
    public ImportDetails(String id, String productID, double unitPrice, int quantity) {
        super(id, productID, unitPrice, quantity);
    }
    
    @Override
    public double totalAmount() {
        return quantity * unitPrice * (1 + MyUtility.VAT);
    }
    
    @Override
    public void display() {
        System.out.printf("|%-15s|%-15s|%-8.2f|%-5d|%-11.2f|\n",id, productID, unitPrice, quantity, totalAmount());
    }
    
}

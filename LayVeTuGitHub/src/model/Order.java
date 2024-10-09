/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import util.MyUtility;

/**
 *
 * @author User
 */
public class Order extends Transaction implements Serializable{
   
    private String customerID;
    
    public Order(){}
    
    public Order(String id, String customerID, String employeeID, String status) {
        super(id, employeeID, status);
        this.customerID = customerID;
        
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    
    @Override
    public String toString() {
        return "Order{" + "customerID=" + customerID + '}';
    }

    @Override
    public void input() {
        do {            
            customerID = MyUtility.getId("Nhap ID KH (CXXX): ", "Vui long nhap theo dinh dang CXXX, "
                    + "X dai dien cho so (0 - 9) ", "^[c|C]\\d{3}$");
            if(!MyUtility.checkCustomerID(customerID)){
                System.out.println("ID KH nay khong ton tai. Vui long nhap ID KH da co san!!");
            }
        } while (!MyUtility.checkCustomerID(customerID));
        super.input();
    }
    
    @Override
    public void display() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");
        System.out.printf("|%-12s|%-15s|%-15s|%-25s|%-15s|\n", id, customerID, employeeID, date.format(formatter), status);
    }
}

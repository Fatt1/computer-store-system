/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Customer implements Serializable {
    private String customerId;
    private String customerName;
    private LocalDate yob;
    private String phoneNumber;

    public Customer(String customerId, String customerName, LocalDate yob, String phoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.yob = yob;
        this.phoneNumber = phoneNumber;
    }


    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerId(){
        return customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getYob() {
        return yob;
    }

    public void setYob(LocalDate yob) {
        this.yob = yob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", customerName=" + customerName + ", yob=" + yob + ", phoneNumber=" + phoneNumber + '}';
    }
    
    public void display() {
        System.out.printf("|%-5s|%-25s|%-25s|%-11s|\n", 
                            customerId, customerName, yob, phoneNumber);
    }
    
}

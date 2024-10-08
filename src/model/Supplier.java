/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Supplier implements Serializable{
    private int supplierId;
    private String supplierName;
    private String address;
    private String phoneNumber;

    public Supplier(int supplierId, String supplierName, String address, String phoneNumber) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + supplierId + ", supplierName=" + supplierName + ", address=" + address + ", phoneNumber=" + phoneNumber + '}';
    }
    
    public void display() {
        System.out.printf("|%-5d|%-25s|%-50s|%-12s|\n",
                            supplierId, supplierName, address, phoneNumber);
                        
    }
    
}

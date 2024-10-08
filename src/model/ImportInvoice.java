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
public class ImportInvoice extends Transaction implements Serializable {
    private int supplierID;
    
    public ImportInvoice(){}
    public ImportInvoice(String id, int supplierID, String employeeID, String status){
        super(id, employeeID, status);
        this.supplierID = supplierID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }
    
    @Override
    public void input() {
        do {            
            supplierID = MyUtility.getAnInteger("Nhap ID nha cung cap: ", "Vui long nhap so nguyen!!");
            if(!MyUtility.checkSupplierID(supplierID)){
                System.out.println("ID nha cung cap nay khong ton tai. Vui long nhap ID nha cung cap da co san!!");
            }
        } while (!MyUtility.checkSupplierID(supplierID));
        super.input();
    }
    
    @Override
    public void display() {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
       System.out.printf("|%-15s|%-15d|%-8s|%-25s|%-15s|\n", id, supplierID, employeeID, date.format(formatter), status);
    }
     
}

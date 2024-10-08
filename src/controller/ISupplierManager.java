/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;


import model.Supplier;

/**
 *
 * @author User
 */
public interface ISupplierManager extends IManager {
    
    public void searchByID();
    public void searchSupplierByName();
    public Supplier[] searchSupplierByName(String supName);
    public int searchByID(int supID);
    public Supplier searchSupplierByID(int supID);
    
}

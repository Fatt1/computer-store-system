/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;


import model.Customer;

/**
 *
 * @author User
 */
public interface ICustomerManager extends IManager{
    public String splitFirstName(String fullName);// để lấy tên ra đem đi sắp xếp, chứ sắp xếp theo họ thì ko có ý nghĩa
    public void searchByName();
    public Customer[] searchByName(String cusName);
    public void searchByID();
    public Customer searchCusByID(String cusID);
    public int searchByID(String cusID);
    public void printCusListAscendingByName();
    
}

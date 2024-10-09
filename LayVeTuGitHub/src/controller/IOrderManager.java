/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import model.Order;

/**
 *
 * @author User
 */
public interface IOrderManager extends IManager {
    public Order[]searchOrder(Filter<Order> filter);
    public void searchOrderByCusTomerID();
    public void searchOrderByEmployeeID();
    public void searchByOrderID();
    public int searchByOrderID(String orderID);
    public Order searchOrderByOrderID(String orderID); 
    public void showOrderDetails();

    
}

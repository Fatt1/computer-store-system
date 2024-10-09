/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import model.OrderDetails;

/**
 *
 * @author User
 */
public interface IOrderDetailsManager extends IDetails {
    public boolean checkOrderID(String orderID);
    public OrderDetails[] searchOrderDetails(Filter<OrderDetails> filter);
    public void searchOrderDetailsByProductID();
}

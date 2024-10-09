/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

/**
 *
 * @author User
 */
public interface IDetails<T> extends IManager {
    public void searchByID();
    public int searchByID(String ID, String productID); // tìm kiếm chính xác để chỉnh xửa
    public T searchDetailsByID(String ID, String productID);
    
    
}

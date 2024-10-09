/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;


import model.Employee;

/**
 *
 * @author User
 */
public interface IEmployeeManager extends IManager {
    public int searchByID(String empID);
    public void searchByID();
    public Employee searchEmployeeByID(String empID);
    public void searchByName();
    public Employee[] searchEmPloyee(Filter<Employee> fitler);
    public void filterTitle();
    public void filterAdress();
    public void loadDataFromFile(String fname);
    public void saveToFile(String fname);
   
}


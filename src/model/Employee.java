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
public class Employee implements Serializable{
    private String employeeID;
    private String FullName;
    private LocalDate birthday;
    private String title;
    private String address;
    private String phoneNumber;

    public Employee(String employeeID, String FullName, LocalDate birthday, String title, String Address, String phoneNumber) {
        this.employeeID = employeeID;
        this.FullName = FullName;
        this.birthday = birthday;
        this.title = title;
        this.address = Address;
        this.phoneNumber = phoneNumber;
    }
    
    
    
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String EmployeeID) {
        this.employeeID = EmployeeID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
  
    @Override
    public String toString() {
        return "Employee{" + "EmployeeID=" + employeeID + ", FullName=" + FullName + ", title=" + title + ", Address=" + address + ", phoneNumber=" + phoneNumber + ", birthday=" + birthday + '}';
    }
    
   public void display(){
       System.out.printf("|%-5s|%-25s|%-25s|%-35s|%-12s|%-12s|\n",employeeID, FullName, title, address, birthday, phoneNumber );
   }
    
    
}

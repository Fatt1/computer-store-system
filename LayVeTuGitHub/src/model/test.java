/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.EmployeeManager;
import controller.ImportInvoiceManager;
import controller.OrderDetailsManager;
import controller.OrderManager;
import controller.ProductManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ui.CustomerUI;
import ui.EmployeeUI;
import ui.ImportInvoiceDetailsUI;
import ui.ImportInvoiceUI;
import ui.OrderDetailsUI;
import ui.OrderUI;
import ui.ProductUI;
import ui.StatisticUI;
import ui.SupplierUI;
import util.MyUtility;

/**^
 *
 * 
 * @author User
 */
public class test {
    public static void main(String[] args) {
       ProductManager pm = new ProductManager();
       pm.loadDataFromFile("DBStore/Products.dat");
       pm.filterProduct();
        
    }
}

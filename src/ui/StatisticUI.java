/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import controller.CustomerManager;
import controller.OrderManager;
import controller.ProductManager;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class StatisticUI {
    public void openSystem(){
        Menu menu = new Menu("THONG KE CO BAN");
        menu.addNewOption("1. THONG KE TOP 3 SAN PHAM BAN CHAY NHAT");
        menu.addNewOption("2. THONG KE SAN PHAM CON TON KHO IT");
        menu.addNewOption("3. THONG KE DOANH THU THEO NAM THANG");
        menu.addNewOption("4. THONG KE DOANH THU CUA TUNG KHACH HANG");
        menu.addNewOption("5. Exit");
        
        int choice;
       
        do {
            System.out.println("+------------------------------------------+");
            System.out.println("|                MENU CHINH                |");
            System.out.println("+------------------------------------------+");
            menu.printMenu();
            System.out.println("+------------------------------------------+");
            choice = menu.getChoice();
            System.out.println();           
            switch (choice) {
            case 1:
                ProductManager pm = new ProductManager();
                pm.loadDataFromFile("DBStore/Products.dat");
                pm.showBestSellingProducts();
                break;
            case 2:
                ProductManager pm2 = new ProductManager();
                pm2.loadDataFromFile("DBStore/Products.dat");
                pm2.showLowStockProducts();
                break;
            case 3:
                OrderManager om = new OrderManager();
                om.loadDataFromFile("DBStore/Orders.dat");
                om.showRevenue();
                break;
            case 4:
                 CustomerManager cm = new CustomerManager();
                cm.loadDataFromFile("DBStore/Customers.dat");
                cm.statisticEachCustomer();
                break; 
        }
            if(choice != 5){
                System.out.println("\nNhan Enter de tiep tuc...");
                new Scanner(System.in).nextLine();
            }
        } while (choice != 5);
        
    }
}

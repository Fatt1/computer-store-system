/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import controller.CustomerManager;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class CustomerUI {
    public void openSystem(){
        Menu menu = new Menu("QUAN LI KHACH HANG");
        
        menu.addNewOption("1. Hien thi khach hang tang dan theo ID ");
        menu.addNewOption("2. Hien thi khach hang tang dan theo ten");
        menu.addNewOption("3. Them khach hang moi");
        menu.addNewOption("4. Tim kiem khach hang theo ID");
        menu.addNewOption("5. Tim kiem khach hang theo ten");
        menu.addNewOption("6. Cap nhat thong tin khach hang");
        menu.addNewOption("7. Xoa khach hang");
        menu.addNewOption("8. Luu file");
        menu.addNewOption("9. Thoat");
        
        CustomerManager cm = new CustomerManager();
        cm.loadDataFromFile("DBStore/Customers.dat");
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
                    System.out.println(">>> HIEN THI KHACH HANG TANG DAN THEO ID");
                    cm.printListAscendingByID();
                    break;
                case 2:
                    System.out.println(">>> HIEN THI KHACH HANG TANG DAN THEO TEN");
                    cm.printCusListAscendingByName();
                    break;
                case 3:
                    System.out.println(">>> THEM KHACH HANG MOI");
                    cm.addNew();
                    break;
                case 4:
                    System.out.println(">>> TIM KIEM KHACH HANG THEO ID");
                    cm.searchByID();
                    break;
                case 5:
                    System.out.println(">>> TIM KIEM KHACH HANG THEO TEN");
                    cm.searchByName();
                    break;
                case 6:
                    System.out.println(">>> CAP NHAT THONG TIN KHACH HANG");
                    cm.updateObjectByID();
                    break;
                case 7:
                    System.out.println(">>> XOA KHACH HANG");
                    cm.removeOjectByID();
                    
                    break;
                case 8: 
                   System.out.println(">>> LUU FILE");
                   cm.saveToFile("DBStore/Customers.dat");
                   cm.loadDataFromFile("DBStore/Customers.dat");
                   break;
            }

            if (choice != 9) {
                System.out.println("\nNhan Enter de tiep tuc...");
                // Anomymous Object, không cần đặt tên, chỉ cần sử dụng 1 lần
                new Scanner(System.in).nextLine();  // Dung man hinh cho nguoi dung nhan Enter de tiep tuc
            }
        } while (choice != 9);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import controller.SupplierManager;

/**
 *
 * @author User
 */
public class SupplierUI {
    public void openSystem() {
        Menu menu = new Menu("QUAN LY NHA CUNG UNG");
        menu.addNewOption("1. Hien thi danh sach nha cung cap");
        menu.addNewOption("2. Them nha cung ung moi");
        menu.addNewOption("3. Tim kiem nha cung cap theo ID");
        menu.addNewOption("4. Tim kiem nha cung cap theo ten");
        menu.addNewOption("5. Cap nhat thong tin nha cung cap");
        menu.addNewOption("6. Xoa nha cung cap");
        menu.addNewOption("7. Luu file");
        menu.addNewOption("8. Thoat");

        SupplierManager sm = new SupplierManager();

        int choice;
        sm.loadDataFromFile("DBStore/Suppliers.dat");
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
                    System.out.println(">>> HIEN THI DANH SACH");
                    sm.printListAscendingByID();
                    break;
                case 2:
                    System.out.println(">>> THEM NHA CUNG CAP MOI");
                    sm.addNew();
                    break;
                case 3:
                    System.out.println(">>> TIM KIEM NHA CUNG CAP THEO ID");
                    sm.searchByID();
                    break;
                case 4:
                    System.out.println(">>> TIM KIEM NHA CUNG CAP THEO TEN");
                    sm.searchSupplierByName();
                    break;
                case 5:
                    System.out.println(">>> CAP NHAT THONG TIN NHA CUNG CAP");
                    sm.updateObjectByID();
                    break;
                case 6:
                    System.out.println(">>> XOA NHA CUNG CAP");
                    sm.removeOjectByID();
                    break;
                case 7:
                    System.out.println(">>> LUU FILE");
                    sm.saveToFile("DBStore/Suppliers.dat");
                    sm.loadDataFromFile("DBStore/Suppliers.dat");
                    break;
                
            }

            if (choice != 8) {
                System.out.println("\nNhan Enter de tiep tuc...");
                new java.util.Scanner(System.in).nextLine();  // Dung man hinh cho nguoi dung nhan Enter de tiep tuc
            }
        } while (choice != 8);
    }

}

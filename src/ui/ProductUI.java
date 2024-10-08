/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import controller.ProductManager;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ProductUI {
    public void openSystem() {
        Menu menu = new Menu("QUAN LY SAN PHAM");
        menu.addNewOption("1. Hien thi danh sach san pham");
        menu.addNewOption("2. Them san pham moi");
        menu.addNewOption("3. Tim kiem san pham theo ID");
        menu.addNewOption("4. Tim kiem san pham theo ten");
        menu.addNewOption("5. Cap nhat thong tin san pham");
        menu.addNewOption("6. Xoa san pham");
        menu.addNewOption("7. Loc san pham");
        menu.addNewOption("8. Luu file");
        menu.addNewOption("9. Thoat");

        ProductManager pm = new ProductManager();

        int choice;
        pm.loadDataFromFile("DBStore/Products.dat");
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
                    System.out.println(">>> HIEN THI DANH SACH SAN PHAM");
                    pm.printListAscendingByID();
                    break;
                case 2:
                    System.out.println(">>> THEM SAN PHAM MOI");
                    pm.addNew();
                    break;
                case 3:
                    System.out.println(">>> TIM KIEM SAN PHAM THEO ID");
                    pm.searchByID();
                    break;
                case 4:
                    System.out.println(">>> TIM KIEM SAN PHAM THEO TEN");
                    pm.searchProductByName();
                    break;
                case 5:
                    System.out.println(">>> CAP NHAT THONG TIN SAN PHAM");
                    pm.updateObjectByID();
                    break;
                case 6:
                    System.out.println(">>> XOA SAN PHAM");
                    pm.removeOjectByID();
                    break;
                    
                case 7:
                    System.out.println(">>> LOC SAN PHAM");
                    pm.filterProduct();
                    break;
                case 8:
                    System.out.println(">>> LUU FILE");
                    pm.saveToFile("DBStore/Products.dat");
                    pm.loadDataFromFile("DBStore/Products.dat"); // load lại data để có full dữ liệu từ trong file
                    break;
                
            }

            if (choice != 9) {
                System.out.println("\nNhan Enter de tiep tuc...");
                new Scanner(System.in).nextLine();  // Dung man hinh cho nguoi dung nhan Enter de tiep tuc
            }
        } while (choice != 9);
    }
}

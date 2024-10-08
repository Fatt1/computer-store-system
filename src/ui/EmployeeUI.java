/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import controller.EmployeeManager;
import controller.ProductManager;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class EmployeeUI {
     public void openSystem() {
        Menu menu = new Menu("QUAN LY NHAN VIEN");
        menu.addNewOption("1. Hien thi danh sach nhan vien");
        menu.addNewOption("2. Them nhan vien moi");
        menu.addNewOption("3. Tim kiem nhan vien theo ID");
        menu.addNewOption("4. Tim kiem nhan vien theo ten");
        menu.addNewOption("5. Loc nhan vien theo chuc vu");
        menu.addNewOption("6. Loc nhan vien theo dia chi");
        menu.addNewOption("7. Cap nhat thong tin nhan vien");
        menu.addNewOption("8. Xoa nhan vien");
        menu.addNewOption("9. Luu file");
        menu.addNewOption("10. Thoat");

         EmployeeManager em = new EmployeeManager();

        int choice;
        em.loadDataFromFile("DBStore/Employees.dat");
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
                System.out.println(">>> HIEN THI DANH SACH NHAN VIEN");
                em.printListAscendingByID();
                break;
            case 2:
                System.out.println(">>> THEM NHAN VIEN MOI");
                em.addNew(); // Đổi thành addNewEmployee
                break;
            case 3:
                System.out.println(">>> TIM KIEM NHAN VIEN THEO ID");
                em.searchByID(); // Giữ nguyên
                break;
            case 4:
                System.out.println(">>> TIM KIEM NHAN VIEN THEO TEN");
                em.searchByName(); // Đổi thành searchByName
                break;
            case 5:
                System.out.println(">>> LOC NHAN VIEN THEO CHUC VU");
                em.filterTitle();// Đổi thành searchByName
                break;
            case 6:
                System.out.println(">>> LOC NHAN VIEN THEO DIA CHI");
                em.filterAdress();// Đổi thành searchByName
                break;    
            case 7:
                System.out.println(">>> CAP NHAT THONG TIN NHAN VIEN");
                em.updateObjectByID();// Đổi thành updateEmployeeByID
                break;
            case 8:
                System.out.println(">>> XOA NHAN VIEN");
                em.removeOjectByID();// Đổi thành deleteEmployeeByID
                break;
            case 9:
                System.out.println(">>> LUU FILE");
                em.saveToFile("DBStore/Employees.dat"); // Chỉnh lại tên file cho đúng
                em.loadDataFromFile("DBStore/Employees.dat");
                break;
            
            }

            if (choice != 10) {
                System.out.println("\nNhan Enter de tiep tuc...");
                new Scanner(System.in).nextLine();  // Dung man hinh cho nguoi dung nhan Enter de tiep tuc
            }
        } while (choice != 10);
    }
}

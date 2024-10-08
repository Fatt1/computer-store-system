/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import controller.EmployeeManager;
import controller.OrderManager;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class OrderUI {
    public void openSystem() {
    Menu menu = new Menu("QUAN LY DON HANG");
    menu.addNewOption("1. Hien thi danh sach don hang");
    menu.addNewOption("2. Hien thi chi tiet don hang");
    menu.addNewOption("3. Them don hang moi");
    menu.addNewOption("4. Tim kiem don hang theo ID");
    menu.addNewOption("5. Tim kiem cac don hang ma nhan vien da nhap");
    menu.addNewOption("6. Tim kiem cac don hang cua khach hang");
    menu.addNewOption("7. Cap nhat thong tin don hang");
    menu.addNewOption("8. Xoa don hang");
    menu.addNewOption("9. Luu file");
    menu.addNewOption("10. Thoat");

    OrderManager om = new OrderManager();

    int choice;
    om.loadDataFromFile("DBStore/Orders.dat"); // Đổi tên file cho đúng
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
                System.out.println(">>> HIEN THI DANH SACH DON HANG");
                om.printListAscendingByID(); // Hiển thị danh sách đơn hàng
                break;
            case 2:
                System.out.println(">>> HIEN THI CHI TIET DON HANG");
                om.showOrderDetails();
                break;
            case 3:
                System.out.println(">>> THEM DON HANG MOI");
                om.addNew();// Thêm đơn hàng mới
                break;
            case 4:
                System.out.println(">>> TIM KIEM DON HANG THEO ID");
                om.searchByOrderID(); // Tìm kiếm đơn hàng theo ID
                break;
            case 5:
                System.out.println(">>> TIM KIEM CAC DON HANG MA NHAN VIEN DA NHAP");
                om.searchOrderByEmployeeID();
                break;
            case 6:
                System.out.println(">>> TIM KIEM CAC DON HANG MA CUA KHACH HANG");
                om.searchOrderByCusTomerID();
                break;
            case 7:
                System.out.println(">>> CAP NHAT THONG TIN DON HANG");
                om.updateObjectByID();// Cập nhật thông tin đơn hàng
                break;
                
            case 8:
                System.out.println(">>> XOA DON HANG");
                om.removeOjectByID();
                break;
            case 9:
                System.out.println(">>> LUU FILE");
                om.saveToFile("DBStore/Orders.dat"); // Lưu dữ liệu vào file
                break;
            
        }

        if (choice != 10) {
            System.out.println("\nNhan Enter de tiep tuc...");
            new Scanner(System.in).nextLine(); // Dừng màn hình cho người dùng nhấn Enter
        }
    } while (choice != 10);
    }
}

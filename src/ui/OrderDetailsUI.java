/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import controller.OrderDetailsManager;
import controller.OrderManager;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class OrderDetailsUI {
    public void openSystem() {
    Menu menu = new Menu("QUAN LY CHI TIET DON HANG");
    menu.addNewOption("1. Hien thi danh sach chi tiet don hang");
    menu.addNewOption("2. Them chi tiet don hang moi");
    menu.addNewOption("3. Tim kiem chi tiet don hang theo ID don hang");
    menu.addNewOption("4. Tim kiem chi tiet don hang theo ID san pham");
    menu.addNewOption("5. Cap nhat thong tin chi tiet don hang");
    menu.addNewOption("6. Xoa chi tiet don hang");
    menu.addNewOption("7. Luu file");
    menu.addNewOption("8. Thoat");

        OrderDetailsManager odm = new OrderDetailsManager();

    int choice;
    odm.loadDataFromFile("DBStore/OrderDetails.dat"); // Đổi tên file cho đúng
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
                System.out.println(">>> HIEN THI DANH SACH CHI TIET DON HANG");
                odm.printListAscendingByID(); // Hiển thị danh sách chi tiết đơn hàng
                break;
            case 2:
                System.out.println(">>> THEM CHI TIET DON HANG MOI");
                odm.addNew(); // Thêm chi tiết đơn hàng mới
                break;
            case 3:
                System.out.println(">>> TIM KIEM CHI TIET DON HANG THEO ID DON HANG");
                odm.searchByID(); // Tìm kiếm chi tiết đơn hàng theo ID
                break;
                
            case 4:
                System.out.println(">>> TIM KIEM CHI TIET DON HANG THEO ID SAN PHAM");
                odm.searchOrderDetailsByProductID(); // Tìm kiếm chi tiết đơn hàng theo ID
                break;    
                
            case 5:
                System.out.println(">>> CAP NHAT THONG TIN CHI TIET DON HANG");
                odm.updateObjectByID(); // Cập nhật thông tin chi tiết đơn hàng
                break;
            case 6:
                System.out.println(">>> XOA CHI TIET DON HANG");
                odm.removeOjectByID(); // Xóa chi tiết đơn hàng
                break;
            case 7:
                System.out.println(">>> LUU FILE");
                odm.saveToFile("DBStore/OrderDetails.dat"); // Lưu dữ liệu vào file
                break;
            
        }
        if (choice != 8) {
            System.out.println("\nNhan Enter de tiep tuc...");
            new Scanner(System.in).nextLine(); // Dừng màn hình cho người dùng nhấn Enter
        }
    } while (choice != 8);
    }
}

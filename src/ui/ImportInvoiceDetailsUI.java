/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import controller.ImportDetailsManager;
import controller.OrderDetailsManager;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ImportInvoiceDetailsUI {

    public void openSystem() {
        Menu menu = new Menu("QUAN LY CHI TIET PHIEU NHAP");
        menu.addNewOption("1. Hien thi danh sach chi tiet phieu nhap");
        menu.addNewOption("2. Them chi tiet phieu nhap moi");
        menu.addNewOption("3. Tim kiem chi tiet phieu nhap theo ID phieu nhap");
        menu.addNewOption("4. Tim kiem chi tiet phieu nhap theo ID san pham ");        
        menu.addNewOption("5. Cap nhat thong tin chi tiet phieu nhap");
        menu.addNewOption("6. Xoa chi tiet phieu nhap");
        menu.addNewOption("7. Luu file");
        menu.addNewOption("8. Thoat");

        ImportDetailsManager idm = new ImportDetailsManager();

        int choice;
        idm.loadDataFromFile("DBStore/ImportDetails.dat"); // Đổi tên file cho đúng
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
                    System.out.println(">>> HIEN THI DANH SACH CHI TIET PHIEU NHAP");
                    idm.printListAscendingByID(); // Hiển thị danh sách chi tiết phiếu nhập
                    break;
                case 2:
                    System.out.println(">>> THEM CHI TIET PHIEU NHAP MOI");
                    idm.addNew(); // Thêm chi tiết phiếu nhập mới
                    break;
                case 3:
                    System.out.println(">>> TIM KIEM CHI TIET PHIEU NHAP THEO ID");
                    idm.searchByID(); // Tìm kiếm chi tiết phiếu nhập theo ID
                    break;
                
                case 4:
                    System.out.println(">>> TIM KIEM CHI TIET PHIEU NHAP THEO ID SAN PHAM");
                    idm.searchImportDetailsByProductID(); // Tìm kiếm chi tiết phiếu nhập theo ID
                    break;
                    
                case 5:
                    System.out.println(">>> CAP NHAT THONG TIN CHI TIET PHIEU NHAP");
                    idm.updateObjectByID(); // Cập nhật thông tin chi tiết phiếu nhập
                    break;
                case 6:
                    System.out.println(">>> XOA CHI TIET PHIEU NHAP");
                    idm.removeOjectByID(); // Xóa chi tiết phiếu nhập
                    break;
                case 7:
                    System.out.println(">>> LUU FILE");
                    idm.saveToFile("DBStore/ImportDetails.dat"); // Lưu dữ liệu vào file
                    break;
                
            }

            if (choice != 8) {
                System.out.println("\nNhan Enter de tiep tuc...");
                new Scanner(System.in).nextLine(); // Dừng màn hình cho người dùng nhấn Enter
            }
        } while (choice != 8);
    }
}

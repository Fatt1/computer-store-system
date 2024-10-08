/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import controller.ImportInvoiceManager;
import controller.OrderManager;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ImportInvoiceUI {

    public void openSystem() {
        Menu menu = new Menu("QUAN LY PHIEU NHAP");
        menu.addNewOption("1. Hien thi danh sach phieu nhap");
        menu.addNewOption("2. Hien thi chi tiet phieu nhap");
        menu.addNewOption("3. Them phieu nhap moi");
        menu.addNewOption("4. Tim kiem phieu nhap theo ID");
        menu.addNewOption("5. Cap nhat thong tin phieu nhap");
        menu.addNewOption("6. Xoa phieu nhap");
        menu.addNewOption("7. Luu file");
        menu.addNewOption("8. Thoat");

        ImportInvoiceManager im = new ImportInvoiceManager();

        int choice;
        im.loadDataFromFile("DBStore/ImportInvoice.dat"); // Đổi tên file cho đúng
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
                    System.out.println(">>> HIEN THI DANH SACH PHIEU NHAP");
                    im.printListAscendingByID(); // Hiển thị danh sách phiếu nhập
                    break;
                case 2:
                    System.out.println(">>> HIEN THI CHI TIET PHIEU NHAP");
                    im.showImportDetails();// Hiển thị chi tiết phiếu nhập
                    break;
                case 3:
                    System.out.println(">>> THEM PHIEU NHAP MOI");
                    im.addNew();// Thêm phiếu nhập mới
                    break;
                case 4:
                    System.out.println(">>> TIM KIEM PHIEU NHAP THEO ID");
                    im.searchByImportID();// Tìm kiếm phiếu nhập theo ID
                    break;
                case 5:
                    System.out.println(">>> CAP NHAT THONG TIN PHIEU NHAP");
                    im.updateObjectByID(); // Cập nhật thông tin phiếu nhập
                    break;
                case 6:
                    System.out.println(">>> XOA PHIEU NHAP");
                    im.removeOjectByID(); // Xóa phiếu nhập
                    break;
                case 7:
                    System.out.println(">>> LUU FILE");
                    im.saveToFile("DBStore/ImportInvoice.dat"); // Lưu dữ liệu vào file phiếu nhập
                    break;
                
            }

            if (choice != 8) {
                System.out.println("\nNhan Enter de tiep tuc...");
                new Scanner(System.in).nextLine(); // Dừng màn hình cho người dùng nhấn Enter
            }
        } while (choice != 8);
    }
}

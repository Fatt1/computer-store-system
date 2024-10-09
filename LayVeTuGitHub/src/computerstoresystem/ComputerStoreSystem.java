/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package computerstoresystem;

import java.util.Scanner;
import ui.CustomerUI;
import ui.EmployeeUI;
import ui.ImportInvoiceDetailsUI;
import ui.ImportInvoiceUI;
import ui.Menu;
import ui.OrderDetailsUI;
import ui.OrderUI;
import ui.ProductUI;
import ui.StatisticUI;
import ui.SupplierUI;

/**
 *
 * @author User
 */
public class ComputerStoreSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   
        Menu menu = new Menu("CHAO MUNG TOI QUAN LI CUA HANG MAY TINH 1");
        menu.addNewOption("1. Quan li san pham");
        menu.addNewOption("2. Quan li khach hang");
        menu.addNewOption("3. Quan li nha cung cap");
        menu.addNewOption("4. Quan li nhan vien");
        menu.addNewOption("5. Quan li hoa don");
        menu.addNewOption("6. Quan li chi tiet hoa don");
        menu.addNewOption("7. Quan li phieu nhap");
        menu.addNewOption("8. Quan li chi tiet phieu nhap");
        menu.addNewOption("9. Thong ke");
        menu.addNewOption("10. Exit");

        int choice;

        do {
            menu.printMenu();
            System.out.println("=====================================");
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    ProductUI pu = new ProductUI();
                    System.out.println(">>quan ly san pham");
                    pu.openSystem(); // Gọi phương thức quản lý sản phẩm
                    break;
                case 2:
                    CustomerUI cu = new CustomerUI();
                    System.out.println(">>QUAN LI KHACH HANG");
                    cu.openSystem(); // Gọi phương thức quản lý khách hàng
                    break;
                case 3:
                    SupplierUI su = new SupplierUI();
                    System.out.println(">>QUAN LI NHA CUNG CAP");
                    su.openSystem(); // Gọi phương thức quản lý nhà cung cấp
                    break;
                case 4:
                    EmployeeUI eu = new EmployeeUI();
                    System.out.println(">>QUAN LI NHAN VIEN");
                    eu.openSystem(); // Gọi phương thức quản lý nhân viên
                    break;
                case 5:
                    OrderUI om = new OrderUI();
                    System.out.println(">>QUAN LI HOA DON");
                    om.openSystem(); // Gọi phương thức quản lý hóa đơn
                    break;
                case 6:
                    OrderDetailsUI odm = new OrderDetailsUI();
                    System.out.println(">>QUAN LI CHI TIET HOA DON");
                    odm.openSystem(); // Gọi phương thức quản lý chi tiết hóa đơn
                    break;
                case 7:
                    ImportInvoiceUI iim = new ImportInvoiceUI();
                    System.out.println(">>QUAN LI PHIEU NHAP");
                    iim.openSystem(); // Gọi phương thức quản lý phiếu nhập
                    break;
                case 8:
                    System.out.println(">>QUAN LI CHI TIET PHIEU NHAP");
                    ImportInvoiceDetailsUI idm = new ImportInvoiceDetailsUI();
                    idm.openSystem(); // Gọi phương thức quản lý chi tiết phiếu nhập
                    break;
                case 9:
                    System.out.println(">>THONG KE");
                    StatisticUI s = new StatisticUI();
                    s.openSystem();
                    break;
                case 10:
                    System.out.println("BYE BYE");
                    break;    
            }
        } while (choice != 10);

    }
}    

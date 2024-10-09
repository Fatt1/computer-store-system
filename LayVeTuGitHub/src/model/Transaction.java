/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public abstract class Transaction implements Serializable{

    protected String id;               // Mã phiếu hoặc đơn hàng
    protected String employeeID;    // Mã nhân viên
    protected LocalDateTime date = LocalDateTime.now();          // Ngày lập
    protected String status;        // Trạng thái (đã xuất, đang xử lý, đã nhập,...)

    public Transaction() {
    }

    public Transaction(String id, String employeeID, String status) {
        this.id = id;
        this.employeeID = employeeID;
        this.status = status;
        
    }

    // Các phương thức getter, setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void input() {
        
        do {
            employeeID = MyUtility.getId("Nhap ID NV (EXXX): ", "Vui long nhap theo dinh dang EXXX, "
                    + "X dai dien cho so (0 - 9) ", "^[e|E]\\d{3}$");
            if (!MyUtility.checkEmployeeID(employeeID)) {
                System.out.println("ID NV nay khong ton tai. Vui long nhap ID NV da co san!!");
            }
        } while (!MyUtility.checkEmployeeID(employeeID));
        Menu mStatus = new Menu("Chon trang thai");
        mStatus.addNewOption("1. Hoan thanh");
        mStatus.addNewOption("2. Dang xu li");
        mStatus.addNewOption("3. Huy");
        mStatus.printMenu();
        int choice = mStatus.getChoice();
        switch (choice) {
            case 1:
                status = "HOAN THANH";
                break;
            case 2:
                status = "DANG XU LY";
                break;
            case 3:
                status = "HUY";
                break;    
        }

    }
    public abstract void display();

}

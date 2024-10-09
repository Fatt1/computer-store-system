/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public class Accessory extends Product {

    private String brand;
    private String color;

    public Accessory() {
    }

    public Accessory(String idProduct, String nameProduct, String nameSupplier, String brand, String color, int quanlity, double price) {
        super(idProduct, nameProduct, nameSupplier, quanlity, price);
        this.brand = brand;
        this.color = color;

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void input() {
        super.input();
        brand = MyUtility.getString("Nhap ten hang san xuat: ", "Vui long khong de trong!!");
        color = MyUtility.getString("Nhap mau sac: ", "Vui long khong de trong!!");
    }

    @Override
    public void display() {
        System.out.printf("|%-10s", "PHU KIEN");
        super.display();
        System.out.printf("%-15s|%-25s|%-5s|%-25s|\n", color, brand, "-", "-");
    }

    @Override
    public void updateDetails() {
        Menu updatePro = new Menu("Thong tin ban muon cap nhat");
        updatePro.addNewOption("1. Cac thuoc tinh chung (Ten SP, Nha cung cap, SL, Gia)");
        updatePro.addNewOption("2. Hang SX");
        updatePro.addNewOption("3. Mau sac");
        updatePro.addNewOption("4. Exit");
        updatePro.printMenu();
        int choice = updatePro.getChoice();
        if (choice == 4) {
            return;
        }
        switch (choice) {
            case 1:
                super.updateProduct();  // Cập nhật các thuộc tính chung
                break;
            case 2:
                brand = MyUtility.getString("Nhap hang sx moi: ", "Vui long khong de trong!!");
                break;
            case 3:
                color = MyUtility.getString("Nhap mau moi: ", "Vui long khong de trong!!");
                break;
        }
    }
}

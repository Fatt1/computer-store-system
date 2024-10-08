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
public class Laptop extends Product {

    private String color;
    private double weight;
    private String brand;

    public Laptop() {
    }

    public Laptop(String idProduct, String nameProduct, String nameSupplier, String color, String brand, double weight, int quanlity, double price) {
        super(idProduct, nameProduct, nameSupplier, quanlity, price);
        this.color = color;
        this.weight = weight;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void input() {
        super.input();
        weight = MyUtility.getADouble("Nhap trong luong: ", "Vui long nhap so!!");
        color = MyUtility.getString("Nhap mau sac: ", "Vui long khong de trong!!");
        brand = MyUtility.getString("Nhap ten hang san xuat: ", "Vui long khong de trong");
    }

    @Override
    public void display() {
        System.out.printf("|%-10s", "LAPTOP");
        super.display();
        System.out.printf("%-15s|%-25s|%-5.2f|%-25s|\n", color, brand, weight, "-");

    }

    @Override
    public void updateDetails() {
        Menu updatePro = new Menu("Thong tin ban muon cap nhat");
        updatePro.addNewOption("1. Cac thuoc tinh chung (Ten SP, Nha cung cap, SL, Gia)");
        updatePro.addNewOption("2. Hang SX");
        updatePro.addNewOption("3. Mau sac");
        updatePro.addNewOption("4. Trong luong");
        updatePro.addNewOption("5. Exit");
        updatePro.printMenu();
        int choice = updatePro.getChoice();
        if (choice == 5) {
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
            case 4: 
                weight = MyUtility.getADouble("Nhap trong luong moi: ", "Vui long nhap so!!");
                break;
        }
    }

}

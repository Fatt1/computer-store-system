/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Scanner;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public class Computer extends Product {

    private String powerSupply;

    public Computer() {
    }

    public Computer(String idProduct, String nameProduct, String nameSupplier, String powerSupply, int quanlity, double price) {
        super(idProduct, nameProduct, nameSupplier, quanlity, price);
        this.powerSupply = powerSupply;

    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    @Override
    public void input() {
        super.input();
        powerSupply = MyUtility.getString("Nhap ten bo nguon: ", "Vui long khong bo trong");

    }

    @Override
    public void display() {
        System.out.printf("|%-10s", "COMPUTER");
        super.display();
        System.out.printf("%-15s|%-25s|%-5s|%-25s|\n", "-", "-", "-", powerSupply);

    }

    @Override
    public void updateDetails() {
        Menu updatePro = new Menu("Thong tin ban muon cap nhat");
        updatePro.addNewOption("1. Cac thuoc tinh chung (Ten SP, Nha cung cap, SL, Gia)");
        updatePro.addNewOption("2. Bo nguon");
        updatePro.addNewOption("3. Exit");
        updatePro.printMenu();
        int choice = updatePro.getChoice();
        if (choice == 3) {
            return;
        }
        switch (choice) {
            case 1:
                super.updateProduct();  // Cập nhật các thuộc tính chung
                break;
            case 2:
                powerSupply = MyUtility.getString("Nhap ten nguon moi: ", "Vui long khong de trong!!");
                break;
           
        }
    }

}

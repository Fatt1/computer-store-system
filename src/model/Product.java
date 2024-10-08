/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
// phải implements Serializable để có thể lưu file theo kiểu Object
public abstract class Product implements Serializable {

    protected String productId; // nên cho final vào vì kh nên thay đổi id, mỗi thằng đều có id riêng
    // thay đổi trùng rất khó quản lí
    // id mặc định cho tất cả đối tượng Product là 4 kí tự
    protected String productName;
    protected String supplierName;
    protected int quanlity;
    protected double price;

    public Product() {
    }

    public Product(String idProduct, String nameProduct, String nameSupplier, int quanlity, double price) {
        this.productId = idProduct;
        this.productName = nameProduct;
        this.supplierName = nameSupplier;
        this.quanlity = quanlity;
        this.price = price;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void updateProduct() {
        Menu updatePro = new Menu("Thong tin ban muon cap nhat");
        updatePro.addNewOption("1. Ten san pham");
        updatePro.addNewOption("2. Ten nha cung cap");
        updatePro.addNewOption("3. So luong");
        updatePro.addNewOption("4. Gia ban");
        updatePro.printMenu();
        
        int choice = updatePro.getChoice();

        switch (choice) {
            case 1:
                productName = MyUtility.getString("Nhap ten san pham moi: ", "Vui long khong de trong!!");
                break;
            case 2:
                supplierName = MyUtility.getString("Nhap ten nha cung cap moi: ", "Vui long khong de trong!!");
                break;
            case 3:
                quanlity = MyUtility.getAnInteger("Nhap so luong moi: ", "Vui long nhap so nguyen!!");
                break;
            case 4:
                price = MyUtility.getADouble("Nhap don gia moi: ", "Vui long nhap so!!");
                break;
        }
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public abstract void updateDetails();

    public void input() {
        productName = MyUtility.getString("Nhap ten san pham: ", "Vui long khong de trong!!");
        supplierName = MyUtility.getString("Nhap ten nha cung cap: ", "Vui long khong de trong!!");
        quanlity = MyUtility.getAnInteger("Nhap SL: ", "Vui long nhap so nguyen!!");
        price = MyUtility.getADouble("Nhap don gia: ", "Vui long nhap so!!");

    }

    @Override
    public String toString() {
        return "Product{" + "idProduct=" + productId + ", nameProduct=" + productName + ", price=" + price + ", nameSupplier=" + supplierName + ", quanlity=" + quanlity + '}';
    }

    public void display() {
        System.out.printf("|%-6s|%-35s|%-25s|%-4d|%-8.2f|",
                productId, productName, supplierName, quanlity, price);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.*;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public class ProductManager implements IProductManager, IOFile {

    enum ProductCategory {
        ACCESSORY, LAPTOP, COMPUTER
    }

    enum Attribute {
        SUPPLIER("NHA CUNG CAP"),
        COLOR("MAU SAC"),
        BRAND("HANG SAN XUAT"),
        PRICE("GIA"),
        QUANTITY("SO LUONG"),
        POWER_SUPPLY("BO NGUON"),
        WEIGHT("TRONG LUONG");

        private final String displayName;

        Attribute(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName; // Trả về tên hiển thị
        }
    }
    private String header = String.format("|%-10s|%-6s|%-35s|%-25s|%-4s|%-8s|%-15s|%-25s|%-5s|%-25s|",
            "LOAI", "ID", "TEN SAN PHAM", "TEN NHA CUNG CAP", "SL", "DON GIA", "MAU SAC", "HANG SAN XUAT", "KG", "BO NGUON");
    private Scanner sc = new Scanner(System.in);
    private Product[] proList = new Product[0];
    private int count = proList.length;
    

    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < count; i++) {
            proList[i] = null;
        }
        count = 0;

    }

    @Override
    public void addNew() {
        String proID;
        int duplicate;
        Menu menu = new Menu("CHON LOAI SAN PHAM");
        menu.addNewOption("1.Accessory");
        menu.addNewOption("2.Computer");
        menu.addNewOption("3.Laptop");
        menu.printMenu();
        int choice = menu.getChoice();

        do {
            proID = MyUtility.getId("Nhap ID(PXXX): ", "Vui long nhap theo dinh dang PXXX, "
                    + "X dai dien cho so (0 - 9)", "^[p|P]\\d{3}$");
            duplicate = searchtByID(proID);
            if (duplicate >= 0) {
                System.out.println("ID da ton tai. Vui long nhap ID khac!!!");
            }

        } while (duplicate != -1);

        proList = Arrays.copyOf(proList, count + 1);
        switch (choice) {
            case 1:
                Accessory x = new Accessory();
                x.setProductId(proID);
                x.input();
                proList[count++] = x;

                break;
            case 2:
                Computer c = new Computer();
                c.setProductId(proID);
                c.input();
                proList[count++] = c;
                break;
            case 3:
                Laptop l = new Laptop();
                l.setProductId(proID);
                l.input();
                proList[count++] = l;
                break;
        }
        System.out.println("Them san pham thanh cong");

    }

    @Override
    public void updateObjectByID() {
        String proID = MyUtility.getString("Vui long nhap ID san pham muon cap nhat: ", "Vui long khong de trong!!");
        Product x = searchObjectByID(proID);
        if (x == null) {
            System.out.println("San pham khong ton tai");
        } else {
            System.out.println("Day la san pham cua ban muon cap nhat");
            System.out.println(header);
            x.display();
            x.updateDetails();
            System.out.println("Cap nhat thong tin thanh cong");
        }
    }

    @Override
    public void removeOjectByID() {
        String proID = MyUtility.getString("Vui long nhap ID san pham muon xoa: ", "Vui long khong de trong!!");
        int pos = searchtByID(proID);
        if (pos == -1) {
            System.out.println("San pham khong ton tai!!");
            return;
        }
        String choice;
        System.out.print("Ban co chac xoa san pham nay (Y/N):");
        do {
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                for (int i = pos; i < count - 1; i++) {
                    proList[i] = proList[i + 1]; // con trỏ cuối cùng sẽ trỏ tới object cuối cùng mà chúng ta ko sử dụng nữa
                    // vì object cuối đã đc dịch lên trên
                    // object cần xóa đã không còn đc trỏ nữa
                    // nhưng vẫn có vùng nhớ trong prolist, nên mình cần phải loại bỏ nó luôn
                }
                proList = Arrays.copyOf(proList, count - 1);
                count--;
                System.out.println("Xoa san pham thanh cong");
                break;
            } else if (choice.equalsIgnoreCase("N")) {
                break;
            }
            System.out.println("Vui long chon (Y/N)");
        } while (true);
    }

    @Override
    public void searchByID() {
        String proID = MyUtility.getString("Vui long nhap ID san pham can tim: ", "Vui long khong de trong!!");
        Product x = searchObjectByID(proID);
        if (x == null) {
            System.out.println("San pham khong ton tai!!");
            return;
        }
        System.out.println("Day la san pham ban tim kiem");
        System.out.println(header);
        x.display();
    }

    @Override
    public Product searchObjectByID(String proID) {
        if (isEmpty()) {
            return null;
        }
        for (Product p : proList) {
            if (p.getProductId().equalsIgnoreCase(proID)) {
                return p; // trả về tọa đồ mà trùng vs id mà mình tìm kiếm
            }
        }
        return null;
    }

    @Override
    public int searchtByID(String proID) {
        if (isEmpty()) {
            return -1;
        }
        for (int i = 0; i < count; i++) {
            if (proList[i].getProductId().equalsIgnoreCase(proID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void searchProductByName() {
        String proName = MyUtility.getString("Nhap ten san pham ma ban muon tim kiem: ", "Vui long khong de trong!!");
        Product res[] = searchProductByName(proName);
        if (res.length == 0) {
            System.out.println("DANH SACH RONG!!");
            return;
        }
        System.out.println("Day la danh sach san pham ma ban tim kiem");
        System.out.println(header);
        for (Product re : res) {
            re.display();
        }
    }

    @Override
    public Product[] searchProductByName(String proName) {
        Product[] res = new Product[0];
        int n = res.length;
        for (Product p : proList) {
            if (p.getProductName().contains(proName)) {
                res = Arrays.copyOf(res, n + 1);
                res[n++] = p;
            }
        }
        return res;
    }

    public Product[] getList() {
        loadDataFromFile("DBStore/Products.dat");
        return proList;
    }

    @Override
    public Product[] filterCategory(ProductCategory category) {
        Product[] res = new Product[0];
        int n = 0;  // Số lượng phần tử trong mảng kết quả

        for (Product p : proList) {

            switch (category) {
                case COMPUTER:
                    if (p instanceof Computer) {
                        res = Arrays.copyOf(res, n + 1); // Tăng kích thước mảng lên 1
                        res[n++] = p; // Gán phần tử và tăng `n`
                    }
                    break;
                case LAPTOP:
                    if (p instanceof Laptop) {
                        res = Arrays.copyOf(res, n + 1);
                        res[n++] = p;
                    }
                    break;
                case ACCESSORY:
                    if (p instanceof Accessory) {
                        res = Arrays.copyOf(res, n + 1);
                        res[n++] = p;
                    }
                    break;
            }
        }
        return res; // Trả về mảng sản phẩm đã lọc theo `category`
    }

    @Override
    public Product[] filterAtribute(ProductCategory category, Attribute attribute, String value) {
        Product productCategory[] = filterCategory(category);
        Product res[] = new Product[0];
        int n = res.length;
        for (Product p : productCategory) {
            switch (attribute) {
                case SUPPLIER:
                    if (p.getSupplierName().contains(value)) {
                        res = Arrays.copyOf(res, n + 1);
                        res[n++] = p;
                    }
                    break;
                case BRAND:
                    if (p instanceof Accessory && ((Accessory) p).getBrand().contains(value)
                            || p instanceof Laptop && ((Laptop) p).getBrand().contains(value)) {
                        res = Arrays.copyOf(res, n + 1);
                        res[n++] = p;
                    }
                    break;

                case PRICE:
                    if (p.getPrice() <= Double.parseDouble(value)) {
                        res = Arrays.copyOf(res, n + 1);
                        res[n++] = p;
                    }
                    break;

                case QUANTITY:
                    if (p.getQuanlity() <= Integer.parseInt(value)) {
                        res = Arrays.copyOf(res, n + 1);
                        res[n++] = p;
                    }
                    break;

                case COLOR:
                    if (p instanceof Accessory && ((Accessory) p).getColor().contains(value)
                            || p instanceof Laptop && ((Laptop) p).getColor().contains(value)) {
                        res = Arrays.copyOf(res, n + 1);
                        res[n++] = p;
                    }
                    break;

                case WEIGHT:
                    if (((Laptop) p).getWeight() <= Double.parseDouble(value)) {
                        res = Arrays.copyOf(res, n + 1);
                        res[n++] = p;
                    }
                    break;

                case POWER_SUPPLY:
                    if (((Computer) p).getPowerSupply().contains(value)) {
                        res = Arrays.copyOf(res, n + 1);
                        res[n++] = p;
                    }
                    break;
            }
        }
        return res;
    }
    
    private int attributeChoice;
    public Menu generalAttributeMenu(){
        Menu menu = new Menu("Loc thong tin chi tiet");
        menu.addNewOption("1. NHA CUNG CAP");
        menu.addNewOption("2. GIA");
        menu.addNewOption("3. SO LUONG");
        return  menu;
        
    }
    
    @Override
    public Menu laptopSpecificMenu(){
        Menu laptopMenu = generalAttributeMenu();
        laptopMenu.addNewOption("4. HANG SAN XUAT");
        laptopMenu.addNewOption("5. MAU SAC");
        laptopMenu.addNewOption("6. TRONG LUONG");
         return laptopMenu;
    }
    
    @Override
    public Menu computerSpecificMenu(){
        Menu computerMenu = generalAttributeMenu();
        computerMenu.addNewOption("4. BO NGUON");
        return computerMenu;
    }
    
    @Override
    public Menu accessorySpecificMenu(){
        Menu accessoryMenu = generalAttributeMenu();
        accessoryMenu.addNewOption("4. HANG SAN XUAT");
        accessoryMenu.addNewOption("5. MAU SAC");
        return accessoryMenu;
    }
    @Override
    public void filterProduct() {
        Menu attributeMenu = null;
        Menu categoryMenu = new Menu("CHON NHOM SAN PHAM");
        categoryMenu.addNewOption("1. LAPTOP");
        categoryMenu.addNewOption("2. COMPUTER");
        categoryMenu.addNewOption("3. ACCESSORY");

        categoryMenu.printMenu();
        int categoryChoice = categoryMenu.getChoice();

        ProductCategory cate = null;
        switch (categoryChoice) {
            case 1:
                cate = ProductCategory.LAPTOP;
                attributeMenu = laptopSpecificMenu();
                
                break;
            case 2:
                cate = ProductCategory.COMPUTER;
                attributeMenu = computerSpecificMenu();
                break;
            case 3:
                cate = ProductCategory.ACCESSORY;
                attributeMenu = accessorySpecificMenu();
                break;
        }
        // nhập lựa chọn cuối cùng của mỗi menu 
        // lấy lựa chọn max + 1 rồi .EXit để tạo ra Menu hoàn chỉnh
        // vd trong menu có 6 lựa chọn rồi thì mình lấy lựa chọn cuối cùng là 6 + 1 = 7. Exit để tạo ra menu hoàn chỉnh
        // không có exit là lặp tới chếtttt
        attributeMenu.addNewOption(String.valueOf(attributeMenu.getMaxChoice() + 1) + ". EXIT");
        int attributeChoice;
       
        Attribute at = null;
        do { 
            // để trong do while để người dùng nhập chọn laptop rồi xong chọn tiếp những thuộc tính của laptop
            // chọn xong r cho chọn lại tiếp thay vì thoát ra chọn nhóm rồi lại chọn thuộc tính
            // in ra danh sách theo nhóm (Laptop, Computer, Accessory)..
            System.out.println(header);
            for (Product x : filterCategory(cate)) {
                x.display();
            }
            
            attributeMenu.printMenu();
            attributeChoice = attributeMenu.getChoice();
            switch (attributeChoice) {
                case 1:
                    at = Attribute.SUPPLIER;
                    break;
                case 2:
                    at = Attribute.PRICE;
                    break;
                case 3:
                    at = Attribute.QUANTITY;
                    break;
                case 4:
                    if(cate == ProductCategory.COMPUTER)
                        at = Attribute.POWER_SUPPLY;
                    else
                        at = Attribute.BRAND;
                    break;
                case 5:
                    at = Attribute.COLOR;
                    break;
                case 6:
                    at = Attribute.WEIGHT;
                    break;
                
            }

            if (attributeChoice != attributeMenu.getMaxChoice()) {
                String value = MyUtility.getString("Nhap thong tin ban muon loc: ", "Vui long khong de trong!!");

                Product[] res = filterAtribute(cate, at, value);
                if (res.length == 0) {
                    System.out.println("RONG!!");
                    return;
                }
                System.out.println(header);
                for (Product p : res) {
                    p.display();
                }
                System.out.println("Nhap enter de tiep tuc");
                sc.nextLine();
            }
        } while (attributeChoice != attributeMenu.getMaxChoice());

    }

    @Override
    public void loadDataFromFile(String fname) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if (count > 0) {
            clear();
        }
        try {
            fis = new FileInputStream(fname); //chỉ có thể đọc file theo kiểu chữ và mã ASCII
            ois = new ObjectInputStream(fis); // đọc theo kiểu object
            Product x;
            while ((x = (Product) ois.readObject()) != null) {
                proList = Arrays.copyOf(proList, count + 1);
                proList[count++] = x;

            }
            fis.close();
            ois.close();
        } catch (EOFException ex) {

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                System.out.println("Loi dong file");
            }
        }
    }

    @Override
    public void saveToFile(String fname) {
        if (isEmpty()) {
            System.out.println("Danh sach rong!!");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(fname);

            ObjectOutputStream out = new ObjectOutputStream(fos);

            for (int i = 0; i < count; i++) {
                out.writeObject(proList[i]);
            }
            System.out.println("Luu thanh cong");
            fos.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void printListAscendingByID() {
        if (isEmpty()) {
            System.out.println("Danh sach rong!!");
            return;
        }
        Arrays.sort(proList, (o1, o2) -> o1.getProductId().compareToIgnoreCase(o2.getProductId())); // so sánh o1 trước
        // đông nghĩa là sắp xếp tăng dần theo ID
        System.out.println("DANH SACH SAN PHAM");
        System.out.println(header);
        for (Product p : proList) {
            p.display();
        }
    }

    @Override
    public void showLowStockProducts() {
        System.out.println("=====DANH SACH SAN PHAM TON KHO IT=====");
        System.out.println(header);
        for (Product p : proList) {
            if (p.getQuanlity() < 10) {
                p.display();
            }
        }

    }

    @Override
    public void showBestSellingProducts() {
        OrderDetailsManager odm = new OrderDetailsManager();
        odm.loadDataFromFile("DBStore/OrderDetails.dat");
        Map<Product, Integer> productSaleMap = new HashMap(); // sản phẩm và số lượng sp bán ra

        for (int i = 0; i < proList.length; i++) {
            Product x = proList[i];
            OrderDetails[] res = odm.searchOrderDetails(o -> o.getProductID().equalsIgnoreCase(x.getProductId())); // trả về ds các đơn hàng của sp có ID trong ds proList
            int amount = 0;

            for (int j = 0; j < res.length; j++) {
                OrderManager om = new OrderManager();
                om.loadDataFromFile("DBStore/Orders.dat");
                if (om.searchOrderByOrderID(res[j].getId()).getStatus().equalsIgnoreCase("HOAN THANH")) // check trạng thái đơn hàng hoan thanh thi moi cong vao
                {
                    amount += res[j].getQuantity(); // cộng số lượng bán ra của đơn hàng
                }
            }
            productSaleMap.put(x, amount);
        }
        List<Map.Entry<Product, Integer>> sortedProductSales = new ArrayList<>(productSaleMap.entrySet());
        sortedProductSales.sort(Map.Entry.<Product, Integer>comparingByValue().reversed());
        System.out.println("===== TOP 3 SAN PHAM BAN CHAY NHAT =====");
        System.out.printf("|%-12s|%-35s|%-15s|\n", "ID SAN PHAM", "TEN SAN PHAM", "SO LUONG BAN");
        for (int i = 0; i < 3; i++) {
            Map.Entry<Product, Integer> entry = sortedProductSales.get(i);
            String productID = entry.getKey().getProductId();
            String productName = entry.getKey().getProductName();
            System.out.printf("|%-12s|%-35s|%-15s|\n", productID, productName, entry.getValue());

        }
    }

}

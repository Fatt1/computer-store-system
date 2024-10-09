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
import java.util.Arrays;
import java.util.Scanner;
import model.Order;
import model.OrderDetails;
import model.Product;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public class OrderDetailsManager implements IOrderDetailsManager, IOFile {

    private Scanner sc = new Scanner(System.in);
    private OrderDetails odList[] = new OrderDetails[0];
    private int count = odList.length;
    private String header = String.format("|%-12s|%-13s|%-8s|%-5s|%-9s|%-11s|", "ID DON HANG", "ID SAN PHAM", "DON GIA", "SL", "GIAM GIA", "THANH TIEN");

    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            odList[i] = null;
        }
        count = 0;
    }

    @Override
    public void addNew() {
        String orderID;
        String proID;

        OrderDetails od = new OrderDetails();
        ProductManager pm = new ProductManager();
        Product proList[] = pm.getList();
        OrderManager om = new OrderManager();
        Order[] orderList = om.getList();
        do {
            do {
                System.out.println("+-------------+");
                System.out.printf("| %-11s |\n", "ID DON HANG");
                System.out.println("+-------------+");

                // In danh sách ID đơn hàng với căn chỉnh
                for (Order o : orderList) {
                    System.out.printf("| %-11s |\n", o.getId());
                }
                System.out.println("+-------------+");

                orderID = MyUtility.getString("Nhap ID don hang: ", "Vui long khong de trong!");
                if (!checkOrderID(orderID)) {
                    System.out.println("ID don hang nay khong ton tai. Vui long nhap ID don hang da co san trong danh sach!!");
                }
            } while (!checkOrderID(orderID));

            do {
                System.out.println("+---------------+");
                System.out.printf("| %-13s |\n", "ID SAN PHAM");
                System.out.println("+---------------+");

                // In danh sách ID đơn hàng với căn chỉnh
                for (Product p : proList) 
                    System.out.printf("| %-13s |\n", p.getProductId());
                System.out.println("+---------------+");
                proID = MyUtility.getId("Nhap ID san pham(PXXX): ", "Vui long nhap theo dinh dang PXXX, "
                        + "X dai dien cho so (0 - 9)", "^[p|P]\\d{3}$");
                if (!MyUtility.checkProductID(proID)) {
                    System.out.println("ID san pham nay khong ton tai. Vui long nhap ID san pham da co san trong danh sach!!");
                }

            } while (!MyUtility.checkProductID(proID));

            if (searchByID(orderID, proID) >= 0) {
                System.out.println("Cap ID don hang va ID san pham da bi trung. Vui long nhap lai!!");
            }
        } while (searchByID(orderID, proID) != -1);
        od.setId(orderID);
        od.setProductID(proID);
        od.input();
        odList = Arrays.copyOf(odList, odList.length + 1);
        odList[count++] = od;
        System.out.println("Nhap chi tiet hoa don thanh cong");
    }

    @Override
    public void updateObjectByID() {
        System.out.println("De chinh sua don chi tiet ban phai nhap ID don hang va ID san pham");
        String orderID = MyUtility.getString("Nhap ID don hang: ", "Vui long khong de trong!");
        String productID = MyUtility.getId("Nhap ID san pham(PXXX): ", "Vui long nhap theo dinh dang PXXX, "
                + "X dai dien cho so (0 - 9)", "^[p|P]\\d{3}$");
        OrderDetails x = searchDetailsByID(orderID, productID);
        if (x == null) {
            System.out.println("Chi tiet hoa don nay khong ton tai!!");
        } else {
            System.out.println("Day la chi tiet don hang ban muon cap nhat");
            System.out.println(header);
            x.display();
            Menu updateOrder = new Menu("Thong tin chi tiet don hang");
            updateOrder.addNewOption("1. Don gia ");
            updateOrder.addNewOption("2. So luong");
            updateOrder.addNewOption("3. Giam gia");
            updateOrder.addNewOption("4. Exit");
            updateOrder.printMenu();
            System.out.println("---------------------");
            int choice = updateOrder.getChoice();
            if (choice == 4) {
                return;
            }
            switch (choice) {
                case 1:
                    double newUnitPrice = MyUtility.getADouble("Nhap don gia moi: ", "Vui long nhap so!!");
                    x.setUnitPrice(newUnitPrice);
                    break;
                case 2:
                    int newQuantity = MyUtility.getAnInteger("Nhap so luong moi : ", "Vui long nhap so nguyen!!");
                    x.setQuantity(newQuantity);
                    break;
                case 3:
                    double newDiscount = MyUtility.getADouble("Nhap giam gia moi: ", "Vui long nhap so!!");
                    x.setDiscount(newDiscount);
                    break;
            }
            System.out.println("Cap nhat thong tin thanh cong");
        }

    }

    @Override
    public void removeOjectByID() {
        System.out.println("De xoa chi tiet don hang ban phai nhap ID don hang va ID san pham");
        String orderID = MyUtility.getString("Nhap ID don hang: ", "Vui long khong de trong!");
        String productID = MyUtility.getId("Nhap ID(PXXX): ", "Vui long nhap theo dinh dang PXXX, "
                + "X dai dien cho so (0 - 9)", "^[p|P]\\d{3}$");
        int pos = searchByID(orderID, productID);
        if (pos == -1) {
            System.out.println("Khong tim thay chi tiet don hang nay!!");
        } else {
            String choice;
            System.out.print("Ban co chac xoa chi tiet don hang nay (Y/N):");
            do {
                choice = sc.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    for (int i = pos; i < count - 1; i++) {
                        odList[i] = odList[i + 1]; // con trỏ cuối cùng sẽ trỏ tới object cuối cùng mà chúng ta ko sử dụng nữa
                    }
                    odList = Arrays.copyOf(odList, count - 1);
                    count--;
                    System.out.println("Xoa chi tiet don hang thanh cong");
                    break;
                } else if (choice.equalsIgnoreCase("N")) {
                    break;
                }
                System.out.println("Vui long chon (Y/N)");
            } while (true);
        }
    }

    @Override
    public void printListAscendingByID() {
        if (odList.length == 0) {
            System.out.println("Danh sach rong!!");
            return;
        }
        Arrays.sort(odList, (o1, o2) -> o1.getId().compareToIgnoreCase(o2.getId()));
        System.out.println("=====DANH SACH CHI TIET DON HANG=====");
        System.out.println(header);
        for (OrderDetails od : odList) {
            od.display();
        }
    }

    @Override
    public void searchByID() {
        String orderID = MyUtility.getString("Nhap ID don hang ma ban muon tim kiem: ", "Vui long khong de trong!!");
        OrderDetails[] res = searchOrderDetails(o -> o.getId().equalsIgnoreCase(orderID));
        if (res.length == 0) {
            System.out.println("Don hang ban tim kiem khong ton tai!!");
            return;
        }
        System.out.println("Day chi tiet don hang ban tim kiem");
        System.out.println(header);
        for (OrderDetails re : res) {
            re.display();
        }
    }

    @Override
    public int searchByID(String orderID, String productID) {
        if (odList.length == 0) {
            return -1;
        }
        for (int i = 0; i < odList.length; i++) {
            OrderDetails x = odList[i];
            if (x.getId().equalsIgnoreCase(orderID) && x.getProductID().equalsIgnoreCase(productID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public OrderDetails searchDetailsByID(String orderID, String productID) {
        if (odList.length == 0) {
            return null;
        }
        for (OrderDetails x : odList) {
            if (x.getId().equalsIgnoreCase(orderID) && x.getProductID().equalsIgnoreCase(productID)) {
                return x;
            }
        }
        return null;
    }

    @Override
    public void loadDataFromFile(String fname) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if (odList.length > 0) {
            clear();
        }
        try {

            fis = new FileInputStream(fname);
            ois = new ObjectInputStream(fis);
            OrderDetails x;
            while ((x = (OrderDetails) ois.readObject()) != null) {
                odList = Arrays.copyOf(odList, odList.length + 1);
                odList[count++] = x;
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
        if (odList.length == 0) {
            System.out.println("Danh sach rong khong co gi de luu!!");
            return;
        }
        try {

            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            for (OrderDetails odList1 : odList) {
                out.writeObject(odList1);
            }
            System.out.println("Luu thanh cong!!!");
            fos.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOrderID(String orderID) {
        OrderManager om = new OrderManager();
        om.loadDataFromFile("DBStore/Orders.dat");
        if (om.searchByOrderID(orderID) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public OrderDetails[] searchOrderDetails(Filter<OrderDetails> filter) {
        OrderDetails[] res = new OrderDetails[0];
        int n = res.length;
        for (OrderDetails od : odList) {
            if (filter.check(od)) {
                res = Arrays.copyOf(res, res.length + 1);
                res[n++] = od;
            }
        }

        return res;
    }

    @Override
    public void searchOrderDetailsByProductID() {
        String proID = MyUtility.getString("Nhap ID san pham ma ban muon tim kiem: ", "Vui long khong de trong!!");
        OrderDetails[] res = searchOrderDetails(o -> o.getProductID().equalsIgnoreCase(proID));
        if (res.length == 0) {
            System.out.println("SAN PHAM NAY KHONG TON TAI TRONG CHI TIET DON HANG!!");
            return;
        }
        System.out.println("====Day chi tiet cac don hang chua san pham ban tim kiem====");
        System.out.println(header);
        for (OrderDetails re : res) {
            re.display();
        }

    }

}

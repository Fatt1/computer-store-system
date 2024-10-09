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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Customer;
import model.CustomerStatistic;
import model.Order;
import model.OrderDetails;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public class CustomerManager implements ICustomerManager, IOFile {

    private Scanner sc = new Scanner(System.in);
    private Customer cusList[] = new Customer[0];
    private int count = cusList.length;
    private String header = String.format("|%-5s|%-25s|%-25s|%-11s|", "ID", "HO TEN", "NGAY SINH", "SDT");

    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < count; i++) {
            cusList[i] = null;
        }
        count = 0;
    }

    @Override
    public void addNew() {
        String id;
        int duplicate;
        do {
            id = MyUtility.getId("Nhap ID KH (CXXX): ", "Vui long nhap theo dinh dang CXXX, "
                    + "X dai dien cho so (0 - 9) ", "^[c|C]\\d{3}$");
            duplicate = searchByID(id);
            if (duplicate >= 0) {
                System.out.println("ID da ton tai. Vui long nhap ID khac!!");
            }
        } while (duplicate != -1);
        String fullName = MyUtility.getString("Nhap ten KH: ", "Vui long khong de trong!!");
        LocalDate yob = MyUtility.getDate("Nhap ngay sinh KH(dd/MM/yyyy): ", "Vui long nhap theo dinh dang(dd/MM/yyyy)", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String phoneNumebr = MyUtility.getString("Nhap SDT KH: ", "Vui long khong de trong!!");
        cusList = Arrays.copyOf(cusList, cusList.length + 1);
        cusList[count++] = new Customer(id, fullName, yob, phoneNumebr);
        System.out.println("Them moi khach hang thanh cong");
    }

    @Override
    public void updateObjectByID() {
        String id = MyUtility.getString("Nhap ID ma ban muon cap nhat: ", "Vui long khong de trong!!");
        Customer x = searchCusByID(id);
        if (x == null) {
            System.out.println("Khach hang khong ton tai!!");
        } else {
            System.out.println("Day la khach hang ban muon cap nhat");
            System.out.println(header);
            x.display();
            Menu updateCus = new Menu("Thong tin ban muon cap nhat");
            updateCus.addNewOption("1. Ten");
            updateCus.addNewOption("2. Nam sinh");
            updateCus.addNewOption("3. SDT");
            updateCus.addNewOption("4. Thoat");

            updateCus.printMenu();
            int choice = updateCus.getChoice();
            if (choice == 4) {
                return;
            }

            switch (choice) {
                case 1:
                    String newName = MyUtility.getString("Nhap ten khach hang moi: ", "Vui long khong de trong!!");
                    x.setCustomerName(newName);
                    break;
                case 2:
                    LocalDate newAge = MyUtility.getDate("Nhap ngay sinh khach hang moi(dd/MM/yyyy): ", "Vui long nhap theo dinh dang(dd/MM/yyyy)", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    x.setYob(newAge);
                    break;
                case 3:
                    String newPhone = MyUtility.getString("Nhap SDT khach hang moi: ", "Vui long khong de trong!!");
                    x.setPhoneNumber(newPhone);
                    break;
            }
            System.out.println("Cap nhat thong tin thanh cong");
        }
    }

    @Override
    public void removeOjectByID() {
        String id = MyUtility.getString("Nhap ID ma ban muon xoa: ", "Vui long khong de trong!!");
        int pos = searchByID(id);
        String choice;
        if (pos == -1) {
            System.out.println("Khach hang khong ton tai!!");
            return;
        }
        System.out.print("Ban chac chan muon xoa khach hang nay (Y/N): ");
        do {

            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                for (int i = pos; i < count - 1; i++) {
                    cusList[i] = cusList[i + 1]; // con trỏ cuối cùng sẽ trỏ tới object cuối cùng mà chúng ta ko sử dụng nữa
                }
                cusList = Arrays.copyOf(cusList, cusList.length - 1);
                count--;
                System.out.println("Xoa khach hang thanh cong");
                break;
            } else if (choice.equalsIgnoreCase("N")) {
                break;
            }
            System.out.print("Vui long nhap (Y/N): ");

        } while (true);
    }

    @Override
    public String splitFirstName(String fullName) {
        String lastName = fullName.substring(fullName.lastIndexOf(' ') + 1); // trả về chuỗi bắt đầu từ vị trí i
        return lastName;
    }

    @Override
    public void searchByName() {
        String cusName = MyUtility.getString("Nhap ten KH ma ban muon tim kiem: ", "Vui long khong de trong!!");
        Customer[] result = searchByName(cusName); // lấy cái tọa độ của cái hàm searchByName
        if (result.length == 0) {
            System.out.println("Khach hang khong ton tai!!");
            return;
        }
        System.out.println("DANH SACH KHACH HANG");
        System.out.println(header);
        for (Customer res : result) {
            res.display();
        }
    }

    @Override
    public Customer[] searchByName(String cusName) {
        Customer[] res = new Customer[0];
        int n = res.length;
        for (Customer cus : cusList) {
            if (cus.getCustomerName().contains(cusName)) {
                res = Arrays.copyOf(res, n + 1);
            }
            res[n++] = cus;
        }
        return res;
    }

    @Override
    public void searchByID() {
        String cusID = MyUtility.getString("Nhap ID khach hang: ", "Vui long khong de trong");
        Customer x = searchCusByID(cusID);
        if (x == null) {
            System.out.println("Khong ton tai khach hang nay!!");
            return;
        }
        System.out.println("Day la khach hang ma ban muon tim kiem");
        System.out.println(header);
        x.display();
    }

    @Override
    public Customer searchCusByID(String cusID) {
        if (isEmpty()) {
            return null;
        }
        for (Customer cus : cusList) {
            if (cus.getCustomerId().equalsIgnoreCase(cusID)) {
                return cus;
            }
        }
        return null;
    }

    @Override
    public int searchByID(String cusID) {
        if (isEmpty()) {
            return -1;
        }
        for (int i = 0; i < count; i++) {
            if (cusList[i].getCustomerId().equalsIgnoreCase(cusID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void printCusListAscendingByName() {
        if (isEmpty()) {
            System.out.println("DANH SACH RONG!!!");
            return;
        }
        System.out.println("DANH SACH KHACH HANG");
        System.out.println(header);
        Arrays.sort(cusList, (o1, o2) -> splitFirstName(o1.getCustomerName()).compareToIgnoreCase(splitFirstName(o2.getCustomerName())));
        for (int i = 0; i < count; i++) {
            cusList[i].display();
        }

    }

    @Override
    public void printListAscendingByID() {
        if (isEmpty()) {
            System.out.println("DANH SACH RONG!!!");
            return;
        }
        Arrays.sort(cusList, (o1, o2) -> o1.getCustomerId().compareToIgnoreCase(o2.getCustomerId()));
        System.out.println("DANH SACH KHACH HANG");
        System.out.println(header);
        for (int i = 0; i < count; i++) {
            cusList[i].display();
        }

    }
    public Customer[] getList(){
        return cusList;
    }
    
    @Override
    public void loadDataFromFile(String fname) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if (count > 0) {
            clear();
        }
        try {
            fis = new FileInputStream(fname);
            ois = new ObjectInputStream(fis);
            Customer x;
            while ((x = (Customer) ois.readObject()) != null) {
                cusList = Arrays.copyOf(cusList, count + 1);
                cusList[count++] = x;
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

            }
        }
    }

    @Override
    public void saveToFile(String fname) {
        if (isEmpty()) {
            System.out.println("DANH SACH RONG KHONG CO GI DE LUU!!");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            for (int i = 0; i < count; i++) {
                out.writeObject(cusList[i]);
            }
            fos.close();
            out.close();
            System.out.println("Luu file thanh cong");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void statisticEachCustomer() {
        OrderManager om = new OrderManager();
        om.loadDataFromFile("DBStore/Orders.dat");
        OrderDetailsManager odm = new OrderDetailsManager();
        odm.loadDataFromFile("DBStore/OrderDetails.dat");
        Map<String, CustomerStatistic> cusStatis = new HashMap<>();
        for (int i = 0; i < cusList.length; i++) { // lấy từng cusID để kiếm xem mỗi KH có bao nhiêu đơn hàng
            Customer x = cusList[i];
            Order[] orders = om.searchOrder(o1 -> o1.getCustomerID().equalsIgnoreCase(x.getCustomerId())); // trả về các đơn hàng của KH
            int totalOrders = 0;
            int totalAmount = 0;
            if (orders.length > 0) {
                for (Order o : orders) { // tính tổng tiền tất cả các đơn hàng của KH
                    OrderDetails[] oderDetials = odm.searchOrderDetails(f -> f.getId().equalsIgnoreCase(o.getId())); // lấy chi tiết đơn hàng 

                    if (o.getStatus().equalsIgnoreCase("HOAN THANH")) { // hoàn thành thì mới tính tiền 
                        totalOrders++;
                        for (OrderDetails ods : oderDetials) {
                            totalAmount += ods.totalAmount();
                        }
                    }
                }
            }

            cusStatis.put(x.getCustomerId(), new CustomerStatistic(totalAmount, totalOrders, x.getCustomerName()));
        }
        List<Map.Entry<String, CustomerStatistic>> sortedCusStatis = new ArrayList<>(cusStatis.entrySet());
        
        sortedCusStatis.sort(Map.Entry.<String, CustomerStatistic>comparingByValue
        ((o1, o2)-> -Double.compare(o1.getTotalAmount(), o2.getTotalAmount())));
        System.out.printf("|%-5s|%-25s|%-15s|%-15s|\n", "ID", "HO TEN", "TONG DON HANG", "TONG TIEN");
        for (Map.Entry<String, CustomerStatistic> s : sortedCusStatis) {
            String id = s.getKey();
            String name = s.getValue().getCustomerName();
            int totalOrder = s.getValue().getTotalOrder();
            double totalAmount = s.getValue().getTotalAmount();
           
            System.out.printf("|%-5s|%-25s|%-15d|%-15.2f|\n", id, name, totalOrder, totalAmount);
        }
    }

}

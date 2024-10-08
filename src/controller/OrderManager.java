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
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import model.Order;
import model.OrderDetails;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public class OrderManager implements IOrderManager, IOFile {

    private Scanner sc = new Scanner(System.in);
    private Order[] orderList = new Order[0];
    private int count = orderList.length;
    private String header = String.format("|%-12s|%-15s|%-15s|%-25s|%-15s|", "ID DON HANG", "ID KHACH HANG", "ID NHAN VIEN", "NGAY GIO", "TRANG THAI");
    private int surrogateKey = 0;

    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            orderList[i] = null;
        }
        count = 0;
    }

    @Override
    public void addNew() {
        Order x = new Order();
        String formattedID = String.format("DH-%03d", ++surrogateKey); // %03d ở đây sẽ thêm số  0 vào nếu không đủ 3 số
        System.out.println("ID hoa don: " + formattedID);
        x.setId(formattedID);
        x.input();
        orderList = Arrays.copyOf(orderList, orderList.length + 1);
        orderList[count++] = x;
        System.out.println("Them hoa don moi thanh cong!!");

    }

    @Override
    public void updateObjectByID() {
        String orderID = MyUtility.getString("Nhap ID don hang ma ban muon cap nhat: ", "Vui long khong de trong!!");
        Order x = searchOrderByOrderID(orderID);
        if (x == null) {
            System.out.println("Khong ton tai don hang nay!!");
            return;
        }
        System.out.println("Day la don hang ban muon cap nhat");
        System.out.println(header);
        x.display();
        Menu updateOrder = new Menu("Thong tin don hang");
        updateOrder.addNewOption("1. ID nhan vien");
        updateOrder.addNewOption("2. ID khach hang");
        updateOrder.addNewOption("3. Trang thai don hang");
        updateOrder.addNewOption("4. Exit");
        updateOrder.printMenu();
        System.out.println("---------------------");
        int choice = updateOrder.getChoice();
        if (choice == 4) {
            return;
        }
        switch (choice) {
            case 1:
                String newEmpID = MyUtility.getId("Nhap ID NV moi(EXXX): ", "Vui long nhap theo dinh dang EXXX, "
                        + "X dai dien cho so (0 - 9) ", "^[e|E]\\d{3}$");
                if (MyUtility.checkEmployeeID(newEmpID)) {
                    x.setEmployeeID(newEmpID);
                } else {
                    System.out.println("Khong ton tai ID NV nay trong danh sach NV");
                }

                break;
            case 2:
                String newCusID = MyUtility.getId("Nhap ID KH moi(CXXX): ", "Vui long nhap theo dinh dang CXXX, "
                        + "X dai dien cho so (0 - 9) ", "^[c|C]\\d{3}$");
                if (MyUtility.checkCustomerID(newCusID)) {
                    x.setCustomerID(newCusID);
                } else {
                    System.out.println("Khong ton tai ID KH nay trong danh sach KH");
                }
                break;
            case 3:
                String newStatus = null;
                Menu mStatus = new Menu("Chon trang thai");
                mStatus.addNewOption("1. Hoan thanh");
                mStatus.addNewOption("2. Dang xu li");
                mStatus.addNewOption("3. Huy");
                mStatus.printMenu();
                int statusChoice = mStatus.getChoice();
                switch (statusChoice) {
                    case 1:
                        newStatus = "HOAN THANH";
                        break;
                    case 2:
                        newStatus = "DANG XU LY";
                        break;
                    case 3:
                        newStatus = "HUY";
                        break;
                }
    
                x.setStatus(newStatus);
                break;
        }
        System.out.println("Cap nhat thong tin thanh cong");
    }

    @Override
    public void removeOjectByID() {
        String orderID = MyUtility.getString("Nhap ID don hang ma ban muon tim kiem: ", "Vui long khong de trong!!");
        int pos = searchByOrderID(orderID);
        if (pos == -1) {
            System.out.println("ID don hang nay khong ton tai!!");
        } else {
            String choice;
            System.out.print("Ban chac chan muon xoa don hang nay (Y/N): ");
            do {
                choice = sc.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    for (int i = pos; i < count - 1; i++) {
                        orderList[i] = orderList[i + 1]; // con trỏ cuối cùng sẽ trỏ tới object cuối cùng mà chúng ta ko sử dụng nữa
                    }
                    orderList = Arrays.copyOf(orderList, orderList.length - 1);
                    count--;
                    System.out.println("Xoa don hang thanh cong");
                    break;
                } else if (choice.equalsIgnoreCase("N")) {
                    break;
                }
                System.out.print("Vui long nhap (Y/N): ");

            } while (true);
        }
    }
    
    @Override
    public Order[] searchOrder(Filter<Order> filter) {
        Order res[] = new Order[0];
        int n = res.length;
        for (Order o : orderList) {
            if(filter.check(o)){
                res = Arrays.copyOf(res, res.length + 1);
                res[n++] = o;
            }
                
        }
        return res;
    }

    @Override
    public void searchOrderByEmployeeID() {
        String empID = MyUtility.getString("Nhap ID nhan vien ma ban muon tim kiem: ", "Vui long khong de trong!!");
        Order[] res = searchOrder((o1) -> o1.getEmployeeID().equalsIgnoreCase(empID));
        if(res.length == 0) {
            System.out.println("NHAN VIEN NAY KHONG CO TRONG DANH SACH NAY!!");
            return;
        }
        System.out.println("");
        System.out.println("====DAY LA CAC DON HANG MA NHAN VIEN " + empID + " NHAP====");
        System.out.println(header);
        for (Order re : res) {
            re.display();
        }
    }
    
        @Override
    public void searchOrderByCusTomerID() {
        String cusID = MyUtility.getString("Nhap ID khach hang ma ban muon tim kiem: ", "Vui long khong de trong!!");
        Order[] res = searchOrder(o1 -> o1.getCustomerID().equalsIgnoreCase(cusID));
        if(res.length == 0) {
            System.out.println("KHACH HANG NAY KHONG CO TRONG DANH SACH NAY!!");
            return;
        }
        System.out.println("");
        System.out.println("====DAY LA CAC DON HANG MA KHAHC HANG " + cusID + " DAT MUA====");
        System.out.println(header);
        for (Order re : res) {
                re.display();
        }
    }
    
    @Override
    public void searchByOrderID() {
        String id = MyUtility.getString("Nhap ID don hang ma ban muon tim kiem: ", "Vui long khong de trong!!");
        Order x = searchOrderByOrderID(id);
        if (x == null) {
            System.out.println("Don hang khong ton tai!!");
            return;
        }
        System.out.println("Day la don hang ban tim kiem");
        System.out.println(header);
        x.display();
    }
    

    @Override
    public int searchByOrderID(String orderID) {
        if (orderList.length == 0) {
            return -1;
        }
        for (int i = 0; i < orderList.length; i++) {
            if (orderList[i].getId().equalsIgnoreCase(orderID)) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public Order searchOrderByOrderID(String orderID) {
        if (orderList.length == 0) {
            return null;
        }
        for (Order o : orderList) {
            if (o.getId().equalsIgnoreCase(orderID)) {
                return o;
            }
        }
        return null;
    }

    public Order[] getList(){
        loadDataFromFile("DBStore/Orders.dat");
        return orderList;
    }

    @Override
    public void loadDataFromFile(String fname) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if (orderList.length > 0) {
            clear();
        }
        try {

            fis = new FileInputStream(fname);
            ois = new ObjectInputStream(fis);
            Order x;
            while ((x = (Order) ois.readObject()) != null) {
                orderList = Arrays.copyOf(orderList, orderList.length + 1);
                orderList[count++] = x;
                int lastSpecialCharacter = x.getId().lastIndexOf("-") + 1;
                if (Integer.parseInt(x.getId().substring(lastSpecialCharacter)) > surrogateKey) {
                    surrogateKey = Integer.parseInt(x.getId().substring(lastSpecialCharacter));
                }
                
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
        try {

            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            for (Order o : orderList) {
                out.writeObject(o);
            }
            System.out.println("Luu thanh cong!!!");
            fos.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printListAscendingByID() {
        if (orderList.length == 0) {
            System.out.println("Danh sach rong!!");
        } else {
            System.out.println("\n====DANH SACH DON HANG====");
            System.out.println(header);
            for (Order orderList1 : orderList) {
                orderList1.display();
            }
        }
    }

    @Override
    public void showOrderDetails() {
        OrderDetailsManager odm = new OrderDetailsManager();
        odm.loadDataFromFile("DBStore/OrderDetails.dat");
        String orderID = MyUtility.getString("Nhap ID don hang ma ban muon xem chi tiet: ", "Vui long khong de trong!!");
        OrderDetails[] tmp = odm.searchOrderDetails(o -> o.getId().equalsIgnoreCase(orderID));
        if (tmp.length == 0) {
            System.out.println("Chi tiet don hang ban dang kiem chua duoc nhap hoac khong ton tai!!");
        } else {
            double totalPrice = 0;
            System.out.printf("|%-12s|%-13s|%-8s|%-5s|%-9s|%-11s|\n", "ID DON HANG", "ID SAN PHAM", "DON GIA", "SL", "GIAM GIA", "THANH TIEN");
            for (OrderDetails t : tmp) {
                t.display();
                totalPrice += t.totalAmount();
            }
            System.out.println("\t\t\t\t\t TONG TIEN: " + totalPrice);
        }
    }

    public void showRevenue() {
        OrderDetailsManager odm = new OrderDetailsManager();
        odm.loadDataFromFile("DBStore/OrderDetails.dat");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth ym = MyUtility.getYearMonth("Nhap thang va nam (MM/yyyy): ", "Vui long nhap theo dinh dang(MM/yyyy)", formatter);

        double totalPrice = 0;
        double totalPriceByMonthYear = 0;
        for (int i = 0; i < orderList.length; i++) {
            Order x = orderList[i];
            if (x.getStatus().equalsIgnoreCase("HOAN THANH") && x.getDate().getYear() == ym.getYear()) {
                OrderDetails[] tmp = odm.searchOrderDetails(o -> o.getId().equalsIgnoreCase(x.getId()));;
                for (int j = 0; j < tmp.length; j++) {
                    totalPrice += tmp[j].totalAmount();
                    if (x.getDate().getMonthValue() == ym.getMonthValue()
                            && x.getDate().getYear() == ym.getYear()) {
                        totalPriceByMonthYear += tmp[j].totalAmount();
                    }
                }

            }
        }
        System.out.println("TONG DOANH THU " + ym.format(formatter) + ": " + totalPriceByMonthYear);
        System.out.println("TONG DOANH THU NAM " + ym.getYear() + ": " + totalPrice);

    }
}

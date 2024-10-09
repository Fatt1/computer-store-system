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
import model.ImportDetails;
import model.ImportInvoice;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public class ImportInvoiceManager implements IImport, IOFile {

    private Scanner sc = new Scanner(System.in);
    private ImportInvoice[] imInvoiceList = new ImportInvoice[0];
    private int count = imInvoiceList.length;
    private String header = String.format("|%-15s|%-15s|%-8s|%-25s|%-15s|", "ID PHIEU NHAP", "ID NCC", "ID NV", "NGAY GIO", "TRANG THAI");
    private int surrogateKey = 0;

    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            imInvoiceList[i] = null;
        }
        count = 0;
    }

    @Override
    public void addNew() {
        ImportInvoice x = new ImportInvoice();
        String formattedID = String.format("PN-%03d", ++surrogateKey); // %03d ở đây sẽ thêm số  0 vào nếu không đủ 3 số
        System.out.println("ID phieu nhap: " + formattedID);
        x.setId(formattedID);
        x.input();
        imInvoiceList = Arrays.copyOf(imInvoiceList, imInvoiceList.length + 1);
        imInvoiceList[count++] = x;
        System.out.println("Them phieu nhap hang moi thanh cong");

    }

    @Override
    public void updateObjectByID() {
        String importID = MyUtility.getString("Nhap ID phieu nhap ma ban muon cap nhat: ", "Vui long khong de trong!!");
        ImportInvoice x = searchImportByID(importID);
        if (x == null) {
            System.out.println("Khong ton tai phieu nhap nay!!");
            return;
        }
        System.out.println("Day la don hang ban muon cap nhap");
        System.out.println(header);
        x.display();
        Menu updateOrder = new Menu("Thong tin phieu nhap");
        updateOrder.addNewOption("1. ID nhan vien");
        updateOrder.addNewOption("2. ID nha cung cap");
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
                int newSupID = MyUtility.getAnInteger("Nhap ID nha cung cap moi:", "Vui long khong de trong!!");
                if (MyUtility.checkSupplierID(newSupID)) {
                    x.setSupplierID(newSupID);
                } else {
                    System.out.println("Khong ton tai ID nha cung cap nay trong danh sach");
                }
                break;
            case 3:
                String newStatus = MyUtility.getString("Nhap trang phieu nhap hang moi: ", "Vui long khong de trong!!");
                x.setStatus(newStatus);
                break;
        }
        System.out.println("Cap nhat thong tin thanh cong");
    }

    @Override
    public void removeOjectByID() {
        String importID = MyUtility.getString("Nhap ID don hang ma ban muon tim kiem: ", "Vui long khong de trong!!");
        int pos = searchByID(importID);
        if (pos == -1) {
            System.out.println("ID phieu nhap nay khong ton tai!!");
        } else {
            String choice;
            System.out.print("Ban chac chan muon xoa phieu nhap nay (Y/N): ");
            do {
                choice = sc.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    for (int i = pos; i < count - 1; i++) {
                        imInvoiceList[i] = imInvoiceList[i + 1]; // con trỏ cuối cùng sẽ trỏ tới object cuối cùng mà chúng ta ko sử dụng nữa
                    }
                    imInvoiceList = Arrays.copyOf(imInvoiceList, count - 1);
                    count--;
                    System.out.println("Xoa phieu nhap thanh cong");
                    break;
                } else if (choice.equalsIgnoreCase("N")) {
                    break;
                }
                System.out.print("Vui long nhap (Y/N): ");

            } while (true);
        }
    }

    @Override
    public void printListAscendingByID() {
        if (imInvoiceList.length == 0) {
            System.out.println("Danh sach rong!!");
            return;
        }
        System.out.println("DANH SACH PHIEU NHAP HANG");
        System.out.println(header);
        for (ImportInvoice imInvoiceList1 : imInvoiceList) {
            imInvoiceList1.display();
        }
    }

    @Override
    public void searchByImportID() {
        String importID = MyUtility.getString("Nhap ID phieu nhap hang ma ban muon tim kiem: ", "Vui long khong de trong!!");
        ImportInvoice x = searchImportByID(importID);
        if (x == null) {
            System.out.println("Phieu nhap hang nay khong ton tai!!");
            return;
        }
        System.out.println("Day la phieu nhap hang ma ban tim kiem");
        System.out.println(header);
        x.display();
    }

    @Override
    public int searchByID(String importID) {
        if (imInvoiceList.length == 0) {
            return -1;
        }
        for (int i = 0; i < imInvoiceList.length; i++) {
            if (imInvoiceList[i].getId().equalsIgnoreCase(importID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ImportInvoice searchImportByID(String importID) {
        if (imInvoiceList.length == 0) {
            return null;
        }
        for (ImportInvoice im : imInvoiceList) {
            if (im.getId().equalsIgnoreCase(importID)) {
                return im;
            }
        }
        return null;
    }

    @Override
    public void loadDataFromFile(String fname) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if (imInvoiceList.length > 0) {
            clear();
        }
        try {

            fis = new FileInputStream(fname);
            ois = new ObjectInputStream(fis);
            ImportInvoice x;
            while ((x = (ImportInvoice) ois.readObject()) != null) {
                imInvoiceList = Arrays.copyOf(imInvoiceList, imInvoiceList.length + 1);
                imInvoiceList[count++] = x;
                int lastSpecialCharacter = x.getId().lastIndexOf("-") + 1;
                if (Integer.parseInt(x.getId().substring(lastSpecialCharacter)) > surrogateKey) {
                    surrogateKey = Integer.parseInt(x.getId().substring(lastSpecialCharacter));
                }
                
            }

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
    
    public ImportInvoice[] getList(){
        loadDataFromFile("DBStore/ImportInvoice.dat");
        return imInvoiceList;
    }
    
    @Override
    public void saveToFile(String fname) {
        try {

            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            for (ImportInvoice im : imInvoiceList) {
                out.writeObject(im);
            }
            System.out.println("Luu thanh cong!!!");
            fos.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showImportDetails() {
        ImportDetailsManager idm = new ImportDetailsManager();
        idm.loadDataFromFile("DBStore/ImportDetails.dat");
        String importID = MyUtility.getString("Nhap ID phieu nhap ma ban muon xem chi tiet: ", "Vui long khong de trong!!");
        ImportDetails[] tmp = idm.searchImportDetails(o -> o.getId().equalsIgnoreCase(importID));
        if (tmp.length == 0) {
            System.out.println("Chi tiet phieu nhap ban dang kiem chua duoc nhap hoac khong ton tai!!");
        } else {
            double totalPrice = 0;
            System.out.printf("|%-15s|%-15s|%-8s|%-5s|%-11s|\n", "ID PHIEU NHAP", "ID SP", "DON GIA", "SL", "THANH TIEN");
            for (int i = 0; i < tmp.length; i++) {
                tmp[i].display();
                totalPrice += tmp[i].totalAmount();
            }
            System.out.println("\t\t\t\t TONG TIEN: " + totalPrice);
        }
    }

}

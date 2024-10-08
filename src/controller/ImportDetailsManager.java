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
import model.Order;
import model.Product;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public class ImportDetailsManager implements IImportDetailsManager, IOFile {

    private ImportDetails imdList[] = new ImportDetails[0];
    private int count = imdList.length;
    private Scanner sc = new Scanner(System.in);
    private String header = String.format("|%-15s|%-15s|%-8s|%-5s|%-11s|", "ID PHIEU NHAP", "ID SAN PHAM", "DON GIA", "SL", "THANH TIEN");

    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            imdList[i] = null;
        }
        count = 0;
    }
    
    @Override
    public boolean checkImportID(String importID) {
        ImportInvoiceManager imInvoice = new ImportInvoiceManager();
        imInvoice.loadDataFromFile("DBStore/ImportInvoice.dat");
        if (imInvoice.searchByID(importID) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void addNew() {
        String importID;
        String proID;
        ImportDetails x = new ImportDetails();
        ProductManager pm = new ProductManager();
        Product proList[] = pm.getList();
        ImportInvoiceManager iim = new ImportInvoiceManager();
        ImportInvoice[] impList = iim.getList();
        
            do {
                do {
                System.out.println("+-----------------+");
                System.out.printf("| %-15s |\n", "ID PHIEU NHAP");
                System.out.println("+-----------------+");

                // In danh sách ID đơn hàng với căn chỉnh
                for (ImportInvoice i : impList) {
                    System.out.printf("| %-15s |\n", i.getId());
                }
                System.out.println("+-----------------+");
                importID = MyUtility.getString("Nhap ID phieu nhap: ", "Vui long khong de trong!");
                if (!checkImportID(importID)) {
                    System.out.println("ID phieu nhap nay khong ton tai. Vui long nhap ID phieu nhap da co san trong danh sach!!");
                }
            } while (!checkImportID(importID));

            do {
                System.out.println("+---------------+");
                System.out.printf("| %-13s |\n", "ID SAN PHAM");
                System.out.println("+---------------+");

                // In danh sách ID đơn hàng với căn chỉnh
                for (Product p : proList) 
                    System.out.printf("| %-13s |\n", p.getProductId());
                System.out.println("+----------------+");
                proID = MyUtility.getId("Nhap ID san pham(PXXX): ", "Vui long nhap theo dinh dang PXXX, "
                        + "X dai dien cho so (0 - 9)", "^[p|P]\\d{3}$");
                if (!MyUtility.checkProductID(proID)) {
                    System.out.println("ID san pham nay khong ton tai. Vui long nhap ID san pham da co san trong danh sach!!");
                }

            } while (!MyUtility.checkProductID(proID));

            if (searchByID(importID, proID) >= 0) {
                System.out.println("Cap ID phieu nhap va ID san pham da bi trung. Vui long nhap lai!!");
            }
        } while (searchByID(importID, proID) != -1);
        x.setId(importID);
        x.setProductID(proID);
        x.input();
        imdList = Arrays.copyOf(imdList, imdList.length + 1);
        imdList[count++] = x;
        System.out.println("Nhap chi tiet phieu nhap hang thanh cong");
    }

    @Override
    public void updateObjectByID() {
        System.out.println("De chinh sua phieu nhap chi tiet ban phai nhap ID phieu nhap va ID san pham");
        String importID = MyUtility.getString("Nhap ID phieu nhap: ", "Vui long khong de trong!");
        String productID = MyUtility.getId("Nhap ID san pham(PXXX): ", "Vui long nhap theo dinh dang PXXX, "
                + "X dai dien cho so (0 - 9)", "^[p|P]\\d{3}$");
        ImportDetails x = ImportDetailsManager.this.searchDetailsByID(importID, productID);
        if (x == null) {
            System.out.println("Chi tiet phieu nhap nay khong ton tai!!");
        } else {
            System.out.println("Day la chi tiet phieu nhap ban muon cap nhat");
            System.out.println(header);
            x.display();
            Menu updateImport = new Menu("Thong tin chi tiet phieu nhap");
            updateImport.addNewOption("1. Don gia ");
            updateImport.addNewOption("2. So luong");
            updateImport.addNewOption("3. Exit");
            updateImport.printMenu();
            System.out.println("---------------------");
            int choice = updateImport.getChoice();
            if (choice == 3) {
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
            }
            System.out.println("Cap nhat thong tin thanh cong");
        }
    }

    @Override
    public void removeOjectByID() {
        System.out.println("De xoa chi tiet phieu nhap ban phai nhap ID phieu nhap va ID san pham");
        String importID = MyUtility.getString("Nhap ID phieu nhap: ", "Vui long khong de trong!");
        String productID = MyUtility.getId("Nhap ID(PXXX): ", "Vui long nhap theo dinh dang PXXX, "
                + "X dai dien cho so (0 - 9)", "^[p|P]\\d{3}$");
        int pos = searchByID(importID, productID);
        if (pos == -1) {
            System.out.println("Khong tim thay chi tiet phieu nhap nay!!");
        } else {
            String choice;
            System.out.print("Ban co chac xoa chi tiet phieu nhap nay (Y/N):");
            do {
                choice = sc.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    for (int i = pos; i < count - 1; i++) {
                        imdList[i] = imdList[i + 1]; // con trỏ cuối cùng sẽ trỏ tới object cuối cùng mà chúng ta ko sử dụng nữa
                }
                    imdList = Arrays.copyOf(imdList, imdList.length - 1);
                    count--;
                    System.out.println("Xoa chi tiet phieu nhap thanh cong");
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
        if (imdList.length == 0) {
            System.out.println("Danh sach rong!!");
            return;
        }
        Arrays.sort(imdList, (o1, o2) -> o1.getId().compareToIgnoreCase(o2.getId()));
        System.out.println("");
        System.out.println("====DANH SACH CHI TIET PHIEU NHAP====");
        System.out.println(header);
        for (ImportDetails x : imdList) {
            x.display();
        }
    }

    @Override
    public void searchByID() {
        String importID = MyUtility.getString("Nhap ID phieu nhap hang ma ban muon tim kiem: ", "Vui long khong de trong!!");
        ImportDetails[] res = searchImportDetails(o -> o.getId().equalsIgnoreCase(importID));
        if (res.length == 0) {
            System.out.println("Phieu nhap hang ban tim kiem khong ton tai!!");
            return;
        }
        System.out.println("Day chi tiet phieu nhap ban tim kiem");
        System.out.println(header);
        for (ImportDetails re : res) {
            re.display();
        }
    }

    @Override
    public int searchByID(String ID, String productID) {
        if (imdList.length == 0) {
            return -1;
        }
        for (int i = 0; i < imdList.length; i++) {
            ImportDetails x = imdList[i];
            if (x.getId().equalsIgnoreCase(ID) && x.getProductID().equalsIgnoreCase(productID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ImportDetails searchDetailsByID(String ID, String productID) {
        if (imdList.length == 0) {
            return null;
        }
        for (ImportDetails x : imdList) {
            if (x.getId().equalsIgnoreCase(ID) && x.getProductID().equalsIgnoreCase(productID)) {
                return x;
            }
        }
        return null;
    }

    @Override
    public ImportDetails[] searchImportDetails(Filter <ImportDetails> filter) {
        ImportDetails res[] = new ImportDetails[0];
        int n = res.length;
        for (ImportDetails x : imdList) {
            if (filter.check(x)) {
                res = Arrays.copyOf(res, res.length + 1);
                res[n++] = x;
            } 
        }
        return res;
    }

    @Override
    public void loadDataFromFile(String fname) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if (imdList.length > 0) {
            clear();
        }
        try {

            fis = new FileInputStream(fname);
            ois = new ObjectInputStream(fis);
            ImportDetails x;
            while ((x = (ImportDetails) ois.readObject()) != null) {
                imdList = Arrays.copyOf(imdList, imdList.length + 1);
                imdList[count++] = x;
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
                System.out.println("Loi mo file file");
            }
        }
    }

    @Override
    public void saveToFile(String fname) {
        if(imdList.length == 0){
            System.out.println("KHONG CO GI DE LUU");
            return;
        }
        try {

            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            for (ImportDetails x : imdList) {
                out.writeObject(x);
            }
            System.out.println("Luu thanh cong!!!");
            fos.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void searchImportDetailsByProductID() {
        String proID = MyUtility.getString("Nhap ID san pham ma ban muon tim kiem: ", "Vui long khong de trong!!");
        ImportDetails[] res = searchImportDetails(o -> o.getProductID().equalsIgnoreCase(proID));
        if(res.length == 0) {
            System.out.println("SAN PHAM NAY KHONG TON TAI TRONG CHI TIET DON HANG!!");
            return;
        }
        System.out.println("");
        System.out.println("====Chi tiet cac don hang chua san pham ban tim kiem====");
        System.out.println(header);
        for (ImportDetails re : res) {
            re.display();
        }
    }


}

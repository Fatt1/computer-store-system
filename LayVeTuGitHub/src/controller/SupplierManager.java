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
import model.Supplier;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public class SupplierManager implements ISupplierManager, IOFile{
    private Supplier supList[] = new Supplier[0];
    private int count = supList.length;
    private Scanner sc = new Scanner(System.in);
    private String header = String.format("|%-5s|%-25s|%-50s|%-12s|", "ID", "TEN NHA CUNG CAP", "DIA CHI", "SDT");
    private int surrogateKey = 0; // key tự tăng
    
    public boolean isEmpty() {
        if(count == 0) return true;
        return false;
    }

    
    public void clear() {
        for (int i = 0; i < count; i++) {
            supList[i] = null;
        }
        count = 0;
    }
    @Override
    public void addNew() {
        
        System.out.println("ID nha cung cap: " + (surrogateKey));
        String supName = MyUtility.getString("Nhap ten nha cung cap: ", "Vui long khong de trong!!");
        String address = MyUtility.getString("Nhap dia chi: ", "Vui long khong de trong!!");
        String phoneNumber = MyUtility.getString("Nhap SDT: ", "Vui long khong de trong!!");
        supList = Arrays.copyOf(supList, count + 1);
        supList[count++] = new Supplier(surrogateKey, supName, address, phoneNumber);
        System.out.println("Them moi nha cung cap thanh cong");
    }

    @Override
    public void updateObjectByID() {
        int supId = MyUtility.getAnInteger("Nhap ID nha cung cap ma ban muon cap nhat: ", "Vui long khong de trong!!");
        Supplier x = searchSupplierByID(supId);
        if(x == null) System.out.println("Nha cung cap khong ton tai!!");
        else{
            System.out.println("Day la nha cung cap ban muon cap nhat");
            System.out.println(header);
            x.display();
            Menu updateCus = new Menu("Thong tin ban muon cap nhat");
            updateCus.addNewOption("1. Ten");
            updateCus.addNewOption("2. Dia chi");
            updateCus.addNewOption("3. SDT");
            updateCus.addNewOption("4. Cap nhat tat ca thong tin");
            updateCus.addNewOption("5. Thoat");
            
            updateCus.printMenu();
            int choice = updateCus.getChoice();
            if(choice == 5) return;
            switch (choice) {
                case 1:
                    String newName = MyUtility.getString("Nhap ten nha cung ung moi: ", "Vui long khong de trong!!");
                    x.setSupplierName(newName);
                    break;
                case 2:
                    String newAdress = MyUtility.getString("Nhap dia chi nha cung ung moi: ", "Vui long khong de trong!!");
                    x.setAddress(newAdress);
                    break;
                case 3:
                    String newPhone = MyUtility.getString("Nhap SDT nha cung ung moi: ", "Vui long khong de trong!!");
                    x.setPhoneNumber(newPhone);
                    break;
                case 4:
                    newName = MyUtility.getString("Nhap ten nha cung ung moi: ", "Vui long khong de trong!!");
                    newAdress = MyUtility.getString("Nhap dia chi nha cung ung moi: ", "Vui long khong de trong!!");
                    newPhone = MyUtility.getString("Nhap SDT nha cung ung moi: ", "Vui long khong de trong!!");
                    x.setAddress(newAdress);
                    x.setPhoneNumber(newPhone);
                    x.setSupplierName(newName);
                    break;
            }
            System.out.println("Cap nhat thong tin thanh cong");
        }
    }

    @Override
    public void removeOjectByID() {
       int supId = MyUtility.getAnInteger("Nhap ID nha cung cap ma ban muon xoa: ", "Vui long khong de trong!!");
       int pos = searchByID(supId);
       String choice;
       if(pos == -1){
           System.out.println("Nha cung cap khong ton tai!!");
           return;
       }
        do {            
            choice = MyUtility.getString("Ban co chac muon xoa nha cung cap nay (Y/N): ", "Vui long khong de trong!!");
            if(choice.equalsIgnoreCase("Y")){
                 for (int i = pos; i < count - 1; i++) {
                    supList[i] = supList[i + 1]; // con trỏ cuối cùng sẽ trỏ tới object cuối cùng mà chúng ta ko sử dụng nữa
                }
                supList = Arrays.copyOf(supList, count - 1);
                count--;
                System.out.println("Xoa thanh cong");
                break;
            }
            else if(choice.equalsIgnoreCase("N"))
                break;
            System.out.println("Vui long nhap (Y/N): ");
        } while (true);
    }

    @Override
    public void searchByID() {
        int supID = MyUtility.getAnInteger("Nhap ID ma ban muon tiem kiem: ", "Vui long nhap so nguyen!!");
        Supplier x = searchSupplierByID(supID);
        if(x == null){
            System.out.println("Nha cung cap khong ton tai!!");
            return;
        }
        System.out.println("Day la nha cung cap ma ban tim kiem");
        System.out.println(header);
        x.display();
    }

    @Override
    public void searchSupplierByName() {
        String supName = MyUtility.getString("Nhap ten nha cung cap ma ban muon tim: ", "Vui long khong de trong!!");
        Supplier res [] = searchSupplierByName(supName);
        if(res.length == 0){
            System.out.println("Khong ton tai khach hang nao!!");
            return;
        }
        System.out.println("DANH SACH NHA CUNG CAP MA BAN TIM KIEM");
        System.out.println(header);
        for (Supplier re : res) {
            re.display();
        }
    }

    @Override
    public Supplier[] searchSupplierByName(String supName) {
        Supplier[] res = new Supplier[0];
        int n = res.length;
        for (Supplier x : supList) {
            if(x.getSupplierName().contains(supName)){
                res = Arrays.copyOf(res, n + 1);
                res[n++] = x; 
            }
                
        }
           
        
        return res;
    }

    @Override
    public int searchByID(int supID) {
        if(isEmpty()) return -1;
        for (int i = 0; i < supList.length; i++) {
            if(supList[i].getSupplierId() == supID)
                return i;
        }
        return -1;

    }

    @Override
    public Supplier searchSupplierByID(int supID) {
         if(isEmpty()) return null;
        for (int i = 0; i < supList.length; i++) {
            if(supList[i].getSupplierId() == supID)
                return supList[i];
        }
        return null;
    }

    @Override
    public void printListAscendingByID() {
        if(isEmpty()){
            System.out.println("DANH SACH RONG!!");
            return;
        }
        Arrays.sort(supList, (o1, o2) -> o1.getSupplierId() - o2.getSupplierId());
        System.out.println("DANH SACH NHA CUNG CAP");
        System.out.println(header);
        for (Supplier s : supList) {
            s.display();
        }      
    }

    @Override
    public void loadDataFromFile(String fname) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if(supList.length > 0) clear();
        try {
            fis = new FileInputStream(fname);
            ois = new ObjectInputStream(fis);
            Supplier x;
            while((x = (Supplier) ois.readObject()) != null){
                supList = Arrays.copyOf(supList, count + 1);
                supList[count++] = x;
                if (x.getSupplierId()> surrogateKey) {
                surrogateKey = x.getSupplierId();
                }
                surrogateKey++;
            }
            
        }catch(EOFException ex){
            
        }
        catch (Exception e) {
            System.out.println(e);
        }finally {
            try {
                if(fis != null) fis.close();
                if(ois != null) ois.close();
            } catch (IOException ex) {
                System.out.println("Loi dong file");
            }
        }
    }

    @Override
    public void saveToFile(String fname) {
        if(isEmpty()){
            System.out.println("DANH SACH KHONG CO GI DE LUU!!");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            for (Supplier s : supList) {
                out.writeObject(s);
            }
            fos.close(); out.close();
            System.out.println("Luu file thanh cong");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}

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
import java.util.Arrays;
import java.util.Scanner;
import model.Employee;
import ui.Menu;
import util.MyUtility;

/**
 *
 * @author User
 */
public class EmployeeManager implements IEmployeeManager{
    private Scanner sc = new Scanner(System.in);
    private Employee emList[] = new Employee[0];
    private int count = emList.length;
    private String header = String.format("|%-5s|%-25s|%-25s|%-35s|%-12s|%-12s|", "ID", "HO TEN", "CHUC VU", "DIA CHI", "NGAY SINH", "SDT");
    
    @Override
    public void clear() {
        for (int i = 0; i < count; i++) 
            emList[i] = null;
        count = 0;
    }
    @Override
    public void addNew() {
        String empID;
        int duplicate;
        do {            
            empID = MyUtility.getId("Nhap ID nhan vien(EXXX): ", "Vui long nhap theo dinh dang (EXXX),"
                    + " X dai dien cho so(0 - 9)", "^[e|E]\\d{3}$");
            duplicate = searchByID(empID);
            if(duplicate >= 0)
                System.out.println("ID da ton tai. Vui long nhap ID khac!!");
            else
                break;
        } while (duplicate != -1);
        String fullName = MyUtility.getString("Nhap ten nhan vien : ", "Vui long khong de trong!!");
        String title = MyUtility.getString("Nhap chuc vu nhan vien: ", "Vui long khong de trong!!");
        String address = MyUtility.getString("Nhap dia chi nhan vien : ", "Vui long khong de!!");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthday = MyUtility.getDate("Nhap ngay sinh(dd/MM/yyyy): ", "Vui long nhap theo dinh dang (dd/MM/yyyy)",formatter);
        String phoneNumber = MyUtility.getString("Nhap SDT nhan vien : ", "Vui long khong de trong!!");
        emList = Arrays.copyOf(emList, count + 1);
        emList[count++] = new Employee(empID, fullName, birthday, title, address, phoneNumber);
        System.out.println("Them nhan vien thanh cong");
    }

    @Override
    public void updateObjectByID() {
        String id = MyUtility.getString("Nhap ID ma ban muon cap nhat: ", "Vui long khong de trong!!");
        Employee x = searchEmployeeByID(id);
        if(x == null)
            System.out.println("Nhan vien khong ton tai!!");
        else{
            System.out.println("Day la nhan vien ban muon cap nhat");
            System.out.println(header);
            x.display();
            Menu updateEmp = new Menu("Thong tin ban muon cap nhat");
            updateEmp.addNewOption("1. Ten");
            updateEmp.addNewOption("2. Chuc vu");
            updateEmp.addNewOption("3. Dia chi");
            updateEmp.addNewOption("4. Ngay sinh");
            updateEmp.addNewOption("5. SDT");
            
            updateEmp.addNewOption("6. Thoat");
            
            updateEmp.printMenu();
            int choice = updateEmp.getChoice();
            if(choice == 6) return;
            
            switch (choice) {
                case 1:
                    String newName = MyUtility.getString("Nhap ten moi: ", "Vui long khong de trong!!");
                    x.setFullName(newName);
                    break;
                case 2:
                    String newTitle = MyUtility.getString("Nhap chuc vu moi: ", "Vui long khong de trong!!");
                    x.setTitle(newTitle);
                    break;
                case 3:
                    String newAdress = MyUtility.getString("Nhap dia chi moi: ", "Vui long khong de trong!!");
                    x.setAddress(newAdress);
                    break;
                case 4:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate newBirthDay = MyUtility.getDate("Nhap ngay sinh moi(dd/MM/yyyy): ", "Vui long nhap theo dinh dang (dd/MM/yyyy)!!", formatter);
                    x.setBirthday(newBirthDay);
                    break;
                case 5:
                    String newPhone = MyUtility.getString("Nhap SDT moi: ", "Vui long khong de trong!!");
                    x.setPhoneNumber(newPhone);
                    break;
            }
            System.out.println("Cap nhat thong tin thanh cong");
        }
    }

    @Override
    public void removeOjectByID() {
        String id = MyUtility.getString("Nhap ID nhan vien ma ban muon xoa: ", "Vui long khong de trong!!");
        int pos = searchByID(id);
        if(pos == -1)
            System.out.println("Nhan vien khong ton tai!!!!");
        else{
            String choice;
            System.out.print("Ban chac chan muon xoa nhan vien nay (Y/N): ");
            do {            
            choice = sc.nextLine();
            if(choice.equalsIgnoreCase("Y")){
                for (int i = pos; i < count - 1; i++) {
                    emList[i] = emList[i + 1]; // con trỏ cuối cùng sẽ trỏ tới object cuối cùng mà chúng ta ko sử dụng nữa
                }
                emList = Arrays.copyOf(emList, emList.length - 1);
                count--;
                System.out.println("Xoa nhan vien thanh cong");
                break;
            }
            else if(choice.equalsIgnoreCase("N"))
                break;
            System.out.print("Vui long nhap (Y/N): ");

        } while (true);
        }
    }

    @Override
    public void printListAscendingByID() {
        if(emList.length == 0){
            System.out.println("Danh sach rong!!");
            return;
        }
        Arrays.sort(emList,(o1,o2) -> o1.getEmployeeID().compareToIgnoreCase(o2.getEmployeeID()));
        System.out.println("DANH SACH NHAN VIEN");
        System.out.println(header);
        for (Employee e : emList) {
            e.display();
        }
    }

    @Override
    public int searchByID(String empID) {
        if(emList.length == 0) return -1;
        for (int i = 0; i < emList.length; i++) 
                if(emList[i].getEmployeeID().equalsIgnoreCase(empID))
                    return i;
        return -1;
    }

    @Override
    public void searchByID() {
        String id = MyUtility.getString("Nhap ID nhan vien ma ban muon tim kiem: ", "Vui long khong de trong!!");
        Employee x = searchEmployeeByID(id);
        if(x == null)
            System.out.println("Nhan vien khong ton tai!!");
        else{
            System.out.println("Day la nhan vien ban tim kiem");
            System.out.println(header);
            x.display();
        }
    }

    @Override
    public Employee searchEmployeeByID(String empID) {
        if(emList.length == 0) return null;
        for (int i = 0; i < count; i++) {
            Employee x = emList[i];
            if(x.getEmployeeID().equalsIgnoreCase(empID))
                return x;
        }
        return null;
    }

    @Override
    public void searchByName() {
        String name = MyUtility.getString("Nhap ten nhan vien ma ban muon tim kiem: ", "Vui long khong de trong!!");
        Employee[] res = searchEmPloyee(e -> e.getFullName().contains(name));
        if(res.length == 0)
            System.out.println("Khong ton tai nhan vien nay!!");
        else{
            System.out.println("Day la nhan vien ban tim kiem");
            System.out.println(header);
            for (Employee re : res) {
                re.display();
            }
        }
    }

    
    
    @Override
    public Employee[] searchEmPloyee(Filter<Employee> fitler) {
        Employee[] res = new Employee[0];
        int n = res.length;
        for (Employee e : emList) {
             if(fitler.check(e)){
                res = Arrays.copyOf(res, n + 1);
                res[n++] = e;
             }
               
        }
        return res;
    }
    
        @Override
    public void filterTitle() {
        String title = MyUtility.getString("Nhap chuc vu ma ban muon loc: ", "Vui long khong de trong!!");
        Employee[] res = searchEmPloyee(e -> e.getTitle().contains(title));
        if(res.length == 0){
            System.out.println("Khong tim thay nhan vien co chuc vu nay!!");
            return;
        }
        System.out.println(header);
        for (Employee re : res) {
                re.display();
        }
    }

    @Override
    public void filterAdress() {
        String address = MyUtility.getString("Nhap dia chi ma ban muon loc: ", "Vui long khong de trong!!");
        Employee[] res = searchEmPloyee(e -> e.getAddress().contains(address));
        if(res.length == 0){
            System.out.println("Khong tim thay nhan vien co dia chi nay!!");
            return;
        }
        System.out.println(header);
        for (Employee re : res) {
                re.display();
        }
    }
    @Override
    public void loadDataFromFile(String fname) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if(emList.length > 0) clear();
        try {
            fis = new FileInputStream(fname);
            ois = new ObjectInputStream(fis);
            Employee x;
            while((x = (Employee) ois.readObject()) != null){
                emList = Arrays.copyOf(emList, count + 1);
                emList[count++] = x;
            }
            fis.close(); ois.close();
        } catch (EOFException ex) {
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {
                if(fis != null) fis.close();
                if(ois != null) ois.close();
            } catch (IOException ex) {
                
            }
        }
    }
    @Override
    public void saveToFile(String fname) {
        try {
            FileOutputStream  fos = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            for (int i = 0; i < emList.length; i++) {
               out.writeObject(emList[i]);
            }
            System.out.println("Luu thanh cong!!!");
            fos.close(); out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    

    
}

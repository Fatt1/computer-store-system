package util;

import controller.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author User
 */
public class MyUtility {
    public final static double VAT = 0.08; 
    private static Scanner sc = new Scanner(System.in);

    // sử dụng static để cho mọi đối tượng đều sài đc, không cần phải new 
    // và không cần lưu lại giá trị nên sử dụng static
    public static int getAnInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound){
        int n;
        if(lowerBound > upperBound){
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }
        while(true){
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if(n < lowerBound || n > upperBound)
                    throw new Exception();
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;

            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    //Sử dụng Regular Expression: để bắt người dùng nhập đúng format mà mình đưa ra 
    public static String getId(String inputMsg, String errorMsg, String format){
        String id;
        boolean match;
        while(true){
                
                System.out.print(inputMsg);
                id = sc.nextLine().trim().toUpperCase();
                match = id.matches(format);
                if(id.isEmpty() || match == false || id.length() == 0)
                    System.out.println(errorMsg);
                else 
                    return id;
            }
        }
    // bắt lỗi việc nhập rỗng chuỗi
    public static String getString(String inputMsg, String errorMsg){
        String s;
        while(true){
            System.out.print(inputMsg);
            s = sc.nextLine().trim().toUpperCase();
            
            if(s.length() == 0 || s.isEmpty())
                System.out.println(errorMsg);
            else
                return s;
        }
    }
    public static LocalDate getDate (String inputMsg, String errorMsg, DateTimeFormatter formatter) {
        LocalDate birthday = null; // LocalDate là dataType để lưu ngày tháng năm
                                   // còn LocalDateTime lưu đc cả ngày tháng năm và thời gian để sử dụng cho việc
                                   // nhập hóa đơn, đăng nhập....
        String input;
        
        while (true) {            
            try {
                System.out.print(inputMsg);
                input = sc.nextLine();

                //để format theo kiểu định dạng mà mình mong muốn

                birthday = LocalDate.parse(input, formatter); // vì birthday chỉ lưu đc ngày tháng năm nên
                                                              // khi nhập chuỗi cần parse lại theo kiểu LocalDate
                return birthday;
            } catch (DateTimeParseException e) { // trường hợp mà người dùng nhập ngày kh tồn tại or ko đúng định dạng
                System.out.println(errorMsg);
            }
        }
    }
    
    public static YearMonth getYearMonth(String inputMsg, String errorMsg, DateTimeFormatter formatter){
        YearMonth yearMonth = null;
        String input;
        while (true) {            
            try {
                System.out.print(inputMsg);
                input = sc.nextLine();

                //để format theo kiểu định dạng mà mình mong muốn

                yearMonth = YearMonth.parse(input, formatter);
                return yearMonth;
            } catch (DateTimeParseException e) { // trường hợp mà người dùng nhập ngày kh tồn tại or ko đúng định dạng
                System.out.println(errorMsg);
            }
        }
    }
    
    public static boolean checkEmployeeID(String empID) {
        EmployeeManager em = new EmployeeManager();
        em.loadDataFromFile("DBStore/Employees.dat");
        if(em.searchByID(empID) == -1)
            return false;
        return true;
        
    }
    
    public static boolean checkCustomerID(String cusID) {
        CustomerManager cm = new CustomerManager();
        cm.loadDataFromFile("DBStore/Customers.dat");
        if(cm.searchByID(cusID) == -1)
            return false;
        return true;
    }
    
    public static boolean checkSupplierID(int supID){
        SupplierManager sm = new SupplierManager();
        sm.loadDataFromFile("DBStore/Suppliers.dat");
        if(sm.searchByID(supID) == -1)
            return false;
        return true;
    }    
    
    public static boolean checkProductID(String productID) {
        ProductManager pm = new ProductManager();
        pm.loadDataFromFile("DBStore/Products.dat");
        if (pm.searchtByID(productID) >= 0) {
            return true;
        }
        return false;
    }
}


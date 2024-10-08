/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.util.ArrayList;
import util.MyUtility;

/**
 *
 * @author User
 */
public class Menu {

    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList();

    // khi tạo cái menu thì cần tên của menu trước khi vô chọn lựa chọn
    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }
    // thêm mới lựa chọn cho menu
    public void addNewOption(String option) {
        // có nên làm thêm trách trường hợp trùng option với nhau không?
        // vì addNewOption này sẽ là do người lập trình sử dụng
        optionList.add(option);
    }
    // in danh sách menu
    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("Menu rong!!");
            return;
        }
        //System.out.println("\n------------------------------------");
        System.out.println(menuTitle);
        for (String s : optionList) {
            System.out.println(s);
        }
    }
    // trả về lựa chọn theo yêu cầu của user
    public int getChoice(){
        int maxChoice = optionList.size();
        String inputMsg = "Chon [1..." + maxChoice +"]: ";
        String errorMsg = "Vui long chon [1..." + maxChoice +"]";
        return MyUtility.getAnInteger(inputMsg, errorMsg, 1, maxChoice);
        // in ra câu lựa chọn nhập cho người dùng
    }
    
    public int getMaxChoice(){
        return optionList.size();
    }
    
}
    

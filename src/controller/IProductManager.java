/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;


import model.Accessory;
import model.Computer;
import model.Laptop;
import model.Product;
import ui.Menu;

/**
 *
 * @author User
 */
public interface IProductManager extends IManager{
    public void searchByID();
    public void searchProductByName();
    public Product[] searchProductByName(String proName);
    public Product searchObjectByID(String proID);
    public int searchtByID(String proID); // hàm helper
    public void showLowStockProducts();
    public void showBestSellingProducts();
    
    public Product[] filterCategory(ProductManager.ProductCategory category);
    public Product[] filterAtribute(ProductManager.ProductCategory category, ProductManager.Attribute attribute, String value);
    public Menu generalAttributeMenu();
    public Menu laptopSpecificMenu();
    public Menu computerSpecificMenu();
    public Menu accessorySpecificMenu();
    public void filterProduct();
}

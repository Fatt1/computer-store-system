/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import model.ImportDetails;

/**
 *
 * @author User
 */
public interface IImportDetailsManager extends IDetails{
    
    public boolean checkImportID(String importID);
    public ImportDetails[] searchImportDetails(Filter <ImportDetails> filter);
    public void searchImportDetailsByProductID();
    
}

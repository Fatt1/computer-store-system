/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import model.ImportInvoice;

/**
 *
 * @author User
 */
public interface IImport extends IManager {

    public void searchByImportID();
    public int searchByID(String importID);
    public ImportInvoice searchImportByID(String importID);
    public void showImportDetails();
}

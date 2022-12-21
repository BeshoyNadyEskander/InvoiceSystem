/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.C01_Bill_dialog;
import view.C02_Item_dialog;
import view.JF01_bill_of_sales_screen_form;
import model.F01_Bill_details;
import model.F02_Item_details;
import model.F04_ItemTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class A04_ButtonControl {
    
    // create frame from Bill view 
    private JF01_bill_of_sales_screen_form frame;
    
    // create object from main control and pass paramter (frame)
    A01_control controller = new  A01_control(frame);
    
    //create object from class bill dialog 
    C01_Bill_dialog  billDialog;
    C02_Item_dialog  itemDialog;

    // create constractor with prameter frame 
    public A04_ButtonControl(JF01_bill_of_sales_screen_form frame) {
        this.frame = frame;
    }

  
    public void deleteBillButton(){
        
        int select_row =frame.getBill_table().getSelectedRow();
        if (select_row != -1){
        
        JFrame  f = new JFrame("delete invoice");
       if(JOptionPane.showConfirmDialog(f,"confirm if you want to delete invoic","delete" ,JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
      {
     
            frame.getBills().remove(select_row);
            frame.getInvoiceTableModel().fireTableDataChanged();
            }else{
                   frame.getBills();
                  frame.getInvoiceTableModel().fireTableDataChanged();

      }
        } 
    }
    
    public void deleteItemButton(){
        // determine selected row of items table
        int selectedRow = frame.getItems_table().getSelectedRow();
        if (selectedRow != -1)
        {
       
            JFrame  f = new JFrame("delete item");
      if(JOptionPane.showConfirmDialog(f,"confirm if you want to delete item","delete" ,JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
      {
          //casting to get Bill model from parent
            F04_ItemTableModel itemTableModel = (F04_ItemTableModel) frame.getItems_table().getModel();
            itemTableModel.getItems().remove(selectedRow);
            itemTableModel.fireTableDataChanged();
            frame.getInvoiceTableModel().fireTableDataChanged();
         
      }else {
           F04_ItemTableModel itemTableModel = (F04_ItemTableModel) frame.getItems_table().getModel();
          itemTableModel.getItems();
            itemTableModel.fireTableDataChanged();
              frame.getInvoiceTableModel().fireTableDataChanged();

      }
        }  
    }
    
    public void createNewBillButton(){
        
         billDialog = new C01_Bill_dialog(frame);
        billDialog.setVisible(true);
    }
    
        
    public void createNewItemButton(){
        
         itemDialog = new C02_Item_dialog(frame);
         
           int selectBill= frame.getBill_table().getSelectedRow();
      
         if(selectBill == -1){

          JOptionPane.showMessageDialog(frame, "you should select invoice", "information", JOptionPane.INFORMATION_MESSAGE);
         itemDialog.setVisible(false);

         }else{
         itemDialog.setVisible(true);
    }
    }
   public void createBillCancel(){
        billDialog.setVisible(false);
        billDialog.dispose();
        billDialog=null;
    }
    public void createBillOk()
    {
        //DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        String date = billDialog.getBillDateField().getText();
        String customerName = billDialog.getCustNameField().getText();
        int IdBill = frame.getNextBillNum();
        
       
        try {
            // splite date in array -
            String[] datePart= date.split("/");
            
            // condition length for parts of date (day&month = 2  & year =4 numbers) 
            if(datePart.length <3 && datePart[0].length() <2 && datePart[1].length()<2 && datePart[2].length()<4)
             {
                 //error message
               JOptionPane.showMessageDialog(frame, "date is not correct you should write data dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
              //condition if user forget enter name show error message
            }else if(customerName.isEmpty()){
               JOptionPane.showMessageDialog(frame, "customer name is not correct", "Error", JOptionPane.ERROR_MESSAGE);

            }
            // user enter data correct create bill
            else{
                int day = Integer.parseInt(datePart[0]);
                int month = Integer.parseInt(datePart[1]);
                int year = Integer.parseInt(datePart[2]);
               
               if(day >31 ||day==0|| month==0|| month>12 ||datePart[2].length()>4 ||datePart[2].length()<4 ){
                JOptionPane.showMessageDialog(frame, "date is not correctly you should write data dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
               }
                
                else{
                   //create object bill details and pass value of new bill
                  F01_Bill_details newBillDetail = new F01_Bill_details(IdBill, date, customerName);
                  //put new object inside array list of bills
                  frame.getBills().add(newBillDetail);
                        //update table bill model 
                     frame.getInvoiceTableModel().fireTableDataChanged();
      
                       billDialog.setVisible(false);
                         billDialog.dispose();
                         billDialog=null;
               }
               
            }
       
        } catch (Exception ex) {
             ex.printStackTrace();
          JOptionPane.showMessageDialog(frame, "             you cannot create invoice.\n you sholud ensure format of date as dd/mm/yyyy and customer name", "Error", JOptionPane.ERROR_MESSAGE);            
        }
        
        
    }
    public void createItemCancel()
    {
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog = null;

    }
    public void createItemOk()
    {
        try{
        String item_name = itemDialog.getItemNameField().getText();
        String item_count = itemDialog.getItemCountField().getText();
        int count = Integer.parseInt(item_count);
        String item_price = itemDialog.getItemPriceField().getText();
        double price = Double.parseDouble(item_price);
         int selectBill= frame.getBill_table().getSelectedRow();

        
              if (selectBill == -1 ||item_name.isEmpty() || item_count.isEmpty() ||count == 0 ||item_price.isEmpty() || price ==0){
                   
             JOptionPane.showMessageDialog(frame, "you miss write details of item ", "Error", JOptionPane.ERROR_MESSAGE); 

              }
              
        
      
        else{
             System.out.println("should select invoice before creating new item");
            // create object for selected bill
            F01_Bill_details bill = frame.getBills().get(selectBill);
            // create object of items details pass name - price - count - object selected bill
            F02_Item_details itemDetails = new F02_Item_details(item_name, price, count, bill);
            bill.getItems().add(itemDetails);
            F04_ItemTableModel itemTableModel = (F04_ItemTableModel) frame.getItems_table().getModel();
            //itemTableModel.getItems().add(itemDetails);
            itemTableModel.fireTableDataChanged();
            frame.getInvoiceTableModel().fireTableDataChanged();
            
      
               
         }
       
        }catch(Exception ex){
            
             ex.printStackTrace();
              
          // JOptionPane.showMessageDialog(frame, "you should select invoice", "Error", JOptionPane.ERROR_MESSAGE);
           JOptionPane.showMessageDialog(frame, "you miss write details of item ", "Error", JOptionPane.ERROR_MESSAGE); 
            
        }
        
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog = null;     
        
    }
    
  
    
}

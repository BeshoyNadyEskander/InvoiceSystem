/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.JF01_bill_of_sales_screen_form;
import model.F01_Bill_details;
import model.F04_ItemTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Lenovo
 */
public class A03_Select implements ListSelectionListener{
    
    
    JF01_bill_of_sales_screen_form frame;

    
    public A03_Select() {
    }

    public A03_Select(JF01_bill_of_sales_screen_form frame) {
     this.frame = frame;
    }
    

    @Override
    public void valueChanged(ListSelectionEvent e) {
           // dtermine item which i selected in the Bill table to display all details in other table
        int indexSelected =frame.getBill_table().getSelectedRow();
        if(indexSelected != -1){
        System.out.println(" select row :" +indexSelected);
       
        F01_Bill_details selectBill = frame.getBills().get(indexSelected);
        
         frame.getBill_number_label().setText(""+selectBill.getId());
          frame.getBill_date_label().setText(selectBill.getDate());
          frame.getCustomer_name_label().setText(selectBill.getCustomerName());
          frame.getBill_total_label().setText(""+selectBill.getBillTotalCost());
          
          F04_ItemTableModel itemTableModel = new F04_ItemTableModel(selectBill.getItems());
           frame.getItems_table().setModel(itemTableModel);
           itemTableModel.fireTableDataChanged();
           frame.getBill_table().getSelectedRow();
           frame.getBill_total_label().setText(""+selectBill.getBillTotalCost());
     
    }
    }
}
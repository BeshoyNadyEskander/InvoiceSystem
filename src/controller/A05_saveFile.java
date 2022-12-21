/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.JF01_bill_of_sales_screen_form;
import model.F01_Bill_details;
import model.F02_Item_details;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author Lenovo
 */
public class A05_saveFile {
    
     // create frame from Bill view 
    private JF01_bill_of_sales_screen_form frame;
    
    // create object from main control and pass paramter (frame)
    A01_control controller = new  A01_control(frame);

    // create constractor with prameter frame 
    public A05_saveFile(JF01_bill_of_sales_screen_form frame) {
        this.frame = frame;
       
    }
    
    
      

    
    public void saveFile(){
       ArrayList<F01_Bill_details>bills = frame.getBills();
       String billHead ="";
        String itemHead ="";
        String safeFileBill;
      String safeFileLine;
        for(F01_Bill_details bill :bills ){
           

            safeFileBill = bill.getAsDataInFile();
            billHead +=safeFileBill + "\n";
            
            for(F02_Item_details item : bill.getItems()){
                
                safeFileLine = item.getAsDataInFile();
                
                itemHead += safeFileLine +"\n";
            }
        }
        System.out.println("safe file completed");
        try{
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);
        if(result == JFileChooser.APPROVE_OPTION){
            File billFill = fileChooser.getSelectedFile();
            FileWriter fileWriteBill = new FileWriter(billFill);
            fileWriteBill.write(billHead);
            fileWriteBill.flush();
            fileWriteBill.close();
            result = fileChooser.showSaveDialog(frame);
            
             if (result == JFileChooser.APPROVE_OPTION){
            File itemFile = fileChooser.getSelectedFile();
            FileWriter fileWriteItem = new FileWriter(itemFile);
            fileWriteItem.write(itemHead);
            fileWriteItem.flush();
            fileWriteItem.close();
            result = fileChooser.showSaveDialog(frame);
            
        }
        }
        }catch(Exception ex){}
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.JF01_bill_of_sales_screen_form;
import model.F01_Bill_details;
import model.F02_Item_details;
import model.F03_BillTableModel;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class A02_loadFile {
    
   // create frame from Bill view 
    private JF01_bill_of_sales_screen_form frame;
    
    // create object from main control and pass paramter (frame)
    A01_control controller = new  A01_control(frame);

    // create constractor with prameter frame 
    public A02_loadFile(JF01_bill_of_sales_screen_form frame) {
        this.frame = frame;
    }


      void open_load_file ()
    {
         JFileChooser fileChooser = new JFileChooser();
        try{
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                
              //select file with using get selectfile method and store in (File)
                File file = fileChooser.getSelectedFile();
                // get path of file and store path in filepath datatype(Path)
                Path filePath = Paths.get(file.getAbsolutePath());
                
                //create list of string to read file as lines 
                java.util.List<String>fileLines = Files.readAllLines(filePath);
                
                System.out.println("Bill has been read");
               // create arraylist of object bill detail from class F01_bill_detail to store data 
                ArrayList<F01_Bill_details> billArray = new ArrayList();
                
                // loop list lines to split lines
                for(String fileLine : fileLines)
                {
                    try {
                    
                    //split fileline int parts
                    String[] linePart = fileLine.split(",");
                    int bill_ID = Integer.parseInt(linePart[0]);
                    String bill_date = linePart[1];
                    String bill_customerName = linePart[2];
                    
                    // object from bill details and pass parameters id,date,name
                    F01_Bill_details bill = new F01_Bill_details(bill_ID, bill_date, bill_customerName);
                    // add value id,date ,name inside billArray
                    billArray.add(bill);
                    }catch(Exception ex){
                        ex.printStackTrace();

                        JOptionPane.showMessageDialog(frame, "the file of Invoice doesn't match legal format", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                }
                System.out.println("check point");
                
                result = fileChooser.showOpenDialog(frame);
                if(result ==JFileChooser.APPROVE_OPTION)
                {
                    File line_file =fileChooser.getSelectedFile();
                    Path line_path = Paths.get(line_file.getAbsolutePath());
                    java.util.List<String> linesOfBill =Files.readAllLines(line_path);
                    System.out.println("lines have been read");
                    
                    for(String lineBill :linesOfBill)
                    {
                        try{
                        String linePart [] = lineBill.split(",");
                        int bill_id = Integer.parseInt(linePart[0]);
                        String nameItem = linePart[1];
                        double priceItem = Double.parseDouble(linePart[2]);
                        int countItem = Integer.parseInt(linePart[3]);
                        
                        F01_Bill_details billInfo=null;
                        for(F01_Bill_details bill :billArray ){
                            if(bill.getId() == bill_id)
                            {
                                billInfo =bill;
                                break;
                            }
                        }
                        
                        //create object from item details and pass parameter 
                        F02_Item_details itemObj = new F02_Item_details(nameItem, priceItem, countItem, billInfo);
                        //relationship between file1 and file2
                        billInfo.getItems().add(itemObj);
                        }catch(Exception ex){
                            ex.printStackTrace();

                           JOptionPane.showMessageDialog(frame, "the file of Items doesn't match legal format", "Error", JOptionPane.ERROR_MESSAGE); 

                        }
                    }
                    System.out.println("check");
                }
                
                frame.setBills(billArray);
                 //create object from bill tabel model to insert into frame
                F03_BillTableModel invoiceTabelModel = new F03_BillTableModel(billArray);
                // set new creating table name (invoiceTabelModel)
                frame.setInvoiceTableModel(invoiceTabelModel);
                // get table of invoice from view screen and set value of creating table
               frame.getBill_table().setModel(invoiceTabelModel);
                //table should update data 
                frame.getInvoiceTableModel().fireTableDataChanged();
            }
    } catch(IOException ex){
        ex.printStackTrace();
        JOptionPane.showMessageDialog(frame, "can not load this file inside the system", "Error", JOptionPane.ERROR_MESSAGE); 
        
    }
    }
          
    
}

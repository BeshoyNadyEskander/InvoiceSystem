
package controller;

import view.C01_Bill_dialog;
import view.C02_Item_dialog;
import view.JF01_bill_of_sales_screen_form;
import model.F01_Bill_details;
import model.F02_Item_details;
import model.F03_BillTableModel;
import model.F04_ItemTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class A01_control  implements ActionListener {

    JF01_bill_of_sales_screen_form frame;
 
    private A04_ButtonControl buttonControl;
  
    public A01_control() {
    }

    public A01_control(JF01_bill_of_sales_screen_form frame) {
     this.frame = frame;
    }
    
    

    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actioncommand = e.getActionCommand();
        System.out.println("Action:" +actioncommand );
        
        switch(actioncommand){
            
            case "Load File":
             
                //create object from class A02_load file and put parameter frame
               A02_loadFile loadfile = new A02_loadFile(frame);
            // call method open load file
                loadfile.open_load_file();
               
                break;
                
               case "Save File":
                   
                A05_saveFile file =new  A05_saveFile(frame);
                file.saveFile();
                
                break;
               
              case "Create New Bill":
                      
                 buttonControl = new A04_ButtonControl(frame);
                 buttonControl.createNewBillButton();
                
                break;
                
                 case "Delete Bill":
                
                    buttonControl = new A04_ButtonControl(frame);
                    buttonControl.deleteBillButton();
                break;
                
                 case "Add new Item":
                buttonControl = new A04_ButtonControl(frame);
                buttonControl.createNewItemButton();
                break;
                
                 case "Remove Item":
              buttonControl = new A04_ButtonControl(frame);
              buttonControl.deleteItemButton();
                break;
                
                 case "createBillOK":
                    buttonControl.createBillOk();
              
                     break;
                     
                case "create Bill Cancel":
                    
                     buttonControl.createBillCancel();
                     break;
                
                
                case "create Item OK":
                         
                    
                    buttonControl.createItemOk();
                         break;
                         
                case "create Item Cancel":
                             
                     
                     buttonControl.createItemCancel();
                         break;
        }
     
    }
     
    
  

    
}

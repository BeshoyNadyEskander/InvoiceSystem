
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class F04_ItemTableModel extends AbstractTableModel{
    
    private ArrayList<F02_Item_details>items;
    
       private String [] columns = {"No." , "Item Name" , "Item price" , "count" , "total price"};

    public F04_ItemTableModel(ArrayList<F02_Item_details> items) {
        this.items = items;
    }

    public ArrayList<F02_Item_details> getItems() {
        return items;
    }

   

       


    @Override
    public int getRowCount() {
        
        return items.size();
    }
    

    @Override
    public int getColumnCount() {
        
        return columns.length;
    }

    @Override
    public String getColumnName(int index_column) {
        
        return columns[index_column];
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
         F02_Item_details item = items.get(rowIndex);
         
         switch(columnIndex){
             case 0: return item.getBill().getId();
             case 1: return item.getName_item();
             case 2: return item.getPrice_item();
             case 3: return item.getCount_item();
             case 4: return item.getItemTotalCost();
             default:return "";
        
         }
    }

    public void getRowCount(int indexSelected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

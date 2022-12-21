
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class F03_BillTableModel extends AbstractTableModel{
    
    
   private ArrayList<F01_Bill_details> bills;
   
   private String [] columns = {"No." , "Date" , "Customer" , "Total"};

    public F03_BillTableModel(ArrayList<F01_Bill_details> bills)
    {
        this.bills = bills;
    }
   
   

    @Override
    public int getRowCount() {
        return bills.size();
    }

    @Override
    public int getColumnCount() {
        
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        F01_Bill_details billDetail= bills.get(rowIndex);
        
        switch (columnIndex){
            case 0: return billDetail.getId();
            case 1: return billDetail.getDate();
            case 2: return billDetail.getCustomerName();
            case 3: return billDetail.getBillTotalCost();
            default: return " ";
        }
        
        
    }
   
   

    
    
}

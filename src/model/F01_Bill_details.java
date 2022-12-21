
package model;

import java.util.ArrayList;


public class F01_Bill_details {
    // we need to use encapsulation so we should use setter and getter for each variable
    private int Id;
    
    private String date;
    private String customerName;
    
    // because every bill has multi item we need store in arraylist
    private ArrayList <F02_Item_details> items;

       
    public F01_Bill_details()
    {
        
    }

    public F01_Bill_details(int Id, String date, String customerName) {
        this.Id = Id;
        this.date = date;
        this.customerName = customerName;
        
        
    }
    
      // method return total cost for invoice 
    public double getBillTotalCost()
    {
        double totalCost =0.0;
        
        //loop on array list of items to get price for items 
        for(F02_Item_details priceItem : getItems())
        {
            totalCost += priceItem.getItemTotalCost();
        }
        return totalCost;
    }
    
     public ArrayList<F02_Item_details> getItems(){
         // we should ensure arraylist (items) not null >> if it is null .. should create value 
        if(items == null)
        {
            items = new ArrayList<>();
        }
        return items;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "F01_Bill_details{" + "Id=" + Id + ", date=" + date + ", customerName=" + customerName + '}';
    }
    
    // method retrn data inside file when user use save button 
   public String getAsDataInFile() {
       return Id + "," +date + "," + customerName;
   }

    
    
}

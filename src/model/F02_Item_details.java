
package model;


// class of details line of item product 
public class F02_Item_details {
    
    private String name_item;
    private double price_item;
    private int count_item;
    
    private F01_Bill_details Bill;

    

    public F02_Item_details() {
    }

    /*public F02_Item_details( String name_item, double price_item, int count_item) {
        this.name_item = name_item;
        this.price_item = price_item;
        this.count_item = count_item;
    }*/
      public F02_Item_details( String name_item, double price_item, int count_item ,F01_Bill_details Bil) {
        this.name_item = name_item;
        this.price_item = price_item;
        this.count_item = count_item;
        this.Bill = Bil;
    }

    


    public String getName_item() {
        return name_item;
    }

    public void setName_item(String name_item) {
        this.name_item = name_item;
    }

    public double getPrice_item() {
        return price_item;
    }

    public void setPrice_item(double price_item) {
        this.price_item = price_item;
    }

    public int getCount_item() {
        return count_item;
    }

    public void setCount_item(int count_item) {
        this.count_item = count_item;
    }
    
    // determine total cost from price of item * count number of items (total price of iems )
    public double getItemTotalCost()
    {
        return price_item * count_item;
    }

    @Override
    public String toString() {
        return "F02_Item_details{" + "Id_item=" + Bill.getId() + ", name_item=" + name_item + ", price_item=" + price_item + ", count_item=" + count_item + '}';
    }

    public F01_Bill_details getBill() {
        return Bill;
    }
    
    
    public String getAsDataInFile() {
        return Bill.getId()+ "," + name_item + "," + price_item + "," + count_item;
    }
    
  
}

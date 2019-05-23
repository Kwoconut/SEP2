package model;

import java.io.Serializable;

public class Sale implements Serializable
{
   private int ID;
   private MyDate startDate;
   private MyDate endDate;
   private Product product;
   private int amount;
   private boolean isChangedValue;

   public Sale(int ID,MyDate startDate, MyDate endDate, Product product, int amount)
   {
	  this.ID=ID;
      this.startDate = startDate;
      this.endDate = endDate;
      this.product = product;
      this.amount = amount;
      isChangedValue = false;
   }
   
   public Sale(int ID,MyDate startDate, MyDate endDate, Product product, int amount,boolean isChangedValue)
   {
	  this.ID=ID;
      this.startDate = startDate;
      this.endDate = endDate;
      this.product = product;
      this.amount = amount;
      this.isChangedValue = isChangedValue;
   }
  
   public Product getProduct()
   {
      return product;
   }

   public MyDate getStartDate()
   {
      return startDate;
   }

   public MyDate getEndDate()
   {
      return endDate;
   }

   public int getPrice()
   {
      return product.getPrice();
   }
   
   public int getAmount() {
      return amount;
   }
   
   public boolean getIsChangedValue()
   {
	   return isChangedValue;
   }
   public int getID() 
   {
		return ID;
   }
   public void setIsChangedValue()
   {
	   if (isChangedValue==true)
			   {
		   isChangedValue = false;
			   }
	   else if (isChangedValue==false)
	   {
		   isChangedValue = true;
	   }
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Sale))
      {
         return false;
      }
      Sale other = (Sale) obj;
      
      return product.equals(other.product) && startDate.equals(other.startDate)
            && endDate.equals(other.endDate);
   }
   
   public String toString() {
      String s = "";
      s+= startDate+""+endDate+""+product;
      return s;
   }


   
}

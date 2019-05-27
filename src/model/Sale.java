package model;

import java.io.Serializable;

public class Sale implements Serializable
{
   /**
    * 
    */
   private static final long serialVersionUID = 2322971685758964438L;
   private int ID;
   private MyDate startDate;
   private MyDate endDate;
   private Product product;
   private int amount;
   private boolean isChangedValue;

   public Sale(int ID, MyDate startDate, MyDate endDate, Product product,
         int amount)
   {
      this.ID = ID;
      this.startDate = startDate;
      this.endDate = endDate;
      this.product = product;
      this.amount = amount;
      isChangedValue = false;
   }

   public Sale(int ID, MyDate startDate, MyDate endDate, Product product,
         int amount, boolean isChangedValue)
   {
      this.ID = ID;
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

   public int getAmount()
   {
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
      isChangedValue = true;
   }

   public boolean validDate()
   {
      if (startDate.isAfter(MyDate.now()) || endDate.isBefore(startDate)
            || startDate == null || endDate == null)
      {
         return false;
      }
      else
      {
         return true;
      }
   }

   public boolean validPrice()
   {
      if (amount <= 0 || amount >= 100)
      {
         return false;
      }
      else
      {
         return true;
      }

   }

   public double getPriceAfterSaleApplied()
   {
      return (double) getPrice() - (getAmount() * getPrice()) / 100;
   }

   public double getInitialPrice()
   {
      return (double) product.getPrice() / (100 - amount) * 100;
   }

   public static double getReducedPrice(int price, int amount)
   {
      return (double) price - (amount * price) / 100;
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Sale))
      {
         return false;
      }
      Sale other = (Sale) obj;
      return ID == other.getID() && startDate.equals(other.getStartDate())
            && endDate.equals(other.getEndDate())
            && product.equals(other.getProduct()) && amount == other.getAmount()
            && isChangedValue == other.getIsChangedValue();
   }

   public String toString()
   {
      String s = "";
      s += startDate + "" + endDate + "" + product + "" + amount + ""
            + isChangedValue;
      return s;
   }

   public void setChangedValue(boolean value)
   {
      this.isChangedValue = value;

   }

}

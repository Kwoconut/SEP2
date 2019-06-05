package model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Sale implements Serializable
{

   private static final long serialVersionUID = 2322971685758964438L;
   private int ID;
   private MyDate startDate;
   private MyDate endDate;
   private Product product;
   private int amount;
   private SaleState state;
   private static DecimalFormat df = new DecimalFormat("#.##");

   public Sale(int ID, MyDate startDate, MyDate endDate, Product product,
         int amount)
   {
      this.ID = ID;
      this.startDate = startDate.copy();
      this.endDate = endDate.copy();
      this.product = product;
      this.amount = amount;
      this.state = new UpcomingSale();
   }

   public Sale(int ID, MyDate startDate, MyDate endDate, Product product,
         int amount, SaleState state)
   {
      this.ID = ID;
      this.startDate = startDate.copy();
      this.endDate = endDate.copy();
      this.product = product;
      this.amount = amount;
      this.state = state;
   }

   public void setState(SaleState state)
   {
      this.state = state;
   }

   public void setNextState()
   {
      state.setNextState(this);
   }

   public SaleState getState()
   {
      return state;
   }

   public void setProduct(Product product)
   {
      this.product = product;
   }

   public Product getProduct()
   {
      return product;
   }

   public MyDate getStartDate()
   {
      return startDate.copy();
   }

   public MyDate getEndDate()
   {
      return endDate.copy();
   }

   public double getPrice()
   {
      return Double.valueOf(df.format(product.getPrice()));
   }

   public int getAmount()
   {
      return amount;
   }

   public int getID()
   {
      return ID;
   }

   public boolean validDate()
   {
      if (startDate.isBefore(MyDate.now()) || endDate.isBefore(startDate))
      {
         return false;
      }
      else
      {
         return true;
      }
   }

   public boolean overridesOtherSalesWithSameProduct(Sale sale)
   {
      if (this.product.equals(sale.getProduct()))
      {
         if (this.startDate.isBetween(sale.getStartDate(), sale.getEndDate())
               || this.endDate.isBetween(sale.getStartDate(), sale.getEndDate())
               || this.startDate.equals(sale.getStartDate())
               || this.endDate.equals(sale.getStartDate())
               || this.startDate.equals(sale.getEndDate())
               || this.endDate.equals(sale.getEndDate()))
         {
            return true;
         }
         return false;
      }
      else
      {
         return false;
      }
   }

   public static double getPriceBeforeSaleApplies(double price, int amount)
   {
      return Double.valueOf(df.format(price / (100 - amount) * 100));
   }

   public static double getReducedPrice(double price, int amount)
   {
      return Double.valueOf(df.format(price - (amount * price) / 100));
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
            && product.equals(other.getProduct())
            && amount == other.getAmount();
   }

   public String toString()
   {
      String s = "";
      s += startDate + "" + endDate + "" + product + "" + amount + "";
      return s;
   }

}

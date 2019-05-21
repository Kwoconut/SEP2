package model;

public class Sale
{
   private MyDate startDate;
   private MyDate endDate;
   private Product product;

   public Sale(MyDate startDate, MyDate endDate, Product product, int amount)
   {
      this.startDate = startDate;
      this.endDate = endDate;
      this.product = product;
      this.product.setPrice(product.getPrice() - (amount * product.getPrice()/100)); 
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

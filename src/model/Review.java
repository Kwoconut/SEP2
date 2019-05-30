package model;

import java.io.Serializable;

public class Review implements Serializable
{
   private double rating;
   private String message;
   private Product product;
   private int ID;

   public Review(int ID, double rating, String message, Product product)
   {
      this.ID = ID;
      this.rating = rating;
      this.message = message;
      this.product = product;
   }

   public double getRating()
   {
      return rating;
   }

   public String getMessage()
   {
      return message;
   }

   public Product getProduct()
   {
      return product;
   }

   public int getID()
   {
      return ID;
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Offer))
      {
         return false;
      }
      Review other = (Review) obj;
      return ID == other.getID() && rating == other.getRating()
            && message.equals(other.getMessage())
            && product.equals(other.getProduct());
   }
}

package model;

import java.io.Serializable;

public class Product implements Serializable
{
   private String name;
   private String finish;
   private String details;
   private String colour;
   private String type;
   private int price;
   public static String TYPE_PLATED_SHEET = "Plated Sheet";
   public static String TYPE_METAL_SHEET = "Metal Sheet";
   public static String TYPE_CLICK_SHEET = "Click Sheet";
   public static String TYPE_RAIN_SYSTEM = "Rain System";
   public static String TYPE_METAL_TILE = "Metal Tile";

   public Product(String name, int price, String finish, String details,
         String colour, String type)
   {
      this.name = name;
      this.price = price;
      this.finish = finish;
      this.details = details;
      this.colour = colour;
      this.type = type;
   }

   public String getName()
   {
      return name;
   }

   public int getPrice()
   {
      return price;
   }

   public void setPrice(int price)
   {
      this.price = price;
   }

   public String getFinish()
   {
      return finish;
   }

   public String getDetails()
   {
      return details;
   }

   public String getColour()
   {
      return colour;
   }

   public String getType()
   {
      return type;
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Product))
      {
         return false;
      }

      Product other = (Product) obj;
      return name.equals(other.getName()) && finish.equals(other.getFinish())
            && colour.equals(other.getColour())
            && details.equals(other.getDetails())
            && type.equals(other.getType()) && price == other.getPrice();
   }

   public String toString()
   {
      String s = "";

      s += name + "\n" + finish + "\n" + colour + "\n" + details + "\n" + type
            + "\n" + price;
      return s;
   }

}

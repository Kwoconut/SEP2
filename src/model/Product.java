package model;

import java.io.Serializable;

public class Product implements Serializable
{
   private int ID;
   private String name;
   private String colour;
   private String type;
   private String imageID;
   private int price;
   public static String TYPE_PLATED_SHEET = "Plated Sheet";
   public static String TYPE_METAL_SHEET = "Metal Sheet";
   public static String TYPE_CLICK_SHEET = "Click Sheet";
   public static String TYPE_RAIN_SYSTEM = "Rain System";
   public static String TYPE_METAL_TILE = "Metal Tile";

   public Product(int ID, String name, int price, String colour,
         String type, String imageID)
   {
      this.ID = ID;
      this.name = name;
      this.price = price;
      this.colour = colour;
      this.type = type;
      this.imageID = imageID;
   }
   
   public int getID()
   {
      return ID;
   }
   
   public String getImageID()
   {
	   return imageID;
   }

   public void setID(int iD)
   {
      ID = iD;
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
      return name.equals(other.getName())
            && colour.equals(other.getColour())

            && type.equals(other.getType()) && price == other.getPrice()
            
            && imageID.equals(other.getImageID());
   }

   public String toString()
   {
      String s = "";

      s += name + "\n" + ID + "\n" + colour + "\n" + type + "\n" + price + "\n" + imageID;
      return s;
   }

}

package model;

import java.util.ArrayList;

public class IDGenerator
{
   public static int generateProductID(ArrayList<Product> products)
   {
      for (int i = 1; i < generateProductMaxID(products); i++)
      {
         boolean flag = false;
         for (Product product : products)
         {
            if (product.getID() == i)
            {
               flag = true;
            }
         }
         if (!flag)
         {
            return i;
         }
      }
      return products.size() + 1;
   }

   public static int generateOfferID(ArrayList<Offer> offers)
   {
      for (int i = 1; i < generateOfferMaxID(offers); i++)
      {
         boolean flag = false;
         for (Offer offer : offers)
         {
            if (offer.getID() == i)
            {
               flag = true;
            }
         }
         if (!flag)
         {
            return i;
         }
      }
      return offers.size() + 1;

   }

   private static int generateProductMaxID(ArrayList<Product> products)
   {
      int maxi = 1;
      for (Product product : products)
      {
         if (product.getID() > maxi)
         {
            maxi = product.getID();
         }
      }
      return maxi;
   }

   private static int generateOfferMaxID(ArrayList<Offer> offers)
   {
      int maxi = 1;
      for (Offer offer : offers)
      {
         if (offer.getID() > maxi)
         {
            maxi = offer.getID();
         }
      }
      return maxi;
   }

}

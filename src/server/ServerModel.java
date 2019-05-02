package server;

import java.util.ArrayList;

import model.Offer;
import model.Product;

public class ServerModel
{
   private ArrayList<Product> products;
   private ArrayList<Offer> offers;

   public ServerModel()
   {
      products = new ArrayList<Product>();
      offers = new ArrayList<Offer>();
      sampleData();
   }

   private void sampleData()
   {
      addProduct(new Product(Product.TYPE_METAL_SHEET, 1500,
            "Sa mori tu", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_METAL_TILE, 2000,
            "Sa mori tu", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_PLATED_SHEET, 3000,
            "Sa mori tu", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_RAIN_SYSTEM, 5000,
            "Sa mori tu", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_RAIN_SYSTEM, 5000,
            "Sa mori tu", "sugator pentru gicu"));
   }

   public void addProduct(Product product)
   {
      products.add(product);
   }

   public ArrayList<Product> getProducts()
   {
      return products;
   }
   
   public ArrayList<Product> getServerProducts()
   {
      return products;
   }
   
   public void addOffer(Offer offer)
   {
      offers.add(offer);
      System.out.println(offers);
   }
   
   public ArrayList<Offer> getOffers()
   {
	   return offers;
   }
}

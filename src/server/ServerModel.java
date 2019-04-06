package server;

import java.util.ArrayList;

import model.Product;

public class ServerModel
{
   private ArrayList<Product> products;

   public ServerModel()
   {
      products = new ArrayList<Product>();
      sampleData();
   }

   private void sampleData()
   {
      addProduct(new Product(Product.TYPE_METAL_SHEET, 1500, "cacat",
            "Sa mori tu", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_METAL_TILE, 2000, "cacat",
            "Sa mori tu", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_PLATED_SHEET, 3000, "cacat",
            "Sa mori tu", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_RAIN_SYSTEM, 5000, "cacat",
            "Sa mori tu", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_RAIN_SYSTEM, 5000, "cacat",
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
}

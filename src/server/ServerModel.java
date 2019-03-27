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
            "Sa mori tu", "nicoleta", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_METAL_TILE, 2000, "cacat",
            "Sa mori tu", "nicoleta", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_PLATED_SHEET, 3000, "cacat",
            "Sa mori tu", "nicoleta", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_RAIN_SYSTEM, 5000, "cacat",
            "Sa mori tu", "nicoleta", "sugator pentru gicu"));
      addProduct(new Product(Product.TYPE_RAIN_SYSTEM, 5000, "cacat",
            "Sa mori tu", "nicoleta", "sugator pentru gicu"));
   }

   public void addProduct(Product product)
   {
      products.add(product);
   }

   public Product[] getProducts()
   {
      Product[] values = new Product[products.size()];
      for (int i = 0; i < products.size(); i++)
      {
         values[i] = products.get(i);
      }
      return values;
   }
}

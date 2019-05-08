package server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import model.Offer;
import model.Product;

public class ServerModel
{
   private ArrayList<Product> products;
   private ArrayList<Offer> offers;
   
   private PropertyChangeSupport support = new PropertyChangeSupport(this);

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
      addOffer(new Offer("Gicu","59423249","Gicu@gmail.com","hello my friends i want new roof"));
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
      support.firePropertyChange("OFFERADDED", null, offer);
   }
   
   public ArrayList<Offer> getOffers()
   {
	   return offers;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      support.addPropertyChangeListener(listener);
   }
}

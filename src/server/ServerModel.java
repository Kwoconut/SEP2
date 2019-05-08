package server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

import DBSConnection.OfferDatabaseHandler;
import DBSConnection.ProductDatabaseHandler;
import DBSConnection.StoreOfferPersistence;
import DBSConnection.StoreProductPersistence;
import model.Offer;
import model.Product;

public class ServerModel
{
   private ArrayList<Product> products;
   private ArrayList<Offer> offers;
   private StoreProductPersistence databaseProductAccess;
   private StoreOfferPersistence databaseOfferAccess;

   private PropertyChangeSupport support = new PropertyChangeSupport(this);

   public ServerModel() throws SQLException
   {
      products = new ArrayList<Product>();
      offers = new ArrayList<Offer>();
      databaseProductAccess = new ProductDatabaseHandler();
      databaseOfferAccess = new OfferDatabaseHandler();
      //sampleDataForCreation();
      removeProduct(new Product(100, "Sugi pula", 555, "Red", Product.TYPE_METAL_SHEET));
      loadProducts();
      loadOffers();
   }
   
   //add data for first setup
   private void sampleDataForCreation() throws SQLException
   {
      databaseProductAccess
            .addProduct(new Product(100, "Metal Sheet", 8888, "Red", Product.TYPE_METAL_SHEET));
      databaseProductAccess
            .addProduct(new Product(200, "Click Sheet", 9999, "Black", Product.TYPE_CLICK_SHEET));
      databaseProductAccess
            .addProduct(new Product(300, "Plated Sheet", 10000, "Silver", Product.TYPE_PLATED_SHEET));
      databaseProductAccess
            .addProduct(new Product(400, "Rain System", 3333, "Silver", Product.TYPE_RAIN_SYSTEM));
      databaseProductAccess
            .addProduct(new Product(500, "Metal Tile", 666, "Red", Product.TYPE_METAL_TILE));
   }
   
   //getters for ArrayLists
   public ArrayList<Product> getProducts()
   {
      return products;
   }

   public ArrayList<Offer> getOffers()
   {
      return offers;
   }
   
   //loading methods
   public void loadProducts()
   {
      try
      {
         products = databaseProductAccess.loadProducts();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void loadOffers()
   {
      try
      {
         offers = databaseOfferAccess.loadOffers();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   //adding methods
   public void addProduct(Product product)
   {
      try
      {
         databaseProductAccess.addProduct(product);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }    
      loadProducts();
      support.firePropertyChange("PRODUCTADDED", null, product);
   }
   
   public void addOffer(Offer offer)
   {
      try
      {
         databaseOfferAccess.addOffer(offer);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      loadOffers();
      support.firePropertyChange("OFFERADDED", null, offer);
   }
   
   //update methods   
   public void updateProduct(Product product)
   {
      try
      {
         databaseProductAccess.updateProduct(product);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      loadProducts();
   }
   
   public void updateOffer(Offer offer)
   {
      try
      {
         databaseOfferAccess.updateOffer(offer);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      loadOffers();
   }
   
   //remove methods
   
   public void removeProduct(Product product)
   {
      try
      {
         databaseProductAccess.removeProduct(product);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      loadProducts();
   }
   
   public void removeOffer(Offer offer)
   {
      try
      {
         databaseOfferAccess.removeOffer(offer);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      loadOffers();
   }
   
   //clear methods
   public void clearProducts() throws SQLException
   {
      databaseProductAccess.clearProducts();
   }
   
   public void clearOffer() throws SQLException
   {
      databaseOfferAccess.clearOffers();
   }

   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      support.addPropertyChangeListener(listener);
   }
}

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
      loadProducts();
      loadOffers();
   }

   private void loadProducts() throws SQLException
   {
      products = databaseProductAccess.loadProducts();
   }

   private void loadOffers() throws SQLException
   {
      offers = databaseOfferAccess.loadOffers();
   }

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
      try
      {
         loadProducts();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("PRODUCTADDED", null, product);
   }

   public ArrayList<Product> getProducts()
   {
      return products;
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
      support.firePropertyChange("OFFERADDED", null, offer);
   }
   
   public void removeOffer(Offer offer) 
   {
		try {
			databaseOfferAccess.removeOffer(offer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		support.firePropertyChange("OFFERREMOVED", null, offer);
		
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

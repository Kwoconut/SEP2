package server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

import DBSConnection.DBSQuery;
import DBSConnection.OfferDatabaseHandler;
import DBSConnection.ProductDatabaseHandler;
import DBSConnection.ReviewDatabaseHandler;
import DBSConnection.SaleDatabaseHandler;
import DBSConnection.StoreOfferPersistence;
import DBSConnection.StoreProductPersistence;
import DBSConnection.StoreReviewPersistence;
import DBSConnection.StoreSalePersistence;
import model.MyDate;
import model.Offer;
import model.Product;
import model.Review;
import model.Sale;

public class ServerModel
{
   private ArrayList<Product> products;
   private ArrayList<Offer> offers;
   private ArrayList<Sale> sales;
   private ArrayList<Review> reviews;
   private StoreProductPersistence databaseProductAccess;
   private StoreOfferPersistence databaseOfferAccess;
   private StoreSalePersistence databaseSaleAccess;
   private StoreReviewPersistence databaseReviewAccess;
   private DBSQuery queryHandler;

   private PropertyChangeSupport support = new PropertyChangeSupport(this);

   public ServerModel() throws SQLException
   {
      products = new ArrayList<Product>();
      offers = new ArrayList<Offer>();
      sales = new ArrayList<Sale>();
      reviews = new ArrayList<Review>();
      queryHandler = new DBSQuery("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "password");
      databaseProductAccess = new ProductDatabaseHandler(queryHandler);
      loadProducts();
      databaseOfferAccess = new OfferDatabaseHandler(queryHandler);
      loadOffers();
      databaseSaleAccess = new SaleDatabaseHandler(queryHandler);
      loadSales();
      databaseReviewAccess = new ReviewDatabaseHandler(queryHandler);
      loadReviews();
      // clearProducts();
      // sampleDataForCreation();
   }

   // add data for first setup
   private void sampleDataForCreation() throws SQLException
   {
      databaseProductAccess.addProduct(new Product(1, "Metal Sheet", 8888,
            "Red", Product.TYPE_METAL_SHEET, ""));
      databaseProductAccess.addProduct(new Product(2, "Click Sheet", 9999,
            "Black", Product.TYPE_CLICK_SHEET, ""));
      databaseProductAccess.addProduct(new Product(3, "Plated Sheet", 10000,
            "Silver", Product.TYPE_PLATED_SHEET, ""));
      databaseProductAccess.addProduct(new Product(4, "Rain System", 3333,
            "Silver", Product.TYPE_RAIN_SYSTEM, ""));
      databaseProductAccess.addProduct(new Product(5, "Metal Tile", 666, "Red",
            Product.TYPE_METAL_TILE, ""));
   }

   // getters for ArrayLists
   public ArrayList<Product> getProducts()
   {
      return products;
   }

   public ArrayList<Offer> getOffers()
   {
      return offers;
   }

   public ArrayList<Sale> getSales()
   {
      return sales;
   }

   // loading methods
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

   public void loadSales()
   {
      try
      {
         sales = databaseSaleAccess.loadSales(products);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void loadReviews()
   {
      try
      {
         reviews = databaseReviewAccess.loadReviews(products);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
   }

   // adding methods
   public void addProduct(Product product)
   {
      products.add(product);
      try
      {
         databaseProductAccess.addProduct(product);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("PRODUCTADDED", null, product);
   }

   public void addOffer(Offer offer)
   {
      offers.add(offer);
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

   // update methods
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
      support.firePropertyChange("PRODUCTUPDATED", null, product);
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
      support.firePropertyChange("OFFERUPDATED", null, offer);
   }

   // remove methods

   public void removeProduct(Product product)
   {
      products.remove(product);
      try
      {
         databaseProductAccess.removeProduct(product);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("PRODUCTREMOVED", null, product);
   }

   public void removeOffer(Offer offer)
   {
      offers.remove(offer);
      try
      {
         databaseOfferAccess.removeOffer(offer);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("OFFERREMOVED", null, offer);
   }

   // clear methods
   public void clearProducts()
   {
      try
      {
         databaseProductAccess.clearProducts();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void clearOffers()
   {
      try
      {
         databaseOfferAccess.clearOffers();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      support.addPropertyChangeListener(listener);
   }

   public void updateRemoveSale(Sale sale)
   {
      for (int i = 0; i < products.size(); i++)
      {
         if (products.get(i).getID() == sale.getProduct().getID())
         {
            products.get(i).setPrice((int) sale.getInitialPrice());
            break;
         }
      }
      try
      {
         databaseSaleAccess.updateRemoveSale((int) sale.getInitialPrice(),
               sale.getProduct().getID());
         databaseSaleAccess.removeSale(sale);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("SALEEDITED", null, sale);
   }

   public void removeSale(Sale sale)
   {
      sale.getProduct().setPrice((int) sale.getInitialPrice());
      sales.remove(sale);
      try
      {
         databaseSaleAccess.removeSale(sale);
         databaseSaleAccess.updateRemoveSale(sale.getProduct().getPrice(),
               sale.getProduct().getID());
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("SALEREMOVED", null, sale);

   }

   public void updateAddSale(Sale sale)
   {
      
      products.parallelStream()
            .filter(product -> product.getID() == sale.getID()).findFirst()
            .get().setPrice(((int) sale.getPriceAfterSaleApplied()));
      sales.parallelStream()
            .filter(sampleSale -> sampleSale.getID() == sale.getID())
            .findFirst().get().setIsChangedValue();

/*
 * for (int i = 0; i < products.size(); i++) { if (products.get(i).getID() ==
 * sale.getProduct().getID()) { newPrice = (int)
 * sale.getPriceAfterSaleApplied(); products.get(i).setPrice(newPrice); break; }
 * } for (int i = 0; i < sales.size(); i++) { if (sales.get(i).getID() ==
 * sale.getID()) { sales.get(i).setIsChangedValue(); break; } }
 */
      try
      {
         databaseSaleAccess.updateAddSale((int) sale.getPriceAfterSaleApplied(),
               sale.getProduct().getID());
         databaseSaleAccess.changedValue(sale);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("SALEEDITED", null, sale);
   }

   public void ChangedValue(Sale sale)
   {
      Sale sendSale = null;
      for (int i = 0; i < sales.size(); i++)
      {
         if (sales.get(i).equals(sale))
         {
            sales.get(i).setIsChangedValue();
            sendSale = sales.get(i);
            break;
         }
      }
      try
      {
         databaseSaleAccess.changedValue(sale);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("SALEEDITED", null, sendSale);

   }

   public void addSale(Sale sale)
   {
      MyDate now = new MyDate();
      if (sale.getIsChangedValue() == false && now.equals(sale.getStartDate()))
      {
         sale.setChangedValue(!sale.getIsChangedValue());
         sale.getProduct().setPrice((int) sale.getPriceAfterSaleApplied());
      }
      sales.add(sale);
      try
      {
         databaseSaleAccess.addSale(sale);
         databaseSaleAccess.updateAddSale((int) sale.getProduct().getPrice(),
               sale.getProduct().getID());
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("SALEADDED", null, sale);
   }

   public void addReview(Review review)
   {
      reviews.add(review);
      try
      {
         databaseReviewAccess.addReview(review);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      support.firePropertyChange("REVIEWADDED", null, review);
   }

   public void removeReview(Review review)
   {
      reviews.remove(review);
      try
      {
         databaseReviewAccess.removeReveiew(review);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      support.firePropertyChange("REVIEWREMOVED", null, review);
   }

   public void clearReviews()
   {
      try
      {
         databaseReviewAccess.clearReviews();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public ArrayList<Review> getReviews()
   {
      return reviews;
   }

public void editSale(Sale sale) {
	// TODO Auto-generated method stub
	
}
}
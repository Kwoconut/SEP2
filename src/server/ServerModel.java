package server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

import DBSConnection.AccountDatabaseHandler;
import DBSConnection.DBSQuery;
import DBSConnection.OfferDatabaseHandler;
import DBSConnection.ProductDatabaseHandler;
import DBSConnection.ReviewDatabaseHandler;
import DBSConnection.SaleDatabaseHandler;
import DBSConnection.StoreAccountPersistence;
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
   private ArrayList<String> usernames;
   private ArrayList<String> password;
   private StoreProductPersistence databaseProductAccess;
   private StoreOfferPersistence databaseOfferAccess;
   private StoreSalePersistence databaseSaleAccess;
   private StoreReviewPersistence databaseReviewAccess;
   private StoreAccountPersistence databaseAccountAccess;
   private DBSQuery queryHandler;

   private PropertyChangeSupport support = new PropertyChangeSupport(this);

   public ServerModel() throws SQLException
   {
      products = new ArrayList<Product>();
      offers = new ArrayList<Offer>();
      sales = new ArrayList<Sale>();
      reviews = new ArrayList<Review>();
      usernames = new ArrayList<String>();
      password = new ArrayList<String>();
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
      databaseAccountAccess = new AccountDatabaseHandler(queryHandler);
      loadUsernamesandPasswords();
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

   public ArrayList<Review> getReviews()
   {
      return reviews;
   }

   public ArrayList<String> getUsernames()
   {
      return usernames;
   }

   public ArrayList<String> getPasswords()
   {
      return password;
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

   public void loadUsernamesandPasswords()
   {
      try
      {
         usernames = databaseAccountAccess.loadUsernames();
         password = databaseAccountAccess.loadPasswords();
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
         e.printStackTrace();
      }
      support.firePropertyChange("OFFERADDED", null, offer);
   }

   public void addSale(Sale sale)
   {

      if (sale.getIsChangedValue() == false
            && MyDate.now().equals(sale.getStartDate()))
      {
         products.parallelStream()
               .filter(product -> product.getID() == sale.getProduct().getID())
               .findFirst().get().setPrice((sale.getPriceAfterSaleApplied()));
         sale.setIsChangedValue();
      }

      sale.setProduct(products.parallelStream()
            .filter(product -> product.getID() == sale.getProduct().getID())
            .findFirst().get());

      System.out.println(sale.getPrice());

      sales.add(sale);

      try
      {
         databaseSaleAccess.addSale(sale);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }

      support.firePropertyChange("SALEADDED", null, sale);

      if (sale.getIsChangedValue())
      {
         support.firePropertyChange("SALEAVAILABLE", null, sale);
      }
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

   // update methods
   public void updateProduct(Product product)
   {
      try
      {
         databaseProductAccess.updateProduct(product);
      }
      catch (SQLException e)
      {
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
         e.printStackTrace();
      }
      support.firePropertyChange("OFFERUPDATED", null, offer);
   }

   public void setSaleAvailable(Sale sale)
   {
      products.parallelStream()
            .filter(product -> product.getID() == sale.getID()).findFirst()
            .get().setPrice((sale.getPriceAfterSaleApplied()));
      sales.parallelStream()
            .filter(sampleSale -> sampleSale.getID() == sale.getID())
            .findFirst().get().setIsChangedValue();
      sale.setProduct(products.parallelStream()
            .filter(product -> product.getID() == sale.getID()).findFirst()
            .get());
      try
      {
         databaseSaleAccess.changedValue(sale);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      support.firePropertyChange("SALEAVAILABLE", null, sale);
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
         e.printStackTrace();
      }
      support.firePropertyChange("OFFERREMOVED", null, offer);
   }

   public void removeSale(Sale sale)
   {

      if (sale.getIsChangedValue())
      {
         products.stream()
               .filter(product -> product.getID() == sale.getProduct().getID())
               .findFirst().get().setPrice(sale.getInitialPrice());
      }
      else
      {
         products.stream()
               .filter(product -> product.getID() == sale.getProduct().getID())
               .findFirst().get().setPrice(sale.getPrice());
      }

      sale.setProduct(products.stream()
            .filter(product -> product.getID() == sale.getProduct().getID())
            .findFirst().get());

      sales.remove(sale);
      try
      {
         databaseSaleAccess.removeSale(sale);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      support.firePropertyChange("SALEREMOVED", null, sale);
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

   // clear methods
   public void clearProducts()
   {
      try
      {
         databaseProductAccess.clearProducts();
      }
      catch (SQLException e)
      {
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
         e.printStackTrace();
      }
   }

   public void clearReviews()
   {
      try
      {
         databaseReviewAccess.clearReviews();
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
   }

   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      support.addPropertyChangeListener(listener);
   }
}
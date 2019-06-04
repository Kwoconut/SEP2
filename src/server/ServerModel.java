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
import model.AvailableSale;
import model.MyDate;
import model.Offer;
import model.Product;
import model.Review;
import model.Sale;
import model.UpcomingSale;

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
   /**
    * ServerModel handles all server based methods of the system
    * @author Group 1
    * @version (Version 1.0, 06/04/2019)
    * @throws SQLException if an network problem occurs
    */
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
   // getters for ArrayLists
   /**
 * returns all products from the database
 */
public ArrayList<Product> getProducts()
   {
      loadProducts();
      return products;
   }
/**
* returns all offers stored locally
*/
   public ArrayList<Offer> getOffers()
   {
      return offers;
   }
   /**
 * returns all sales stored locally
 */
   public ArrayList<Sale> getSales()
   {
      return sales;
   }
   /**
 * returns all reviews stored locally
 */
   public ArrayList<Review> getReviews()
   {
      return reviews;
   }
   /**
 * returns all usernames stored locally
 */
   public ArrayList<String> getUsernames()
   {
      return usernames;
   }
   /**
    * returns all passwords stored locally
    */
   public ArrayList<String> getPasswords()
   {
      return password;
   }

   // loading methods
   /**
    * store locally all products from the database
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * store locally all offers from the database
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * store locally all sales from the database
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * store locally all reviews from the database
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * store locally all of administration usernames and passwords from the database
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * store a product locally and on database and updates the system through a fire property
    * @param product product to be stored
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * store an offer locally and on database and updates the system through a fire property
    * @param offer offer to be stored
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * store a sale locally and on database and updates the system through a fire property
    * @param sale sale to be stored
    * @throws SQLException if an network problem occurs
    */
   public void addSale(Sale sale)
   {

      if (sale.getState() instanceof UpcomingSale
            && MyDate.now().equals(sale.getStartDate()))
      {
         sale.setProduct(products.parallelStream()
               .filter(product -> product.getID() == sale.getProduct().getID())
               .findFirst().get());
         sale.setNextState();
      }

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

      if (sale.getState() instanceof AvailableSale)
      {
         support.firePropertyChange("SALEAVAILABLE", null, sale);
      }
   }
   /**
    * store a review locally and on database and updates the system through a fire property
    * @param review review to be stored
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * updates a product on database and updates the system through a fire property
    * @param product product to be updated
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * updates an offer on database and updates the system through a fire property
    * @param offer offer to be updated
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * Set a sale to available state
    * @param sale sale to be set to next state
    */
   public void setSaleAvailable(Sale sale)
   {
      for (Sale element : sales)
      {
         if (element.getID() == sale.getID())
         {
            element.setNextState();
            support.firePropertyChange("SALEAVAILABLE", null, element);
            break;
         }
      }

   }

   // remove methods
   /**
    * remove a product locally and on database and updates the system through a fire property
    * @param product product to be removed
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * remove an offer locally and on database and updates the system through a fire property
    * @param offer offer to be removed
    * @throws SQLException if an network problem occurs
    */
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
   /**
    * remove a sale locally and on database and updates the system through a fire property
    * @param sale sale to be removed
    * @throws SQLException if an network problem occurs
    */
   public void removeSale(Sale sale)
   {

      for (Sale element : sales)
      {
         if (element.getID() == sale.getID())
         {
            if (sale.getState() instanceof AvailableSale
                  && element.getState() instanceof AvailableSale)
            {
               element.setNextState();
            }
            try
            {
               databaseSaleAccess.removeSale(sale);
            }
            catch (SQLException e)
            {
               e.printStackTrace();
            }

            support.firePropertyChange("SALEREMOVED", null, element);
            sales.remove(element);
            break;
         }
      }
   }
   /**
    * remove locally a comment from a specific review, updates data from database and system through a fire property
    * @param review review to be removed
    * @throws SQLException if an network problem occurs
    */
   public void removeReviewComment(Review review)
   {
      reviews.stream().filter(sampleReview -> sampleReview.getID() == review.getID()).findFirst().get().removeMessage();
      review.removeMessage();
      try
      {
         databaseReviewAccess.updateReview(review);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("COMMENTREMOVED", null, review);
   }

   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      support.addPropertyChangeListener(listener);
   }
}
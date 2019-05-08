package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Offer;
import model.Product;

public interface StorePersistence
{

   public ArrayList<Product> loadProducts() throws SQLException;
   
   public ArrayList<Product> loadPlatedSheets() throws SQLException;
   
   public ArrayList<Product> loadMetalSheets() throws SQLException;
   
   public ArrayList<Product> loadClickSheet() throws SQLException;
   
   public ArrayList<Product> loadRainSystem() throws SQLException;
   
   public ArrayList<Product> loadMetalTile() throws SQLException;

   public ArrayList<Offer> loadOffers() throws SQLException;

   //public void saveProducts(ArrayList<Product> products) throws SQLException;

   //public void saveOffers(ArrayList<Offer> offers) throws SQLException;

   public void updateProduct(Product product) throws SQLException;

   public void updateOffer(Offer offer) throws SQLException;

   public void removeProduct(Product product) throws SQLException;

   public void removeOffer(Offer offer) throws SQLException;

   public void clearProducts() throws SQLException;

   public void clearOffers() throws SQLException;

}

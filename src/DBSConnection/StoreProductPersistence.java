package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Offer;
import model.Product;

public interface StoreProductPersistence
{

   ArrayList<Product> loadProducts() throws SQLException;
   
   ArrayList<Product> loadPlatedSheets() throws SQLException;
   
   ArrayList<Product> loadMetalSheets() throws SQLException;
   
   ArrayList<Product> loadClickSheet() throws SQLException;
   
   ArrayList<Product> loadRainSystem() throws SQLException;
   
   ArrayList<Product> loadMetalTile() throws SQLException;

   //public void saveProducts(ArrayList<Product> products) throws SQLException;

   void addProduct(Product product) throws SQLException;
   
   void updateProduct(Product product) throws SQLException;

   void removeProduct(Product product) throws SQLException;

   void clearProducts() throws SQLException;

}

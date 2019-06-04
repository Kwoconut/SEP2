package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;

public interface StoreProductPersistence
{

   ArrayList<Product> loadProducts() throws SQLException;

   void addProduct(Product product) throws SQLException;

   void updateProduct(Product product) throws SQLException;

   void removeProduct(Product product) throws SQLException;

}

package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;

public class ProductDatabaseHandler implements StoreProductPersistence
{

   private DBSQuery query;

   public ProductDatabaseHandler(DBSQuery query)
   {
      this.query = query;
   }

   @Override
   public ArrayList<Product> loadProducts() throws SQLException
   {
      return new ArrayList<Product>(query.map(
            "SELECT product_id, name, price, color, product_type, imageID FROM Product;",
            statement -> {
            }, resultSet -> {
               return new Product(resultSet.getInt("product_id"),
                     resultSet.getString("name"), resultSet.getInt("price"),
                     resultSet.getString("color"),
                     resultSet.getString("product_type"),
                     resultSet.getString("imageID"));
            }));
   }

   @Override
   public void addProduct(Product product) throws SQLException
   {
      query.executeUpdate(
            "INSERT INTO Product (product_id, name, price, color, product_type, imageID) VALUES (?, ?, ?, ?, ?, ?);",
            statement -> {
               statement.setInt(1, product.getID());
               statement.setString(2, product.getName());
               statement.setDouble(3, product.getPrice());
               statement.setString(4, product.getColour());
               statement.setString(5, product.getType());
               statement.setString(6, product.getImageID());
            });
   }

   @Override
   public void updateProduct(Product product) throws SQLException
   {
      query.executeUpdate(
            "UPDATE Product SET name = ?, price = ?, color = ?, product_type = ?, imageID = ?, WHERE product_id = ?;",
            statement -> {
               statement.setString(1, product.getName());
               statement.setDouble(2, product.getPrice());
               statement.setString(3, product.getColour());
               statement.setString(4, product.getType());
               statement.setString(5, product.getImageID());
               statement.setInt(6, product.getID());
            });
   }

   @Override
   public void removeProduct(Product product) throws SQLException
   {
      query.executeUpdate("DELETE FROM Product WHERE product_id = ?;",
            statement -> {
               statement.setInt(1, product.getID());
            });
   }
}

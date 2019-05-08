package DBSConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;

public class ProductDatabaseHandler implements StoreProductPersistence
{

   private Connection connection;

   public ProductDatabaseHandler() throws SQLException
   {
      DriverManager.registerDriver(new org.postgresql.Driver());
      connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/postgres", "postgres",
            "password");
      PreparedStatement set = connection.prepareStatement("SET SCHEMA 'sep2'");
      set.execute();
   }

   public void closeConnection() throws SQLException
   {
      connection.close();
   }

   @Override
   public ArrayList<Product> loadProducts() throws SQLException
   {
      ArrayList<Product> products = new ArrayList<Product>();
      PreparedStatement selectStatement = connection.prepareStatement(
            "SELECT product_id, name, price, color, product_type FROM Product");
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next())
      {
         Product product = new Product(rs.getInt("product_id"),
               rs.getString("name"), rs.getInt("price"), rs.getString("color"),
               rs.getString("product_type"));
         products.add(product);
      }
      return products;
   }

   @Override
   public void addProduct(Product product) throws SQLException
   {
      PreparedStatement insertStatement = connection.prepareStatement(
            "INSERT INTO Product (product_id, name, price, color, product_type) VALUES (?, ?, ?, ?, ?)");
      insertStatement.setInt(1, product.getID());
      insertStatement.setString(2, product.getName());
      insertStatement.setInt(3, product.getPrice());
      insertStatement.setString(4, product.getColour());
      insertStatement.setString(5, product.getType());
      insertStatement.executeUpdate();
   }

   @Override
   public void updateProduct(Product product) throws SQLException
   {
      PreparedStatement updateStatement = connection.prepareStatement(
            "UPDATE Product SET name = '?', price = '?', color = '?', product_type = '?' WHERE prodct_id = '?'");
      updateStatement.setString(1, product.getName());
      updateStatement.setInt(2, product.getPrice());
      updateStatement.setString(3, product.getColour());
      updateStatement.setString(4, product.getType());
      updateStatement.setInt(5, product.getID());
      updateStatement.executeUpdate();
   }

   @Override
   public void removeProduct(Product product) throws SQLException
   {
      PreparedStatement deleteStatement = connection
            .prepareStatement("DELETE FROM Product WHERE name = '?')");
      deleteStatement.setString(1, product.getName());
      deleteStatement.executeUpdate();
   }

   @Override
   public void clearProducts() throws SQLException
   {
      PreparedStatement truncateStatement = connection
            .prepareStatement("TRUNCATE TABLE Product CASCADE");
      truncateStatement.executeUpdate();
   }

}

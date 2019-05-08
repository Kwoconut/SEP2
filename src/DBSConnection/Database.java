package DBSConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Offer;
import model.Product;

public class Database implements StorePersistence
{

   private Connection connection;

   public Database() throws SQLException
   {
      DriverManager.registerDriver(new org.postgresql.Driver());
      connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/postgres", "postgres",
            "password");

   }

   public void closeConnection() throws SQLException
   {
      connection.close();
   }

   @Override
   public ArrayList<Product> loadProducts() throws SQLException
   {
      ArrayList<Product> products = new ArrayList<Product>();
      PreparedStatement selectStatement = connection
            .prepareStatement("SELECT name, price, color, product_type FROM Product");
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next())
      {
         Product product = new Product(rs.getString("name"), rs.getInt("price"),
               rs.getString("color"), rs.getString("product_type"));
         products.add(product);
      }
      return products;
   }
   
   @Override
   public ArrayList<Product> loadPlatedSheets() throws SQLException
   {
      ArrayList<Product> products = new ArrayList<Product>();
      PreparedStatement selectStatement = connection
            .prepareStatement("SELECT name, price, color, product_type WHERE product_type = 'Plated Sheet' FROM Product");
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next())
      {
         Product product = new Product(rs.getString("name"), rs.getInt("price"),
               rs.getString("color"), rs.getString("product_type"));
         products.add(product);
      }
      return products;
   }

   @Override
   public ArrayList<Product> loadMetalSheets() throws SQLException
   {
      ArrayList<Product> products = new ArrayList<Product>();
      PreparedStatement selectStatement = connection
            .prepareStatement("SELECT name, price, color, product_type WHERE product_type = 'Metal Sheet' FROM Product");
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next())
      {
         Product product = new Product(rs.getString("name"), rs.getInt("price"),
               rs.getString("color"), rs.getString("product_type"));
         products.add(product);
      }
      return products;
   }

   @Override
   public ArrayList<Product> loadClickSheet() throws SQLException
   {
      ArrayList<Product> products = new ArrayList<Product>();
      PreparedStatement selectStatement = connection
            .prepareStatement("SELECT name, price, color, product_type WHERE product_type = 'Click Sheet' FROM Product");
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next())
      {
         Product product = new Product(rs.getString("name"), rs.getInt("price"),
               rs.getString("color"), rs.getString("product_type"));
         products.add(product);
      }
      return products;
   }

   @Override
   public ArrayList<Product> loadRainSystem() throws SQLException
   {
      ArrayList<Product> products = new ArrayList<Product>();
      PreparedStatement selectStatement = connection
            .prepareStatement("SELECT name, price, color, product_type WHERE product_type = 'Rain System' FROM Product");
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next())
      {
         Product product = new Product(rs.getString("name"), rs.getInt("price"),
               rs.getString("color"), rs.getString("product_type"));
         products.add(product);
      }
      return products;
   }

   @Override
   public ArrayList<Product> loadMetalTile() throws SQLException
   {
      ArrayList<Product> products = new ArrayList<Product>();
      PreparedStatement selectStatement = connection
            .prepareStatement("SELECT name, price, color, product_type WHERE product_type = 'Metal Tile' FROM Product");
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next())
      {
         Product product = new Product(rs.getString("name"), rs.getInt("price"),
               rs.getString("color"), rs.getString("product_type"));
         products.add(product);
      }
      return products;
   }
   

   @Override
   public ArrayList<Offer> loadOffers() throws SQLException
   {
      ArrayList<Offer> offers = new ArrayList<Offer>();
      PreparedStatement selectStatement = connection
            .prepareStatement("SELECT name, phone, email, message FROM Offer");
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next())
      {
         Offer offer = new Offer(rs.getString("name"), rs.getString("phone"),
               rs.getString("email"), rs.getString("message"));
         offers.add(offer);
      }
      return offers;
   }

//   @Override
//   public void saveProducts(ArrayList<Product> products) throws SQLException
//   {
//      clearProducts();
//      for(Product product: products)
//      {
//         PreparedStatement insertStatement = connection
//               .prepareStatement("INSERT INTO Product (name, price, color, product_type) VALUES (?, ?, ?, ?)");
//         insertStatement.setString(1, product.getName());
//         insertStatement.setInt(2, product.getPrice());
//         insertStatement.setString(3, product.getColour());
//         insertStatement.setString(4, product.getType());
//         insertStatement.executeUpdate();             
//      }
//   }

//   @Override
//   public void saveOffers(ArrayList<Offer> offers) throws SQLException
//   {
//    clearOffers();
//    for(Offer offer: offers)
//    {
//       PreparedStatement insertStatement = connection
//             .prepareStatement("INSERT INTO Offer (name, phone, email, message) VALUES (?, ?, ?, ?)");
//       insertStatement.setString(1, offer.getName());
//       insertStatement.setString(2, offer.getPhoneNo());
//       insertStatement.setString(3, offer.getEmail());
//       insertStatement.setString(4, offer.getMessage());
//       insertStatement.executeUpdate();             
//    }
//   }

   
   // use these 2 methods if you update field / fields or you create a new object 
   @Override
   public void updateProduct(Product product) throws SQLException
   {
      PreparedStatement insertStatement = connection
            .prepareStatement(
                  "INSERT INTO Product (name, price, color, product_type) VALUES (?, ?, ?, ?)");
      insertStatement.setString(1, product.getName());
      insertStatement.setInt(2, product.getPrice());
      insertStatement.setString(3, product.getColour());
      insertStatement.setString(4, product.getType());
      insertStatement.executeUpdate();
   }

   @Override
   public void updateOffer(Offer offer) throws SQLException
   {
      PreparedStatement insertStatement = connection
            .prepareStatement(
                  "INSERT INTO Offer (name, phone, email, message) VALUES (?, ?, ?, ?)");
      insertStatement.setString(1, offer.getName());
      insertStatement.setString(2, offer.getPhoneNo());
      insertStatement.setString(3, offer.getEmail());
      insertStatement.setString(4, offer.getMessage());
      insertStatement.executeUpdate();
   }

   @Override
   public void removeProduct(Product product) throws SQLException
   {
      PreparedStatement deleteStatement = connection
            .prepareStatement(
                  "DELETE FROM Product WHERE name = '?')");
      deleteStatement.setString(1, product.getName());
      deleteStatement.executeUpdate();
   }

   @Override
   public void removeOffer(Offer offer) throws SQLException
   {
      PreparedStatement deleteStatement = connection
            .prepareStatement(
                  "DELETE FROM Offer WHERE name = '?'");
      deleteStatement.setString(1, offer.getName());
      deleteStatement.executeUpdate();

   }
   
   @Override
   public void clearProducts() throws SQLException
   {      
      PreparedStatement truncateStatement = connection.prepareStatement("TRUNCATE TABLE Product CASCADE");
      truncateStatement.executeUpdate();
   }

   @Override
   public void clearOffers() throws SQLException
   {
      PreparedStatement truncateStatement = connection.prepareStatement("TRUNCATE TABLE Offer CASCADE");
      truncateStatement.executeUpdate();
   }


}

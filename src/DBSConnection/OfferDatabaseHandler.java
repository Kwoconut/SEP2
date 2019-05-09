package DBSConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Offer;

public class OfferDatabaseHandler implements StoreOfferPersistence
{

   private Connection connection;

   public OfferDatabaseHandler() throws SQLException
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
   public ArrayList<Offer> loadOffers() throws SQLException
   {
      ArrayList<Offer> offers = new ArrayList<Offer>();
      PreparedStatement selectStatement = connection.prepareStatement(
            "SELECT offer_id, name, phone, email, message FROM Offer");
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next())
      {
         Offer offer = new Offer(rs.getInt("offer_id"), rs.getString("name"),
               rs.getString("phone"), rs.getString("email"),
               rs.getString("message"));
         offers.add(offer);
      }
      return offers;
   }
   
   @Override
   public void addOffer(Offer offer) throws SQLException
   {
      PreparedStatement insertStatement = connection.prepareStatement(
            "INSERT INTO Offer (offer_id, name, phone, email, message) VALUES (?, ?, ?, ?, ?)");
      insertStatement.setInt(1, offer.getID());
      insertStatement.setString(2, offer.getName());
      insertStatement.setString(3, offer.getPhoneNo());
      insertStatement.setString(4, offer.getEmail());
      insertStatement.setString(5, offer.getMessage());
      insertStatement.executeUpdate();
   }

   @Override
   public void updateOffer(Offer offer) throws SQLException
   {
      PreparedStatement updateStatement = connection.prepareStatement(
            "UPDATE Offer SET name = ?, phone = ?, email = ?, message = ? WHERE offer_id = ?");
      updateStatement.setString(1, offer.getName());
      updateStatement.setString(2, offer.getPhoneNo());
      updateStatement.setString(3, offer.getEmail());
      updateStatement.setString(4, offer.getMessage());
      updateStatement.setInt(5, offer.getID());
      updateStatement.executeUpdate();
   }

   @Override
   public void removeOffer(Offer offer) throws SQLException
   {
      PreparedStatement deleteStatement = connection
            .prepareStatement("DELETE FROM Offer WHERE offer_id = ?");
      deleteStatement.setInt(1, offer.getID());
      deleteStatement.executeUpdate();
   }

   @Override
   public void clearOffers() throws SQLException
   {
      PreparedStatement truncateStatement = connection
            .prepareStatement("TRUNCATE TABLE Offer CASCADE");
      truncateStatement.executeUpdate();
   }
}

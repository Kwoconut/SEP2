package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Offer;

public class OfferDatabaseHandler implements StoreOfferPersistence
{

   private DBSQuery query;

   public OfferDatabaseHandler(DBSQuery query)
   {
      this.query = query;
   }

   @Override
   public ArrayList<Offer> loadOffers() throws SQLException
   {
      return new ArrayList<Offer>(query.map(
            "SELECT offer_id, name, phone, email, message FROM Offer;",
            statement -> {
            }, resultSet -> {
               return new Offer(resultSet.getInt("offer_id"),
                     resultSet.getString("name"), resultSet.getString("phone"),
                     resultSet.getString("email"),
                     resultSet.getString("message"));
            }));
   }

   @Override
   public void addOffer(Offer offer) throws SQLException
   {
      query.executeUpdate(
            "INSERT INTO Offer(offer_id, name, phone, email, message) VALUES (?, ?, ?, ?, ?);",
            statement -> {
               statement.setInt(1, offer.getID());
               statement.setString(2, offer.getName());
               statement.setString(3, offer.getPhoneNo());
               statement.setString(4, offer.getEmail());
               statement.setString(5, offer.getMessage());
            });
   }

   @Override
   public void updateOffer(Offer offer) throws SQLException
   {
      query.executeUpdate(
            "UPDATE Offer SET name = ?, phone = ?, email = ?, message = ? WHERE offer_id = ?;",
            statement -> {
               statement.setString(1, offer.getName());
               statement.setString(2, offer.getPhoneNo());
               statement.setString(3, offer.getEmail());
               statement.setString(4, offer.getMessage());
               statement.setInt(5, offer.getID());
            });
   }

   @Override
   public void removeOffer(Offer offer) throws SQLException
   {
      query.executeUpdate("DELETE FROM Offer WHERE offer_id = ?;",
            statement -> {
               statement.setInt(1, offer.getID());
            });
   }
}

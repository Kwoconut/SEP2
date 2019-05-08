package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Offer;

public interface StoreOfferPersistence
{

   // void saveOffers(ArrayList<Offer> offers) throws SQLException;
   
   ArrayList<Offer> loadOffers() throws SQLException;
   
   void addOffer(Offer offer) throws SQLException;
   
   void removeOffer(Offer offer) throws SQLException;
   
   void updateOffer(Offer offer) throws SQLException;
   
   void clearOffers() throws SQLException;

}

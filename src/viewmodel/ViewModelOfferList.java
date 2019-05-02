package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Offer;
import model.StoreModel;

public class ViewModelOfferList implements PropertyChangeListener
{
   private ObservableList<ViewModelRequestOffer> offers;
   private StoreModel model;

   public ViewModelOfferList(StoreModel model)
   {
      this.model = model;
      this.model.addListener(this);
      this.offers = FXCollections.observableArrayList();
   }

   public ObservableList<ViewModelRequestOffer> getOffers()
   {
      return offers;
   }
   
   public void requestOffers() throws RemoteException
   {
      model.requestOffers();
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> executePropertyChange(evt));
   }
   
   @SuppressWarnings("unchecked")
   public void executePropertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("OFFERLIST"))
      {
         ArrayList<Offer> elements = new ArrayList<Offer>();
         elements = (ArrayList<Offer>) evt.getNewValue();

         for (Offer element : elements)
         {
            offers.add(new ViewModelRequestOffer(model, element));
         }
      }

   }

}

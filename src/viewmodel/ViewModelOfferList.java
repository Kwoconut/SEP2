package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Offer;
import model.SOfferModel;
import model.StoreModel;

public class ViewModelOfferList implements PropertyChangeListener
{
   private ObservableList<ViewModelManageOffer> offers;
   private ObjectProperty<ViewModelManageOffer> selected;
   private StringProperty error;
   private SOfferModel model;

   public ViewModelOfferList(SOfferModel model) throws RemoteException
   {
      this.model = model;
      error = new SimpleStringProperty("");
      selected = new SimpleObjectProperty<ViewModelManageOffer>();
      this.model.addListener(this);
      this.offers = FXCollections.observableArrayList();
      this.model.requestOffers();
   }

   public ObservableList<ViewModelManageOffer> getOffers()
   {
      return offers;
   }
   
   public StringProperty getErrorProperty()
   {
      return error;
   }
   
   public ObjectProperty<ViewModelManageOffer> getSelected()
   {
      return selected;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {
         try
         {
            executePropertyChange(evt);
         }
         catch (RemoteException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      });
   }

   @SuppressWarnings("unchecked")
   public void executePropertyChange(PropertyChangeEvent evt) throws RemoteException
   {
      if (evt.getPropertyName().equals("OFFERLIST"))
      {
         ArrayList<Offer> elements = new ArrayList<Offer>();
         elements = (ArrayList<Offer>) evt.getNewValue();

         for (Offer element : elements)
         {
            offers.add(new ViewModelManageOffer(model, element));
         }
      }
      else if (evt.getPropertyName().equals("NEWOFFER"))
      {
         Offer offer = (Offer) evt.getNewValue();
         offers.add(new ViewModelManageOffer(model, offer));
      }
      else if (evt.getPropertyName().equals("MINUSOFFER"))
      {
         System.out.println("----");
         Offer offer = (Offer) evt.getNewValue();
         offers.remove(new ViewModelManageOffer(model,offer));
      }

   }
   
   public void setSelected(ViewModelManageOffer newValue)
   {
       selected.set(newValue);  
   }
   

}

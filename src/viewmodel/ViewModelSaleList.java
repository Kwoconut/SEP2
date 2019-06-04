package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AvailableSale;
import model.SSalesModel;
import model.Sale;

public class ViewModelSaleList implements PropertyChangeListener
{
   private ObservableList<ViewModelSale> sales;
   private SSalesModel model;
   private StringProperty loginProperty ;

   public ViewModelSaleList(SSalesModel model) throws RemoteException
   {
      this.model = model;
      this.sales = FXCollections.observableArrayList();
      this.model.addListener(this);
      this.loginProperty= new SimpleStringProperty("");
   }

   public ObservableList<ViewModelSale> getSales()
   {
      return sales;
   }
   
   public StringProperty getLoginProperty() {
      return loginProperty;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> executePropertyChange(evt));
   }

   public void executePropertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("SALEAVAILABLE"))
      {
         Sale element = (Sale) evt.getNewValue();
         if (element.getState() instanceof AvailableSale)
         {
            sales.add(new ViewModelSale(model, element));
         }
      }
      else if (evt.getPropertyName().equals("MINUSSALE"))
      {
         sales.remove(new ViewModelSale(model, (Sale) evt.getNewValue()));
      }
      else if (evt.getPropertyName().equals("VALIDLOGIN"))
      {
         loginProperty.set((String) evt.getOldValue());
      }
   }
}

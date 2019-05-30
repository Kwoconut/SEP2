package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.SSalesModel;
import model.Sale;

public class ViewModelSaleList implements PropertyChangeListener
{
   private ObservableList<ViewModelSale> sales;
   private SSalesModel model;

   public ViewModelSaleList(SSalesModel model) throws RemoteException
   {
      this.model = model;
      this.sales = FXCollections.observableArrayList();
      this.model.addListener(this);
   }

   public ObservableList<ViewModelSale> getSales()
   {
      return sales;
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
         if (element.getIsChangedValue())
         {
            sales.add(new ViewModelSale(model, element));
         }
      }
      else if (evt.getPropertyName().equals("MINUSSALE"))
      {
         sales.remove(new ViewModelSale(model, (Sale) evt.getNewValue()));
      }
   }
}

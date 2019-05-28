package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MyDate;
import model.Product;
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
      else if (evt.getPropertyName().equals("SALEPRODUCTPRICEUPDATE"))
      {
         Sale sale = (Sale) evt.getNewValue();
         sales.stream().filter(
               sampleSale -> sampleSale.getIDProperty().get() == sale.getID())
               .findFirst().get().getProductProperty().get().getPriceProperty()
               .set(sale.getPriceAfterSaleApplied());
         sales.stream().filter(
               sampleSale -> sampleSale.getIDProperty().get() == sale.getID())
               .findFirst().get().getInitialPriceProperty()
               .set(sale.getPrice());
         
         System.out.println(sales.get(0).getInitialPriceProperty().get());
         System.out.println("----------");
      }
      else if (evt.getPropertyName().equals("MINUSSALE"))
      {
         sales.remove(new ViewModelSale(model, (Sale) evt.getNewValue()));
      }
      if (evt.getPropertyName().equals("SALEPRODUCTPRICEREVERT"))
      {
         Sale sale = (Sale) evt.getNewValue();
         sales.stream().filter(
               sampleSale -> sampleSale.getIDProperty().get() == sale.getID())
               .findFirst().get().getProductProperty().get().getPriceProperty()
               .set(sale.getPrice());
         sales.stream().filter(
               sampleSale -> sampleSale.getIDProperty().get() == sale.getID())
               .findFirst().get().getInitialPriceProperty()
               .set(sale.getPrice());
      }

   }
}

package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

   public ViewModelSaleList(SSalesModel model)
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

   @SuppressWarnings("unchecked")
   public void executePropertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("SALESLIST"))
      {
         ArrayList<Sale> elements = new ArrayList<Sale>();
         elements = (ArrayList<Sale>) evt.getNewValue();
         sales.clear();
         for (Sale element : elements)
         {
            if (element.getIsChangedValue() == true)
            {
               sales.add(new ViewModelSale(model, element));
               System.out.println("--------");
            }
         }
      }
      else if (evt.getPropertyName().equals("NEWSALE"))
      {
         Sale element = (Sale) evt.getNewValue();
         if (element.getIsChangedValue() == true)
         {
            sales.add(new ViewModelSale(model, element));
         }
      }
      else if (evt.getPropertyName().equals("MINUSSALE"))
      {
         Sale element = (Sale) evt.getNewValue();
         sales.remove(new ViewModelSale(model, element));
      }

   }
}

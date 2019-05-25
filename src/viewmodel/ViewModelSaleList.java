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
      Platform.runLater(() ->executePropertyChange(evt));
   }

   @SuppressWarnings("unchecked")
   public void executePropertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("SALESLIST"))
      {
         ArrayList<Sale> elements = new ArrayList<Sale>();
         elements = (ArrayList<Sale>) evt.getNewValue();
         for (Sale element : elements)
         {
            sales.add(new ViewModelSale(model, element));
         }
      }
      else if (evt.getPropertyName().equals("NEWSALE"))
      {
         Sale element = (Sale) evt.getNewValue();
         sales.add(new ViewModelSale(model, element));
      }
      else if (evt.getPropertyName().equals("MINUSSALE"))
      {
         Sale element = (Sale) evt.getNewValue();
         sales.remove(new ViewModelSale(model, element));
      }
      else if (evt.getPropertyName().equals("EDITSALE"))
      {
         Sale element = (Sale) evt.getNewValue();
         for(int i=0;i<sales.size();i++)
         {
        	 if(sales.get(i).getIDProperty().getValue() == element.getID())
        	 {
        		 sales.set(i, new ViewModelSale(model,element));
        		 break;
        	 }
         }

      }

   }
}

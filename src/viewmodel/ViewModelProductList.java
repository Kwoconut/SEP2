package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.SProductModel;

public class ViewModelProductList implements PropertyChangeListener
{
   private ObservableList<ViewModelProduct> products;
   private SProductModel model;

   public ViewModelProductList(SProductModel model)
   {
      this.model = model;
      this.model.addListener(this);
      this.products = FXCollections.observableArrayList();

   }

   public ObservableList<ViewModelProduct> getProducts()
   {
      return products;
   }

   public void getProductByID(int index1, int index2)
   {
      int x = 0;
      int y = 0;
      System.out.println(index1);
      System.out.println(index2);
      for (int i = 0; i < products.size(); i++)
      {
         if (index1 == x & index2 == y)
         {
            this.model.getAverage(products.get(i).getIdProperty().get());
            this.model.getReviewCommentsByProductID(
                  products.get(i).getIdProperty().get());
         }
         x++;
         if (x == 4)
         {
            y++;
            x = 0;
         }
      }
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      executePropertyChange(evt);
   }

   @SuppressWarnings("unchecked")
   public void executePropertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("DETAILS"))
      {
         products.clear();
         ArrayList<Product> product = new ArrayList<Product>();
         product = (ArrayList<Product>) evt.getNewValue();
         for (Product element : product)
         {
            products.add(new ViewModelProduct(model, element));
         }
      }

   }

}
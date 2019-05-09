package viewmodel;

import java.beans.PropertyChangeListener;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Offer;
import model.Product;
import model.StoreModel;

public class ViewModelProduct
{
   private IntegerProperty idProperty;
   private StringProperty nameProperty;
   private StringProperty typeProperty;
   private StringProperty colourProperty;
   private IntegerProperty priceProperty;
   private StoreModel model;
   
   public ViewModelProduct (StoreModel model)
   {
      this.model = model;
      idProperty = new SimpleIntegerProperty();
      nameProperty = new SimpleStringProperty("");
      typeProperty = new SimpleStringProperty("");
      colourProperty = new SimpleStringProperty("");
      priceProperty = new SimpleIntegerProperty();
      
   }
   public ViewModelProduct (StoreModel model, Product product)
   {
      this.model = model;
      idProperty = new SimpleIntegerProperty(product.getID());
      nameProperty = new SimpleStringProperty(product.getName());
      typeProperty = new SimpleStringProperty(product.getType());
      colourProperty = new SimpleStringProperty(product.getColour());
      priceProperty = new SimpleIntegerProperty(product.getPrice());
      
   }
   
   public IntegerProperty getIdProperty() {
      return idProperty;
   }
   
   public StringProperty getNameProperty() {
      return nameProperty;
   }
   
   public StringProperty getTypeProperty() {
      return typeProperty;
   }
   
   public StringProperty getColourProperty() {
      return colourProperty;
   }
   
   public IntegerProperty getPriceProperty() {
      return priceProperty;
   }
   
   public boolean equals(Object obj)
   {
      if (!(obj instanceof ViewModelProduct))
      {
         return false;
      }
      ViewModelProduct other = (ViewModelProduct) obj;
      return other.getNameProperty().get().equals(nameProperty.get())
            && other.getIdProperty().get()==idProperty.get()
            && other.getColourProperty().get().equals(colourProperty.get())
            && other.getTypeProperty().get().equals(typeProperty.get())
            && other.getPriceProperty().get()==priceProperty.get();
   }
}

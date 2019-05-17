package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Product;
import model.SProductModel;

public class ViewModelProduct
{
   private IntegerProperty idProperty;
   private StringProperty nameProperty;
   private StringProperty typeProperty;
   private StringProperty colourProperty;
   private IntegerProperty priceProperty;
   private SProductModel model;
   
   public ViewModelProduct (SProductModel model)
   {
      this.model = model;
      idProperty = new SimpleIntegerProperty();
      nameProperty = new SimpleStringProperty("");
      typeProperty = new SimpleStringProperty("");
      colourProperty = new SimpleStringProperty("");
      priceProperty = new SimpleIntegerProperty();
      
   }
   public ViewModelProduct (SProductModel model, Product product)
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
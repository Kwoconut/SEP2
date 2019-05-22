package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.MyDate;
import model.Product;
import model.SSalesModel;
import model.Sale;

public class ViewModelSale{

private ObjectProperty<MyDate> startDateProperty;
private ObjectProperty<MyDate> endDateProperty;
private ObjectProperty<ViewModelProduct> productProperty;
private IntegerProperty amountProperty;
private SSalesModel model;

public ViewModelSale (SSalesModel model , ViewModelProduct product)
{
   this.model = model;
   startDateProperty = new SimpleObjectProperty<MyDate>();
   endDateProperty = new SimpleObjectProperty<MyDate>();
   amountProperty = new SimpleIntegerProperty();
   productProperty = new SimpleObjectProperty<ViewModelProduct>(); 
   
}
public ViewModelSale(SSalesModel model, Sale sale)
{
   this.model = model;
   startDateProperty = new SimpleObjectProperty<MyDate>(sale.getStartDate());
   endDateProperty = new SimpleObjectProperty<MyDate>(sale.getEndDate());
   productProperty = new SimpleObjectProperty<ViewModelProduct>(new ViewModelProduct(sale.getProduct()));

}


public ObjectProperty<MyDate> getStartDateProperty() {
   return startDateProperty;
}

public ObjectProperty<MyDate> getEndDateProperty() {
   return endDateProperty;
}

public ObjectProperty<ViewModelProduct> getProductProperty(){
   return productProperty;
}

public IntegerProperty getAmountProperty() {
   return amountProperty;
}

public void addSale() {
   model.addSale(startDateProperty.get(), endDateProperty.get(), productProperty.get(), amountProperty.get());
}

public boolean equals(Object obj)
{
   if (!(obj instanceof ViewModelSale))
   {
      return false;
   }
   ViewModelSale other = (ViewModelSale) obj;
   return other.getStartDateProperty().get().equals(startDateProperty.get())
         && other.getEndDateProperty().get().equals(endDateProperty.get())
         && other.getProductProperty().get().equals(productProperty.get())
         && other.getAmountProperty().get()==amountProperty.get();
}
}

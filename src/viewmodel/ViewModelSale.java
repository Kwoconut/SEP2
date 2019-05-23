package viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.MyDate;
import model.Product;
import model.SSalesModel;
import model.Sale;

public class ViewModelSale
{

   private ObjectProperty<MyDate> startDateProperty;
   private ObjectProperty<MyDate> endDateProperty;
   private ObjectProperty<ViewModelProduct> productProperty;
   private IntegerProperty amountProperty;
   private IntegerProperty IDProperty;
   private BooleanProperty isAvailable;
   private DoubleProperty initialPrice;
   private SSalesModel model;

   public ViewModelSale(SSalesModel model, ViewModelProduct product)
   {
      this.model = model;
      startDateProperty = new SimpleObjectProperty<MyDate>();
      endDateProperty = new SimpleObjectProperty<MyDate>();
      amountProperty = new SimpleIntegerProperty();
      IDProperty = new SimpleIntegerProperty();
      productProperty = new SimpleObjectProperty<ViewModelProduct>();
      isAvailable = new SimpleBooleanProperty();
      initialPrice = new SimpleDoubleProperty();

   }

   public ViewModelSale(SSalesModel model, Sale sale)
   {
      this.model = model;
      startDateProperty = new SimpleObjectProperty<MyDate>(sale.getStartDate());
      endDateProperty = new SimpleObjectProperty<MyDate>(sale.getEndDate());
      productProperty = new SimpleObjectProperty<ViewModelProduct>(
            new ViewModelProduct(sale.getProduct()));
      amountProperty = new SimpleIntegerProperty(sale.getAmount());
      IDProperty = new SimpleIntegerProperty(sale.getID());
      isAvailable = new SimpleBooleanProperty(sale.getIsChangedValue());
      initialPrice = new SimpleDoubleProperty(sale.getInitialPrice());

   }

   public DoubleProperty getInitialPriceProperty()
   {
      return initialPrice;
   }

   public ObjectProperty<MyDate> getStartDateProperty()
   {
      return startDateProperty;
   }

   public StringProperty getStartDateStringProperty()
   {
      return new SimpleStringProperty(startDateProperty.get().toString());
   }

   public ObjectProperty<MyDate> getEndDateProperty()
   {
      return endDateProperty;
   }

   public StringProperty getEndDateStringProperty()
   {
      return new SimpleStringProperty(endDateProperty.get().toString());
   }

   public IntegerProperty getIDProperty()
   {
      return IDProperty;
   }

   public ObjectProperty<ViewModelProduct> getProductProperty()
   {
      return productProperty;
   }

   public IntegerProperty getAmountProperty()
   {
      return amountProperty;
   }

   public BooleanProperty getBooleanProperty()
   {
      return isAvailable;
   }

   public void addSale()
   {
      model.addSale(startDateProperty.get(), endDateProperty.get(),
            productProperty.get(), amountProperty.get());
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
            && other.getAmountProperty().get() == amountProperty.get()
            && other.getBooleanProperty().get() == isAvailable.get()
            && other.getIDProperty().get() == IDProperty.get();
   }
}

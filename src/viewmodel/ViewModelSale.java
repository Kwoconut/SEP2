package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

public class ViewModelSale implements PropertyChangeListener
{

   private ObjectProperty<MyDate> startDateProperty;
   private ObjectProperty<MyDate> endDateProperty;
   private ObjectProperty<ViewModelProduct> productProperty;
   private IntegerProperty amountProperty;
   private IntegerProperty IDProperty;
   private BooleanProperty isAvailable;
   private DoubleProperty initialPrice;
   private SSalesModel model;
   private StringProperty errorProperty1;
   private StringProperty errorProperty2;
   private DoubleProperty priceAfterReduction;

   public ViewModelSale(SSalesModel model)
   {
      this.model = model;
      startDateProperty = new SimpleObjectProperty<MyDate>();
      endDateProperty = new SimpleObjectProperty<MyDate>();
      amountProperty = new SimpleIntegerProperty();
      IDProperty = new SimpleIntegerProperty();
      productProperty = new SimpleObjectProperty<ViewModelProduct>();
      isAvailable = new SimpleBooleanProperty();
      initialPrice = new SimpleDoubleProperty();
      errorProperty1 = new SimpleStringProperty("");
      errorProperty2 = new SimpleStringProperty("");
      priceAfterReduction = new SimpleDoubleProperty();
      this.model.addListener(this);

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
      errorProperty1 = new SimpleStringProperty("");
      errorProperty2 = new SimpleStringProperty("");
      priceAfterReduction = new SimpleDoubleProperty();
      this.model.addListener(this);
   }

   public void setSelectedProduct(ViewModelProduct product)
   {
      productProperty = new SimpleObjectProperty<ViewModelProduct>(product);
   }

   public DoubleProperty getInitialPriceProperty()
   {
      return initialPrice;
   }

   public DoubleProperty getPriceAfterReductionProperty()
   {
      return new SimpleDoubleProperty(
            (double) productProperty.get().getPriceProperty().get()
                  - (amountProperty.get()
                        * productProperty.get().getPriceProperty().get()
                        / 100));
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

   public StringProperty getError1()
   {
      return errorProperty1;
   }

   public StringProperty getError2()
   {
      return errorProperty2;
   }

   public void addSale()
   {
      model.addSale(startDateProperty.get(), endDateProperty.get(),
            productProperty.get(), amountProperty.get());
   }

   public void editSale()
   {

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

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("INVALIDDATE"))
      {
         errorProperty1.set((String) evt.getNewValue());
      }
      else if (evt.getPropertyName().equals("VALIDDATE"))
      {
         errorProperty1.set("");
      }

   }
}

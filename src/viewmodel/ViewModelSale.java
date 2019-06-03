package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
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
import model.AvailableSale;
import model.MyDate;
import model.Product;
import model.SSalesModel;
import model.Sale;
import model.SaleState;
import model.UpcomingSale;

public class ViewModelSale implements PropertyChangeListener
{

   private ObjectProperty<MyDate> startDateProperty;
   private ObjectProperty<MyDate> endDateProperty;
   private IntegerProperty productIDProperty;
   private StringProperty productNameProperty;
   private StringProperty productTypeProperty;
   private StringProperty productColorProperty;
   private DoubleProperty productPriceProperty;
   private StringProperty productImageProperty;
   private IntegerProperty saleIDProperty;
   private IntegerProperty saleAmountProperty;
   private ObjectProperty<SaleState> saleStateProperty;
   private DoubleProperty initialPriceProperty;
   private DoubleProperty priceAfterReductionProperty;
   private StringProperty errorProperty1;
   private SSalesModel model;

   public ViewModelSale(SSalesModel model)
   {
      this.model = model;
      this.startDateProperty = new SimpleObjectProperty<MyDate>();
      this.endDateProperty = new SimpleObjectProperty<MyDate>();
      this.productIDProperty = new SimpleIntegerProperty();
      this.productNameProperty = new SimpleStringProperty();
      this.productTypeProperty = new SimpleStringProperty();
      this.productColorProperty = new SimpleStringProperty();
      this.productPriceProperty = new SimpleDoubleProperty();
      this.productImageProperty = new SimpleStringProperty();
      this.saleIDProperty = new SimpleIntegerProperty();
      this.saleAmountProperty = new SimpleIntegerProperty();
      this.saleStateProperty = new SimpleObjectProperty<SaleState>();
      this.initialPriceProperty = new SimpleDoubleProperty();
      this.priceAfterReductionProperty = new SimpleDoubleProperty();
      this.errorProperty1 = new SimpleStringProperty("");
      this.model.addListener(this);
   }

   public ViewModelSale(SSalesModel model, Sale sale)
   {
      this.model = model;
      this.startDateProperty = new SimpleObjectProperty<MyDate>(
            sale.getStartDate());
      this.endDateProperty = new SimpleObjectProperty<MyDate>(
            sale.getEndDate());
      this.productIDProperty = new SimpleIntegerProperty(
            sale.getProduct().getID());
      this.productNameProperty = new SimpleStringProperty(
            sale.getProduct().getName());
      this.productTypeProperty = new SimpleStringProperty(
            sale.getProduct().getType());
      this.productColorProperty = new SimpleStringProperty(
            sale.getProduct().getColour());
      this.productPriceProperty = new SimpleDoubleProperty(
            sale.getProduct().getPrice());
      this.productImageProperty = new SimpleStringProperty(
            sale.getProduct().getImageID());
      this.saleIDProperty = new SimpleIntegerProperty(sale.getID());
      this.saleAmountProperty = new SimpleIntegerProperty(sale.getAmount());
      this.saleStateProperty = new SimpleObjectProperty<SaleState>(
            sale.getState());
      if (saleStateProperty.get() instanceof UpcomingSale)
      {
         initialPriceProperty = new SimpleDoubleProperty(sale.getPrice());
         priceAfterReductionProperty = new SimpleDoubleProperty(
               ((UpcomingSale) sale.getState()).getPriceAfterSaleApplies(sale));
      }
      else if (saleStateProperty.get() instanceof AvailableSale)
      {
         initialPriceProperty = new SimpleDoubleProperty(
               ((AvailableSale) sale.getState()).getInitialPrice(sale));
         priceAfterReductionProperty = new SimpleDoubleProperty(
               sale.getPrice());
      }
      this.errorProperty1 = new SimpleStringProperty("");
      this.model.addListener(this);
   }

   public void setSelectedProduct(ViewModelProduct product)
   {
      productIDProperty.set(product.getIdProperty().get());
      productNameProperty.set(product.getNameProperty().get());
      productTypeProperty.set(product.getTypeProperty().get());
      productColorProperty.set(product.getColourProperty().get());
      productPriceProperty.set(product.getPriceProperty().get());
      productImageProperty.set(product.getImageProperty().get());
   }

   public ObjectProperty<MyDate> getStartDateProperty()
   {
      return startDateProperty;
   }

   public ObjectProperty<MyDate> getEndDateProperty()
   {
      return endDateProperty;
   }

   public IntegerProperty getProductIDProperty()
   {
      return productIDProperty;
   }

   public StringProperty getProductNameProperty()
   {
      return productNameProperty;
   }

   public StringProperty getProductTypeProperty()
   {
      return productTypeProperty;
   }

   public StringProperty getProductColorProperty()
   {
      return productColorProperty;
   }

   public DoubleProperty getProductPriceProperty()
   {
      return productPriceProperty;
   }

   public StringProperty getProductImageProperty()
   {
      return productImageProperty;
   }

   public IntegerProperty getSaleIDProperty()
   {
      return saleIDProperty;
   }

   public IntegerProperty getAmountProperty()
   {
      return saleAmountProperty;
   }

   public ObjectProperty<SaleState> getStateProperty()
   {
      return saleStateProperty;
   }

   public DoubleProperty getInitialPriceProperty()
   {
      return initialPriceProperty;
   }

   public DoubleProperty getPriceAfterReductionProperty()
   {
      return priceAfterReductionProperty;
   }

   public StringProperty getError1()
   {
      return errorProperty1;
   }

   public void updatePriceAfterReduction()
   {

      priceAfterReductionProperty.set(Sale.getReducedPrice(
            productPriceProperty.get(), saleAmountProperty.get()));
   }

   public void addSale() throws RemoteException
   {
      Product product = new Product(productIDProperty.get(),
            productNameProperty.get(), productPriceProperty.get(),
            productColorProperty.get(), productTypeProperty.get(),
            productImageProperty.get());

      model.addSale(startDateProperty.get(), endDateProperty.get(), product,
            saleAmountProperty.get());
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
            && other.getProductIDProperty().get() == productIDProperty.get()
            && other.getAmountProperty().get() == saleAmountProperty.get()
            && other.getSaleIDProperty().get() == saleIDProperty.get();

   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getPropertyName().equals("INVALIDSALE"))
      {
         errorProperty1.set("Invalid dates");
      }
      else if (evt.getPropertyName().equals("VALIDSALE"))
      {
         errorProperty1.set("");
      }

   }
}

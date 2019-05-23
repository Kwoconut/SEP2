package view;

import java.io.IOException;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.MyDate;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelManageSalesList;
import viewmodel.ViewModelProduct;
import viewmodel.ViewModelSale;

public class ViewManageSalesList implements View
{

   @FXML
   private TableView<ViewModelProduct> productTable;
   @FXML
   private TableColumn<ViewModelProduct, Number> productID;
   @FXML
   private TableColumn<ViewModelProduct, String> productName;
   @FXML
   private TableColumn<ViewModelProduct, String> productType;
   @FXML
   private TableColumn<ViewModelProduct, String> productColor;
   @FXML
   private TableColumn<ViewModelProduct, Number> productPrice;
   @FXML
   private TextField productTextField;

   @FXML
   private TableView<ViewModelSale> saleTable;
   @FXML
   private TableColumn<ViewModelSale, String> saleProduct;
   @FXML
   private TableColumn<ViewModelSale, String> startDate;
   @FXML
   private TableColumn<ViewModelSale, String> endDate;
   @FXML
   private TableColumn<ViewModelSale, Number> priceReduction;
   @FXML
   private TableColumn<ViewModelSale, Number> salePrice;
   @FXML
   private TextField saleTextField;

   private String title;
   private Scene scene;
   private MainView view;
   private ViewModelManageSalesList viewModel;

   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      this.viewModel = viewModel.getViewModelManageSalesList();
      this.view = view;
      this.scene = scene;
      this.title = title;

      ObservableList<ViewModelProduct> productData = FXCollections
            .observableArrayList();
      productData = this.viewModel.getProducts();

      productID.setCellValueFactory(
            cellData -> cellData.getValue().getIdProperty());
      productName.setCellValueFactory(
            cellData -> cellData.getValue().getNameProperty());
      productType.setCellValueFactory(
            cellData -> cellData.getValue().getTypeProperty());
      productColor.setCellValueFactory(
            cellData -> cellData.getValue().getColourProperty());
      productPrice.setCellValueFactory(
            cellData -> cellData.getValue().getPriceProperty());

      FilteredList<ViewModelProduct> filteredProductData = new FilteredList<ViewModelProduct>(
            productData, e -> true);

      productTextField.setOnKeyReleased(e -> {
         productTextField.textProperty()
               .addListener((observableValue, oldValue, newValue) -> {
                  filteredProductData.setPredicate(
                        (Predicate<? super ViewModelProduct>) product -> {
                           if (newValue == null || newValue.isEmpty())
                           {
                              return true;
                           }
                           String lowerCaseFilter = newValue.toLowerCase();
                           if (Integer.toString(product.getIdProperty().get())
                                 .contains(newValue))
                           {
                              return true;
                           }
                           else if (product.getNameProperty().getValue()
                                 .toLowerCase().contains(lowerCaseFilter))
                           {
                              return true;
                           }
                           return false;
                        });
               });
         SortedList<ViewModelProduct> sortedProductData = new SortedList<ViewModelProduct>(
               filteredProductData);
         sortedProductData.comparatorProperty()
               .bind(productTable.comparatorProperty());
      });

      productTable.setItems(filteredProductData);

      saleProduct.setCellValueFactory(cellData -> cellData.getValue()
            .getProductProperty().get().getNameProperty());
      startDate.setCellValueFactory(
            cellData -> cellData.getValue().getStartDateStringProperty());
      endDate.setCellValueFactory(
            cellData -> cellData.getValue().getEndDateStringProperty());
      priceReduction.setCellValueFactory(
            cellData -> cellData.getValue().getAmountProperty());
      salePrice.setCellValueFactory(cellData -> cellData.getValue()
            .getProductProperty().get().getPriceProperty());

      ObservableList<ViewModelSale> sales = FXCollections.observableArrayList();
      sales = this.viewModel.getSales();

      FilteredList<ViewModelSale> filteredSaleData = new FilteredList<ViewModelSale>(
            sales, e -> true);

      saleTextField.setOnKeyReleased(e -> {
         saleTextField.textProperty()
               .addListener((observableValue, oldValue, newValue) -> {
                  filteredSaleData.setPredicate(
                        (Predicate<? super ViewModelSale>) sale -> {
                           if (newValue == null || newValue.isEmpty())
                           {
                              return true;
                           }
                           String lowerCaseFilter = newValue.toLowerCase();
                           if (sale.getProductProperty().get().getNameProperty()
                                 .getValue().toLowerCase()
                                 .contains(lowerCaseFilter))
                           {
                              return true;
                           }
                           return false;
                        });
               });
         SortedList<ViewModelSale> sortedSaleData = new SortedList<ViewModelSale>(
               filteredSaleData);
         sortedSaleData.comparatorProperty()
               .bind(saleTable.comparatorProperty());
      });

      saleTable.setItems(filteredSaleData);

   }

   public Scene getScene()
   {
      return scene;
   }

   public String getTitle()
   {
      return title;
   }

   public void onCheckOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("offerlist");
   }

   public void onHomeButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("start");
   }

   public void onRequestOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("offer");
   }

   public void onManageSaleButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("saleslist");
   }
   
   public void onCheckSalesButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("sales");
   }

   public void onCreateSaleButtonPressed()
   {

   }

   public void onEditSaleButtonPressed()
   {

   }

   public void onCancelSaleButtonPressed()
   {

   }

}

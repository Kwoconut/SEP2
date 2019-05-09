package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelProduct;
import viewmodel.ViewModelProductList;

public class ViewProductList implements View
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
   private TableColumn<ViewModelProduct, String> productColour;
   @FXML
   private TableColumn<ViewModelProduct, Number> productPrice;
   
   private String title;
   private Scene scene;
   private MainView view;
   private ViewModelProductList viewModel;


   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      this.viewModel = viewModel.viewModelProductList();
      this.view = view;
      this.scene = scene;
      this.title = title;

      productID.setCellValueFactory(
            cellData -> cellData.getValue().getIdProperty());
      productName.setCellValueFactory(
            cellData -> cellData.getValue().getNameProperty());
      productType.setCellValueFactory(
            cellData -> cellData.getValue().getTypeProperty());
      productColour.setCellValueFactory(
            cellData -> cellData.getValue().getColourProperty());
      productPrice.setCellValueFactory(
            cellData -> cellData.getValue().getPriceProperty());
      
      productTable.setItems(this.viewModel.getProduct());
   }
   
   public Scene getScene()
   {
      return scene;
   }

   public String getTitle()
   {
      return title;
   }
   

   @FXML
   public void backToMain() throws IOException 
   {
      nextWindow();
   }
   
   public void nextWindow() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("start");
   }

}

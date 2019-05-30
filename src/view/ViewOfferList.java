package view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelManageOffer;
import viewmodel.ViewModelOfferList;

public class ViewOfferList extends View
{
   @FXML
   private TableView<ViewModelManageOffer> offerListTable;
   @FXML
   private TableColumn<ViewModelManageOffer, String> nameColumn;
   @FXML
   private TableColumn<ViewModelManageOffer, String> phoneColumn;
   @FXML
   private TableColumn<ViewModelManageOffer, String> emailColumn;
   @FXML
   private TableColumn<ViewModelManageOffer, String> messageColumn;
   @FXML
   private Label errorLabel;

   private String title;
   private Scene scene;
   private ViewModelOfferList viewModel;
   private MainViewViewModel model;
   
   public ViewOfferList()
   {
      
   }

   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      model = viewModel;
      this.viewModel = viewModel.getViewModelOfferList();
      super.setMainView(view);
      this.scene = scene;
      this.title = title;

      nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
      phoneColumn.setCellValueFactory(
            cellData -> cellData.getValue().getPhoneNumber());
      emailColumn
            .setCellValueFactory(cellData -> cellData.getValue().getEmail());
      messageColumn
            .setCellValueFactory(cellData -> cellData.getValue().getMessage());

      errorLabel.textProperty()
            .bindBidirectional(this.viewModel.getErrorProperty());

      offerListTable.setItems(this.viewModel.getOffers());

      offerListTable.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldValue, newValue) -> this.viewModel.setSelected(newValue));

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
   private void cancelButtonPressed() throws IOException
   {
      nextWindow();
   }

   public void nextWindow() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("start");
   }

   public void onCheckOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("offerlist");
   }

   public void onHomeButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("start");
   }

   public void onRequestOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("offer");
   }

   public void onManageSaleButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("saleslist");
   }

   public void onCheckSalesButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("sales");
   }

   @FXML
   private void manageButtonPressed() throws IOException
   {
      if (this.viewModel.getSelected().getValue() == null)
      {
         errorLabel.setText("Please select an offer");
      }
      else
      {
         model.getViewModelManageOffer()
               .setSelected(viewModel.getSelected().get());
         getScene().getWindow().hide();
         super.getMainView().setWindow("manageoffer");
         errorLabel.setText("");
      }
   }

}

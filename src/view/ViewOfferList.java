package view;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelOfferList;
import viewmodel.ViewModelRequestOffer;

public class ViewOfferList implements View
{
   @FXML
   private TableView<ViewModelRequestOffer> offerListTable;
   @FXML
   private TableColumn<ViewModelRequestOffer, String> nameColumn;
   @FXML
   private TableColumn<ViewModelRequestOffer, String> phoneColumn;
   @FXML
   private TableColumn<ViewModelRequestOffer, String> emailColumn;
   @FXML
   private TableColumn<ViewModelRequestOffer, String> messageColumn;
   @FXML
   private Label errorLabel;

   private String title;
   private Scene scene;
   private MainView view;
   private ViewModelOfferList viewModel;

   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      this.viewModel = viewModel.getViewModelOfferList();
      this.view = view;
      this.scene = scene;
      this.title = title;

      nameColumn.setCellValueFactory(
            cellData -> cellData.getValue().getNameProperty());
      phoneColumn.setCellValueFactory(
            cellData -> cellData.getValue().getPhoneProperty());
      emailColumn.setCellValueFactory(
            cellData -> cellData.getValue().getEmailProperty());
      messageColumn.setCellValueFactory(
            cellData -> cellData.getValue().getMessageProperty());

      errorLabel.textProperty().bindBidirectional(this.viewModel.getErrorProperty());

      offerListTable.setItems(this.viewModel.getOffers());

      offerListTable.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldValue, newValue) -> this.viewModel.setSelected(newValue));
      offerListTable.getSelectionModel().selectedItemProperty()
            .addListener((obs, oldValue, newValue) -> viewModel
                  .getViewModelManageOffer().setSelected(newValue));

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
      view.setWindow("start");
   }

   @FXML
   private void manageButtonPressed() throws IOException
   {
     if (this.viewModel.getSelected() == null)
     {
        errorLabel.setText("Please select an offer");
     }
     else
     {
        getScene().getWindow().hide();
        view.setWindow("manageoffer");
     }
   }

}

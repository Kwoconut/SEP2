package view;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelManageOffer;
import viewmodel.ViewModelOfferList;
import viewmodel.ViewModelRequestOffer;

public class ViewOfferList implements View
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
   private MainView view;
   private ViewModelOfferList viewModel;
   private MainViewViewModel model;

   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      model = viewModel;
      this.viewModel = viewModel.getViewModelOfferList();
      this.view = view;
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
      view.setWindow("start");
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
         view.setWindow("manageoffer");
         errorLabel.setText("");
      }
   }

}

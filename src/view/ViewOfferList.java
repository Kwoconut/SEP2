package view;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.ViewModelOfferList;
import viewmodel.ViewModelRequestOffer;

public class ViewOfferList
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
   
   private String title;
   private Scene scene;
   private MainView view;
   private ViewModelOfferList viewModel;


   public void init(ViewModelOfferList viewModel, MainView view, Scene scene,
         String title) throws RemoteException
   {
      this.viewModel = viewModel;
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
      
      offerListTable.setItems(viewModel.getOffers());
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
      view.setWindow("START");
   }

}

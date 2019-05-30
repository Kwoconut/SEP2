package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelManageOffer;

public class ViewManageOffer extends View
{

   @FXML
   Label nameField;
   @FXML
   Label phoneNumberField;
   @FXML
   Label emailField;
   @FXML
   Label messageField;

   private String title;
   private Scene scene;
   private ViewModelManageOffer viewModel;

   public ViewManageOffer()
   {

   }

   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)

   {

      this.viewModel = viewModel.getViewModelManageOffer();
      super.setMainView(view);
      this.scene = scene;
      this.title = title;
      nameField.textProperty().bind(this.viewModel.getName());
      phoneNumberField.textProperty().bind(this.viewModel.getPhoneNumber());
      emailField.textProperty().bind(this.viewModel.getEmail());
      messageField.textProperty().bind(this.viewModel.getMessage());
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
   private void closeButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("offerlist");
   }

   @FXML
   private void deleteButtonPressed() throws IOException
   {
      Alert alert = new Alert(AlertType.CONFIRMATION,
            "Remove Offer " + nameField.getText() + " ?", ButtonType.YES,
            ButtonType.CANCEL);
      alert.showAndWait();
      if (alert.getResult() == ButtonType.YES)
      {
         viewModel.removeOffer();
         getScene().getWindow().hide();
         super.getMainView().setWindow("offerlist");
      }
   }

}
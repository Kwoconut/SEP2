package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewmodel.ViewModelOffer;

public class ViewOffer
{
   @FXML
   TextField nameField;

   @FXML
   TextField phoneField;

   @FXML
   TextField emailField;

   @FXML
   TextField messageField;

   @FXML
   Label errorLabel;

   private ViewModelOffer model;

   private MainView view;

   private Scene scene;

   private String title;

   public ViewOffer()
   {

   }

   public void init(ViewModelOffer viewModel, MainView view, Scene scene,
         String title)
   {
      this.model = viewModel;
      this.view = view;
      this.scene = scene;
      this.title = title;
      nameField.textProperty().bindBidirectional(viewModel.getNameProperty());
      phoneField.textProperty().bindBidirectional(viewModel.getPhoneProperty());
      emailField.textProperty().bindBidirectional(viewModel.getEmailProperty());
      messageField.textProperty()
            .bindBidirectional(viewModel.getMessageProperty());
      errorLabel.textProperty().bind(viewModel.getErrorProperty());

   }

   public void onSubmitButton() throws IOException
   {
      model.sendValuesToServer();
      if (errorLabel.getText().equals(""))
      {
         getScene().getWindow().hide();
         view.setWindow("START");
      }
   }

   public void onCancelButton() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("START");
   }

   public String getTitle()
   {
      return title;
   }

   public Scene getScene()
   {
      return scene;
   }
}

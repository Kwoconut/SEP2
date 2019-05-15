package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelRequestOffer;

public class ViewRequestOffer implements View
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

   private ViewModelRequestOffer model;

   private MainView view;

   private Scene scene;

   private String title;

   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      this.model = viewModel.getViewModelRequestOffer();
      this.view = view;
      this.scene = scene;
      this.title = title;
      nameField.textProperty().bindBidirectional(model.getNameProperty());
      phoneField.textProperty().bindBidirectional(model.getPhoneProperty());
      emailField.textProperty().bindBidirectional(model.getEmailProperty());
      messageField.textProperty()
            .bindBidirectional(model.getMessageProperty());
      errorLabel.textProperty().bindBidirectional(model.getErrorProperty());

   }

   public void onSubmitButton() throws IOException
   {
      model.addOffer();
      if (errorLabel.getText().equals(""))
      {
         getScene().getWindow().hide();
         nameField.setText("");
         phoneField.setText("");
         emailField.setText("");
         messageField.setText("");
         nameField.setPromptText("Name");
         phoneField.setPromptText("Phone");
         emailField.setPromptText("Email");
         messageField.setPromptText("Message");
         view.setWindow("start");
      }
   }

   public void onCancelButton() throws IOException
   {
      getScene().getWindow().hide();
      nameField.setText("");
      phoneField.setText("");
      emailField.setText("");
      messageField.setText("");
      nameField.setPromptText("Name");
      phoneField.setPromptText("Phone");
      emailField.setPromptText("Email");
      messageField.setPromptText("Message");
      errorLabel.setText("");
      view.setWindow("start");
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

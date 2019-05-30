package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelLogin;

public class ViewLogin extends View
{

   @FXML
   TextField usernameField;

   @FXML
   PasswordField passwordField;

   @FXML
   Label errorLabel;

   private ViewModelLogin model;

   private Scene scene;

   private String title;

   public ViewLogin()
   {

   }

   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      this.model = viewModel.getViewModelLogin();
      super.setMainView(view);
      this.scene = scene;
      this.title = title;
      usernameField.textProperty()
            .bindBidirectional(model.getUsernameProperty());
      passwordField.textProperty()
            .bindBidirectional(model.getPasswordProperty());
      errorLabel.textProperty().bindBidirectional(model.getErrorProperty());
   }

   private void resetFields() throws IOException
   {
      usernameField.setText("");
      passwordField.setText("");
      usernameField.setPromptText("Username");
      passwordField.setPromptText("Password");
      errorLabel.setText("");
   }

   @Override
   public Scene getScene()
   {
      return scene;
   }

   @Override
   public String getTitle()
   {
      return title;
   }

   public void onSignInAsGuestButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      resetFields();
      super.getMainView().setWindow("start");
   }

   public void onSignInButtonPressed() throws IOException
   {
      model.validateLogin();
      if (errorLabel.getText().equals("")
            && model.getLoginProperty().get().equals("administrator"))
      {
         getScene().getWindow().hide();
         resetFields();
         super.getMainView().setWindow("start");
      }
   }

}

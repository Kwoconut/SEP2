package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import viewmodel.ViewModelStore;

public class ViewStore
{

   @FXML
   Label label00;
   
   @FXML
   Label label01;
   
   @FXML
   Label label02;
   
   @FXML
   Label label10;
   
   @FXML
   Label label11;
   
   @FXML
   Label label12;
   
   @FXML
   Label label20;
   
   @FXML
   Label label21;
   
   @FXML
   Label label22;
   
   @FXML
   Label label30;
   
   @FXML
   Label label31;
   
   @FXML
   Label label32;
   
   @FXML
   Label label40;
   
   @FXML
   Label label41;
   
   @FXML
   Label label42;
   
   

   private ViewModelStore model;

   private MainView view;

   private Scene scene;

   private String title;

   public void init(ViewModelStore viewModel, MainView view, Scene scene,
         String title)
   {
      this.model = viewModel;
      this.view = view;
      this.scene = scene;
      this.title = title;
      label00.textProperty().bind(model.getNameProperty(0));
      label10.textProperty().bind(model.getNameProperty(1));
      label20.textProperty().bind(model.getNameProperty(2));
      label30.textProperty().bind(model.getNameProperty(3));
      label40.textProperty().bind(model.getNameProperty(4));
      label01.textProperty().bind(model.getPriceProperty(0));
      label11.textProperty().bind(model.getPriceProperty(1));
      label21.textProperty().bind(model.getPriceProperty(2));
      label31.textProperty().bind(model.getPriceProperty(3));
      label41.textProperty().bind(model.getPriceProperty(4));
         
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

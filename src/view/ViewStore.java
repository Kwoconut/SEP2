package view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelStore;

public class ViewStore implements View
{
   
   @FXML
   ArrayList<Label> nameLabelList;
   
   @FXML
   ArrayList<Label> priceLabelList;   

   private ViewModelStore model;

   private MainView view;

   private Scene scene;

   private String title;
   
   public ViewStore()
   {
      
   }

   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      this.model = viewModel.getViewModelStore();
      this.view = view;
      this.scene = scene;
      this.title = title;
      for (int i = 0 ; i<5;i++)
      {
         nameLabelList.get(i).textProperty().bind(model.getNameProperty(i));
         priceLabelList.get(i).textProperty().bind(model.getPriceProperty(i));
      }
         
   }
   
   public void onRequestOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("offer");
   }
   
   public void onCheckOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("offerlist");
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

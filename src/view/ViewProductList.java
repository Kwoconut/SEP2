package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelProduct;
import viewmodel.ViewModelProductList;

public class ViewProductList implements View
{
   
   private String title;
   private Scene scene;
   private MainView view;
   private ViewModelProductList viewModel;


   public void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title)
   {
      this.viewModel = viewModel.viewModelProductList();
      this.view = view;
      this.scene = scene;
      this.title = title;

   }
   
   public Scene getScene()
   {
      return scene;
   }

   public String getTitle()
   {
      return title;
   }
   
   public void onCheckOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("offerlist");
   }
   
   public void onHomeButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("start");
   }
   
   public void onRequestOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("offer");
   }
   
   public void onManageSaleButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      view.setWindow("saleslist");
   }

}

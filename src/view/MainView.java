package view;

import java.io.IOException;
import java.rmi.RemoteException;

import viewmodel.MainViewViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView
{
   private Stage primaryStage;

   private View view;
   
   private String currentViewID;

   private MainViewViewModel viewModel;

   public MainView(MainViewViewModel viewModel)
   {
      this.viewModel = viewModel;
   }

   public void start(Stage primaryStage) throws IOException
   {
     this.primaryStage = primaryStage;
     setWindow("start");
   }

   public void setWindow(String id) throws IOException
   {
     
     view = ViewFactory.getView(id, viewModel, this); 
     
     primaryStage.setScene(view.getScene());
     primaryStage.setTitle(view.getTitle());
     primaryStage.show();

   }
   
   public MainViewViewModel getMainViewViewModel()
   {
	   return viewModel;
   }

}

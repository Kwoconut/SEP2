package view;

import javafx.scene.Scene;
import viewmodel.MainViewViewModel;

public abstract class View
{
   private MainView view;
   
   public void setMainView(MainView view)
   {
      this.view = view;
   }
   
   public MainView getMainView()
   {
      return view;
   }
   
   abstract void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title);

   abstract Scene getScene();

   abstract String getTitle();
}

package view;

import javafx.scene.Scene;
import viewmodel.MainViewViewModel;

public interface View
{
   void init(MainViewViewModel viewModel, MainView view, Scene scene,
         String title);

   Scene getScene();

   String getTitle();
}

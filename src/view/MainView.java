package view;

import java.io.IOException;
import viewmodel.MainViewViewModel;
import javafx.stage.Stage;

public class MainView
{
   private Stage primaryStage;
   private View view;
   private MainViewViewModel viewModel;

   public MainView(MainViewViewModel viewModel)
   {
      this.viewModel = viewModel;
   }

   public void start(Stage primaryStage) throws IOException
   {
      this.primaryStage = primaryStage;
      setWindow("login");
   }

   public void setWindow(String id) throws IOException
   {

      view = ViewFactory.getView(id, viewModel, this);

      primaryStage.setScene(view.getScene());
      primaryStage.setTitle(view.getTitle());
      primaryStage.show();

   }

}

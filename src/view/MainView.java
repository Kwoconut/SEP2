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

      if (id.equals("productList"))
      {
         ViewProductList viewProductList = (ViewProductList) view;
         viewProductList.refresh();
      }
      else if (id.equals("sales"))
      {
         ViewSalesList viewSalesList = (ViewSalesList) view;
         viewSalesList.refresh();
      }
      else if (id.equals("createsale"))
      {
         ViewCreateSale viewCreateSale = (ViewCreateSale) view;
         viewCreateSale.refresh();
      }
      else if (id.equals("review"))
      {
         ViewProductReview viewProductReview = (ViewProductReview) view;
         viewProductReview.refresh();
      }

      primaryStage.setScene(view.getScene());
      primaryStage.setTitle(view.getTitle());
      primaryStage.show();

   }

}

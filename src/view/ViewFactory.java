package view;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import viewmodel.MainViewViewModel;

public class ViewFactory
{
   private static HashMap<String, View> views = new HashMap<String, View>();

   static View getView(String getType, MainViewViewModel viewModel,
         MainView mainView) throws IOException
   {
      View view = views.get(getType);

      if (view == null)
      {
         switch (getType)
         {
            case "start":
            {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(
                     mainView.getClass().getResource("ViewStore.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root, 1200, 700);
               view = loader.getController();
               view.init(viewModel, mainView, scene, "Store");
               break;
            }
            case "offer":
            {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(
                     mainView.getClass().getResource("ViewRequestOffer.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root, 1200, 700);
               view = loader.getController();
               view.init(viewModel, mainView, scene, "Request-Offer");
               break;
            }
            case "offerlist":
            {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(
                     mainView.getClass().getResource("ViewOfferList.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root, 1200, 700);
               view = loader.getController();
               view.init(viewModel, mainView, scene, "Offer-List");
               break;
            }
            case "productList":
            {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(
                     mainView.getClass().getResource("ViewProductList.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root, 1200, 700);
               view = loader.getController();
               view.init(viewModel, mainView, scene, "Product-Details");
               break;
            }
            case "manageoffer":
            {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(
                     mainView.getClass().getResource("ViewManageOffer.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root, 1200, 700);
               view = loader.getController();
               view.init(viewModel, mainView, scene, "Manage-Offer");
               break;
            }
            case "saleslist":
            {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(mainView.getClass()
                     .getResource("ViewManageSalesList.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root, 1200, 700);
               view = loader.getController();
               view.init(viewModel, mainView, scene, "Manage-Sales");
               break;
            }
            case "sales":
            {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(
                     mainView.getClass().getResource("ViewSalesList.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root, 1200, 700);
               view = loader.getController();
               view.init(viewModel, mainView, scene, "Salelist");
               break;
            }

            case "createsale":
            {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(
                     mainView.getClass().getResource("ViewCreateSale.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root, 1200, 700);
               view = loader.getController();
               view.init(viewModel, mainView, scene, "Create Sale");
               break;
            }
            
            case "login":
            {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(
                     mainView.getClass().getResource("ViewLogin.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root, 1200, 700);
               view = loader.getController();
               view.init(viewModel, mainView, scene, "Login");
               break;
            }
            case "review":
            {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(
                     mainView.getClass().getResource("ViewProductReview.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root, 1200, 700);
               view = loader.getController();
               view.init(viewModel, mainView, scene, "Review");
               break;
            }

         }
         views.put(getType, view);
      }
      
      if (getType.equals("productList"))
      {
         ViewProductList viewProductList = (ViewProductList) view;
         viewProductList.refresh();
      }
      else if (getType.equals("sales"))
      {
         ViewSalesList viewSalesList = (ViewSalesList) view;
         viewSalesList.refresh();
      }
      else if (getType.equals("review"))
      {
         ViewProductReview viewProductReview = (ViewProductReview) view;
         viewProductReview.refresh();
      }
      else if (getType.equals("createsale"))
      {
        ViewCreateSale viewCreateSale = (ViewCreateSale) view;
        viewCreateSale.refresh();
      }
      
      return view;

   }
}

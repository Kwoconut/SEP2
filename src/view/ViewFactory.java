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

         }
         views.put(getType, view);
      }
      return view;

   }
}

package view;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import viewmodel.MainViewViewModel;
import viewmodel.ViewModelProduct;
import viewmodel.ViewModelProductList;

public class ViewProductList extends View
{

   @FXML
   ScrollPane mainPane;

   private String title;
   private Scene scene;
   private ViewModelProductList viewModel;

   public ViewProductList()
   {

   }

   @SuppressWarnings("static-access")
   public void init(MainViewViewModel vm, MainView view, Scene scene,
         String title)
   {
      this.viewModel = vm.getViewModelProductList();
      super.setMainView(view);
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

   public void refresh()
   {
      ObservableList<ViewModelProduct> products = this.viewModel.getProducts();

      ObservableList<StackPane> stackPane = FXCollections.observableArrayList();
      ObservableList<Label> names = FXCollections.observableArrayList();
      ObservableList<Label> prices = FXCollections.observableArrayList();
      ObservableList<ImageView> image = FXCollections.observableArrayList();

      GridPane gridPane = new GridPane();
      gridPane.setPadding(new Insets(20, 20, 20, 31));
      gridPane.setVgap(20);
      gridPane.setHgap(43);

      int x = 0;
      int y = 0;

      for (int i = 0; i < products.size(); i++)
      {
         names.add(new Label(products.get(i).getNameProperty().get()));
         prices.add(new Label(
               Double.toString(products.get(i).getPriceProperty().get())
                     + "DKK"));
         image.add(new ImageView(new Image(
               "images/" + products.get(i).getImageProperty().get())));

         stackPane.add(new StackPane());
         stackPane.get(i).setPrefWidth(212.8);
         stackPane.get(i).setPrefHeight(393);
         stackPane.get(i).setStyle("-fx-background-color: lightgrey");
         names.get(i).setFont(Font.font(20));
         prices.get(i).setFont(Font.font(20));
         names.get(i).setPadding(new Insets(40, 10, 10, 10));
         prices.get(i).setPadding(new Insets(10, 10, 40, 10));
         image.get(i).setFitHeight(150);
         image.get(i).setFitWidth(200);
         stackPane.get(i).getChildren().addAll(names.get(i));
         stackPane.get(i).getChildren().addAll(prices.get(i));
         stackPane.get(i).getChildren().addAll(image.get(i));
         StackPane.setAlignment(names.get(i), Pos.TOP_CENTER);
         StackPane.setAlignment(prices.get(i), Pos.BOTTOM_CENTER);
         GridPane.setConstraints(stackPane.get(i), x, y);
         x++;
         if (x == 4)
         {
            y++;
            x = 0;
         }
      }
      gridPane.getChildren().addAll(stackPane);

      gridPane.addEventFilter(MouseEvent.MOUSE_PRESSED,
            new EventHandler<MouseEvent>()
            {
               public void handle(MouseEvent e)
               {
                  for (Node node : gridPane.getChildren())
                  {
                     if (node.getBoundsInParent().contains(e.getSceneX(),
                           e.getSceneY()))
                     {
                        getScene().getWindow().hide();
                        viewModel.getProductByID(GridPane.getColumnIndex(node),
                              GridPane.getRowIndex(node));
                        try
                        {
                           onSelectItemPressed();
                        }
                        catch (IOException e1)
                        {
                           // TODO Auto-generated catch block
                           e1.printStackTrace();
                        }
                     }

                  }
               }
            });

      mainPane.setContent(gridPane);

   }

   public void onCheckOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("offerlist");
   }

   public void onHomeButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("start");
   }

   public void onRequestOfferButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("offer");
   }

   public void onManageSaleButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("saleslist");
   }

   public void onCheckSalesButtonPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("sales");
   }
   
   public void onSelectItemPressed() throws IOException
   {
      getScene().getWindow().hide();
      super.getMainView().setWindow("review");
   }

}

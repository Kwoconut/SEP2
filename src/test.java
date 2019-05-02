
import view.MainView;
import viewmodel.MainViewViewModel;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Product;
import model.Store;
import model.StoreModel;

public class test extends Application
{

   @Override
   public void start(Stage stage) throws Exception
   {
      StoreModel model = new Store();
      model.addProduct(new Product(Product.TYPE_METAL_SHEET, 1500,
            "Sa mori tu", "sugator pentru gicu"));
      model.addProduct(new Product(Product.TYPE_METAL_TILE, 2000,
            "Sa mori tu", "sugator pentru gicu"));
      model.addProduct(new Product(Product.TYPE_PLATED_SHEET, 3000,
            "Sa mori tu", "sugator pentru gicu"));
      model.addProduct(new Product(Product.TYPE_RAIN_SYSTEM, 5000,
            "Sa mori tu", "sugator pentru gicu"));
      model.addProduct(new Product(Product.TYPE_RAIN_SYSTEM, 5000,
            "Sa mori tu", "sugator pentru gicu"));
      MainViewViewModel mvvm = new MainViewViewModel(model);
      MainView view = new MainView(mvvm);
      view.start(stage);
      
   }
}

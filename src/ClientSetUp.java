import client.Client;
import client.IClient;
import client.RIClient;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Product;
import model.Store;
import model.StoreModel;
import server.ServerAccess;
import view.MainView;
import viewmodel.MainViewViewModel;

public class ClientSetUp extends Application
{
   

   @Override
   public void start(Stage stage) throws Exception
   {
      StoreModel model = new Store();
      RIClient client = new Client(model);
      MainViewViewModel mvvm = new MainViewViewModel(model);
      MainView view = new MainView(mvvm);
      view.start(stage);
   }

}

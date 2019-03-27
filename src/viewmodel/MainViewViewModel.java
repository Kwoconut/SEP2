package viewmodel;

import java.rmi.RemoteException;

import model.StoreModel;

public class MainViewViewModel
{
   
   private ViewModelStore viewModelStore;
   private StoreModel model;
   
   public MainViewViewModel(StoreModel model) throws RemoteException
   {
      this.model = model;
      viewModelStore = new ViewModelStore(model);
   }
   
   public ViewModelStore getViewModelStore()
   {
      return viewModelStore;
   }

}

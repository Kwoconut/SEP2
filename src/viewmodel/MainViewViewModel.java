package viewmodel;

import java.rmi.RemoteException;

import model.StoreModel;

public class MainViewViewModel
{
   
   private ViewModelStore viewModelStore;
   private ViewModelOffer viewModelOffer;
   private StoreModel model;
   
   public MainViewViewModel(StoreModel model) throws RemoteException
   {
      this.model = model;
      viewModelStore = new ViewModelStore(model);
      viewModelOffer = new ViewModelOffer(model);
   }
   
   public ViewModelStore getViewModelStore()
   {
      return viewModelStore;
   }
   
   public ViewModelOffer getViewModelOffer()
   {
      return viewModelOffer;
   }

}

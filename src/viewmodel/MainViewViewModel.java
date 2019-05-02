package viewmodel;

import java.rmi.RemoteException;

import model.StoreModel;

public class MainViewViewModel
{
   
   private ViewModelStore viewModelStore;
   private ViewModelRequestOffer viewModelRequestOffer;
   private ViewModelOfferList viewModelOfferList;
   private StoreModel model;
   
   public MainViewViewModel(StoreModel model) throws RemoteException
   {
      this.model = model;
      viewModelStore = new ViewModelStore(model);
      viewModelRequestOffer = new ViewModelRequestOffer(model);
      viewModelOfferList = new ViewModelOfferList(model);
   }
   
   public ViewModelStore getViewModelStore()
   {
      return viewModelStore;
   }
   
   public ViewModelRequestOffer getViewModelRequestOffer()
   {
      return viewModelRequestOffer;
   }
   
   public ViewModelOfferList getViewModelOfferList()
   {
      return viewModelOfferList;
   }

}

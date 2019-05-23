package viewmodel;

import java.rmi.RemoteException;

import model.StoreModel;

public class MainViewViewModel
{

   private ViewModelStore viewModelStore;
   private ViewModelRequestOffer viewModelRequestOffer;
   private ViewModelOfferList viewModelOfferList;
   private ViewModelProduct viewModelProduct;
   private ViewModelProductList viewModelProductList;
   private ViewModelManageOffer viewModelManageOffer;
   private ViewModelManageSalesList viewModelManageSalesList;
   private ViewModelSaleList viewModelSaleList;
   private StoreModel model;

   public MainViewViewModel(StoreModel model) throws RemoteException
   {
      this.model = model;
      viewModelProduct = new ViewModelProduct(model);
      viewModelProductList = new ViewModelProductList(model);
      viewModelStore = new ViewModelStore(model);
      viewModelRequestOffer = new ViewModelRequestOffer(model);
      viewModelOfferList = new ViewModelOfferList(model);
      viewModelManageOffer = new ViewModelManageOffer(model);
      viewModelManageSalesList = new ViewModelManageSalesList(model, model);
      viewModelSaleList = new ViewModelSaleList(model);
   }

   public ViewModelProductList getViewModelProductList()
   {
      return viewModelProductList;
   }

   public ViewModelProduct getViewModelProduct()
   {
      return viewModelProduct;
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

   public ViewModelManageOffer getViewModelManageOffer()
   {
      return viewModelManageOffer;
   }

   public ViewModelManageSalesList getViewModelManageSalesList()
   {
      return viewModelManageSalesList;
   }
   public ViewModelSaleList getViewModelSaleList()
   {
      return viewModelSaleList;
   }

}

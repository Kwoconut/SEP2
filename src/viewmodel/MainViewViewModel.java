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
   private ViewModelSale viewModelSale;
   private ViewModelLogin viewModelLogin;
   private ViewModelProductReview viewModelProductReview;
   private ViewModelInfo viewModelInfo;
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
      viewModelSaleList = new ViewModelSaleList(model);
      viewModelManageSalesList = new ViewModelManageSalesList(model, model);
      viewModelSale = new ViewModelSale(model);
      viewModelLogin = new ViewModelLogin(model);
      viewModelProductReview = new ViewModelProductReview(model);
      viewModelInfo = new ViewModelInfo(model);
   }

   public ViewModelProductReview getViewModelProductReview()
   {
      return viewModelProductReview;
   }

   public ViewModelSale getViewModelSale()
   {
      return viewModelSale;
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
   
   public ViewModelInfo getViewModelInfo()
   {
      return viewModelInfo;
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

   public ViewModelLogin getViewModelLogin()
   {
      return viewModelLogin;
   }

}

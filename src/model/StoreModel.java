package model;

public interface StoreModel extends SProductModel, SOfferModel,
      StoreModelClientHandler, SSalesModel, SReviewModel
{
    void validateLogin(String user, String password);
}

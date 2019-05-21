package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.Client;

public interface StoreModel extends SProductModel, SOfferModel, StoreModelClientHandler, SSalesModel
{

}

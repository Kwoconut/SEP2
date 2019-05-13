package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

import client.Client;

public class Store implements StoreModel, Serializable
{
   private ArrayList<Product> products;
   private ArrayList<Offer> offers;
   private Client client;
   private PropertyChangeSupport support = new PropertyChangeSupport(this);
   private int index;
   
   public Store()
   {
      products = new ArrayList<Product>();
      offers = new ArrayList<Offer>();
   }

   @Override
   public void addProduct(Product product)
   {
      products.add(product);
   }

   @Override
   public ArrayList<Product> getProducts()
   {
      return products;
   }
   
   @Override
   public ArrayList<Offer> getOffers() 
   {
	   return offers;
   }
   public void getProductsByType(String type)
   {
      ArrayList<Product> elements = new ArrayList<Product>();
      for(Product value: products) {
         if(value.getType().equals(type)) {
            elements.add(value);
         }
      }
      support.firePropertyChange("DETAILS", null, elements);
   }

   @Override
   public void getProductsFromServer(ArrayList<Product> values)
   {

      products = values;
      support.firePropertyChange("SEND", "", values);
   }
   
   @Override
   public void getOffersFromServer(ArrayList<Offer> offers)
   {
      this.offers = offers;
      support.firePropertyChange("OFFERLIST", "", offers);

   }
   
   public void addOfferFromServer(Offer offer)
   {
      support.firePropertyChange("NEWOFFER", "", offer);
   }

   @Override
   public void setClient(Client client)
   {
      this.client = client;
   }

   @Override
   public Client getClient()
   {
      return client;
   }

   @Override
   public void requestOffers() throws RemoteException
   {
      client.requestOffers();
      
   }
   
   @Override
   public void requestProducts() throws RemoteException
   {
      client.requestProducts();
   }

   @Override
   public void addListener(PropertyChangeListener listener)
   {
      support.addPropertyChangeListener(listener);
   }

   @Override
   public void addOffer(Offer offer)
   {
      int errorCase = offer.checkFormat();

      switch (errorCase)
      {
         case 0:
         {
            support.firePropertyChange("nameInvalid", "",
                  "Please input a name");
            break;
         }
         case 1:
         {
            support.firePropertyChange("phoneInvalid", "",
                  "Invalid phone number");
            break;
         }
         case 2:
         {
            support.firePropertyChange("emailInvalid", "", "Invalid email");
            break;
         }
         case 3:
         {
            support.firePropertyChange("messageInvalid", "",
                  "Please input a message");
            break;
         }
         case 4:
         {
            try
            {
               offers.add(offer);
               client.sendOfferToServer(offer);
            }
            catch (RemoteException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }

         }
      }

   }
   
   @Override
   public int generateProductID()
   {
      for(int i = 1; i < generateProductMaxID(); i++)
      {
         boolean flag = false;
         for(Product product: products)
         {
            if(product.getID() == i)
            {
               flag = true;
            }
         }
         if(!flag)
         {
            return i;
         }
      }
      return products.size() + 1;
   }
   
   private int generateProductMaxID()
   {
      int maxi = 1;
      for(Product product:products)
      {
         if(product.getID() > maxi)
         {
            maxi = product.getID();
         }
      }
      return maxi;
   }
   
   @Override
   public int generateOfferID()
   {
      for(int i = 1; i < generateOfferMaxID(); i++)
      {
         boolean flag = false;
         for(Offer offer: offers)
         {
            if(offer.getID() == i)
            {
               flag = true;
            }
         }
         if(!flag)
         {
            return i;
         }
      }
      return offers.size() + 1;
   }

   private int generateOfferMaxID()
   {
      int maxi = 1;
      for(Offer offer:offers)
      {
         if(offer.getID() > maxi)
         {
            maxi = offer.getID();
         }
      }
      return maxi;
   }
   
   @Override
   public void removeOffer() 
   {
	   Offer offer = offers.get(index);
	   try 
	{
        offers.remove(index);
	    client.removeOfferFromServer(offer);
	} 
	    catch (RemoteException e) 
	{
	    e.printStackTrace();
	}
   }
	   
   public void saveIndex(int index)
   {
	   this.index = index;
	   System.out.println(offers.get(index));
   }
   
   public void removeOfferFromServer(Offer offer)
   {
      support.firePropertyChange("MINUSOFFER", "", offer);
   }
   
   public Offer getTheOffer()
   {
	   return offers.get(index);
   }
}

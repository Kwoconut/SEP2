package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import client.Client;
import viewmodel.ViewModelProduct;

public class Store implements Serializable, StoreModel
{
   private ArrayList<Product> products;
   private ArrayList<Offer> offers;
   private Client client;
   private PropertyChangeSupport support = new PropertyChangeSupport(this);
   private ArrayList<Sale> sales;

   public Store()
   {
      products = new ArrayList<Product>();
      offers = new ArrayList<Offer>();
   }

   public void addProduct(Product product)
   {
      products.add(product);
   }

   @Override
   public void addOffer(String name, String phone, String email, String message)
   {
      Offer offer = new Offer(IDGenerator.generateOfferID(offers), name, phone,
            email, message);

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
               support.firePropertyChange("valid", null, " ");
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

   public ArrayList<Product> getProducts()
   {
      return products;
   }

   public ArrayList<Offer> getOffers()
   {
      return offers;
   }

   public ArrayList<Sale> getSales()
   {
      return sales;
   }

   @Override
   public void removeOffer(int id, String name, String phone, String email,
         String message) throws RemoteException
   {
      Offer offer = new Offer(id, name, phone, email, message);
      System.out.println("Removing offer with id " + offer.getID());
      client.removeOfferFromServer(offer);
   }

   @Override
   public ArrayList<Product> getProductsByType(String type)
   {
      ArrayList<Product> elements = new ArrayList<Product>();
      for (Product value : products)
      {
         if (value.getType().equals(type))
         {
            elements.add(value);
         }
      }
      support.firePropertyChange("DETAILS", " ", elements);
      return elements;
   }

   @Override
   public void getProductsFromServer(ArrayList<Product> values)
   {

      products = values;
      ArrayList<Product> firstfiveproducts;
      firstfiveproducts = new ArrayList<Product>();
      firstfiveproducts.add(values.stream()
            .filter(
                  product -> product.getType().equals(Product.TYPE_CLICK_SHEET))
            .findFirst().get());
      firstfiveproducts.add(values.stream()
            .filter(
                  product -> product.getType().equals(Product.TYPE_METAL_SHEET))
            .findFirst().get());
      firstfiveproducts.add(values.stream().filter(
            product -> product.getType().equals(Product.TYPE_PLATED_SHEET))
            .findFirst().get());
      firstfiveproducts.add(values.stream()
            .filter(
                  product -> product.getType().equals(Product.TYPE_METAL_TILE))
            .findFirst().get());
      firstfiveproducts.add(values.stream()
            .filter(
                  product -> product.getType().equals(Product.TYPE_RAIN_SYSTEM))
            .findFirst().get());
      support.firePropertyChange("SEND", "", firstfiveproducts);
      support.firePropertyChange("ALLPRODUCTS", "", products);
   }

   @Override
   public void getOffersFromServer(ArrayList<Offer> offers)
   {
      this.offers = offers;
      support.firePropertyChange("OFFERLIST", "", offers);
   }

   @Override
   public void addOfferFromServer(Offer offer)
   {
      offers.add(offer);
      support.firePropertyChange("NEWOFFER", "", offer);
   }

   @Override
   public void removeOfferFromServer(Offer offer)
   {
      offers.remove(offer);
      support.firePropertyChange("MINUSOFFER", "", offer);
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
   public void setClient(Client client)
   {
      this.client = client;
   }

   public Client getClient()
   {
      return client;
   }

   @Override
   public void addListener(PropertyChangeListener listener)
   {
      support.addPropertyChangeListener(listener);
   }

   @Override
   public void requestSales() throws RemoteException
   {
      client.requestSales();
   }

   @Override
   public void addSale(MyDate startDate, MyDate endDate,
         ViewModelProduct product, int amount)
   {
      {
         Product sampleProduct = new Product(product.getIdProperty().get(),
               product.getNameProperty().get(),
               product.getPriceProperty().get(),
               product.getColourProperty().get(),
               product.getTypeProperty().get());
         Sale sale = new Sale(IDGenerator.generateSaleID(sales), startDate,
               endDate, sampleProduct, amount);

         try
         {
            client.sendSaleToServer(sale);
         }
         catch (RemoteException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   @Override
   public void removeSale(int ID, MyDate startDate, MyDate endDate,
         ViewModelProduct product, int amount) throws RemoteException
   {

      Product sampleProduct = new Product(product.getIdProperty().get(),
            product.getNameProperty().get(), product.getPriceProperty().get(),
            product.getColourProperty().get(), product.getTypeProperty().get());
      Sale sale = new Sale(ID, startDate, endDate, sampleProduct, amount);
      System.out.println(
            "Removing Sale with product " + sale.getProduct().getName());
      client.removeSaleFromServer(sale);
   }

   @Override
   public void getSalesFromServer(ArrayList<Sale> sale)
   {
      this.sales = sale;
      support.firePropertyChange("SALESLIST", "", sale);

   }

   @Override
   public void addSaleFromServer(Sale sale)
   {
      sales.add(sale);
      support.firePropertyChange("NEWSALE", "", sale);
   }

   @Override
   public void removeSaleFromServer(Sale sale)
   {
      sales.remove(sale);
      support.firePropertyChange("MINUSSALE", "", sale);
   }

}
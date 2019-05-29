package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;
import client.Client;
import client.IClient;
import javafx.beans.property.ObjectProperty;
import viewmodel.ViewModelProduct;
import viewmodel.ViewModelSale;

public class Store implements Serializable, StoreModel
{
   private ArrayList<Product> products;
   private ArrayList<Offer> offers;
   private IClient client;
   private PropertyChangeSupport support = new PropertyChangeSupport(this);
   private ArrayList<Sale> sales;
   private ArrayList<Review> reviews;
   private ArrayList<String> usernames;
   private ArrayList<String> passwords;

   public Store()
   {
      products = new ArrayList<Product>();
      offers = new ArrayList<Offer>();
      sales = new ArrayList<Sale>();
      reviews = new ArrayList<Review>();
      usernames = new ArrayList<String>();
      passwords = new ArrayList<String>();
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
               client.sendOfferToServer(offer);
            }
            catch (RemoteException e)
            {
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

   public ArrayList<Review> getReviews()
   {
      return reviews;
   }

   @Override
   public void removeOffer(int id, String name, String phone, String email,
         String message) throws RemoteException
   {
      Offer offer = new Offer(id, name, phone, email, message);
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
         ViewModelProduct product, int amount) throws RemoteException
   {

      Product sampleProduct = new Product(product.getIdProperty().get(),
            product.getNameProperty().get(), product.getPriceProperty().get(),
            product.getColourProperty().get(), product.getTypeProperty().get(),
            "");
      Sale sale = new Sale(IDGenerator.generateSaleID(sales), startDate,
            endDate, sampleProduct, amount);

      if (sale.validDate())
      {
         int ok = 0;
         for (Sale element : sales)
         {
            if (element.overridesOtherSales(sale))
            {
               ok = 1;
               break;
            }
         }
         if (ok == 1)
         {
            support.firePropertyChange("INVALIDDATE", "",
                  "There is a sale on the specified dates");
         }
         else
         {
            support.firePropertyChange("VALIDDATE", null, "");
            client.sendSaleToServer(sale);
         }
      }
      else
      {
         support.firePropertyChange("INVALIDDATE", "", "Invalid date");
      }

   }

   @Override
   public void removeSale(ObjectProperty<ViewModelSale> sale)
         throws RemoteException
   {
      Product sampleProduct = products.stream()
            .filter(product -> product.getID() == sale.get()
                  .getProductProperty().get().getIdProperty().get())
            .findFirst().get();

      Sale sampleSale = new Sale(sale.get().getIDProperty().get(),
            sale.get().getStartDateProperty().get(),
            sale.get().getEndDateProperty().get(), sampleProduct,
            sale.get().getAmountProperty().get(),
            sale.get().getBooleanProperty().get());

      if (sampleSale.getIsChangedValue())
      {
         sampleSale.getProduct().setPrice(sampleSale.getInitialPrice());
      }

      client.removeSaleFromServer(sampleSale);
   }

   @Override
   public void getSalesFromServer(ArrayList<Sale> sales)
   {
      this.sales = sales;
      support.firePropertyChange("SALESLIST", "", sales);

      for (Sale element : sales)
      {
         if (element.getIsChangedValue())
         {
            support.firePropertyChange("SALEAVAILABLE", "", element);
         }
      }

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
      System.out.println(sale.getPrice());
      System.out.println(sales.get(0).getPrice());
      sales.remove(sale);

      if (sale.getIsChangedValue() == true)
      {
         products.stream()
               .filter(product -> product.getID() == sale.getProduct().getID())
               .findFirst().get().setPrice(sale.getPrice());
      }

      support.firePropertyChange("SALEPRODUCTPRICEREVERT", "", sale);
      support.firePropertyChange("MINUSSALE", "", sale);
   }

   @Override
   public void addAvailableSaleFromServer(Sale sale)
   {
      products.stream()
            .filter(product -> product.getID() == sale.getProduct().getID())
            .findFirst().get().setPrice(sale.getPriceAfterSaleApplied());

      support.firePropertyChange("SALEAVAILABLE", "", sale);
      support.firePropertyChange("SALEPRODUCTPRICEUPDATE", "", sale);
   }

   @Override
   public void requestReviews() throws RemoteException
   {
      client.requestReviews();

   }

   @Override
   public void addReview(Review review)
   {
      try
      {
         client.sendReviewToServer(review);
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public void removeReview(Review review) throws RemoteException
   {
      client.removeReviewFromServer(review);

   }

   @Override
   public void getReviewsFromServer(ArrayList<Review> reviews)
   {
      this.reviews = reviews;
      support.firePropertyChange("REVIEWSLIST", "", reviews);

   }

   @Override
   public void addReviewFromServer(Review review)
   {
      reviews.add(review);
      support.firePropertyChange("NEWREVIEW", "", review);

   }

   @Override
   public void removeReviewFromServer(Review review)
   {
      reviews.remove(review);
      support.firePropertyChange("MINUSREVIEW", "", review);

   }

   @Override
   public double getAverage(int productID)
   {
      double average = reviews.stream()
            .filter(review -> review.getProduct().getID() == productID)
            .mapToDouble(review -> review.getRating()).average().getAsDouble();
      Product product = products.stream()
            .filter(sampleProduct -> sampleProduct.getID() == productID)
            .findFirst().get();
      support.firePropertyChange("AVERAGERATING", product, average);
      return average;
   }

   @Override
   public ArrayList<String> getReviewCommentsByProductID(int productID)
   {
      List<String> commentsList = reviews.stream()
            .filter(review -> review.getProduct().getID() == productID)
            .map(review -> review.getMessage()).collect(Collectors.toList());
      ArrayList<String> comments = new ArrayList<String>(commentsList);
      support.firePropertyChange("COMMENTS", "", comments);
      return comments;
   }

   public void validateLogin(String username, String password)
   {
      if (username == null || username.isEmpty())
      {
         support.firePropertyChange("INVALIDLOGIN", "No credentials inputed",
               username);
      }
      else if (password == null || password.length() < 6)
      {
         support.firePropertyChange("INVALIDLOGIN",
               "Password must contain at least 6 letters", username);
      }
      else if (usernames.stream().filter(user -> user.equals(username))
            .findFirst().isPresent())
      {
         for (int i = 0; i < usernames.size(); i++)
         {
            if (usernames.get(i).equals(username)
                  && passwords.get(i).equals(password))
            {
               support.firePropertyChange("VALIDLOGIN", "administrator", "");
            }
         }
      }
      else
      {
         support.firePropertyChange("INVALIDLOGIN",
               "Invalid username or password", username);
      }
   }

   @Override
   public void getUsernamesFromServer(ArrayList<String> usernames)
   {
      this.usernames = usernames;
   }

   @Override
   public void getPasswordsFromServer(ArrayList<String> passwords)
   {
      this.passwords = passwords;
   }

   @Override
   public void requestUsernames() throws RemoteException
   {
      client.requestUsernames();
   }

   @Override
   public void requestPasswords() throws RemoteException
   {
      client.requestPasswords();
   }

}
package model;

import java.util.ArrayList;

import client.Client;
/**
 * StoreModelClientHandler defines the interface handling all actions handed to client package  
 * connecting to the server
 * @author Group 1
 * @version (Version 1.0, 06/04/2019)
 */
public interface StoreModelClientHandler
{/**
	   * Storing the value of current client 
	   * @param client instance of client to be stored
	   */
   void setClient(Client client);
   /**
	   * Storing locally all the products from server
	   * @param products products to be stored locally
	   */
   void getProductsFromServer(ArrayList<Product> products);
   /**
	   * Storing locally all the offers from server and launches and event that updates the viewmodel 
	   * @param offer offers to be stored locally
	   */
   void getOffersFromServer(ArrayList<Offer> offer);
   /**
	   * Storing locally all the sales from server and launches an event that updates the viewmodel
	   * @param sale sales to be stored locally
	   */
   void getSalesFromServer(ArrayList<Sale> sale);
   /**
	   * Storing locally an specific offer from server and launches an event that updates the viewmodel 
	   * @param offer offer to be stored locally
	   */
   void addOfferFromServer(Offer offer);
   /**
	   * Remove locally an specific offer from server and launches an event that updates the viewmodel 
	   * @param offer offer to be stored locally
	   */
   void removeOfferFromServer(Offer offer);
   /**
	   * Storing locally an specific sale from server and launches an event that updates the viewmodel 
	   * @param sale sale to be stored locally
	   */
   void addSaleFromServer(Sale sale);
   /**
	   * Remove locally an specific sale from server and launches an event that updates the viewmodel 
	   * @param sale sale to be stored locally
	   */
   void removeSaleFromServer(Sale sale);
   /**
	   * Storing locally all the reviews from server and launches an event that updates the viewmodel 
	   * @param reviews reviews to be stored locally
	   */
   void getReviewsFromServer(ArrayList<Review> reviews);
   /**
	   * Storing locally an specific review from server and launches an event that updates the viewmodel 
	   * @param review review to be stored locally
	   */
   void addReviewFromServer(Review review);
   /**
	   * Remove locally an specific review from server and launches an event that updates the viewmodel 
	   * @param review review to be stored locally
	   */
   void removeReviewCommentFromServer(Review review);
   /**
	   * Change the price of a product based on the sale and launches an event that updates the viewmodel
	   * @param sale sale to be applied to a specific product
	   */
   void addAvailableSaleFromServer(Sale sale);
   /**
	   * Storing locally all of administration usernames from server 
	   * @param usernames administration usernames to be stored locally
	   */
   void getUsernamesFromServer(ArrayList<String> usernames);
   /**
	   * Storing locally all of administration passwords from server 
	   * @param passwords administration passwords to be stored locally
	   */
   void getPasswordsFromServer(ArrayList<String> passwords);

}

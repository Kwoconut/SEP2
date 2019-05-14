package testing;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import client.Client;
import model.Offer;
import model.Store;
import model.StoreModel;
import viewmodel.ViewModelOfferList;

public class Testing_Sprint2_Day1_viewmodel_ViewModelOfferLisT {
	private ViewModelOfferList vms;
	private StoreModel model;
	private Client client;
	
	@Before
	public void init() throws RemoteException, MalformedURLException, NotBoundException
	{
		this.model = new Store();
		client = new Client(model);
		vms = new ViewModelOfferList(model);
	}
	
	@Test
	public void getOffer() 
	{
		Offer offer = new Offer("Gicu Romanciuc", "502916554432", "280166@via.dk", "hi , i would like to get teenager metal tile or 25 square meters");
		model.addOffer(offer);
		assertEquals(offer,vms.getOffer(0));
	}

}

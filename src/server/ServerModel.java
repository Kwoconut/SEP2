
package server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

import DBSConnection.DBSQuery;
import DBSConnection.OfferDatabaseHandler;
import DBSConnection.ProductDatabaseHandler;
import DBSConnection.SaleDatabaseHandler;
import DBSConnection.StoreOfferPersistence;
import DBSConnection.StoreProductPersistence;
import DBSConnection.StoreSalePersistence;
import model.Offer;
import model.Product;
import model.Sale;

public class ServerModel
{
   private ArrayList<Product> products;
   private ArrayList<Offer> offers;
   private ArrayList<Sale> sales;
   private StoreProductPersistence databaseProductAccess;
   private StoreOfferPersistence databaseOfferAccess;
   private StoreSalePersistence databaseSaleAccess;
   private DBSQuery queryHandler;

   private PropertyChangeSupport support = new PropertyChangeSupport(this);

   public ServerModel() throws SQLException
   {
      products = new ArrayList<Product>();
      offers = new ArrayList<Offer>();
      sales = new ArrayList<Sale>();
      queryHandler = new DBSQuery("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "password");
      databaseProductAccess = new ProductDatabaseHandler(queryHandler);
      loadProducts();
      databaseOfferAccess = new OfferDatabaseHandler(queryHandler);
      loadOffers();
      databaseSaleAccess = new SaleDatabaseHandler(queryHandler);
      loadSales();
      // clearProducts();
      // sampleDataForCreation();
   }

   // add data for first setup
   private void sampleDataForCreation() throws SQLException
   {
      databaseProductAccess.addProduct(new Product(1, "Metal Sheet", 8888,
            "Red", Product.TYPE_METAL_SHEET,""));
      databaseProductAccess.addProduct(new Product(2, "Click Sheet", 9999,
            "Black", Product.TYPE_CLICK_SHEET,""));
      databaseProductAccess.addProduct(new Product(3, "Plated Sheet", 10000,
            "Silver", Product.TYPE_PLATED_SHEET,""));
      databaseProductAccess.addProduct(new Product(4, "Rain System", 3333,
            "Silver", Product.TYPE_RAIN_SYSTEM,""));
      databaseProductAccess.addProduct(
            new Product(5, "Metal Tile", 666, "Red", Product.TYPE_METAL_TILE,""));
   }

   // getters for ArrayLists
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

   // loading methods
   public void loadProducts()
   {
      try
      {
         products = databaseProductAccess.loadProducts();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void loadOffers()
   {
      try
      {
         offers = databaseOfferAccess.loadOffers();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void loadSales()
   {
      try {
		sales = databaseSaleAccess.loadSales(products);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

   // adding methods
   public void addProduct(Product product)
   {
      products.add(product);
      try
      {
         databaseProductAccess.addProduct(product);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("PRODUCTADDED", null, product);
   }

   public void addOffer(Offer offer)
   {
      offers.add(offer);
      try
      {
         databaseOfferAccess.addOffer(offer);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("OFFERADDED", null, offer);
   }

   // update methods
   public void updateProduct(Product product)
   {
      try
      {
         databaseProductAccess.updateProduct(product);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("PRODUCTUPDATED", null, product);
   }

   public void updateOffer(Offer offer)
   {
      try
      {
         databaseOfferAccess.updateOffer(offer);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("OFFERUPDATED", null, offer);
   }

   // remove methods

   public void removeProduct(Product product)
   {
      products.remove(product);
      try
      {
         databaseProductAccess.removeProduct(product);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("PRODUCTREMOVED", null, product);
   }

   public void removeOffer(Offer offer)
   {
      offers.remove(offer);
      try
      {
         databaseOfferAccess.removeOffer(offer);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      support.firePropertyChange("OFFERREMOVED", null, offer);
   }

   // clear methods
   public void clearProducts()
   {
      try
      {
         databaseProductAccess.clearProducts();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void clearOffers()
   {
      try
      {
         databaseOfferAccess.clearOffers();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      support.addPropertyChangeListener(listener);
   }
public void updateRemoveSale(Sale sale) {
	int newPrice = 0;
	for(int i=0;i<products.size();i++)
	{
		if(products.get(i).getID()==sale.getProduct().getID())
		{
			int C= sale.getPrice();
			int B= sale.getAmount();
			 newPrice = (C/(100-B)*100);
			products.get(i).setPrice(newPrice);
			break;
		}
	}
    try {
		databaseSaleAccess.updateRemoveSale(newPrice,sale.getProduct().getID());
		databaseSaleAccess.removeSale(sale);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    support.firePropertyChange("SALEREMOVEUPDATED", null, sale);
 }
public void removeSale(Sale sale) {
	sales.remove(sale);
    try {
		databaseSaleAccess.removeSale(sale);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    support.firePropertyChange("SALEREMOVED", null, sale);
	
}

public void updateAddSale(Sale sale) {
	int newPrice=0;
	int product_id=0;
	for(int i=0;i<products.size();i++)
	{
		if(products.get(i).getID()==sale.getProduct().getID())
		{
			newPrice = (sale.getPrice()-sale.getPrice())/sale.getAmount();
			product_id = sale.getProduct().getID();
			products.get(i).setPrice(newPrice);
			break;
		}
	}
	for(int i=0;i<sales.size();i++)
	{
		if(sales.get(i).getID()==sale.getID())
		{
			sales.get(i).setIsChangedValue();
			break;
		}
	}
	try {
		databaseSaleAccess.updateAddSale(newPrice,product_id);
		databaseSaleAccess.changedValue(sale);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    support.firePropertyChange("SALEADDUPDATED", null, sale);
	}

public void ChangedValue(Sale sale) 
{
	for(int i=0;i<sales.size();i++)
{
	if(sales.get(i).equals(sale))
	{
		sales.get(i).setIsChangedValue();
	}
		break;
	}
	try {
		databaseSaleAccess.changedValue(sale);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    support.firePropertyChange("CHANGEDVALUE", null, sale);
	
}

public void addSale(Sale sale) {
	sales.add(sale);
    try {
    	String startDay = sale.getStartDate().getYear() +"-"+ sale.getStartDate().getMonth()+"-"+sale.getStartDate().getDay();
        String endDay = sale.getEndDate().getYear()+"-"+sale.getEndDate().getMonth()+"-"+sale.getEndDate().getDay();
		databaseSaleAccess.addSale(sale,startDay,endDay);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    support.firePropertyChange("SALEADDED", null, sale);
}
}

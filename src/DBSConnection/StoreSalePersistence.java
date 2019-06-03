package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;
import model.Sale;

public interface StoreSalePersistence
{

   void addSale(Sale sale) throws SQLException;

   void removeSale(Sale sale) throws SQLException;

   ArrayList<Sale> loadSales(ArrayList<Product> products) throws SQLException;

   void clearSales() throws SQLException;

}

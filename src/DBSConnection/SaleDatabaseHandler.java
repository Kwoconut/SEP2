package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;

import model.MyDate;
import model.Product;
import model.Sale;

public class SaleDatabaseHandler implements StoreSalePersistence{
	
	private DBSQuery query;

	public SaleDatabaseHandler(DBSQuery queryHandler) throws SQLException {
		this.query = queryHandler;
	}
	
	@Override
	public ArrayList<Sale> loadSales(ArrayList<Product> products) throws SQLException
	{
		return new ArrayList<Sale>(query.map(
	            "SELECT sale_id, start_date, end_date, amount, isChangedValue,product_id FROM Sale;",
	            statement -> {
	            }, resultSet -> {
	            	
	            	String startDate = resultSet.getString("start_date");
	            	String endDate = resultSet.getString("end_date");
	            	int product_id = resultSet.getInt("product_id");
	            	Product product = null;
	            	for(int i=0;i<products.size();i++)
	            	{
	            		if(products.get(i).getID()== resultSet.getInt("product_id"))
	            		{
	            			product = products.get(i);
	            			break;
	            		}
	            	}
	            	System.out.println("ssdass");
	            	System.out.println(product);
	               return new Sale(resultSet.getInt("sale_id"),new MyDate(startDate.charAt(8)+startDate.charAt(9),startDate.charAt(5)+startDate.charAt(6),
	               +startDate.charAt(0)+startDate.charAt(1)+startDate.charAt(2)+startDate.charAt(3)),
	            		   new MyDate(endDate.charAt(8)+endDate.charAt(9),endDate.charAt(5)+endDate.charAt(6),
	    	               +endDate.charAt(0)+endDate.charAt(1)+endDate.charAt(2)+endDate.charAt(3)),
	                     product,
	                     resultSet.getInt("amount"),
	                     resultSet.getBoolean("isChangedValue"));
	            }));
	}

	@Override
	public void addSale(Sale sale,String startDay,String endDay) throws SQLException{
		query.executeUpdate(
	            "INSERT INTO Sale (sale_id, start_date, end_date, amount, isChangedValue,product_id) VALUES (?, ?, ?, ?, ?, ?)",
	            statement -> {
	               statement.setInt(1, sale.getID());
	               statement.setString(2, startDay);
	               statement.setString(3, endDay);
	               statement.setInt(4, sale.getAmount());
	               statement.setBoolean(5, sale.getIsChangedValue());
	               statement.setInt(6, sale.getProduct().getID());
	            });
		
	}

	@Override
	public void changedValue(Sale sale) throws SQLException{
		query.executeUpdate(
	            "UPDATE Sale SET isChangedValue = ? WHERE sale_id = ?",
	            statement -> {
	               statement.setBoolean(1, sale.getIsChangedValue());
	               statement.setInt(2, sale.getID());
	            });

	}

	@Override
	public void updateAddSale(int newPrice,int product_id) throws SQLException {
		query.executeUpdate(
	            "UPDATE Product SET price = ? WHERE product_id = ?",
	            statement -> {
	               statement.setInt(1, newPrice);
	               statement.setInt(2, product_id);
	            });	
		
	}

	@Override
	public void removeSale(Sale sale) throws SQLException {
		query.executeUpdate("DELETE FROM Sale WHERE sale_id = ?",
	            statement -> {
	               statement.setInt(1, sale.getID());
	            });
	}

	@Override
	public void updateRemoveSale(int price,int product_id) throws SQLException {
		
		query.executeUpdate(
	            "UPDATE Product SET price = ? WHERE product_id = ?",
	            statement -> {
	               statement.setInt(1, price);
	               statement.setInt(2, product_id);
	            });	
	}
}

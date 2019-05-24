package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
	            	Date date = resultSet.getDate("start_date");
	            	Date date1 = resultSet.getDate("end_date");
	            	Calendar calendar = Calendar.getInstance();
	            	Calendar calendar1 = Calendar.getInstance();
	            	calendar.setTime(date);
	            	calendar1.setTime(date1);
	            	MyDate startDate = new MyDate(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR));
	            	MyDate endDate = new MyDate(calendar1.get(Calendar.DAY_OF_MONTH),calendar1.get(Calendar.MONTH)+1,calendar1.get(Calendar.YEAR));	            
	            	Product product = null;
	            	for(int i=0;i<products.size();i++)
	            	{
	            		if(products.get(i).getID()== resultSet.getInt("product_id"))
	            		{
	            			product = products.get(i);
	            			break;
	            		}
	            	}
	               return new Sale(resultSet.getInt("sale_id"),startDate,
	            		   endDate,
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
	               statement.setBoolean(1, true);
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
	
	   @Override
	   public void clearSales() throws SQLException
	   {
	      query.executeUpdate("DELETE FROM Sale", statement -> {
	      });
	   }
}

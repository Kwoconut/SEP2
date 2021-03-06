package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.MyDate;
import model.Product;
import model.Sale;
import model.UpcomingSale;

public class SaleDatabaseHandler implements StoreSalePersistence
{

   private DBSQuery query;

   public SaleDatabaseHandler(DBSQuery queryHandler) throws SQLException
   {
      this.query = queryHandler;
   }

   @Override
   public ArrayList<Sale> loadSales(ArrayList<Product> products)
         throws SQLException
   {
      return new ArrayList<Sale>(query.map(
            "SELECT sale_id, start_date, end_date, amount, product_id FROM Sale;",
            statement -> {
            }, resultSet -> {
               Date date = resultSet.getDate("start_date");
               Date date1 = resultSet.getDate("end_date");
               Calendar calendar = Calendar.getInstance();
               Calendar calendar1 = Calendar.getInstance();
               calendar.setTime(date);
               calendar1.setTime(date1);
               MyDate startDate = new MyDate(
                     calendar.get(Calendar.DAY_OF_MONTH),
                     calendar.get(Calendar.MONTH) + 1,
                     calendar.get(Calendar.YEAR));
               MyDate endDate = new MyDate(calendar1.get(Calendar.DAY_OF_MONTH),
                     calendar1.get(Calendar.MONTH) + 1,
                     calendar1.get(Calendar.YEAR));
               Product product = null;
               for (int i = 0; i < products.size(); i++)
               {
                  if (products.get(i).getID() == resultSet.getInt("product_id"))
                  {
                     product = products.get(i);
                     break;
                  }
               }
               Sale sale = new Sale(resultSet.getInt("sale_id"), startDate,
                     endDate, product, resultSet.getInt("amount"));
               sale.setState(new UpcomingSale());
               return sale;
            }));
   }

   @Override
   public void addSale(Sale sale) throws SQLException
   {
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, sale.getStartDate().getYear());
      cal.set(Calendar.MONTH, sale.getStartDate().getMonth() - 1);
      cal.set(Calendar.DAY_OF_MONTH, sale.getStartDate().getDay());

      java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());

      cal.set(Calendar.YEAR, sale.getEndDate().getYear());
      cal.set(Calendar.MONTH, sale.getEndDate().getMonth() - 1);
      cal.set(Calendar.DAY_OF_MONTH, sale.getEndDate().getDay());

      java.sql.Date date2 = new java.sql.Date(cal.getTimeInMillis());

      query.executeUpdate(
            "INSERT INTO Sale (sale_id, start_date, end_date, amount, product_id) VALUES (?, ?, ?, ?, ?);",
            statement -> {
               statement.setInt(1, sale.getID());
               statement.setDate(2, date);
               statement.setDate(3, date2);
               statement.setInt(4, sale.getAmount());
               statement.setInt(5, sale.getProduct().getID());
            });

   }

   @Override
   public void removeSale(Sale sale) throws SQLException
   {
      query.executeUpdate("DELETE FROM Sale WHERE sale_id = ?", statement -> {
         statement.setInt(1, sale.getID());
      });
   }

}

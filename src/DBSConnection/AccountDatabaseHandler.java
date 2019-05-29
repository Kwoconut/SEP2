package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDatabaseHandler implements StoreAccountPersistence
{
   private DBSQuery query;

   public AccountDatabaseHandler(DBSQuery query)
   {
      this.query = query;
   }

   @Override
   public ArrayList<String> loadUsernames() throws SQLException
   {
      return new ArrayList<String>(
            query.map("SELECT username FROM Account;", statement -> {
            }, resultSet -> {
               return new String(resultSet.getString("username"));
            }));
   }

   @Override
   public ArrayList<String> loadPasswords() throws SQLException
   {
      return new ArrayList<String>(
            query.map("SELECT password FROM Account;", statement -> {
            }, resultSet -> {
               return new String(resultSet.getString("password"));
            }));
   }

}

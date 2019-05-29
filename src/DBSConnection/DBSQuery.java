package DBSConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.FunctionalInterface;

public class DBSQuery
{
   static
   {
      try
      {
         DriverManager.registerDriver(new org.postgresql.Driver());
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
   }

   private String url;
   private String username;
   private String password;

   public DBSQuery(String url, String username, String password)
   {
      this.url = url;
      this.username = username;
      this.password = password;
   }

   private Connection getConnection() throws SQLException
   {
      Connection connection = DriverManager.getConnection(url, username,
            password);
      PreparedStatement set = connection.prepareStatement("SET SCHEMA 'sep2';");
      set.execute();
      return connection;
   }

   @FunctionalInterface
   public interface SQLConsumer
   {
      void accept(PreparedStatement statement) throws SQLException;
   }

   @FunctionalInterface
   public interface SQLFunction<T>
   {
      T apply(PreparedStatement statement) throws SQLException;
   }

   @FunctionalInterface
   public interface SQLFactory<T>
   {
      T get(ResultSet resultSet) throws SQLException;
   }

   public void executeUpdate(String sql, SQLConsumer consumer)
         throws SQLException
   {
      Connection connection = getConnection();
      try
      {
         PreparedStatement statement = connection.prepareStatement(sql);
         consumer.accept(statement);
         statement.executeUpdate();
      }
      finally
      {
         connection.close();
      }
   }

   public <T> T executeQuery(String sql, SQLFunction<T> function)
         throws SQLException
   {
      Connection connection = getConnection();
      try
      {
         PreparedStatement statement = connection.prepareStatement(sql);
         return function.apply(statement);
      }
      finally
      {
         connection.close();
      }
   }

   public <T> List<T> map(String sql, SQLConsumer consumer,
         SQLFactory<T> factory) throws SQLException
   {
      return executeQuery(sql, statement -> {
         consumer.accept(statement);
         ResultSet resultSet = statement.executeQuery();
         ArrayList<T> list = new ArrayList<T>();
         while (resultSet.next())
         {
            list.add(factory.get(resultSet));
         }
         return list;
      });

   }

}

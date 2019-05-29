package DBSConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StoreAccountPersistence
{
   
   ArrayList<String> loadUsernames() throws SQLException;
   
   ArrayList<String> loadPasswords() throws SQLException;

}

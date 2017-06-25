package bizoncloudone.com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import bizoncloudone.com.properties.PropertiesUtil;

public class DBConnection {

    private static Connection connection = null;

    public Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                HashMap<String, String> propHm = PropertiesUtil.getAllProperties(); 
                String driver = propHm.get("driver");
                String url = propHm.get("url");
                String user = propHm.get("user");
                String password = propHm.get("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
 
    public static void main(String[] args) {
    	connection = new  DBConnection().getConnection();
    	System.out.println(connection);
	}
}

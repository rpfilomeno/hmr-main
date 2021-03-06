package hmr.com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

    private static Connection connection = null;
    private static Connection connection2 = null;
    private static Connection connection3 = null;
    private static Connection connection4 = null;
    private static Connection connection5 = null;
    private static Connection connection6 = null;
    private static Connection connection7 = null;
    private static Connection connection_mssql = null;

    public Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed())
            return connection;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            //    e.printStackTrace();
            }
            return connection;
        }

    }

    public Connection getConnection2() throws SQLException {
        if (connection2 != null && !connection2.isClosed())
            return connection2;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection2 = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            //    e.printStackTrace();
            }
            return connection2;
        }

    }
    
    public Connection getConnection3() throws SQLException {
        if (connection3 != null && !connection3.isClosed())
            return connection3;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection3 = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            //    e.printStackTrace();
            }
            return connection3;
        }

    }
    
    public Connection getConnection4() throws SQLException {
        if (connection4 != null && !connection4.isClosed())
            return connection4;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection4 = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            //    e.printStackTrace();
            }
            return connection4;
        }

    }
    
    public Connection getConnection5() throws SQLException {
        if (connection5 != null && !connection5.isClosed())
            return connection5;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection5 = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            //    e.printStackTrace();
            }
            return connection5;
        }

    }
    
    public Connection getConnection6() throws SQLException {
        if (connection6 != null && !connection6.isClosed())
            return connection6;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection6 = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            //    e.printStackTrace();
            }
            return connection6;
        }

    }
    
    public Connection getConnection7() throws SQLException {
        if (connection7 != null && !connection7.isClosed())
            return connection7;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection7 = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            //    e.printStackTrace();
            }
            return connection7;
        }

    }
    
    
    public Connection getConnection_MSSQL() throws SQLException {
        if (connection_mssql != null && !connection_mssql.isClosed())
            return connection_mssql;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db_mssql.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                
                
                System.out.println("driver "+driver);
                System.out.println("url "+url);
                System.out.println("user "+user);
                System.out.println("password "+password);
                
                Class.forName(driver);
                connection_mssql = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            //    e.printStackTrace();
            }
            return connection_mssql;
        }

    }
/*
    public Connection getConnection() {
    	Connection connection = null;
        Context ctx = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/bizadmin_gli");
            connection = ds.getConnection();
             
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    	return  connection;
    }
    */
    public static void main(String[] args) throws NamingException, SQLException {
    	Connection connection = new  DBConnection().getConnection();
    	System.out.println(connection);
	}
}
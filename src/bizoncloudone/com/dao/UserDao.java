package bizoncloudone.com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bizoncloudone.com.bean.User;
import bizoncloudone.com.util.DBConnection;

public class UserDao extends DBConnection {
	
	private Connection conn = null;
	DBConnection dbConn = null;
	private Integer id = null;
	private String email_add = null;
	
	public UserDao(){
		dbConn = new DBConnection();
	}
	
	public UserDao(Integer id, String email_add){
		this.id = id;
		this.email_add = email_add;
		dbConn = new DBConnection();
	}

	public List<User> getUserList(){

		String email_add = this.email_add;
		
		//if(email_add!=null){
		//	email_add = email_add.replaceAll("@flyacecorp.com", "");
		//}
		
		List<User> uList = new ArrayList<User>();
		
		String sql = "SELECT id, email_add, active from user where 1=1";
		
		if(id!=null){
			sql = sql + " and id = "+id;
		}
		
		if(email_add!=null){
			sql = sql + " and email_add = '"+email_add+"'";
		}

		try {
			//Connection conn = getConnection();
			conn = getConnection();
			 /*
			if(conn==null || conn.isClosed()){
				conn = dbConn.getConnection();
			}
*/
			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			User u = null;

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_add(rs.getString("email_add"));
				u.setActive(rs.getBoolean("active"));
				uList.add(u);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return uList;
	}
	
	
	public List<User> getNewUserList(){


		
		List<User> uList = new ArrayList<User>();
		
		String sql = "SELECT id, email_add, active, first_name, last_name from user_login where status = 'New'";
		


		try {
			//Connection conn = getConnection();
			conn = getConnection();
			 /*
			if(conn==null || conn.isClosed()){
				conn = dbConn.getConnection();
			}
*/
			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			User u = null;

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_add(rs.getString("email_add"));
				u.setActive(rs.getBoolean("active"));
				u.setFirst_name(rs.getString("first_name"));
				u.setLast_name(rs.getString("last_name"));
				
				uList.add(u);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return uList;
	}
	
	public List<User> getActiveUserList(){

		String email_add = this.email_add;
		
		//if(email_add!=null){
		//	email_add = email_add.replaceAll("@flyacecorp.com", "");
		//}
		
		List<User> uList = new ArrayList<User>();
		
		String sql = "SELECT id, email_add, active from user where active=1";
		
		if(id!=null){
			sql = sql + " and id = "+id;
		}
		
		if(email_add!=null){
			sql = sql + " and email_add = '"+email_add+"'";
		}

		try {
			//Connection conn = getConnection();
			 conn = getConnection();
			
			 /*
			if(conn==null || conn.isClosed()){
				conn = dbConn.getConnection();
			}
*/

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			User u = null;

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_add(rs.getString("email_add"));
				u.setActive(rs.getBoolean("active"));
				uList.add(u);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return uList;
	}
	
	public HashMap<Integer,User> getUserHM(){

		//String email_add = this.email_add;
		
		//if(email_add!=null){
		//	email_add = email_add.replaceAll("@flyacecorp.com", "");
		//}
		
		HashMap<Integer,User> uHM = new HashMap<Integer,User>();
		
		String sql = "SELECT id, email_add, active from user";
		
	

		try {
			//Connection conn = getConnection();
			 conn = getConnection();
			 /*
			if(conn==null || conn.isClosed()){
				conn = dbConn.getConnection();
			}
*/

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			User u = null;

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_add(rs.getString("email_add"));
				u.setActive(rs.getBoolean("active"));
				uHM.put(u.getId(),u);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return uHM;
	}
	
	public int insertUser(String email_add, Boolean active){
		
		int i = 0;
		

		
		try {
			//Connection conn = getConnection();
			 conn = getConnection();
			 //if(conn==null || conn.isClosed()){
			//	conn = dbConn.getConnection();
			//}

			java.sql.Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();
		      
		      String sql = "INSERT INTO user (email_add, active, date_created) " +
		                   "VALUES ('"+email_add+"', "+ active+", now())";
		      
		      
		      System.out.println("sql : "+sql);
		      i = stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}

		return i;
		
	}
	
	public int updateUser(String email_add,
	Boolean active,
	Integer id){
		
		int i = 0;
		

		
		try {
			//Connection conn = getConnection();
			 conn = getConnection();
			 /*
			if(conn==null || conn.isClosed()){
				conn = dbConn.getConnection();
			}
*/

			java.sql.Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();
		      
		      String sql = "update user SET email_add = '"+email_add+"', active="+ active+", date_updated=now() where id="+id;

		      System.out.println("sql : "+sql);
		      i = stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}

		return i;
		
	}
	
	
	public int updateUserStatus(String status , Integer id){
		
		int i = 0;
		

		
		try {
			//Connection conn = getConnection();
			 conn = getConnection();
			 /*
			if(conn==null || conn.isClosed()){
				conn = dbConn.getConnection();
			}
*/

			java.sql.Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();
		      
		      String sql = "update user_login SET status = '"+status+"', date_updated=now() where id="+id;

		      System.out.println("sql : "+sql);
		      i = stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}

		return i;
		
	}
	
	
	public User getUser(String email_add){
		
		//if(email_add!=null){
		//	email_add = email_add.replaceAll("@flyacecorp.com", "");
		//}
		

		User u = null;
		
		String sql = "SELECT id, email_add from user where ";

		if(email_add!=null){
			sql = sql + "  email_add = '"+email_add+"'";
		}

		try {
			//Connection conn = getConnection();
			 conn = getConnection();
			 /*
			if(conn==null || conn.isClosed()){
				conn = dbConn.getConnection();
			}
*/

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_add(rs.getString("email_add"));
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return u;
	}	
	
	public User isUserExist(String email_add, String pass_word){
		
		//if(email_add!=null){
		//	email_add = email_add.replaceAll("@flyacecorp.com", "");
		//}
		

		User u = null;
		
		String sql = "SELECT id, email_add from user where ";

		//if(email_add!=null){
			sql = sql + "  email_add = '"+email_add+"' and pass_word = '"+pass_word+"'";
		//}

		try {
			//Connection conn = getConnection();
			 conn = getConnection();
			 /*
			if(conn==null || conn.isClosed()){
				conn = dbConn.getConnection();
			}
*/

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_add(rs.getString("email_add"));
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return u;
	}	
	
    public static void main(String[] args) {
    	UserDao ud = new UserDao(1, null);
    	List<User> uList = ud.getUserList();
    	System.out.println("User Size : "+uList.size());
    	for(User u : uList){
    		System.out.println(u.getId() + ", " + u.getEmail_add());
    	}
	}
	
}

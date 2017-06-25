package bizoncloudone.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import bizoncloudone.com.bean.UserLogin;
import bizoncloudone.com.bean.UserRoleLogin;
import bizoncloudone.com.util.DBConnection;

public class UserRoleLoginDao extends DBConnection {
	
	private Connection conn = null;
	DBConnection dbConn = null;
	private Integer id = null;
	private String email_add = null;
	
	public UserRoleLoginDao(){
		dbConn = new DBConnection();
	}
	
	public UserRoleLoginDao(Integer id, String email_add){
		this.id = id;
		this.email_add = email_add;
		dbConn = new DBConnection();
	}

	
	public int insertUserLogin(String email_add,
	String pass_word,
	Boolean active){
		
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
		      
		      String sql = "INSERT INTO user_login (email_add, pass_word, active, date_created) " +
		                   "VALUES ('"+email_add+"', '"+pass_word+"', "+ active+", now())";
		      
		      
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
	
	
	public int updatePassword(String email_add,
	String pass_word,
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
		      
		      String sql = "update user_login SET pass_word ='"+pass_word+"', date_updated=now() where id="+id+" and email_add='"+email_add+"'";

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
	
	
	public int updateUserLogin(String email_add,
	String pass_word,
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
		      
		      String sql = "update user_login SET email_add = '"+email_add+"', pass_word ='"+pass_word+"', active="+ active+", date_updated=now() where id="+id;

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
	
	public List<UserLogin> getUserLoginList(){

		String email_add = this.email_add;
		
		//if(email_add!=null){
		//	email_add = email_add.replaceAll("@flyacecorp.com", "");
		//}
		
		List<UserLogin> uList = new ArrayList<UserLogin>();
		
		String sql = "SELECT id, email_add, pass_word, active from user_login where 1=1";
		
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

			UserLogin ul = null;

			while(rs.next()){
				ul = new UserLogin();
				ul.setId(rs.getInt("id"));
				ul.setEmail_add(rs.getString("email_add"));
				ul.setPass_word(rs.getString("pass_word"));
				ul.setActive(rs.getBoolean("active"));
				uList.add(ul);
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
	
	public UserLogin getUserLogin(String email_add){
		
		//if(email_add!=null){
		//	email_add = email_add.replaceAll("@flyacecorp.com", "");
		//}
		

		UserLogin u = null;
		
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
				u = new UserLogin();
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
	
	public int getUserLoginCount(String email_add){
		int i = 0;
		//if(email_add!=null){
		//	email_add = email_add.replaceAll("@flyacecorp.com", "");
		//}
		

		//UserLogin u = null;
		
		String sql = "SELECT count(*) as cnt from user where email_add = '"+email_add+"'";


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
				i = rs.getInt("cnt");
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
		
		return i;
	}	
	
	
	public HashMap<Integer,UserRoleLogin> getUerRoleLoginHM(){
		
		HashMap<Integer,UserRoleLogin> hm = new HashMap<Integer,UserRoleLogin>();
		
		Connection conn = null;

		UserRoleLogin url = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, user_id, role_id, date_created, date_updated from user_role_login");

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				url = new UserRoleLogin();
				url.setId(rs.getInt("id"));
				url.setUser_id(rs.getInt("user_id"));
				url.setRole_id(rs.getInt("role_id"));
				url.setDate_created(rs.getTimestamp("date_created"));
				url.setDate_updated(rs.getTimestamp("date_updated"));
				hm.put(url.getUser_id(), url);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
		}
		
		return hm;
	}	
	
	
	public UserRoleLogin getUerRoleLogin(Integer user_id){

		Connection conn = null;

		UserRoleLogin url = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, user_id, role_id, date_created, date_updated from user_role_login where user_id="+user_id);

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				url = new UserRoleLogin();
				url.setId(rs.getInt("id"));
				url.setUser_id(rs.getInt("user_id"));
				url.setRole_id(rs.getInt("role_id"));
				url.setDate_created(rs.getTimestamp("date_created"));
				url.setDate_updated(rs.getTimestamp("date_updated"));
				
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
		}
		
		return url;
	}	
	
	
	public UserRoleLogin insertUserRoleLogin(Integer user_id, Integer role_id, Integer created_by){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		UserRoleLogin url = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
		      
		    String sql = "INSERT INTO user_role_login (user_id, role_id, created_by, date_created) " +
		                   "VALUES ( ?, ?, ?, ?)";

	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        
	        stmt.setInt(1, user_id);
	        stmt.setInt(2, role_id);
	        stmt.setInt(3, created_by);
	        stmt.setDate(4, sqlDate);
		      
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	url = new UserRoleLogin(); 
	            	url.setId(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
		    
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

		return url;
	}
	
	public int updateUserLogin(Integer id, Integer role_id, Integer updated_by){
		
		Connection conn = null;
		
		int i = 0;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
		    stmt = conn.createStatement();
		      
		    String sql = "update user_role_login SET role_id = "+role_id+", updated_by ="+updated_by+", date_updated=now() where id="+id;

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
	
	public int updateUserRoleLogin(Integer id, Integer role_id, Integer updated_by){
		
		Connection conn = null;
		
		int i = 0;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
		    stmt = conn.createStatement();
		      
		    String sql = "update user_role_login SET role_id = "+role_id+", updated_by ="+updated_by+", date_updated=now() where id="+id;

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
	
	public int updateUserRoleLoginByUserId(Integer user_id, Integer role_id, Integer updated_by){
		
		Connection conn = null;
		
		int i = 0;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
		    stmt = conn.createStatement();
		      
		    String sql = "update user_role_login SET role_id = "+role_id+", updated_by ="+updated_by+", date_updated=now() where user_id="+user_id;

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
	
    public static void main(String[] args) {
    	UserRoleLoginDao ud = new UserRoleLoginDao(1, null);
    	List<UserLogin> uList = ud.getUserLoginList();
    	System.out.println("UserLogin Size : "+uList.size());
    	for(UserLogin u : uList){
    		System.out.println(u.getId() + ", " + u.getEmail_add());
    	}
	}
	
}

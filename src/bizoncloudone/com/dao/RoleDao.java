package bizoncloudone.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import bizoncloudone.com.bean.UserLogin;
import bizoncloudone.com.util.DBConnection;

public class RoleDao extends DBConnection {
	
	private Connection conn = null;
	DBConnection dbConn = null;
	private Integer id = null;
	private String email_add = null;
	
	public RoleDao(){
		dbConn = new DBConnection();
	}
	
	public RoleDao(Integer id, String email_add){
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
		
		
		List<UserLogin> uList = new ArrayList<UserLogin>();
		
		String sql = "SELECT id, email_add, pass_word, active from user_login where 1=1";
		
		if(id!=null){
			sql = sql + " and id = "+id;
		}
		
		if(email_add!=null){
			sql = sql + " and email_add = '"+email_add+"'";
		}
		
		sql = sql + " order by id";

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
	
	
	public UserLogin getUerLogin(String email_add, String pass_word){
		
		Connection conn = null;

		UserLogin ul = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_add, first_name, last_name, date_created from user_login where active=1 ");

		sb.append("and email_add = '"+email_add+"' and pass_word = '"+pass_word+"'") ;

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			//if(conn==null || conn.isClosed()){
				
			//	conn = dbConn.getConnection();
			//	System.out.println("new conn : "+conn);
			//}

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				ul = new UserLogin();
				ul.setId(rs.getInt("id"));
				ul.setEmail_add(rs.getString("email_add"));
				ul.setFirst_name(rs.getString("first_name"));
				ul.setLast_name(rs.getString("last_name"));
				ul.setDate_created(rs.getDate("date_created"));
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
		
		return ul;
	}	
	
	public ArrayList <UserLogin> getUerLoginList(){
		
		Connection conn = null;

		ArrayList <UserLogin> ulList = new ArrayList <UserLogin>();
		
		UserLogin ul = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_add, first_name, last_name, active, date_created, date_updated from user_login order by id");

		//sb.append("and email_add = '"+email_add+"' and pass_word = '"+pass_word+"'") ;

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			//if(conn==null || conn.isClosed()){
				
			//	conn = dbConn.getConnection();
			//	System.out.println("new conn : "+conn);
			//}

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				ul = new UserLogin();
				ul.setId(rs.getInt("id"));
				ul.setEmail_add(rs.getString("email_add"));
				ul.setFirst_name(rs.getString("first_name"));
				ul.setLast_name(rs.getString("last_name"));
				ul.setActive(rs.getBoolean("active"));
				ul.setDate_created(rs.getDate("date_created"));
				ul.setDate_updated(rs.getDate("date_updated"));
				ulList.add(ul);
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
		
		return ulList;
	}	
	
	
	public UserLogin getUerLogin(Integer id){
		
		Connection conn = null;
		
		UserLogin ul = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_add, pass_word, first_name, last_name, active, date_created, date_updated, updated_by, created_by from role ");

		sb.append("where id = "+id);

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				ul = new UserLogin();
				ul.setId(rs.getInt("id"));
				ul.setEmail_add(rs.getString("email_add"));
				ul.setFirst_name(rs.getString("first_name"));
				ul.setLast_name(rs.getString("last_name"));
				ul.setActive(rs.getBoolean("active"));
				ul.setDate_created(rs.getDate("date_created"));
				ul.setDate_updated(rs.getDate("date_updated"));
				ul.setPass_word(rs.getString("pass_word"));
				ul.setUpdated_by(rs.getInt("updated_by"));
				ul.setCreated_by(rs.getInt("created_by"));
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
		
		return ul;
	}	
	
	public int updateUserLogin(Integer id, String email_add, String pass_word, String first_name, String last_name, Boolean active, Integer updated_by, Integer role_id){
		
		int i = 0;
		
		try {
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			  
			String sql = "update user_login SET first_name = '"+first_name+"', last_name= '"+last_name+"', email_add = '"+email_add+"'";
			
			if(!"**********".equals(pass_word) && !"".equals(pass_word)){
				sql = sql+", pass_word ='"+pass_word+"'";
			}

			sql = sql+ ", active="+ active+", updated_by ="+updated_by+", date_updated=now() where id="+id;
			System.out.println("sql : "+sql);
			
			i = stmt.executeUpdate(sql);
			
			if(i>0){
				UserRoleLoginDao urlDao = new UserRoleLoginDao();
				
				urlDao.updateUserLogin(id, role_id, updated_by);
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

		return i;
		
	}
	
	public int updateUserLogin(Integer id, String pass_word, Integer updated_by){
		
		int i = 0;
		
		try {
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			  
			String sql = "update user_login SET pass_word = '"+pass_word+"'";


			sql = sql+ ", updated_by ="+updated_by+", date_updated=now() where id="+id;
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
	
	public UserLogin insertUserLogin(String email_add, String pass_word, String first_name, String last_name, Boolean active, Integer created_by, Integer role_id){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		UserLogin ul = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
		      
		    String sql = "INSERT INTO user_login (email_add, pass_word, first_name, last_name, active, created_by, date_created) " +
		                   "VALUES ( ?, ?, ?, ?, ?, ?, ?)";

	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        
	        stmt.setString(1, email_add);
	        stmt.setString(2, pass_word);
	        stmt.setString(3, first_name);
	        stmt.setString(4, last_name);
	        stmt.setBoolean(5, active);
	        stmt.setInt(6, created_by);
	        stmt.setDate(7, sqlDate);
		      
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	ul = new UserLogin(); 
	            	ul.setId(generatedKeys.getInt(1));
	            	
	            	UserRoleLoginDao urlDao = new UserRoleLoginDao();
	            	
	            	urlDao.insertUserRoleLogin(ul.getId(), role_id, created_by);
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

		return ul;
	}
	
	public UserLogin getUserLogin(String email_add){
		
		Connection conn = null;
		
		UserLogin ul = null;
		
		String sql = "SELECT id, email_add from user_login where ";

			sql = sql + "email_add = '"+email_add+"'";
		
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				ul = new UserLogin();
				ul.setId(rs.getInt("id"));
				ul.setEmail_add(rs.getString("email_add"));
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
		
		return ul;
	}	
	
	public HashMap<Integer,UserLogin> getUserLoginHM(){
		
		Connection conn = null;

		HashMap<Integer,UserLogin> ulHM = new HashMap<Integer,UserLogin>();
		
		UserLogin ul = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_add, first_name, last_name, active, date_created, date_updated from user_login order by last_name");

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				ul = new UserLogin();
				ul.setId(rs.getInt("id"));
				ul.setEmail_add(rs.getString("email_add"));
				ul.setFirst_name(rs.getString("first_name"));
				ul.setLast_name(rs.getString("last_name"));
				ul.setActive(rs.getBoolean("active"));
				ul.setDate_created(rs.getDate("date_created"));
				ul.setDate_updated(rs.getDate("date_updated"));
				ulHM.put(ul.getId(),ul);
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
		
		return ulHM;
	}	
	
    public static void main(String[] args) {
    	RoleDao ud = new RoleDao(1, null);
    	List<UserLogin> uList = ud.getUserLoginList();
    	System.out.println("UserLogin Size : "+uList.size());
    	for(UserLogin u : uList){
    		System.out.println(u.getId() + ", " + u.getEmail_add());
    	}
	}
	
}

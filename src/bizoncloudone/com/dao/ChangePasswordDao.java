package bizoncloudone.com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bizoncloudone.com.bean.ChangePassword;
import bizoncloudone.com.bean.User;
import bizoncloudone.com.util.DBConnection;
import bizoncloudone.com.util.StringUtils;

public class ChangePasswordDao extends DBConnection {
	
	private Connection conn = null;
	DBConnection dbConn = null;
	private Integer id = null;
	private String user_id = null;
	
	public ChangePasswordDao(){
		dbConn = new DBConnection();
	}
	
	public ChangePasswordDao(Integer id, String user_id){
		this.id = id;
		this.user_id = user_id;
		dbConn = new DBConnection();
	}
	
	public int insertChangePassword(Integer user_id, String status){
		
		int i = 0;
		
		StringUtils su = new StringUtils();
		String key_id = su.generateRandomKey();
		
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
		      
		      String sql = "INSERT INTO change_password (user_id, status, key_id, date_created) " +
		                   "VALUES ('"+user_id+"','"+status+"','"+key_id+"', now() )";
		      
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
	
	public HashMap <String,String> insertChangePasswordHM(Integer user_id, String status){
		HashMap <String,String> hm = new HashMap <String,String>();
		
		int i = 0;
		
		StringUtils su = new StringUtils();
		String key_id = su.generateRandomKey();
		
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
		      
		      String sql = "INSERT INTO change_password (user_id, status, key_id, date_created) " +
		                   "VALUES ('"+user_id+"','"+status+"','"+key_id+"', now() )";
		      
		      System.out.println("sql : "+sql);
		      i = stmt.executeUpdate(sql);
		      hm.put("ra", String.valueOf(i) );
		      hm.put("key_id", key_id);
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

		return hm;
		
	}
	
	public int isChangePasswordWaitingForApprovalExist(String id){
		
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
		      
		      String sql = "SELECT count(*) as cnt from change_password where id = "+id+" and status='Waiting For Approval'";
		      
		      System.out.println("sql : "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				
				//User u = null;
				
				while(rs.next()){
					i = rs.getInt("cnt");
				}
			rs.close();
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
	
	public int isChangePasswordExist(String email_add){
		
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
		      
		      String sql = "SELECT count(*) as cnt from user_token where email_add = '"+email_add+"'";
		      
		      System.out.println("sql : "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				
				//User u = null;
				
				while(rs.next()){
					i = rs.getInt("cnt");
				}
			rs.close();
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
	
	
	public int updateChangePassword(String key_id, String id, String status){
		
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
		      
		      String sql = "UPDATE change_password " +
		                   "SET status ='"+status+"',  " +
		                   "date_updated = now() " +
		      			   "where key_id = '"+key_id+"' and id="+id+" and status = 'Waiting For Approval'";
		      
		      
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

	
	public int updateChangePassword(String access_token, String email_add){
		
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
		      
		      String sql = "UPDATE user_token " +
		                   "SET access_token ='"+access_token+"',  " +
		      			   "date_updated = now() " +
		      			   "where email_add = '"+email_add+"'";
		      
		      
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

	public List<ChangePassword> getChangePasswordList(){

		List<ChangePassword> cpList = new ArrayList<ChangePassword>();
		
		String sql = "SELECT cp.id, cp.user_id, u.email_add, cp.status, cp.date_created, cp.date_updated, cp.key_id from change_password cp, user u where cp.user_id = u.id";
		
		if(id!=null){
			sql = sql + " and cp.id = "+id;
		}
		
		if(user_id!=null){
			sql = sql + " and cp.user_id = '"+user_id+"'";
		}
		
		sql = sql + " order by cp.date_updated desc";

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

			ChangePassword cp = null;

			while(rs.next()){
				cp = new ChangePassword();
				cp.setId(rs.getInt("id"));
				cp.setUser_id(rs.getInt("user_id"));
				cp.setKey_id(rs.getString("key_id") );
				cp.setEmail_add(rs.getString("email_add"));
				cp.setStatus(rs.getString("status"));
				
				cp.setDate_created(rs.getTimestamp("date_created"));
				cp.setDate_updated(rs.getTimestamp("date_updated"));
				cpList.add(cp);
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
		
		return cpList;
	}
	

	public ChangePassword getChangePassword(String id){

		ChangePassword cp = null;
		
		String sql = "SELECT cp.id, cp.user_id, u.email_add, cp.status, cp.date_created, cp.date_updated, cp.key_id from change_password cp, user u where cp.user_id = u.id";
		

			sql = sql + " and cp.id = "+id;
		
		

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
				cp = new ChangePassword();
				cp.setId(rs.getInt("id"));
				cp.setUser_id(rs.getInt("user_id"));
				cp.setKey_id(rs.getString("key_id") );
				cp.setEmail_add(rs.getString("email_add"));
				cp.setStatus(rs.getString("status"));
				
				cp.setDate_created(rs.getTimestamp("date_created"));
				cp.setDate_updated(rs.getTimestamp("date_updated"));
				
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
		
		return cp;
	}
	
	public ChangePassword getChangePasswordByKeyId(String key_id){

		ChangePassword cp = null;
		
		String sql = "SELECT cp.id, cp.user_id, u.email_add, cp.status, cp.date_created, cp.date_updated, cp.key_id from change_password cp, user u where cp.user_id = u.id";
		

			sql = sql + " and cp.key_id = '"+key_id+"'";
		
		

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
				cp = new ChangePassword();
				cp.setId(rs.getInt("id"));
				cp.setUser_id(rs.getInt("user_id"));
				cp.setKey_id(rs.getString("key_id") );
				cp.setEmail_add(rs.getString("email_add"));
				cp.setStatus(rs.getString("status"));
				
				cp.setDate_created(rs.getTimestamp("date_created"));
				cp.setDate_updated(rs.getTimestamp("date_updated"));
				
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
		
		return cp;
	}
	
    public static void main(String[] args) {
    	ChangePasswordDao utd = new ChangePasswordDao(1, null);
    	List<ChangePassword> cpList = utd.getChangePasswordList();
    	System.out.println("User Size : "+cpList.size());
    	for(ChangePassword ut : cpList){
    		System.out.println(ut.getId() + ", " + ut.getUser_id());
    	}
	}
	
}

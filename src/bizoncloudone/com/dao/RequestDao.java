package bizoncloudone.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import bizoncloudone.com.bean.Request;
import bizoncloudone.com.bean.UserRoleLogin;
import bizoncloudone.com.util.DBConnection;
import bizoncloudone.com.util.StringUtils;

public class RequestDao extends DBConnection {
	
	private Connection conn = null;
	DBConnection dbConn = null;
	private Integer id = null;
	private String email_add = null;
	
	public RequestDao(){
		dbConn = new DBConnection();
	}
	
	public RequestDao(Integer id, String email_add){
		this.id = id;
		this.email_add = email_add;
		dbConn = new DBConnection();
	}

	
	public int insertRequest(String email_add,
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
		      
		      String sql = "INSERT INTO request (email_add, pass_word, active, date_created) " +
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
		      
		      String sql = "update request SET pass_word ='"+pass_word+"', date_updated=now() where id="+id+" and email_add='"+email_add+"'";

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
	
	
	public int updateRequest(Integer id, Integer user_id, Integer status, Integer updated_by){
		
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
		      
		      String sql = "update request SET status = "+status+", user_id = "+user_id+", date_updated=now(), updated_by = "+updated_by+" where id="+id;

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
	
	/*
	
	public List<Request> getRequestList(){

		String email_add = this.email_add;
		
		
		List<Request> uList = new ArrayList<Request>();
		
		String sql = "SELECT id, email_add, pass_word, active from request where 1=1";
		
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
	

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			Request r = null;

			while(rs.next()){
				r = new Request();
				r.setId(rs.getInt("id"));
				r.setEmail_add(rs.getString("email_add"));
				r.setPass_word(rs.getString("pass_word"));
				r.setActive(rs.getBoolean("active"));
				uList.add(r);
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
	
*/
	
	public int getRequestCount(String email_add){
		int i = 0;
		//if(email_add!=null){
		//	email_add = email_add.replaceAll("@flyacecorp.com", "");
		//}
		

		//Request u = null;
		
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
	
	
	public Request getRequest(String email_add, String pass_word){
		
		Connection conn = null;

		Request r = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_add, first_name, last_name, date_created from request where active=1 ");

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
				r = new Request();
				r.setId(rs.getInt("id"));
				//r.setEmail_add(rs.getString("email_add"));
				//r.setFirst_name(rs.getString("first_name"));
				//r.setLast_name(rs.getString("last_name"));
				r.setDate_created(rs.getDate("date_created"));
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
		
		return r;
	}	
	
	public ArrayList <Request> getRequestList(){
		
		Connection conn = null;

		ArrayList <Request> reqList = new ArrayList <Request>();
		
		Request r = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, user_id, status, access_key, date_created, date_updated, created_by, updated_by from request order by id");

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
				r = new Request();
				r.setId(rs.getInt("id"));
				r.setUser_id(rs.getInt("user_id"));
				r.setStatus(rs.getInt("status"));
				r.setAccess_key(rs.getString("access_key"));

				r.setDate_created(rs.getTimestamp("date_created"));
				r.setDate_updated(rs.getTimestamp("date_updated"));
				r.setUpdated_by(rs.getInt("updated_by"));
				r.setCreated_by(rs.getInt("created_by"));

				reqList.add(r);
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
		
		return reqList;
	}	
	
	
	public ArrayList <Request> getRequestListByStatus(Integer status){
		
		Connection conn = null;

		ArrayList <Request> reqList = new ArrayList <Request>();
		
		Request r = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, user_id, status, access_key, date_created, date_updated, created_by, updated_by, email_tag, sms_code, sms_msg from request where status = "+status+"  ");

		sb.append("and date_created > CURDATE() - INTERVAL 2 DAY  order by id desc ") ;

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
				r = new Request();
				r.setId(rs.getInt("id"));
				r.setUser_id(rs.getInt("user_id"));
				r.setStatus(rs.getInt("status"));
				r.setAccess_key(rs.getString("access_key"));
				r.setEmail_tag(rs.getBoolean("email_tag"));
				
				r.setSms_code(rs.getString("sms_code"));
				r.setSms_msg(rs.getString("sms_msg"));
				
				r.setDate_created(rs.getTimestamp("date_created"));
				r.setDate_updated(rs.getTimestamp("date_updated"));
				r.setUpdated_by(rs.getInt("updated_by"));
				r.setCreated_by(rs.getInt("created_by"));

				reqList.add(r);
				
				
				System.out.println("reqList "+reqList.size());
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
		
		return reqList;
	}	
	
	public ArrayList <Request> getRequestListLatest(){
		
		Connection conn = null;

		ArrayList <Request> reqList = new ArrayList <Request>();
		
		Request r = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, user_id, STATUS, access_key, date_created, date_updated, created_by, verify_code, updated_by FROM request ORDER BY date_created DESC, date_updated DESC LIMIT 7");

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				r = new Request();
				r.setId(rs.getInt("id"));
				r.setUser_id(rs.getInt("user_id"));
				r.setStatus(rs.getInt("status"));
				r.setAccess_key(rs.getString("access_key"));
				r.setVerify_code(rs.getInt("verify_code"));
				

				r.setDate_created(rs.getTimestamp("date_created"));
				r.setDate_updated(rs.getTimestamp("date_updated"));
				r.setUpdated_by(rs.getInt("updated_by"));
				r.setCreated_by(rs.getInt("created_by"));

				reqList.add(r);
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
		
		return reqList;
	}	
	
	
	public Request getRequest(Integer id){
		
		Connection conn = null;
		
		Request r = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, user_id, status, access_key, date_created, date_updated, updated_by, verify_code, sms_msg, created_by from request ");

		sb.append("where id = "+id);

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				r = new Request();
				r.setId(rs.getInt("id"));
				r.setUser_id(rs.getInt("user_id"));
				r.setStatus(rs.getInt("status"));
				r.setAccess_key(rs.getString("access_key"));
				r.setVerify_code(rs.getInt("verify_code"));
				
				r.setSms_msg(rs.getString("sms_msg"));
				
				
				r.setDate_created(rs.getDate("date_created"));
				r.setDate_updated(rs.getDate("date_updated"));
				r.setUpdated_by(rs.getInt("updated_by"));
				r.setCreated_by(rs.getInt("created_by"));
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
		
		return r;
	}	
	
	
	public Request getRequestByAccessKey(String access_key){
		
		Connection conn = null;
		
		Request r = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, user_id, status, access_key, date_created, date_updated, updated_by, created_by from request ");

		sb.append("where access_key = '"+access_key+"'");

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				r = new Request();
				r.setId(rs.getInt("id"));
				r.setUser_id(rs.getInt("user_id"));
				r.setStatus(rs.getInt("status"));
				r.setAccess_key(rs.getString("access_key"));
				
				r.setDate_created(rs.getDate("date_created"));
				r.setDate_updated(rs.getDate("date_updated"));
				r.setUpdated_by(rs.getInt("updated_by"));
				r.setCreated_by(rs.getInt("created_by"));
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
		
		return r;
	}	
	
	public int updateRequestSMS(Integer id, String sms, String cd){
		
		int i = 0;
		
		try {
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			  
			String sql = "update request SET ";
			
			if("sms_msg".equals(cd)){
				sql = sql+"sms_msg ='"+sms+"'";
			}else if("sms_code".equals(cd)){
				sql = sql+"sms_code ='"+sms+"'";
			}

			sql = sql+ " where id="+id;
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
	
	
	public int updateRequestStatus(){
		
		Connection conn = null;
		
		int i = 0;
		
		Request r = null;
		
		List<Request> rList = new ArrayList<Request>();
		
		try {
			
			
			
			StringBuilder sb = new StringBuilder("SELECT id, user_id, STATUS, access_key, date_created, date_updated, updated_by, created_by FROM request WHERE STATUS = 1 ");

			sb.append("AND date_created BETWEEN CURDATE() - INTERVAL 4 DAY AND CURDATE() ");


			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				r = new Request();
				r.setId(rs.getInt("id"));
				r.setUser_id(rs.getInt("user_id"));
				r.setStatus(rs.getInt("status"));
				r.setAccess_key(rs.getString("access_key"));
				
				r.setDate_created(rs.getDate("date_created"));
				r.setDate_updated(rs.getDate("date_updated"));
				r.setUpdated_by(rs.getInt("updated_by"));
				r.setCreated_by(rs.getInt("created_by"));
				rList.add(r);
			}

	
			
			Statement stmt1 = null;
			for(Request r1 : rList){
				
				String sql = "update request SET status = 4 where id = ";
				sql = sql + r1.getId();
				
				Connection conn1 = null;
				DBConnection dbConn1 = new DBConnection();
				conn1 = dbConn1.getConnection();
				stmt1 = conn1.createStatement();				
				stmt1.executeUpdate(sql);
			}
			
			
			//DBConnection dbConn = new DBConnection();
			
			//conn = dbConn.getConnection();
			
			//Statement stmt = conn.createStatement();
			/*
			stmt = conn.createStatement();
			  
			String sql = "update request SET ";
			
			if("sms_msg".equals(cd)){
				sql = sql+"sms_msg ='"+sms+"'";
			}else if("sms_code".equals(cd)){
				sql = sql+"sms_code ='"+sms+"'";
			}

			sql = sql+ " where id="+id;
			System.out.println("sql : "+sql);
			
			i = stmt.executeUpdate(sql);
			*/

			
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
	
	public int updateRequest(Integer id, String email_add, String pass_word, String first_name, String last_name, Boolean active, Integer updated_by, Integer role_id){
		
		int i = 0;
		
		try {
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			  
			String sql = "update request SET first_name = '"+first_name+"', last_name= '"+last_name+"', email_add = '"+email_add+"'";
			
			if(!"**********".equals(pass_word) && !"".equals(pass_word)){
				sql = sql+", pass_word ='"+pass_word+"'";
			}

			sql = sql+ ", active="+ active+", updated_by ="+updated_by+", date_updated=now() where id="+id;
			System.out.println("sql : "+sql);
			
			i = stmt.executeUpdate(sql);
			
			if(i>0){
				UserRoleLoginDao urlDao = new UserRoleLoginDao();
				
				//urlDao.updateRequest(id, role_id, updated_by);
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
	
	public int updateRequest(Integer id, Integer status, Integer updated_by){
		
		int i = 0;
		
		try {
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			  
			String sql = "update request SET status = "+status+"";


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
	
	public Request insertRequest(Integer user_id, Integer status_id, String access_key, Integer created_by, Boolean pass_word){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Request r = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Integer verify_code = Integer.valueOf(StringUtils.generateSMSVerify()) ;
		      
		    String sql = "INSERT INTO request (user_id, status, access_key, created_by, date_created, email_tag, verify_code) " +
		                   "VALUES ( ?, ?, ?, ?, ?, ?, ?)";

	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        Timestamp sqlDate = new Timestamp(Calendar.getInstance().getTime().getTime());
	        
	        stmt.setInt(1, user_id);
	        stmt.setInt(2, status_id);
	        stmt.setString(3, access_key);
	        stmt.setInt(4, created_by);
	        stmt.setTimestamp(5, sqlDate);
	        stmt.setBoolean(6, pass_word);
	        stmt.setInt(7, verify_code);
	        
		      
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating request failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	r = new Request(); 
	            	r.setId(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Creating request failed, no ID obtained.");
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

		return r;
	}
	
	public Request getRequest(String email_add){
		
		Connection conn = null;
		
		Request r = null;
		
		String sql = "SELECT id, email_add from request where ";

			sql = sql + "email_add = '"+email_add+"'";
		
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				r = new Request();
				r.setId(rs.getInt("id"));
				//r.setEmail_add(rs.getString("email_add"));
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
		
		return r;
	}	
	
	public HashMap<Integer,Request> getRequestHM(){
		
		Connection conn = null;

		HashMap<Integer,Request> ulHM = new HashMap<Integer,Request>();
		
		Request r = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_add, first_name, last_name, active, date_created, date_updated from request order by last_name");

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				r = new Request();
				r.setId(rs.getInt("id"));
				//r.setEmail_add(rs.getString("email_add"));
				//r.setFirst_name(rs.getString("first_name"));
				//r.setLast_name(rs.getString("last_name"));
				//r.setActive(rs.getBoolean("active"));
				r.setDate_created(rs.getDate("date_created"));
				r.setDate_updated(rs.getDate("date_updated"));
				ulHM.put(r.getId(),r);
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
    	RequestDao ud = new RequestDao(1, null);
    	List<Request> uList = ud.getRequestList();
    	System.out.println("Request Size : "+uList.size());
    	for(Request u : uList){
    		//System.out.println(u.getId() + ", " + u.getEmail_add());
    	}
	}
	
}

package bizoncloudone.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import bizoncloudone.com.bean.Snapshot;
import bizoncloudone.com.util.DBConnection;

public class SnapshotDao extends DBConnection {
	
	private Connection conn = null;
	DBConnection dbConn = null;
	private Integer id = null;
	private String email_add = null;
	
	public SnapshotDao(){
		dbConn = new DBConnection();
	}
	
	public SnapshotDao(Integer id, String email_add){
		this.id = id;
		this.email_add = email_add;
		dbConn = new DBConnection();
	}

	
	public int insertSnapshot(String email_add,
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
		      
		      String sql = "INSERT INTO snapshot (email_add, pass_word, active, date_created) " +
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
	
	
	
	public int updateSnapshot(){
		
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
		      
		      i = getSnapshotBookingCounts(0,-7);

		      String value = Integer.toString(i);

		      
		      String sql = "update snapshot SET value = '"+value+"' where name='booking_1_week_ago'";

		      System.out.println("sql : "+sql);
		      
		      conn = getConnection();
		      
		      stmt = conn.createStatement();
		      
		      i = stmt.executeUpdate(sql);
		      
		      
		      i = getSnapshotBookingCounts(-8,-14);

		      value = Integer.toString(i);
		      
		      sql = "update snapshot SET value = '"+value+"' where name='booking_2_weeks_ago'";

		      System.out.println("sql : "+sql);
		      
		      conn = getConnection();
		      
		      stmt = conn.createStatement();
		      
		      i = stmt.executeUpdate(sql);
		      
		      
		      i = getSnapshotBookingCounts(-15,-21);

		      value = Integer.toString(i);
		      
		      sql = "update snapshot SET value = '"+value+"' where name='booking_3_weeks_ago'";

		      System.out.println("sql : "+sql);
		      
		      conn = getConnection();
		      
		      stmt = conn.createStatement();
		      
		      i = stmt.executeUpdate(sql);
		      
		      i = getSnapshotBookingCounts(-22,-28);

		      value = Integer.toString(i);
		      
		      sql = "update snapshot SET value = '"+value+"' where name='booking_4_weeks_ago'";

		      System.out.println("sql : "+sql);
		      
		      conn = getConnection();
		      
		      stmt = conn.createStatement();
		      
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
	
	public List<Snapshot> getSnapshotList(){

		String email_add = this.email_add;
		
		
		List<Snapshot> uList = new ArrayList<Snapshot>();
		
		String sql = "SELECT name,  from snapshot where 1=1";
		
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

			Snapshot s = null;

			while(rs.next()){
				s = new Snapshot();
				/*
				s.setId(rs.getInt("id"));
				s.setEmail_add(rs.getString("email_add"));
				s.setPass_word(rs.getString("pass_word"));
				s.setActive(rs.getBoolean("active"));
				*/
				uList.add(s);
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
	

	
	public int getSnapshotCount(String email_add){
		int i = 0;
		//if(email_add!=null){
		//	email_add = email_add.replaceAll("@flyacecorp.com", "");
		//}
		

		//Snapshot u = null;
		
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
	
	
	public Snapshot getUerLogin(String email_add, String pass_word){
		
		Connection conn = null;

		Snapshot s = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_add, first_name, last_name, date_created from snapshot where active=1 ");

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
				s = new Snapshot();
				/*
				s.setId(rs.getInt("id"));
				s.setEmail_add(rs.getString("email_add"));
				s.setFirst_name(rs.getString("first_name"));
				s.setLast_name(rs.getString("last_name"));
				s.setDate_created(rs.getDate("date_created"));
			*/	
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
		
		return s;
	}	
	
	public ArrayList <Snapshot> getUerLoginList(){
		
		Connection conn = null;

		ArrayList <Snapshot> ulList = new ArrayList <Snapshot>();
		
		Snapshot s = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_add, first_name, last_name, active, date_created, date_updated from snapshot order by id");

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
				s = new Snapshot();
				/*
				s.setId(rs.getInt("id"));
				s.setEmail_add(rs.getString("email_add"));
				s.setFirst_name(rs.getString("first_name"));
				s.setLast_name(rs.getString("last_name"));
				s.setActive(rs.getBoolean("active"));
				s.setDate_created(rs.getDate("date_created"));
				s.setDate_updated(rs.getDate("date_updated"));
				ulList.add(s);
				*/
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
	
	
	public ArrayList <Snapshot> getSnapshotRequestorList(){
		
		Connection conn = null;

		ArrayList <Snapshot> ulList = new ArrayList <Snapshot>();
		
		Snapshot s = null;
		
		StringBuilder sb = new StringBuilder("SELECT s.id, s.email_add, s.first_name, s.last_name, s.active, s.date_created, s.date_updated from snapshot s, user_role_login url where s.id = url.user_id and url.role_id = 2000 order by id");

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
				s = new Snapshot();
				/*
				s.setId(rs.getInt("id"));
				s.setEmail_add(rs.getString("email_add"));
				s.setFirst_name(rs.getString("first_name"));
				s.setLast_name(rs.getString("last_name"));
				s.setActive(rs.getBoolean("active"));
				s.setDate_created(rs.getDate("date_created"));
				s.setDate_updated(rs.getDate("date_updated"));
				*/
				ulList.add(s);
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
	
	public Snapshot getUerLogin(Integer id){
		
		Connection conn = null;
		
		Snapshot s = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_add, pass_word, first_name, last_name, active, date_created, date_updated, updated_by, created_by from snapshot ");

		sb.append("where id = "+id);

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				s = new Snapshot();
				/*
				s.setId(rs.getInt("id"));
				s.setEmail_add(rs.getString("email_add"));
				s.setFirst_name(rs.getString("first_name"));
				s.setLast_name(rs.getString("last_name"));
				s.setActive(rs.getBoolean("active"));
				s.setDate_created(rs.getDate("date_created"));
				s.setDate_updated(rs.getDate("date_updated"));
				s.setPass_word(rs.getString("pass_word"));
				s.setUpdated_by(rs.getInt("updated_by"));
				s.setCreated_by(rs.getInt("created_by"));
				*/
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
		
		return s;
	}	
	
	public int updateSnapshot(Integer id, String email_add, String pass_word, String first_name, String last_name, Boolean active, Integer updated_by, Integer role_id){
		
		int i = 0;
		
		try {
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			  
			String sql = "update snapshot SET first_name = '"+first_name+"', last_name= '"+last_name+"', email_add = '"+email_add+"'";
			
			if(!"**********".equals(pass_word) && !"".equals(pass_word)){
				sql = sql+", pass_word ='"+pass_word+"'";
			}

			sql = sql+ ", active="+ active+", updated_by ="+updated_by+", date_updated=now() where id="+id;
			System.out.println("sql : "+sql);
			
			i = stmt.executeUpdate(sql);
			
			if(i>0){
				//UserRoleLoginDao urlDao = new UserRoleLoginDao();
				
				//urlDao.updateSnapshot(id, role_id, updated_by);
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
	
	public int updateSnapshot(Integer id, String pass_word, Integer updated_by){
		
		int i = 0;
		
		try {
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			  
			String sql = "update snapshot SET pass_word = '"+pass_word+"'";


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
	
	public Snapshot insertSnapshot(String email_add, String pass_word, String first_name, String last_name, Boolean active, Integer created_by, Integer role_id){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Snapshot s = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
		      
		    String sql = "INSERT INTO snapshot (email_add, pass_word, first_name, last_name, active, created_by, date_created) " +
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
	            	s = new Snapshot(); 
	            	//s.setId(generatedKeys.getInt(1));
	            	
	            	//UserRoleLoginDao urlDao = new UserRoleLoginDao();
	            	
	            	//urlDao.insertUserRoleLogin(s.getId(), role_id, created_by);
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

		return s;
	}
	
	public Snapshot getSnapshot(String email_add){
		
		Connection conn = null;
		
		Snapshot s = null;
		
		String sql = "SELECT id, email_add from snapshot where ";

			sql = sql + "email_add = '"+email_add+"'";
		
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				s = new Snapshot();
				//s.setId(rs.getInt("id"));
				//s.setEmail_add(rs.getString("email_add"));
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
		
		return s;
	}	
	
	
	public int getSnapshotBookingCounts(int days1, int days2){
		
		Connection conn = null;

		//HashMap<String,String> sHM = new HashMap<String,String>();
		
		int i = 0;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.add(Calendar.DATE, days1);
		cal2.add(Calendar.DATE, days2);
		//java.util.Date dt1 = new java.util.Date(cal.getTime().getTime());
		
		//java.util.Date dt2 = new java.util.Date(cal.getTime().getTime().);
		
		
		
		System.out.println(dateFormat.format(cal1.getTime()));
		String date1 = dateFormat.format(cal1.getTime());
		String date2 = dateFormat.format(cal2.getTime());
		
		StringBuilder sb = new StringBuilder("SELECT count(*) cnt from booking where date_created between '"+date2+" 00:00:00' and '"+date1+" 23:59:00' ");

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

		
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
				System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
		}
		
		return i;
	}	
	
	
	public HashMap<String,String> getSnapshotHM(){
		
		Connection conn = null;

		HashMap<String,String> sHM = new HashMap<String,String>();
		
		Snapshot s = null;
		
		StringBuilder sb = new StringBuilder("SELECT name, value from snapshot");

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				s = new Snapshot();
				s.setName(rs.getString("name"));
				s.setValue(rs.getString("value"));
				sHM.put(s.getName(),s.getValue());
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
		
		return sHM;
	}	
	
    public static void main(String[] args) {
    	SnapshotDao ud = new SnapshotDao(1, null);
    	List<Snapshot> uList = ud.getSnapshotList();
    	System.out.println("Snapshot Size : "+uList.size());
    	for(Snapshot u : uList){
    		//System.out.println(u.getId() + ", " + u.getEmail_add());
    	}
	}
	
}

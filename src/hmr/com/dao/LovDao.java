package hmr.com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hmr.com.bean.Lov;
import hmr.com.util.DBConnection;

public class LovDao extends DBConnection {

	public HashMap<Integer,Lov> getLovHM(){
		
		Connection conn = null;

		HashMap<Integer,Lov> lovHM = new HashMap<Integer,Lov>();
		
		Lov l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, name, value, group, description, active, order");

		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from lov");
		
		sb.append(" order by value_name");
		
		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				l = new Lov();
				l.setId(rs.getInt("id"));
				l.setName(rs.getString("name"));
				l.setValue(rs.getString("value"));
				l.setGroup(rs.getString("group"));
				l.setDescription(rs.getString("description"));
				l.setActive(rs.getBoolean("active"));
				l.setOrder(rs.getInt("order"));
				
				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lovHM.put(l.getId(),l);
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
		
		return lovHM;
	}	
	
	
	
	
	
	public HashMap<Integer,Lov> getLovHM(String group){
		
		Connection conn = null;

		HashMap<Integer,Lov> lovHM = new HashMap<Integer,Lov>();
		
		Lov l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, `name`, `value`, `group`, description, active, `order`");

		sb.append(", date_created, date_updated, created_by, updated_by");

		sb.append(" from lov");
		
		sb.append(" where `group`='"+group+"'");
		
		sb.append(" order by `name`");
		
		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				l = new Lov();
				l.setId(rs.getInt("id"));
				l.setName(rs.getString("name"));
				l.setValue(rs.getString("value"));
				l.setGroup(rs.getString("group"));
				l.setDescription(rs.getString("description"));
				l.setActive(rs.getBoolean("active"));
				l.setOrder(rs.getInt("order"));
				
				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lovHM.put(l.getId(),l);
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
		
		return lovHM;
	}
	
	
	public List<Lov> getLovList(String group){
		
		Connection conn = null;
		Statement stmt = null;
		List<Lov> lovList = new ArrayList<Lov>();
		
		Lov l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, `name`, `value`, `group`, description, active, `order`");

		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from lov");
		
		sb.append(" where `group`='"+group+"'");
		
		sb.append(" order by `name`");
		
		try {

			DBConnection dbConn = new DBConnection();
			
			if(dbConn.getConnection2()!=null && !dbConn.getConnection2().isClosed()){
				conn = dbConn.getConnection2();
			}else if(dbConn.getConnection3()!=null && !dbConn.getConnection3().isClosed()){
				conn = dbConn.getConnection3();
			}else if(dbConn.getConnection4()!=null && !dbConn.getConnection4().isClosed()){
				conn = dbConn.getConnection4();
			}else if(dbConn.getConnection5()!=null && !dbConn.getConnection5().isClosed()){
				conn = dbConn.getConnection5();
			}else if(dbConn.getConnection6()!=null && !dbConn.getConnection6().isClosed()){
				conn = dbConn.getConnection6();
			}
			
			System.out.println("conn : "+conn);
			
			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				l = new Lov();
				l.setId(rs.getInt("id"));
				l.setName(rs.getString("name"));
				l.setValue(rs.getString("value"));
				l.setGroup(rs.getString("group"));
				l.setDescription(rs.getString("description"));
				l.setActive(rs.getBoolean("active"));
				l.setOrder(rs.getInt("order"));
				
				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lovList.add(l);
			}

			//rs.close();
			//stmt.close();
		} catch (Exception e) {
			
			
			
			try {

				DBConnection dbConn = new DBConnection();
				
				if(dbConn.getConnection2()!=null && !dbConn.getConnection2().isClosed()){
					conn = dbConn.getConnection2();
				}else if(dbConn.getConnection3()!=null && !dbConn.getConnection3().isClosed()){
					conn = dbConn.getConnection3();
				}else if(dbConn.getConnection4()!=null && !dbConn.getConnection4().isClosed()){
					conn = dbConn.getConnection4();
				}else if(dbConn.getConnection5()!=null && !dbConn.getConnection5().isClosed()){
					conn = dbConn.getConnection5();
				}else if(dbConn.getConnection6()!=null && !dbConn.getConnection6().isClosed()){
					conn = dbConn.getConnection6();
				}
				
				System.out.println("conn : "+conn);
				
				stmt = conn.createStatement();

				System.out.println("sql : "+sb.toString());
				
				ResultSet rs = stmt.executeQuery(sb.toString());

				while(rs.next()){
					l = new Lov();
					l.setId(rs.getInt("id"));
					l.setName(rs.getString("name"));
					l.setValue(rs.getString("value"));
					l.setGroup(rs.getString("group"));
					l.setDescription(rs.getString("description"));
					l.setActive(rs.getBoolean("active"));
					l.setOrder(rs.getInt("order"));
					
					//SystemBean - start
					l.setDate_created(rs.getTimestamp("date_created"));
					l.setDate_updated(rs.getTimestamp("date_updated"));
					l.setCreated_by(rs.getInt("created_by"));
					l.setUpdated_by(rs.getInt("updated_by"));
					//SystemBean - end
					
					lovList.add(l);
				}

				//rs.close();
				//stmt.close();
			} catch (SQLException e2) {}
			
			
			//throw new RuntimeException(e);
		} finally {
			
			/*
			if (conn != null) {
				try {
				System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			if (stmt != null) {
				try {
				System.out.println("conn closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("conn after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		return lovList;
	}
	
	public int updateLovListValue(Integer id, String newValue, Integer user_id){
		
		int i = 0;
		
		
		
		if(user_id==null || user_id==0){
			
		}
		
		Connection conn = null;
		
		try {
			
			DBConnection dbConn = new DBConnection();

			if(dbConn.getConnection2()!=null && !dbConn.getConnection2().isClosed()){
				conn = dbConn.getConnection2();
			}else if(dbConn.getConnection3()!=null && !dbConn.getConnection3().isClosed()){
				conn = dbConn.getConnection3();
			}else if(dbConn.getConnection4()!=null && !dbConn.getConnection4().isClosed()){
				conn = dbConn.getConnection4();
			}else if(dbConn.getConnection5()!=null && !dbConn.getConnection5().isClosed()){
				conn = dbConn.getConnection5();
			}else if(dbConn.getConnection5()!=null && !dbConn.getConnection5().isClosed()){
				conn = dbConn.getConnection6();
			}
			
			
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			
			//USER-STATUS - 11 - Registered
			
			String sql = "update lov SET `value`='"+newValue+"'";
			sql = sql+ ", updated_by="+user_id+", date_updated=now() where id="+id+"";
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
	
	
	
	
	
}

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

		List<Lov> lovList = new ArrayList<Lov>();
		
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
				
				lovList.add(l);
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
		
		return lovList;
	}
	
}
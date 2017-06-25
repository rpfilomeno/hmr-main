package bizoncloudone.com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bizoncloudone.com.bean.ParamsLov;
import bizoncloudone.com.util.DBConnection;

public class ParamsLovDao extends DBConnection {

	public HashMap<Integer,ParamsLov> getParamsLovHM(){
		
		Connection conn = null;

		HashMap<Integer,ParamsLov> plHM = new HashMap<Integer,ParamsLov>();
		
		ParamsLov pl = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, key_cd, value_name, desc_remark, date_created, date_updated, created_by, updated_by");

		sb.append(" from params_lov");
		
		sb.append(" order by value_name");
		
		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				pl = new ParamsLov();
				pl.setId(rs.getInt("id"));
				pl.setKey_cd(rs.getString("key_cd"));
				pl.setValue_name(rs.getString("value_name"));
				pl.setDesc_remark(rs.getString("desc_remark"));
				plHM.put(pl.getId(),pl);
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
		
		return plHM;
	}	
	
	
	
	
	
	public HashMap<Integer,ParamsLov> getParamsLovHM(String key_cd){
		
		Connection conn = null;

		HashMap<Integer,ParamsLov> plHM = new HashMap<Integer,ParamsLov>();
		
		ParamsLov pl = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, key_cd, value_name, desc_remark, date_created, date_updated, created_by, updated_by");

		sb.append(" from params_lov");
		
		sb.append(" where key_cd='"+key_cd+"'");
		
		sb.append(" order by value_name");
		
		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				pl = new ParamsLov();
				pl.setId(rs.getInt("id"));
				pl.setKey_cd(rs.getString("key_cd"));
				pl.setValue_name(rs.getString("value_name"));
				pl.setDesc_remark(rs.getString("desc_remark"));
				plHM.put(pl.getId(),pl);
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
		
		return plHM;
	}
	
	
	public List<ParamsLov> getParamsLovList(String key_cd){
		
		Connection conn = null;

		List<ParamsLov> plList = new ArrayList<ParamsLov>();
		
		ParamsLov pl = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, key_cd, value_name, desc_remark, date_created, date_updated, created_by, updated_by");

		sb.append(" from params_lov");
		
		sb.append(" where key_cd='"+key_cd+"'");
		
		sb.append(" order by value_name");
		
		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				pl = new ParamsLov();
				pl.setId(rs.getInt("id"));
				pl.setKey_cd(rs.getString("key_cd"));
				pl.setValue_name(rs.getString("value_name"));
				pl.setDesc_remark(rs.getString("desc_remark"));
				plList.add(pl);
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
		
		return plList;
	}
	
}

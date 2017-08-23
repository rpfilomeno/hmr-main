package hmr.com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hmr.com.util.DBConnection;
import hmr.com.bean.LotRange;

public class LotRangeDao extends DBConnection {

	//private Connection conn = null;
	//DBConnection dbConn = null;

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public LotRangeDao(){
		//dbConn = new DBConnection();
	}
	


	public LotRangeDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	public LotRange getLotRangeById(BigDecimal id){

		LotRange lr = null;
		
		StringBuilder sb = new StringBuilder("Select id, lot_id, range_start, range_end, increment_amount");

		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from lot_range where id ="+id);


		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection2();
			
			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());


			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				lr = new LotRange();

				lr.setId(rs.getBigDecimal("id"));
				lr.setLot_id(rs.getBigDecimal("lot_id"));	
				lr.setRange_start(rs.getBigDecimal("range_start"));
				lr.setRange_end(rs.getBigDecimal("range_end"));
				lr.setIncrement_amount(rs.getBigDecimal("increment_amount"));

            	lr.setDate_created(rs.getTimestamp("date_created"));
            	lr.setCreated_by(rs.getInt("created_by"));
            	lr.setDate_updated(rs.getTimestamp("date_updated"));
            	lr.setUpdated_by(rs.getInt("updated_by"));
         
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
		
		return lr;
	}
	


	public LotRange insertLotRangeOnCreate(
				BigDecimal lot_id,
				BigDecimal range_start,
				BigDecimal range_end,
				BigDecimal increment_amount,
				Integer user_id
			) {

		int affectedRows = 0;
		
		LotRange ar = null;



			StringBuilder sb = new StringBuilder("INSERT into lot_range (lot_id, range_start, range_end, increment_amount");
			sb.append(", date_created, created_by)");
			sb.append(" VALUES(");
			sb.append(" ?, ?, ?, ?");
			sb.append(",?, ?");
			sb.append(")");
			

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection2();

			
		    String sql = sb.toString();
	        stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        stmt.setBigDecimal(1, lot_id);
	        stmt.setBigDecimal(2, range_start);
	        stmt.setBigDecimal(3, range_end);
	        stmt.setBigDecimal(4, increment_amount);
	        stmt.setTimestamp(5, sqlDate_t);
	        stmt.setInt(6, user_id);

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating lot range failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	ar = new LotRange(); 
	            	ar.setId(new BigDecimal(generatedKeys.getInt(1)));
	            	
	            }
	            else {
	                throw new SQLException("Creating lot range failed, no ID obtained.");
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

		return ar;
	}
	
	public int deleteLotRange(BigDecimal lotRangeId_wip) {
		Connection conn = null;
		int affectedRows = 0;
		try {
			DBConnection dbConn = new DBConnection();
			conn = dbConn.getConnection();
			Statement stmt = conn.createStatement();
			String Sql ="DELETE FROM `hmr`.`lot_range` WHERE `id`='"+lotRangeId_wip.toString()+"'";
			affectedRows = stmt.executeUpdate(Sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return affectedRows;
	}
	
	public LotRange updateLotRangeOnUpdate(
				BigDecimal lot_id,
				BigDecimal range_start,
				BigDecimal range_end,
				BigDecimal increment_amount,
				Integer user_id,
				BigDecimal lotRangeId_wip
			){
		

		int affectedRows = 0;
		
		LotRange lr = null;

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection2();

			StringBuilder sb = new StringBuilder("UPDATE lot_range SET lot_id=?, range_start=?, range_end=?, increment_amount=?");

			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+lotRangeId_wip);

			
		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        stmt = conn.prepareStatement(sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


	        stmt.setBigDecimal(1, lot_id);
	        stmt.setBigDecimal(2, range_start);
	        stmt.setBigDecimal(3, range_end);
	        stmt.setBigDecimal(4, increment_amount);
	        
	        stmt.setTimestamp(5, sqlDate_t);
	        stmt.setInt(6, user_id);


		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	lr = null;
	        }else{
	        	lr = new LotRange(); 
            	lr.setId(lotRangeId_wip);
            	
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
	
		return lr;
	}

	public List<LotRange> getLotRangeListByLotId(BigDecimal lot_id){

		List<LotRange> lrList = new ArrayList<LotRange>();
		
		StringBuilder sb = new StringBuilder("Select id, lot_id, range_start, range_end, increment_amount");

		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from lot_range where lot_id="+lot_id);
		
		sb.append(" order by range_start, range_end asc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection5();
			
			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			LotRange lr = null;

			while(rs.next()){
				lr = new LotRange();

				lr.setId(rs.getBigDecimal("id"));
				lr.setLot_id(rs.getBigDecimal("lot_id"));	
				lr.setRange_start(rs.getBigDecimal("range_start"));
				lr.setRange_end(rs.getBigDecimal("range_end"));
				lr.setIncrement_amount(rs.getBigDecimal("increment_amount"));

				//SystemBean - start
				lr.setDate_created(rs.getTimestamp("date_created"));
				lr.setDate_updated(rs.getTimestamp("date_updated"));
				lr.setCreated_by(rs.getInt("created_by"));
				lr.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lrList.add(lr);
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
		
		return lrList;
	}
	
	
	
	public LotRange getLotRangeListByLotId(BigDecimal lot_id, BigDecimal bid_amount){
		
		LotRange lr = null;
		
		StringBuilder sb = new StringBuilder("Select id, lot_id, range_start, range_end, increment_amount");

		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from lot_range where lot_id="+lot_id+" and range_start >= "+bid_amount+" and range_end <="+bid_amount);
		
		sb.append(" order by range_start, range_end asc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection5();
			
			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			

			while(rs.next()){
				lr = new LotRange();

				lr.setId(rs.getBigDecimal("id"));
				lr.setLot_id(rs.getBigDecimal("lot_id"));	
				lr.setRange_start(rs.getBigDecimal("range_start"));
				lr.setRange_end(rs.getBigDecimal("range_end"));
				lr.setIncrement_amount(rs.getBigDecimal("increment_amount"));

				//SystemBean - start
				lr.setDate_created(rs.getTimestamp("date_created"));
				lr.setCreated_by(rs.getInt("created_by"));
				lr.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				//arList.add(ar);
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
		
		return lr;
	}
	
	public LotRange getLotRangeByLotIdAndBidAmount(BigDecimal lot_id, BigDecimal bid_amount) {
		LotRange lr = null;

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection6();
			
			stmt = conn.createStatement();
		
		    String sql ="SELECT * FROM lot_range WHERE"+
		    		" lot_id = "+lot_id.toString()+
		    		" AND "+bid_amount.toString()+" >= range_start"+
		    		" AND "+bid_amount+" <= range_end;";
		    System.out.println("sql : "+sql);
		    
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()){
				lr = new LotRange();
				lr.setId(rs.getBigDecimal("id"));
				lr.setLot_id(rs.getBigDecimal("lot_id"));	
				lr.setRange_start(rs.getBigDecimal("range_start"));
				lr.setRange_end(rs.getBigDecimal("range_end"));
				lr.setIncrement_amount(rs.getBigDecimal("increment_amount"));
				lr.setDate_created(rs.getTimestamp("date_created"));
				lr.setCreated_by(rs.getInt("created_by"));
				lr.setUpdated_by(rs.getInt("updated_by"));
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
		return lr;
	}
	
}

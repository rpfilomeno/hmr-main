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
import hmr.com.bean.AuctionRange;
import hmr.com.bean.LotRange;

public class AuctionRangeDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public AuctionRangeDao(){
		dbConn = new DBConnection();
	}
	


	public AuctionRangeDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	public AuctionRange getAuctionRangeById(BigDecimal id){
		
		Connection conn = null;

		AuctionRange ar = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_id, range_start, range_end, increment_amount");

		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction_range where id ="+id);


		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);
			
			if(conn==null){
				dbConn = new DBConnection();
				conn = dbConn.getConnection();
			}
		
			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			if(stmt==null || stmt.isClosed()){
				stmt = conn.createStatement();
			}
			
			
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				ar = new AuctionRange();

				ar.setId(rs.getBigDecimal("id"));
				ar.setAuction_id(rs.getBigDecimal("auction_id"));	
				ar.setRange_start(rs.getBigDecimal("range_start"));
				ar.setRange_end(rs.getBigDecimal("range_end"));
				ar.setIncrement_amount(rs.getBigDecimal("increment_amount"));

            	ar.setDate_created(rs.getTimestamp("date_created"));
            	ar.setCreated_by(rs.getInt("created_by"));
            	ar.setDate_updated(rs.getTimestamp("date_updated"));
            	ar.setUpdated_by(rs.getInt("updated_by"));
         
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
		
		return ar;
	}

	public AuctionRange insertAuctionRangeOnCreate(
				BigDecimal auction_id,
				BigDecimal range_start,
				BigDecimal range_end,
				BigDecimal increment_amount,
				Integer user_id
			) {
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		AuctionRange ar = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			StringBuilder sb = new StringBuilder("INSERT into auction_range (auction_id, range_start, range_end, increment_amount");

			sb.append(", date_created, created_by)");
			
			sb.append(" VALUES(");
			
			sb.append(" ?, ?, ?, ?");
			sb.append(",?, ?");

			
			sb.append(")");
			
			
		    String sql = sb.toString();
	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        stmt.setBigDecimal(1, auction_id);
	        stmt.setBigDecimal(2, range_start);
	        stmt.setBigDecimal(3, range_end);
	        stmt.setBigDecimal(4, increment_amount);
	        stmt.setTimestamp(5, sqlDate_t);
	        stmt.setInt(6, user_id);

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating auction range failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	ar = new AuctionRange(); 
	            	ar.setId(new BigDecimal(generatedKeys.getInt(1)));
	            	
	            }
	            else {
	                throw new SQLException("Creating auction range failed, no ID obtained.");
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
	
	
	public AuctionRange updateAuctionRangeOnUpdate(
				BigDecimal auction_id,
				BigDecimal range_start,
				BigDecimal range_end,
				BigDecimal increment_amount,
				Integer user_id,
				BigDecimal auctionRangeId_wip
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		AuctionRange ar = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			StringBuilder sb = new StringBuilder("UPDATE auction_range SET auction_id=?, range_start=?, range_end=?, increment_amount=?");

			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+auctionRangeId_wip);

			
		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


	        stmt.setBigDecimal(1, auction_id);
	        stmt.setBigDecimal(2, range_start);
	        stmt.setBigDecimal(3, range_end);
	        stmt.setBigDecimal(4, increment_amount);
	        
	        stmt.setTimestamp(5, sqlDate_t);
	        stmt.setInt(6, user_id);


		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	ar = null;
	        }else{
	        	ar = new AuctionRange(); 
            	ar.setId(auctionRangeId_wip);
            	
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

	public List<AuctionRange> getAuctionRangeListByAuctionId(BigDecimal auction_id){

		List<AuctionRange> arList = new ArrayList<AuctionRange>();
		
		StringBuilder sb = new StringBuilder("Select id, auction_id, range_start, range_end, increment_amount");

		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction_range where auction_id="+auction_id);
		
		sb.append(" order by range_start, range_end asc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			AuctionRange ar = null;

			while(rs.next()){
				ar = new AuctionRange();

				ar.setId(rs.getBigDecimal("id"));
				ar.setAuction_id(rs.getBigDecimal("auction_id"));	
				ar.setRange_start(rs.getBigDecimal("range_start"));
				ar.setRange_end(rs.getBigDecimal("range_end"));
				ar.setIncrement_amount(rs.getBigDecimal("increment_amount"));

				//SystemBean - start
				ar.setDate_created(rs.getTimestamp("date_created"));
				ar.setDate_updated(rs.getTimestamp("date_updated"));
				ar.setCreated_by(rs.getInt("created_by"));
				ar.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				arList.add(ar);
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
		
		return arList;
	}
	
	
	
	public AuctionRange getAuctionRangeListByAuctionId(BigDecimal auction_id, BigDecimal bid_amount){

		//List<AuctionRange> arList = new ArrayList<AuctionRange>();
		
		AuctionRange ar = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_id, range_start, range_end, increment_amount");

		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction_range where auction_id="+auction_id+" and range_start >= "+bid_amount+" and range_end <="+bid_amount);
		
		sb.append(" order by range_start, range_end asc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			

			while(rs.next()){
				ar = new AuctionRange();

				ar.setId(rs.getBigDecimal("id"));
				ar.setAuction_id(rs.getBigDecimal("auction_id"));	
				ar.setRange_start(rs.getBigDecimal("range_start"));
				ar.setRange_end(rs.getBigDecimal("range_end"));
				ar.setIncrement_amount(rs.getBigDecimal("increment_amount"));

				//SystemBean - start
				ar.setDate_created(rs.getTimestamp("date_created"));
				ar.setDate_updated(rs.getTimestamp("date_updated"));
				ar.setCreated_by(rs.getInt("created_by"));
				ar.setUpdated_by(rs.getInt("updated_by"));
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
		
		return ar;
	}
	
	public AuctionRange getAuctionRangeByAuctionIdAndBidAmount(BigDecimal auction_id, BigDecimal bid_amount) {
		AuctionRange ar = null;
		if(bid_amount.compareTo(BigDecimal.ZERO) == 0) {
			bid_amount = BigDecimal.ONE;
		}
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();	
		    stmt = conn.createStatement();
		    String sql ="SELECT * FROM auction_range WHERE"+
		    		" auction_id = "+auction_id.toString()+
		    		" AND "+bid_amount.toString()+" >= range_start"+
		    		" AND "+bid_amount+" <= range_end;";
		    System.out.println("sql : "+sql);
		    
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()){
		    	ar = new AuctionRange();
		    	ar.setId(rs.getBigDecimal("id"));
		    	ar.setAuction_id(rs.getBigDecimal("auction_id"));	
		    	ar.setRange_start(rs.getBigDecimal("range_start"));
		    	ar.setRange_end(rs.getBigDecimal("range_end"));
		    	ar.setIncrement_amount(rs.getBigDecimal("increment_amount"));
		    	ar.setDate_created(rs.getTimestamp("date_created"));
		    	ar.setCreated_by(rs.getInt("created_by"));
		    	ar.setUpdated_by(rs.getInt("updated_by"));
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
		return ar;
	}
	
}

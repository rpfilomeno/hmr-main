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

import hmr.com.bean.Lot;
import hmr.com.util.DBConnection;

public class AuctionUserWatchlistDao extends DBConnection {
	private Connection conn = null;
	DBConnection dbConn = null;

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public AuctionUserWatchlistDao(){
		dbConn = new DBConnection();
	}
	

	public AuctionUserWatchlistDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public int deleteAuctionUserWatchlist(BigDecimal auction_id, BigDecimal lot_id, Integer user_id) {
		Connection conn = null;
		int affectedRows = 0;
		try {
			DBConnection dbConn = new DBConnection();
			conn = dbConn.getConnection();
			Statement stmt = conn.createStatement();
			String Sql ="DELETE FROM `auction_user_watchlist` WHERE `auction_id` = " + auction_id + " AND `lot_id` = "+lot_id+" AND `user_id` = "+ user_id;
			affectedRows = stmt.executeUpdate(Sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
		}
		return affectedRows;
	}
	
	public int insertAuctionUserWatchlist(BigDecimal auction_id, BigDecimal lot_id, Integer user_id){
		Connection conn = null;
		int affectedRows = 0;
		
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
			
			StringBuilder sb = new StringBuilder("INSERT INTO `auction_user_watchlist` (`auction_id`, `lot_id`, `user_id`, `date_created`, `date_updated`, `updated_by`, `created_by`)");
			sb.append(" VALUES ( ?, ?, ?, NOW(), NULL, NULL, NULL)");

		    String sql = sb.toString();
	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        System.out.println("sql : "+sql);
	        
	        stmt.setBigDecimal(1, auction_id);
	        stmt.setBigDecimal(2, lot_id);
	        stmt.setInt(3, user_id);
	        
		    
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating watchlist failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            }
	            else {
	                throw new SQLException("Creating lot failed, no ID obtained.");
	            }
	        }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
		}
		return affectedRows;
	}
	
	public List<BigDecimal> getAuctionUserWatchlistLotIdListByAuction(BigDecimal auction_id, Integer user_id) {
		List<BigDecimal> lotids = new ArrayList<BigDecimal>();
		
		StringBuilder sb = new StringBuilder("SELECT lot_id");
		sb.append(" FROM `auction_user_watchlist`");
		sb.append(" WHERE user_id=" + user_id);
		sb.append(" AND auction_id=" + auction_id);
		Connection conn = null;
		try {
			conn = getConnection6();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Lot l = null;

			while(rs.next()){
				lotids.add(rs.getBigDecimal("lot_id"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
		}
		
		return lotids;
				
	} 
	
	public Lot getAuctionUserWatchlistLotByUserId(Integer user_id) {
		Lot auwl =null;
		return auwl;
				
	}

}

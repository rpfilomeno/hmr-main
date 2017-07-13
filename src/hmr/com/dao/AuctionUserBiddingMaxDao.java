package hmr.com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.bean.AuctionUserBiddingMax;
import hmr.com.util.DBConnection;

public class AuctionUserBiddingMaxDao extends DBConnection {
	private Connection conn = null;
	DBConnection dbConn = null;
	
	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public AuctionUserBiddingMaxDao(){
		dbConn = new DBConnection();
	}
	
	public AuctionUserBiddingMaxDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public AuctionUserBiddingMax getAuctionUserBiddingMaxByLotIdAndBidderId(BigDecimal lot_id, Integer bidder_id) {
		AuctionUserBiddingMax au = new AuctionUserBiddingMax();
		return au;
	}
	
	public int insertAuctionUserBiddingMax(Integer lot_id, BigDecimal amount_bid, BigDecimal amount_buy, BigDecimal amount_offer, Integer qty,Integer bidder_id) {
		int i = 0;
		
		try {
			 conn = getConnection();

			  Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();
		      String sql = "INSERT INTO `auction_user_bidding_max` (`id`, `lot_id`, `qty`, `auction_id`, `date_created`, `date_updated`, `updated_by`, `created_by`, `bidder_id`,`amount_bid`,`amount_buy`,`amount_offer`) " +
		                   "VALUES (NULL, '"+lot_id.toString()+"', '"+qty.toString()+"', NULL, now(), NULL, NULL, NULL, '"+bidder_id.toString()+"','"+amount_bid.toString()+"','"+amount_buy.toString()+"','"+amount_offer+"')";

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

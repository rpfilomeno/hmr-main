package hmr.com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.bean.Auction;
import hmr.com.bean.AuctionUserBiddingMax;
import hmr.com.bean.Lot;
import hmr.com.manager.BiddingTransactionManager;
import hmr.com.manager.LotManager;
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
		
		BigDecimal auction_id = new BigDecimal("0");
		try {
		
		LotManager lMngr = new LotManager();
		Lot lot = lMngr.getLotByLotId(new BigDecimal(lot_id));
		auction_id = lot.getAuction_id();
		
		} catch(Exception ex){
			
		}
		
		int i = 0;
		
		Connection conn = null;
		try {
			DBConnection dbConn = new DBConnection();
			
			
			if(dbConn.getConnection5()!=null && !dbConn.getConnection6().isClosed()){
				conn = dbConn.getConnection5();
			}else if(dbConn.getConnection7()!=null && !dbConn.getConnection7().isClosed()){
				conn = dbConn.getConnection7();
			}

			  Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();
		      String sql = "INSERT INTO `auction_user_bidding_max` (`id`, `lot_id`, `qty`, `auction_id`, `date_created`, `date_updated`, `updated_by`, `created_by`, `bidder_id`,`amount_bid`,`amount_buy`,`amount_offer`) " +
		                   "VALUES (NULL, '"+lot_id.toString()+"', '"+qty.toString()+"', '"+auction_id+"', now(), NULL, NULL, NULL, '"+bidder_id.toString()+"','"+amount_bid.toString()+"','"+amount_buy.toString()+"','"+amount_offer+"')";

		      System.out.println("sql : "+sql);
		      i = stmt.executeUpdate(sql);
		      
			//stmt.close();
		      
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			try {
				
				
				
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
			*/
		}

		return i;		
	}
	
	
	public ArrayList<AuctionUserBiddingMax> getAuctionUserBiddingMaxList(Integer lot_id){
		
		//Date dtNow = new Date();
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//String dtToday = sdf.format(dtNow);

		ArrayList<AuctionUserBiddingMax> aubmList = new ArrayList<AuctionUserBiddingMax>();
		
		StringBuilder sb = new StringBuilder("Select `id`, `lot_id`, `qty`, `auction_id`, `bidder_id`,`amount_bid`,`amount_buy`,`amount_offer`");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction_user_bidding_max where lot_id="+lot_id);
		
		sb.append(" order by date_created asc");

		Connection conn = null;
		try {
			DBConnection dbConn = new DBConnection();
			
			
			if(dbConn.getConnection6()!=null && !dbConn.getConnection6().isClosed()){
				conn = dbConn.getConnection6();
			}else if(dbConn.getConnection7()!=null && !dbConn.getConnection7().isClosed()){
				conn = dbConn.getConnection7();
			}
		
			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			if(stmt==null || stmt.isClosed()){
				stmt = conn.createStatement();
			}else{
				
			}

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			AuctionUserBiddingMax aubm = null;

			while(rs.next()){
				aubm = new AuctionUserBiddingMax();
				aubm.setId(rs.getBigDecimal("id"));
				// `id`, ``, ``, ``, ``,``,``,``

				aubm.setLot_id(rs.getInt("lot_id"));
				aubm.setQty(rs.getInt("qty"));
				aubm.setAuction_id(rs.getBigDecimal("auction_id"));
				aubm.setBidder_id(rs.getInt("bidder_id"));
				aubm.setAmount_bid(rs.getBigDecimal("amount_bid"));
				aubm.setAmount_buy(rs.getBigDecimal("amount_buy"));
				aubm.setAmount_offer(rs.getBigDecimal("amount_offer"));
				
				//SystemBean - start
				aubm.setDate_created(rs.getTimestamp("date_created"));
				aubm.setDate_updated(rs.getTimestamp("date_updated"));
				aubm.setCreated_by(rs.getInt("created_by"));
				aubm.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				aubmList.add(aubm);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return aubmList;
	}
	
	public ArrayList<AuctionUserBiddingMax> getAuctionUserBiddingMaxListByLotIdAndUser(BigDecimal lot_id, Integer user_id){
		
		//Date dtNow = new Date();
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//String dtToday = sdf.format(dtNow);

		ArrayList<AuctionUserBiddingMax> aubmList = new ArrayList<AuctionUserBiddingMax>();
		
		StringBuilder sb = new StringBuilder("Select `id`, `lot_id`, `qty`, `auction_id`, `bidder_id`,`amount_bid`,`amount_buy`,`amount_offer`");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction_user_bidding_max where lot_id="+lot_id+" and bidder_id ="+user_id);
		
		sb.append(" order by date_created asc");

		Connection conn = null;
		try {
			DBConnection dbConn = new DBConnection();
			
			
			if(dbConn.getConnection9()!=null && !dbConn.getConnection9().isClosed()){
				conn = dbConn.getConnection9();
			}else if(dbConn.getConnection7()!=null && !dbConn.getConnection7().isClosed()){
				conn = dbConn.getConnection7();
			}
		
			java.sql.Statement stmt = conn.createStatement();

			//System.out.println("sql : "+sb.toString());
			
			if(stmt==null || stmt.isClosed()){
				stmt = conn.createStatement();
			}else{
				
			}

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			AuctionUserBiddingMax aubm = null;

			while(rs.next()){
				aubm = new AuctionUserBiddingMax();
				aubm.setId(rs.getBigDecimal("id"));
				// `id`, ``, ``, ``, ``,``,``,``

				aubm.setLot_id(rs.getInt("lot_id"));
				aubm.setQty(rs.getInt("qty"));
				aubm.setAuction_id(rs.getBigDecimal("auction_id"));
				aubm.setBidder_id(rs.getInt("bidder_id"));
				aubm.setAmount_bid(rs.getBigDecimal("amount_bid"));
				aubm.setAmount_buy(rs.getBigDecimal("amount_buy"));
				aubm.setAmount_offer(rs.getBigDecimal("amount_offer"));
				
				//SystemBean - start
				aubm.setDate_created(rs.getTimestamp("date_created"));
				aubm.setDate_updated(rs.getTimestamp("date_updated"));
				aubm.setCreated_by(rs.getInt("created_by"));
				aubm.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				aubmList.add(aubm);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			/*
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
			*/
		}
		
		return aubmList;
	}
	
	
	
	public HashMap<String, AuctionUserBiddingMax> getAuctionUserBiddingMaxHMByAuctionIdSetLotIdAndUser(BigDecimal auction_id){
		
		//Date dtNow = new Date();
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//String dtToday = sdf.format(dtNow);
		
		HashMap<String, AuctionUserBiddingMax> aubmLotUserHM = new HashMap<String, AuctionUserBiddingMax>();

		//ArrayList<AuctionUserBiddingMax> aubmList = new ArrayList<AuctionUserBiddingMax>();
		
		StringBuilder sb = new StringBuilder("Select `id`, `lot_id`, `qty`, `auction_id`, `bidder_id`,`amount_bid`,`amount_buy`,`amount_offer`");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction_user_bidding_max where auction_id="+auction_id);
		
		sb.append(" order by date_created asc");

		Connection conn = null;
		try {
			DBConnection dbConn = new DBConnection();
			
			
			if(dbConn.getConnection6()!=null && !dbConn.getConnection6().isClosed()){
				conn = dbConn.getConnection6();
			}else if(dbConn.getConnection7()!=null && !dbConn.getConnection7().isClosed()){
				conn = dbConn.getConnection7();
			}
		
			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			if(stmt==null || stmt.isClosed()){
				stmt = conn.createStatement();
			}else{
				
			}

			//System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			AuctionUserBiddingMax aubm = null;

			while(rs.next()){
				aubm = new AuctionUserBiddingMax();
				aubm.setId(rs.getBigDecimal("id"));
				// `id`, ``, ``, ``, ``,``,``,``

				aubm.setLot_id(rs.getInt("lot_id"));
				aubm.setQty(rs.getInt("qty"));
				aubm.setAuction_id(rs.getBigDecimal("auction_id"));
				aubm.setBidder_id(rs.getInt("bidder_id"));
				aubm.setAmount_bid(rs.getBigDecimal("amount_bid"));
				aubm.setAmount_buy(rs.getBigDecimal("amount_buy"));
				aubm.setAmount_offer(rs.getBigDecimal("amount_offer"));
				
				//SystemBean - start
				aubm.setDate_created(rs.getTimestamp("date_created"));
				aubm.setDate_updated(rs.getTimestamp("date_updated"));
				aubm.setCreated_by(rs.getInt("created_by"));
				aubm.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				//aubmList.add(aubm);
				aubmLotUserHM.put(aubm.getLot_id()+"_"+aubm.getBidder_id(), aubm);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return aubmLotUserHM;
	}
	
	

}

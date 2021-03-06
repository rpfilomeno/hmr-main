package hmr.com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hmr.com.util.DBConnection;
import hmr.com.bean.Lot;


public class LotDao extends DBConnection {

	//private Connection conn = null;
	//DBConnection dbConn = null;

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public LotDao(){
		//dbConn = new DBConnection();
	}
	

	public LotDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	
	public Lot getLotById(BigDecimal id){
		
		Lot l = null;
		
		StringBuilder sb = new StringBuilder("SELECT *");
		
		sb.append(" from lot where id ="+id);

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());

			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				l = new Lot();

				l.setId(rs.getBigDecimal("id"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setStarting_bid_amount(rs.getBigDecimal("starting_bid_amount"));
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));
				

            	l.setDate_created(rs.getTimestamp("date_created"));
            	l.setCreated_by(rs.getInt("created_by"));
            	l.setDate_updated(rs.getTimestamp("date_updated"));
            	l.setUpdated_by(rs.getInt("updated_by"));
            	
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		return l;
	}
	
	public Lot getLotByLotId(BigDecimal id){

		Lot l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, bid_count, weight_total");

		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time, is_available_lot, starting_bid_amount");
		
		sb.append(" from lot where lot_id ="+id);

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				l = new Lot();

				l.setId(rs.getBigDecimal("id"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));
				l.setStarting_bid_amount(rs.getBigDecimal("starting_bid_amount"));
				

            	l.setDate_created(rs.getTimestamp("date_created"));
            	l.setCreated_by(rs.getInt("created_by"));
            	l.setDate_updated(rs.getTimestamp("date_updated"));
            	l.setUpdated_by(rs.getInt("updated_by"));
            	
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			//throw new RuntimeException(e);
		} finally {
			
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			
		}
		
		return l;
	}
	
	
	public Lot getLotByAuctionId(BigDecimal auction_id){

		Lot l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time, is_available_lot, weight_total");
		
		sb.append(" from lot where auction_id ="+auction_id);

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());

			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				l = new Lot();

				l.setId(rs.getBigDecimal("id"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));
				
            	l.setDate_created(rs.getTimestamp("date_created"));
            	l.setCreated_by(rs.getInt("created_by"));
            	l.setDate_updated(rs.getTimestamp("date_updated"));
            	l.setUpdated_by(rs.getInt("updated_by"));
            	
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		return l;
	}
	
	public Lot getLotByAuctionIdAndLotNo(BigDecimal auction_id, BigDecimal lot_no) {

		Lot l = null;
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");
		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, bid_count");
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time, is_available_lot, weight_total");
		sb.append(" from lot where auction_id ="+auction_id+" and lot_no="+lot_no);
		
		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

			stmt = conn.createStatement();
			System.out.println("sql : "+sb.toString());
			

			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				l = new Lot();

				l.setId(rs.getBigDecimal("id"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));
				
            	l.setDate_created(rs.getTimestamp("date_created"));
            	try{
            		l.setCreated_by(rs.getInt("created_by"));
            	}catch(Exception ex){
            		
            	}
            	
            	l.setDate_updated(rs.getTimestamp("date_updated"));
            	l.setUpdated_by(rs.getInt("updated_by"));
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		return l;
		
	}
	
	public HashMap<BigDecimal, Lot> getLotHMByAuctionId(BigDecimal auction_id){
		
		HashMap<BigDecimal, Lot> lotHM = new HashMap<BigDecimal, Lot>();

		Lot l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, date_sync, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time, is_available_lot, weight_total");
		
		sb.append(" from lot where auction_id ="+auction_id);

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());

			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				l = new Lot();

				l.setId(rs.getBigDecimal("id"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setDate_sync(rs.getTimestamp("date_sync"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));

				l.setWeight_total(rs.getBigDecimal("weight_total"));
            	l.setDate_created(rs.getTimestamp("date_created"));
            	l.setCreated_by(rs.getInt("created_by"));
            	l.setDate_updated(rs.getTimestamp("date_updated"));
            	l.setUpdated_by(rs.getInt("updated_by"));
            	
            	lotHM.put(l.getId(), l);
            	
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		return lotHM;
	}
	
	
	public HashMap<BigDecimal, Lot> getLotHMByAuctionId_SetLotId(BigDecimal auction_id){
		
		HashMap<BigDecimal, Lot> lotHM = new HashMap<BigDecimal, Lot>();

		Lot l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, date_sync, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time, is_available_lot, weight_total");
		
		sb.append(" from lot where auction_id ="+auction_id);


		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				l = new Lot();

				l.setId(rs.getBigDecimal("id"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setDate_sync(rs.getTimestamp("date_sync"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));
				
            	l.setDate_created(rs.getTimestamp("date_created"));
            	l.setCreated_by(rs.getInt("created_by"));
            	l.setDate_updated(rs.getTimestamp("date_updated"));
            	l.setUpdated_by(rs.getInt("updated_by"));
            	
            	lotHM.put(l.getLot_id(), l);
            	
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			
			*/
		}
		
		return lotHM;
	}
	
	public Lot insertLotOnCreate(
				BigDecimal lot_no,
				BigDecimal lot_id,
				BigDecimal auction_id,
				String lot_desc,
				BigDecimal assessment_value,
				BigDecimal duties,
				BigDecimal vat,
				String unit,
				BigDecimal premium_rate,
				Integer lot_type_id,
				Integer active,
				Integer unit_qty,
				

				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action_taken,
				Integer is_buy,
				Integer is_bid,
				BigDecimal buy_price,
				Integer bidder_id,
				Integer lot_increment_time,
				
				Integer user_id

			) {
		int affectedRows = 0;
		
		Lot l = null;
		
		StringBuilder sb = new StringBuilder("INSERT into lot (lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");

		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time");
		
		sb.append(", date_created, created_by)");
		
		sb.append(" VALUES(");
		
		sb.append(" ?, ?, ?, ?");
		
		sb.append(", ?, ?, ?, ?, ?, ?, ?, ?");
		
		sb.append(", ?, ?, ?, ?, ?, ?, ?, ?");

		sb.append(", ?, ?");
		
		sb.append(")");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

		    String sql = sb.toString();
		    
	        stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        System.out.println("sql : "+sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        stmt.setBigDecimal(1, lot_no);
	        stmt.setBigDecimal(2, lot_id);
	        stmt.setBigDecimal(3, auction_id);
	        stmt.setString(4, lot_desc);
	        stmt.setBigDecimal(5, assessment_value);
	        stmt.setBigDecimal(6, duties);
	        stmt.setBigDecimal(7, vat);
	        stmt.setString(8, unit);
	        stmt.setBigDecimal(9, premium_rate);
	        stmt.setInt(10, lot_type_id);
	        stmt.setInt(11, active);
	        stmt.setInt(12, unit_qty);
	        
	        stmt.setBigDecimal(13, amount_bid);
	        stmt.setBigDecimal(14, amount_buy);
	        stmt.setInt(15, action_taken);
	        stmt.setInt(16, is_buy);
	        stmt.setInt(17, is_bid);
	        stmt.setBigDecimal(18, buy_price);
	        stmt.setInt(19, bidder_id);
	        stmt.setInt(20, lot_increment_time);

	        stmt.setTimestamp(21, sqlDate_t);
	        stmt.setInt(22, user_id);
		    
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating lot failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	l = new Lot(); 
	            	l.setId(new BigDecimal( generatedKeys.getInt(1)));
	            	
	            	//writeBlob(a.getId(),"",conn);
	            	
	            	/*
	            	a.setEmail_address(emailAddress);
	            	a.setFirst_name(firstName);
	            	a.setLast_name(lastName);
	            	a.setMobile_no_1(mobileNo1);
	            	a.setMobile_no_2(mobileNo2);
	            	
	            	a.setGender(gender);
	            	a.setRole(role);
	            	a.setBidder_no(bidderNo);
	            	a.setReserve_bidder_no(reserveBidderNo);
	            	a.setCompany(company);
	            	a.setStatus(userStatus);
	            	a.setActive(active);
	            	a.setLandline_no(landLineNo);
	            	
	            	a.setNews_letter(newsLetter);
	            	a.setNews_letter_registration_date(newsLetterRegistrationDate_t);
	            	a.setVerification_email_key(verificationEmailKey);
	            	a.setDate_registration(registrationDate_t);
	            	a.setDate_password_change(passwordChangeDate_t);
	            	a.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
	            	a.setBirth_date(birthDate_d);
	            	a.setPass_word(passWord);
	            	*/
	            	
	            }
	            else {
	                throw new SQLException("Creating lot failed, no ID obtained.");
	            }
	        }
		    
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {/*
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
		*/	
		}

		return l;
	}
	
	
	public Lot insertLotOnUpload(
			BigDecimal lot_id,
			BigDecimal auction_id,
			BigDecimal lot_number,
			Integer is_available_lot,
			String lot_description,
			Integer lot_type_id,
			BigDecimal premium_rate,
			String unit,
			Integer unit_qty,
			BigDecimal vat,
			BigDecimal duties,
			BigDecimal assessment_value,
			String lot_name,
			Timestamp last_date_sync,
			Integer user_id
		) {

	
	int affectedRows = 0;
	
	Lot l = null;
	
	StringBuilder sb = new StringBuilder("INSERT into lot (lot_id, auction_id, lot_no");
	
	sb.append(", is_available_lot, lot_desc, lot_type_id, premium_rate, unit, unit_qty");
	
	sb.append(", vat, duties, assessment_value, lot_name, date_sync");
	
	sb.append(", date_created, created_by)");

	sb.append(" VALUES(");
	
	sb.append(" ?, ?, ?");
	
	sb.append(", ?, ?, ?, ?, ?, ?");
	
	sb.append(", ?, ?, ?, ?, ?");
	
	sb.append(", ?, ?");
	
	sb.append(")");

	Connection conn = null;
	
	DBConnection dbConn = null;
	
	PreparedStatement stmt = null;
	
	try {

		dbConn = new DBConnection();
		
		conn = dbConn.getConnection4();

	    String sql = sb.toString();

        stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        System.out.println("sql : "+sql);
        
        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


		stmt.setBigDecimal(1, lot_id);
		stmt.setBigDecimal(2, auction_id);
        stmt.setBigDecimal(3, lot_number);
        
        stmt.setInt(4, is_available_lot);
        
        stmt.setString(5, lot_description);
        stmt.setInt(6, lot_type_id);
        stmt.setBigDecimal(7, premium_rate);
        stmt.setString(8, unit);
        stmt.setInt(9, unit_qty);
        stmt.setBigDecimal(10, vat);
        stmt.setBigDecimal(11, duties);
        stmt.setBigDecimal(12, assessment_value);
        stmt.setString(13, lot_name);
        stmt.setTimestamp(14, last_date_sync);
        stmt.setTimestamp(15, sqlDate_t);
        stmt.setInt(16, user_id);

	    
	    
	    affectedRows = stmt.executeUpdate();
	    
	    if (affectedRows == 0) {
            throw new SQLException("Creating lot failed, no rows affected.");
        }
	    
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            	l = new Lot(); 
            	l.setId(new BigDecimal( generatedKeys.getInt(1)));
            	
            	//writeBlob(a.getId(),"",conn);
            	
            	/*
            	a.setEmail_address(emailAddress);
            	a.setFirst_name(firstName);
            	a.setLast_name(lastName);
            	a.setMobile_no_1(mobileNo1);
            	a.setMobile_no_2(mobileNo2);
            	
            	a.setGender(gender);
            	a.setRole(role);
            	a.setBidder_no(bidderNo);
            	a.setReserve_bidder_no(reserveBidderNo);
            	a.setCompany(company);
            	a.setStatus(userStatus);
            	a.setActive(active);
            	a.setLandline_no(landLineNo);
            	
            	a.setNews_letter(newsLetter);
            	a.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	a.setVerification_email_key(verificationEmailKey);
            	a.setDate_registration(registrationDate_t);
            	a.setDate_password_change(passwordChangeDate_t);
            	a.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	a.setBirth_date(birthDate_d);
            	a.setPass_word(passWord);
            	*/
            	
            }
            else {
                throw new SQLException("Creating lot failed, no ID obtained.");
            }
        }
	    
		//stmt.close();
	} catch (SQLException e) {
		throw new RuntimeException(e);
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
			System.out.println("stmt closing : "+stmt);
			stmt.close();
			stmt = null;
			System.out.println("stmt after closing : "+stmt);
			} catch (SQLException e) {}
		}
		*/
	}

	return l;
}
	
	
	public Lot updateLotOnUpdate(
				BigDecimal lot_no,
				BigDecimal lot_id,
				BigDecimal auction_id,
				String lot_desc,
				BigDecimal assessment_value,
				BigDecimal duties,
				BigDecimal vat,
				String unit,
				BigDecimal premium_rate,
				Integer lot_type_id,
				Integer active,
				Integer unit_qty,
				
				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action_taken,
				Integer is_buy,
				Integer is_bid,
				BigDecimal buy_price,
				Integer bidder_id,
				Integer lot_increment_time,
				String lot_name,
				
				BigDecimal starting_bid_amount,

				Integer user_id,
				BigDecimal lotId_wip
			){
		
		int affectedRows = 0;
		
		Lot l = null;
		
		StringBuilder sb = new StringBuilder("Update Lot Set lot_no=?, lot_id=?, auction_id=?, lot_desc=?, assessment_value=?");

		sb.append(", duties=?, vat=?, unit=?, premium_rate=?, lot_type_id=?, active=?, unit_qty=?");
		
		sb.append(", amount_bid=?, amount_buy=?, action_taken=?, is_buy=?, is_bid=?, buy_price=?, bidder_id=?, lot_increment_time=?, lot_name=?, starting_bid_amount=?");

		sb.append(", date_updated=?, updated_by=?");
		
		sb.append(" where id="+lotId_wip);
	
		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();
			
			System.out.println("sql : "+sb.toString());

		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        stmt = conn.prepareStatement(sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	        
	        stmt.setBigDecimal(1, lot_no);
	        stmt.setBigDecimal(2, lot_id);
	        stmt.setBigDecimal(3, auction_id);
	        stmt.setString(4, lot_desc);
	        stmt.setBigDecimal(5, assessment_value);
	        stmt.setBigDecimal(6, duties);
	        stmt.setBigDecimal(7, vat);
	        stmt.setString(8, unit);
	        stmt.setBigDecimal(9, premium_rate);
	        stmt.setInt(10, lot_type_id);
	        stmt.setInt(11, active);
	        stmt.setInt(12, unit_qty);
	        
	        
	        stmt.setBigDecimal(13, amount_bid);
	        stmt.setBigDecimal(14, amount_buy);
	        stmt.setInt(15, action_taken);
	        stmt.setInt(16, is_buy);
	        stmt.setInt(17, is_bid);
	        stmt.setBigDecimal(18, buy_price);
	        stmt.setInt(19, bidder_id);
	        stmt.setInt(20, lot_increment_time);
	        stmt.setString(21, lot_name);
	        
	        stmt.setBigDecimal(22, starting_bid_amount);
	        
	        
	        
	        stmt.setTimestamp(23, sqlDate_t);
	        stmt.setInt(24, user_id);


		    //System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	l = null;
	        }else{
	        	l = new Lot(); 
            	l.setId(lotId_wip);
            	/*
            	a.setEmail_address(emailAddress);
            	a.setFirst_name(firstName);
            	a.setLast_name(lastName);
            	a.setMobile_no_1(mobileNo1);
            	a.setMobile_no_2(mobileNo2);
            	
            	a.setGender(gender);
            	a.setRole(role);
            	a.setBidder_no(bidderNo);
            	a.setReserve_bidder_no(reserveBidderNo);
            	a.setCompany(company);
            	a.setStatus(userStatus);
            	a.setActive(active);
            	a.setLandline_no(landLineNo);
            	
            	a.setNews_letter(newsLetter);
            	a.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	a.setVerification_email_key(verificationEmailKey);
            	a.setDate_registration(registrationDate_t);
            	a.setDate_password_change(passwordChangeDate_t);
            	a.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	a.setBirth_date(birthDate_d);
            	a.setPass_word(passWord);
            	*/
	        }
		    

			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			
			*/
		}
	
		return l;
	}
	
	
	public Lot updateLotOnUpload(
			BigDecimal lot_id,
			BigDecimal auction_id,
			BigDecimal lot_number,
			Integer is_available_lot,
			String lot_description,
			Integer lot_type_id,
			BigDecimal premium_rate,
			String unit,
			Integer unit_qty,
			BigDecimal vat,
			BigDecimal duties,
			BigDecimal assessment_value,
			Timestamp last_date_sync,
				Integer user_id
			){

		int affectedRows = 0;
		
		Lot l = null;
	
		StringBuilder sb = new StringBuilder("Update Lot Set lot_id=?, auction_id=?, lot_no=?");
		
		sb.append(", is_available_lot=?, lot_desc=?, lot_type_id=?, premium_rate=?, unit=?, unit_qty=?");
		
		sb.append(", vat=?, duties=?, assessment_value=?, date_sync=?");

		sb.append(", date_updated=?, updated_by=?");
		
		sb.append(" where lot_id="+lot_id);
		
		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();
			
			System.out.println("sql : "+sb.toString());
			
		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        stmt = conn.prepareStatement(sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

			stmt.setBigDecimal(1, lot_id);
			stmt.setBigDecimal(2, auction_id);
	        stmt.setBigDecimal(3, lot_number);
	        
	        stmt.setInt(4, is_available_lot);
	        
	        stmt.setString(5, lot_description);
	        stmt.setInt(6, lot_type_id);
	        stmt.setBigDecimal(7, premium_rate);
	        stmt.setString(8, unit);
	        stmt.setInt(9, unit_qty);
	        stmt.setBigDecimal(10, vat);
	        stmt.setBigDecimal(11, duties);
	        stmt.setBigDecimal(12, assessment_value);
	        stmt.setTimestamp(13, last_date_sync);

	        stmt.setTimestamp(14, sqlDate_t);
	        stmt.setInt(15, user_id);
	
	
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	l = null;
	        }else{
	        	l = new Lot(); 
	        	l.setId(lot_id);
	        	/*
	        	a.setEmail_address(emailAddress);
	        	a.setFirst_name(firstName);
	        	a.setLast_name(lastName);
	        	a.setMobile_no_1(mobileNo1);
	        	a.setMobile_no_2(mobileNo2);
	        	
	        	a.setGender(gender);
	        	a.setRole(role);
	        	a.setBidder_no(bidderNo);
	        	a.setReserve_bidder_no(reserveBidderNo);
	        	a.setCompany(company);
	        	a.setStatus(userStatus);
	        	a.setActive(active);
	        	a.setLandline_no(landLineNo);
	        	
	        	a.setNews_letter(newsLetter);
	        	a.setNews_letter_registration_date(newsLetterRegistrationDate_t);
	        	a.setVerification_email_key(verificationEmailKey);
	        	a.setDate_registration(registrationDate_t);
	        	a.setDate_password_change(passwordChangeDate_t);
	        	a.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
	        	a.setBirth_date(birthDate_d);
	        	a.setPass_word(passWord);
	        	*/
	        }
		    
	
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
	
		return l;
	}
	
	
	public Lot updateLotTotalsOnUpload(
			BigDecimal lot_id,
			BigDecimal srp,
			BigDecimal target_price,
			BigDecimal reserve_price,
			BigDecimal assess_value,
			BigDecimal weight
			){
		

		int affectedRows = 0;
		
		Lot l = null;
		
		StringBuilder sb = new StringBuilder("Update Lot Set srp_total=?, target_price_total=?, reserve_price_total=?, assess_value_total=?, weight_total=?");

		sb.append(" where lot_id="+lot_id);
	
		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();
			
			System.out.println("sql : "+sb.toString());

			Lot lot = getLotById(lot_id);
			
			conn = dbConn.getConnection5();
			
			BigDecimal srp_total = new BigDecimal(0);
			try{
				srp_total = srp.add(lot.getSrp_total());
			}catch(Exception e){}
			
			
			BigDecimal target_price_total = new BigDecimal(0);
			try{
				target_price_total = srp.add(lot.getTarget_price_total());
			}catch(Exception e){}
			
			BigDecimal reserve_price_total = new BigDecimal(0);
			try{
				reserve_price_total = srp.add(lot.getReserve_price_total());
			}catch(Exception e){}
			
			BigDecimal assess_value_total = new BigDecimal(0);
			try{
				assess_value_total = srp.add(lot.getAssess_value_total());
			}catch(Exception e){}
			
			BigDecimal weight_total = new BigDecimal(0);
			try{
				weight_total = srp.add(lot.getWeight_total());
			}catch(Exception e){}

		    String sql = sb.toString();
		    
	        stmt = conn.prepareStatement(sql);

			stmt.setBigDecimal(1, srp_total);
			stmt.setBigDecimal(2, target_price_total);
	        stmt.setBigDecimal(3, reserve_price_total);
	        stmt.setBigDecimal(4, assess_value_total);
	        stmt.setBigDecimal(5, weight_total);
	
		    //System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	l = null;
	        }else{
	        	l = new Lot(); 
	        	l.setId(lot_id);
	        	/*
	        	a.setEmail_address(emailAddress);
	        	a.setFirst_name(firstName);
	        	a.setLast_name(lastName);
	        	a.setMobile_no_1(mobileNo1);
	        	a.setMobile_no_2(mobileNo2);
	        	
	        	a.setGender(gender);
	        	a.setRole(role);
	        	a.setBidder_no(bidderNo);
	        	a.setReserve_bidder_no(reserveBidderNo);
	        	a.setCompany(company);
	        	a.setStatus(userStatus);
	        	a.setActive(active);
	        	a.setLandline_no(landLineNo);
	        	
	        	a.setNews_letter(newsLetter);
	        	a.setNews_letter_registration_date(newsLetterRegistrationDate_t);
	        	a.setVerification_email_key(verificationEmailKey);
	        	a.setDate_registration(registrationDate_t);
	        	a.setDate_password_change(passwordChangeDate_t);
	        	a.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
	        	a.setBirth_date(birthDate_d);
	        	a.setPass_word(passWord);
	        	*/
	        }
		    
	
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			
			*/
			
		}
	
		return l;
	}
	
	
	
	public List<Lot> getLotList(){

		List<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time, is_available_lot, weight_total");
		
		sb.append(" from lot");
		
		sb.append(" order by id desc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Lot l = null;

			while(rs.next()){
				l = new Lot();
				l.setId(rs.getBigDecimal("id"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			
			*/
		}
		
		return lList;
	}
	
	
	public ArrayList<Lot> getLotListByAuctionId(BigDecimal auction_id){

		ArrayList<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT *");
		
		sb.append(" from lot where auction_id= "+auction_id);
		
		sb.append(" order by lot_no asc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Lot l = null;

			while(rs.next()){
				l = new Lot();
				l.setId(rs.getBigDecimal("id"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setStarting_bid_amount(rs.getBigDecimal("starting_bid_amount"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			
			*/
		}
		
		System.out.println("getLotListByAuctionId "+lList.size());
		
		return lList;
	}
	
	public ArrayList<Lot> getActiveLotListByAuctionId(BigDecimal auction_id){

		ArrayList<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT *");
		
		sb.append(" from lot where auction_id= "+auction_id+" and active = 1");
		
		sb.append(" order by lot_no asc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection6();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Lot l = null;

			while(rs.next()){
				l = new Lot();
				l.setId(rs.getBigDecimal("id"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setStarting_bid_amount(rs.getBigDecimal("starting_bid_amount"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
			}

			//rs.close();
			//stmt.close();
		} catch (Exception e) {
			
			
			try {

				dbConn = new DBConnection();
				
				conn = dbConn.getConnection6();

				stmt = conn.createStatement();

				System.out.println("sql : "+sb.toString());
				
				ResultSet rs = stmt.executeQuery(sb.toString());

				Lot l = null;

				while(rs.next()){
					l = new Lot();
					l.setId(rs.getBigDecimal("id"));
					l.setLot_no(rs.getBigDecimal("lot_no"));
					l.setLot_name(rs.getString("lot_name"));
					l.setLot_id(rs.getBigDecimal("lot_id"));
					l.setAuction_id(rs.getBigDecimal("auction_id"));
					l.setLot_desc(rs.getString("lot_desc"));
					l.setAssessment_value(rs.getBigDecimal("assessment_value"));
					l.setDuties(rs.getBigDecimal("duties"));
					l.setVat(rs.getBigDecimal("vat"));
					l.setUnit(rs.getString("unit"));
					l.setPremium_rate(rs.getBigDecimal("premium_rate"));
					l.setLot_type_id(rs.getInt("lot_type_id"));
					l.setActive(rs.getInt("active"));
					l.setUnit_qty(rs.getInt("unit_qty"));
					
					l.setStarting_bid_amount(rs.getBigDecimal("starting_bid_amount"));
					
					l.setAmount_bid(rs.getBigDecimal("amount_bid"));
					l.setAmount_buy(rs.getBigDecimal("amount_buy"));
					l.setAction_taken(rs.getInt("action_taken"));
					l.setIs_buy(rs.getInt("is_buy"));
					l.setIs_bid(rs.getInt("is_bid"));
					l.setBuy_price(rs.getBigDecimal("buy_price"));
					l.setBidder_id(rs.getInt("bidder_id"));
					l.setLot_increment_time(rs.getInt("lot_increment_time"));
					l.setBid_count(rs.getInt("bid_count"));
					l.setEnd_date_time(rs.getTimestamp("end_date_time"));
					l.setIs_available_lot(rs.getInt("is_available_lot"));
					l.setWeight_total(rs.getBigDecimal("weight_total"));

					//SystemBean - start
					l.setDate_created(rs.getTimestamp("date_created"));
					l.setDate_updated(rs.getTimestamp("date_updated"));
					l.setCreated_by(rs.getInt("created_by"));
					l.setUpdated_by(rs.getInt("updated_by"));
					//SystemBean - end
					
					lList.add(l);
				}

				//rs.close();
				//stmt.close();
			} catch (Exception ex) {}
			
			
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		//System.out.println("getLotListByAuctionId "+lList.size());
		
		return lList;
	}
	
	
	public ArrayList<Lot> getActiveLotListByAuctionIdwithBidder(BigDecimal auction_id){

		ArrayList<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT *");
		
		sb.append(" from lot where auction_id= "+auction_id+" and active = 1 and bidder_id > 0");
		
		sb.append(" order by lot_no asc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection7();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Lot l = null;

			while(rs.next()){
				l = new Lot();
				l.setId(rs.getBigDecimal("id"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setStarting_bid_amount(rs.getBigDecimal("starting_bid_amount"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		//System.out.println("getLotListByAuctionId "+lList.size());
		
		return lList;
	}
	
	
	public Lot getActiveLotListByLotIdwithBidder(BigDecimal lot_id){
		
		Lot l = null;
		
		StringBuilder sb = new StringBuilder("SELECT *");
		
		sb.append(" from lot where lot_id= "+lot_id+" and active = 1 and bidder_id > 0");
		
		//sb.append(" order by lot_no asc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection10();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			

			while(rs.next()){
				l = new Lot();
				l.setId(rs.getBigDecimal("id"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setStarting_bid_amount(rs.getBigDecimal("starting_bid_amount"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				//lList.add(l);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		//System.out.println("getLotListByAuctionId "+lList.size());
		
		return l;
	}
	
	
	
	public ArrayList<Lot> getActiveLotListByAuctionId2(BigDecimal auction_id){

		ArrayList<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT *");
		
		sb.append(" from lot where auction_id= "+auction_id+" and active = 1");
		
		sb.append(" order by lot_no asc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection10();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Lot l = null;

			while(rs.next()){
				l = new Lot();
				l.setId(rs.getBigDecimal("id"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setStarting_bid_amount(rs.getBigDecimal("starting_bid_amount"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		//System.out.println("getLotListByAuctionId "+lList.size());
		
		return lList;
	}
	
	public List<Lot> getLotListByTypeAndActive(Integer auctionType){

		List<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time, is_available_lot, weight_total");
		
		sb.append(" from lot where auction_type ="+auctionType+" and active = 1");
		
		sb.append(" order by id desc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Lot l = null;

			while(rs.next()){
				l = new Lot();
				l.setId(rs.getBigDecimal("id"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
            	//l.setImageBytes(rs.getBytes("image"));
            	//a.setImageSmallBytes(rs.getBytes("image_small"));

				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				l.setWeight_total(rs.getBigDecimal("weight_total"));
				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		return lList;
	}
	

	public Lot updateLot_End_date_time_OnBatchBiddingExtend(
			Timestamp end_date_time,
			Integer user_id,
			BigDecimal lotId_wip
		){

	int affectedRows = 0;
	
	Lot l = null;
	
	StringBuilder sb = new StringBuilder("Update Lot Set end_date_time=?");

	sb.append(", date_updated=?, updated_by=?");
	
	sb.append(" where id="+lotId_wip);

	Connection conn = null;
	
	DBConnection dbConn = null;
	
	PreparedStatement stmt = null;
	
	try {

		dbConn = new DBConnection();
		
		conn = dbConn.getConnection5();

		System.out.println("sql : "+sb.toString());

	    String sql = sb.toString();
	    
        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt = conn.prepareStatement(sql);
        
        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        
        stmt.setTimestamp(1, end_date_time);
        stmt.setTimestamp(2, sqlDate_t);
        stmt.setInt(3, user_id);


	    System.out.println("sql : "+sql);
	    
	    affectedRows = stmt.executeUpdate();
	    
	    if (affectedRows == 0) {
	    	l = null;
        }else{
        	l = new Lot(); 
        	l.setId(lotId_wip);
        	/*
        	a.setEmail_address(emailAddress);
        	a.setFirst_name(firstName);
        	a.setLast_name(lastName);
        	a.setMobile_no_1(mobileNo1);
        	a.setMobile_no_2(mobileNo2);
        	
        	a.setGender(gender);
        	a.setRole(role);
        	a.setBidder_no(bidderNo);
        	a.setReserve_bidder_no(reserveBidderNo);
        	a.setCompany(company);
        	a.setStatus(userStatus);
        	a.setActive(active);
        	a.setLandline_no(landLineNo);
        	
        	a.setNews_letter(newsLetter);
        	a.setNews_letter_registration_date(newsLetterRegistrationDate_t);
        	a.setVerification_email_key(verificationEmailKey);
        	a.setDate_registration(registrationDate_t);
        	a.setDate_password_change(passwordChangeDate_t);
        	a.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
        	a.setBirth_date(birthDate_d);
        	a.setPass_word(passWord);
        	*/
        }
	    

			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}

		return l;
	}
	
	public ArrayList<Lot> getLotListJoinAuctionUserWishlistByUserId(Integer user_id){
		ArrayList<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT lot.*");
		sb.append(" FROM `lot`, `auction_user_watchlist` ");
		sb.append(" WHERE lot.lot_id = auction_user_watchlist.lot_id");
		sb.append(" AND lot.active = 1");
		sb.append(" AND auction_user_watchlist.user_id = " + user_id);
		sb.append(" GROUP BY lot.id");
		
		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection5();
			
			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());

			ResultSet rs = stmt.executeQuery(sb.toString());
			Lot l = null;

			while(rs.next()){
				l = new Lot();
				l.setId(rs.getBigDecimal("id"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		return lList;
		  
	}
	
	public ArrayList<Lot> getLotListJoinBiddingTransactionByUserId(Integer user_id){
		ArrayList<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT lot.*");
		sb.append(" FROM `lot`, `bidding_transaction` ");
		sb.append(" WHERE lot.lot_id = bidding_transaction.lot_id");
		sb.append(" AND lot.active = 1");
		sb.append(" AND bidding_transaction.user_id = " + user_id);
		sb.append(" GROUP BY lot.id");
		

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection5();
			
			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());
			Lot l = null;

			while(rs.next()){
				l = new Lot();
				l.setId(rs.getBigDecimal("id"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			*/
		}
		
		return lList;
		  
	}
	
	
	
	public ArrayList<Lot> getLotListBySearch(String search){

		ArrayList<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT lot.id, lot_name, lot_no, lot_id, lot.auction_id, lot_desc, assessment_value");

		sb.append(", lot.duties, lot.vat, lot.unit, lot.premium_rate, lot_type_id, lot.active, lot.unit_qty");
		
		sb.append(", lot.amount_bid, lot.amount_buy, lot.action_taken, lot.is_buy, lot.is_bid, lot.buy_price, lot.bidder_id, lot_increment_time, lot.bid_count");
		
		sb.append(", lot.date_created, lot.created_by, lot.date_updated, lot.updated_by, lot.end_date_time, is_available_lot");
		
		sb.append(" FROM lot, auction WHERE (auction.auction_id=lot.auction_id) AND auction.visibility=33 AND (lot.lot_name LIKE '%"+search+"%' OR lot.lot_desc LIKE '%"+search+"%')");
		
		sb.append(" order by lot.lot_no asc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection5();
			
			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Lot l = null;

			while(rs.next()){
				l = new Lot();
				l.setId(rs.getBigDecimal("id"));
				l.setLot_no(rs.getBigDecimal("lot_no"));
				l.setLot_name(rs.getString("lot_name"));
				l.setLot_id(rs.getBigDecimal("lot_id"));
				l.setAuction_id(rs.getBigDecimal("auction_id"));
				l.setLot_desc(rs.getString("lot_desc"));
				l.setAssessment_value(rs.getBigDecimal("assessment_value"));
				l.setDuties(rs.getBigDecimal("duties"));
				l.setVat(rs.getBigDecimal("vat"));
				l.setUnit(rs.getString("unit"));
				l.setPremium_rate(rs.getBigDecimal("premium_rate"));
				l.setLot_type_id(rs.getInt("lot_type_id"));
				l.setActive(rs.getInt("active"));
				l.setUnit_qty(rs.getInt("unit_qty"));
				
				l.setAmount_bid(rs.getBigDecimal("amount_bid"));
				l.setAmount_buy(rs.getBigDecimal("amount_buy"));
				l.setAction_taken(rs.getInt("action_taken"));
				l.setIs_buy(rs.getInt("is_buy"));
				l.setIs_bid(rs.getInt("is_bid"));
				l.setBuy_price(rs.getBigDecimal("buy_price"));
				l.setBidder_id(rs.getInt("bidder_id"));
				l.setLot_increment_time(rs.getInt("lot_increment_time"));
				l.setBid_count(rs.getInt("bid_count"));
				l.setEnd_date_time(rs.getTimestamp("end_date_time"));
				l.setIs_available_lot(rs.getInt("is_available_lot"));
				

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			
			*/
		}
		
		System.out.println("getLotListByAuctionId "+lList.size());
		
		return lList;
	}
	
	public int updateLotSetStartingBidAmount(
			BigDecimal auction_id,
			BigDecimal starting_bid_amount,
			Integer user_id
		){
	int affectedRows = 0;
	
	//Lot l = null;
	
	int replaceSBA = 0;
	
	if(starting_bid_amount.doubleValue() == 0){
		replaceSBA = 1;
	}else if(starting_bid_amount.doubleValue() == 1){
		replaceSBA = 0;
	}
	

	StringBuilder sb = new StringBuilder("Update Lot Set starting_bid_amount=?");
	
	sb.append(", date_updated=?, updated_by=?");
	
	sb.append(" where auction_id="+auction_id+" and starting_bid_amount ="+replaceSBA);
	
	Connection conn = null;
	
	DBConnection dbConn = null;
	
	PreparedStatement stmt = null;
	
	try {

		dbConn = new DBConnection();
		
		conn = dbConn.getConnection6();
		
		System.out.println("sql : "+sb.toString());

	    String sql = sb.toString();
	    
        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt = conn.prepareStatement(sql);
        
        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        //stmt.setBigDecimal(1, auction_id);
        stmt.setBigDecimal(1, starting_bid_amount);
        
        stmt.setTimestamp(2, sqlDate_t);
        stmt.setInt(3, user_id);

	    System.out.println("sql : "+sql);
	    
	    affectedRows = stmt.executeUpdate();


		//stmt.close();
	} catch (SQLException e) {
		throw new RuntimeException(e);
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
			System.out.println("stmt closing : "+stmt);
			stmt.close();
			stmt = null;
			System.out.println("stmt after closing : "+stmt);
			} catch (SQLException e) {}
		}
		*/
	}

	return affectedRows;
}
	
	public int updateLotSetIsBuy(
			BigDecimal auction_id,
			Integer is_buy,
			Integer user_id
		){

	int affectedRows = 0;
	
	//Lot l = null;
	
	int replaceSBA = 0;
	
	if(is_buy.doubleValue() == 0){
		replaceSBA = 1;
	}else if(is_buy.doubleValue() == 1){
		replaceSBA = 0;
	}

		StringBuilder sb = new StringBuilder("Update Lot Set is_buy=?");
		
		sb.append(", date_updated=?, updated_by=?");
		
		sb.append(" where auction_id="+auction_id+" and is_buy ="+replaceSBA);

		
		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection7();
			
			System.out.println("sql : "+sb.toString());
		
	    String sql = sb.toString();
	    
        stmt = conn.prepareStatement(sql);
        
        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        //stmt.setBigDecimal(1, auction_id);
        stmt.setInt(1, is_buy);
        
        stmt.setTimestamp(2, sqlDate_t);
        stmt.setInt(3, user_id);


	    //System.out.println("sql : "+sql);
	    
	    affectedRows = stmt.executeUpdate();
	    
	   
        
	    

		//stmt.close();
	} catch (SQLException e) {
		throw new RuntimeException(e);
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
			System.out.println("stmt closing : "+stmt);
			stmt.close();
			stmt = null;
			System.out.println("stmt after closing : "+stmt);
			} catch (SQLException e) {}
		}
		
		*/
	}

	return affectedRows;
}
	
	
	
	public int updateLotSetLotTotals(
			BigDecimal lot_id,
			BigDecimal reserve_price ,
			BigDecimal srp, 
			BigDecimal target_price, 
			BigDecimal assess_value,
			String bid_qualifier_price,
			Integer user_id
		){
	

	int affectedRows = 0;
	


		StringBuilder sb = new StringBuilder("Update Lot Set reserve_price_total=?, srp_total=?, target_price_total=?, assess_value_total=?, buy_price=?");
		
		sb.append(", date_updated=?, updated_by=?");
		
		sb.append(" where lot_id="+lot_id);

		BigDecimal buy_price = new BigDecimal("0");
    	if("Reserve Price".equals(bid_qualifier_price)){
    		buy_price = reserve_price;
    	}else if("SRP".equals(bid_qualifier_price)){
    		buy_price = srp;
    	}else if("Target Price".equals(bid_qualifier_price)){
    		buy_price = target_price;
    	}else if("Assess Value Price".equals(bid_qualifier_price)){
    		buy_price = assess_value;
    	}
    	
    	
		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection7();
			
			System.out.println("sql : "+sb.toString());
		
	    String sql = sb.toString();
	    
        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt = conn.prepareStatement(sql);
        
        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        //stmt.setBigDecimal(1, auction_id);
        stmt.setBigDecimal(1, reserve_price);
        stmt.setBigDecimal(2, srp);
        stmt.setBigDecimal(3, target_price);
        stmt.setBigDecimal(4, assess_value);
        stmt.setBigDecimal(5, buy_price);
        
        stmt.setTimestamp(6, sqlDate_t);
        stmt.setInt(7, user_id);
	    
	    affectedRows = stmt.executeUpdate();

		//stmt.close();
	} catch (SQLException e) {
		throw new RuntimeException(e);
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
			System.out.println("stmt closing : "+stmt);
			stmt.close();
			stmt = null;
			System.out.println("stmt after closing : "+stmt);
			} catch (SQLException e) {}
		}
		
		*/
	}

	return affectedRows;
}
	
	
	public int updateLotSetEndDateTime(
			BigDecimal lot_id,
			Timestamp end_date_time,
			Integer user_id
		){
		
		int affectedRows = 0;
		
	

	
			StringBuilder sb = new StringBuilder("Update Lot Set end_date_time=?");
			
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where lot_id="+lot_id);

	    	
			Connection conn = null;
			
			DBConnection dbConn = null;
			
			PreparedStatement stmt = null;
			
			try {

				dbConn = new DBConnection();
				
				conn = dbConn.getConnection4();
				
				System.out.println("sql : "+sb.toString());
			
			
		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        stmt = conn.prepareStatement(sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	
	        
	        java.sql.Timestamp sqlDate_t2 = new java.sql.Timestamp(end_date_time.getTime());
	        
	        //stmt.setBigDecimal(1, auction_id);
	        stmt.setTimestamp(1, sqlDate_t2);
	        
	        stmt.setTimestamp(2, sqlDate_t);
	        stmt.setInt(3, user_id);
	
	
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();

			//stmt.close();
		} catch (SQLException e) {
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
				System.out.println("stmt closing : "+stmt);
				stmt.close();
				stmt = null;
				System.out.println("stmt after closing : "+stmt);
				} catch (SQLException e) {}
			}
			
			*/
		}
	
		return affectedRows;
	}
	
	
	
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        //writeBlob(122, "johndoe_resume.pdf");
 
    }

}

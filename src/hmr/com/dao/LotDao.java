package hmr.com.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.util.DBConnection;
import hmr.com.bean.Lot;
import hmr.com.util.StringUtil;

public class LotDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public LotDao(){
		dbConn = new DBConnection();
	}
	

	public LotDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	
	public Lot getLotById(BigDecimal id){
		
		Connection conn = null;

		Lot l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, bid_count");

		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time");
		
		sb.append(" from lot where id ="+id);


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
				
			
				
				
            	l.setDate_created(rs.getTimestamp("date_created"));
            	l.setCreated_by(rs.getInt("created_by"));
            	l.setDate_updated(rs.getTimestamp("date_updated"));
            	l.setUpdated_by(rs.getInt("updated_by"));
            	
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
		
		return l;
	}
	
	
	public Lot getLotByAuctionId(BigDecimal auction_id){
		
		Connection conn = null;

		Lot l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time");
		
		sb.append(" from lot where auction_id ="+auction_id);


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
				
            	l.setDate_created(rs.getTimestamp("date_created"));
            	l.setCreated_by(rs.getInt("created_by"));
            	l.setDate_updated(rs.getTimestamp("date_updated"));
            	l.setUpdated_by(rs.getInt("updated_by"));
            	
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
		
		return l;
	}
	
	public HashMap<BigDecimal, Lot> getLotHMByAuctionId(BigDecimal auction_id){
		
		HashMap<BigDecimal, Lot> lotHM = new HashMap<BigDecimal, Lot>();
		
		Connection conn = null;

		Lot l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, date_sync, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time");
		
		sb.append(" from lot where auction_id ="+auction_id);


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

            	l.setDate_created(rs.getTimestamp("date_created"));
            	l.setCreated_by(rs.getInt("created_by"));
            	l.setDate_updated(rs.getTimestamp("date_updated"));
            	l.setUpdated_by(rs.getInt("updated_by"));
            	
            	lotHM.put(l.getId(), l);
            	
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
		
		return lotHM;
	}
	
	
	public HashMap<BigDecimal, Lot> getLotHMByAuctionId_SetLotId(BigDecimal auction_id){
		
		HashMap<BigDecimal, Lot> lotHM = new HashMap<BigDecimal, Lot>();
		
		Connection conn = null;

		Lot l = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, date_sync, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time");
		
		sb.append(" from lot where auction_id ="+auction_id);


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
				
            	l.setDate_created(rs.getTimestamp("date_created"));
            	l.setCreated_by(rs.getInt("created_by"));
            	l.setDate_updated(rs.getTimestamp("date_updated"));
            	l.setUpdated_by(rs.getInt("updated_by"));
            	
            	lotHM.put(l.getLot_id(), l);
            	
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
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Lot l = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
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
			
			
		    String sql = sb.toString();
	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
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
	
	Connection conn = null;
	
	int affectedRows = 0;
	
	Lot l = null;

	try {
		DBConnection dbConn = new DBConnection();
		
		conn = dbConn.getConnection();
		
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
		
		
	    String sql = sb.toString();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        System.out.println("sql : "+sql);
        
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

				Integer user_id,
				BigDecimal lotId_wip
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Lot l = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			StringBuilder sb = new StringBuilder("Update Lot Set lot_no=?, lot_id=?, auction_id=?, lot_desc=?, assessment_value=?");

			sb.append(", duties=?, vat=?, unit=?, premium_rate=?, lot_type_id=?, active=?, unit_qty=?");
			
			sb.append(", amount_bid=?, amount_buy=?, action_taken=?, is_buy=?, is_bid=?, buy_price=?, bidder_id=?, lot_increment_time=?, lot_name=?");

			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+lotId_wip);

			
			
			
		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
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
	        
	        
	        stmt.setTimestamp(22, sqlDate_t);
	        stmt.setInt(23, user_id);


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
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Lot l = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			StringBuilder sb = new StringBuilder("Update Lot Set lot_id=?, auction_id=?, lot_no=?");
	
			sb.append(", is_available_lot=?, lot_desc=?, lot_type_id=?, premium_rate=?, unit=?, unit_qty=?");
			
			sb.append(", vat=?, duties=?, assessment_value=?, date_sync=?");
	
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where lot_id="+lot_id);
	
			
			
			
		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
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
	
		return l;
	}
	
	
	public List<Lot> getLotList(){

		List<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time");
		
		sb.append(" from lot");
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

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

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
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
		
		return lList;
	}
	
	
	public ArrayList<Lot> getLotListByAuctionId(BigDecimal auction_id){

		ArrayList<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time");
		
		sb.append(" from lot where auction_id= "+auction_id);
		
		sb.append(" order by lot_no asc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

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
				

				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
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
		
		System.out.println("getLotListByAuctionId "+lList.size());
		
		return lList;
	}
	
	
	public List<Lot> getLotListByTypeAndActive(Integer auctionType){

		List<Lot> lList = new ArrayList<Lot>();
		
		StringBuilder sb = new StringBuilder("SELECT id, lot_name, lot_no, lot_id, auction_id, lot_desc, assessment_value");

		sb.append(", duties, vat, unit, premium_rate, lot_type_id, active, unit_qty");
		
		sb.append(", amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price, bidder_id, lot_increment_time, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by, end_date_time");
		
		sb.append(" from lot where auction_type ="+auctionType+" and active = 1");
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

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
				
				//SystemBean - start
				l.setDate_created(rs.getTimestamp("date_created"));
				l.setDate_updated(rs.getTimestamp("date_updated"));
				l.setCreated_by(rs.getInt("created_by"));
				l.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				lList.add(l);
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
		
		return lList;
	}
	

 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        //writeBlob(122, "johndoe_resume.pdf");
 
    }

}

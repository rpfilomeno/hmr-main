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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.util.DBConnection;
import hmr.com.bean.ItemStaging;
import hmr.com.bean.AuctionStaging;


public class ItemStagingDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public ItemStagingDao(){
		dbConn = new DBConnection();
	}
	
	public ItemStagingDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	public ItemStaging getAuctionById(Integer id){
		
		Connection conn = null;

		ItemStaging is = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");

		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, image, image_small, auction_name");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction where id ="+id);


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
				is = new ItemStaging();
/*
				is.setId(rs.getBigDecimal("id"));

				is.setAuction_name(rs.getString("auction_name"));	
				is.setAuction_no(rs.getBigDecimal("auction_no"));
				is.setLocation(rs.getString("location"));
				is.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				is.setStart_date_time(rs.getTimestamp("start_date_time"));
				is.setEnd_date_time(rs.getTimestamp("end_date_time"));
				is.setAuction_desc(rs.getString("auction_desc"));
				is.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				is.setCoordinator(rs.getInt("coordinator"));
				is.setVisibility(rs.getInt("visibility"));
				is.setAuction_item_closing(rs.getInt("auction_item_closing"));
				is.setAuction_type(rs.getInt("auction_type"));
				is.setAuction_id(rs.getBigDecimal("auction_id"));
            	is.setStatus(rs.getInt("status"));
            	is.setActive(rs.getInt("active"));
				is.setNo_of_lots(rs.getInt("no_of_lots"));
				is.setNo_of_items(rs.getInt("no_of_items"));
				is.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				is.setBid_deposit(rs.getInt("bid_deposit"));
				is.setDate_sync(rs.getTimestamp("date_sync"));

            	is.setDate_created(rs.getTimestamp("date_created"));
            	is.setCreated_by(rs.getInt("created_by"));
            	is.setDate_updated(rs.getTimestamp("date_updated"));
            	is.setUpdated_by(rs.getInt("updated_by"));
            	
            	//InputStream binaryStream = rs.getBinaryStream("image");
            	//is.setImageInputStream(binaryStream);
            	
            	is.setImageBytes(rs.getBytes("image"));
            	is.setImageSmallBytes(rs.getBytes("image_small"));
            	
            	//a.
            	//binaryStream.
            	
            	System.out.println("asdfasdf df terms_and_conditions "+a.getTerms_and_conditions());
            	*/
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
		
		return is;
	}
	
	public ItemStaging getAuctionById(BigDecimal id){
		
		Connection conn = null;

		ItemStaging is = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");

		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, image, image_small, auction_name");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction where id ="+id);


		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			//System.out.println("conn : "+conn);
			
			if(conn==null){
				dbConn = new DBConnection();
				conn = dbConn.getConnection();
			}
		
			java.sql.Statement stmt = conn.createStatement();

			//System.out.println("sql : "+sb.toString());
			
			if(stmt==null || stmt.isClosed()){
				stmt = conn.createStatement();
			}
			
			
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				is = new ItemStaging();
/*
				is.setId(rs.getBigDecimal("id"));

				is.setAuction_name(rs.getString("auction_name"));	
				is.setAuction_no(rs.getBigDecimal("auction_no"));
				is.setLocation(rs.getString("location"));
				is.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				is.setStart_date_time(rs.getTimestamp("start_date_time"));
				is.setEnd_date_time(rs.getTimestamp("end_date_time"));
				is.setAuction_desc(rs.getString("auction_desc"));
				is.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				is.setCoordinator(rs.getInt("coordinator"));
				is.setVisibility(rs.getInt("visibility"));
				is.setAuction_item_closing(rs.getInt("auction_item_closing"));
				is.setAuction_type(rs.getInt("auction_type"));
				is.setAuction_id(rs.getBigDecimal("auction_id"));
            	is.setStatus(rs.getInt("status"));
            	is.setActive(rs.getInt("active"));
				is.setNo_of_lots(rs.getInt("no_of_lots"));
				is.setNo_of_items(rs.getInt("no_of_items"));
				is.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				is.setBid_deposit(rs.getInt("bid_deposit"));
				is.setDate_sync(rs.getTimestamp("date_sync"));

            	is.setDate_created(rs.getTimestamp("date_created"));
            	is.setCreated_by(rs.getInt("created_by"));
            	is.setDate_updated(rs.getTimestamp("date_updated"));
            	is.setUpdated_by(rs.getInt("updated_by"));
            	
            	//InputStream binaryStream = rs.getBinaryStream("image");
            	//is.setImageInputStream(binaryStream);
            	
            	is.setImageBytes(rs.getBytes("image"));
            	is.setImageSmallBytes(rs.getBytes("image_small"));
            	
            	//a.
            	//binaryStream.
            	
            	System.out.println("asdfasdf df terms_and_conditions "+a.getTerms_and_conditions());
            	*/
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
		
		return is;
	}
	
	
	public ItemStaging getAuctionByAuctionId(BigDecimal auction_id){
		
		Connection conn = null;

		ItemStaging is = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");

		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, image, image_small, auction_name");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction where auction_id ="+auction_id);


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
				is = new ItemStaging();
/*
				is.setId(rs.getBigDecimal("id"));

				is.setAuction_name(rs.getString("auction_name"));	
				is.setAuction_no(rs.getBigDecimal("auction_no"));
				is.setLocation(rs.getString("location"));
				is.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				is.setStart_date_time(rs.getTimestamp("start_date_time"));
				is.setEnd_date_time(rs.getTimestamp("end_date_time"));
				is.setAuction_desc(rs.getString("auction_desc"));
				is.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				is.setCoordinator(rs.getInt("coordinator"));
				is.setVisibility(rs.getInt("visibility"));
				is.setAuction_item_closing(rs.getInt("auction_item_closing"));
				is.setAuction_type(rs.getInt("auction_type"));
				is.setAuction_id(rs.getBigDecimal("auction_id"));
            	is.setStatus(rs.getInt("status"));
            	is.setActive(rs.getInt("active"));
				is.setNo_of_lots(rs.getInt("no_of_lots"));
				is.setNo_of_items(rs.getInt("no_of_items"));
				is.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				is.setBid_deposit(rs.getInt("bid_deposit"));
				is.setDate_sync(rs.getTimestamp("date_sync"));

            	is.setDate_created(rs.getTimestamp("date_created"));
            	is.setCreated_by(rs.getInt("created_by"));
            	is.setDate_updated(rs.getTimestamp("date_updated"));
            	is.setUpdated_by(rs.getInt("updated_by"));
            	
            	//InputStream binaryStream = rs.getBinaryStream("image");
            	//is.setImageInputStream(binaryStream);
            	
            	is.setImageBytes(rs.getBytes("image"));
            	is.setImageSmallBytes(rs.getBytes("image_small"));
            	
            	//a.
            	//binaryStream.
            	*/
            	//System.out.println("asdfasdf df terms_and_conditions "+a.getTerms_and_conditions());
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
		
		return is;
	}
	
	public ItemStaging getAuctionImageById(Integer id, String size){
		
		Connection conn = null;

		ItemStaging is = null;
		
		StringBuilder sb = new StringBuilder("Select id, image, image_small");

		sb.append(" from auction where id ="+id);


		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				is = new ItemStaging();
/*
				is.setId(rs.getBigDecimal("id"));
            	is.setImageBytes(rs.getBytes("image"));
            	is.setImageSmallBytes(rs.getBytes("image_small"));
*/
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
		
		return is;
	}
	/*
	
	public ItemStaging getAuction(String auctionId){
		
		Connection conn = null;

		ItemStaging is = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, role from auction where ");

		//sb.append("email_address = '"+userId+"'") ;

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				is = new ItemStaging();
				is.setId(rs.getInt("id"));

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
		
		return is;
	}
	
	public ItemStaging getAuctionRegistration(String auctionId, String vek){
		
		Connection conn = null;

		ItemStaging is = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, verification_email_key, date_registration from auction where ");

		//sb.append("email_address = '"+userId+"' and verification_email_key = '"+vek+"'");

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				is = new ItemStaging();
				is.setId(rs.getInt("id"));

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
		
		return is;
	}
	
	
	public int insertAuctionOnRegistration(String firstName, String lastName, String auctionId, Integer mobileNo, String verification_email_key){
		
		int i = 0;
		
		try {
			 conn = getConnection();

			  Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();

		      //String sql = "INSERT INTO auction (first_name, last_name, email_address, mobile_no_1, verification_email_key, role, date_created) " +
		      //             "VALUES ('"+firstName+"', '"+lastName+"', '"+userId+"' ,"+ mobileNo+", '"+verification_email_key+"', 2, now())";
		      String sql = "";
		      
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
*/	
	/*
	
	public int insertAuctionOnCreate(
				String firstName,
				String lastName,
				String birthDate,
				Integer gender,
				String company,
				String emailAddress,
				String passWord,
				Integer auctionStatus,
				Integer active,
				Integer role,
				Integer newsLetter,
				String newsLetterRegistrationDate,
				String registrationDate,
				String passwordChangeDate,
				Integer mobileNo1,
				Integer mobileNo2,
				Integer landLineNo,
				Integer bidderNo,
				Integer reserveBidderNo,
				String verificationEmailKey,
				Integer showChangePasswordNextLogin,
				Integer auction_id,
				String a
			){
		
		int i = 0;
		
		try {
			 conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();
		      
				StringBuilder sb = new StringBuilder("INSERT INTO auction (email_address, first_name, last_name, mobile_no_1, mobile_no_2");

				sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
				
				sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration");
		      
				sb.append(", date_password_change, show_change_password_next_login, birth_date");
				
				sb.append(", date_created, created_by)");
				
				sb.append(" VALUES(");
				
				sb.append("'"+emailAddress+"', '"+firstName+"', '"+lastName+"' ,"+ mobileNo1+", "+mobileNo2+"");
				sb.append(", "+gender+", "+role+", "+bidderNo+" ,"+ reserveBidderNo+", '"+company+"' ,"+ auctionStatus+", "+active+", "+landLineNo);
				sb.append(", "+newsLetter+", '"+newsLetterRegistrationDate+"', '"+verificationEmailKey+"' ,'"+ registrationDate+"'");
				sb.append(", '"+passwordChangeDate+"', "+showChangePasswordNextLogin+", '"+birthDate+"'");
				
				sb.append("now(),"+user_id);
				
				sb.append(")");
		      
		      
		      String sql = sb.toString();
		      
		      
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
	*/

	public int insertItemStagingOnSearch(
			BigDecimal item_id,
			BigDecimal lot_id,
			Integer status_id,
			BigDecimal reference_no,
			Integer pullout_id,
			BigDecimal target_price,
			BigDecimal reserved_price,
			BigDecimal rate,
			BigDecimal amount_bid,
			Integer received_items_id,
			String qt_remarks,
			BigDecimal assess_value,
			Integer payment_status,
			BigDecimal bidder_number_id,
			Integer payables_id,
			BigDecimal product_code,
			BigDecimal srp,
			BigDecimal consignor_id,
			String description,
			BigDecimal delivery_receipt_id,
			Timestamp last_date_sync
			) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int affectedRows = 0;
		
		//AuctionStaging is = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection7();


			StringBuilder sb = new StringBuilder("INSERT into item_staging (item_id, lot_id, status_id, reference_no, pullout_id,");

			sb.append(" target_price, reserved_price, rate, amount_bid, received_items_id,");
			
			sb.append(" qt_remarks, assess_value, payment_status, bidder_number_id, payables_id,");

			sb.append(" product_code, srp, consignor_id, description, delivery_receipt_id, last_date_sync");
			
			sb.append(" )VALUES(");
			
			sb.append(" ?, ?, ?, ?, ?,");
			
			sb.append(" ?, ?, ?, ?, ?,");
			
			sb.append(" ?, ?, ?, ?, ?,");
			
			sb.append(" ?, ?, ?, ?, ?, ?");

			sb.append(")");
			
			
		    String sql = sb.toString();
	        
	        
	        
			try{
				stmt = conn.prepareStatement(sql);
			}catch(Exception ex){
				conn = dbConn.getConnection4();
				//stmt = conn.prepareStatement(sql);
				
				try{
					stmt = conn.prepareStatement(sql);
				}catch(Exception ex1){
					conn = dbConn.getConnection5();
					stmt = conn.prepareStatement(sql);
				}
				
			}
	        
			if(stmt.isClosed()){
				stmt = conn.prepareStatement(sql);
			}
	        
	        /*
			if(conn!=null && !conn.isClosed()){
				try{
					stmt = conn.prepareStatement(sql);
				}catch(Exception ex){
					conn = dbConn.getConnection2();
					stmt = conn.prepareStatement(sql);
				}
				
			}else{
				dbConn = new DBConnection();
				conn = dbConn.getConnection3();
				stmt = conn.prepareStatement(sql);
			}
			
			
			if(stmt!=null && !stmt.isClosed()){
				if(conn!=null && !conn.isClosed()){
					stmt = conn.prepareStatement(sql);
				}else{
					dbConn = new DBConnection();
					conn = dbConn.getConnection4();
					stmt = conn.prepareStatement(sql);
				}
			}else{
				dbConn = new DBConnection();
				conn = dbConn.getConnection();
				if(stmt!=null && !stmt.isClosed()){
					
				}else{
					stmt = conn.prepareStatement(sql);
				}
				if(conn!=null && !conn.isClosed()){
					stmt = conn.prepareStatement(sql);
				}else{
					
					stmt = conn.prepareStatement(sql);
				}
				
			}
			
			if(conn==null || conn.isClosed()){
				conn = dbConn.getConnection();
			}
	        
			if(stmt!=null && !stmt.isClosed()){
				
			}else{
				stmt = conn.prepareStatement(sql);
			}
			

	        
			if((stmt==null || stmt.isClosed()) && conn!=null && !conn.isClosed()){
				stmt = conn.prepareStatement(sql);
			}
			
			if(conn.isClosed()){
				conn = dbConn.getConnection();
				stmt = conn.prepareStatement(sql);
			}
			*/

	        stmt.setBigDecimal(1, item_id);
	        stmt.setBigDecimal(2, lot_id);
	        stmt.setInt(3, status_id);
	        stmt.setBigDecimal(4, reference_no);
	        stmt.setInt(5, pullout_id);
	        

	        stmt.setBigDecimal(6, target_price);
	        stmt.setBigDecimal(7, reserved_price);
	        stmt.setBigDecimal(8, rate);
	        stmt.setBigDecimal(9, amount_bid);
	        stmt.setInt(10, received_items_id);
	
			
	        
	        stmt.setString(11, qt_remarks);
	        stmt.setBigDecimal(12, assess_value);
	        stmt.setInt(13, payment_status);
	        stmt.setBigDecimal(14, bidder_number_id);
	        stmt.setInt(15, payables_id);


	        stmt.setBigDecimal(16, product_code);
	        stmt.setBigDecimal(17, srp);
	        stmt.setBigDecimal(18, consignor_id);
	        stmt.setString(19, description);
	        stmt.setBigDecimal(20, delivery_receipt_id);
	        stmt.setTimestamp(21, last_date_sync); 

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    //if (affectedRows == 0) {
	        //    throw new SQLException("Creating lot staging failed, no rows affected.");
	        //}

			//stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getCause());
			
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}

		return affectedRows;
	}
	
	public int insertItemStagingOnSearch2(
			BigDecimal item_id,
			BigDecimal lot_id,
			Integer status_id,
			BigDecimal reference_no,
			Integer pullout_id,
			BigDecimal target_price,
			BigDecimal reserved_price,
			BigDecimal rate,
			BigDecimal amount_bid,
			Integer received_items_id,
			String qt_remarks,
			BigDecimal assess_value,
			Integer payment_status,
			BigDecimal bidder_number_id,
			Integer payables_id,
			BigDecimal product_code,
			BigDecimal srp,
			BigDecimal consignor_id,
			String description,
			BigDecimal delivery_receipt_id,
			Timestamp last_date_sync
			) {
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		System.out.println("22222222222222");
		//AuctionStaging is = null;

		PreparedStatement stmt = null;
		
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection2();


			StringBuilder sb = new StringBuilder("INSERT into item_staging (item_id, lot_id, status_id, reference_no, pullout_id,");

			sb.append(" target_price, reserved_price, rate, amount_bid, received_items_id,");
			
			sb.append(" qt_remarks, assess_value, payment_status, bidder_number_id, payables_id,");

			sb.append(" product_code, srp, consignor_id, description, delivery_receipt_id, last_date_sync");
			
			sb.append(" )VALUES(");
			
			sb.append(" ?, ?, ?, ?, ?,");
			
			sb.append(" ?, ?, ?, ?, ?,");
			
			sb.append(" ?, ?, ?, ?, ?,");
			
			sb.append(" ?, ?, ?, ?, ?, ?");

			sb.append(")");
			
			
		    String sql = sb.toString();
	        
	        
	        
			try{
				stmt = conn.prepareStatement(sql);
			}catch(Exception ex){
				conn = dbConn.getConnection3();
				//stmt = conn.prepareStatement(sql);
				
				try{
					stmt = conn.prepareStatement(sql);
				}catch(Exception ex1){
					conn = dbConn.getConnection7();
					stmt = conn.prepareStatement(sql);
				}
				
			}
	        
			if(stmt.isClosed()){
				stmt = conn.prepareStatement(sql);
			}
	        /*
			if(conn!=null && !conn.isClosed()){
				try{
					stmt = conn.prepareStatement(sql);
				}catch(Exception ex){
					conn = dbConn.getConnection7();
					stmt = conn.prepareStatement(sql);
				}
				
			}else{
				dbConn = new DBConnection();
				conn = dbConn.getConnection3();
				stmt = conn.prepareStatement(sql);
			}
			
			
			if(stmt!=null && !stmt.isClosed()){
				if(conn!=null && !conn.isClosed()){
					stmt = conn.prepareStatement(sql);
				}else{
					dbConn = new DBConnection();
					conn = dbConn.getConnection5();
					stmt = conn.prepareStatement(sql);
				}
			}else{
				dbConn = new DBConnection();
				conn = dbConn.getConnection();
				if(stmt!=null && !stmt.isClosed()){
					
				}else{
					stmt = conn.prepareStatement(sql);
				}
				if(conn!=null && !conn.isClosed()){
					stmt = conn.prepareStatement(sql);
				}else{
					
					stmt = conn.prepareStatement(sql);
				}
				
			}
			
			if(conn==null || conn.isClosed()){
				conn = dbConn.getConnection3();
			}
	        
			if(stmt!=null && !stmt.isClosed()){
				
			}else{
				stmt = conn.prepareStatement(sql);
			}
			

	        
			if((stmt==null || stmt.isClosed()) && conn!=null && !conn.isClosed()){
				stmt = conn.prepareStatement(sql);
			}
			
			if(conn.isClosed()){
				conn = dbConn.getConnection4();
				stmt = conn.prepareStatement(sql);
			}
*/
	        stmt.setBigDecimal(1, item_id);
	        stmt.setBigDecimal(2, lot_id);
	        stmt.setInt(3, status_id);
	        stmt.setBigDecimal(4, reference_no);
	        stmt.setInt(5, pullout_id);
	        

	        stmt.setBigDecimal(6, target_price);
	        stmt.setBigDecimal(7, reserved_price);
	        stmt.setBigDecimal(8, rate);
	        stmt.setBigDecimal(9, amount_bid);
	        stmt.setInt(10, received_items_id);
	
			
	        
	        stmt.setString(11, qt_remarks);
	        stmt.setBigDecimal(12, assess_value);
	        stmt.setInt(13, payment_status);
	        stmt.setBigDecimal(14, bidder_number_id);
	        stmt.setInt(15, payables_id);


	        stmt.setBigDecimal(16, product_code);
	        stmt.setBigDecimal(17, srp);
	        stmt.setBigDecimal(18, consignor_id);
	        stmt.setString(19, description);
	        stmt.setBigDecimal(20, delivery_receipt_id);
	        stmt.setTimestamp(21, last_date_sync); 

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    //if (affectedRows == 0) {
	        //    throw new SQLException("Creating lot staging failed, no rows affected.");
	        //}

			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			
		}

		return affectedRows;
	}
	
	public int deleteItemStagingOnSearch() {
	
	Connection conn = null;
	
	int affectedRows = 0;
	
	AuctionStaging is = null;

	try {
		DBConnection dbConn = new DBConnection();
		
		conn = dbConn.getConnection();
		

		StringBuilder sb = new StringBuilder("DELETE from item_staging");
		
	    String sql = sb.toString();
        Statement stmt = conn.createStatement();
        
        

	    System.out.println("sql : "+sql);
	    
	    affectedRows = stmt.executeUpdate(sql);
	    /*
	    if (affectedRows == 0) {
            throw new SQLException("Creating auction failed, no rows affected.");
        }
*/
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

	return affectedRows;
}
	
	public ItemStaging updateItemStagingOnUpdate(
			String auction_name,
			BigDecimal auction_id,
			BigDecimal auction_no,
			String location,
				Date start_date_time,
				Date end_date_time,
				String auction_desc,
				Integer coordinator,
				Integer auction_type,
				Integer auctionStatus,
				Integer active,
				Integer visibility,
				Integer auction_item_increment_time,
				Integer bid_deposit,
				BigDecimal bid_deposit_amount,
				Integer no_of_lots,
				Integer no_of_items,
				Date date_sync,
				String terms_and_conditions,
				Integer user_id,
				BigDecimal auctionId_wip
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		ItemStaging is = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();

			StringBuilder sb = new StringBuilder("Update auction SET auction_no=?, location=?, bid_deposit_amount=?, start_date_time=?, end_date_time=?");

			sb.append(", auction_desc=?, terms_and_conditions=?, coordinator=?, visibility=?, auction_type=?, active=?, auction_id=?");
			
			sb.append(", no_of_lots=?, no_of_items=?, auction_item_increment_time=?, bid_deposit=?, date_sync=?, status=?");
		
			sb.append(", date_updated=?, updated_by=?, auction_name=?");
			
			sb.append(" where auction_id="+auctionId_wip);

			
			
			
		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        
	        
	        
	        java.sql.Date date_sync_d = null;
	        java.sql.Timestamp date_sync_t = null;
	        if(date_sync!=null){
	        	date_sync_d = new java.sql.Date(date_sync.getTime());
	        	date_sync_t = new java.sql.Timestamp(date_sync.getTime());
	        }
	        java.sql.Date start_date_time_d = null;
	        java.sql.Timestamp start_date_time_t = null;
	        if(start_date_time!=null){
	        	start_date_time_d = new java.sql.Date(start_date_time.getTime());
	        	start_date_time_t = new java.sql.Timestamp(start_date_time.getTime());
	        }
	        java.sql.Date end_date_time_d = null;
	        java.sql.Timestamp end_date_time_t = null;
	        if(end_date_time!=null){
	        	end_date_time_d = new java.sql.Date(end_date_time.getTime());
	        	end_date_time_t = new java.sql.Timestamp(end_date_time.getTime());
	        }
	        
	        System.out.println("start_date_time_t SAVING :"+start_date_time_t);
	        System.out.println("end_date_time_t SAVING :"+end_date_time_t);
	        System.out.println("terms_and_conditions SAVING :"+terms_and_conditions);
	        
	        
	        stmt.setBigDecimal(1, auction_no);
	        stmt.setString(2, location);
	        stmt.setBigDecimal(3, bid_deposit_amount);
	        stmt.setTimestamp(4, start_date_time_t);
	        stmt.setTimestamp(5, end_date_time_t);

	        //terms_and_conditions = "<p>terms_and_conditions</p><p>asdf<p>";
	        
	        stmt.setString(6, auction_desc);
	        stmt.setString(7, terms_and_conditions);
	        stmt.setInt(8, coordinator);
	        stmt.setInt(9, visibility);
	        stmt.setInt(10, auction_type);
	        stmt.setInt(11, active);
	        stmt.setBigDecimal(12, auction_id);
	        
	        stmt.setInt(13, no_of_lots);
	        stmt.setInt(14, no_of_items);
	        stmt.setInt(15, auction_item_increment_time);
	        stmt.setInt(16, bid_deposit);
	        stmt.setTimestamp(17, date_sync_t);
	        stmt.setInt(18, auctionStatus);
	        
	        stmt.setTimestamp(19, sqlDate_t);
	        stmt.setInt(20, user_id);
	        stmt.setString(21, auction_name);

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	is = null;
	        }else{
	        	is = new ItemStaging(); 
            	//is.setId(auctionId_wip);
            	/*
            	is.setEmail_address(emailAddress);
            	is.setFirst_name(firstName);
            	is.setLast_name(lastName);
            	is.setMobile_no_1(mobileNo1);
            	is.setMobile_no_2(mobileNo2);
            	
            	is.setGender(gender);
            	is.setRole(role);
            	is.setBidder_no(bidderNo);
            	is.setReserve_bidder_no(reserveBidderNo);
            	is.setCompany(company);
            	is.setStatus(userStatus);
            	is.setActive(active);
            	is.setLandline_no(landLineNo);
            	
            	is.setNews_letter(newsLetter);
            	is.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	is.setVerification_email_key(verificationEmailKey);
            	is.setDate_registration(registrationDate_t);
            	is.setDate_password_change(passwordChangeDate_t);
            	is.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	is.setBirth_date(birthDate_d);
            	is.setPass_word(passWord);
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
	
		return is;
	}
	
	
	public ItemStaging updateAuctionImage(
				File file_small,
				File file,
				Integer user_id,
				BigDecimal auctionId_wip
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		ItemStaging is = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			String imageSmallField = "image_small";
			
			String imageField = "image";
			
			FileInputStream fis_small = null;
			if(file_small!=null){
				try {
					fis_small = new FileInputStream ( file_small );
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FileInputStream fis = null;
			if(file!=null){
				try {
					fis = new FileInputStream ( file );
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


			
			StringBuilder sb = new StringBuilder("Update auction");
			if(file_small!=null && file!=null){
				sb.append(" SET image_small=?, image=?");
			}else{
				if(file_small!=null){
					sb.append(" SET image_small=?");
				}else if(file!=null){
					sb.append(" SET image=?");
				}
			}
			
			
			//sb.append(", auction_desc=?, terms_and_conditions=?, coordinator=?, visibility=?, auction_type=?, active=?, auction_id=?");
			
			//sb.append(", no_of_lots=?, no_of_items=?, auction_item_increment_time=?, bid_deposit=?, date_sync=?, status=?");
		
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+auctionId_wip);

		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        
			if(file_small!=null && file!=null){
		        stmt.setBlob (1, fis_small);
		        stmt.setBlob (2, fis);
		        stmt.setTimestamp(3, sqlDate_t);
		        stmt.setBigDecimal(4, auctionId_wip);
			}else{
				if(file_small!=null){

			        stmt.setBlob (1, fis_small);
			        stmt.setTimestamp(2, sqlDate_t);
			        stmt.setBigDecimal(3, auctionId_wip);
					
				}else if(file!=null){
			        stmt.setBlob (1, fis);
			        stmt.setTimestamp(2, sqlDate_t);
			        stmt.setBigDecimal(3, auctionId_wip);
					
				}
			}

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	is = null;
	        }else{
	        	is = new ItemStaging(); 
            	//is.setId(auctionId_wip);
            	/*
            	is.setEmail_address(emailAddress);
            	is.setFirst_name(firstName);
            	is.setLast_name(lastName);
            	is.setMobile_no_1(mobileNo1);
            	is.setMobile_no_2(mobileNo2);
            	
            	is.setGender(gender);
            	is.setRole(role);
            	is.setBidder_no(bidderNo);
            	is.setReserve_bidder_no(reserveBidderNo);
            	is.setCompany(company);
            	is.setStatus(userStatus);
            	is.setActive(active);
            	is.setLandline_no(landLineNo);
            	
            	is.setNews_letter(newsLetter);
            	is.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	is.setVerification_email_key(verificationEmailKey);
            	is.setDate_registration(registrationDate_t);
            	is.setDate_password_change(passwordChangeDate_t);
            	is.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	is.setBirth_date(birthDate_d);
            	is.setPass_word(passWord);
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
	
		return is;
	}
	
	
	
	public List<ItemStaging> getItemStagingList(){

		List<ItemStaging> isList = new ArrayList<ItemStaging>();
		
		StringBuilder sb = new StringBuilder("Select item_id, lot_id, status_id, reference_no, pullout_id,");

		sb.append(" target_price, reserved_price, rate, amount_bid, received_items_id,");
		
		sb.append(" qt_remarks, assess_value, payment_status, bidder_number_id, payables_id,");
		
		sb.append(" product_code, srp, consignor_id, description, delivery_receipt_id, last_date_sync");
		
		sb.append(" from item_staging");
		
		sb.append(" order by item_id asc");

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();
			
			//System.out.println("conn : "+conn);
			
			if(conn==null){
				dbConn = new DBConnection();
				conn = dbConn.getConnection4();
			}
		
			java.sql.Statement stmt = conn.createStatement();

			//System.out.println("sql : "+sb.toString());
			
			if(stmt==null || stmt.isClosed()){
				stmt = conn.createStatement();
			}

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			ItemStaging is = null;

			while(rs.next()){
				is = new ItemStaging();

	
				is.setItem_id(rs.getBigDecimal("item_id") );
				is.setLot_id(rs.getBigDecimal("lot_id"));
				is.setStatus_id(rs.getInt("status_id"));
				is.setReference_no(rs.getBigDecimal("reference_no"));
				is.setPullout_id(rs.getInt("pullout_id"));

				is.setTarget_price(rs.getBigDecimal("target_price"));
				is.setReserve_price(rs.getBigDecimal("reserved_price"));
				is.setRate(rs.getBigDecimal("rate"));
				is.setAmount_bid(rs.getBigDecimal("amount_bid"));
				is.setReceived_items_id(rs.getInt("received_items_id"));
				
			
				is.setQt_remarks(rs.getString("qt_remarks"));
				is.setAssess_value(rs.getBigDecimal("assess_value"));
				is.setPayment_status(rs.getInt("payment_status"));
				is.setBidder_number_id(rs.getBigDecimal("bidder_number_id"));
				is.setPayables_id(rs.getInt("payables_id"));
				
				is.setProduct_code(rs.getBigDecimal("product_code"));
				is.setSrp(rs.getBigDecimal("srp"));
				is.setConsignor_id(rs.getBigDecimal("consignor_id"));
				is.setDescription(rs.getString("description"));
				is.setDelivery_receipt_id(rs.getBigDecimal("delivery_receipt_id"));
				is.setLast_date_sync(rs.getTimestamp("last_date_sync"));

				isList.add(is);
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
		
		return isList;
	}
	
}

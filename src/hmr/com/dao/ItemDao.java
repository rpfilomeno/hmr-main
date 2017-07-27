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
import hmr.com.bean.Item;
import hmr.com.bean.Lot;
import hmr.com.util.StringUtil;

public class ItemDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;
	
	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public ItemDao(){
		dbConn = new DBConnection();
	}
	

	public ItemDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	public Item getItemById(BigDecimal id){
		
		Connection conn = null;

		Item i = null;

		StringBuilder sb = new StringBuilder("Select id, lot_id, auction_id, item_id, reference_no, target_price");

		sb.append(", reserve_price, amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price");
		
		sb.append(", bidder_id, item_increment_time, item_desc, image, category_level_1, category_level_2, category_level_3, currency, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from item where id ="+id);


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
				i = new Item();

				i.setId(rs.getBigDecimal("id"));

				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setReference_no(rs.getBigDecimal("reference_no"));
				i.setTarget_price(rs.getBigDecimal("target_price"));
				i.setReserve_price(rs.getBigDecimal("reserve_price"));
				i.setAmount_bid(rs.getBigDecimal("amount_bid"));
				i.setAmount_buy(rs.getBigDecimal("amount_buy"));
				i.setAction_taken(rs.getInt("action_taken"));
				i.setIs_buy(rs.getInt("is_buy"));
				i.setIs_bid(rs.getInt("is_bid"));
				i.setBuy_price(rs.getBigDecimal("buy_price"));
				i.setBidder_id(rs.getInt("bidder_id"));
				i.setItem_increment_time(rs.getInt("item_increment_time"));
				i.setItem_desc(rs.getString("item_desc"));
				i.setImageBytes(rs.getBytes("image"));
				i.setCategory_level_1(rs.getInt("category_level_1"));
				i.setCategory_level_2(rs.getInt("category_level_2"));
				i.setCategory_level_3(rs.getInt("category_level_3"));
				i.setCurrency(rs.getInt("currency"));
				i.setBid_count(rs.getInt("bid_count"));

            	i.setDate_created(rs.getTimestamp("date_created"));
            	i.setCreated_by(rs.getInt("created_by"));
            	i.setDate_updated(rs.getTimestamp("date_updated"));
            	i.setUpdated_by(rs.getInt("updated_by"));

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
		
		return i;
	}
	
	
	public Item getItemImageById(Integer id, String size){
		
		Connection conn = null;

		Item i = null;
		
		StringBuilder sb = new StringBuilder("Select id, image");

		sb.append(" from item where id ="+id);


		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				i = new Item();

				i.setId(rs.getBigDecimal("id"));
            	i.setImageBytes(rs.getBytes("image"));
            	//i.setImageSmallBytes(rs.getBytes("image_small"));

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
		
		return i;
	}

	public Item insertItemOnCreate(
				BigDecimal lot_id,
				BigDecimal item_id,
				BigDecimal auction_id,
				BigDecimal reference_no,
				BigDecimal target_price,
				BigDecimal reserve_price,
				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action_taken,
				Integer is_buy,
				Integer is_bid,
				BigDecimal buy_price,
				Integer bidder_id,
				Integer sync_status,
				Integer item_increment_time,
				String item_desc,
				Integer category_level_1,
				Integer category_level_2,
				Integer category_level_3,
				Integer user_id
			) {
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Item i = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection6();
			

			//String SERVER_DIRECTORY_HMR_IMAGES = (String)req.getSession().getAttribute("SERVER_DIRECTORY_HMR_IMAGES");
			
			//File image = new File(SERVER_DIRECTORY_HMR_IMAGES + "default-image.jpg");
			//FileInputStream fis = new FileInputStream ( image );
			
			
			//File image_small = new File(SERVER_DIRECTORY_HMR_IMAGES + "default-image-small.jpg");
			//FileInputStream fis_small = new FileInputStream ( image_small );

			StringBuilder sb = new StringBuilder("INSERT into item (lot_id, item_id, auction_id, reference_no, target_price");

			sb.append(", reserve_price, amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price");
			
			//sb.append(", bidder_id, item_increment_time, item_desc, image, category_level_1, category_level_2, category_level_3");
			
			sb.append(", bidder_id, item_increment_time, item_desc, category_level_1, category_level_2, category_level_3");
			
			sb.append(", date_created, created_by)");
			
			sb.append(" VALUES(");
			
			sb.append(" ?, ?, ?, ?, ?");
			sb.append(",?, ?, ?, ?, ?, ?, ?");
			//sb.append(",?, ?, ?, ?, ?, ?, ?");
			sb.append(",?, ?, ?, ?, ?, ?");
			sb.append(",?, ?");
			
			sb.append(")");
			
			
		    String sql = sb.toString();
	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


	        stmt.setBigDecimal(1, lot_id);
	        stmt.setBigDecimal(2, item_id);
	        stmt.setBigDecimal(3, auction_id);
	        stmt.setBigDecimal(4, reference_no);
	        stmt.setBigDecimal(5, target_price);
	        stmt.setBigDecimal(6, reserve_price);
	        stmt.setBigDecimal(7, amount_bid);
	        stmt.setBigDecimal(8, amount_buy);
	        stmt.setInt(9, action_taken);
	        stmt.setInt(10, is_buy);
	        stmt.setInt(11, is_bid);
	        stmt.setBigDecimal(12, buy_price);
	        
	        
	        stmt.setInt(13, bidder_id);
	        stmt.setInt(14, item_increment_time);
	        stmt.setString(15, item_desc);
	        //stmt.setBlob (16, null, 0);
	        
	        stmt.setInt(16, category_level_1);
	        stmt.setInt(17, category_level_2);
	        stmt.setInt(18, category_level_3);
	        
	        stmt.setTimestamp(19, sqlDate_t);
	        stmt.setInt(20, user_id);

	        
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating item failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	i = new Item(); 
	            	i.setId(generatedKeys.getBigDecimal(1));
	            	
	            	//writeBlob(a.getId(),"",conn);
	            	
	            	/*
	            	i.setEmail_address(emailAddress);
	            	i.setFirst_name(firstName);
	            	i.setLast_name(lastName);
	            	i.setMobile_no_1(mobileNo1);
	            	i.setMobile_no_2(mobileNo2);
	            	
	            	i.setGender(gender);
	            	i.setRole(role);
	            	i.setBidder_no(bidderNo);
	            	i.setReserve_bidder_no(reserveBidderNo);
	            	i.setCompany(company);
	            	i.setStatus(userStatus);
	            	i.setActive(active);
	            	i.setLandline_no(landLineNo);
	            	
	            	i.setNews_letter(newsLetter);
	            	i.setNews_letter_registration_date(newsLetterRegistrationDate_t);
	            	i.setVerification_email_key(verificationEmailKey);
	            	i.setDate_registration(registrationDate_t);
	            	i.setDate_password_change(passwordChangeDate_t);
	            	i.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
	            	i.setBirth_date(birthDate_d);
	            	i.setPass_word(passWord);
	            	*/
	            	
	            }
	            else {
	                throw new SQLException("Creating item failed, no ID obtained.");
	            }
	        }
		    
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		//} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}

		return i;
	}
	
	public Item insertItemOnUpload(
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
			BigDecimal auction_id,
			Timestamp last_date_sync,
			Integer user_id
		) {
	
	Connection conn = null;
	
	int affectedRows = 0;
	
	Item i = null;

	try {
		DBConnection dbConn = new DBConnection();
		
		conn = dbConn.getConnection5();
		

		String SERVER_DIRECTORY_HMR_IMAGES = null;
		
		try{
			SERVER_DIRECTORY_HMR_IMAGES = (String)req.getSession().getAttribute("SERVER_DIRECTORY_HMR_IMAGES");
			
		}catch(Exception ex){
			
			
			SERVER_DIRECTORY_HMR_IMAGES ="C:\\Work\\workspace\\HMR\\WebContent\\hmr\\images\\";
		}
		
		File image = new File(SERVER_DIRECTORY_HMR_IMAGES + "default-image.jpg");
		FileInputStream fis = new FileInputStream ( image );
		
		
		//File image_small = new File(SERVER_DIRECTORY_HMR_IMAGES + "default-image-small.jpg");
		//FileInputStream fis_small = new FileInputStream ( image_small );

		StringBuilder sb = new StringBuilder("INSERT into item (item_id, lot_id, status_id, reference_no, pullout_id, target_price");
	
			sb.append(", reserve_price, rate, amount_bid, received_items_id, qt_remarks, assess_value, payment_status");
			
			sb.append(", bidder_id, payables_id, product_code, srp, consignor_id, item_desc");
			
			sb.append(", delivery_receipt_id, auction_id, synched_date");
		
		sb.append(", date_created, created_by)");
		
		sb.append(" VALUES(");
		
		sb.append("  ?, ?, ?, ?, ?, ?");
		sb.append(", ?, ?, ?, ?, ?, ?, ?");
		sb.append(", ?, ?, ?, ?, ?, ?");
		sb.append(", ?, ?, ?");
		sb.append(", ?, ?");
		
		sb.append(")");
		
		
	    String sql = sb.toString();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        
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
        stmt.setBigDecimal(21, auction_id);
        stmt.setTimestamp(22, last_date_sync);
        stmt.setTimestamp(23, sqlDate_t);
        stmt.setInt(24, user_id);

        
	    System.out.println("sql : "+sql);
	    
	    affectedRows = stmt.executeUpdate();
	    
	    if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }
	    
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            	i = new Item(); 
            	i.setId(generatedKeys.getBigDecimal(1));
            	
            	//writeBlob(a.getId(),"",conn);
            	
            	/*
            	i.setEmail_address(emailAddress);
            	i.setFirst_name(firstName);
            	i.setLast_name(lastName);
            	i.setMobile_no_1(mobileNo1);
            	i.setMobile_no_2(mobileNo2);
            	
            	i.setGender(gender);
            	i.setRole(role);
            	i.setBidder_no(bidderNo);
            	i.setReserve_bidder_no(reserveBidderNo);
            	i.setCompany(company);
            	i.setStatus(userStatus);
            	i.setActive(active);
            	i.setLandline_no(landLineNo);
            	
            	i.setNews_letter(newsLetter);
            	i.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	i.setVerification_email_key(verificationEmailKey);
            	i.setDate_registration(registrationDate_t);
            	i.setDate_password_change(passwordChangeDate_t);
            	i.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	i.setBirth_date(birthDate_d);
            	i.setPass_word(passWord);
            	*/
            	
            }
            else {
                throw new SQLException("Creating item failed, no ID obtained.");
            }
        }
	    
		stmt.close();
	} catch (SQLException e) {
		throw new RuntimeException(e);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (conn != null) {
			try {
			conn.close();
			} catch (SQLException e) {}
		}
	}

	return i;
}
	
	
	public Item updateItemOnUpdate(
				BigDecimal lot_id,
				BigDecimal item_id,
				BigDecimal auction_id,
				BigDecimal reference_no,
				BigDecimal target_price,
				BigDecimal reserve_price,
				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action_taken,
				Integer is_buy,
				Integer is_bid,
				BigDecimal buy_price,
				Integer bidder_id,
				Integer sync_status,
				Integer item_increment_time,
				String item_desc,
				Integer category_level_1,
				Integer category_level_2,
				Integer category_level_3,
				Integer user_id,
				BigDecimal itemId_wip
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Item i = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			StringBuilder sb = new StringBuilder("UPDATE item SET lot_id=?, item_id=?, auction_id=?, reference_no=?, target_price=?");

			sb.append(", reserve_price=?, amount_bid=?, amount_buy=?, action_taken=?, is_buy=?, is_bid=?, buy_price=?");
			
			sb.append(", bidder_id=?, item_increment_time=?, item_desc=?, category_level_1=?, category_level_2=?, category_level_3=?");
		
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+itemId_wip);

			System.out.println("itemId_wip "+itemId_wip);
			
			
		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        stmt.setBigDecimal(1, lot_id);
	        stmt.setBigDecimal(2, item_id);
	        stmt.setBigDecimal(3, auction_id);
	        stmt.setBigDecimal(4, reference_no);
	        stmt.setBigDecimal(5, target_price);
	        stmt.setBigDecimal(6, reserve_price);
	        stmt.setBigDecimal(7, amount_bid);
	        stmt.setBigDecimal(8, amount_buy);
	        stmt.setInt(9, action_taken);
	        stmt.setInt(10, is_buy);
	        stmt.setInt(11, is_bid);
	        stmt.setBigDecimal(12, buy_price);
	        
	        
	        stmt.setInt(13, bidder_id);
	        stmt.setInt(14, item_increment_time);
	        stmt.setString(15, item_desc);

	        stmt.setInt(16, category_level_1);
	        stmt.setInt(17, category_level_2);
	        stmt.setInt(18, category_level_3);
	        
	        stmt.setTimestamp(19, sqlDate_t);
	        stmt.setInt(20, user_id);

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	i = null;
	        }else{
	        	i = new Item(); 
            	i.setId(itemId_wip);
            	/*
            	i.setEmail_address(emailAddress);
            	i.setFirst_name(firstName);
            	i.setLast_name(lastName);
            	i.setMobile_no_1(mobileNo1);
            	i.setMobile_no_2(mobileNo2);
            	
            	i.setGender(gender);
            	i.setRole(role);
            	i.setBidder_no(bidderNo);
            	i.setReserve_bidder_no(reserveBidderNo);
            	i.setCompany(company);
            	i.setStatus(userStatus);
            	i.setActive(active);
            	i.setLandline_no(landLineNo);
            	
            	i.setNews_letter(newsLetter);
            	i.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	i.setVerification_email_key(verificationEmailKey);
            	i.setDate_registration(registrationDate_t);
            	i.setDate_password_change(passwordChangeDate_t);
            	i.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	i.setBirth_date(birthDate_d);
            	i.setPass_word(passWord);
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
	
		return i;
	}
	
	public Item updateItemOnUpload(
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
			BigDecimal auction_id,
			Timestamp last_date_sync,
			Integer user_id
		){
	
		Connection conn = null;
		
		int affectedRows = 0;
		
		Item i = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection2();
	
			StringBuilder sb = new StringBuilder("UPDATE item SET item_id=?, lot_id=?, status_id=?, reference_no=?, pullout_id=?, target_price=?");
	
			sb.append(", reserve_price=?, rate=?, amount_bid=?, received_items_id=?, qt_remarks=?, assess_value=?, payment_status=?");
			
			sb.append(", bidder_id=?, payables_id=?, product_code=?, srp=?, consignor_id=?, item_desc=?");
			
			sb.append(", delivery_receipt_id=?, auction_id=?, synched_date=?");
		
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where item_id="+item_id);
	
			System.out.println("item_id "+item_id);
			
	

		
		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

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
	        stmt.setBigDecimal(21, auction_id);
	        stmt.setTimestamp(22, last_date_sync);
	        stmt.setTimestamp(23, sqlDate_t);
	        stmt.setInt(24, user_id);
	
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	i = null;
	        }else{
	        	i = new Item(); 
	        	i.setId(item_id);
	        	/*
	        	i.setEmail_address(emailAddress);
	        	i.setFirst_name(firstName);
	        	i.setLast_name(lastName);
	        	i.setMobile_no_1(mobileNo1);
	        	i.setMobile_no_2(mobileNo2);
	        	
	        	i.setGender(gender);
	        	i.setRole(role);
	        	i.setBidder_no(bidderNo);
	        	i.setReserve_bidder_no(reserveBidderNo);
	        	i.setCompany(company);
	        	i.setStatus(userStatus);
	        	i.setActive(active);
	        	i.setLandline_no(landLineNo);
	        	
	        	i.setNews_letter(newsLetter);
	        	i.setNews_letter_registration_date(newsLetterRegistrationDate_t);
	        	i.setVerification_email_key(verificationEmailKey);
	        	i.setDate_registration(registrationDate_t);
	        	i.setDate_password_change(passwordChangeDate_t);
	        	i.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
	        	i.setBirth_date(birthDate_d);
	        	i.setPass_word(passWord);
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
	
		return i;
	}

	
	public Item updateItemImage(
				File file,
				Integer user_id,
				BigDecimal itemId_wip
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Item i = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			
			String imageField = "image";
			
			FileInputStream fis = null;
			if(file!=null){
				try {
					fis = new FileInputStream ( file );
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


			
			StringBuilder sb = new StringBuilder("Update item");

					sb.append(" SET image=?");
				
			
			
			
			//sb.append(", item_desc=?, terms_and_conditions=?, coordinator=?, visibility=?, item_type=?, active=?, item_id=?");
			
			//sb.append(", no_of_lots=?, no_of_items=?, item_item_increment_time=?, bid_deposit=?, date_sync=?, status=?");
		
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+itemId_wip);

		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


			        stmt.setBlob (1, fis);
			        stmt.setTimestamp(2, sqlDate_t);
			        stmt.setBigDecimal(3, itemId_wip);
					
				
			

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	i = null;
	        }else{
	        	i = new Item(); 
            	i.setId(itemId_wip);
            	/*
            	i.setEmail_address(emailAddress);
            	i.setFirst_name(firstName);
            	i.setLast_name(lastName);
            	i.setMobile_no_1(mobileNo1);
            	i.setMobile_no_2(mobileNo2);
            	
            	i.setGender(gender);
            	i.setRole(role);
            	i.setBidder_no(bidderNo);
            	i.setReserve_bidder_no(reserveBidderNo);
            	i.setCompany(company);
            	i.setStatus(userStatus);
            	i.setActive(active);
            	i.setLandline_no(landLineNo);
            	
            	i.setNews_letter(newsLetter);
            	i.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	i.setVerification_email_key(verificationEmailKey);
            	i.setDate_registration(registrationDate_t);
            	i.setDate_password_change(passwordChangeDate_t);
            	i.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	i.setBirth_date(birthDate_d);
            	i.setPass_word(passWord);
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
	
		return i;
	}
	
	
	
	public List<Item> getItemList(){

		List<Item> aList = new ArrayList<Item>();
		

		StringBuilder sb = new StringBuilder("Select id, lot_id, auction_id, item_id, reference_no, target_price");

		sb.append(", reserve_price, amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price");
		
		sb.append(", bidder_id, item_increment_time, item_desc, image, category_level_1, category_level_2, category_level_3, currency, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from item");
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Item i = null;

			while(rs.next()){
				i = new Item();
				i.setId(rs.getBigDecimal("id"));
				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setReference_no(rs.getBigDecimal("reference_no"));
				i.setTarget_price(rs.getBigDecimal("target_price"));
				i.setReserve_price(rs.getBigDecimal("reserve_price"));
				i.setAmount_bid(rs.getBigDecimal("amount_bid"));
				i.setAmount_buy(rs.getBigDecimal("amount_buy"));
				i.setAction_taken(rs.getInt("action_taken"));
				i.setIs_buy(rs.getInt("is_buy"));
				i.setIs_bid(rs.getInt("is_bid"));
				i.setBuy_price(rs.getBigDecimal("buy_price"));
				i.setBidder_id(rs.getInt("bidder_id"));
				i.setItem_increment_time(rs.getInt("item_increment_time"));
				i.setItem_desc(rs.getString("item_desc"));
				i.setImageBytes(rs.getBytes("image"));
				i.setCategory_level_1(rs.getInt("category_level_1"));
				i.setCategory_level_2(rs.getInt("category_level_2"));
				i.setCategory_level_3(rs.getInt("category_level_3"));
				i.setCurrency(rs.getInt("currency"));
				i.setBid_count(rs.getInt("bid_count"));
				
				//SystemBean - start
				i.setDate_created(rs.getTimestamp("date_created"));
				i.setDate_updated(rs.getTimestamp("date_updated"));
				i.setCreated_by(rs.getInt("created_by"));
				i.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				aList.add(i);
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
		
		return aList;
	}
	
	public List<Item> getItemListByAuctionId(BigDecimal auction_id){

		List<Item> aList = new ArrayList<Item>();
		

		StringBuilder sb = new StringBuilder("Select id, lot_id, auction_id, item_id, reference_no, target_price");

		sb.append(", reserve_price, amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price");
		
		sb.append(", bidder_id, item_increment_time, item_desc, image, category_level_1, category_level_2, category_level_3, currency, bid_count");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from item where auction_id="+auction_id);
		
		sb.append(" order by item_desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Item i = null;

			while(rs.next()){
				i = new Item();
				i.setId(rs.getBigDecimal("id"));
				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setReference_no(rs.getBigDecimal("reference_no"));
				i.setTarget_price(rs.getBigDecimal("target_price"));
				i.setReserve_price(rs.getBigDecimal("reserve_price"));
				i.setAmount_bid(rs.getBigDecimal("amount_bid"));
				i.setAmount_buy(rs.getBigDecimal("amount_buy"));
				i.setAction_taken(rs.getInt("action_taken"));
				i.setIs_buy(rs.getInt("is_buy"));
				i.setIs_bid(rs.getInt("is_bid"));
				i.setBuy_price(rs.getBigDecimal("buy_price"));
				i.setBidder_id(rs.getInt("bidder_id"));
				i.setItem_increment_time(rs.getInt("item_increment_time"));
				i.setItem_desc(rs.getString("item_desc"));
				i.setImageBytes(rs.getBytes("image"));
				i.setCategory_level_1(rs.getInt("category_level_1"));
				i.setCategory_level_2(rs.getInt("category_level_2"));
				i.setCategory_level_3(rs.getInt("category_level_3"));
				i.setCurrency(rs.getInt("currency"));
				i.setBid_count(rs.getInt("bid_count"));
				
				//SystemBean - start
				i.setDate_created(rs.getTimestamp("date_created"));
				i.setDate_updated(rs.getTimestamp("date_updated"));
				i.setCreated_by(rs.getInt("created_by"));
				i.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				aList.add(i);
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
		
		return aList;
	}
	
	
	public List<Item> getLotItemsById(BigDecimal lot_id){

		List<Item> iL = new ArrayList<Item>();
		

		StringBuilder sb = new StringBuilder("Select id, lot_id, auction_id, item_id, reference_no, target_price");

		sb.append(", reserve_price, amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price");
		
		sb.append(", bidder_id, item_increment_time, item_desc, image, category_level_1, category_level_2, category_level_3, currency, bid_count, synched_date");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from item where lot_id="+lot_id.toString());
		
		sb.append(" order by item_desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Item i = null;

			while(rs.next()){
				i = new Item();
				i.setId(rs.getBigDecimal("id"));
				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setReference_no(rs.getBigDecimal("reference_no"));
				i.setTarget_price(rs.getBigDecimal("target_price"));
				i.setReserve_price(rs.getBigDecimal("reserve_price"));
				i.setAmount_bid(rs.getBigDecimal("amount_bid"));
				i.setAmount_buy(rs.getBigDecimal("amount_buy"));
				i.setAction_taken(rs.getInt("action_taken"));
				i.setIs_buy(rs.getInt("is_buy"));
				i.setIs_bid(rs.getInt("is_bid"));
				i.setBuy_price(rs.getBigDecimal("buy_price"));
				i.setBidder_id(rs.getInt("bidder_id"));
				i.setItem_increment_time(rs.getInt("item_increment_time"));
				i.setItem_desc(rs.getString("item_desc"));
				i.setImageBytes(rs.getBytes("image"));
				i.setCategory_level_1(rs.getInt("category_level_1"));
				i.setCategory_level_2(rs.getInt("category_level_2"));
				i.setCategory_level_3(rs.getInt("category_level_3"));
				i.setCurrency(rs.getInt("currency"));
				i.setBid_count(rs.getInt("bid_count"));
				i.setSynched_date(rs.getTimestamp("synched_date"));
				
				
				//SystemBean - start
				i.setDate_created(rs.getTimestamp("date_created"));
				i.setDate_updated(rs.getTimestamp("date_updated"));
				i.setCreated_by(rs.getInt("created_by"));
				i.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				iL.add(i);

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
		
		return iL;
	}
	
	
	
	public HashMap<BigDecimal, Item> getItemHMByLotId(BigDecimal lot_id){

		HashMap<BigDecimal, Item> iHM = new HashMap<BigDecimal, Item>();
		

		StringBuilder sb = new StringBuilder("Select id, lot_id, auction_id, item_id, reference_no, target_price");

		sb.append(", reserve_price, amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price");
		
		sb.append(", bidder_id, item_increment_time, item_desc, image, category_level_1, category_level_2, category_level_3, currency, bid_count, synched_date");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from item where lot_id="+lot_id);
		
		sb.append(" order by item_desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = null;
			
			if(conn!=null && !conn.isClosed()){
				stmt = conn.createStatement();
			}else{
				DBConnection dbConn = new DBConnection();
				conn = dbConn.getConnection();
				stmt = conn.createStatement();
			}
			
			
			if(stmt!=null && !stmt.isClosed()){
				stmt = conn.createStatement();
			}else{
				//DBConnection dbConn = new DBConnection();
				conn = dbConn.getConnection();
				stmt = conn.createStatement();
			}
			
			
			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Item i = null;

			while(rs.next()){
				i = new Item();
				i.setId(rs.getBigDecimal("id"));
				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setReference_no(rs.getBigDecimal("reference_no"));
				i.setTarget_price(rs.getBigDecimal("target_price"));
				i.setReserve_price(rs.getBigDecimal("reserve_price"));
				i.setAmount_bid(rs.getBigDecimal("amount_bid"));
				i.setAmount_buy(rs.getBigDecimal("amount_buy"));
				i.setAction_taken(rs.getInt("action_taken"));
				i.setIs_buy(rs.getInt("is_buy"));
				i.setIs_bid(rs.getInt("is_bid"));
				i.setBuy_price(rs.getBigDecimal("buy_price"));
				i.setBidder_id(rs.getInt("bidder_id"));
				i.setItem_increment_time(rs.getInt("item_increment_time"));
				i.setItem_desc(rs.getString("item_desc"));
				i.setImageBytes(rs.getBytes("image"));
				i.setCategory_level_1(rs.getInt("category_level_1"));
				i.setCategory_level_2(rs.getInt("category_level_2"));
				i.setCategory_level_3(rs.getInt("category_level_3"));
				i.setCurrency(rs.getInt("currency"));
				i.setBid_count(rs.getInt("bid_count"));
				i.setSynched_date(rs.getTimestamp("synched_date"));
				
				
				//SystemBean - start
				i.setDate_created(rs.getTimestamp("date_created"));
				i.setDate_updated(rs.getTimestamp("date_updated"));
				i.setCreated_by(rs.getInt("created_by"));
				i.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				iHM.put(i.getItem_id(), i);
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
		
		return iHM;
	}
	
	public HashMap<BigDecimal, Item> getItemHMByAuctionId_SetReferenceNo(BigDecimal auction_id){

		HashMap<BigDecimal, Item> iHM = new HashMap<BigDecimal, Item>();
		

		StringBuilder sb = new StringBuilder("Select id, lot_id, auction_id, item_id, reference_no, target_price");

		sb.append(", reserve_price, amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price");
		
		sb.append(", bidder_id, item_increment_time, item_desc, image, category_level_1, category_level_2, category_level_3, currency, bid_count, synched_date");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from item where auction_id="+auction_id);
		
		sb.append(" order by item_desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = null;
			
			if(conn!=null && !conn.isClosed()){
				stmt = conn.createStatement();
			}else{
				DBConnection dbConn = new DBConnection();
				conn = dbConn.getConnection();
				stmt = conn.createStatement();
			}
			
			
			if(stmt!=null && !stmt.isClosed()){
				stmt = conn.createStatement();
			}else{
				//DBConnection dbConn = new DBConnection();
				conn = dbConn.getConnection();
				stmt = conn.createStatement();
			}
			
			
			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Item i = null;

			while(rs.next()){
				i = new Item();
				i.setId(rs.getBigDecimal("id"));
				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setReference_no(rs.getBigDecimal("reference_no"));
				i.setTarget_price(rs.getBigDecimal("target_price"));
				i.setReserve_price(rs.getBigDecimal("reserve_price"));
				i.setAmount_bid(rs.getBigDecimal("amount_bid"));
				i.setAmount_buy(rs.getBigDecimal("amount_buy"));
				i.setAction_taken(rs.getInt("action_taken"));
				i.setIs_buy(rs.getInt("is_buy"));
				i.setIs_bid(rs.getInt("is_bid"));
				i.setBuy_price(rs.getBigDecimal("buy_price"));
				i.setBidder_id(rs.getInt("bidder_id"));
				i.setItem_increment_time(rs.getInt("item_increment_time"));
				i.setItem_desc(rs.getString("item_desc"));
				i.setImageBytes(rs.getBytes("image"));
				i.setCategory_level_1(rs.getInt("category_level_1"));
				i.setCategory_level_2(rs.getInt("category_level_2"));
				i.setCategory_level_3(rs.getInt("category_level_3"));
				i.setCurrency(rs.getInt("currency"));
				i.setBid_count(rs.getInt("bid_count"));
				
				i.setSynched_date(rs.getTimestamp("synched_date"));
				
				
				//SystemBean - start
				
				i.setDate_updated(rs.getTimestamp("date_updated"));
				i.setCreated_by(rs.getInt("created_by"));
				i.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				iHM.put(i.getReference_no(), i);
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
		
		return iHM;
	}
	
	
	public HashMap<BigDecimal, Item> getItemHMByAuctionId_SetItemId(BigDecimal auction_id){

		HashMap<BigDecimal, Item> iHM = new HashMap<BigDecimal, Item>();
		

		StringBuilder sb = new StringBuilder("Select id, lot_id, auction_id, item_id, reference_no, target_price");

		sb.append(", reserve_price, amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price");
		
		sb.append(", bidder_id, item_increment_time, item_desc, image, category_level_1, category_level_2, category_level_3, currency, bid_count, synched_date");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from item where auction_id="+auction_id);
		
		sb.append(" order by item_desc");

		Connection conn = null;
		java.sql.Statement stmt = null;
		
		
		
		
		try {
			
			
			try{
				stmt = conn.prepareStatement(sb.toString());
			}catch(Exception ex){
				conn = dbConn.getConnection3();
				//stmt = conn.prepareStatement(sql);
				
				try{
					stmt = conn.prepareStatement(sb.toString());
				}catch(Exception ex1){
					conn = dbConn.getConnection2();
					stmt = conn.prepareStatement(sb.toString());
				}
				
			}
	        
			if(stmt.isClosed()){
				stmt = conn.prepareStatement(sb.toString());
			}
			
			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Item i = null;

			while(rs.next()){
				i = new Item();
				i.setId(rs.getBigDecimal("id"));
				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setReference_no(rs.getBigDecimal("reference_no"));
				i.setTarget_price(rs.getBigDecimal("target_price"));
				i.setReserve_price(rs.getBigDecimal("reserve_price"));
				i.setAmount_bid(rs.getBigDecimal("amount_bid"));
				i.setAmount_buy(rs.getBigDecimal("amount_buy"));
				i.setAction_taken(rs.getInt("action_taken"));
				i.setIs_buy(rs.getInt("is_buy"));
				i.setIs_bid(rs.getInt("is_bid"));
				i.setBuy_price(rs.getBigDecimal("buy_price"));
				i.setBidder_id(rs.getInt("bidder_id"));
				i.setItem_increment_time(rs.getInt("item_increment_time"));
				i.setItem_desc(rs.getString("item_desc"));
				i.setImageBytes(rs.getBytes("image"));
				i.setCategory_level_1(rs.getInt("category_level_1"));
				i.setCategory_level_2(rs.getInt("category_level_2"));
				i.setCategory_level_3(rs.getInt("category_level_3"));
				i.setCurrency(rs.getInt("currency"));
				i.setBid_count(rs.getInt("bid_count"));
				
				i.setSynched_date(rs.getTimestamp("synched_date"));
				
				
				//SystemBean - start
				
				i.setDate_updated(rs.getTimestamp("date_updated"));
				i.setCreated_by(rs.getInt("created_by"));
				i.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				iHM.put(i.getItem_id(), i);
			}

			rs.close();
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
		
		return iHM;
	}
	
	public HashMap<BigDecimal, Item> getItemHMByAuctionId_SetItemId2(BigDecimal auction_id){

		HashMap<BigDecimal, Item> iHM = new HashMap<BigDecimal, Item>();
		

		StringBuilder sb = new StringBuilder("Select id, lot_id, auction_id, item_id, reference_no, target_price");

		sb.append(", reserve_price, amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price");
		
		sb.append(", bidder_id, item_increment_time, item_desc, image, category_level_1, category_level_2, category_level_3, currency, bid_count, synched_date");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from item where auction_id="+auction_id);
		
		sb.append(" order by item_desc");

		Connection conn = null;
		java.sql.Statement stmt = null;
		
		
		
		
		try {
			
			
			try{
				stmt = conn.prepareStatement(sb.toString());
			}catch(Exception ex){
				conn = dbConn.getConnection5();
				//stmt = conn.prepareStatement(sql);
				
				try{
					stmt = conn.prepareStatement(sb.toString());
				}catch(Exception ex1){
					conn = dbConn.getConnection6();
					stmt = conn.prepareStatement(sb.toString());
				}
				
			}
	        
			if(stmt.isClosed()){
				stmt = conn.prepareStatement(sb.toString());
			}
			
			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Item i = null;

			while(rs.next()){
				i = new Item();
				i.setId(rs.getBigDecimal("id"));
				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setReference_no(rs.getBigDecimal("reference_no"));
				i.setTarget_price(rs.getBigDecimal("target_price"));
				i.setReserve_price(rs.getBigDecimal("reserve_price"));
				i.setAmount_bid(rs.getBigDecimal("amount_bid"));
				i.setAmount_buy(rs.getBigDecimal("amount_buy"));
				i.setAction_taken(rs.getInt("action_taken"));
				i.setIs_buy(rs.getInt("is_buy"));
				i.setIs_bid(rs.getInt("is_bid"));
				i.setBuy_price(rs.getBigDecimal("buy_price"));
				i.setBidder_id(rs.getInt("bidder_id"));
				i.setItem_increment_time(rs.getInt("item_increment_time"));
				i.setItem_desc(rs.getString("item_desc"));
				i.setImageBytes(rs.getBytes("image"));
				i.setCategory_level_1(rs.getInt("category_level_1"));
				i.setCategory_level_2(rs.getInt("category_level_2"));
				i.setCategory_level_3(rs.getInt("category_level_3"));
				i.setCurrency(rs.getInt("currency"));
				i.setBid_count(rs.getInt("bid_count"));
				
				i.setSynched_date(rs.getTimestamp("synched_date"));
				
				
				//SystemBean - start
				
				i.setDate_updated(rs.getTimestamp("date_updated"));
				i.setCreated_by(rs.getInt("created_by"));
				i.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				iHM.put(i.getItem_id(), i);
			}

			rs.close();
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
		
		return iHM;
	}
	
	
	/*
	
	public List<Item> getItemListByTypeAndActive(Integer itemType){

		List<Item> aList = new ArrayList<Item>();
		

		StringBuilder sb = new StringBuilder("Select id, lot_id, auction_id, item_id, reference_no, target_price");

		sb.append(", reserve_price, amount_bid, amount_buy, action_taken, is_buy, is_bid, buy_price");
		
		sb.append(", bidder_id, item_increment_time, item_desc, image, category_level_1, category_level_2, category_level_3");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from item where item_type ="+itemType+" and active = 1");
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Item i = null;

			while(rs.next()){
				i = new Item();
				i.setId(rs.getInt("id"));
				i.setItem_name(rs.getString("item_name"));
				i.setItem_no(rs.getInt("item_no"));
				i.setLocation(rs.getString("location"));
				i.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				i.setStart_date_time(rs.getTimestamp("start_date_time"));
				i.setEnd_date_time(rs.getTimestamp("end_date_time"));
				i.setItem_desc(rs.getString("item_desc"));
				i.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				i.setCoordinator(rs.getInt("coordinator"));
				i.setVisibility(rs.getInt("visibility"));
				i.setItem_item_closing(rs.getInt("item_item_closing"));
				i.setItem_type(rs.getInt("item_type"));
				i.setItem_id(rs.getInt("item_id"));
            	i.setStatus(rs.getInt("status"));
            	i.setActive(rs.getInt("active"));
				i.setNo_of_lots(rs.getInt("no_of_lots"));
				i.setNo_of_items(rs.getInt("no_of_items"));
				i.setItem_item_increment_time(rs.getInt("item_item_increment_time"));
				i.setBid_deposit(rs.getInt("bid_deposit"));
				i.setDate_sync(rs.getTimestamp("date_sync"));
				
            	i.setImageBytes(rs.getBytes("image"));
            	i.setImageSmallBytes(rs.getBytes("image_small"));

				//SystemBean - start
				i.setDate_created(rs.getTimestamp("date_created"));
				i.setDate_updated(rs.getTimestamp("date_updated"));
				i.setCreated_by(rs.getInt("created_by"));
				i.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				aList.add(a);
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
		
		return aList;
	}
	
	*/
	
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        //writeBlob(122, "johndoe_resume.pdf");
 
    }

}

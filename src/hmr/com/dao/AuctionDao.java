package hmr.com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hmr.com.util.DBConnection;
import hmr.com.bean.Auction;

public class AuctionDao extends DBConnection {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public AuctionDao(){
	}
	
	public AuctionDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	
public Auction getAuctionByToken(String token){

		Auction a = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");

		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, image, image_small, auction_name, category_level_1, one_lot_per_bidder");
		
		sb.append(", one_start_bid, bid_qualifier_price, token, auto_send_post_notification");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction where token = '"+token+"'");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());

			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				a = new Auction();

				a.setId(rs.getBigDecimal("id"));

				a.setAuction_name(rs.getString("auction_name"));	
				a.setAuction_no(rs.getBigDecimal("auction_no"));
				a.setLocation(rs.getString("location"));
				a.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				a.setStart_date_time(rs.getTimestamp("start_date_time"));
				a.setEnd_date_time(rs.getTimestamp("end_date_time"));
				a.setAuction_desc(rs.getString("auction_desc"));
				a.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				a.setCoordinator(rs.getInt("coordinator"));
				a.setVisibility(rs.getInt("visibility"));
				a.setAuction_item_closing(rs.getInt("auction_item_closing"));
				a.setAuction_type(rs.getInt("auction_type"));
				a.setAuction_id(rs.getBigDecimal("auction_id"));
            	a.setStatus(rs.getInt("status"));
            	a.setActive(rs.getInt("active"));
				a.setNo_of_lots(rs.getInt("no_of_lots"));
				a.setNo_of_items(rs.getInt("no_of_items"));
				a.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				a.setBid_deposit(rs.getInt("bid_deposit"));
				a.setDate_sync(rs.getTimestamp("date_sync"));
				a.setCategory_level_1(rs.getInt("category_level_1"));
				a.setOne_lot_per_bidder(rs.getInt("one_lot_per_bidder"));
				a.setOne_start_bid(rs.getInt("one_start_bid"));
				a.setBid_qualifier_price(rs.getInt("bid_qualifier_price"));
				a.setToken(rs.getString("token"));
				a.setAuto_send_post_notification(rs.getInt("auto_send_post_notification"));

            	a.setDate_created(rs.getTimestamp("date_created"));
            	a.setCreated_by(rs.getInt("created_by"));
            	a.setDate_updated(rs.getTimestamp("date_updated"));
            	a.setUpdated_by(rs.getInt("updated_by"));

			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			

		}
		
		return a;
	}
	
	public Auction getAuctionById(Integer id){

		Auction a = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");

		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, image, image_small, auction_name, category_level_1, one_lot_per_bidder");
		
		sb.append(", one_start_bid, bid_qualifier_price, token, auto_send_post_notification");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction where id ="+id);

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				a = new Auction();

				a.setId(rs.getBigDecimal("id"));

				a.setAuction_name(rs.getString("auction_name"));	
				a.setAuction_no(rs.getBigDecimal("auction_no"));
				a.setLocation(rs.getString("location"));
				a.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				a.setStart_date_time(rs.getTimestamp("start_date_time"));
				a.setEnd_date_time(rs.getTimestamp("end_date_time"));
				a.setAuction_desc(rs.getString("auction_desc"));
				a.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				a.setCoordinator(rs.getInt("coordinator"));
				a.setVisibility(rs.getInt("visibility"));
				a.setAuction_item_closing(rs.getInt("auction_item_closing"));
				a.setAuction_type(rs.getInt("auction_type"));
				a.setAuction_id(rs.getBigDecimal("auction_id"));
            	a.setStatus(rs.getInt("status"));
            	a.setActive(rs.getInt("active"));
				a.setNo_of_lots(rs.getInt("no_of_lots"));
				a.setNo_of_items(rs.getInt("no_of_items"));
				a.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				a.setBid_deposit(rs.getInt("bid_deposit"));
				a.setDate_sync(rs.getTimestamp("date_sync"));
				a.setCategory_level_1(rs.getInt("category_level_1"));
				a.setOne_lot_per_bidder(rs.getInt("one_lot_per_bidder"));
				a.setOne_start_bid(rs.getInt("one_start_bid"));
				a.setBid_qualifier_price(rs.getInt("bid_qualifier_price"));
				a.setToken(rs.getString("token"));
				a.setAuto_send_post_notification(rs.getInt("auto_send_post_notification"));

            	a.setDate_created(rs.getTimestamp("date_created"));
            	a.setCreated_by(rs.getInt("created_by"));
            	a.setDate_updated(rs.getTimestamp("date_updated"));
            	a.setUpdated_by(rs.getInt("updated_by"));

			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
		}
		
		return a;
	}
	
	public Auction getAuctionById(BigDecimal id){

		Auction a = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");

		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, image, image_small, auction_name, category_level_1, one_lot_per_bidder");
		
		sb.append(", one_start_bid, bid_qualifier_price, token, auto_send_post_notification");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction where id ="+id);

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());			
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				a = new Auction();

				a.setId(rs.getBigDecimal("id"));

				a.setAuction_name(rs.getString("auction_name"));	
				a.setAuction_no(rs.getBigDecimal("auction_no"));
				a.setLocation(rs.getString("location"));
				a.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				a.setStart_date_time(rs.getTimestamp("start_date_time"));
				a.setEnd_date_time(rs.getTimestamp("end_date_time"));
				a.setAuction_desc(rs.getString("auction_desc"));
				a.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				a.setCoordinator(rs.getInt("coordinator"));
				a.setVisibility(rs.getInt("visibility"));
				a.setAuction_item_closing(rs.getInt("auction_item_closing"));
				a.setAuction_type(rs.getInt("auction_type"));
				a.setAuction_id(rs.getBigDecimal("auction_id"));
            	a.setStatus(rs.getInt("status"));
            	a.setActive(rs.getInt("active"));
				a.setNo_of_lots(rs.getInt("no_of_lots"));
				a.setNo_of_items(rs.getInt("no_of_items"));
				a.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				a.setBid_deposit(rs.getInt("bid_deposit"));
				a.setDate_sync(rs.getTimestamp("date_sync"));
				a.setCategory_level_1(rs.getInt("category_level_1"));
				a.setOne_lot_per_bidder(rs.getInt("one_lot_per_bidder"));
				a.setOne_start_bid(rs.getInt("one_start_bid"));
				a.setBid_qualifier_price(rs.getInt("bid_qualifier_price"));
				a.setToken(rs.getString("token"));
				a.setAuto_send_post_notification(rs.getInt("auto_send_post_notification"));
				
            	a.setDate_created(rs.getTimestamp("date_created"));
            	a.setCreated_by(rs.getInt("created_by"));
            	a.setDate_updated(rs.getTimestamp("date_updated"));
            	a.setUpdated_by(rs.getInt("updated_by"));

			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			

		}
		
		return a;
	}
	
	
	public Auction getAuctionByAuctionId(BigDecimal auction_id){

		Auction a = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");

		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, image, image_small, auction_name, category_level_1, one_lot_per_bidder");
		
		sb.append(", one_start_bid, bid_qualifier_price, token, auto_send_post_notification, lot_per_auction_item_increment_time");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction where auction_id ="+auction_id);


		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());	
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				a = new Auction();

				a.setId(rs.getBigDecimal("id"));

				a.setAuction_name(rs.getString("auction_name"));	
				a.setAuction_no(rs.getBigDecimal("auction_no"));
				a.setLocation(rs.getString("location"));
				a.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				a.setStart_date_time(rs.getTimestamp("start_date_time"));
				a.setEnd_date_time(rs.getTimestamp("end_date_time"));
				a.setAuction_desc(rs.getString("auction_desc"));
				a.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				a.setCoordinator(rs.getInt("coordinator"));
				a.setVisibility(rs.getInt("visibility"));
				a.setAuction_item_closing(rs.getInt("auction_item_closing"));
				a.setAuction_type(rs.getInt("auction_type"));
				a.setAuction_id(rs.getBigDecimal("auction_id"));
            	a.setStatus(rs.getInt("status"));
            	a.setActive(rs.getInt("active"));
				a.setNo_of_lots(rs.getInt("no_of_lots"));
				a.setNo_of_items(rs.getInt("no_of_items"));
				a.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				a.setBid_deposit(rs.getInt("bid_deposit"));
				a.setDate_sync(rs.getTimestamp("date_sync"));
				a.setCategory_level_1(rs.getInt("category_level_1"));
				a.setOne_lot_per_bidder(rs.getInt("one_lot_per_bidder"));
				
				a.setOne_start_bid(rs.getInt("one_start_bid"));
				a.setBid_qualifier_price(rs.getInt("bid_qualifier_price"));
				a.setToken(rs.getString("token"));
				a.setAuto_send_post_notification(rs.getInt("auto_send_post_notification"));
				a.setLot_per_auction_item_increment_time(rs.getInt("lot_per_auction_item_increment_time"));
				
	
            	a.setDate_created(rs.getTimestamp("date_created"));
            	a.setCreated_by(rs.getInt("created_by"));
            	a.setDate_updated(rs.getTimestamp("date_updated"));
            	a.setUpdated_by(rs.getInt("updated_by"));
            	
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			/*
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
		
		return a;
	}
	
	public Auction getAuctionImageById(Integer id, String size){
		
		Auction a = null;
		
		StringBuilder sb = new StringBuilder("Select id, image, image_small");

		sb.append(" from auction where id ="+id);

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());	
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				a = new Auction();

				a.setId(rs.getBigDecimal("id"));
            	a.setImageBytes(rs.getBytes("image"));
            	a.setImageSmallBytes(rs.getBytes("image_small"));

			}

			//rs.close();
			//stmt.close();
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
		
		return a;
	}

	public Auction insertAuctionOnCreate(
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
				Integer category_level_1,
				Integer one_lot_per_bidder,
				Integer one_start_bid,
				Integer bid_qualifier_price,
				Integer auto_send_post_notification,
				Integer user_id
			) {

		int affectedRows = 0;
		
		Auction a = null;
		
		StringBuilder sb = new StringBuilder("INSERT into auction (auction_no, location, bid_deposit_amount, start_date_time, end_date_time");

		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status");
		
		sb.append(", date_created, created_by, auction_name, category_level_1, one_lot_per_bidder, one_start_bid, bid_qualifier_price, auto_send_post_notification)");
		
		sb.append(" VALUES(");
		
		sb.append(" ?, ?, ?, ?, ?");
		sb.append(",?, ?, ?, ?, ?, ?, ?");
		sb.append(",?, ?, ?, ?, ?, ?");
		sb.append(",?, ?, ?, ?, ?, ?, ?");
		
		sb.append(")");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection2();
			
			System.out.println("sql : "+sb.toString());

			//String SERVER_DIRECTORY_HMR_IMAGES = (String)req.getSession().getAttribute("SERVER_DIRECTORY_HMR_IMAGES");
			
			//File image = new File(SERVER_DIRECTORY_HMR_IMAGES + "default-image.jpg");
			//FileInputStream fis = new FileInputStream ( image );
			
			
			//File image_small = new File(SERVER_DIRECTORY_HMR_IMAGES + "default-image-small.jpg");
			//FileInputStream fis_small = new FileInputStream ( image_small );

		    String sql = sb.toString();
	        
		    stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        //java.sql.Date date_sync_d = null;
	        java.sql.Timestamp date_sync_t = null;
	        if(date_sync!=null){
	        	//date_sync_d = new java.sql.Date(date_sync.getTime());
	        	date_sync_t = new java.sql.Timestamp(date_sync.getTime());
	        }
	        //java.sql.Date start_date_time_d = null;
	        java.sql.Timestamp start_date_time_t = null;
	        if(start_date_time!=null){
	        	//start_date_time_d = new java.sql.Date(start_date_time.getTime());
	        	start_date_time_t = new java.sql.Timestamp(start_date_time.getTime());
	        }
	        //java.sql.Date end_date_time_d = null;
	        java.sql.Timestamp end_date_time_t = null;
	        if(end_date_time!=null){
	        	//end_date_time_d = new java.sql.Date(end_date_time.getTime());
	        	end_date_time_t = new java.sql.Timestamp(end_date_time.getTime());
	        }

	        stmt.setBigDecimal(1, auction_no);
	        stmt.setString(2, location);
	        stmt.setBigDecimal(3, bid_deposit_amount);
	        stmt.setTimestamp(4, start_date_time_t);
	        stmt.setTimestamp(5, end_date_time_t);

	        //System.out.println("terms_and_conditions SAVING :"+terms_and_conditions);
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
	        
	        //stmt.setBlob (21, fis, (int) image.length() );
	        //stmt.setBlob (22, fis_small, (int) image_small.length() );
	        //stmt.setBlob (21, fis, (int) image.length() );
		    
	        stmt.setString(21, auction_name);
	        stmt.setInt(22, category_level_1);
	        stmt.setInt(23, one_lot_per_bidder);
	        stmt.setInt(24, one_start_bid);
	        stmt.setInt(25, bid_qualifier_price);
	        stmt.setInt(26, auto_send_post_notification);

		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating auction failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	a = new Auction(); 
	            	a.setId(new BigDecimal(generatedKeys.getInt(1)));
	            	
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
	                throw new SQLException("Creating auction failed, no ID obtained.");
	            }
	        }
		    
			//stmt.close();
		} catch (SQLException e) {
			//throw new RuntimeException(e);
		//} catch (FileNotFoundException e) {

		//	e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			

		}

		return a;
	}
	
	public Auction insertAuctionOnUpload(
			BigDecimal auction_id,
			BigDecimal auction_no,
			Timestamp auction_date,
			String location,
			BigDecimal default_premium,
			String auction_name,
			Timestamp last_date_sync,
			Integer user_id
			) {

		int affectedRows = 0;
		
		Auction a = null;
		
		StringBuilder sb = new StringBuilder("INSERT into auction (auction_id, auction_no, start_date_time, end_date_time, location, default_premium, auction_name, date_sync, status");
		
		sb.append(", date_created, created_by)");
		
		sb.append(" VALUES(");
		
		sb.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?");
		sb.append(",?, ?");
		
		sb.append(")");
	
		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection2();
			
			System.out.println("sql : "+sb.toString());
			
			/*	
			String SERVER_DIRECTORY_HMR_IMAGES = null;
			
			try{
				SERVER_DIRECTORY_HMR_IMAGES = (String)req.getSession().getAttribute("SERVER_DIRECTORY_HMR_IMAGES");
				
			}catch(Exception ex){
				
				
				SERVER_DIRECTORY_HMR_IMAGES ="C:\\Work\\workspace\\HMR\\WebContent\\hmr\\images\\";
			}
			
			File image = new File(SERVER_DIRECTORY_HMR_IMAGES + "default-image.jpg");
			FileInputStream fis = new FileInputStream ( image );
			
			
			File image_small = new File(SERVER_DIRECTORY_HMR_IMAGES + "default-image-small.jpg");
			FileInputStream fis_small = new FileInputStream ( image_small );
*/
			
		    String sql = sb.toString();
	        stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	/*
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
	*/
	      
	        stmt.setBigDecimal(1, auction_id);
	        stmt.setBigDecimal(2, auction_no);
	        stmt.setTimestamp(3, auction_date);
	        stmt.setTimestamp(4, auction_date);
	        stmt.setString(5, location);
	        stmt.setBigDecimal(6, default_premium);
	        stmt.setString(7, auction_name);
	        
	        stmt.setTimestamp(8, last_date_sync);
	        stmt.setInt(9, 32);
	        
	        stmt.setTimestamp(10, sqlDate_t);
	        stmt.setInt(11, user_id);
	        
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating auction failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	a = new Auction(); 
	            	a.setId(new BigDecimal(generatedKeys.getInt(1)));
	            	a.setAuction_id(auction_id);
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
	                throw new SQLException("Creating auction failed, no ID obtained.");
	            }
	        }
		    
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		//} catch (FileNotFoundException e) {

		//	e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			

		}
	
		return a;
	}
	

	
	public Auction updateAuctionOnUpdate(
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
				Integer category_level_1,
				Integer one_lot_per_bidder,
				Integer one_start_bid,
				Integer bid_qualifier_price,
				Integer auto_send_post_notification,
				Integer lot_per_auction_item_increment_time,
				Integer user_id,
				BigDecimal auctionId_wip
			){

		int affectedRows = 0;
		
		Auction a = null;
		
		StringBuilder sb = new StringBuilder("Update auction SET auction_no=?, location=?, bid_deposit_amount=?, start_date_time=?, end_date_time=?");

		sb.append(", auction_desc=?, terms_and_conditions=?, coordinator=?, visibility=?, auction_type=?, active=?, auction_id=?");
		
		sb.append(", no_of_lots=?, no_of_items=?, auction_item_increment_time=?, bid_deposit=?, date_sync=?, status=?");
	
		sb.append(", date_updated=?, updated_by=?, auction_name=?, category_level_1=?, one_lot_per_bidder=?, one_start_bid=?, bid_qualifier_price=?, auto_send_post_notification=?, lot_per_auction_item_increment_time=?");
		
		sb.append(" where auction_id="+auctionId_wip);
	
		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection2();
			
			System.out.println("sql : "+sb.toString());

		    String sql = sb.toString();
		    
	        stmt = conn.prepareStatement(sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        //java.sql.Date date_sync_d = null;
	        java.sql.Timestamp date_sync_t = null;
	        if(date_sync!=null){
	        	//date_sync_d = new java.sql.Date(date_sync.getTime());
	        	date_sync_t = new java.sql.Timestamp(date_sync.getTime());
	        }
	        //java.sql.Date start_date_time_d = null;
	        java.sql.Timestamp start_date_time_t = null;
	        if(start_date_time!=null){
	        	//start_date_time_d = new java.sql.Date(start_date_time.getTime());
	        	start_date_time_t = new java.sql.Timestamp(start_date_time.getTime());
	        }
	        //java.sql.Date end_date_time_d = null;
	        java.sql.Timestamp end_date_time_t = null;
	        if(end_date_time!=null){
	        	//end_date_time_d = new java.sql.Date(end_date_time.getTime());
	        	end_date_time_t = new java.sql.Timestamp(end_date_time.getTime());
	        }
	        
	        //System.out.println("start_date_time_t SAVING :"+start_date_time_t);
	        //System.out.println("end_date_time_t SAVING :"+end_date_time_t);
	        //System.out.println("terms_and_conditions SAVING :"+terms_and_conditions);
	        
	        
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
	        stmt.setInt(22, category_level_1); 
	        stmt.setInt(23, one_lot_per_bidder); 
	         
	        stmt.setInt(24, one_start_bid); 
	        stmt.setInt(25, bid_qualifier_price);
	        stmt.setInt(26, auto_send_post_notification);
	        stmt.setInt(27, lot_per_auction_item_increment_time);

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	a = null;
	        }else{
	        	a = new Auction(); 
            	a.setId(auctionId_wip);
            	a.setBid_qualifier_price(bid_qualifier_price);
            	a.setEnd_date_time(a.getEnd_date_time());
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
			if (conn != null) {
				try {
				System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			

		}
	
		return a;
	}
	
	public Auction updateAuctionOnUpload(
			BigDecimal auction_id,
			BigDecimal auction_no,
			Timestamp auction_date,
			String location,
			BigDecimal default_premium,
			Timestamp last_date_sync,
			Integer user_id
			){
		
		int affectedRows = 0;
		
		Auction a = null;
		
		StringBuilder sb = new StringBuilder("Update auction SET auction_id=?, auction_no=?, start_date_time=?, end_date_time=?, location=?, default_premium=?, date_sync=?");

		sb.append(", date_updated=?, updated_by=?");
		
		sb.append(" where auction_id="+auction_id);
		
		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection2();
			
			System.out.println("sql : "+sb.toString());

		    String sql = sb.toString();
		    
	        stmt = conn.prepareStatement(sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        stmt.setBigDecimal(1, auction_id);
	        stmt.setBigDecimal(2, auction_no);
	        stmt.setTimestamp(3, auction_date);
	        stmt.setTimestamp(4, auction_date);
	        stmt.setString(5, location);
	        stmt.setBigDecimal(6, default_premium);
	        stmt.setTimestamp(7, last_date_sync);
	        stmt.setTimestamp(8, sqlDate_t);
	        stmt.setInt(9, user_id);
 
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	a = null;
	        }else{
	        	a = new Auction(); 
            	a.setId(auction_id);
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
		    

			//   stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			

		}
	
		return a;
	}
	
	/*
	
	public Auction updateAuctionImage(
				File file_small,
				File file,
				Integer user_id,
				BigDecimal auctionId_wip
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Auction a = null;
	
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
					e.printStackTrace();
				}
			}
			FileInputStream fis = null;
			if(file!=null){
				try {
					fis = new FileInputStream ( file );
				} catch (FileNotFoundException e) {
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
		    	a = null;
	        }else{
	        	a = new Auction(); 
            	a.setId(auctionId_wip);
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
            	*//*
	        }
		    

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
	
		return a;
	}
	
	*/
	
	public List<Auction> getAuctionList(){

		List<Auction> aList = new ArrayList<Auction>();
		
		StringBuilder sb = new StringBuilder("Select id, auction_name, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");
	
		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, category_level_1, one_lot_per_bidder");
		
		sb.append(", one_start_bid, bid_qualifier_price, token, auto_send_post_notification");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction");
		
		sb.append(" order by id desc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		PreparedStatement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection3();
			
			System.out.println("sql : "+sb.toString());

		    String sql = sb.toString();
		    
	        stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sb.toString());

			Auction a = null;

			while(rs.next()){
				a = new Auction();
				a.setId(rs.getBigDecimal("id"));
				a.setAuction_name(rs.getString("auction_name"));
				a.setAuction_no(rs.getBigDecimal("auction_no"));
				a.setLocation(rs.getString("location"));
				a.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				a.setStart_date_time(rs.getTimestamp("start_date_time"));
				a.setEnd_date_time(rs.getTimestamp("end_date_time"));
				a.setAuction_desc(rs.getString("auction_desc"));
				a.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				a.setCoordinator(rs.getInt("coordinator"));
				a.setVisibility(rs.getInt("visibility"));
				a.setAuction_item_closing(rs.getInt("auction_item_closing"));
				a.setAuction_type(rs.getInt("auction_type"));
				a.setAuction_id(rs.getBigDecimal("auction_id"));
            	a.setStatus(rs.getInt("status"));
            	a.setActive(rs.getInt("active"));
				a.setNo_of_lots(rs.getInt("no_of_lots"));
				a.setNo_of_items(rs.getInt("no_of_items"));
				a.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				a.setBid_deposit(rs.getInt("bid_deposit"));
				a.setDate_sync(rs.getTimestamp("date_sync"));
				a.setCategory_level_1(rs.getInt("category_level_1"));
				a.setOne_lot_per_bidder(rs.getInt("one_lot_per_bidder"));
				a.setOne_start_bid(rs.getInt("one_start_bid"));
				a.setBid_qualifier_price(rs.getInt("bid_qualifier_price"));
				a.setToken(rs.getString("token"));
				a.setAuto_send_post_notification(rs.getInt("auto_send_post_notification"));
				

				//SystemBean - start
				a.setDate_created(rs.getTimestamp("date_created"));
				a.setDate_updated(rs.getTimestamp("date_updated"));
				a.setCreated_by(rs.getInt("created_by"));
				a.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				aList.add(a);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			

		}
		
		return aList;
	}
	
	public List<Auction> getAuctionListByTypeAndActive(Integer auctionType){

		List<Auction> aList = new ArrayList<Auction>();
		
		StringBuilder sb = new StringBuilder("Select id, auction_name, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");
	
		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, category_level_1, one_lot_per_bidder");
		
		sb.append(", one_start_bid, bid_qualifier_price, token, auto_send_post_notification");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction where auction_type ="+auctionType+" and active = 1 and start_date_time is not null and end_date_time is not null");
		
		sb.append(" order by end_date_time asc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection3();
			
			System.out.println("sql : "+sb.toString());
		
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sb.toString());

			Auction a = null;

			while(rs.next()){
				a = new Auction();
				a.setId(rs.getBigDecimal("id"));
				a.setAuction_name(rs.getString("auction_name"));
				a.setAuction_no(rs.getBigDecimal("auction_no"));
				a.setLocation(rs.getString("location"));
				a.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				a.setStart_date_time(rs.getTimestamp("start_date_time"));
				a.setEnd_date_time(rs.getTimestamp("end_date_time"));
				a.setAuction_desc(rs.getString("auction_desc"));
				a.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				a.setCoordinator(rs.getInt("coordinator"));
				a.setVisibility(rs.getInt("visibility"));
				a.setAuction_item_closing(rs.getInt("auction_item_closing"));
				a.setAuction_type(rs.getInt("auction_type"));
				a.setAuction_id(rs.getBigDecimal("auction_id"));
            	a.setStatus(rs.getInt("status"));
            	a.setActive(rs.getInt("active"));
				a.setNo_of_lots(rs.getInt("no_of_lots"));
				a.setNo_of_items(rs.getInt("no_of_items"));
				a.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				a.setBid_deposit(rs.getInt("bid_deposit"));
				a.setDate_sync(rs.getTimestamp("date_sync"));
				a.setCategory_level_1(rs.getInt("category_level_1"));
				a.setOne_lot_per_bidder(rs.getInt("one_lot_per_bidder"));
				a.setOne_start_bid(rs.getInt("one_start_bid"));
				a.setBid_qualifier_price(rs.getInt("bid_qualifier_price"));
				a.setToken(rs.getString("token"));
				a.setAuto_send_post_notification(rs.getInt("auto_send_post_notification"));
				
            	//a.setImageBytes(rs.getBytes("image"));
            	//a.setImageSmallBytes(rs.getBytes("image_small"));

				//SystemBean - start
				a.setDate_created(rs.getTimestamp("date_created"));
				a.setDate_updated(rs.getTimestamp("date_updated"));
				a.setCreated_by(rs.getInt("created_by"));
				a.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				aList.add(a);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			//throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			

		}
		
		return aList;
	}
	
	
	public List<Auction> getAuctionListEndingTodayActiveOpen(){
		
		Date dtNow = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String dtToday = sdf.format(dtNow);

		List<Auction> aList = new ArrayList<Auction>();
		
		StringBuilder sb = new StringBuilder("Select id, auction_name, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");
	
		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		//sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, image, image_small, category_level_1, one_lot_per_bidder");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, category_level_1, one_lot_per_bidder");
		
		sb.append(", one_start_bid, bid_qualifier_price, token, auto_send_post_notification");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction where active = 1 and status = 30 and end_date_time between '"+dtToday+" 00:00:01' and '"+dtToday+" 23:59:59' and start_date_time is not null and end_date_time is not null");
		
		sb.append(" order by end_date_time asc");

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();
			
			System.out.println("sql : "+sb.toString());
		
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sb.toString());

			Auction a = null;

			while(rs.next()){
				a = new Auction();
				a.setId(rs.getBigDecimal("id"));
				a.setAuction_name(rs.getString("auction_name"));
				a.setAuction_no(rs.getBigDecimal("auction_no"));
				a.setLocation(rs.getString("location"));
				a.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				a.setStart_date_time(rs.getTimestamp("start_date_time"));
				a.setEnd_date_time(rs.getTimestamp("end_date_time"));
				a.setAuction_desc(rs.getString("auction_desc"));
				a.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				a.setCoordinator(rs.getInt("coordinator"));
				a.setVisibility(rs.getInt("visibility"));
				a.setAuction_item_closing(rs.getInt("auction_item_closing"));
				a.setAuction_type(rs.getInt("auction_type"));
				a.setAuction_id(rs.getBigDecimal("auction_id"));
            	a.setStatus(rs.getInt("status"));
            	a.setActive(rs.getInt("active"));
				a.setNo_of_lots(rs.getInt("no_of_lots"));
				a.setNo_of_items(rs.getInt("no_of_items"));
				a.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				a.setBid_deposit(rs.getInt("bid_deposit"));
				a.setDate_sync(rs.getTimestamp("date_sync"));
				a.setCategory_level_1(rs.getInt("category_level_1"));
				a.setOne_lot_per_bidder(rs.getInt("one_lot_per_bidder"));
				a.setOne_start_bid(rs.getInt("one_start_bid"));
				a.setBid_qualifier_price(rs.getInt("bid_qualifier_price"));
				a.setToken(rs.getString("token"));
				a.setAuto_send_post_notification(rs.getInt("auto_send_post_notification"));
				
            	//a.setImageBytes(rs.getBytes("image"));
            	//a.setImageSmallBytes(rs.getBytes("image_small"));

				//SystemBean - start
				a.setDate_created(rs.getTimestamp("date_created"));
				a.setDate_updated(rs.getTimestamp("date_updated"));
				a.setCreated_by(rs.getInt("created_by"));
				a.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				aList.add(a);
			}

			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			

		}
		
		return aList;
	}
	
	public int updateToken(BigDecimal id, String token){
		
		StringBuilder sb = new StringBuilder("UPDATE `auction`");
		
		sb.append(" SET `token` = '" + token + "' ");
		
		sb.append("WHERE `auction`.`id` = "+ id.toString());

		Connection conn = null;
		
		DBConnection dbConn = null;
		
		Statement stmt = null;
		
		try {

			dbConn = new DBConnection();
			
			conn = dbConn.getConnection4();
			
			System.out.println("sql : "+sb.toString());
		
			stmt = conn.createStatement();

			return stmt.executeUpdate(sb.toString());
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				//System.out.println("conn closing : "+conn);
				conn.close();
				conn = null;
				//System.out.println("conn after closing : "+conn);
				} catch (SQLException e) {}
			}
			

		}
	}
	

}

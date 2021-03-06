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
import hmr.com.bean.Auction;
import hmr.com.bean.AuctionStaging;


public class AuctionStagingDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public AuctionStagingDao(){
		dbConn = new DBConnection();
	}
	
	public AuctionStagingDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	/*
	public Auction getAuction(String auctionId, String pw){
		
		Connection conn = null;

		Auction a = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, mobile_no_2");

		sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
		
		sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration, date_password_change, show_change_password_next_login, birth_date");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from auction where active=1 ");
		
		sb.append("and email_address = '"+userId+"' and pass_word = '"+pw+"'") ;

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				a = new Auction();
				a.setId(rs.getInt("id"));
				a.setEmail_address(rs.getString("email_address"));
				a.setFirst_name(rs.getString("first_name"));
				a.setLast_name(rs.getString("last_name"));
				a.setDate_created(rs.getTimestamp("date_created"));
				a.setRole(rs.getInt("role"));
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
		
		return a;
	}	
	
	
	public Auction getAuctionActive(String auctionId, String pw){
		
		Connection conn = null;

		Auction a = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, mobile_no_2");

		sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
		
		sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration, date_password_change, show_change_password_next_login, birth_date");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from auction where active=1 ");
		
		sb.append("and email_address = '"+userId+"' and pass_word = '"+pw+"'") ;

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				a = new Auction();
				a.setId(rs.getInt("id"));
				a.setEmail_address(rs.getString("email_address"));
				a.setFirst_name(rs.getString("first_name"));
				a.setLast_name(rs.getString("last_name"));
				a.setDate_created(rs.getTimestamp("date_created"));
				a.setRole(rs.getInt("role"));
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
		
		return a;
	}	
	*/
	
	public Auction getAuctionById(Integer id){
		
		Connection conn = null;

		Auction a = null;
		
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

            	a.setDate_created(rs.getTimestamp("date_created"));
            	a.setCreated_by(rs.getInt("created_by"));
            	a.setDate_updated(rs.getTimestamp("date_updated"));
            	a.setUpdated_by(rs.getInt("updated_by"));
            	
            	//InputStream binaryStream = rs.getBinaryStream("image");
            	//a.setImageInputStream(binaryStream);
            	
            	a.setImageBytes(rs.getBytes("image"));
            	a.setImageSmallBytes(rs.getBytes("image_small"));
            	
            	//a.
            	//binaryStream.
            	
            	System.out.println("asdfasdf df terms_and_conditions "+a.getTerms_and_conditions());
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
		
		return a;
	}
	
	public Auction getAuctionById(BigDecimal id){
		
		Connection conn = null;

		Auction a = null;
		
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

            	a.setDate_created(rs.getTimestamp("date_created"));
            	a.setCreated_by(rs.getInt("created_by"));
            	a.setDate_updated(rs.getTimestamp("date_updated"));
            	a.setUpdated_by(rs.getInt("updated_by"));
            	
            	//InputStream binaryStream = rs.getBinaryStream("image");
            	//a.setImageInputStream(binaryStream);
            	
            	a.setImageBytes(rs.getBytes("image"));
            	a.setImageSmallBytes(rs.getBytes("image_small"));
            	
            	//a.
            	//binaryStream.
            	
            	System.out.println("asdfasdf df terms_and_conditions "+a.getTerms_and_conditions());
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
		
		return a;
	}
	
	
	public Auction getAuctionByAuctionId(BigDecimal auction_id){
		
		Connection conn = null;

		Auction a = null;
		
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

            	a.setDate_created(rs.getTimestamp("date_created"));
            	a.setCreated_by(rs.getInt("created_by"));
            	a.setDate_updated(rs.getTimestamp("date_updated"));
            	a.setUpdated_by(rs.getInt("updated_by"));
            	
            	//InputStream binaryStream = rs.getBinaryStream("image");
            	//a.setImageInputStream(binaryStream);
            	
            	a.setImageBytes(rs.getBytes("image"));
            	a.setImageSmallBytes(rs.getBytes("image_small"));
            	
            	//a.
            	//binaryStream.
            	
            	System.out.println("asdfasdf df terms_and_conditions "+a.getTerms_and_conditions());
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
		
		return a;
	}
	
	public Auction getAuctionImageById(Integer id, String size){
		
		Connection conn = null;

		Auction a = null;
		
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
				a = new Auction();

				a.setId(rs.getBigDecimal("id"));
            	a.setImageBytes(rs.getBytes("image"));
            	a.setImageSmallBytes(rs.getBytes("image_small"));

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
		
		return a;
	}
	/*
	
	public Auction getAuction(String auctionId){
		
		Connection conn = null;

		Auction a = null;
		
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
				a = new Auction();
				a.setId(rs.getInt("id"));

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
		
		return a;
	}
	
	public Auction getAuctionRegistration(String auctionId, String vek){
		
		Connection conn = null;

		Auction a = null;
		
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
				a = new Auction();
				a.setId(rs.getInt("id"));

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
		
		return a;
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

	public int insertAuctionStagingOnSearch(
				BigDecimal auction_id,
				BigDecimal auction_no,
				Date auction_date,
				String location,
				BigDecimal default_premium,
				Timestamp last_date_sync
			) {

		
		int affectedRows = 0;
		
		AuctionStaging a = null;

		Connection conn = null;
		
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
			}else if(dbConn.getConnection7()!=null && !dbConn.getConnection7().isClosed()){
				conn = dbConn.getConnection7();
			}
			

			StringBuilder sb = new StringBuilder("INSERT into auction_staging (auction_id, auction_no, auction_date, location, default_premium, last_date_sync");

			sb.append(" )VALUES(");
			
			sb.append(" ?, ?, ?, ?, ?, ?");

			sb.append(")");
			
			
		    String sql = sb.toString();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        //java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        //java.sql.Date auction_date_d = null;
	        java.sql.Timestamp auction_date_t = null;
	        if(auction_date!=null){
	        	//auction_date_d = new java.sql.Date(auction_date.getTime());
	        	auction_date_t = new java.sql.Timestamp(auction_date.getTime());
	        }

	        
	        stmt.setBigDecimal(1, auction_id);
	        stmt.setBigDecimal(2, auction_no);
	        stmt.setTimestamp(3, auction_date_t);
	        stmt.setString(4, location);
	        stmt.setBigDecimal(5, default_premium);
	        stmt.setTimestamp(6, last_date_sync); 

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating auction failed, no rows affected.");
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

		return affectedRows;
	}
	
	
	public int deleteAuctionStagingOnSearch() {
	
	Connection conn = null;
	
	int affectedRows = 0;
	
	AuctionStaging a = null;

	try {
		DBConnection dbConn = new DBConnection();
		
		conn = dbConn.getConnection();
		

		StringBuilder sb = new StringBuilder("DELETE from auction_staging");
		
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
				Integer user_id,
				BigDecimal auctionId_wip
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Auction a = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

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
	
		return a;
	}
	
	
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
	
		return a;
	}
	
	
	
	public List<AuctionStaging> getAuctionStagingList(){

		List<AuctionStaging> asList = new ArrayList<AuctionStaging>();
		
		StringBuilder sb = new StringBuilder("Select auction_id, auction_no, auction_date, location, default_premium, last_date_sync");

		sb.append(" from auction_staging");
		
		sb.append(" order by auction_id desc");

		Connection conn = null;
		
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
			}else if(dbConn.getConnection7()!=null && !dbConn.getConnection7().isClosed()){
				conn = dbConn.getConnection7();
			}

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			AuctionStaging as = null;

			while(rs.next()){
				as = new AuctionStaging();
				
				as.setAuction_id(rs.getBigDecimal("auction_id"));
				as.setAuction_no(rs.getBigDecimal("auction_no") );
				as.setAuction_date(rs.getTimestamp("auction_date"));
				as.setLocation(rs.getString("location"));
				as.setDefault_premium(rs.getBigDecimal("default_premium"));
				as.setLast_date_sync(rs.getTimestamp("last_date_sync"));

				asList.add(as);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			//throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return asList;
	}
	
	public List<Auction> getAuctionListByTypeAndActive(Integer auctionType){

		List<Auction> aList = new ArrayList<Auction>();
		
		StringBuilder sb = new StringBuilder("Select id, auction_name, auction_no, location, bid_deposit_amount, start_date_time, end_date_time");
	
		sb.append(", auction_desc, terms_and_conditions, coordinator, visibility, auction_item_closing, auction_type, active, auction_id");
		
		sb.append(", no_of_lots, no_of_items, auction_item_increment_time, bid_deposit, date_sync, status, image, image_small");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction where auction_type ="+auctionType+" and active = 1 and start_date_time is not null and end_date_time is not null");
		
		sb.append(" order by end_date_time asc");

		Connection conn = null;
		
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
			}else if(dbConn.getConnection7()!=null && !dbConn.getConnection7().isClosed()){
				conn = dbConn.getConnection7();
			}

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
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
				
            	a.setImageBytes(rs.getBytes("image"));
            	a.setImageSmallBytes(rs.getBytes("image_small"));

				//SystemBean - start
				a.setDate_created(rs.getTimestamp("date_created"));
				a.setDate_updated(rs.getTimestamp("date_updated"));
				a.setCreated_by(rs.getInt("created_by"));
				a.setUpdated_by(rs.getInt("updated_by"));
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
	

}

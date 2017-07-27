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
import hmr.com.bean.LotStaging;
import hmr.com.bean.AuctionStaging;


public class LotStagingDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public LotStagingDao(){
		dbConn = new DBConnection();
	}
	
	public LotStagingDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	public LotStaging getAuctionById(Integer id){
		
		Connection conn = null;

		LotStaging ls = null;
		
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
				ls = new LotStaging();
/*
				ls.setId(rs.getBigDecimal("id"));

				ls.setAuction_name(rs.getString("auction_name"));	
				ls.setAuction_no(rs.getBigDecimal("auction_no"));
				ls.setLocation(rs.getString("location"));
				ls.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				ls.setStart_date_time(rs.getTimestamp("start_date_time"));
				ls.setEnd_date_time(rs.getTimestamp("end_date_time"));
				ls.setAuction_desc(rs.getString("auction_desc"));
				ls.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				ls.setCoordinator(rs.getInt("coordinator"));
				ls.setVisibility(rs.getInt("visibility"));
				ls.setAuction_item_closing(rs.getInt("auction_item_closing"));
				ls.setAuction_type(rs.getInt("auction_type"));
				ls.setAuction_id(rs.getBigDecimal("auction_id"));
            	ls.setStatus(rs.getInt("status"));
            	ls.setActive(rs.getInt("active"));
				ls.setNo_of_lots(rs.getInt("no_of_lots"));
				ls.setNo_of_items(rs.getInt("no_of_items"));
				ls.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				ls.setBid_deposit(rs.getInt("bid_deposit"));
				ls.setDate_sync(rs.getTimestamp("date_sync"));

            	ls.setDate_created(rs.getTimestamp("date_created"));
            	ls.setCreated_by(rs.getInt("created_by"));
            	ls.setDate_updated(rs.getTimestamp("date_updated"));
            	ls.setUpdated_by(rs.getInt("updated_by"));
            	
            	//InputStream binaryStream = rs.getBinaryStream("image");
            	//ls.setImageInputStream(binaryStream);
            	
            	ls.setImageBytes(rs.getBytes("image"));
            	ls.setImageSmallBytes(rs.getBytes("image_small"));
            	
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
		
		return ls;
	}
	
	public LotStaging getAuctionById(BigDecimal id){
		
		Connection conn = null;

		LotStaging ls = null;
		
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
				ls = new LotStaging();
/*
				ls.setId(rs.getBigDecimal("id"));

				ls.setAuction_name(rs.getString("auction_name"));	
				ls.setAuction_no(rs.getBigDecimal("auction_no"));
				ls.setLocation(rs.getString("location"));
				ls.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				ls.setStart_date_time(rs.getTimestamp("start_date_time"));
				ls.setEnd_date_time(rs.getTimestamp("end_date_time"));
				ls.setAuction_desc(rs.getString("auction_desc"));
				ls.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				ls.setCoordinator(rs.getInt("coordinator"));
				ls.setVisibility(rs.getInt("visibility"));
				ls.setAuction_item_closing(rs.getInt("auction_item_closing"));
				ls.setAuction_type(rs.getInt("auction_type"));
				ls.setAuction_id(rs.getBigDecimal("auction_id"));
            	ls.setStatus(rs.getInt("status"));
            	ls.setActive(rs.getInt("active"));
				ls.setNo_of_lots(rs.getInt("no_of_lots"));
				ls.setNo_of_items(rs.getInt("no_of_items"));
				ls.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				ls.setBid_deposit(rs.getInt("bid_deposit"));
				ls.setDate_sync(rs.getTimestamp("date_sync"));

            	ls.setDate_created(rs.getTimestamp("date_created"));
            	ls.setCreated_by(rs.getInt("created_by"));
            	ls.setDate_updated(rs.getTimestamp("date_updated"));
            	ls.setUpdated_by(rs.getInt("updated_by"));
            	
            	//InputStream binaryStream = rs.getBinaryStream("image");
            	//ls.setImageInputStream(binaryStream);
            	
            	ls.setImageBytes(rs.getBytes("image"));
            	ls.setImageSmallBytes(rs.getBytes("image_small"));
            	
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
		
		return ls;
	}
	
	
	public LotStaging getAuctionByAuctionId(BigDecimal auction_id){
		
		Connection conn = null;

		LotStaging ls = null;
		
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
				ls = new LotStaging();
/*
				ls.setId(rs.getBigDecimal("id"));

				ls.setAuction_name(rs.getString("auction_name"));	
				ls.setAuction_no(rs.getBigDecimal("auction_no"));
				ls.setLocation(rs.getString("location"));
				ls.setBid_deposit_amount(rs.getBigDecimal("bid_deposit_amount"));
				ls.setStart_date_time(rs.getTimestamp("start_date_time"));
				ls.setEnd_date_time(rs.getTimestamp("end_date_time"));
				ls.setAuction_desc(rs.getString("auction_desc"));
				ls.setTerms_and_condition(rs.getString("terms_and_conditions"));	
				ls.setCoordinator(rs.getInt("coordinator"));
				ls.setVisibility(rs.getInt("visibility"));
				ls.setAuction_item_closing(rs.getInt("auction_item_closing"));
				ls.setAuction_type(rs.getInt("auction_type"));
				ls.setAuction_id(rs.getBigDecimal("auction_id"));
            	ls.setStatus(rs.getInt("status"));
            	ls.setActive(rs.getInt("active"));
				ls.setNo_of_lots(rs.getInt("no_of_lots"));
				ls.setNo_of_items(rs.getInt("no_of_items"));
				ls.setAuction_item_increment_time(rs.getInt("auction_item_increment_time"));
				ls.setBid_deposit(rs.getInt("bid_deposit"));
				ls.setDate_sync(rs.getTimestamp("date_sync"));

            	ls.setDate_created(rs.getTimestamp("date_created"));
            	ls.setCreated_by(rs.getInt("created_by"));
            	ls.setDate_updated(rs.getTimestamp("date_updated"));
            	ls.setUpdated_by(rs.getInt("updated_by"));
            	
            	//InputStream binaryStream = rs.getBinaryStream("image");
            	//ls.setImageInputStream(binaryStream);
            	
            	ls.setImageBytes(rs.getBytes("image"));
            	ls.setImageSmallBytes(rs.getBytes("image_small"));
            	
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
		
		return ls;
	}
	
	public LotStaging getAuctionImageById(Integer id, String size){
		
		Connection conn = null;

		LotStaging ls = null;
		
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
				ls = new LotStaging();
/*
				ls.setId(rs.getBigDecimal("id"));
            	ls.setImageBytes(rs.getBytes("image"));
            	ls.setImageSmallBytes(rs.getBytes("image_small"));
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
		
		return ls;
	}
	/*
	
	public LotStaging getAuction(String auctionId){
		
		Connection conn = null;

		LotStaging ls = null;
		
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
				ls = new LotStaging();
				ls.setId(rs.getInt("id"));

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
		
		return ls;
	}
	
	public LotStaging getAuctionRegistration(String auctionId, String vek){
		
		Connection conn = null;

		LotStaging ls = null;
		
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
				ls = new LotStaging();
				ls.setId(rs.getInt("id"));

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
		
		return ls;
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

	public int insertLotStagingOnSearch(
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
				Timestamp last_date_sync
			) {
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		AuctionStaging ls = null;

		PreparedStatement stmt = null;
				
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection7();


			StringBuilder sb = new StringBuilder("INSERT into lot_staging (lot_id, auction_id, lot_number, is_available_lot, lot_description,");

			sb.append(" lot_type_id, premium_rate, unit, unit_qty, vat, duties, assessment_value, last_date_sync");
			
			sb.append(" )VALUES(");
			
			sb.append(" ?, ?, ?, ?, ?,");
			
			sb.append(" ?, ?, ?, ?, ?, ?, ?, ?");

			sb.append(")");
			
			
		    String sql = sb.toString();
	        stmt = conn.prepareStatement(sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        //java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        //java.sql.Date auction_date_d = null;
/*
	        java.sql.Timestamp auction_date_t = null;
	        if(auction_date!=null){
	        	//auction_date_d = new java.sql.Date(auction_date.getTime());
	        	auction_date_t = new java.sql.Timestamp(auction_date.getTime());
	        }
*/
	        
	        
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
	
	
	public int deleteLotStagingOnSearch() {
	
	Connection conn = null;
	
	int affectedRows = 0;
	
	AuctionStaging ls = null;

	try {
		DBConnection dbConn = new DBConnection();
		
		conn = dbConn.getConnection();
		

		StringBuilder sb = new StringBuilder("DELETE from lot_staging");
		
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
	
	public LotStaging updateLotStagingOnUpdate(
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
		
		LotStaging ls = null;
	
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
		    	ls = null;
	        }else{
	        	ls = new LotStaging(); 
            	//ls.setId(auctionId_wip);
            	/*
            	ls.setEmail_address(emailAddress);
            	ls.setFirst_name(firstName);
            	ls.setLast_name(lastName);
            	ls.setMobile_no_1(mobileNo1);
            	ls.setMobile_no_2(mobileNo2);
            	
            	ls.setGender(gender);
            	ls.setRole(role);
            	ls.setBidder_no(bidderNo);
            	ls.setReserve_bidder_no(reserveBidderNo);
            	ls.setCompany(company);
            	ls.setStatus(userStatus);
            	ls.setActive(active);
            	ls.setLandline_no(landLineNo);
            	
            	ls.setNews_letter(newsLetter);
            	ls.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	ls.setVerification_email_key(verificationEmailKey);
            	ls.setDate_registration(registrationDate_t);
            	ls.setDate_password_change(passwordChangeDate_t);
            	ls.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	ls.setBirth_date(birthDate_d);
            	ls.setPass_word(passWord);
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
	
		return ls;
	}
	
	
	public LotStaging updateAuctionImage(
				File file_small,
				File file,
				Integer user_id,
				BigDecimal auctionId_wip
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		LotStaging ls = null;
	
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
		    	ls = null;
	        }else{
	        	ls = new LotStaging(); 
            	//ls.setId(auctionId_wip);
            	/*
            	ls.setEmail_address(emailAddress);
            	ls.setFirst_name(firstName);
            	ls.setLast_name(lastName);
            	ls.setMobile_no_1(mobileNo1);
            	ls.setMobile_no_2(mobileNo2);
            	
            	ls.setGender(gender);
            	ls.setRole(role);
            	ls.setBidder_no(bidderNo);
            	ls.setReserve_bidder_no(reserveBidderNo);
            	ls.setCompany(company);
            	ls.setStatus(userStatus);
            	ls.setActive(active);
            	ls.setLandline_no(landLineNo);
            	
            	ls.setNews_letter(newsLetter);
            	ls.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	ls.setVerification_email_key(verificationEmailKey);
            	ls.setDate_registration(registrationDate_t);
            	ls.setDate_password_change(passwordChangeDate_t);
            	ls.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	ls.setBirth_date(birthDate_d);
            	ls.setPass_word(passWord);
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
	
		return ls;
	}
	
	
	
	public List<LotStaging> getLotStagingList(){

		List<LotStaging> lsList = new ArrayList<LotStaging>();
		
		StringBuilder sb = new StringBuilder("Select auction_id, lot_id, lot_number, is_available_lot, lot_description, lot_type_id,");

		sb.append(" premium_rate, unit, unit_qty, vat, duties, assessment_value, last_date_sync");
		
		sb.append(" from lot_staging");
		
		sb.append(" order by lot_id asc");

		Connection conn = null;
		PreparedStatement stmt = null;
		

		
		try {
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection7();
			
			
			try{
				stmt = conn.prepareStatement(sb.toString());
			}catch(Exception ex){
				conn = dbConn.getConnection7();
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

			LotStaging ls = null;

			while(rs.next()){
				ls = new LotStaging();


				ls.setAuction_id(rs.getBigDecimal("auction_id"));
				ls.setLot_id(rs.getBigDecimal("lot_id") );
				ls.setLot_number(rs.getBigDecimal("lot_number"));
				ls.setIs_available_lot(rs.getInt("is_available_lot"));
				ls.setLot_description(rs.getString("lot_description"));
				ls.setLot_type_id(rs.getInt("lot_type_id"));
				ls.setUnit(rs.getString("unit"));
				ls.setUnit_qty(rs.getInt("unit_qty"));
				ls.setPremium_rate(rs.getBigDecimal("premium_rate"));

				ls.setVat(rs.getBigDecimal("vat"));
				ls.setDuties(rs.getBigDecimal("duties"));
				ls.setAssessment_value(rs.getBigDecimal("assessment_value"));
				ls.setLast_date_sync(rs.getTimestamp("last_date_sync"));

				lsList.add(ls);
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
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			
		}
		
		return lsList;
	}
	
}

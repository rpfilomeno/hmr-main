package hmr.com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.util.DBConnection;
import hmr.com.bean.BiddingTransaction;
import hmr.com.util.StringUtil;

public class BiddingTransactionDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;
	
	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public BiddingTransactionDao(){
		dbConn = new DBConnection();
	}
	
	public BiddingTransactionDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	public BiddingTransaction getBiddingTransactionById(BigDecimal id){
		
		Connection conn = null;

		BiddingTransaction bt = null;
		
		
		StringBuilder sb = new StringBuilder("Select id, lot_id, amount_bid, amount_buy, action_taken");

		sb.append(", status, user_id");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from user where id ="+id);


		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				bt = new BiddingTransaction();
				
            	bt.setId(rs.getBigDecimal("id"));
            	//lot_id, amount_bid, amount_buy, action, status, user_id
            	/*
            	bt.setEmail_address(rs.getString("email_address"));
            	bt.setFirst_name(rs.getString("first_name"));
            	bt.setLast_name(rs.getString("last_name"));
            	bt.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
            	bt.setMobile_no_2(rs.getBigDecimal("mobile_no_2"));
            	
            	bt.setGender(rs.getInt("gender"));
            	bt.setRole(rs.getInt("role"));
            	bt.setBidder_no(rs.getInt("bidder_no"));
            	bt.setReserve_bidder_no(rs.getInt("reserve_bidder_no"));
            	bt.setCompany(rs.getString("company"));
            	bt.setStatus(rs.getInt("status"));
            	bt.setActive(rs.getInt("active"));
            	bt.setLandline_no(rs.getBigDecimal("landline_no"));
            	
            	bt.setNews_letter(rs.getInt("news_letter"));
            	bt.setNews_letter_registration_date(rs.getTimestamp("news_letter_registration_date"));
            	bt.setVerification_email_key(rs.getString("verification_email_key"));
            	bt.setDate_registration(rs.getTimestamp("date_registration"));
            	bt.setDate_password_change(rs.getTimestamp("date_password_change"));
            	bt.setShowChangePasswordNextLogin(rs.getInt("show_change_password_next_login"));
            	bt.setBirth_date(rs.getDate("birth_date"));
            	bt.setPass_word(rs.getString("pass_word"));
            	
            	*/
            	bt.setDate_created(rs.getTimestamp("date_created"));
            	bt.setCreated_by(rs.getInt("created_by"));
            	bt.setDate_updated(rs.getTimestamp("date_updated"));
            	bt.setUpdated_by(rs.getInt("updated_by"));
            	
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
		
		return bt;
	}
	
	

	public BiddingTransaction getBiddingTransactionByBiddingTransactionId(BigDecimal biddingTransaction_id){
		
		Connection conn = null;

		BiddingTransaction bt = null;
		
		
		StringBuilder sb = new StringBuilder("Select id, lot_id, amount_bid, amount_buy, action");

		sb.append(", status, user_id");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from user where id ="+biddingTransaction_id);


		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				bt = new BiddingTransaction();
				
            	bt.setId(rs.getBigDecimal("id"));
            	//lot_id, amount_bid, amount_buy, action, status, user_id
            	/*
            	bt.setEmail_address(rs.getString("email_address"));
            	bt.setFirst_name(rs.getString("first_name"));
            	bt.setLast_name(rs.getString("last_name"));
            	bt.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
            	bt.setMobile_no_2(rs.getBigDecimal("mobile_no_2"));
            	
            	bt.setGender(rs.getInt("gender"));
            	bt.setRole(rs.getInt("role"));
            	bt.setBidder_no(rs.getInt("bidder_no"));
            	bt.setReserve_bidder_no(rs.getInt("reserve_bidder_no"));
            	bt.setCompany(rs.getString("company"));
            	bt.setStatus(rs.getInt("status"));
            	bt.setActive(rs.getInt("active"));
            	bt.setLandline_no(rs.getBigDecimal("landline_no"));
            	
            	bt.setNews_letter(rs.getInt("news_letter"));
            	bt.setNews_letter_registration_date(rs.getTimestamp("news_letter_registration_date"));
            	bt.setVerification_email_key(rs.getString("verification_email_key"));
            	bt.setDate_registration(rs.getTimestamp("date_registration"));
            	bt.setDate_password_change(rs.getTimestamp("date_password_change"));
            	bt.setShowChangePasswordNextLogin(rs.getInt("show_change_password_next_login"));
            	bt.setBirth_date(rs.getDate("birth_date"));
            	bt.setPass_word(rs.getString("pass_word"));
            	
            	*/
            	bt.setDate_created(rs.getTimestamp("date_created"));
            	bt.setCreated_by(rs.getInt("created_by"));
            	bt.setDate_updated(rs.getTimestamp("date_updated"));
            	bt.setUpdated_by(rs.getInt("updated_by"));
            	
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
		
		return bt;
	}
	
	
	public BiddingTransaction getBiddingTransaction(String userId){
		
		Connection conn = null;

		BiddingTransaction bt = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, role from user where ");

		sb.append("email_address = '"+userId+"'") ;

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				bt = new BiddingTransaction();
				/*
				bt.setId(rs.getInt("id"));
				bt.setEmail_address(rs.getString("email_address"));
				bt.setFirst_name(rs.getString("first_name"));
				bt.setLast_name(rs.getString("last_name"));
				bt.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
				bt.setRole(rs.getInt("role"));
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
		
		return bt;
	}
	
	public BiddingTransaction getBiddingTransactionRegistration(String userId, String vek){
		
		Connection conn = null;

		BiddingTransaction bt = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, verification_email_key, date_registration from user where ");

		sb.append("email_address = '"+userId+"' and verification_email_key = '"+vek+"'");

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				bt = new BiddingTransaction();
				
				/*
				bt.setId(rs.getInt("id"));
				bt.setEmail_address(rs.getString("email_address"));
				bt.setFirst_name(rs.getString("first_name"));
				bt.setLast_name(rs.getString("last_name"));
				bt.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
				bt.setDate_registration(rs.getTimestamp("date_registration"));
				
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
		
		return bt;
	}
	
	
	public int insertBiddingTransactionOnRegistration(String firstName, String lastName, String userId, BigDecimal mobileNo, String verification_email_key){
		
		int i = 0;
		
		try {
			 conn = getConnection();

			  Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();

		      String sql = "INSERT INTO user (first_name, last_name, email_address, mobile_no_1, verification_email_key, role, date_created) " +
		                   "VALUES ('"+firstName+"', '"+lastName+"', '"+userId+"' ,"+ mobileNo+", '"+verification_email_key+"', 2, now())";
		      
		      
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
	
	/*
	
	public int insertBiddingTransactionOnCreate(
				String firstName,
				String lastName,
				String birthDate,
				Integer gender,
				String company,
				String emailAddress,
				String passWord,
				Integer userStatus,
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
				Integer user_id,
				String a
			){
		
		int i = 0;
		
		try {
			 conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();
		      
				StringBuilder sb = new StringBuilder("INSERT INTO user (email_address, first_name, last_name, mobile_no_1, mobile_no_2");

				sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
				
				sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration");
		      
				sb.append(", date_password_change, show_change_password_next_login, birth_date");
				
				sb.append(", date_created, created_by)");
				
				sb.append(" VALUES(");
				
				sb.append("'"+emailAddress+"', '"+firstName+"', '"+lastName+"' ,"+ mobileNo1+", "+mobileNo2+"");
				sb.append(", "+gender+", "+role+", "+bidderNo+" ,"+ reserveBidderNo+", '"+company+"' ,"+ userStatus+", "+active+", "+landLineNo);
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

	public BiddingTransaction insertBiddingTransactionOnCreate(
			BigDecimal lot_id,
			BigDecimal amount_bid,
			BigDecimal amount_buy,
			Integer action_taken,
			Integer status,
			Integer user_id_bidder,
			Integer user_id
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		BiddingTransaction bt = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			StringBuilder sb = new StringBuilder("INSERT INTO user (lot_id, amount_bid, amount_buy ");

			sb.append(", action_taken, status, user_id");
			
			sb.append(", date_created, created_by)");
			
			sb.append(" VALUES(");
			
			sb.append(" ?, ?, ?");
			sb.append(",?, ?, ?");
			sb.append(", ?, ?");
			
			sb.append(")");
			
			
		    String sql = sb.toString();
	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


	        stmt.setBigDecimal(1, lot_id);
	        stmt.setBigDecimal(2, amount_bid);
	        stmt.setBigDecimal(3, amount_buy);
	        stmt.setInt(4, action_taken);
	        stmt.setInt(5, status);
	        stmt.setInt(6, user_id_bidder);
	        stmt.setTimestamp(7, sqlDate_t);
	        stmt.setInt(8, user_id);
		      
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	/*
	            	bt = new BiddingTransaction(); 
	            	bt.setId(generatedKeys.getInt(1));
	            	bt.setEmail_address(emailAddress);
	            	bt.setFirst_name(firstName);
	            	bt.setLast_name(lastName);
	            	bt.setMobile_no_1(mobileNo1);
	            	bt.setMobile_no_2(mobileNo2);
	            	
	            	bt.setGender(gender);
	            	bt.setRole(role);
	            	bt.setBidder_no(bidderNo);
	            	bt.setReserve_bidder_no(reserveBidderNo);
	            	bt.setCompany(company);
	            	bt.setStatus(userStatus);
	            	bt.setActive(active);
	            	bt.setLandline_no(landLineNo);
	            	
	            	bt.setNews_letter(newsLetter);
	            	bt.setNews_letter_registration_date(newsLetterRegistrationDate_t);
	            	bt.setVerification_email_key(verificationEmailKey);
	            	bt.setDate_registration(registrationDate_t);
	            	bt.setDate_password_change(passwordChangeDate_t);
	            	bt.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
	            	bt.setBirth_date(birthDate_d);
	            	bt.setPass_word(passWord);
	            */	
	            	
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
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

		return bt;
	}
	
	
	public BiddingTransaction updateBiddingTransactionOnUpdate(
				BigDecimal lot_id,
				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action_taken,
				Integer status,
				Integer user_id_bidder,
				Integer user_id,
				BigDecimal biddingTransactionId_wip
	
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		BiddingTransaction bt = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
		      
			StringBuilder sb = new StringBuilder("Update bidding_transaction SET lot_id=?, amount_bid=?, amount_buy=? ");

			sb.append(", action_taken=?, status=?, user_id=?");
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+biddingTransactionId_wip);

		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	
	        stmt.setBigDecimal(1, lot_id);
	        stmt.setBigDecimal(2, amount_bid);
	        stmt.setBigDecimal(3, amount_buy);
	        stmt.setInt(4, action_taken);
	        stmt.setInt(5, status);
	        stmt.setInt(6, user_id_bidder);
	        stmt.setTimestamp(7, sqlDate_t);
	        stmt.setInt(8, user_id);
	        
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	bt = null;
	        }else{
	        	bt = new BiddingTransaction(); 
	        	/*
            	bt.setId(userId_wip);
            	bt.setEmail_address(emailAddress);
            	bt.setFirst_name(firstName);
            	bt.setLast_name(lastName);
            	bt.setMobile_no_1(mobileNo1);
            	bt.setMobile_no_2(mobileNo2);
            	
            	bt.setGender(gender);
            	bt.setRole(role);
            	bt.setBidder_no(bidderNo);
            	bt.setReserve_bidder_no(reserveBidderNo);
            	bt.setCompany(company);
            	bt.setStatus(userStatus);
            	bt.setActive(active);
            	bt.setLandline_no(landLineNo);
            	
            	bt.setNews_letter(newsLetter);
            	bt.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	bt.setVerification_email_key(verificationEmailKey);
            	bt.setDate_registration(registrationDate_t);
            	bt.setDate_password_change(passwordChangeDate_t);
            	bt.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	bt.setBirth_date(birthDate_d);
            	bt.setPass_word(passWord);
            	
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
	
		return bt;
	}
	
	
	
	public int updatePasswordOnActivation(String userId, String pw, Integer user_id){
		
		int i = 0;
		
		try {
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			
			//USER-STATUS - 11 - Registered
			
			String sql = "update user SET pass_word='"+pw+"', active=1, status=11, date_registration=now()";
			sql = sql+ ", updated_by="+user_id+", date_updated=now() where email_address='"+userId+"'";
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
	
	
	public List<BiddingTransaction> getBiddingTransactionList(){

		List<BiddingTransaction> btList = new ArrayList<BiddingTransaction>();
		
		StringBuilder sb = new StringBuilder("Select id, lot_id, amount_bid, amount_buy, action_taken");

		sb.append(", status, user_id");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from bidding_transaction");
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			BiddingTransaction bt = null;

			while(rs.next()){
				bt = new BiddingTransaction();
				bt.setId(rs.getBigDecimal("id"));
				bt.setLot_id(rs.getBigDecimal("lot_id"));
				bt.setAmount_bid(rs.getBigDecimal("amount_bid"));
				bt.setAmount_buy(rs.getBigDecimal("amount_buy"));

				bt.setAction_taken(rs.getInt("action_taken"));
				bt.setStatus(rs.getInt("status"));
				bt.setUser_id(rs.getInt("user_id"));
				
				//SystemBean - start
				bt.setDate_created(rs.getTimestamp("date_created"));
				bt.setDate_updated(rs.getTimestamp("date_updated"));
				bt.setCreated_by(rs.getInt("created_by"));
				bt.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				btList.add(bt);
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
		
		return btList;
	}
	
	public List<BiddingTransaction> getBiddingTransactionListByRole(Integer role){

		List<BiddingTransaction> btList = new ArrayList<BiddingTransaction>();
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, mobile_no_2");

		sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
		
		sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from user where role ="+role);
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			BiddingTransaction bt = null;

			while(rs.next()){
				bt = new BiddingTransaction();
				/*
				bt.setId(rs.getInt("id"));
				bt.setFirst_name(rs.getString("first_name"));
				bt.setLast_name(rs.getString("last_name"));
				bt.setEmail_address(rs.getString("email_address"));
				bt.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
				bt.setMobile_no_2(rs.getBigDecimal("mobile_no_2"));
				bt.setGender(rs.getInt("gender"));
				bt.setRole(rs.getInt("role"));
				bt.setBidder_no(rs.getInt("bidder_no"));
				bt.setReserve_bidder_no(rs.getInt("reserve_bidder_no"));
				bt.setCompany(rs.getString("company"));
				bt.setStatus(rs.getInt("status"));
				bt.setActive(rs.getInt("active"));
				bt.setLandline_no(rs.getBigDecimal("landline_no"));
				bt.setNews_letter(rs.getInt("news_letter"));
				bt.setNews_letter_registration_date(rs.getTimestamp("news_letter_registration_date"));
				bt.setVerification_email_key(rs.getString("verification_email_key"));
				bt.setDate_registration(rs.getTimestamp("date_registration"));
				*/
				//SystemBean - start
				bt.setDate_created(rs.getTimestamp("date_created"));
				bt.setDate_updated(rs.getTimestamp("date_updated"));
				bt.setCreated_by(rs.getInt("created_by"));
				bt.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				btList.add(bt);
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
		
		return btList;
	}
	
	public HashMap<BigDecimal, BiddingTransaction> getBiddingTransactionHMByRole(Integer role){

		HashMap<BigDecimal, BiddingTransaction> btHM = new HashMap<BigDecimal, BiddingTransaction>();
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, mobile_no_2");

		sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
		
		sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from user where role ="+role);
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			BiddingTransaction bt = null;

			while(rs.next()){
				bt = new BiddingTransaction();
				
				/*
				bt.setId(rs.getInt("id"));
				bt.setFirst_name(rs.getString("first_name"));
				bt.setLast_name(rs.getString("last_name"));
				bt.setEmail_address(rs.getString("email_address"));
				bt.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
				bt.setMobile_no_2(rs.getBigDecimal("mobile_no_2"));
				bt.setGender(rs.getInt("gender"));
				bt.setRole(rs.getInt("role"));
				bt.setBidder_no(rs.getInt("bidder_no"));
				bt.setReserve_bidder_no(rs.getInt("reserve_bidder_no"));
				bt.setCompany(rs.getString("company"));
				bt.setStatus(rs.getInt("status"));
				bt.setActive(rs.getInt("active"));
				bt.setLandline_no(rs.getBigDecimal("landline_no"));
				bt.setNews_letter(rs.getInt("news_letter"));
				bt.setNews_letter_registration_date(rs.getTimestamp("news_letter_registration_date"));
				bt.setVerification_email_key(rs.getString("verification_email_key"));
				bt.setDate_registration(rs.getTimestamp("date_registration"));
				*/
				//SystemBean - start
				bt.setDate_created(rs.getTimestamp("date_created"));
				bt.setDate_updated(rs.getTimestamp("date_updated"));
				bt.setCreated_by(rs.getInt("created_by"));
				bt.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				btHM.put(bt.getId(),bt);
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
		
		return btHM;
	}
	
	
	public int updateBiddingTransactionPassword(String userId, String pw){
		
		int i = 0;

		try {
			 conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();
		      
		      String sql = "update user SET pass_word = '"+pw+"', date_password_change=now(), show_change_password_next_login = 1 where email_address='"+userId+"'";

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

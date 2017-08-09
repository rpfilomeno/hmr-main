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

import hmr.com.util.DBConnection;
import hmr.com.bean.User;
import hmr.com.util.StringUtil;

public class UserDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;
	private Integer id = null;
	private String email_add = null;
	
	public UserDao(){
		dbConn = new DBConnection();
	}
	
	public UserDao(Integer id, String email_add){
		this.id = id;
		this.email_add = email_add;
		dbConn = new DBConnection();
	}
	

	
	public User getUser(String userId, String pw){
		
		Connection conn = null;

		User u = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, mobile_no_2");

		sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
		
		sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration, date_password_change, show_change_password_next_login, birth_date, new_password");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from user where active=1 ");
		
		sb.append("and email_address = '"+userId+"' and (pass_word = '"+pw+"' or new_password = '"+pw+"')") ;

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_address(rs.getString("email_address"));
				u.setFirst_name(rs.getString("first_name"));
				u.setLast_name(rs.getString("last_name"));
				u.setDate_created(rs.getTimestamp("date_created"));
				u.setRole(rs.getInt("role"));
				u.setNew_password(rs.getString("new_password"));
				u.setShowChangePasswordNextLogin(rs.getInt("show_change_password_next_login"));
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
		
		return u;
	}	
	
	
	public User getUserOnLogin(String userId, String pw){
		
		Connection conn = null;

		User u = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, mobile_no_2");

		sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
		
		sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration, date_password_change, show_change_password_next_login, birth_date, new_password");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from user where active=1 and status=11 ");
		
		sb.append("and email_address = '"+userId+"' and (pass_word = '"+pw+"' or new_password = '"+pw+"')") ;

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_address(rs.getString("email_address"));
				u.setFirst_name(rs.getString("first_name"));
				u.setLast_name(rs.getString("last_name"));
				u.setDate_created(rs.getTimestamp("date_created"));
				u.setRole(rs.getInt("role"));
				u.setNew_password(rs.getString("new_password"));
				u.setShowChangePasswordNextLogin(rs.getInt("show_change_password_next_login"));
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
		
		return u;
	}	
		
	
	public User getUserActive(String userId, String pw){
		
		Connection conn = null;

		User u = null;
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, mobile_no_2");

		sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
		
		sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration, date_password_change, show_change_password_next_login, birth_date");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from user where active=1 ");
		
		sb.append("and email_address = '"+userId+"' and pass_word = '"+pw+"'") ;

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_address(rs.getString("email_address"));
				u.setFirst_name(rs.getString("first_name"));
				u.setLast_name(rs.getString("last_name"));
				u.setDate_created(rs.getTimestamp("date_created"));
				u.setRole(rs.getInt("role"));
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
		
		return u;
	}	
	
	
	public User getUserById(Integer id){
		
		Connection conn = null;

		User u = null;
		
		
		StringBuilder sb = new StringBuilder("Select id, email_address, first_name, last_name, mobile_no_1, mobile_no_2");

		sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
		
		sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration");
	  
		sb.append(", date_password_change, show_change_password_next_login, birth_date, pass_word");
		
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
				u = new User();

            	u.setId(rs.getInt("id"));
            	u.setEmail_address(rs.getString("email_address"));
            	u.setFirst_name(rs.getString("first_name"));
            	u.setLast_name(rs.getString("last_name"));
            	u.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
            	u.setMobile_no_2(rs.getBigDecimal("mobile_no_2"));
            	
            	u.setGender(rs.getInt("gender"));
            	u.setRole(rs.getInt("role"));
            	u.setBidder_no(rs.getInt("bidder_no"));
            	u.setReserve_bidder_no(rs.getInt("reserve_bidder_no"));
            	u.setCompany(rs.getString("company"));
            	u.setStatus(rs.getInt("status"));
            	u.setActive(rs.getInt("active"));
            	u.setLandline_no(rs.getBigDecimal("landline_no"));
            	
            	u.setNews_letter(rs.getInt("news_letter"));
            	u.setNews_letter_registration_date(rs.getTimestamp("news_letter_registration_date"));
            	u.setVerification_email_key(rs.getString("verification_email_key"));
            	u.setDate_registration(rs.getTimestamp("date_registration"));
            	u.setDate_password_change(rs.getTimestamp("date_password_change"));
            	u.setShowChangePasswordNextLogin(rs.getInt("show_change_password_next_login"));
            	u.setBirth_date(rs.getDate("birth_date"));
            	u.setPass_word(rs.getString("pass_word"));
            	
            	
            	u.setDate_created(rs.getTimestamp("date_created"));
            	u.setCreated_by(rs.getInt("created_by"));
            	u.setDate_updated(rs.getTimestamp("date_updated"));
            	u.setUpdated_by(rs.getInt("updated_by"));
            	
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
		
		return u;
	}
	
	
	
	public User getUser(String userId){
		
		Connection conn = null;

		User u = null;
		
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
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_address(rs.getString("email_address"));
				u.setFirst_name(rs.getString("first_name"));
				u.setLast_name(rs.getString("last_name"));
				u.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
				u.setRole(rs.getInt("role"));
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
		
		return u;
	}
	
	public User getUserRegistration(String userId, String vek){
		
		Connection conn = null;

		User u = null;
		
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
				u = new User();
				u.setId(rs.getInt("id"));
				u.setEmail_address(rs.getString("email_address"));
				u.setFirst_name(rs.getString("first_name"));
				u.setLast_name(rs.getString("last_name"));
				u.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
				u.setDate_registration(rs.getTimestamp("date_registration"));
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
		
		return u;
	}
	
	
	public int insertUserOnRegistration(
			String firstName, 
			String lastName, 
			String userId, 
			BigDecimal mobileNo, 
			Integer gender, 
			Integer dobmonth,
			Integer dobday,
			Integer dobyear,
			String addressStreetNo,
			String addressBaranggay,
			String addressCity,
			String addressProvince,
			String addressCountry,
			String addressZipCode,
			String companyName,
			String verification_email_key){
		
		int i = 0;
		int last_inserted_id = 0;
		String dob = dobyear+"/"+String.format("%02d", dobmonth)+"/"+String.format("%02d", dobday);
		
		try {
			  conn = getConnection();

			  Statement stmt = conn.createStatement();
		      String sql = "INSERT INTO user (first_name, last_name, email_address, mobile_no_1, verification_email_key, role, status, birth_date, company, `date_created`) " +
		                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
		      
		      PreparedStatement prest = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		      prest.setString(1,firstName);
		      prest.setString(2,lastName);
		      prest.setString(3,userId);
		      prest.setString(4,mobileNo.toString());
		      prest.setString(5,verification_email_key);
		      prest.setInt(6,2);
		      prest.setInt(7,12);
		      prest.setString(8,dob);
		      prest.setString(9,companyName);
		      prest.executeUpdate();
		      ResultSet rs = prest.getGeneratedKeys();
              if(rs.next()) last_inserted_id = rs.getInt(1);

		      
		      System.out.println("sql : "+sql);		      

		      

		      sql ="INSERT INTO `user_address` (`user_id`, `address_line_1`, `baranggay`, `province`, `city`, `country`, `address_type`, `postal_code`, `date_created`) "+
		      "VALUES ('"+last_inserted_id+"', '"+addressStreetNo+"', '"+addressBaranggay+"', "+ addressProvince +",'"+addressCity+"', '"+addressCountry+"', '1', '"+addressZipCode+"', NOW());";
		      
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
	
public int insertUserOnRegistration(String firstName, String lastName, String userId, BigDecimal mobileNo, String verification_email_key){
		
		int i = 0;
		
		try {
			 conn = getConnection();

			  Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();

		      String sql = "INSERT INTO user (first_name, last_name, email_address, mobile_no_1, verification_email_key, role, status, date_created) " +
		                   "VALUES ('"+firstName+"', '"+lastName+"', '"+userId+"' ,"+ mobileNo+", '"+verification_email_key+"', 2, 12, now())";
		      
		      
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
	
	public int insertUserOnCreate(
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

	public User insertUserOnCreate(

				String firstName,
				String lastName,
				Date birthDate,
				Integer gender,
				String company,
				String emailAddress,
				String passWord,
				Integer userStatus,
				Integer active,
				Integer role,
				Integer newsLetter,
				Date newsLetterRegistrationDate,
				Date registrationDate,
				Date passwordChangeDate,
				BigDecimal mobileNo1,
				BigDecimal mobileNo2,
				BigDecimal landLineNo,
				Integer bidderNo,
				Integer reserveBidderNo,
				String verificationEmailKey,
				Integer showChangePasswordNextLogin,
				Integer user_id

			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		User u = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
		      
			StringBuilder sb = new StringBuilder("INSERT INTO user (email_address, first_name, last_name, mobile_no_1, mobile_no_2");

			sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
			
			sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration");
	      
			sb.append(", date_password_change, show_change_password_next_login, birth_date, pass_word");
			
			sb.append(", date_created, created_by)");
			
			sb.append(" VALUES(");
			
			sb.append(" ?, ?, ?, ?, ?");
			sb.append(",?, ?, ?, ?, ?, ?, ?, ?");
			sb.append(",?, ?, ?, ?");
			sb.append(",?, ?, ?, ?");
			sb.append(", ?, ?");
			
			sb.append(")");
			
			
		    String sql = sb.toString();
	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        java.sql.Date newsLetterRegistrationDate_d = null;
	        java.sql.Timestamp newsLetterRegistrationDate_t = null;
	        if(newsLetterRegistrationDate!=null){
	        	newsLetterRegistrationDate_d = new java.sql.Date(newsLetterRegistrationDate.getTime());
	        	newsLetterRegistrationDate_t = new java.sql.Timestamp(newsLetterRegistrationDate.getTime());
	        }
	        
	        java.sql.Date registrationDate_d = null;
	        java.sql.Timestamp registrationDate_t = null;
	        if(registrationDate!=null){
	        	registrationDate_d = new java.sql.Date(registrationDate.getTime());
	        	registrationDate_t = new java.sql.Timestamp(registrationDate.getTime());
	        }
	        
	        java.sql.Date passwordChangeDate_d = null;
	        java.sql.Timestamp passwordChangeDate_t = null;
	        if(passwordChangeDate!=null){
	        	passwordChangeDate_d = new java.sql.Date(passwordChangeDate.getTime());
	        	passwordChangeDate_t = new java.sql.Timestamp(passwordChangeDate.getTime());
	        }
	        
	        java.sql.Date birthDate_d = null;
	        java.sql.Timestamp birthDate_t = null;
	        if(birthDate!=null){
	        	birthDate_d = new java.sql.Date(birthDate.getTime());
	        	birthDate_t = new java.sql.Timestamp(birthDate.getTime());
	        }
	        
	        
	        stmt.setString(1, emailAddress);
	        stmt.setString(2, firstName);
	        stmt.setString(3, lastName);
	        stmt.setBigDecimal(4, mobileNo1);
	        stmt.setBigDecimal(5, mobileNo2);
	        
	        stmt.setInt(6, gender);
	        stmt.setInt(7, role);
	        stmt.setInt(8, bidderNo);
	        stmt.setInt(9, reserveBidderNo);
	        stmt.setString(10, company);
	        stmt.setInt(11, userStatus);
	        stmt.setInt(12, active);
	        stmt.setBigDecimal(13, landLineNo);

	        stmt.setInt(14, newsLetter);
	        stmt.setTimestamp(15, newsLetterRegistrationDate_t);
	        stmt.setString(16, verificationEmailKey);
	        stmt.setTimestamp(17, registrationDate_t);

	        stmt.setTimestamp(18, passwordChangeDate_t);
	        stmt.setInt(19, showChangePasswordNextLogin);
	        stmt.setDate(20, birthDate_d);
	        stmt.setString(21, passWord);
	        
	        
	        
	        stmt.setTimestamp(22, sqlDate_t);
	        stmt.setInt(23, user_id);
		      
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	u = new User(); 
	            	u.setId(generatedKeys.getInt(1));
	            	u.setEmail_address(emailAddress);
	            	u.setFirst_name(firstName);
	            	u.setLast_name(lastName);
	            	u.setMobile_no_1(mobileNo1);
	            	u.setMobile_no_2(mobileNo2);
	            	
	            	u.setGender(gender);
	            	u.setRole(role);
	            	u.setBidder_no(bidderNo);
	            	u.setReserve_bidder_no(reserveBidderNo);
	            	u.setCompany(company);
	            	u.setStatus(userStatus);
	            	u.setActive(active);
	            	u.setLandline_no(landLineNo);
	            	
	            	u.setNews_letter(newsLetter);
	            	u.setNews_letter_registration_date(newsLetterRegistrationDate_t);
	            	u.setVerification_email_key(verificationEmailKey);
	            	u.setDate_registration(registrationDate_t);
	            	u.setDate_password_change(passwordChangeDate_t);
	            	u.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
	            	u.setBirth_date(birthDate_d);
	            	u.setPass_word(passWord);
	            	
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

		return u;
	}
	
	
	public User updateUserOnUpdate(
	
				String firstName,
				String lastName,
				Date birthDate,
				Integer gender,
				String company,
				String emailAddress,
				String passWord,
				Integer userStatus,
				Integer active,
				Integer role,
				Integer newsLetter,
				Date newsLetterRegistrationDate,
				Date registrationDate,
				Date passwordChangeDate,
				BigDecimal mobileNo1,
				BigDecimal mobileNo2,
				BigDecimal landLineNo,
				Integer bidderNo,
				Integer reserveBidderNo,
				String verificationEmailKey,
				Integer showChangePasswordNextLogin,
				Integer user_id,
				Integer userId_wip
	
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		User u = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
		      
			StringBuilder sb = new StringBuilder("Update user SET email_address=?, first_name=?, last_name=?, mobile_no_1=?, mobile_no_2=?");
	
			sb.append(", gender=?, role=?, bidder_no=?, reserve_bidder_no=?, company=?, status=?, active=?, landline_no=?");
			
			sb.append(", news_letter=?, news_letter_registration_date=?, verification_email_key=?, date_registration=?");
	      
			sb.append(", date_password_change=?, show_change_password_next_login=?, birth_date=?, pass_word=?");
			
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+userId_wip);

		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	
	        java.sql.Date newsLetterRegistrationDate_d = null;
	        java.sql.Timestamp newsLetterRegistrationDate_t = null;
	        if(newsLetterRegistrationDate!=null){
	        	newsLetterRegistrationDate_d = new java.sql.Date(newsLetterRegistrationDate.getTime());
	        	newsLetterRegistrationDate_t = new java.sql.Timestamp(newsLetterRegistrationDate.getTime());
	        }
	        
	        java.sql.Date registrationDate_d = null;
	        java.sql.Timestamp registrationDate_t = null;
	        if(registrationDate!=null){
	        	registrationDate_d = new java.sql.Date(registrationDate.getTime());
	        	registrationDate_t = new java.sql.Timestamp(registrationDate.getTime());
	        }
	        
	        java.sql.Date passwordChangeDate_d = null;
	        java.sql.Timestamp passwordChangeDate_t = null;
	        if(passwordChangeDate!=null){
	        	passwordChangeDate_d = new java.sql.Date(passwordChangeDate.getTime());
	        	passwordChangeDate_t = new java.sql.Timestamp(passwordChangeDate.getTime());
	        }
	        
	        java.sql.Date birthDate_d = null;
	        java.sql.Timestamp birthDate_t = null;
	        if(birthDate!=null){
	        	birthDate_d = new java.sql.Date(birthDate.getTime());
	        	birthDate_t = new java.sql.Timestamp(birthDate.getTime());
	        }
	        
	        
	        stmt.setString(1, emailAddress);
	        stmt.setString(2, firstName);
	        stmt.setString(3, lastName);
	        stmt.setBigDecimal(4, mobileNo1);
	        stmt.setBigDecimal(5, mobileNo2);
	        
	        stmt.setInt(6, gender);
	        stmt.setInt(7, role);
	        stmt.setInt(8, bidderNo);
	        stmt.setInt(9, reserveBidderNo);
	        stmt.setString(10, company);
	        stmt.setInt(11, userStatus);
	        stmt.setInt(12, active);
	        stmt.setBigDecimal(13, landLineNo);
	
	        stmt.setInt(14, newsLetter);
	        stmt.setTimestamp(15, newsLetterRegistrationDate_t);
	        stmt.setString(16, verificationEmailKey);
	        stmt.setTimestamp(17, registrationDate_t);
	
	        stmt.setTimestamp(18, passwordChangeDate_t);
	        stmt.setInt(19, showChangePasswordNextLogin);
	        stmt.setTimestamp(20, birthDate_t);
	        stmt.setString(21, passWord);
	        
	        
	        
	        stmt.setTimestamp(22, sqlDate_t);
	        stmt.setInt(23, user_id);
		      
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	u = null;
	        }else{
	        	u = new User(); 
            	u.setId(userId_wip);
            	u.setEmail_address(emailAddress);
            	u.setFirst_name(firstName);
            	u.setLast_name(lastName);
            	u.setMobile_no_1(mobileNo1);
            	u.setMobile_no_2(mobileNo2);
            	
            	u.setGender(gender);
            	u.setRole(role);
            	u.setBidder_no(bidderNo);
            	u.setReserve_bidder_no(reserveBidderNo);
            	u.setCompany(company);
            	u.setStatus(userStatus);
            	u.setActive(active);
            	u.setLandline_no(landLineNo);
            	
            	u.setNews_letter(newsLetter);
            	u.setNews_letter_registration_date(newsLetterRegistrationDate_t);
            	u.setVerification_email_key(verificationEmailKey);
            	u.setDate_registration(registrationDate_t);
            	u.setDate_password_change(passwordChangeDate_t);
            	u.setShowChangePasswordNextLogin(showChangePasswordNextLogin);
            	u.setBirth_date(birthDate_d);
            	u.setPass_word(passWord);
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
	
		return u;
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
	
public int updatePassword(String userId, String pw, Integer user_id){
		
		int i = 0;
		
		try {
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			
			//USER-STATUS - 11 - Registered
			
			String sql = "update user SET pass_word='"+pw+"',";
			sql = sql+ " updated_by="+user_id+", date_updated=now() where email_address='"+userId+"'";
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
	
	
	public List<User> getUserList(){

		List<User> uList = new ArrayList<User>();
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, mobile_no_2");

		sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
		
		sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from user");
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			User u = null;

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setFirst_name(rs.getString("first_name"));
				u.setLast_name(rs.getString("last_name"));
				u.setEmail_address(rs.getString("email_address"));
				u.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
				u.setMobile_no_2(rs.getBigDecimal("mobile_no_2"));
				u.setGender(rs.getInt("gender"));
				u.setRole(rs.getInt("role"));
				u.setBidder_no(rs.getInt("bidder_no"));
				u.setReserve_bidder_no(rs.getInt("reserve_bidder_no"));
				u.setCompany(rs.getString("company"));
				u.setStatus(rs.getInt("status"));
				u.setActive(rs.getInt("active"));
				u.setLandline_no(rs.getBigDecimal("landline_no"));
				u.setNews_letter(rs.getInt("news_letter"));
				u.setNews_letter_registration_date(rs.getTimestamp("news_letter_registration_date"));
				u.setVerification_email_key(rs.getString("verification_email_key"));
				u.setDate_registration(rs.getTimestamp("date_registration"));
				
				//SystemBean - start
				u.setDate_created(rs.getTimestamp("date_created"));
				u.setDate_updated(rs.getTimestamp("date_updated"));
				u.setCreated_by(rs.getInt("created_by"));
				u.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				uList.add(u);
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
		
		return uList;
	}
	
	public List<User> getUserListByRole(Integer role){

		List<User> uList = new ArrayList<User>();
		
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

			User u = null;

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setFirst_name(rs.getString("first_name"));
				u.setLast_name(rs.getString("last_name"));
				u.setEmail_address(rs.getString("email_address"));
				u.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
				u.setMobile_no_2(rs.getBigDecimal("mobile_no_2"));
				u.setGender(rs.getInt("gender"));
				u.setRole(rs.getInt("role"));
				u.setBidder_no(rs.getInt("bidder_no"));
				u.setReserve_bidder_no(rs.getInt("reserve_bidder_no"));
				u.setCompany(rs.getString("company"));
				u.setStatus(rs.getInt("status"));
				u.setActive(rs.getInt("active"));
				u.setLandline_no(rs.getBigDecimal("landline_no"));
				u.setNews_letter(rs.getInt("news_letter"));
				u.setNews_letter_registration_date(rs.getTimestamp("news_letter_registration_date"));
				u.setVerification_email_key(rs.getString("verification_email_key"));
				u.setDate_registration(rs.getTimestamp("date_registration"));
				
				//SystemBean - start
				u.setDate_created(rs.getTimestamp("date_created"));
				u.setDate_updated(rs.getTimestamp("date_updated"));
				u.setCreated_by(rs.getInt("created_by"));
				u.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				uList.add(u);
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
		
		return uList;
	}
	
	public HashMap<Integer, User> getUserHMByRole(Integer role){

		HashMap<Integer, User> uHM = new HashMap<Integer, User>();
		
		StringBuilder sb = new StringBuilder("SELECT id, email_address, first_name, last_name, mobile_no_1, mobile_no_2");

		sb.append(", gender, role, bidder_no, reserve_bidder_no, company, status, active, landline_no");
		
		sb.append(", news_letter, news_letter_registration_date, verification_email_key, date_registration");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from user where role ="+role);
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql HM : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			User u = null;

			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setFirst_name(rs.getString("first_name"));
				u.setLast_name(rs.getString("last_name"));
				u.setEmail_address(rs.getString("email_address"));
				u.setMobile_no_1(rs.getBigDecimal("mobile_no_1"));
				u.setMobile_no_2(rs.getBigDecimal("mobile_no_2"));
				u.setGender(rs.getInt("gender"));
				u.setRole(rs.getInt("role"));
				u.setBidder_no(rs.getInt("bidder_no"));
				u.setReserve_bidder_no(rs.getInt("reserve_bidder_no"));
				u.setCompany(rs.getString("company"));
				u.setStatus(rs.getInt("status"));
				u.setActive(rs.getInt("active"));
				u.setLandline_no(rs.getBigDecimal("landline_no"));
				u.setNews_letter(rs.getInt("news_letter"));
				u.setNews_letter_registration_date(rs.getTimestamp("news_letter_registration_date"));
				u.setVerification_email_key(rs.getString("verification_email_key"));
				u.setDate_registration(rs.getTimestamp("date_registration"));
				
				//SystemBean - start
				u.setDate_created(rs.getTimestamp("date_created"));
				u.setDate_updated(rs.getTimestamp("date_updated"));
				u.setCreated_by(rs.getInt("created_by"));
				u.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				uHM.put(u.getId(),u);
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
		
		return uHM;
	}
	
	
	public int updateUserPassword(String userId, String pw){
		
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
	

	public int updateUserNewPassword(String userId, String pw){
		
		int i = 0;

		try {
			 conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();
		      
		      String sql = "update user SET new_password = '"+pw+"', date_password_change=now(), show_change_password_next_login = 1 where email_address='"+userId+"'";

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
	
	public int updateUserNewPassword(String userId, String pw, Integer showChangePasswordNextLogin){
		
		int i = 0;

		try {
			 conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();
			
		      stmt = conn.createStatement();
		      
		      String sql = "update user SET new_password = '"+pw+"', date_password_change=now(), show_change_password_next_login = "+showChangePasswordNextLogin+" where email_address='"+userId+"'";

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

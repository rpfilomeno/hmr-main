package hmr.com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hmr.com.util.DBConnection;
import hmr.com.bean.AuctionUser;


public class AuctionUserDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;
	
	public AuctionUserDao(){
		dbConn = new DBConnection();
	}

	public AuctionUser getAuctionUserById(Integer id){
		
		Connection conn = null;

		AuctionUser u = null;
		

		
		StringBuilder sb = new StringBuilder("Select id, auction_id, user_id, status, active");

		sb.append(", date_created, created_by, date_updated, updated_by, company_id_no, image_1");
		
		sb.append(" from auction_user where id ="+id);


		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				u = new AuctionUser();

            	u.setId(rs.getBigDecimal("id"));
            	u.setAuction_id(rs.getBigDecimal("auction_id"));
            	u.setUser_id(rs.getInt("user_id"));
            	u.setStatus(rs.getInt("status"));
            	u.setActive(rs.getInt("active"));
            	u.setCompany_id_no(rs.getString("company_id_no"));
            	u.setImageBytes(rs.getBytes("image_1"));

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
	
	
public AuctionUser getAuctionUserByUserId(Integer user_id){
		
		Connection conn = null;

		AuctionUser u = null;
		

		
		StringBuilder sb = new StringBuilder("Select id, auction_id, user_id, status, active");

		sb.append(", date_created, created_by, date_updated, updated_by, company_id_no, image_1");
		
		sb.append(" from auction_user where user_id ="+user_id);


		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				u = new AuctionUser();

            	u.setId(rs.getBigDecimal("id"));
            	u.setAuction_id(rs.getBigDecimal("auction_id"));
            	u.setUser_id(rs.getInt("user_id"));
            	u.setStatus(rs.getInt("status"));
            	u.setActive(rs.getInt("active"));
            	u.setCompany_id_no(rs.getString("company_id_no"));
            	u.setImageBytes(rs.getBytes("image_1"));

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
	
	
	public AuctionUser getAuctionUserByUserIdAndAuctionIdAndStatus(BigDecimal user_id, BigDecimal auction_id, Integer status){
		
		Connection conn = null;

		AuctionUser u = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_id, user_id, status, active");

		sb.append(", company_id_no, image_1");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from auction_user where user_id ="+user_id+" AND auction_id="+auction_id + " AND status= " + status);


		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection2();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				u = new AuctionUser();

            	u.setId(rs.getBigDecimal("id"));
            	u.setAuction_id(rs.getBigDecimal("auction_id"));
            	u.setUser_id(rs.getInt("user_id"));
            	u.setStatus(rs.getInt("status"));
            	u.setActive(rs.getInt("active"));
            	u.setCompany_id_no(rs.getString("company_id_no"));
            	u.setImageBytes(rs.getBytes("image_1"));

            	u.setDate_created(rs.getTimestamp("date_created"));
            	u.setCreated_by(rs.getInt("created_by"));
            	u.setDate_updated(rs.getTimestamp("date_updated"));
            	u.setUpdated_by(rs.getInt("updated_by"));
            	
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
		}
		
		return u;
	}
	
	
	
	
	public AuctionUser insertAuctionUserOnCreate(
			BigDecimal auction_id,
			Integer bidder_id,
			Integer status,
			Integer active,
			Integer user_id,
			String company_id_no 
		){
	
	Connection conn = null;
	
	int affectedRows = 0;
	
	AuctionUser u = null;

	try {
		DBConnection dbConn = new DBConnection();
		
		conn = dbConn.getConnection();
	      
		StringBuilder sb = new StringBuilder("INSERT INTO auction_user (auction_id, user_id, status, active, company_id_no");

		sb.append(", date_created, created_by)");
		
		sb.append(" VALUES(");
		
		sb.append(" ?, ?, ?, ?, ?");
		sb.append(", ?, ?");
		
		sb.append(")");
		
		
	    String sql = sb.toString();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


        stmt.setBigDecimal(1, auction_id);
        stmt.setInt(2, bidder_id);
        stmt.setInt(3, status);
        stmt.setInt(4, active);
        stmt.setString(5, company_id_no);

        stmt.setTimestamp(6, sqlDate_t);
        stmt.setInt(7, user_id);

	      
	    System.out.println("sql : "+sql);
	    
	    affectedRows = stmt.executeUpdate();
	    
	    if (affectedRows == 0) {
            throw new SQLException("Creating auctionUser failed, no rows affected.");
        }
	    
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            	u = new AuctionUser(); 
            	u.setId(generatedKeys.getBigDecimal(1));
            	u.setAuction_id(auction_id);
            	u.setUser_id(bidder_id);
            	u.setStatus(status);
            	u.setActive(active);
            	u.setCreated_by(user_id);
            	
            }
            else {
                throw new SQLException("Creating auctionUser failed, no ID obtained.");
            }
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

	return u;
}
	
	
	
	public AuctionUser insertAuctionUserOnCreate(
				BigDecimal auction_id,
				Integer bidder_id,
				Integer status,
				Integer active,
				Integer user_id
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		AuctionUser u = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
		      
			StringBuilder sb = new StringBuilder("INSERT INTO auction_user (auction_id, user_id, status, active");

			sb.append(", date_created, created_by)");
			
			sb.append(" VALUES(");
			
			sb.append(" ?, ?, ?, ?");
			sb.append(", ?, ?");
			
			sb.append(")");
			
			
		    String sql = sb.toString();
	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


	        stmt.setBigDecimal(1, auction_id);
	        stmt.setInt(2, bidder_id);
	        stmt.setInt(3, status);
	        stmt.setInt(4, active);

	        stmt.setTimestamp(5, sqlDate_t);
	        stmt.setInt(6, user_id);

		      
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating auctionUser failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	u = new AuctionUser(); 
	            	u.setId(generatedKeys.getBigDecimal(1));
	            	u.setAuction_id(auction_id);
	            	u.setUser_id(bidder_id);
	            	u.setStatus(status);
	            	u.setActive(active);
	            	u.setCreated_by(user_id);
	            	
	            }
	            else {
	                throw new SQLException("Creating auctionUser failed, no ID obtained.");
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
	
	
	public AuctionUser updateAuctionUserOnUpdate(
				BigDecimal auction_id,
				Integer bidder_id,
				Integer status,
				Integer active,
				Integer user_id,
				BigDecimal auctionUserId_wip,
				String company_id_no
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		AuctionUser u = null;
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
		      
			StringBuilder sb = new StringBuilder("Update auction_user SET auction_id=?, user_id=?, status=?, active=?, company_id_no=?");
			
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+auctionUserId_wip);

		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	        stmt.setBigDecimal(1, auction_id);
	        stmt.setInt(2, bidder_id);
	        stmt.setInt(3, status);
	        stmt.setInt(4, active);
	        stmt.setString(5, company_id_no);
	        stmt.setTimestamp(6, sqlDate_t);
	        stmt.setInt(7, user_id);
		      
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	u = null;
	        }else{
	        	u = new AuctionUser(); 
            	u.setId(auctionUserId_wip);
            	u.setAuction_id(auction_id);
            	u.setUser_id(bidder_id);
            	u.setStatus(status);
            	u.setActive(active);
            	u.setCreated_by(user_id);
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
	

	public List<AuctionUser> getAuctionUserList(){

		List<AuctionUser> uList = new ArrayList<AuctionUser>();
		
		StringBuilder sb = new StringBuilder("SELECT id, auction_id, user_id, status, active");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(", company_id_no, image_1");
		
		sb.append(" from auction_user");
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			AuctionUser u = null;

			while(rs.next()){
				u = new AuctionUser();
            	u.setId(rs.getBigDecimal("id"));
            	u.setAuction_id(rs.getBigDecimal("auction_id"));
            	u.setUser_id(rs.getInt("user_id"));
            	u.setStatus(rs.getInt("status"));
            	u.setActive(rs.getInt("active"));
            	u.setCompany_id_no(rs.getString("company_id_no"));
            	u.setImageBytes(rs.getBytes("image_1"));


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
	
	public List<AuctionUser> getAuctionUserListByAuctionId(BigDecimal auction_id){

		List<AuctionUser> uList = new ArrayList<AuctionUser>();
		
		StringBuilder sb = new StringBuilder("select user.email_address, user.first_name, user.last_name,");
		
		sb.append(" auction_user.*");
		
		sb.append(" from auction_user, user where user.id = auction_user.user_id AND auction_id ="+auction_id);
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();
 
			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			AuctionUser u = null;

			while(rs.next()){
				u = new AuctionUser();
            	u.setId(rs.getBigDecimal("id"));
            	u.setAuction_id(rs.getBigDecimal("auction_id"));
            	u.setUser_id(rs.getInt("user_id"));
            	u.setStatus(rs.getInt("status"));
            	u.setActive(rs.getInt("active"));
            	u.setCompany_id_no(rs.getString("company_id_no"));
            	u.setImageBytes(rs.getBytes("image_1"));
            	
            	u.setEmail(rs.getString("email_address")); 
            	u.setFirstname(rs.getString("first_name"));
            	u.setLastname(rs.getString("last_name")); 


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
	

	

	
	
}

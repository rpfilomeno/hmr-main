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

import hmr.com.bean.User;
import hmr.com.bean.UserAddress;
import hmr.com.util.DBConnection;
import hmr.com.util.StringUtil;

public class UserAddressDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;
	private Integer id = null;
	private String email_add = null;
	
	public UserAddressDao(){
		dbConn = new DBConnection();
	}
	
	public UserAddressDao(Integer id, String email_add){
		this.id = id;
		this.email_add = email_add;
		dbConn = new DBConnection();
	}
	
	public List<UserAddress> getUserAddressList(){

		List<UserAddress> uList = new ArrayList<UserAddress>();
		
		StringBuilder sb = new StringBuilder("SELECT id, user_id, address_line_1, baranggay, city, country, "
				+ "address_type, postal_code");
		
		sb.append(", date_created, date_updated, created_by, updated_by");
		
		sb.append(" from user_address");
		
		sb.append(" order by id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			UserAddress u = null;

			while(rs.next()){
				u = new UserAddress();
				u.setId(rs.getInt("id"));
				u.setUser_id(rs.getInt("user_id"));
				u.setAddress_line_1(rs.getString("address_line_1"));
				u.setBaranggay(rs.getString("baranggay"));
				u.setCity(rs.getString("city"));
				u.setCountry(rs.getString("country"));
				u.setAddress_type(rs.getInt("address_type"));
				u.setPostal_code(rs.getInt("postal_code"));
				
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
	
	public UserAddress insertUserAddressOnCreate(
			Integer user_id,
			String address_line_1,
			String baranggay,
			String city,
			String country,
			Integer address_type,
			Integer postal_code,
			Integer userId
		) {
		Connection conn = null;
		int affectedRows = 0;
		UserAddress ua = null;
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
		      
			StringBuilder sb = new StringBuilder("INSERT INTO user_address (user_id, address_line_1, baranggay, city, country");

			sb.append(", address_type, postal_code");
						
			sb.append(", date_created, created_by)");
			
			sb.append(" VALUES(");
			
			sb.append(" ?, ?, ?, ?, ?");
			sb.append(",?, ?");
			sb.append(", ?, ?");
			
			sb.append(")");
			
			
		    String sql = sb.toString();
	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	      
	        stmt.setInt(1, user_id);
	        stmt.setString(2, address_line_1);
	        stmt.setString(3, baranggay);
	        stmt.setString(4, city);
	        stmt.setString(5, country);
	        stmt.setInt(6, address_type);
	        stmt.setInt(7, postal_code);
	        stmt.setTimestamp(8, sqlDate_t);
	        stmt.setInt(9, userId);
	        
	        System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	ua = new UserAddress(); 
	            	ua.setId(generatedKeys.getInt(1));
	    			ua.setUser_id(user_id);
	            	ua.setAddress_line_1(address_line_1);
	            	ua.setBaranggay(baranggay);
	            	ua.setCity(city);
	            	ua.setCountry(country);
	            	ua.setAddress_type(address_type);
	            	ua.setPostal_code(postal_code);
	            }
	            else {
	                throw new SQLException("Creating user address failed, no ID obtained.");
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
		return ua;
	}
	
	public UserAddress updateUserAddressOnUpdate(
			Integer user_id,
			String address_line_1,
			String baranggay,
			String city,
			String province,
			String country,
			Integer address_type,
			Integer postal_code,
			Integer userId,
			Integer userAddressId_wip
		) {
		
		
		Connection conn = null;
		UserAddress ua = null;
		int affectedRows = 0;
		
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			StringBuilder sb = new StringBuilder("Update user_address SET user_id=?, address_line_1=?, baranggay=?, "
					+ "province=?, city=?, country=?");
	
			sb.append(", address_type=?, postal_code=?");
			
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+userAddressId_wip);

		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        

	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	        
	        
	        stmt.setInt(1, user_id);
	        stmt.setString(2, address_line_1);
	        stmt.setString(3, baranggay);
	        stmt.setString(4, province);
	        stmt.setString(5, city);
	        stmt.setString(6, country);
	        stmt.setInt(7, address_type);
	        stmt.setInt(8, postal_code);
	        stmt.setTimestamp(9, sqlDate_t);
	        stmt.setInt(10, userId);
	        
		      
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	ua = null;
	        }else{
	        	ua = new UserAddress(); 
	        	ua.setId(userAddressId_wip);
    			ua.setUser_id(user_id);
            	ua.setAddress_line_1(address_line_1);
            	ua.setBaranggay(baranggay);
            	ua.setProvince(province); 
            	ua.setCity(city);
            	ua.setCountry(country);
            	ua.setAddress_type(address_type);
            	ua.setPostal_code(postal_code);
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
	
		return ua;
	}
	
	public UserAddress updateUserAddressOnUpdate(
			Integer user_id,
			String address_line_1,
			String baranggay,
			String city,
			String country,
			Integer address_type,
			Integer postal_code,
			Integer userId,
			Integer userAddressId_wip
		) {
		
		
		Connection conn = null;
		UserAddress ua = null;
		int affectedRows = 0;
		
	
		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			StringBuilder sb = new StringBuilder("Update user_address SET user_id=?, address_line_1=?, baranggay=?, "
					+ "city=?, country=?");
	
			sb.append(", address_type=?, postal_code=?");
			
			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+userAddressId_wip);

		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        

	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	        
	        
	        stmt.setInt(1, user_id);
	        stmt.setString(2, address_line_1);
	        stmt.setString(3, baranggay);
	        stmt.setString(4, city);
	        stmt.setString(5, country);
	        stmt.setInt(6, address_type);
	        stmt.setInt(7, postal_code);
	        stmt.setTimestamp(8, sqlDate_t);
	        stmt.setInt(9, userId);
	        
		      
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	ua = null;
	        }else{
	        	ua = new UserAddress(); 
	        	ua.setId(userAddressId_wip);
    			ua.setUser_id(user_id);
            	ua.setAddress_line_1(address_line_1);
            	ua.setBaranggay(baranggay);
            	ua.setCity(city);
            	ua.setCountry(country);
            	ua.setAddress_type(address_type);
            	ua.setPostal_code(postal_code);
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
	
		return ua;
	}
	
	public UserAddress getUserAddressById(Integer id) {
		Connection conn = null;

		UserAddress ua = null;
		
		StringBuilder sb = new StringBuilder("Select id, user_id, address_line_1, baranggay, city, country");

		sb.append(", address_type, postal_code");	  
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from user_address where id ="+id);
		

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				ua = new UserAddress();
				
            	ua.setId(rs.getInt("id"));
            	
            	ua.setUser_id(rs.getInt("user_id"));
            	ua.setAddress_line_1(rs.getString("address_line_1"));
            	ua.setBaranggay(rs.getString("baranggay"));
            	ua.setCity(rs.getString("city"));
            	ua.setCountry(rs.getString("country"));
            	ua.setAddress_type(rs.getInt("address_type"));
            	ua.setPostal_code(rs.getInt("postal_code"));
            	
            	ua.setDate_created(rs.getTimestamp("date_created"));
            	ua.setCreated_by(rs.getInt("created_by"));
            	ua.setDate_updated(rs.getTimestamp("date_updated"));
            	ua.setUpdated_by(rs.getInt("updated_by"));
            	
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
		
		return ua;
	}
	
	public UserAddress getUserAddressByUserId(Integer user_id) {
		Connection conn = null;

		UserAddress ua = null;
		
		StringBuilder sb = new StringBuilder("Select id, user_id, address_line_1, baranggay, city, province, country");

		sb.append(", address_type, postal_code");	  
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from user_address where user_id ="+user_id);
		

		try {

			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();
			
			System.out.println("conn : "+conn);

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			while(rs.next()){
				ua = new UserAddress();
				
            	ua.setId(rs.getInt("id"));
            	
            	ua.setUser_id(rs.getInt("user_id"));
            	ua.setAddress_line_1(rs.getString("address_line_1"));
            	ua.setBaranggay(rs.getString("baranggay"));
            	ua.setCity(rs.getString("city"));
            	ua.setProvince(rs.getString("province"));
            	ua.setCountry(rs.getString("country"));
            	ua.setAddress_type(rs.getInt("address_type"));
            	ua.setPostal_code(rs.getInt("postal_code"));
            	
            	ua.setDate_created(rs.getTimestamp("date_created"));
            	ua.setCreated_by(rs.getInt("created_by"));
            	ua.setDate_updated(rs.getTimestamp("date_updated"));
            	ua.setUpdated_by(rs.getInt("updated_by"));
            	
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
		
		return ua;
	}
	
}

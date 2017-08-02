package hmr.com.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.util.DBConnection;
import hmr.com.bean.Image;


public class ImageDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;

	

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public ImageDao(){
		dbConn = new DBConnection();
	}
	
	public ImageDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public List<Image> getImageListByAuctionId(BigDecimal auction_id) {
		List<Image> iList= new ArrayList<Image>();
		StringBuilder sb = new StringBuilder("Select id, auction_id, lot_id, item_id, active, image");
		sb.append(", date_created, created_by, date_updated, updated_by");
		sb.append(" from image where auction_id ="+  auction_id);
		
		DBConnection dbConn = new DBConnection();
		
		try {
			conn = dbConn.getConnection7();
			java.sql.Statement stmt = conn.createStatement();
			System.out.println("sql : "+sb.toString());		
			ResultSet rs = stmt.executeQuery(sb.toString());
			Image i = null;
			while(rs.next()){
				i = new Image();
				i.setId(rs.getBigDecimal("id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setActive(rs.getInt("active"));
				i.setImageBytes(rs.getBytes("image"));
				iList.add(i);
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
		return iList;
	}
	public List<Image> getImageListByLotId(BigDecimal lot_id) {
		List<Image> iList= new ArrayList<Image>();
		StringBuilder sb = new StringBuilder("Select id, auction_id, lot_id, item_id, active, image");
		sb.append(", date_created, created_by, date_updated, updated_by");
		sb.append(" from image where lot_id ="+  lot_id);

		DBConnection dbConn = new DBConnection();
		
		
		try {
			conn = dbConn.getConnection3();
			java.sql.Statement stmt = conn.createStatement();
			System.out.println("sql : "+sb.toString());		
			ResultSet rs = stmt.executeQuery(sb.toString());
			Image i = null;
			while(rs.next()){
				i = new Image();
				i.setId(rs.getBigDecimal("id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setActive(rs.getInt("active"));
				i.setImageBytes(rs.getBytes("image"));
				iList.add(i);
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
		return iList;
	}
	public List<Image> getImageListByItemId(BigDecimal item_id) {
		List<Image> iList= new ArrayList<Image>();
		StringBuilder sb = new StringBuilder("Select id, auction_id, lot_id, item_id, active, image");
		sb.append(", date_created, created_by, date_updated, updated_by");
		sb.append(" from image where item_id ="+  item_id);
		try {
			conn = getConnection2();
			java.sql.Statement stmt = conn.createStatement();
			System.out.println("sql : "+sb.toString());		
			ResultSet rs = stmt.executeQuery(sb.toString());
			Image i = null;
			while(rs.next()){
				i = new Image();
				i.setId(rs.getBigDecimal("id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));
				i.setLot_id(rs.getBigDecimal("lot_id"));
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setActive(rs.getInt("active"));
				i.setImageBytes(rs.getBytes("image"));
				iList.add(i);
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
		return iList;
	}
	
	public int deleteImage(BigDecimal image_id) {
		Connection conn = null;
		int affectedRows = 0;
		try {
			DBConnection dbConn = new DBConnection();
			conn = dbConn.getConnection();
			Statement stmt = conn.createStatement();
			String Sql ="DELETE FROM `image` WHERE `id`='"+image_id.toString()+"'";
			affectedRows = stmt.executeUpdate(Sql);
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

	public Image getImageById(BigDecimal id){
		
		Connection conn = null;

		Image i = null;
		
		StringBuilder sb = new StringBuilder("Select id, auction_id, item_id, active, image");
		
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from image where id ="+id);


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
				i = new Image();

				i.setId(rs.getBigDecimal("id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));	
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setActive(rs.getInt("active"));
				i.setImageBytes(rs.getBytes("image"));
				
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
	
	public Image getImageBytesById(BigDecimal id) {
		Connection conn = null;
		Image i = null;	
		StringBuilder sb = new StringBuilder("Select image");
		sb.append(" from image where id ="+id);
		try {
			DBConnection dbConn = new DBConnection();
			conn = dbConn.getConnection();
			if(conn==null){
				dbConn = new DBConnection();
				conn = dbConn.getConnection();
			}		
			java.sql.Statement stmt = conn.createStatement();
			if(stmt==null || stmt.isClosed()){
				stmt = conn.createStatement();
			}
			ResultSet rs = stmt.executeQuery(sb.toString());
			while(rs.next()){
				i = new Image();
				i.setImageBytes(rs.getBytes("image"));
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
	
	public Image getThumbnailBytesById(BigDecimal id) {
		Connection conn = null;
		Image i = null;	
		StringBuilder sb = new StringBuilder("Select thumbnail");
		sb.append(" from image where id ="+id);
		try {
			DBConnection dbConn = new DBConnection();
			conn = dbConn.getConnection();
			if(conn==null){
				dbConn = new DBConnection();
				conn = dbConn.getConnection();
			}		
			java.sql.Statement stmt = conn.createStatement();
			if(stmt==null || stmt.isClosed()){
				stmt = conn.createStatement();
			}
			ResultSet rs = stmt.executeQuery(sb.toString());
			while(rs.next()){
				i = new Image();
				i.setImageBytes(rs.getBytes("thumbnail"));
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

	public Image getImageByAuctionId(BigDecimal auction_id){
		
		Connection conn = null;

		Image i = null;
		
		StringBuilder sb = new StringBuilder("Select image");

		sb.append(" from image where auction_id ="+auction_id+" ORDER BY id LIMIT 1");


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
				i = new Image();
				i.setImageBytes(rs.getBytes("image"));
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
	
public Image getThumbnailByAuctionId(BigDecimal auction_id){
		
		Connection conn = null;

		Image i = null;
		
		StringBuilder sb = new StringBuilder("Select thumbnail");

		sb.append(" from image where auction_id ="+auction_id+" LIMIT 1");


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
				i = new Image();
				i.setImageBytes(rs.getBytes("thumbnail"));
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
	
	public Image getImageByLotId(BigDecimal lot_id){
		
		Connection conn = null;

		Image i = null;
		
		StringBuilder sb = new StringBuilder("Select image");

		sb.append(" from image where lot_id ="+lot_id+" ORDER BY id LIMIT 1");


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
				i = new Image();
				i.setImageBytes(rs.getBytes("image"));
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
	
public Image getThumbnailByLotId(BigDecimal lot_id){
		
		Connection conn = null;

		Image i = null;
		
		StringBuilder sb = new StringBuilder("Select thumbnail");

		sb.append(" from image where lot_id ="+lot_id+" LIMIT 1");


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
				i = new Image();
				i.setImageBytes(rs.getBytes("thumbnail"));
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
	
	public Image getImageByItemId(BigDecimal item_id){
		
		Connection conn = null;

		Image i = null;
		
		StringBuilder sb = new StringBuilder("Select image");

		sb.append(" from image where item_id ="+item_id+" ORDER BY id LIMIT 1");


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
				i = new Image();
				i.setImageBytes(rs.getBytes("image"));
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
	
public Image getThumbnailByItemId(BigDecimal item_id){
		
		Connection conn = null;

		Image i = null;
		
		StringBuilder sb = new StringBuilder("Select thumbnail");

		sb.append(" from image where item_id ="+item_id+" LIMIT 1");


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
				i = new Image();
				i.setImageBytes(rs.getBytes("thumbnail"));
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
	
	public int insertImageOnCreate(
			Integer auction_id,
			Integer lot_id,
			Integer item_id,
			InputStream image,
			InputStream thumbnail,
			Integer active,
			BigDecimal user_id
		) {
	
	Connection conn = null;
	
	int affectedRows = 0;
	
	Image i = null;

	try {
		DBConnection dbConn = new DBConnection();
		conn = dbConn.getConnection();
		
		StringBuilder sb = new StringBuilder("INSERT into image (auction_id, lot_id, item_id, active, image, thumbnail");
		sb.append(", date_created, created_by)");
		sb.append(" VALUES(");
		sb.append(" ?, ?, ?, ?, ?, ?");
		sb.append(",?, ?");
		sb.append(")");
		
	    String sql = sb.toString();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        stmt.setInt(1, auction_id);
        stmt.setInt(2, lot_id);
        stmt.setInt(3, item_id);
        stmt.setInt(4, active);
        stmt.setBlob (5, image);
        stmt.setBlob (6, thumbnail);
        stmt.setTimestamp(7, sqlDate_t);
        stmt.setBigDecimal(8, user_id);

	    System.out.println("sql : "+sql);
	    affectedRows = stmt.executeUpdate();
	    
	    
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
	
	
	public Image insertImageOnCreate(
				BigDecimal auction_id,
				BigDecimal lot_id,
				BigDecimal item_id,
				File file,
				Integer active,
				Integer user_id
			) {
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Image i = null;

		try {
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			FileInputStream fis = null;
			if(file!=null){
				try {
					fis = new FileInputStream ( file );
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			StringBuilder sb = new StringBuilder("INSERT into image (auction_id, lot_id, item_id, active, image");

			sb.append(", date_created, created_by)");
			
			sb.append(" VALUES(");
			
			sb.append(" ?, ?, ?, ?, ?");
			sb.append(",?, ?");
			
			sb.append(")");
			
			
		    String sql = sb.toString();
	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


	        stmt.setBigDecimal(1, auction_id);
	        stmt.setBigDecimal(2, lot_id);
	        
	        stmt.setBigDecimal(3, item_id);
	        stmt.setInt(4, active);
	        stmt.setBlob (5, fis);
	        
	        stmt.setTimestamp(6, sqlDate_t);
	        stmt.setInt(7, user_id);

		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
	            throw new SQLException("Creating image failed, no rows affected.");
	        }
		    
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	i = new Image(); 
	            	i.setId(new BigDecimal(generatedKeys.getInt(1)));
	            	
	            }
	            else {
	                throw new SQLException("Creating image failed, no ID obtained.");
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

		return i;
	}
	
	
	public Image updateImageOnUpdate(
				BigDecimal auction_id,
				BigDecimal lot_id,
				BigDecimal item_id,
				File file,
				Integer active,
				Integer user_id,
				BigDecimal imageId_wip
			){
		
		Connection conn = null;
		
		int affectedRows = 0;
		
		Image i = null;
	
		FileInputStream fis = null;
		if(file!=null){
			try {
				fis = new FileInputStream ( file );
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		try {
			

			
			
			DBConnection dbConn = new DBConnection();
			
			conn = dbConn.getConnection();

			StringBuilder sb = new StringBuilder("Update image SET auction_id=?, lot_id=?, item_id=?, active=?, image=?");

			sb.append(", date_updated=?, updated_by=?");
			
			sb.append(" where id="+imageId_wip);

		    String sql = sb.toString();
		    
	        //PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	    
	        stmt.setBigDecimal(1, auction_id);
	        stmt.setBigDecimal(2, lot_id);
	        stmt.setBigDecimal(3, item_id);
	        stmt.setInt(4, active);
	        stmt.setBlob (5, fis);
	        
	        stmt.setTimestamp(6, sqlDate_t);
	        stmt.setInt(7, user_id);
	        
		    System.out.println("sql : "+sql);
		    
		    affectedRows = stmt.executeUpdate();
		    
		    if (affectedRows == 0) {
		    	i = null;
	        }else{
	        	i = new Image(); 
            	i.setId(imageId_wip);
            	
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

	public List<Image> getImageList(){

		List<Image> iList = new ArrayList<Image>();

		StringBuilder sb = new StringBuilder("Select id, auction_id, item_id, active, image");
	
		sb.append(", date_created, created_by, date_updated, updated_by");
		
		sb.append(" from image");
		
		sb.append(" order by auction_id, item_id desc");

		try {
			conn = getConnection();

			java.sql.Statement stmt = conn.createStatement();

			System.out.println("sql : "+sb.toString());
			
			ResultSet rs = stmt.executeQuery(sb.toString());

			Image i = null;

			while(rs.next()){
				i = new Image();
				i.setId(rs.getBigDecimal("id"));
				i.setAuction_id(rs.getBigDecimal("auction_id"));	
				i.setItem_id(rs.getBigDecimal("item_id"));
				i.setActive(rs.getInt("active"));
				i.setImageBytes(rs.getBytes("image"));

				//SystemBean - start
				i.setDate_created(rs.getTimestamp("date_created"));
				i.setDate_updated(rs.getTimestamp("date_updated"));
				i.setCreated_by(rs.getInt("created_by"));
				i.setUpdated_by(rs.getInt("updated_by"));
				//SystemBean - end
				
				iList.add(i);
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
		
		return iList;
	}
	
	

}

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
import hmr.com.util.StringUtil;

public class CategoryLevelDao extends DBConnection {

	private Connection conn = null;
	DBConnection dbConn = null;

	

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	
	public CategoryLevelDao(){
		dbConn = new DBConnection();
	}
	
	public CategoryLevelDao(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	

}

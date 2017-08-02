package hmr.com.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import hmr.com.manager.AuctionManager;
import hmr.com.manager.ImageManager;
import hmr.com.manager.ItemManager;
import hmr.com.manager.LoginManager;
import hmr.com.manager.UserManager;
import hmr.com.util.DBConnection;
import hmr.com.bean.Auction;
import hmr.com.bean.Item;
import hmr.com.bean.User;

@SuppressWarnings("serial")
public class Image extends HttpServlet {
	 
	//String emaidAddPost = null;
	//String emaidAddGet = null;
	
	String managerGet = null;
	String actionGet = null;
	String userIdGet = null;
	String vekGet = null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doProcess(req, res);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("Controller doProcessGet - start");
		doProcess(req, res);
		System.out.println("");
		System.out.println("Controller doProcessGet - end");

	}
	
	@SuppressWarnings("unchecked")
	protected void doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		System.out.println("Controller - start");
		System.out.println("");
		
		OutputStream output = res.getOutputStream();
		
		try {
		
		BigDecimal id = new BigDecimal(req.getParameter("id")) ;
		//String s = req.getParameter("s") ;
		String t = req.getParameter("t") ;
		
		ImageManager iMngr = new ImageManager(req, res);
		hmr.com.bean.Image i = new hmr.com.bean.Image();

		if(t!=null){
			if("i".equals(t)){
				i = iMngr.getImageByItemId(id);
			}else if("l".equals(t)){
				i = iMngr.getImageByLotId(id);
			}else if("a".equals(t)){
				i = iMngr.getImageByAuctionId(id);
			}else if("it".equals(t)){
				i = iMngr.getThumbnailByItemId(id);
			}else if("lt".equals(t)){
				i = iMngr.getThumbnailByLotId(id);
			}else if("at".equals(t)){
				i = iMngr.getThumbnailByAuctionId(id);
			}else {
				i = iMngr.getThumbnailBytesById(id);
			}
			
		} else {
			 i = iMngr.getImageBytesById(id);
		}

			if(i!=null){
				byte barray[] = i.getImageBytes();
				output.write(barray);
				res.setContentType("image/png");
			}else{
				throw new Exception();
			}
		} catch (Exception ex) {
			BufferedImage originalImage = ImageIO.read(new File(getClass().getClassLoader().getResource("hmr.jpg").getFile()));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( originalImage, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			output.write(imageInByte);
			res.setContentType("image/jpg");

		} finally {
			output.close();
		}
		
		
		

		//res.set
		System.out.println("");
		System.out.println("Controller - end");
	}
	
}



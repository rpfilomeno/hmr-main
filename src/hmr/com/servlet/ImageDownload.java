package hmr.com.servlet;

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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import bizoncloudone.com.bean.ParamsLov;
import bizoncloudone.com.bean.Request;
import bizoncloudone.com.bean.RoleLogin;
import bizoncloudone.com.bean.UserLogin;
import bizoncloudone.com.bean.UserRoleLogin;
import hmr.com.manager.AuctionManager;
import hmr.com.manager.ItemManager;
import hmr.com.manager.LoginManager;
import hmr.com.manager.UserManager;
import bizoncloudone.com.manager.ParamsLovManager;
import bizoncloudone.com.manager.RequestManager;
import bizoncloudone.com.manager.RoleLoginManager;
import bizoncloudone.com.manager.RunnableEmailManager;
import bizoncloudone.com.manager.UserLoginManager;
import bizoncloudone.com.util.DBConnection;
import hmr.com.bean.Auction;
import hmr.com.bean.Item;
import hmr.com.bean.User;

@SuppressWarnings("serial")
public class ImageDownload extends HttpServlet {
	 
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
		
		Integer id = Integer.valueOf(req.getParameter("id")) ;
		String s = req.getParameter("s") ;
		String t = req.getParameter("t") ;

		if(t.equals("i")){
			
			ItemManager iMngr = new ItemManager(req, res);
			
			Item i = iMngr.getItemById(new BigDecimal(id));
			//InputStream input = a.getImageInputStream();
			
			if(i!=null){
				
				byte barray[] = null;

					barray = i.getImageBytes();
				

				res.setContentType("image/jpg");
				res.setHeader("Content-disposition","attachment; filename=" +"c:\\hmr-image-"+id+"70x82.jpg");
				res.getOutputStream().write(barray); 
				res.getOutputStream().flush();  

			}
			
			
		}else{
			
			AuctionManager aMngr = new AuctionManager(req, res);
			Auction a = aMngr.getAuctionById(new BigDecimal(id));
			//InputStream input = a.getImageInputStream();
			
			if(a!=null){
				
				byte barray[] = null;
				if("s".equals(s)){
					barray = a.getImageSmallBytes();
				}else{
					barray = a.getImageBytes();
				}

				res.setContentType("image/jpg");
				res.setHeader("Content-disposition","attachment; filename=" +"c:\\hmr-image-"+id+"70x82.jpg");
				res.getOutputStream().write(barray); 
				res.getOutputStream().flush();  

			}
			
			
		}


		//res.set
		System.out.println("");
		System.out.println("Controller - end");
	}
	
}



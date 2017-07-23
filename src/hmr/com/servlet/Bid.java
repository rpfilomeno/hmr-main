package hmr.com.servlet;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bizoncloudone.com.bean.ParamsLov;
import bizoncloudone.com.bean.Request;
import bizoncloudone.com.bean.RoleLogin;
import bizoncloudone.com.bean.UserLogin;
import bizoncloudone.com.bean.UserRoleLogin;
import hmr.com.manager.AuctionManager;
import hmr.com.manager.AuctionRangeManager;
import hmr.com.manager.AuctionUserBiddingMaxManager;
import hmr.com.manager.AuctionUserManager;
import hmr.com.manager.BiddingTransactionManager;
import hmr.com.manager.ImageManager;
import hmr.com.manager.ItemManager;
import hmr.com.manager.LoginManager;
import hmr.com.manager.LotManager;
import hmr.com.manager.LotRangeManager;
import hmr.com.manager.RunnableNegotiatedBidManager;
import hmr.com.manager.UploadAuctionManager;
import hmr.com.manager.UserManager;
import hmr.com.util.EmailUtil;
import bizoncloudone.com.manager.ParamsLovManager;
import bizoncloudone.com.manager.RequestManager;
import bizoncloudone.com.manager.RoleLoginManager;
import bizoncloudone.com.manager.RunnableEmailManager;
import bizoncloudone.com.manager.UserLoginManager;
import bizoncloudone.com.util.DBConnection;
import hmr.com.bean.Auction;
import hmr.com.bean.AuctionRange;
import hmr.com.bean.BiddingTransaction;
import hmr.com.bean.Image;
import hmr.com.bean.Item;
import hmr.com.bean.Lot;
import hmr.com.bean.LotRange;
import hmr.com.bean.User;
import hmr.com.dao.AuctionDao;
import hmr.com.dao.UserDao;

@SuppressWarnings("serial")
public class Bid extends HttpServlet {

	String managerGet = null;
	String actionGet = null;
	String userIdGet = null;
	String vekGet = null;
	private OutputStream out;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doProcess(req, resp);

	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("Controller doProcessGet - start");
		
		String action = req.getParameter("a")!=null ? (String)req.getParameter("a") : "";
		String uid = req.getParameter("uid")!=null ? (String)req.getParameter("uid") : "";
		String mngr = req.getParameter("mngr")!=null ? (String)req.getParameter("mngr") : "";
		String vek = req.getParameter("vek")!=null ? (String)req.getParameter("vek") : "";
		String aid = req.getParameter("aid")!=null ? (String)req.getParameter("aid") : "";
		
		managerGet = mngr;
		actionGet = action;
		userIdGet = uid;
		vekGet = vek;
		
		System.out.println("managerGet : "+managerGet);
		System.out.println("actionGet : "+actionGet);
		System.out.println("userIdGet : "+userIdGet);
		System.out.println("vekGet : "+vek);
		
		if(actionGet!=null){
			
			req.setAttribute("action", actionGet);
			req.setAttribute("userId", userIdGet);
			req.setAttribute("manager", managerGet);
			req.setAttribute("vek", vekGet);
			req.setAttribute("aid", aid);
			

			System.out.println("");
			System.out.println("Controller doProcessGet - end");
			RequestDispatcher rd = req.getRequestDispatcher("security-check.jsp");
			rd.forward(req, res);
			
		}else{
			doProcess(req, res);
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	protected void doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		System.out.println("Controller - start");
		System.out.println("");
		
		String COMPANY_NAME = (String)req.getSession().getAttribute("COMPANY_NAME");
		String COMPANY_NAME_ACRONYM = (String)req.getSession().getAttribute("COMPANY_NAME_ACRONYM");
		String SKIN_HEADER_COLOR = (String)req.getSession().getAttribute("SKIN_HEADER_COLOR");
		String CREDENTIALS_DIRECTORY = (String)req.getSession().getAttribute("CREDENTIALS_DIRECTORY");
		String HOST_NAME = (String)req.getSession().getAttribute("HOST_NAME");
		String SERVER_DIRECTORY = (String)req.getSession().getAttribute("SERVER_DIRECTORY");
		String PROTOCOL = (String)req.getSession().getAttribute("PROTOCOL");
		String ADMIN_MOBILE_NO = (String)req.getSession().getAttribute("ADMIN_MOBILE_NO");
		String ADMIN_EMAIL_ADD_CC = (String)req.getSession().getAttribute("ADMIN_EMAIL_ADD_CC");
		String SERVER_DIRECTORY_HMR_IMAGES = (String)req.getSession().getAttribute("SERVER_DIRECTORY_HMR_IMAGES");
		
		

		if(COMPANY_NAME==null){
		    try {
	            Properties prop = new Properties();
	            InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("app.properties");
	            prop.load(inputStream);
	            
	            COMPANY_NAME = prop.getProperty("COMPANY_NAME");
	            COMPANY_NAME_ACRONYM = prop.getProperty("COMPANY_NAME_ACRONYM");
	            SKIN_HEADER_COLOR = prop.getProperty("SKIN_HEADER_COLOR");

	            String HOST_NAME_ = prop.getProperty("HOST_NAME");
	            String HOST_NAME_ONLINE_ = prop.getProperty("HOST_NAME_ONLINE");
	            String PROTOCOL_ = prop.getProperty("PROTOCOL");
	            String PROTOCOL_ONLINE_ = prop.getProperty("PROTOCOL_ONLINE");
	            String SERVER_DIRECTORY_ = prop.getProperty("SERVER_DIRECTORY");
	            String SERVER_DIRECTORY_ONLINE_ = prop.getProperty("SERVER_DIRECTORY_ONLINE");
	            String CREDENTIALS_DIRECTORY_ = prop.getProperty("CREDENTIALS_DIRECTORY");
	            String CREDENTIALS_DIRECTORY_ONLINE_ = prop.getProperty("CREDENTIALS_DIRECTORY_ONLINE");
	            ADMIN_MOBILE_NO = prop.getProperty("ADMIN_MOBILE_NO");
	            ADMIN_EMAIL_ADD_CC = prop.getProperty("ADMIN_EMAIL_ADD_CC");
	            SERVER_DIRECTORY_HMR_IMAGES = prop.getProperty("SERVER_DIRECTORY_HMR_IMAGES");
	            String IS_ONLINE_ = prop.getProperty("IS_ONLINE");
	            
	            
	            if("YES".equals(IS_ONLINE_)){
	            	HOST_NAME = HOST_NAME_ONLINE_;
	            	PROTOCOL = PROTOCOL_ONLINE_;
	            	SERVER_DIRECTORY = SERVER_DIRECTORY_ONLINE_;
	            	CREDENTIALS_DIRECTORY = CREDENTIALS_DIRECTORY_ONLINE_;
	            }else{
	            	HOST_NAME = HOST_NAME_;
	            	PROTOCOL = PROTOCOL_;
	            	SERVER_DIRECTORY = SERVER_DIRECTORY_;
	            	CREDENTIALS_DIRECTORY = CREDENTIALS_DIRECTORY_;
	            }
	            
	            req.getSession().setAttribute("HOST_NAME", HOST_NAME);
	            req.getSession().setAttribute("SERVER_DIRECTORY", SERVER_DIRECTORY);
	            req.getSession().setAttribute("PROTOCOL", PROTOCOL);
	            req.getSession().setAttribute("COMPANY_NAME", COMPANY_NAME);
	            req.getSession().setAttribute("COMPANY_NAME_ACRONYM", COMPANY_NAME_ACRONYM);
	            req.getSession().setAttribute("SKIN_HEADER_COLOR", SKIN_HEADER_COLOR);
	            req.getSession().setAttribute("CREDENTIALS_DIRECTORY", CREDENTIALS_DIRECTORY);
	            req.getSession().setAttribute("ADMIN_MOBILE_NO", ADMIN_MOBILE_NO);
	            req.getSession().setAttribute("ADMIN_EMAIL_ADD_CC", ADMIN_EMAIL_ADD_CC);
	            req.getSession().setAttribute("SERVER_DIRECTORY_HMR_IMAGES", SERVER_DIRECTORY_HMR_IMAGES);
	            
	            
	            System.out.println("HOST_NAME = "+HOST_NAME);
	            System.out.println("SERVER_DIRECTORY = "+SERVER_DIRECTORY);
	            System.out.println("PROTOCOL = "+PROTOCOL);
	            System.out.println("COMPANY_NAME = "+COMPANY_NAME);
	            System.out.println("COMPANY_NAME_ACRONYM = "+COMPANY_NAME_ACRONYM);
	            System.out.println("SKIN_HEADER_COLOR = "+SKIN_HEADER_COLOR);
	            System.out.println("CREDENTIALS_DIRECTORY = "+CREDENTIALS_DIRECTORY);
	            System.out.println("ADMIN_MOBILE_NO = "+ADMIN_MOBILE_NO);
	            System.out.println("ADMIN_EMAIL_ADD_CC = "+ADMIN_EMAIL_ADD_CC);
	            System.out.println("SERVER_DIRECTORY_HMR_IMAGES = "+SERVER_DIRECTORY_HMR_IMAGES);
  

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
		}//company name
		

    
		String sid = req.getSession().getId();
		String manager = req.getParameter("manager")!=null ? (String)req.getParameter("manager") : "";
		String action = req.getParameter("action")!=null ? (String)req.getParameter("action") : "";
		String userId = req.getParameter("userId")!=null ? (String)req.getParameter("userId") : (String)req.getAttribute("userId");
		String vek = req.getParameter("vek")!=null ? (String)req.getParameter("vek") : (String)req.getAttribute("vek");
		String aid = req.getParameter("aid")!=null ? (String)req.getParameter("aid") : (String)req.getAttribute("aid");
		String action_id = req.getParameter("action_id")!=null ? (String)req.getParameter("action_id") : "";
		List<FileItem> files = new ArrayList<FileItem>();
		
		
		
		String page = null;

		
		File auction_file = null;
		File auction_file_small = null;
		File item_file = null;
		//String file_name = "";
		BigDecimal user_id = BigDecimal.ZERO;
		if(req.getSession().getAttribute("user-id")!=null ) {
			user_id =  new BigDecimal((String) ""+req.getSession().getAttribute("user-id"));
		}
		
		BigDecimal auctionId_wip = BigDecimal.ZERO;
		BigDecimal itemId_wip = BigDecimal.ZERO;
		
        /*if(ServletFileUpload.isMultipartContent(req)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                    	System.out.println("item.getFieldName() "+item.getFieldName());
                    	if("file".equals(item.getFieldName()) &&  item.getSize() > 0){
                    		files.add(item);
                    	}
                    }else{                   	
                    	String fieldname = item.getFieldName();
                        String fieldvalue = item.getString();
                        if (fieldname.equals("action")) {
                        	action=fieldvalue;
                        	req.getSession().setAttribute("action", action);
                        }else if (fieldname.equals("manager")) {
                        	manager=fieldvalue;
                        	req.getSession().setAttribute("manager", manager);
                        }else if (fieldname.equals("userId")) {
                        	userId=fieldvalue;
                        	req.getSession().setAttribute("userId", userId);
                        }else if (fieldname.equals("user-id")) {
                        	user_id=Integer.valueOf(fieldvalue) ;
                        	req.getSession().setAttribute("user-id", user_id);
                        }else if (fieldname.equals("auctionId_wip")) {
                        	if(fieldvalue!=null && !"".equals(fieldvalue)){
                        		auctionId_wip=new BigDecimal(fieldvalue) ;
                        	}else{
                        		auctionId_wip = new BigDecimal(0);
                        	}
                        	                        	
                        	req.getSession().setAttribute("auctionId-wip", auctionId_wip);
                        	req.setAttribute("auctionId-wip", auctionId_wip);
                        }else if (fieldname.equals("itemId_wip")) {
                        	if(fieldvalue!=null && !"".equals(fieldvalue)){
                        		itemId_wip=new BigDecimal(fieldvalue) ;
                        	}else{
                        		itemId_wip = new BigDecimal(0);
                        	}
                        	req.getSession().setAttribute("itemId-wip", itemId_wip);
                        }else if (fieldname.equals("auction_id")) {
                        	BigDecimal auction_id = new BigDecimal(0);
                        	if(fieldvalue!=null && !"".equals(fieldvalue)){
                        		auction_id=new BigDecimal(fieldvalue) ;
                        	}else{
                        		auction_id = new BigDecimal(0);
                        	}
                        	req.getSession().setAttribute("auction_id", auction_id);
                        	req.setAttribute("auction_id", auction_id);
                        }else if (fieldname.equals("action_id")) {
                        	if(fieldvalue!=null && !"".equals(fieldvalue)){
                        		action_id=fieldvalue;
                        	}
                        	req.getSession().setAttribute("action_id", action_id);
                        	req.setAttribute("action_id", action_id);
                        }
                    }
                }
            } catch (Exception ex) {
            	ex.printStackTrace();
            	req.setAttribute("message", "File Upload Failed due to " + ex);
            }          
        }//file uploads
         */		
		
		System.out.println("Paramerters - start");
		System.out.println("manager : "+manager);
		System.out.println("action : "+action);
		System.out.println("userId : "+userId);
		System.out.println("vek : "+vek);
		System.out.println("Paramerters - end");
		System.out.println("");


        AuctionManager aMngr = new AuctionManager(req,res);
        AuctionUserManager auMngr = new AuctionUserManager();
        
        
		
		//all page get requests
		if(manager.equals("get")){
			
			if("private-invite".equals(action)){

				Auction a = new AuctionManager().getAuctionByToken(aid);
				req.setAttribute("auction", a);

				if(userId!=null){
					req.setAttribute("userId", userId);
				}
				// Lets make sure user logs in first
				page ="invite-login.jsp";
				
			} else
			
			if("login".equals(action)){
				
				if(userId!=null){
					req.setAttribute("userId", userId);
				}
				
				page ="login.jsp";
			
			}else if("registration".equals(action)){
				
				page ="registration.jsp";
				
			}else if("home".equals(action)){
				
				page ="index.jsp";
				
			}else if("forgotPassword".equals(action)){
				
				page ="forgot-password.jsp";
				
			}else if("logout".equals(action)){
				System.out.println("User log out successful.");

				req.getSession().removeAttribute("firstName");
				req.getSession().removeAttribute("lastName");
				req.getSession().removeAttribute("fullName");
				req.getSession().removeAttribute("userId");
				req.getSession().removeAttribute("user-id");
				req.getSession().removeAttribute("user-role-id");
				req.getSession().removeAttribute("user-lov-role");

				page ="index.jsp";
			}else if("verifyEmail".equals(action)){
				UserManager uMngr = new UserManager(req,res);
				page = uMngr.doUserManager();
				
			}else if("auctionBidDetails".equals(action)){
				AuctionRangeManager arMngr = new AuctionRangeManager(req,res);
				LotManager lMngr = new LotManager(req,res);
				ItemManager iMngr = new ItemManager(req,res);
				List<Lot> lList = new ArrayList<Lot>();
				List<Lot> lListExpired = new ArrayList<Lot>();
				LotRangeManager lrMngr = new LotRangeManager();
				
				Auction a = aMngr.getAuctionById(new BigDecimal(aid));
				iMngr.setLovValuesCurrency(req, res);
				
				//AuctionRange ar = new AuctionRange();
				//List<AuctionRange> arList = arMngr.getAuctionRangeListByAuctionId(a.getAuction_id());
				//HashMap<BigDecimal, Lot> lotHM  = lMngr.getLotHMByAuctionId(a.getAuction_id());
				//List<Item> iList = iMngr.getItemListByAuctionId(a.getAuction_id());
				
				List<Lot> lotList = lMngr.getLotListByAuctionId(a.getAuction_id());
				for(Lot lot : lotList){
					BigDecimal increment_amount = BigDecimal.ZERO;

					//Check if there is lot level bid increment else use auction level value
					increment_amount = lrMngr.getIncrementAmountByLotId(lot.getLot_id(), lot.getAmount_bid());
					if(increment_amount.equals(BigDecimal.ZERO)) {
						System.out.println("Using auction bid increment on lot");
						increment_amount = arMngr.getIncrementAmountByAuctionId(a.getAuction_id(), lot.getAmount_bid());
					}
					BigDecimal amount_bid_next=  increment_amount.add(lot.getAmount_bid());
					lot.setAmount_bid_next(amount_bid_next);

					
					//check of there is lot level end_date_time
					if(lot.getEnd_date_time()==null) {
						System.out.println("Using auction end date time on lot");
						lot.setEnd_date_time(a.getEnd_date_time());
					}
					
					//check if the end time is expired
					if(lot.getEnd_date_time().before(new Timestamp(System.currentTimeMillis()))) {
						lot.setIs_bid(0);
						lot.setIs_buy(0);
						lListExpired.add(lot);
					} else {
						lList.add(lot);
					}
					
							
				}//for loop
				
				//append the expired lots to end
				if(!lListExpired.isEmpty()) lList.addAll(lListExpired);
				
				List<Image> auction_images = new ImageManager().getImageListByAuctionId(a.getAuction_id());

				req.setAttribute("lList", lList);
				req.setAttribute("auction", a);
				req.setAttribute("auction_images", auction_images);
				//req.setAttribute("auction-item", a);
				//req.setAttribute("lotHM", lotHM);
				//req.setAttribute("arList", arList);
				
				page ="auction-bid-details.jsp";
				
			}else if("auctionBidDetailsDoBid".equals(action)){
				BiddingTransactionManager bMngr = new BiddingTransactionManager();
				bMngr.doBiddingTransactionManager();
			}
			
		}//page get requests

		//all non-page get requests
		if(userId!=null && !"".equals(userId) && !manager.equals("get")){
			
			if(manager.equals("image-manager")) {
				ImageManager iMngr = new ImageManager();
				//image listing
				if("auctionImageDelete".equals(action)) {
					if(iMngr.deleteImage(new BigDecimal(action_id))) {
						System.out.println("Auction image deleted"+action_id);
						req.setAttribute("msgbgcol", "green");
						req.setAttribute("msgInfo", "Auction image deleted.");
					}
					
					String auctionId = req.getParameter("wip_id")!=null ? (String)req.getParameter("wip_id") : (String)req.getParameter("auctionId_wip");
					req.setAttribute("images", iMngr.getImageListByAuctionId(new BigDecimal(auctionId)));
					req.setAttribute("action_id", auctionId);
					req.setAttribute("action", "doAuctionImageUpload");
					page = "image-upload.jsp";
					
				}else if("lotImageDelete".equals(action)) {
					if(iMngr.deleteImage(new BigDecimal(action_id))) {
						System.out.println("Lot image deleted"+action_id);
						req.setAttribute("msgbgcol", "green");
						req.setAttribute("msgInfo", "Lot image deleted.");
					}
					
					String lotId = req.getParameter("wip_id")!=null ? (String)req.getParameter("wip_id") : (String)req.getParameter("auctionId_wip");
					req.setAttribute("images", iMngr.getImageListByLotId(new BigDecimal(lotId)));
					req.setAttribute("action_id", lotId);
					req.setAttribute("action", "doLotImageUpload");
					page = "image-upload.jsp";
					
				}else if("itemImageDelete".equals(action)) {
					if(iMngr.deleteImage(new BigDecimal(action_id))) {
						System.out.println("Item image deleted"+action_id);
						req.setAttribute("msgbgcol", "green");
						req.setAttribute("msgInfo", "Item image deleted.");
					}
					String itemId = req.getParameter("wip_id")!=null ? (String)req.getParameter("wip_id") : (String)req.getParameter("auctionId_wip");
					req.setAttribute("images", iMngr.getImageListByItemId(new BigDecimal(itemId)));
					req.setAttribute("action_id", itemId);
					req.setAttribute("action", "doItemImageUpload");
					page = "image-upload.jsp";
					
				}else if("auctionImageUpload".equals(action)) {
					String auctionId = req.getParameter("auction_id")!=null ? (String)req.getParameter("auction_id") : (String)req.getParameter("auctionId_wip");
					req.setAttribute("images", iMngr.getImageListByAuctionId(new BigDecimal(auctionId)));
					req.setAttribute("action_id", auctionId);
					req.setAttribute("action", "doAuctionImageUpload");
					page = "image-upload.jsp";
				}else if("lotImageUpload".equals(action)) {
					String lotId = req.getParameter("lotId_wip")!=null ? (String)req.getParameter("lotId_wip") : (String)req.getParameter("lot_id");
					req.setAttribute("images", iMngr.getImageListByLotId(new BigDecimal(lotId)));
					req.setAttribute("action_id", lotId);
					req.setAttribute("action", "doLotImageUpload");
					page = "image-upload.jsp";
				}else if("itemImageUpload".equals(action)) {
					String itemId = req.getParameter("itemId_wip")!=null ? (String)req.getParameter("itemId_wip") : (String)req.getParameter("itemId");
					req.setAttribute("images", iMngr.getImageListByItemId(new BigDecimal(itemId)));
					req.setAttribute("action_id", itemId);
					req.setAttribute("action", "doItemImageUpload");
					page = "image-upload.jsp";
				}
				
				//posted
				if("doAuctionImageUpload".equals(action)) {
					System.out.println("Auction image uploaded");
					
					if(!files.isEmpty()) {
						for (FileItem item : files) {
							System.out.println("item.getFieldName() "+item.getFieldName());
							InputStream inputStream = item.getInputStream();
                    	    String lot_no = "0";
                    	    String lot_id = "0";
                    	    String auction_id = "0";
                    	    
                    	    try {
                    	    	Pattern regex = Pattern.compile("(^\\d+)");
                    	    	Matcher regexMatcher = regex.matcher(item.getName());
                    	    	if (regexMatcher.find()) {
                    	    		lot_no = regexMatcher.group(1);
                    	    		System.out.println("File is assigned to lot id: " + lot_no);
                    	    	}
                    	    } catch (PatternSyntaxException ex) {
                    	    	// Syntax error in the regular expression
                    	    }
                    	    
                    	    //Lets try to find the lot_id given lot_no and action_id=auction_id
                    	    LotManager ltMngr = new LotManager();
                    	    Lot lot = ltMngr.getLotByAuctionIdAndLotNo(new BigDecimal(action_id), new BigDecimal(lot_no));
                    	    if( lot != null) {
                    	    	lot_id = lot.getLot_id().toString();
                    	    } else {
                    	    	auction_id = action_id;
                    	    }

                    	    
                    	    if(!iMngr.insertImageInputStream(
    								Integer.valueOf(auction_id),
    								Integer.valueOf(lot_id),
    								Integer.valueOf("0"), 
    								inputStream, 
    								Integer.valueOf("1"), 
    								user_id)) {
    							throw new RuntimeException("Image not uploaded.");
    						}
						}
					}
					page = "blank.jsp";
				}else if("doLotImageUpload".equals(action)) {
					System.out.println("Lot image uploaded");
					if(!files.isEmpty()) {
						for (FileItem item : files) {
							System.out.println("item.getFieldName() "+item.getFieldName());
							InputStream inputStream = item.getInputStream();
                    	    String item_id = "0";
                    	    String lot_id = "0";
                    	    
                    	    try {
                    	    	Pattern regex = Pattern.compile("(^\\d+)");
                    	    	Matcher regexMatcher = regex.matcher(item.getName());
                    	    	if (regexMatcher.find()) {
                    	    		item_id = regexMatcher.group(1);
                    	    		System.out.println("File is assigned to item id: " + item_id);
                    	    	}
                    	    } catch (PatternSyntaxException ex) {
                    	    	// Syntax error in the regular expression
                    	    }
                    	    if( item_id == "0") {
                    	    	lot_id = action_id;
                    	    }
                    	    
                    	    if(!iMngr.insertImageInputStream(
    								Integer.valueOf("0"),
    								Integer.valueOf(lot_id),
    								Integer.valueOf(item_id), 
    								inputStream, 
    								Integer.valueOf("1"), 
    								user_id)) {
    							throw new RuntimeException("Image not uploaded.");
    						}
						}
					}
					page = "blank.jsp";
				}else if("doItemImageUpload".equals(action)) {
					System.out.println("Item image uploaded");
					if(!files.isEmpty()) {
						for (FileItem item : files) {
							System.out.println("item.getFieldName() "+item.getFieldName());
							InputStream inputStream = item.getInputStream();
                    	    if(!iMngr.insertImageInputStream(
    								Integer.valueOf("0"),
    								Integer.valueOf("0"),
    								Integer.valueOf(action_id), 
    								inputStream, 
    								Integer.valueOf("1"), 
    								user_id)) {
    							throw new RuntimeException("Image not uploaded.");
    						}
						}
					}
					page = "blank.jsp";
				}
				
				
				
				
			} else if(manager.equals("bid-manager")) {
				String doAction = req.getParameter("doaction")!=null ? (String)req.getParameter("doaction") : "";
				String reqlotId = req.getParameter("lotId")!=null ? (String)req.getParameter("lotId") : "";
				String reqamount = req.getParameter("amount")!=null ? (String)req.getParameter("amount") : "";
				String requnitqty = req.getParameter("unit_qty")!=null ? (String)req.getParameter("unit_qty") : "0";
				String note = req.getParameter("note")!=null ? (String)req.getParameter("note") : "";
				
				if(reqlotId!="" && reqamount!="") {
					UserDao ud = new UserDao();
					User u = ud.getUser(userId);
					
					BiddingTransactionManager btMngr = new BiddingTransactionManager();
					AuctionUserBiddingMaxManager auMngr1 = new AuctionUserBiddingMaxManager();
					Integer lotId = Integer.valueOf(reqlotId);
					BigDecimal amount = new BigDecimal(reqamount);
					Integer unit_qty = Integer.valueOf(requnitqty);
			
					//BID, MAX BID and BUY button clicks
					if(doAction.equals("BID")) {
						btMngr.insertBiddingTransactionMakeBid(lotId, amount, u.getId(), unit_qty);
						req.setAttribute("msgbgcol", "green");
						req.setAttribute("msgInfo", "Bid submitted.");
					}else if(doAction.equals("BUY")) {
						btMngr.insertBiddingTransactionMakeBuy(lotId, amount, u.getId(), unit_qty);
						req.setAttribute("msgbgcol", "green");
						req.setAttribute("msgInfo", "Buy submitted.");
					}else if(doAction.equals("SET-MAXIMUM-BID")) {
						auMngr1.insertAuctionUserBiddingMaxManager(lotId, amount, u.getId(),unit_qty);
						req.setAttribute("msgbgcol", "green");
						req.setAttribute("msgInfo", "Maximum bid submitted.");
					}else if(doAction.equals("PRE-BID")) {
						auMngr1.insertAuctionUserBiddingMaxManager(lotId, amount, u.getId(),unit_qty);
						req.setAttribute("msgbgcol", "green");
						req.setAttribute("msgInfo", "Pre-bid submitted.");
					}else if(doAction.equals("NEGOTIATED")) {
						btMngr.insertBiddingTransactionNegotiated(lotId, amount, u.getId(), unit_qty, note);
						req.setAttribute("msgbgcol", "green");
						req.setAttribute("msgInfo", "Negotiated bid submitted.");
						
						
						LotManager lMngr = new LotManager();
						
						Lot lot = lMngr.getLotByLotId(new BigDecimal(lotId));
						Auction auction = aMngr.getAuctionByAuctionId(lot.getAuction_id());
						
						RunnableNegotiatedBidManager adminRNBM = new RunnableNegotiatedBidManager(
								"HMR Auctions : Negotiated Bid Admin Notification", 
								"noreplyhmrauctions@gmail.com", "", 
								lot.getAuction_id().toString(), auction.getAuction_name(), auction.getAuction_desc(), 
								lot.getId().toString(), lot.getLot_no().toString(), lot.getLot_name(), lot.getLot_desc(), 
								u.getId().toString(), u.getFirst_name(), u.getLast_name(), u.getEmail_address(), 
								amount.toString(), note, unit_qty.toString());
						adminRNBM.start();
						
						RunnableNegotiatedBidManager bidderRNB = new RunnableNegotiatedBidManager(
								"HMR Auctions : Negotiated Bid User Notification", 
								u.getEmail_address(), "", 
								lot.getAuction_id().toString(), auction.getAuction_name(), auction.getAuction_desc(), 
								lot.getId().toString(), lot.getLot_no().toString(), lot.getLot_name(), lot.getLot_desc(), 
								u.getId().toString(), u.getFirst_name(), u.getLast_name(), u.getEmail_address(), 
								amount.toString(), note, unit_qty.toString());
						bidderRNB.start();
						
						
						
						
					}
					
					BigDecimal lotId_wip = req.getParameter("lotId_wip")!=null ? new BigDecimal(req.getParameter("lotId_wip")): new BigDecimal(0);
					
					System.out.println("lotId_wip : "+lotId_wip);
					
					AuctionRangeManager arMngr = new AuctionRangeManager(req,res);
					ItemManager iMngr = new ItemManager(req,res);
					LotRangeManager lrMngr = new LotRangeManager();
					
					LotManager lMngr = new LotManager();
					Lot l = lMngr.getLotById(lotId_wip);
					
					Auction a = aMngr.getAuctionByAuctionId(l.getAuction_id());
					

					BigDecimal increment_amount = BigDecimal.ZERO;

					//Check if there is lot level bid increment else use auction level value
					increment_amount = lrMngr.getIncrementAmountByLotId(l.getLot_id(), l.getAmount_bid());
					if(increment_amount.equals(BigDecimal.ZERO)) {
						System.out.println("Using auction bid increment on lot");
						increment_amount = arMngr.getIncrementAmountByAuctionId(a.getAuction_id(), l.getAmount_bid());
					}
					BigDecimal amount_bid_next=  increment_amount.add(l.getAmount_bid());
					l.setAmount_bid_next(amount_bid_next);
					
					//Check if there is lot level bid increment else use auction level value
					if(l.getEnd_date_time() == null) {
						
						l.setEnd_date_time(a.getEnd_date_time());
					}
					
					//check if the end time is expired
					if(l.getEnd_date_time().before(new Timestamp(System.currentTimeMillis()))) {
						l.setIs_bid(0);
						l.setIs_buy(0);
					} 
					
					List<Item> iL = iMngr.getLotItemsById(l.getLot_id());
					
					
					List<Image> lot_images = new ImageManager().getImageListByLotId(l.getId());
					
					List<BiddingTransaction> bidding_transactions = new BiddingTransactionManager().getLatestBiddingTransactionLotId(l.getLot_id());
					req.setAttribute("bidding_transactions", bidding_transactions);
					req.setAttribute("lot_images", lot_images);
					req.setAttribute("lot", l);
					req.setAttribute("items", iL);
					req.setAttribute("auction", a);
					
					
					
					page = "lot-bid-details.jsp";
					
					
				}
			} else if(manager.equals("login-manager")){
				LoginManager liMngr = new LoginManager(req,res);
				page = liMngr.doLoginManager();
				
				
			}else if(manager.equals("user-manager")){
				UserManager uMngr = new UserManager(req,res);
				page = uMngr.doUserManager();
				
			}else if(manager.equals("auction-manager")){		
				if("saveAuctionImage".equals(action) && (auction_file_small!=null || auction_file!=null) ){
					System.out.println("BID saveAuctionImage "+auction_file_small+" - "+auction_file);
					page = aMngr.doAuctionManager(auction_file_small, auction_file, action, auctionId_wip);
				}else{
					page = aMngr.doAuctionManager();
				}
			}else if(manager.equals("auction-range-manager")){
				System.out.println("BID auction-range-manager");
				AuctionRangeManager arMngr = new AuctionRangeManager(req,res);
				page = arMngr.doAuctionRangeManager();
				
			}else if(manager.equals("lot-manager")){
				System.out.println("BID - Lot Manager");		
				LotManager lMngr = new LotManager(req,res);
				page = lMngr.doLotManager();		
			} else if(manager.equals("lot-range-manager")){
				System.out.println("BID lot-range-manager");
				LotRangeManager lrMngr = new LotRangeManager(req,res);
				page = lrMngr.doLotRangeManager();
				
			}else if(manager.equals("item-manager")){
				
				System.out.println("BID - Item Manager "+item_file);
				
				ItemManager iMngr = new ItemManager(req,res);
				
				page = iMngr.doItemManager();
			}else if(manager.equals("upload-auction-manager")){
				
				System.out.println("BID - Upload Auction Manager");
				
				UploadAuctionManager lMngr = new UploadAuctionManager(req,res);
				
				page = lMngr.doUploadAuctionManager();
			
			
			}else if(manager.equals("auction-user-manager")){
				
				System.out.println("BID - Auction User Manager");
				
				
				page = auMngr.doAuctionUserManager();
			}else if(manager.equals("bidding-transaction-manager")){
				
				System.out.println("BID - Bidding Transaction Manager");
				
				BiddingTransactionManager btMngr = new BiddingTransactionManager(req,res);
				
				page = btMngr.doBiddingTransactionManager();

			}
			else 
			{
				if("login".equals(action) || "reLogin".equals(action)){
				
				System.out.println("aaaaaaaaaa");	
					
				LoginManager liMngr = new LoginManager(req, res);
				
				userId = req.getParameter("userId");
				String pw = req.getParameter("pw");
				
				User u = new User();
				
				if("login".equals(action)){
					u = liMngr.getUser(userId, pw);
				}else{
					
				}
	
				if(u!=null){
					
					System.out.println("User log in successful.");
					
					
					//req.getSession().setAttribute("userLoginList3", ulList3);
					req.getSession().setAttribute("userId", u.getEmail_address());
					req.getSession().setAttribute("firstName", u.getFirst_name());
					req.getSession().setAttribute("lastName", u.getLast_name());
					req.getSession().setAttribute("fullName", u.getFirst_name()+" "+u.getLast_name());
					
					System.out.println("Details : "+u.getFirst_name()+" "+u.getLast_name() + " : "+u.getEmail_address());

	
					page ="index.jsp";
					
				}else{
					
					System.out.println("User login failed.");
					
					req.setAttribute("msgType", "Alert");
					req.setAttribute("msg", "Please input valid credentials.");
					
					page ="login.jsp";
					
				}
					
				}else if("logout".equals(action)){
					
					System.out.println("User sign out successful.");
					
					//req.setAttribute("msgType", "CallOut");
					//req.setAttribute("msg", "Sign Out.");
	
					//req.getSession().removeAttribute("emailAdd_"+emailAdd);
					//req.getSession().removeAttribute("emailAdd_"+sid);
					//req.getSession().removeAttribute("userLogin_"+sid);
					//req.getSession().removeAttribute("userLastAction_"+sid);
					req.getSession().removeAttribute("firstName");
					req.getSession().removeAttribute("lastName");
					req.getSession().removeAttribute("fullName");
					
					page ="index.jsp";
					
				}else{
					
					req.setAttribute("msgType", "Alert");
					req.setAttribute("msg", "Please Sign In.");
						
					page ="login.jsp";
					
				}
			}
			
		}else{			

	
	
		}
		
		
List<Auction> aL = null;
        
        List<Auction> activeOnlineAuctionList = new ArrayList<Auction>();
        List<Auction> activeNegotiatedAuctionList = new ArrayList<Auction>();
        List<Auction> activeLiveAuctionList = new ArrayList<Auction>();
        
        List<Auction> activeOnlineAuctionListPrivate = new ArrayList<Auction>();
        List<Auction> activeNegotiatedAuctionListPrivate = new ArrayList<Auction>();
        List<Auction> activeLiveAuctionListPrivate = new ArrayList<Auction>();
        
        
        aL = aMngr.getAuctionListByTypeAndActive(15);
        for(Auction x : aL) {
        	if(x.getVisibility()==33) {
        		activeOnlineAuctionList.add(x);
        	}else if( x.getVisibility()==34 && auMngr.isUserApprovedOnAuction(user_id, x.getAuction_id())){
        		activeOnlineAuctionList.add(x);
        	} else {
        		activeOnlineAuctionListPrivate.add(x);
        	}
        }
        	
        
        //Listings
        if(req.getSession().getAttribute("user-id")!=null )	user_id =  new BigDecimal((String) ""+req.getSession().getAttribute("user-id"));
	
    	req.getSession().setAttribute("ACTIVE-ONLINE-AUCTION-LIST", activeOnlineAuctionList);
    	req.setAttribute("ACTIVE-ONLINE-AUCTION-LIST", activeOnlineAuctionList);
        
        aL = aMngr.getAuctionListByTypeAndActive(16);
        for(Auction y : aL) {
        	if(y.getVisibility()==33) {
        		activeNegotiatedAuctionList.add(y);
        	}else if( y.getVisibility()==34 && auMngr.isUserApprovedOnAuction(user_id, y.getAuction_id())){
        		activeNegotiatedAuctionList.add(y);
        	} else {
        		activeNegotiatedAuctionListPrivate.add(y);
        	}
        }
    	req.getSession().setAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST", activeNegotiatedAuctionList);
    	req.setAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST", activeNegotiatedAuctionList);
        
    	aL = aMngr.getAuctionListByTypeAndActive(16);
        for(Auction z : aL) {
        	if(z.getVisibility()==33) {
        		activeLiveAuctionList.add(z);
        	}else if( z.getVisibility()==34 && auMngr.isUserApprovedOnAuction(user_id, z.getAuction_id())){
        		activeLiveAuctionList.add(z);
        	} else {
        		activeLiveAuctionListPrivate.add(z);
        	}
        }
    	req.getSession().setAttribute("ACTIVE-LIVE-AUCTION-LIST", activeLiveAuctionList);
    	req.setAttribute("ACTIVE-LIVE-AUCTION-LIST", activeLiveAuctionList);


		if(manager.equals("") && action.equals("")){
			page = "index.jsp";
		}
		
		System.out.println("");
		System.out.println("Controller - end");
		
		System.out.println("page : "+page);

		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, res);
		
		
	}
	
}



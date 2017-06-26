package hmr.com.servlet;

import java.io.File;
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
import hmr.com.manager.AuctionUserManager;
import hmr.com.manager.BiddingTransactionManager;
import hmr.com.manager.ItemManager;
import hmr.com.manager.LoginManager;
import hmr.com.manager.LotManager;
import hmr.com.manager.LotRangeManager;
import hmr.com.manager.UploadAuctionManager;
import hmr.com.manager.UserManager;
import bizoncloudone.com.manager.ParamsLovManager;
import bizoncloudone.com.manager.RequestManager;
import bizoncloudone.com.manager.RoleLoginManager;
import bizoncloudone.com.manager.UserLoginManager;
import bizoncloudone.com.util.DBConnection;
import hmr.com.bean.Auction;
import hmr.com.bean.AuctionRange;
import hmr.com.bean.Item;
import hmr.com.bean.Lot;
import hmr.com.bean.User;
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
		
		String page = null;

		
		File auction_file = null;
		File auction_file_small = null;
		File item_file = null;
		//String file_name = "";
		Integer user_id = 0;
		BigDecimal auctionId_wip = new BigDecimal(0);
		BigDecimal itemId_wip = new BigDecimal(0);
		
        if(ServletFileUpload.isMultipartContent(req)){
            try {
            	
            	//final Part filePart = req.getPart("myFileSmall");
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                    	
                    	System.out.println("item.getFieldName() "+item.getFieldName());
                    	
                    	if("myFileSmall".equals(item.getFieldName()) &&  item.getSize() > 0){
                    		
                        	out = new FileOutputStream(new File("c:/hmr/tmp/"
                                    + item.getName()));
                    	    InputStream filecontent = null;
                    	    //final PrintWriter writer = res.getWriter();
                        	
                    	    filecontent  = item.getInputStream();

                    	    int read = 0;
                            final byte[] bytes = new byte[1024];

                            while ((read = filecontent.read(bytes)) != -1) {
                                out.write(bytes, 0, read);
                            }
                            
                        	System.out.println("item.getName() "+item.getName());
                        	//System.out.println("New file " + fileName + " created at " + path);
                        	
                        	//String aaa = item.getString("StoreLocation");
                        	//System.out.println("StoreLocation "+aaa);
                        	
                        	auction_file_small = new File("c:/hmr/tmp/"+item.getName());

                        	System.out.println("file.getCanonicalPath "+auction_file_small.getCanonicalPath());
                        	System.out.println("file.getAbsolutePath "+auction_file_small.getAbsolutePath());
                        	System.out.println("file.getPath "+auction_file_small.getPath());
                    		
                    	}else if("myFile".equals(item.getFieldName())  &&  item.getSize() > 0 ){
                    		
                        	out = new FileOutputStream(new File("c:/hmr/tmp/" + item.getName()));
                    	    InputStream filecontent = null;
                    	    //final PrintWriter writer = res.getWriter();
                        	
                    	    filecontent  = item.getInputStream();

                    	    int read = 0;
                            final byte[] bytes = new byte[1024];

                            while ((read = filecontent.read(bytes)) != -1) {
                                out.write(bytes, 0, read);
                            }
                            
                        	System.out.println("item.getName() "+item.getName());
                        	//System.out.println("New file " + fileName + " created at " + path);
                        	
                        	//String aaa = item.getString("StoreLocation");
                        	//System.out.println("StoreLocation "+aaa);
                        	
                        	auction_file = new File("c:/hmr/tmp/"+item.getName());
                        	
                        	//System.out.println("file.getCanonicalPath "+auction_file.getCanonicalPath());
                        	//System.out.println("file.getAbsolutePath "+auction_file.getAbsolutePath());
                        	//System.out.println("file.getPath "+auction_file.getPath());
                    		
                    	}else if("myFileItem".equals(item.getFieldName())  &&  item.getSize() > 0 ){

                        	out = new FileOutputStream(new File("c:/hmr/tmp/"
                                    + item.getName()));
                    	    InputStream filecontent = null;
                    	    //final PrintWriter writer = res.getWriter();
                        	
                    	    filecontent  = item.getInputStream();

                    	    int read = 0;
                            final byte[] bytes = new byte[1024];

                            while ((read = filecontent.read(bytes)) != -1) {
                                out.write(bytes, 0, read);
                            }
                            
                        	System.out.println("item.getName() "+item.getName());
                        	//System.out.println("New file " + fileName + " created at " + path);
                        	
                        	//String aaa = item.getString("StoreLocation");
                        	//System.out.println("StoreLocation "+aaa);
                        	
                        	item_file = new File("c:/hmr/tmp/"+item.getName());
                        	
                        	//System.out.println("file.getCanonicalPath "+auction_file.getCanonicalPath());
                        	//System.out.println("file.getAbsolutePath "+auction_file.getAbsolutePath());
                        	//System.out.println("file.getPath "+auction_file.getPath());
                    		
                    	}

                        
                      
                    }else{
                    	
                    	String fieldname = item.getFieldName();
                        String fieldvalue = item.getString();
                        if (fieldname.equals("action")) {
                        	action=fieldvalue;
                        	req.getSession().setAttribute("action", action);
                        }
                        if (fieldname.equals("manager")) {
                        	manager=fieldvalue;
                        	req.getSession().setAttribute("manager", manager);
                        }
                        if (fieldname.equals("userId")) {
                        	userId=fieldvalue;
                        	req.getSession().setAttribute("userId", userId);
                        }
                        if (fieldname.equals("user-id")) {
                        	user_id=Integer.valueOf(fieldvalue) ;
                        	req.getSession().setAttribute("user-id", user_id);
                        }
                        if (fieldname.equals("auctionId_wip")) {
                        	if(fieldvalue!=null && !"".equals(fieldvalue)){
                        		auctionId_wip=new BigDecimal(fieldvalue) ;
                        	}else{
                        		auctionId_wip = new BigDecimal(0);
                        	}
                        	                        	
                        	req.getSession().setAttribute("auctionId-wip", auctionId_wip);
                        	req.setAttribute("auctionId-wip", auctionId_wip);
                        	//System.out.println("auctionId_wip Session : "+Integer.valueOf(req.getSession().getAttribute("auctionId-wip").toString()) );
                        	
                        }
                        if (fieldname.equals("itemId_wip")) {
                        	if(fieldvalue!=null && !"".equals(fieldvalue)){
                        		itemId_wip=new BigDecimal(fieldvalue) ;
                        	}else{
                        		itemId_wip = new BigDecimal(0);
                        	}
                        	
                        	
                        	req.getSession().setAttribute("itemId-wip", itemId_wip);
                        	
                        	//System.out.println("itemId_wip Session : "+Integer.valueOf(req.getSession().getAttribute("itemId-wip").toString()) );
                        	
                        }
                        if (fieldname.equals("auction_id")) {
                        	BigDecimal auction_id = new BigDecimal(0);
                        	if(fieldvalue!=null && !"".equals(fieldvalue)){
                        		auction_id=new BigDecimal(fieldvalue) ;
                        	}else{
                        		auction_id = new BigDecimal(0);
                        	}

                        	req.getSession().setAttribute("auction_id", auction_id);
                        	req.setAttribute("auction_id", auction_id);
                        	//System.out.println("itemId_wip Session : "+Integer.valueOf(req.getSession().getAttribute("itemId-wip").toString()) );
                        	
                        }
                        
                    }
              
                }
           
               
            } catch (Exception ex) {
            	ex.printStackTrace();
            	req.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }//file uploads
		
		
		System.out.println("Paramerters - start");
		System.out.println("manager : "+manager);
		System.out.println("action : "+action);
		System.out.println("userId : "+userId);
		System.out.println("vek : "+vek);
		System.out.println("Paramerters - end");
		System.out.println("");

		
        AuctionManager aucMngr = new AuctionManager(req,res);
        List<Auction> activeOnlineAuctionList = null;
        if(req.getSession().getAttribute("ACTIVE-ONLINE-AUCTION-LIST")==null){
        	activeOnlineAuctionList = aucMngr.getAuctionListByTypeAndActive(15);
        	req.getSession().setAttribute("ACTIVE-ONLINE-AUCTION-LIST", activeOnlineAuctionList);
        	req.setAttribute("ACTIVE-ONLINE-AUCTION-LIST", activeOnlineAuctionList);
        }else{
        	activeOnlineAuctionList = aucMngr.getAuctionListByTypeAndActive(15);
        	req.setAttribute("ACTIVE-ONLINE-AUCTION-LIST", activeOnlineAuctionList);
        }
        
        
        List<Auction> activeNegotiatedAuctionList = null;
        if(req.getSession().getAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST")==null){
        	activeNegotiatedAuctionList = aucMngr.getAuctionListByTypeAndActive(16);
        	req.getSession().setAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST", activeNegotiatedAuctionList);
        	req.setAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST", activeNegotiatedAuctionList);
        }else{
        	activeNegotiatedAuctionList = aucMngr.getAuctionListByTypeAndActive(16);
        	req.setAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST", activeNegotiatedAuctionList);
        }
        
        
        

		
		//all page get requests
		if(manager.equals("get")){
			
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
	
				AuctionManager aMngr = new AuctionManager(req,res);
				AuctionRangeManager arMngr = new AuctionRangeManager(req,res);
				LotManager lMngr = new LotManager(req,res);
				ItemManager iMngr = new ItemManager(req,res);
				
				
				Auction a = aMngr.getAuctionById(new BigDecimal(aid));
				
				//AuctionRange ar = new AuctionRange();
				
				
				List<AuctionRange> arList = arMngr.getAuctionRangeListByAuctionId(a.getAuction_id());
				
				List<Lot> lotList = lMngr.getLotListByAuctionId(a.getAuction_id());
				
				HashMap<BigDecimal, Lot> lotHM  = lMngr.getLotHMByAuctionId(a.getAuction_id());
				
				//List<Item> iList = iMngr.getItemListByAuctionId(a.getAuction_id());
				
				iMngr.setLovValuesCurrency(req, res);
				
				List<Lot> lList = new ArrayList<Lot>();
				
				for(Lot lot : lotList){
					
					BigDecimal amount_bid_next = new BigDecimal(0);
					
					boolean isStop = false;
					
					for(AuctionRange ar : arList){
						
						System.out.println("LOT DESC : "+lot.getLot_desc());
						System.out.println("NEXT BID :  "+ar.getRange_start().doubleValue()+" - "+ar.getRange_end().doubleValue());
						System.out.println("AMOUNT : "+ar.getIncrement_amount()+" - "+isStop);
						
						BigDecimal lotAmountBid = new BigDecimal("0");
						if(lot.getAmount_bid()!=null){
							lotAmountBid = lot.getAmount_bid();
						}else{
							lot.setAmount_bid(new BigDecimal("0"));
						}
						
						if(lot.getAmount_bid().doubleValue() >= ar.getRange_start().doubleValue() && 
								lot.getAmount_bid().doubleValue() <= ar.getRange_end().doubleValue() &&	
								!isStop){

							amount_bid_next = ar.getIncrement_amount().add(lotAmountBid);
							isStop = true;

						}else if(lot.getAmount_bid().doubleValue()==0 && !isStop){
							amount_bid_next = ar.getIncrement_amount().add(lotAmountBid);
							isStop = true;
						}
						
					}
					
					lot.setAmount_bid_next(amount_bid_next);
					lList.add(lot);
				}

				req.setAttribute("auction", a);
				req.setAttribute("lotHM", lotHM);
				req.setAttribute("lList", lList);
				req.setAttribute("arList", arList);
				
				req.setAttribute("auction-item", a);
				page ="auction-bid-details.jsp";
			}else if("auctionBidDetailsDoBid".equals(action)){
				BiddingTransactionManager bMngr = new BiddingTransactionManager();
				bMngr.doBiddingTransactionManager();
			}
			
		}//page get requests

		//all non-page get requests
		if(userId!=null && !"".equals(userId) && !manager.equals("get")){
			
			if(manager.equals("bid-manager")) {
				String doAction = req.getParameter("doaction")!=null ? (String)req.getParameter("doaction") : "";
				String reqlotId = req.getParameter("lotId")!=null ? (String)req.getParameter("lotId") : "";
				String reqamount = req.getParameter("amount")!=null ? (String)req.getParameter("amount") : "";
				if(reqlotId!="" && reqamount!="") {
					UserDao ud = new UserDao();
					User u = ud.getUser(userId);
					
					BiddingTransactionManager btMngr = new BiddingTransactionManager();
					Integer lotId = Integer.valueOf(reqlotId);
					BigDecimal amount = new BigDecimal(reqamount);
			
					//BID and BUY button clicks
					if(doAction.equals("BID")) {
						btMngr.insertBiddingTransactionMakeBid(lotId, amount, u.getId());
					}else if(doAction.equals("BUY")) {
						btMngr.insertBiddingTransactionMakeBuy(lotId, amount, u.getId());
					}
					req.getSession(true).setAttribute("msgInfo", "Bid Submitted");	
					req.getSession(true).setAttribute("msgbgcol", "#65f442");
					page = "index.jsp";
					
					
				}
			} else if(manager.equals("login-manager")){
				LoginManager liMngr = new LoginManager(req,res);
				page = liMngr.doLoginManager();
				
				
			}else if(manager.equals("user-manager")){
				UserManager uMngr = new UserManager(req,res);
				page = uMngr.doUserManager();
				
			}else if(manager.equals("auction-manager")){		
				AuctionManager aMngr = new AuctionManager(req,res);
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
				
				if("saveItemImage".equals(action) && item_file!=null){
					
					System.out.println("BID saveItemImage "+item_file);

					page = iMngr.doItemManager(item_file, action, itemId_wip);
				}else{
					page = iMngr.doItemManager();
				}
				
				
				
			}else if(manager.equals("upload-auction-manager")){
				
				System.out.println("BID - Upload Auction Manager");
				
				UploadAuctionManager lMngr = new UploadAuctionManager(req,res);
				
				page = lMngr.doUploadAuctionManager();
			
			
			}else if(manager.equals("auction-user-manager")){
				
				System.out.println("BID - Auction User Manager");
				
				AuctionUserManager auMngr = new AuctionUserManager(req,res);
				
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



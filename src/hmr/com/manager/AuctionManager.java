package hmr.com.manager;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.bean.Auction;
import hmr.com.bean.AuctionUser;
import hmr.com.bean.Item;
import hmr.com.bean.Lot;
import hmr.com.bean.Lov;
import hmr.com.bean.User;
import hmr.com.dao.AuctionDao;
import hmr.com.dao.UserDao;
import hmr.com.util.Hash;


public class AuctionManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public AuctionManager(){}
	/*
	public AuctionManager(HttpServletRequest req, HttpServletResponse res, String emailAdd){
		this.req = req;
		this.res = res;
	}
	*/
	public AuctionManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doAuctionManager(File file_small, File file, String action, BigDecimal auctionId_wip){
		String page = "index.jsp";
		
		if(action.equals("saveAuctionImage")){
			
			Auction a = new Auction();
			
			a = updateAuctionImage(file_small, file,1,auctionId_wip);

			if(a!=null){
				a = getAuctionById(a.getId());
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Auction images updated succeessful.");
			}else{
				a = getAuctionById(auctionId_wip);
			}
			
			
			page = "auction.jsp";
		}
		
		return page;
	}
	
	public String doAuctionManager(){
		String page = null;
		
		
		String action = req.getParameter("action")!=null ? req.getParameter("action") : (String)req.getSession().getAttribute("action");
		Integer user_id = 0;
		BigDecimal auctionId_wip = req.getParameter("auctionId_wip")!=null && !"".equals(req.getParameter("auctionId_wip"))  ? new BigDecimal(req.getParameter("auctionId_wip")): new BigDecimal(0);
		//auctionId_wip = !req.getParameter("auctionId_wip").equals("") ? new BigDecimal(req.getParameter("auctionId_wip")) : new BigDecimal(0);
		
		
		AuctionUserManager auMngr = new AuctionUserManager(req, res);
		
		if("auctionList".equals(action)){
			
			System.out.println("auctionList");
			List<Auction> aList = getAuctionList();
			req.setAttribute("auctionList", aList);
			page ="auction-list.jsp";
		
		}else if("createAuction".equals(action)){
			
			System.out.println("createAuction");
			UserDao ud = new UserDao();
			List<User> userCoordinatorList = ud.getUserListByRole(5);
			req.setAttribute("userCoordinatorList", userCoordinatorList);
			page ="auction-create.jsp";
		
		}else if("saveAuction".equals(action)){
			
			System.out.println("saveAuction");

			//String userId = req.getParameter("userId")!=null ? req.getParameter("userId") : "";
			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));

			String auction_name = req.getParameter("auction_name")!=null ? req.getParameter("auction_name") : "";
			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			BigDecimal auction_no = !req.getParameter("auction_no").equals("") ? new BigDecimal(req.getParameter("auction_no")) : new BigDecimal(0);
			String location = req.getParameter("location")!=null ? req.getParameter("location") : "";
			String start_date_time = req.getParameter("start_date_time")!=null ? req.getParameter("start_date_time") : "";
			String end_date_time = req.getParameter("end_date_time")!=null ? req.getParameter("end_date_time") : "";
			String auction_desc = req.getParameter("auction_desc")!=null ? req.getParameter("auction_desc") : "";
			Integer coordinator = req.getParameter("coordinator")!=null ? Integer.valueOf(req.getParameter("coordinator")) : 0;
			Integer auction_type = req.getParameter("auctionStatus")!=null ? Integer.valueOf(req.getParameter("auction_type")) : 0;
			Integer auctionStatus = req.getParameter("auctionStatus")!=null ? Integer.valueOf(req.getParameter("auctionStatus")) : 0;
			Integer active = !req.getParameter("active").equals("") ? Integer.valueOf(req.getParameter("active")) : 0;
			Integer visibility = !req.getParameter("visibility").equals("") ? Integer.valueOf(req.getParameter("visibility")) : 0;
			Integer auction_item_increment_time = !req.getParameter("auction_item_increment_time").equals("") ? Integer.valueOf(req.getParameter("auction_item_increment_time")) : 0;
			Integer bid_deposit = !req.getParameter("bid_deposit").equals("") ? Integer.valueOf(req.getParameter("bid_deposit")) : 0;
			BigDecimal bid_deposit_amount = !req.getParameter("bid_deposit_amount").equals("") ? new BigDecimal(req.getParameter("bid_deposit_amount")) : new BigDecimal(0);
			Integer no_of_lots = !req.getParameter("no_of_lots").equals("") ? Integer.valueOf(req.getParameter("no_of_lots")) : 0;
			Integer no_of_items = !req.getParameter("no_of_items").equals("") ? Integer.valueOf(req.getParameter("no_of_items")) : 0;
			String date_sync = req.getParameter("date_sync")!=null ? req.getParameter("date_sync") : "";
			String terms_and_conditions = req.getParameter("terms_and_conditions")!=null ? req.getParameter("terms_and_conditions").replaceAll("\\r|\\n", "").replaceAll("\"", "'") : "";
			Integer category_level_1 = !req.getParameter("category_level_1").equals("") ? Integer.valueOf(req.getParameter("category_level_1")) : 0;
			Integer one_lot_per_bidder = !req.getParameter("one_lot_per_bidder").equals("") ? Integer.valueOf(req.getParameter("one_lot_per_bidder")) : 0;
			Integer one_start_bid = req.getParameter("one_start_bid")!=null && !req.getParameter("one_start_bid").equals("") ? Integer.valueOf(req.getParameter("one_start_bid")) : 0;
			Integer bid_qualifier_price = req.getParameter("bid_qualifier_price")!=null && !req.getParameter("bid_qualifier_price").equals("") ? Integer.valueOf(req.getParameter("bid_qualifier_price")) : 0;
			
			Integer auto_send_post_notification = !req.getParameter("auto_send_post_notification").equals("") ? Integer.valueOf(req.getParameter("auto_send_post_notification")) : 0;
			
			
			Date date_sync_d = null; 
			if(!"".equals(date_sync))
			{
			    try {
			    	date_sync_d = INPUT_DATE_FMT.parse(date_sync);
			    } catch (ParseException e) {}
			}
			

			Date start_date_time_d = null; 
			if(!"".equals(start_date_time))
			{
			    try {
			    	start_date_time_d = INPUT_DATE_FMT.parse(start_date_time);
			    } catch (ParseException e) {}
			}
			
			Date end_date_time_d = null; 
			if(!"".equals(end_date_time))
			{
			    try {
			    	end_date_time_d = INPUT_DATE_FMT.parse(end_date_time);
			    } catch (ParseException e) {}
			}
			

			Auction a = insertAuctionOnCreate(
						auction_name,
						auction_id,
						auction_no,
						location,
						start_date_time_d,
						end_date_time_d,
						auction_desc,
						coordinator,
						auction_type,
						auctionStatus,
						active,
						visibility,
						auction_item_increment_time,
						bid_deposit,
						bid_deposit_amount,
						no_of_lots,
						no_of_items,
						date_sync_d,
						terms_and_conditions,
						category_level_1,
						one_lot_per_bidder,
						one_start_bid,
						bid_qualifier_price,
						auto_send_post_notification,
						user_id
					);
			
			if(a!=null && a.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Auction created succeessful.");

				a = getAuctionById(a.getId());
				
				AuctionRangeManager arMngr = new AuctionRangeManager();
				
				arMngr.insertAuctionRangeOnCreate(auction_id, new BigDecimal(1), new BigDecimal(100), new BigDecimal(50), user_id);
				arMngr.insertAuctionRangeOnCreate(auction_id, new BigDecimal(101), new BigDecimal(1000), new BigDecimal(100), user_id);
				arMngr.insertAuctionRangeOnCreate(auction_id, new BigDecimal(1001), new BigDecimal(10000), new BigDecimal(500), user_id);
				arMngr.insertAuctionRangeOnCreate(auction_id, new BigDecimal(10001), new BigDecimal(10000000), new BigDecimal(5000), user_id);

				UserManager uMngr = new UserManager(req, res);
				
				HashMap<Integer,User> coordinatorUserHM = null;
				List<User> coordinatorUserList = null;

				if(req.getSession().getAttribute("COORDINATOR-USER-HM")==null ){
					try {
						coordinatorUserHM = uMngr.getUserHMByRole(5);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					req.getSession().setAttribute("COORDINATOR-USER-HM", coordinatorUserHM);
					req.setAttribute("COORDINATOR-USER-HM", coordinatorUserHM);
				}else{
					coordinatorUserHM = (HashMap<Integer,User>)req.getSession().getAttribute("COORDINATOR-USER-HM");
				}
				
				if(req.getSession().getAttribute("COORDINATOR-USER-LIST")==null ){
					try {
						coordinatorUserList = uMngr.getUserListByRole(5);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					req.getSession().setAttribute("COORDINATOR-USER-LIST", coordinatorUserList);
					req.setAttribute("COORDINATOR-USER-LIST", coordinatorUserList);
				}else{
					coordinatorUserList = (List<User>)req.getSession().getAttribute("COORDINATOR-USER-LIST");
				}

				setLovValuesCategoryLevel(req,res);
				setLovValuesCurrency(req, res);
				
				req.getSession().setAttribute("auction", a);
				req.setAttribute("auction", a);				
				page ="auction.jsp";

			}else if("auctionBidDetails".equals(action)){
				page ="auction-bid-details.jsp";
			}else{
				/*
				a = new Auction();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Auction created failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("auction", a);
				
				req.setAttribute("auction", a);
				
				page ="auction-list.jsp";
				*/
			}

			

		
		}else if("viewAuction".equals(action) || 
				"viewAuctionPrivateInvites".equals(action) ||
				"USER-APPROVE".equals(action) ||
				"USER-REJECT".equals(action) ||
				"USER-APPROVE-TO-PENDING".equals(action) ||
				"USER-REJECTED-TO-PENDING".equals(action)
				){
			
				System.out.println("viewAuction");

				Integer auctionUserId_wip = (req.getParameter("auctionUserId_wip")!=null && !"".equals(req.getParameter("auctionUserId_wip"))) ? Integer.valueOf(req.getParameter("auctionUserId_wip")): 0;	

				//Auction a = getAuctionById(auctionId_wip);
				Auction a = getAuctionByAuctionId(auctionId_wip);
				
				
				
				req.getSession().setAttribute("auction", a);
				req.setAttribute("auction", a);

				
				List<Lot> lList = new LotManager().getLotListByAuctionId(auctionId_wip);
				
				req.setAttribute("lotList", lList);
				
				
				ItemManager iMngr = new ItemManager();
				
				List<Item> iList = iMngr.getItemListByAuctionId(auctionId_wip);
				
				req.setAttribute("itemList", iList);
				
				
				
				UserDao ud = new UserDao();
				
				List<User> userBidderList = ud.getUserListByRole(2);
			
				req.setAttribute("userBidderList", userBidderList);
				
				
				AuctionDao ad = new AuctionDao();
				
				List<Auction> auctionList = ad.getAuctionList();
				
				req.setAttribute("auctionList", auctionList);

				
				UserManager uMngr = new UserManager(req, res);
				
				HashMap<Integer,User> bidderUserHM = null;
				List<User> bidderUserList = null;
				
				if(req.getSession().getAttribute("BIDDER-USER-HM")==null ){
					try {
						bidderUserHM = uMngr.getUserHMByRole(2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					req.getSession().setAttribute("BIDDER-USER-HM", bidderUserHM);
					req.setAttribute("BIDDER-USER-HM", bidderUserHM);
				}else{
					bidderUserHM = (HashMap<Integer,User>)req.getSession().getAttribute("BIDDER-USER-HM");
					req.setAttribute("BIDDER-USER-HM", bidderUserHM);
				}
				
				
				if(req.getSession().getAttribute("BIDDER-USER-LIST")==null ){
					try {
						bidderUserList = uMngr.getUserListByRole(2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					req.getSession().setAttribute("BIDDER-USER-LIST", bidderUserList);
					req.setAttribute("BIDDER-USER-LIST", bidderUserList);
				}else{
					bidderUserList = (List<User>)req.getSession().getAttribute("BIDDER-USER-LIST");
					req.setAttribute("BIDDER-USER-LIST", bidderUserList);
				}
				
				
				
				
				
				setLovAuctionUserValues(req, res);
				setLovValuesCategoryLevel(req,res);
				setLovValuesCurrency(req, res);
				List<AuctionUser> auctionUserList = auMngr.getAuctionUserListByAuctionId(auctionId_wip);
				req.setAttribute("auctionUserList", auctionUserList);
				
				
				if("viewAuctionPrivateInvites".equals(action)) {
					auctionUserList = auMngr.getAuctionUserListByAuctionId(auctionId_wip);
					req.setAttribute("auctionUserList", auctionUserList);
					page ="auction-private.jsp";
				
				}else if("USER-APPROVE".equals(action)){
					AuctionUser au = auMngr.getAuctionUserById(auctionUserId_wip);
					au = auMngr.updateAuctionUserOnUpdate(auctionId_wip, au.getUser_id(), 25, au.getActive(), user_id, au.getId(), au.getCompany_id_no());
					req.setAttribute("msgbgcol", "green");
					req.setAttribute("msgInfo", "Auction user has been approved.");
					auctionUserList = auMngr.getAuctionUserListByAuctionId(auctionId_wip);
					req.setAttribute("auctionUserList", auctionUserList);
					page ="auction-private.jsp";

				}else if("USER-REJECT".equals(action)){
					AuctionUser au = auMngr.getAuctionUserById(auctionUserId_wip);
					au = auMngr.updateAuctionUserOnUpdate(auctionId_wip, au.getUser_id(), 28, au.getActive(), user_id, au.getId(), au.getCompany_id_no());
					req.setAttribute("msgbgcol", "red");
					req.setAttribute("msgInfo", "Auction user has been rejected.");
					auctionUserList = auMngr.getAuctionUserListByAuctionId(auctionId_wip);
					req.setAttribute("auctionUserList", auctionUserList);
					page ="auction-private.jsp";
					
				}else if("USER-APPROVE-TO-PENDING".equals(action)){
					AuctionUser au = auMngr.getAuctionUserById(auctionUserId_wip);
					au = auMngr.updateAuctionUserOnUpdate(auctionId_wip, au.getUser_id(), 26, au.getActive(), user_id, au.getId(), au.getCompany_id_no());
					req.setAttribute("msgbgcol", "yellow");
					req.setAttribute("msgInfo", "Approved auction user has been set to pending status.");
					auctionUserList = auMngr.getAuctionUserListByAuctionId(auctionId_wip);
					req.setAttribute("auctionUserList", auctionUserList);
					page ="auction-private.jsp";
					
				}else if("USER-REJECTED-TO-PENDING".equals(action)){
					AuctionUser au = auMngr.getAuctionUserById(auctionUserId_wip);
					au = auMngr.updateAuctionUserOnUpdate(auctionId_wip, au.getUser_id(), 26, au.getActive(), user_id, au.getId(), au.getCompany_id_no());
					req.setAttribute("msgbgcol", "yellow");
					req.setAttribute("msgInfo", "Approved auction user has been set to pending status.");
					auctionUserList = auMngr.getAuctionUserListByAuctionId(auctionId_wip);
					req.setAttribute("auctionUserList", auctionUserList);
					page ="auction-private.jsp";
					
				} else {
					page ="auction.jsp";
				}
				


		}else if("updateAuction".equals(action)){
			
			System.out.println("updateAuction");

			auctionId_wip = req.getParameter("auctionId_wip")!=null && !req.getParameter("auctionId_wip").equals("") ? new BigDecimal(req.getParameter("auctionId_wip")): new BigDecimal(req.getSession().getAttribute("auctionId-wip").toString());
			
			System.out.println("updateAuction : auctionId_wip "+auctionId_wip);
			if(auctionId_wip == new BigDecimal(0)){
				auctionId_wip = new BigDecimal(req.getSession().getAttribute("auctionId-wip").toString());
			}
			if(auctionId_wip.floatValue() > 0){
				
				//Auction a = getAuctionById(auctionId_wip);
				Auction a = getAuctionByAuctionId(auctionId_wip);

				req.getSession().setAttribute("auction", a);
				
				req.setAttribute("auction", a);

				UserDao ud = new UserDao();
				
				List<User> userCoordinatorList = ud.getUserListByRole(5);

				req.setAttribute("userCoordinatorList", userCoordinatorList);
				
				page ="auction-update.jsp";
				
			}

			
		}else if("saveUpdateAuction".equals(action)){
			
			System.out.println("saveUpdateAuction");

			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));
			auctionId_wip = !req.getParameter("auctionId_wip").equals("") ? new BigDecimal(req.getParameter("auctionId_wip")) : new BigDecimal(0);

			String auction_name = req.getParameter("auction_name")!=null ? req.getParameter("auction_name") : "";
			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			BigDecimal auction_no = !req.getParameter("auction_no").equals("") ? new BigDecimal(req.getParameter("auction_no")) : new BigDecimal(0);
			String location = req.getParameter("location")!=null ? req.getParameter("location") : "";
			String start_date_time = req.getParameter("start_date_time")!=null ? req.getParameter("start_date_time") : "";
			String end_date_time = req.getParameter("end_date_time")!=null ? req.getParameter("end_date_time") : "";
			String auction_desc = req.getParameter("auction_desc")!=null ? req.getParameter("auction_desc") : "";
			Integer coordinator = req.getParameter("coordinator")!=null ? Integer.valueOf(req.getParameter("coordinator")) : 0;
			Integer auction_type = req.getParameter("auctionStatus")!=null ? Integer.valueOf(req.getParameter("auction_type")) : 0;
			Integer auctionStatus = req.getParameter("auctionStatus")!=null ? Integer.valueOf(req.getParameter("auctionStatus")) : 0;
			Integer active = !req.getParameter("active").equals("") ? Integer.valueOf(req.getParameter("active")) : 0;
			Integer visibility = !req.getParameter("visibility").equals("") ? Integer.valueOf(req.getParameter("visibility")) : 0;
			Integer auction_item_increment_time = !req.getParameter("auction_item_increment_time").equals("") ? Integer.valueOf(req.getParameter("auction_item_increment_time")) : 0;
			Integer bid_deposit = !req.getParameter("bid_deposit").equals("") ? Integer.valueOf(req.getParameter("bid_deposit")) : 0;
			BigDecimal bid_deposit_amount = !req.getParameter("bid_deposit_amount").equals("") ? new BigDecimal(req.getParameter("bid_deposit_amount")) : new BigDecimal(0);
			Integer no_of_lots = !req.getParameter("no_of_lots").equals("") ? Integer.valueOf(req.getParameter("no_of_lots")) : 0;
			Integer no_of_items = !req.getParameter("no_of_items").equals("") ? Integer.valueOf(req.getParameter("no_of_items")) : 0;
			String date_sync = req.getParameter("date_sync")!=null ? req.getParameter("date_sync") : "";
			String terms_and_conditions = req.getParameter("terms_and_conditions")!=null ? req.getParameter("terms_and_conditions").replaceAll("\\r|\\n", "").replaceAll("\"", "'")  : "";
			Integer category_level_1 = !req.getParameter("category_level_1").equals("") ? Integer.valueOf(req.getParameter("category_level_1")) : 0;
			Integer one_lot_per_bidder = req.getParameter("one_lot_per_bidder")!=null && !req.getParameter("one_lot_per_bidder").equals("") ? Integer.valueOf(req.getParameter("one_lot_per_bidder")) : 0;
			
			Integer one_start_bid = req.getParameter("one_start_bid")!=null && !req.getParameter("one_start_bid").equals("") ? Integer.valueOf(req.getParameter("one_start_bid")) : 0;
			Integer bid_qualifier_price = req.getParameter("bid_qualifier_price")!=null && !req.getParameter("bid_qualifier_price").equals("") ? Integer.valueOf(req.getParameter("bid_qualifier_price")) : 0;
			
			Integer auto_send_post_notification = !req.getParameter("auto_send_post_notification").equals("") ? Integer.valueOf(req.getParameter("auto_send_post_notification")) : 0;
			
			Date date_sync_d = null; 
			if(!"".equals(date_sync))
			{
			    try {
			    	date_sync_d = INPUT_DATE_FMT.parse(date_sync);
			    } catch (ParseException e) {}
			}
			

			Date start_date_time_d = null; 
			if(!"".equals(start_date_time))
			{
			    try {
			    	start_date_time_d = INPUT_DATE_FMT.parse(start_date_time);
			    } catch (ParseException e) {}
			}
			
			Date end_date_time_d = null; 
			if(!"".equals(end_date_time))
			{
			    try {
			    	end_date_time_d = INPUT_DATE_FMT.parse(end_date_time);
			    } catch (ParseException e) {}
			}
			
	        
	        System.out.println("start_date_time_d SAVING :"+start_date_time_d);
	        System.out.println("end_date_time_d SAVING :"+end_date_time_d);

			
			Auction a = updateAuctionOnUpdate(
						auction_name,
						auction_id,
						auction_no,
						location,
						start_date_time_d,
						end_date_time_d,
						auction_desc,
						coordinator,
						auction_type,
						auctionStatus,
						active,
						visibility,
						auction_item_increment_time,
						bid_deposit,
						bid_deposit_amount,
						no_of_lots,
						no_of_items,
						date_sync_d,
						terms_and_conditions,
						category_level_1,
						one_lot_per_bidder,
						one_start_bid,
						bid_qualifier_price,
						auto_send_post_notification,
						user_id,
						auctionId_wip
					);
			

			
			
			if(a!=null && a.getId()!=null){
				
				//a = getAuctionById(auctionId_wip);
				a = getAuctionByAuctionId(auctionId_wip);
				
				setLovValuesCategoryLevel(req,res);
				setLovValuesCurrency(req, res);
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Auction updated succeessful.");
				
				req.getSession().setAttribute("auction", a);
				
				req.setAttribute("auction", a);
				
				LotManager lMngr = new LotManager();
				
				List<Lot> lList = lMngr.getLotListByAuctionId(auctionId_wip);
				
				req.setAttribute("lotList", lList);
				
				
				ItemManager iMngr = new ItemManager();
				
				List<Item> iList = iMngr.getItemListByAuctionId(auctionId_wip);
				
				req.setAttribute("itemList", iList);
				
				
				
				UserDao ud = new UserDao();
				
				List<User> userBidderList = ud.getUserListByRole(2);
			
				req.setAttribute("userBidderList", userBidderList);
				
				
				AuctionDao ad = new AuctionDao();
				
				List<Auction> auctionList = ad.getAuctionList();
				
				req.setAttribute("auctionList", auctionList);

				
				UserManager uMngr = new UserManager(req, res);
				
				HashMap<Integer,User> bidderUserHM = null;
				List<User> bidderUserList = null;
				
				if(req.getSession().getAttribute("BIDDER-USER-HM")==null ){
					try {
						bidderUserHM = uMngr.getUserHMByRole(2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					req.getSession().setAttribute("BIDDER-USER-HM", bidderUserHM);
					req.setAttribute("BIDDER-USER-HM", bidderUserHM);
				}else{
					bidderUserHM = (HashMap<Integer,User>)req.getSession().getAttribute("BIDDER-USER-HM");
					req.setAttribute("BIDDER-USER-HM", bidderUserHM);
				}
				
				
				if(req.getSession().getAttribute("BIDDER-USER-LIST")==null ){
					try {
						bidderUserList = uMngr.getUserListByRole(2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					req.getSession().setAttribute("BIDDER-USER-LIST", bidderUserList);
					req.setAttribute("BIDDER-USER-LIST", bidderUserList);
				}else{
					bidderUserList = (List<User>)req.getSession().getAttribute("BIDDER-USER-LIST");
					req.setAttribute("BIDDER-USER-LIST", bidderUserList);
				}
				
				
				
				//logic of all auction triggered
				//One as Start Bidding
				
				if(a.getOne_start_bid()==1){
					lMngr.updateLotSetStartingBidAmount(auction_id, new BigDecimal(1), user_id);
				}else{
					lMngr.updateLotSetStartingBidAmount(auction_id, new BigDecimal(0), user_id);
				}
			    
				if(a.getAuction_type()==16){
					lMngr.updateLotSetIsBuy(auction_id, 1, user_id);
				}else{
					lMngr.updateLotSetIsBuy(auction_id, 0, user_id);
				}
				
				if(a.getBid_qualifier_price().doubleValue() > 0){
					

						RunnableItemManager rim = new RunnableItemManager("lotTotalsCompute", auction_id);
						rim.start();
					
					
					/*
					if(a.getBid_qualifier_price() == 1){
	            		//bid_qualifier_price = "Reserve Price";
	            	}else if(a.getBid_qualifier_price() == 2){
	            		//bid_qualifier_price = "SRP";
	            	}else if(a.getBid_qualifier_price() == 3){
	            		//bid_qualifier_price = "Target Price";
	            	}else if(a.getBid_qualifier_price() == 4){
	            		//bid_qualifier_price = "Assess Value Price";
	            	}
					*/
				}
				

				List<AuctionUser> auctionUserList = auMngr.getAuctionUserListByAuctionId(auctionId_wip);
				req.setAttribute("auctionUserList", auctionUserList);
				setLovAuctionUserValues(req,res);
				page ="auction.jsp";
				
			}else{
				
				a = new Auction();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Auction update failed.<br>Please contact your administrator.");
				req.getSession().setAttribute("auction", a);
				req.setAttribute("auction", a);
				page ="auction-list.jsp";
				
			}
			
			
		}else if("saveAuctionImage".equals(action)){
			
			System.out.println("saveAuctionImage");
			
			
			/*
			try {
				//processRequestUpload(req,res);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 */
			
			setLovValuesCategoryLevel(req,res);
			setLovValuesCurrency(req, res);
			setLovAuctionUserValues(req,res);
			
			page= "auction.jsp";
		}
		
		
		req.getSession().setAttribute("auctionId_wip", auctionId_wip);
		req.setAttribute("auctionId_wip", auctionId_wip);

		return page;
		
	}
	

	
	/*
	public Auction getAuction(String auctionId){
		
		Auction a = new Auction();

		AuctionDao ad = new AuctionDao();

		a = ad.getAuction(auctionId);
		
		return a;
		
	}	
	*/
	/*
	public Auction getAuctionById(Integer id){
		
		Auction a = new Auction();

		AuctionDao ad = new AuctionDao();

		a = ad.getAuctionById(id);
		
		return a;
		
	}
*/
	public Auction getAuctionById(BigDecimal id){
		
		Auction a = new Auction();

		AuctionDao ad = new AuctionDao();

		a = ad.getAuctionById(id);
		
		return a;
		
	}
	
	
	public Auction getAuctionByAuctionId(BigDecimal auction_id){
		
		Auction a = new Auction();

		AuctionDao ad = new AuctionDao();

		a = ad.getAuctionByAuctionId(auction_id);
		
		return a;
		
	}	

	public Auction getAuctionImageById(Integer id, String size){
		
		Auction a = new Auction();

		AuctionDao ad = new AuctionDao();

		a = ad.getAuctionImageById(id, size);
		
		return a;
		
	}	
	
	
	/*
	public int insertAuctionOnRegistration(String firstName, String lastName, String auctionId, Integer mobileNo, String verification_email_key){
		
		int i = 0;
		
		AuctionDao ad = new AuctionDao();

		i = ad.insertAuctionOnRegistration(firstName, lastName, auctionId, mobileNo, verification_email_key);
		
		return i;
		
	}
	*/
	public Auction insertAuctionOnCreate(
				String auction_name,
				BigDecimal auction_id,
				BigDecimal auction_no,
				String location,
				Date start_date_time,
				Date end_date_time,
				String auction_desc,
				Integer coordinator,
				Integer auction_type,
				Integer auctionStatus,
				Integer active,
				Integer visibility,
				Integer auction_item_increment_time,
				Integer bid_deposit,
				BigDecimal bid_deposit_amount,
				Integer no_of_lots,
				Integer no_of_items,
				Date date_sync,
				String terms_and_conditions,
				Integer category_level_1, 
				Integer one_lot_per_bidder,
				Integer one_start_bid,
				Integer bid_qualifier_price,
				Integer auto_send_post_notification,
				Integer user_id
			){
		
		Auction a = null;
		
		AuctionDao ad = new AuctionDao(req,res);

		a = ad.insertAuctionOnCreate(
				auction_name,
				auction_id,
				auction_no,
				location,
				start_date_time,
				end_date_time,
				auction_desc,
				coordinator,
				auction_type,
				auctionStatus,
				active,
				visibility,
				auction_item_increment_time,
				bid_deposit,
				bid_deposit_amount,
				no_of_lots,
				no_of_items,
				date_sync,
				terms_and_conditions,
				category_level_1,
				one_lot_per_bidder,
				one_start_bid,
				bid_qualifier_price,
				auto_send_post_notification,
				user_id
				);
		
		return a;
		
	}
	
	
	public Auction insertAuctionOnUpload(
			BigDecimal auction_id,
			BigDecimal auction_no,
			Timestamp auction_date,
			String location,
			BigDecimal default_premium,
			String auction_name,
			Timestamp last_date_sync,
			Integer user_id
		){
	
	Auction a = null;
	
	AuctionDao ad = new AuctionDao(req,res);

	a = ad.insertAuctionOnUpload(
			auction_id,
			auction_no,
			auction_date,
			location,
			default_premium,
			auction_name,
			last_date_sync,
			user_id
			);
	
	return a;
	
}
	
	public Auction updateAuctionOnUpdate(
				String auction_name,
				BigDecimal auction_id,
				BigDecimal auction_no,
				String location,
				Date start_date_time_d,
				Date end_date_time_d,
				String auction_desc,
				Integer coordinator,
				Integer auction_type,
				Integer auctionStatus,
				Integer active,
				Integer visibility,
				Integer auction_item_increment_time,
				Integer bid_deposit,
				BigDecimal bid_deposit_amount,
				Integer no_of_lots,
				Integer no_of_items,
				Date date_sync_d,
				String terms_and_conditions,
				Integer category_level_1,
				Integer one_lot_per_bidder,
				Integer one_start_bid,
				Integer bid_qualifier_price,
				Integer auto_send_post_notification,
				Integer user_id,
				BigDecimal auctionId_wip
			
			){
		
		Auction a = null;
		
		AuctionDao ad = new AuctionDao();
	
		a = ad.updateAuctionOnUpdate(
					auction_name,
					auction_id,
					auction_no,
					location,
					start_date_time_d,
					end_date_time_d,
					auction_desc,
					coordinator,
					auction_type,
					auctionStatus,
					active,
					visibility,
					auction_item_increment_time,
					bid_deposit,
					bid_deposit_amount,
					no_of_lots,
					no_of_items,
					date_sync_d,
					terms_and_conditions,
					category_level_1,
					one_lot_per_bidder,
					one_start_bid,
					bid_qualifier_price,
					auto_send_post_notification,
					user_id,
					auctionId_wip
				);
		
		return a;
		
	}
	
	
	public Auction updateAuctionOnUpload(
			BigDecimal auction_id,
			BigDecimal auction_no,
			Timestamp auction_date,
			String location,
			BigDecimal default_premium,
			Timestamp last_date_sync,
			Integer user_id
		){
	
	Auction a = null;
	
	AuctionDao ad = new AuctionDao();

	a = ad.updateAuctionOnUpload(
			auction_id,
			auction_no,
			auction_date,
			location,
			default_premium,
			last_date_sync,
			user_id
			);
	
	return a;
	
}
	
	
	public Auction updateAuctionImage(
			File file_small,
			File file,
			Integer user_id,
			BigDecimal auctionId_wip
		){
	
	Auction a = null;
	
	AuctionDao ad = new AuctionDao();

	a = ad.updateAuctionImage(
				file_small,
				file,
				user_id,
				auctionId_wip
			);
	
	return a;
	
}
	

	
	public List<Auction> getAuctionList(){
		
		List<Auction> aList = new ArrayList<Auction>();

		AuctionDao ad = new AuctionDao();

		aList = ad.getAuctionList();
		
		return aList;
		
	}
	
	
	public List<Auction> getAuctionListByTypeAndActive(Integer auctionType){
		
		List<Auction> aList = new ArrayList<Auction>();

		AuctionDao ad = new AuctionDao(req,res);

		aList = ad.getAuctionListByTypeAndActive(auctionType);
		
		return aList;
		
	}
	
	
	
	
	private void setLovValuesCategoryLevel(HttpServletRequest req, HttpServletResponse res){
		

		LovManager lovMngr = new LovManager(req, res);
		
		HashMap<Integer,Lov> catLev1LovHM = null;
		List<Lov> catLev1LovList = null;
		
		HashMap<Integer,Lov> catLev2LovHM = null;
		List<Lov> catLev2LovList = null;
		
		HashMap<Integer,Lov> catLev3LovHM = null;
		List<Lov> catLev3LovList = null;

		try {
			
			System.out.println("ITEM-CATEGORY-LEVEL-1-HM : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-HM"));
			System.out.println("ITEM-CATEGORY-LEVEL-1-LIST : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-LIST"));
			System.out.println("ITEM-CATEGORY-LEVEL-2-HM : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-HM"));
			System.out.println("ITEM-CATEGORY-LEVEL-2-LIST : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-LIST"));
			System.out.println("ITEM-CATEGORY-LEVEL-3-HM : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-HM"));
			System.out.println("ITEM-CATEGORY-LEVEL-3-LIST : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-LIST"));
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-HM")==null ){
				catLev1LovHM = lovMngr.getLovHM("ITEM-CATEGORY-LEVEL-1");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-1-HM", catLev1LovHM);
			}else{
				catLev1LovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-HM");
				req.setAttribute("ITEM-CATEGORY-LEVEL-1-HM", catLev1LovHM);
			}
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-LIST")==null ){
				catLev1LovList = lovMngr.getLovList("ITEM-CATEGORY-LEVEL-1");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-1-LIST", catLev1LovList);
			}else{
				catLev1LovList = (List<Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-LIST");
				req.setAttribute("ITEM-CATEGORY-LEVEL-1-LIST", catLev1LovList);
			}
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-HM")==null ){
				catLev2LovHM = lovMngr.getLovHM("ITEM-CATEGORY-LEVEL-2");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-2-HM", catLev2LovHM);
			}else{
				catLev2LovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-HM");
				req.setAttribute("ITEM-CATEGORY-LEVEL-2-HM", catLev2LovHM);
			}
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-LIST")==null ){
				catLev2LovList = lovMngr.getLovList("ITEM-CATEGORY-LEVEL-2");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-2-LIST", catLev2LovList);
			}else{
				catLev2LovList = (List<Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-LIST");
				req.setAttribute("ITEM-CATEGORY-LEVEL-2-LIST", catLev2LovList);
			}
			
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-HM")==null ){
				catLev3LovHM = lovMngr.getLovHM("ITEM-CATEGORY-LEVEL-3");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-3-HM", catLev3LovHM);
			}else{
				catLev3LovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-HM");
				req.setAttribute("ITEM-CATEGORY-LEVEL-3-HM", catLev3LovHM);
			}
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-LIST")==null ){
				catLev3LovList = lovMngr.getLovList("ITEM-CATEGORY-LEVEL-3");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-3-LIST", catLev3LovList);
			}else{
				catLev3LovList = (List<Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-LIST");
				req.setAttribute("ITEM-CATEGORY-LEVEL-3-LIST", catLev3LovList);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void setLovValuesCurrency(HttpServletRequest req, HttpServletResponse res){

		LovManager lovMngr = new LovManager(req, res);
		
		HashMap<Integer,Lov> currencyLovHM = null;
		List<Lov> currencyLovList = null;

		try {
			
			System.out.println("CURRENCY-HM : " + req.getSession().getAttribute("CURRENCY-HM"));
			System.out.println("CURRENCY-LIST : " + req.getSession().getAttribute("CURRENCY-LIST"));

			
			if(req.getSession().getAttribute("CURRENCY-HM")==null ){
				currencyLovHM = lovMngr.getLovHM("CURRENCY");
				req.getSession().setAttribute("CURRENCY-HM", currencyLovHM);
			}else{
				currencyLovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("CURRENCY-HM");
				req.setAttribute("CURRENCY-HM", currencyLovHM);
			}
			
			if(req.getSession().getAttribute("CURRENCY-LIST")==null ){
				currencyLovList = lovMngr.getLovList("CURRENCY");
				req.getSession().setAttribute("CURRENCY-LIST", currencyLovList);
			}else{
				currencyLovList = (List<Lov>)req.getSession().getAttribute("CURRENCY-LIST");
				req.setAttribute("CURRENCY-LIST", currencyLovList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void setLovAuctionUserValues(HttpServletRequest req, HttpServletResponse res){
		

		LovManager lovMngr = new LovManager(req, res);

		HashMap<Integer,Lov> auctionUserStatusLovHM = null;
		List<Lov> auctionUserStatusLovList = null;
	
		try {

			System.out.println("AUCTION-USER-STATUS-HM : " + req.getSession().getAttribute("AUCTION-USER-STATUS-HM"));
			System.out.println("AUCTION-USER-STATUS-LIST : " + req.getSession().getAttribute("AUCTION-USER-STATUS-LIST"));
			
			if(req.getSession().getAttribute("AUCTION-USER-STATUS-HM")==null ){
				auctionUserStatusLovHM = lovMngr.getLovHM("AUCTION-USER-STATUS");
				req.getSession().setAttribute("AUCTION-USER-STATUS-HM", auctionUserStatusLovHM);
			}else{
				auctionUserStatusLovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("AUCTION-USER-STATUS-HM");
				req.setAttribute("AUCTION-USER-STATUS-HM", auctionUserStatusLovHM);
			}
			
			if(req.getSession().getAttribute("AUCTION-USER-STATUS-LIST")==null ){
				auctionUserStatusLovList = lovMngr.getLovList("AUCTION-USER-STATUS");
				req.getSession().setAttribute("AUCTION-USER-STATUS-LIST", auctionUserStatusLovList);
			}else{
				auctionUserStatusLovList = (List<Lov>)req.getSession().getAttribute("AUCTION-USER-STATUS-LIST");
				req.setAttribute("AUCTION-USER-STATUS-LIST", auctionUserStatusLovList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public String generateAuctionPrivateToken(BigDecimal auctionId){
		String token = getAuctionById(auctionId).getToken();
		if(token == null) {
			token = Hash.md5(auctionId.toString()+"hrm2017salt");
			new AuctionDao().updateToken(auctionId, token);
		}
		return token;
	}
	
	public Auction getAuctionByToken(String token) {
		return new AuctionDao().getAuctionByToken(token);
				
	}
	
}

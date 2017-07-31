package hmr.com.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.bean.ItemStaging;
import hmr.com.bean.User;
import hmr.com.dao.ItemStagingDao;
import hmr.com.dao.UserDao;

public class ItemStagingManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public ItemStagingManager(){}
	/*
	public ItemStagingManager(HttpServletRequest req, HttpServletResponse res, String emailAdd){
		this.req = req;
		this.res = res;
	}
	*/
	public ItemStagingManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doItemStagingManager(File file_small, File file, String action, BigDecimal auctionId_wip){
		String page = "index.jsp";
		
		if(action.equals("saveItemStagingImage")){
			
			ItemStaging is = new ItemStaging();
			
			is = updateItemStagingImage(file_small, file,1,auctionId_wip);

			if(is!=null){
				//is = getItemStagingById(is.getItem_id());
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "ItemStaging images updated succeessful.");
			}else{
				is = getItemStagingById(auctionId_wip);
			}
			
			
			page = "auction.jsp";
		}
		
		return page;
	}
	
	public String doItemStagingManager(){
		String page = null;
		
		
		String action = req.getParameter("action")!=null ? req.getParameter("action") : (String)req.getSession().getAttribute("action");
		//String file_name = "";
		Integer user_id = 0;
		BigDecimal auctionId_wip = new BigDecimal(0);
		//String manager = "";
		//String userId = "";
		
		System.out.println("Paramerters doItemStagingManager - start");
		System.out.println("action : "+action);
		try{
			System.out.println("auctionId_wip Session : "+Integer.valueOf(req.getSession().getAttribute("auctionId-wip").toString()) );
		}catch(Exception exx){}
		
		System.out.println("Paramerters doItemStagingManager - end");
		System.out.println("");
		
		
		if("auctionList".equals(action)){
			
			System.out.println("auctionList");

			//List<ItemStaging> isList = getItemStagingList();
			
			//req.setAttribute("auctionList", isList);

			page ="auction-list.jsp";
		
		}else if("createItemStaging".equals(action)){
			
			System.out.println("createItemStaging");
			
			UserDao ud = new UserDao();
			
			List<User> userCoordinatorList = ud.getUserListByRole(5);

			req.setAttribute("userCoordinatorList", userCoordinatorList);

			page ="auction-create.jsp";
		
		}else if("saveItemStaging".equals(action)){
			
			System.out.println("saveItemStaging");

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
			String terms_and_conditions = req.getParameter("terms_and_conditions")!=null ? req.getParameter("terms_and_conditions").replaceAll("\\r|\\n", "") : "";

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
			

			ItemStaging is = insertItemStagingOnCreate(
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
						user_id
					);
			
			if(is!=null && is.getItem_id()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "ItemStaging created succeessful.");

				is = getItemStagingById(is.getItem_id());
				
				//ItemStagingRangeManager arMngr = new ItemStagingRangeManager();
				/*
				arMngr.insertItemStagingRangeOnCreate(auction_id, new BigDecimal(1), new BigDecimal(100), new BigDecimal(50), user_id);
				arMngr.insertItemStagingRangeOnCreate(auction_id, new BigDecimal(101), new BigDecimal(1000), new BigDecimal(100), user_id);
				arMngr.insertItemStagingRangeOnCreate(auction_id, new BigDecimal(1001), new BigDecimal(10000), new BigDecimal(500), user_id);
				arMngr.insertItemStagingRangeOnCreate(auction_id, new BigDecimal(10001), new BigDecimal(100000), new BigDecimal(5000), user_id);
*/
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
				
				
				req.getSession().setAttribute("auction", is);
				
				req.setAttribute("auction", is);
				
				page ="auction.jsp";
				
				
				
				
			}else if("auctionBidDetails".equals(action)){
				
				page ="auction-bid-details.jsp";
			}else{
				/*
				is = new ItemStaging();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "ItemStaging created failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("auction", is);
				
				req.setAttribute("auction", is);
				
				page ="auction-list.jsp";
				*/
			}

			

		
		}else if("viewItemStaging".equals(action)){
			
			System.out.println("viewItemStaging");

			auctionId_wip = !req.getParameter("auctionId_wip").equals("") ? new BigDecimal(req.getParameter("auctionId_wip")) : new BigDecimal(0);
			if(auctionId_wip.floatValue() > 0){
				
				//ItemStaging is = getItemStagingById(auctionId_wip);
				ItemStaging is = getItemStagingByItemStagingId(auctionId_wip);
				
				
				req.getSession().setAttribute("auction", is);
				
				req.setAttribute("auction", is);
				
				page ="auction.jsp";
				
			}

		}else if("updateItemStaging".equals(action)){
			
			System.out.println("updateItemStaging");

			auctionId_wip = req.getParameter("auctionId_wip")!=null && !req.getParameter("auctionId_wip").equals("") ? new BigDecimal(req.getParameter("auctionId_wip")): new BigDecimal(req.getSession().getAttribute("auctionId-wip").toString());
			
			System.out.println("updateItemStaging : auctionId_wip "+auctionId_wip);
			if(auctionId_wip == new BigDecimal(0)){
				auctionId_wip = new BigDecimal(req.getSession().getAttribute("auctionId-wip").toString());
			}
			if(auctionId_wip.floatValue() > 0){
				
				//ItemStaging is = getItemStagingById(auctionId_wip);
				ItemStaging is = getItemStagingByItemStagingId(auctionId_wip);
				
				
				
				req.getSession().setAttribute("auction", is);
				
				req.setAttribute("auction", is);
				
				
				UserDao ud = new UserDao();
				
				List<User> userCoordinatorList = ud.getUserListByRole(5);

				req.setAttribute("userCoordinatorList", userCoordinatorList);
				
				page ="auction-update.jsp";
				
			}

			
		}else if("saveUpdateItemStaging".equals(action)){
			
			System.out.println("saveUpdateItemStaging");

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
			String terms_and_conditions = req.getParameter("terms_and_conditions")!=null ? req.getParameter("terms_and_conditions").replaceAll("\\r|\\n", "") : "";

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

			
			ItemStaging is = updateItemStagingOnUpdate(
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
						user_id,
						auctionId_wip
					);
			

			
			
			if(is!=null && is.getItem_id()!=null){
				
				//is = getItemStagingById(auctionId_wip);
				is = getItemStagingByItemStagingId(auctionId_wip);
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "ItemStaging updated succeessful.");
				
				req.getSession().setAttribute("auction", is);
				
				req.setAttribute("auction", is);
				
				page ="auction.jsp";
				
			}else{
				
				is = new ItemStaging();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "ItemStaging update failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("auction", is);
				
				req.setAttribute("auction", is);
				
				page ="auction-list.jsp";
				
			}
			
			
		}else if("saveItemStagingImage".equals(action)){
			
			System.out.println("saveItemStagingImage");
			
			
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
			page= "auction.jsp";
		}
		
		
		
		
	
		
		System.out.println("Paramerters doItemStagingManager - page : "+page);
		
		return page;
		
	}
	

	
	/*
	public ItemStaging getItemStaging(String auctionId){
		
		ItemStaging is = new ItemStaging();

		ItemStagingDao isd = new ItemStagingDao();

		is = isd.getItemStaging(auctionId);
		
		return is;
		
	}	
	*/
	/*
	public ItemStaging getItemStagingById(Integer id){
		
		ItemStaging is = new ItemStaging();

		ItemStagingDao isd = new ItemStagingDao();

		is = isd.getItemStagingById(id);
		
		return is;
		
	}
*/
	public ItemStaging getItemStagingById(BigDecimal id){
		
		ItemStaging is = new ItemStaging();

		ItemStagingDao isd = new ItemStagingDao();

		//is = isd.getItemStagingById(id);
		
		return is;
		
	}
	
	
	public ItemStaging getItemStagingByItemStagingId(BigDecimal auction_id){
		
		ItemStaging is = new ItemStaging();

		ItemStagingDao isd = new ItemStagingDao();

		//is = isd.getItemStagingByItemStagingId(auction_id);
		
		return is;
		
	}	

	public ItemStaging getItemStagingImageById(Integer id, String size){
		
		ItemStaging is = new ItemStaging();

		ItemStagingDao isd = new ItemStagingDao();

		//is = isd.getItemStagingImageById(id, size);
		
		return is;
		
	}	
	
	
	/*
	public int insertItemStagingOnRegistration(String firstName, String lastName, String auctionId, Integer mobileNo, String verification_email_key){
		
		int i = 0;
		
		ItemStagingDao isd = new ItemStagingDao();

		i = isd.insertItemStagingOnRegistration(firstName, lastName, auctionId, mobileNo, verification_email_key);
		
		return i;
		
	}
	*/
	public ItemStaging insertItemStagingOnCreate(
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
				Integer user_id
			){
		
		ItemStaging is = null;
		
		ItemStagingDao isd = new ItemStagingDao(req,res);
/*
		is = isd.insertItemStagingOnCreate(
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
				user_id
				);
		*/
		return is;
		
	}
	
	
	public ItemStaging updateItemStagingOnUpdate(
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
				Integer user_id,
				BigDecimal auctionId_wip
			
			){
		
		ItemStaging is = null;
		
		ItemStagingDao isd = new ItemStagingDao();
	/*
		is = isd.updateItemStagingOnUpdate(
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
					user_id,
					auctionId_wip
				);
		*/
		return is;
		
	}
	
	
	
	public ItemStaging updateItemStagingImage(
			File file_small,
			File file,
			Integer user_id,
			BigDecimal auctionId_wip
		){
	
	ItemStaging is = null;
	
	ItemStagingDao isd = new ItemStagingDao();
/*
	is = isd.updateItemStagingImage(
				file_small,
				file,
				user_id,
				auctionId_wip
			);
	*/
	return is;
	
}
	

	
	public List<ItemStaging> getItemStagingList(){
		
		List<ItemStaging> isList = new ArrayList<ItemStaging>();

		ItemStagingDao isd = new ItemStagingDao();

		isList = isd.getItemStagingList();
		
		return isList;
		
	}
	
	
	public List<ItemStaging> getItemStagingListByTypeAndActive(Integer auctionType){
		
		List<ItemStaging> isList = new ArrayList<ItemStaging>();

		ItemStagingDao isd = new ItemStagingDao(req,res);

		//isList = isd.getItemStagingListByTypeAndActive(auctionType);
		
		return isList;
		
	}
	
	public int deleteItemStagingOnSearch(){
		ItemStagingDao isd = new ItemStagingDao(req,res);
		int i = isd.deleteItemStagingOnSearch();
		return i;
		
	}
	
	public int insertItemStagingOnSearch(
			BigDecimal item_id,
			BigDecimal lot_id,
			Integer status_id,
			BigDecimal reference_no,
			Integer pullout_id,
			BigDecimal target_price,
			BigDecimal reserved_price,
			BigDecimal rate,
			BigDecimal amount_bid,
			Integer received_items_id,
			String qt_remarks,
			BigDecimal assess_value,
			Integer payment_status,
			BigDecimal bidder_number_id,
			Integer payables_id,
			BigDecimal product_code,
			BigDecimal srp,
			BigDecimal consignor_id,
			String description,
			BigDecimal delivery_receipt_id,
			Timestamp last_date_sync){
		ItemStagingDao isd = new ItemStagingDao(req,res);
		isd.insertItemStagingOnSearch(
				item_id,
				lot_id,
				status_id,
				reference_no,
				pullout_id,
				target_price,
				reserved_price,
				rate,
				amount_bid,
				received_items_id,
				qt_remarks,
				assess_value,
				payment_status,
				bidder_number_id,
				payables_id,
				product_code,
				srp,
				consignor_id,
				description,
				delivery_receipt_id,
				last_date_sync
				
				);
		return 1;
	}
	
	//Second Connection
	public int insertItemStagingOnSearch2(
			BigDecimal item_id,
			BigDecimal lot_id,
			Integer status_id,
			BigDecimal reference_no,
			Integer pullout_id,
			BigDecimal target_price,
			BigDecimal reserved_price,
			BigDecimal rate,
			BigDecimal amount_bid,
			Integer received_items_id,
			String qt_remarks,
			BigDecimal assess_value,
			Integer payment_status,
			BigDecimal bidder_number_id,
			Integer payables_id,
			BigDecimal product_code,
			BigDecimal srp,
			BigDecimal consignor_id,
			String description,
			BigDecimal delivery_receipt_id,
			Timestamp last_date_sync){
		ItemStagingDao isd = new ItemStagingDao(req,res);
		isd.insertItemStagingOnSearch2(
				item_id,
				lot_id,
				status_id,
				reference_no,
				pullout_id,
				target_price,
				reserved_price,
				rate,
				amount_bid,
				received_items_id,
				qt_remarks,
				assess_value,
				payment_status,
				bidder_number_id,
				payables_id,
				product_code,
				srp,
				consignor_id,
				description,
				delivery_receipt_id,
				last_date_sync
				
				);
		return 1;
	}
	
}

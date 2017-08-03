package hmr.com.manager;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hmr.com.bean.Item;
import hmr.com.bean.Lov;
import hmr.com.bean.User;
import hmr.com.dao.ItemDao;
import hmr.com.dao.UserDao;


public class ItemManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public ItemManager(){}
	/*
	public ItemManager(HttpServletRequest req, HttpServletResponse res, String emailAdd){
		this.req = req;
		this.res = res;
	}
	*/
	public ItemManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doItemManager(File file, String action, BigDecimal itemId_wip){
		String page = "index.jsp";
		
		if(action.equals("saveItemImage")){
			
			Item i = new Item();
			
			i = updateItemImage(file,1,itemId_wip);

			if(i!=null){
				i = getItemById(i.getId());
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Item images updated succeessful.");
			}else{
				i = getItemById(itemId_wip);
			}
			
			
			page = "item.jsp";
		}
		
		return page;
	}
	
	public String doItemManager(){
		String page = null;
		
		
		String action = req.getParameter("action")!=null ? req.getParameter("action") : (String)req.getSession().getAttribute("action");
		//String file_name = "";
		Integer user_id = 0;
		
		BigDecimal auctionId_wip = new BigDecimal(0);
		
		try{
			auctionId_wip = req.getParameter("auctionId_wip")!=null && !"".equals(req.getParameter("auctionId_wip"))  ? new BigDecimal(req.getParameter("auctionId_wip")): new BigDecimal(0);
		}catch(Exception exx){}
		
		BigDecimal itemId_wip = new BigDecimal(0);
		//String manager = "";
		//String userId = "";
		
		System.out.println("Paramerters doItemManager - start");
		System.out.println("action : "+action);
		try{
			System.out.println("itemId_wip Session : "+Integer.valueOf(req.getSession().getAttribute("itemId-wip").toString()) );
		}catch(Exception exx){}
		
		System.out.println("Paramerters doItemManager - end");
		System.out.println("");
		
		
		if("itemList".equals(action)){
			
			System.out.println("itemList");

			List<Item> iList = getItemList();
			
			req.setAttribute("itemList", iList);

			page ="item-list.jsp";
		
		}else if("createItem".equals(action)){
			
			System.out.println("createItem");
			
			UserDao ud = new UserDao();
			
			List<User> userBidderList = ud.getUserListByRole(2);
			
			setLovValuesCategoryLevel(req, res);

			req.setAttribute("userBidderList", userBidderList);

			page ="item-create.jsp";
		
		}else if("saveItem".equals(action)){
			
			System.out.println("saveItem");

			//String userId = req.getParameter("userId")!=null ? req.getParameter("userId") : "";
			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));

			BigDecimal lot_id = !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			BigDecimal item_id = !req.getParameter("item_id").equals("") ? new BigDecimal(req.getParameter("item_id")) : new BigDecimal(0);
			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			BigDecimal reference_no = !req.getParameter("reference_no").equals("") ? new BigDecimal(req.getParameter("reference_no")) : new BigDecimal(0);
			BigDecimal target_price = !req.getParameter("target_price").equals("") ? new BigDecimal(req.getParameter("target_price")) : new BigDecimal(0);
			BigDecimal reserve_price= !req.getParameter("reserve_price").equals("") ? new  BigDecimal(req.getParameter("reserve_price")) : new BigDecimal(0);
			BigDecimal amount_bid = !req.getParameter("amount_bid").equals("") ? new BigDecimal(req.getParameter("amount_bid")) : new BigDecimal(0);
			BigDecimal amount_buy= !req.getParameter("amount_buy").equals("") ? new BigDecimal(req.getParameter("amount_buy")) : new BigDecimal(0);
			Integer action_taken = !req.getParameter("action_taken").equals("") ? Integer.valueOf(req.getParameter("action_taken")) : 0;
			Integer is_buy = !req.getParameter("is_buy").equals("") ? Integer.valueOf(req.getParameter("is_buy")) : 0;
			Integer is_bid = !req.getParameter("is_bid").equals("") ? Integer.valueOf(req.getParameter("is_bid")) : 0;
			BigDecimal buy_price = !req.getParameter("buy_price").equals("") ? new BigDecimal(req.getParameter("buy_price")) : new BigDecimal(0);
			Integer bidder_id = !req.getParameter("bidder_id").equals("") ? Integer.valueOf(req.getParameter("bidder_id")) : 0;
			Integer sync_status = !req.getParameter("sync_status").equals("") ? Integer.valueOf(req.getParameter("sync_status")) : 0;
			Integer item_increment_time = !req.getParameter("item_increment_time").equals("") ? Integer.valueOf(req.getParameter("item_increment_time")) : 0;
			String item_desc = req.getParameter("item_desc")!=null ? req.getParameter("item_desc") : "";
			Integer category_level_1 = req.getParameter("category_level_1")!=null ? Integer.valueOf(req.getParameter("category_level_1")) : 0;
			
			//System.out.println("req.getParameter(category_level_2) "+req.getParameter("category_level_2"));
			
			Integer category_level_2 = req.getParameter("category_level_2")!=null ? Integer.valueOf(req.getParameter("category_level_2")) : 0;
			Integer category_level_3 = req.getParameter("category_level_3")!=null ? Integer.valueOf(req.getParameter("category_level_3")) : 0;
			
			Item i = insertItemOnCreate(
						lot_id,
						item_id,
						auction_id,
						reference_no,
						target_price,
						reserve_price,
						amount_bid,
						amount_buy,
						action_taken,
						is_buy,
						is_bid,
						buy_price,
						bidder_id,
						sync_status,
						item_increment_time,
						item_desc,
						category_level_1,
						category_level_2,
						category_level_3,
						user_id
					);
			
			if(i!=null && i.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Item created succeessful.");

				i = getItemById(i.getId());
				
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
				
				setLovValuesCategoryLevel(req, res);
				
				req.getSession().setAttribute("item", i);
				
				req.setAttribute("item", i);
				
				page ="item.jsp";
				
			}else{
				/*
				i = new Item();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Item created failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("item", i);
				
				req.setAttribute("item", i);
				
				page ="item-list.jsp";
				*/
			}

			

		
		}else if("viewItem".equals(action)){
			
			System.out.println("viewItem");

			itemId_wip = req.getParameter("itemId_wip")!=null ? new BigDecimal(req.getParameter("itemId_wip")): new BigDecimal(0);

			auctionId_wip = req.getParameter("auctionId_wip")!=null && !"".equals(req.getParameter("auctionId_wip"))  ? new BigDecimal(req.getParameter("auctionId_wip")): new BigDecimal(0);
			
			if(itemId_wip !=  new BigDecimal(0)){
				
				
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
				
				
				try {
					bidderUserHM = uMngr.getUserHMByRole(2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				req.getSession().setAttribute("BIDDER-USER-HM", bidderUserHM);
				req.setAttribute("BIDDER-USER-HM", bidderUserHM);
				
				
				setLovValuesCategoryLevel(req, res);
				
				Item i = getItemById(itemId_wip);
				
				req.getSession().setAttribute("item", i);
				
				req.setAttribute("item", i);
				
				page ="item.jsp";
				
			}

		}else if("updateItem".equals(action)){
			
			System.out.println("updateItem");

			auctionId_wip = req.getParameter("auctionId_wip")!=null && !"".equals(req.getParameter("auctionId_wip"))  ? new BigDecimal(req.getParameter("auctionId_wip")): new BigDecimal(0);

			itemId_wip = req.getParameter("itemId_wip")!=null && !req.getParameter("itemId_wip").equals("") ? new BigDecimal(req.getParameter("itemId_wip")): new BigDecimal(req.getSession().getAttribute("itemId-wip").toString());
			
			System.out.println("updateItem : itemId_wip "+itemId_wip);
			if(itemId_wip == new BigDecimal(0)){
				itemId_wip = new BigDecimal(req.getSession().getAttribute("itemId-wip").toString());
			}
			if(itemId_wip !=  new BigDecimal(0)){
				
				Item i = getItemById(itemId_wip);
				
				req.getSession().setAttribute("item", i);
				
				req.setAttribute("item", i);
				
				
				UserDao ud = new UserDao();
				
				List<User> userBidderList = ud.getUserListByRole(2);
				
				setLovValuesCategoryLevel(req, res);

				req.setAttribute("userBidderList", userBidderList);
				
				page ="item-update.jsp";
				
			}

			
		}else if("saveUpdateItem".equals(action)){
			
			System.out.println("saveUpdateItem");

			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));
			
			
			System.out.println("req.getParameter(itemId_wip) "+req.getParameter("itemId_wip"));
			itemId_wip = req.getParameter("itemId_wip")!=null ? new BigDecimal(req.getParameter("itemId_wip")) :  new BigDecimal(0);

			
			BigDecimal lot_id = !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			BigDecimal item_id = !req.getParameter("item_id").equals("") ? new BigDecimal(req.getParameter("item_id")) : new BigDecimal(0);
			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			BigDecimal reference_no = !req.getParameter("reference_no").equals("") ? new BigDecimal(req.getParameter("reference_no")) : new BigDecimal(0);
			BigDecimal target_price = !req.getParameter("target_price").equals("") ? new BigDecimal(req.getParameter("target_price")) : new BigDecimal(0);
			BigDecimal reserve_price= !req.getParameter("reserve_price").equals("") ? new BigDecimal(req.getParameter("reserve_price")) : new BigDecimal(0);
			BigDecimal amount_bid = !req.getParameter("amount_bid").equals("") ? new  BigDecimal(req.getParameter("amount_bid")) : new BigDecimal(0);
			BigDecimal amount_buy= !req.getParameter("amount_buy").equals("") ? new BigDecimal(req.getParameter("amount_buy")) : new BigDecimal(0);
			Integer action_taken = !req.getParameter("action_taken").equals("") ? Integer.valueOf(req.getParameter("action_taken")) : 0;
			Integer is_buy = !req.getParameter("is_buy").equals("") ? Integer.valueOf(req.getParameter("is_buy")) : 0;
			Integer is_bid = !req.getParameter("is_bid").equals("") ? Integer.valueOf(req.getParameter("is_bid")) : 0;
			BigDecimal buy_price = !req.getParameter("buy_price").equals("") ? new BigDecimal(req.getParameter("buy_price")) : new BigDecimal(0);
			Integer bidder_id = req.getParameter("bidder_id")!=null && !req.getParameter("bidder_id").equals("") ? Integer.valueOf(req.getParameter("bidder_id")) : 0;
			Integer sync_status = !req.getParameter("sync_status").equals("") ? Integer.valueOf(req.getParameter("sync_status")) : 0;
			Integer item_increment_time = !req.getParameter("item_increment_time").equals("") ? Integer.valueOf(req.getParameter("item_increment_time")) : 0;
			String item_desc = req.getParameter("item_desc")!=null ? req.getParameter("item_desc") : "";
			Integer category_level_1 = !req.getParameter("category_level_1").equals("") ? Integer.valueOf(req.getParameter("category_level_1")) : 0;
			Integer category_level_2 = !req.getParameter("category_level_2").equals("") ? Integer.valueOf(req.getParameter("category_level_2")) : 0;
			Integer category_level_3 = !req.getParameter("category_level_3").equals("") ? Integer.valueOf(req.getParameter("category_level_3")) : 0;
			
			System.out.println("Item Manager buy_price "+buy_price);
			
			
			Item i = updateItemOnUpdate(
						lot_id,
						item_id,
						auction_id,
						reference_no,
						target_price,
						reserve_price,
						amount_bid,
						amount_buy,
						action_taken,
						is_buy,
						is_bid,
						buy_price,
						bidder_id,
						sync_status,
						item_increment_time,
						item_desc,
						category_level_1,
						category_level_2,
						category_level_3,
						user_id,
						itemId_wip
					);
			

			
			
			if(i!=null && i.getId()!=null){
				
				i = getItemById(itemId_wip);
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Item updated succeessful.");
				
				
				UserDao ud = new UserDao();
				
				List<User> userBidderList = ud.getUserListByRole(2);
				
				setLovValuesCategoryLevel(req, res);

				req.setAttribute("userBidderList", userBidderList);
				
				req.getSession().setAttribute("item", i);
				
				req.setAttribute("item", i);
				
				page ="item.jsp";
				
			}else{
				
				i = new Item();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Item update failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("item", i);
				
				req.setAttribute("item", i);
				
				page ="item-list.jsp";
				
			}
			
			
		}else if("saveItemImage".equals(action)){
			
			System.out.println("saveItemImage");
			
			
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
			page= "item.jsp";
		}
		
		req.getSession().setAttribute("auctionId_wip", auctionId_wip);
		
		req.setAttribute("auctionId_wip", auctionId_wip);
	
		
		System.out.println("Paramerters doItemManager - page : "+page);
		
		return page;
		
	}
	

	
	/*
	public Item getItem(String itemId){
		
		Item i = new Item();

		ItemDao ad = new ItemDao();

		i = ad.getItem(itemId);
		
		return i;
		
	}	
	*/
	public Item getItemById(BigDecimal id){
		
		Item i = new Item();

		ItemDao ad = new ItemDao();

		i = ad.getItemById(id);
		
		return i;
		
	}	
	
	public Item getItemImageById(Integer id, String size){
		
		Item i = new Item();

		ItemDao ad = new ItemDao();

		i = ad.getItemImageById(id, size);
		
		return i;
		
	}	
	
	
	/*
	public int insertItemOnRegistration(String firstName, String lastName, String itemId, Integer mobileNo, String verification_email_key){
		
		int i = 0;
		
		ItemDao ad = new ItemDao();

		i = ad.insertItemOnRegistration(firstName, lastName, itemId, mobileNo, verification_email_key);
		
		return i;
		
	}
	*/
	public Item insertItemOnCreate(
				BigDecimal lot_id,
				BigDecimal item_id,
				BigDecimal auction_id,
				BigDecimal reference_no,
				BigDecimal target_price,
				BigDecimal reserve_price,
				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action_taken,
				Integer is_buy,
				Integer is_bid,
				BigDecimal buy_price,
				Integer bidder_id,
				Integer sync_status,
				Integer item_increment_time,
				String item_desc,
				Integer category_level_1,
				Integer category_level_2,
				Integer category_level_3,
				Integer user_id
			){
		
		Item i = null;
		
		ItemDao ad = new ItemDao(req,res);

		i = ad.insertItemOnCreate(
				lot_id,
				item_id,
				auction_id,
				reference_no,
				target_price,
				reserve_price,
				amount_bid,
				amount_buy,
				action_taken,
				is_buy,
				is_bid,
				buy_price,
				bidder_id,
				sync_status,
				item_increment_time,
				item_desc,
				category_level_1,
				category_level_2,
				category_level_3,
				user_id
				);
		
		return i;
		
	}
	
	public Item insertItemOnUpload(
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
			BigDecimal auction_id,
			Timestamp last_date_sync,
			Integer user_id
		){
	
	Item i = null;
	
	ItemDao ad = new ItemDao(req,res);

	i = ad.insertItemOnUpload(
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
			auction_id,
			last_date_sync,
			user_id
			);
	
	return i;
	
}

	
	
	
	public Item updateItemOnUpdate(
				BigDecimal lot_id,
				BigDecimal item_id,
				BigDecimal auction_id,
				BigDecimal reference_no,
				BigDecimal target_price,
				BigDecimal reserve_price,
				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action_taken,
				Integer is_buy,
				Integer is_bid,
				BigDecimal buy_price,
				Integer bidder_id,
				Integer sync_status,
				Integer item_increment_time,
				String item_desc,
				Integer category_level_1,
				Integer category_level_2,
				Integer category_level_3,
				Integer user_id,
				BigDecimal itemId_wip
			
			){
		
		Item i = null;
		
		ItemDao ad = new ItemDao();
	
		i = ad.updateItemOnUpdate(
					lot_id,
					item_id,
					auction_id,
					reference_no,
					target_price,
					reserve_price,
					amount_bid,
					amount_buy,
					action_taken,
					is_buy,
					is_bid,
					buy_price,
					bidder_id,
					sync_status,
					item_increment_time,
					item_desc,
					category_level_1,
					category_level_2,
					category_level_3,
					user_id,
					itemId_wip
				);
		
		return i;
		
	}
	
	public Item updateItemOnUpload(
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
			BigDecimal weight,
			BigDecimal auction_id,
			Timestamp last_date_sync,
			Integer user_id
		){
	
	Item i = null;
	
	ItemDao ad = new ItemDao();

	i = ad.updateItemOnUpload(
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
				weight,
				auction_id,
				last_date_sync,
				user_id
			);
	
	return i;
	
}
	
	
	public Item updateItemImage(
			File file,
			Integer user_id,
			BigDecimal itemId_wip
		){
	
	Item i = null;
	
	ItemDao ad = new ItemDao();

	i = ad.updateItemImage(
				file,
				user_id,
				itemId_wip
			);
	
	return i;
	
}
	/*
	public Item getItemRegistration(String itemId, String vek){
		
		Item i = new Item();

		ItemDao ad = new ItemDao();

		i = ad.getItemRegistration(itemId, vek);
		
		return i;
		
	}	
	*/
	/*
	public int updatePasswordOnActivation(String itemId, String pw, Integer item_id){
		
		int i = 0;
		
		ItemDao ad = new ItemDao();

		i = ad.updatePasswordOnActivation(itemId, pw, item_id);
		
		return i;
		
	}
	*/
	public List<Item> getItemList(){
		
		List<Item> iList = new ArrayList<Item>();

		ItemDao ad = new ItemDao();

		iList = ad.getItemList();
		
		return iList;
		
	}
	
	
	public List<Item> getItemListByAuctionId(BigDecimal auction_id){
		
		List<Item> iList = new ArrayList<Item>();

		ItemDao ad = new ItemDao();

		iList = ad.getItemListByAuctionId(auction_id);
		
		return iList;
		
	}
	
	public HashMap<BigDecimal, Item> getItemHMByLotId(BigDecimal lot_id){
		
		HashMap<BigDecimal, Item> iHM = new HashMap<BigDecimal, Item>();

		ItemDao ad = new ItemDao();

		iHM = ad.getItemHMByLotId(lot_id);
		
		return iHM;
		
	}
	
	public HashMap<BigDecimal, Item> getItemHMByAuctionId_SetReferenceNo(BigDecimal auction_id){
		
		HashMap<BigDecimal, Item> iHM = new HashMap<BigDecimal, Item>();

		ItemDao ad = new ItemDao();

		iHM = ad.getItemHMByAuctionId_SetReferenceNo(auction_id);
		
		return iHM;
		
	}
	
	public HashMap<BigDecimal, Item> getItemHMByAuctionId_SetItemId(BigDecimal auction_id){
		
		HashMap<BigDecimal, Item> iHM = new HashMap<BigDecimal, Item>();

		ItemDao ad = new ItemDao();

		iHM = ad.getItemHMByAuctionId_SetItemId(auction_id);
		
		return iHM;
		
	}
	
	public List<Item> getLotItemsById(BigDecimal lot_id) {
		ItemDao ad = new ItemDao();
		return ad.getLotItemsById(lot_id);
	}
	
	/*
	public List<Item> getItemListByTypeAndActive(Integer itemType){
		
		List<Item> iList = new ArrayList<Item>();

		ItemDao ad = new ItemDao(req,res);

		iList = ad.getItemListByTypeAndActive(itemType);
		
		return iList;
		
	}
	
	*/
	
	
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
	
	
	
}

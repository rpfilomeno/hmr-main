package hmr.com.manager;


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


import hmr.com.bean.Auction;
import hmr.com.bean.BiddingTransaction;
import hmr.com.bean.Image;
import hmr.com.bean.Item;
import hmr.com.bean.Lot;
import hmr.com.bean.User;
import hmr.com.dao.LotDao;
import hmr.com.dao.UserDao;


public class LotManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public LotManager(){}
	/*
	public LotManager(HttpServletRequest req, HttpServletResponse res, String emailAdd){
		this.req = req;
		this.res = res;
	}
	*/
	public LotManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	/*
	public String doLotManager(File file_small, File file, String action, Integer lotId_wip){
		String page = "index.jsp";
		
		if(action.equals("saveLotImage")){
			
			Lot l = new Lot();
			
			a = updateLotImage(file_small, file,1,lotId_wip);

			if(a!=null){
				a = getLotById(l.getId());
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Lot images updated succeessful.");
			}else{
				a = getLotById(lotId_wip);
			}
			
			
			page = "lot.jsp";
		}
		
		return page;
	}
	*/
	public String doLotManager(){
		String page = null;
		
		
		String action = req.getParameter("action")!=null ? req.getParameter("action") : (String)req.getSession().getAttribute("action");
		//String file_name = "";
		Integer user_id = 0;
		
		BigDecimal auctionId_wip = new BigDecimal(0);
		
		try{
			auctionId_wip = req.getParameter("auctionId_wip")!=null && !"".equals(req.getParameter("auctionId_wip"))  ? new BigDecimal(req.getParameter("auctionId_wip")): new BigDecimal(0);
		}catch(Exception exx){}

		BigDecimal lotId_wip = new BigDecimal(0);
		//String manager = "";
		//String userId = "";
		
		System.out.println("Paramerters doLotManager - start");
		System.out.println("action : "+action);
		System.out.println("auctionId_wip : "+auctionId_wip);
		System.out.println("Paramerters doLotManager - end");
		System.out.println("");
		
		
		if("lotList".equals(action)){
			
			System.out.println("lotList");

			List<Lot> lList = getLotList();
			
			req.setAttribute("lotList", lList);

			page ="lot-list.jsp";
		
		}else if("lotBidDetails".equals(action)){
			System.out.println("lotBidDetails");
			lotId_wip = req.getParameter("lotId_wip")!=null ? new BigDecimal(req.getParameter("lotId_wip")): new BigDecimal(0);
			
			System.out.println("lotId_wip : "+lotId_wip);
			
			AuctionManager aMngr = new AuctionManager(req,res);
			AuctionRangeManager arMngr = new AuctionRangeManager(req,res);
			LotManager lMngr = new LotManager(req,res);
			ItemManager iMngr = new ItemManager(req,res);
			LotRangeManager lrMngr = new LotRangeManager();
			
			
			Lot l = lMngr.getLotById(lotId_wip);
			Auction a = aMngr.getAuctionByAuctionId(l.getAuction_id());
			
			Lot delta_l = this.applyLotRules(l);
			
			List<Item> iL = iMngr.getLotItemsById(l.getLot_id());
				
			List<Image> lot_images = new ImageManager().getImageListByLotId(l.getId());
			
			List<BiddingTransaction> bidding_transactions = new BiddingTransactionManager().getLatestBiddingTransactionLotId(l.getLot_id());
			
			req.setAttribute("lot_images", lot_images);
			req.setAttribute("lot", delta_l);
			req.setAttribute("items", iL);
			req.setAttribute("auction", a);
			req.setAttribute("bidding_transactions", bidding_transactions);

			page ="lot-bid-details.jsp";
			
		}else if("createLot".equals(action)){
			
			System.out.println("createLot");
			
			UserDao ud = new UserDao();
			
			List<User> userCoordinatorList = ud.getUserListByRole(5);

			req.setAttribute("userCoordinatorList", userCoordinatorList);

			List<User> userBidderList = ud.getUserListByRole(2);
			
			//setLovValuesCategoryLevel(req, res);

			req.setAttribute("userBidderList", userBidderList);

			page ="lot-create.jsp";
		
		}else if("saveLot".equals(action)){
			
			System.out.println("saveLot");

			//String userId = req.getParameter("userId")!=null ? req.getParameter("userId") : "";
			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));

			BigDecimal lot_no = !req.getParameter("lot_no").equals("") ? new BigDecimal(req.getParameter("lot_no")) : new BigDecimal(0);
			BigDecimal lot_id = !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			String lot_desc = req.getParameter("lot_desc")!=null ? req.getParameter("lot_desc") : "";
			BigDecimal assessment_value = !req.getParameter("assessment_value").equals("") ? new BigDecimal(req.getParameter("assessment_value")) : new BigDecimal(0);
			BigDecimal duties = !req.getParameter("duties").equals("") ? new BigDecimal(req.getParameter("duties")) : new BigDecimal(0);
			BigDecimal vat = !req.getParameter("vat").equals("") ? new BigDecimal(req.getParameter("vat")) : new BigDecimal(0);
			String unit = req.getParameter("unit")!=null ? req.getParameter("unit") : "";
			BigDecimal premium_rate = !req.getParameter("premium_rate").equals("") ? new BigDecimal(req.getParameter("premium_rate")) : new BigDecimal(0);
			Integer lot_type_id = !req.getParameter("lot_type_id").equals("") ? Integer.valueOf(req.getParameter("lot_type_id")) : 0;
			Integer active = !req.getParameter("active").equals("") ? Integer.valueOf(req.getParameter("active")) : 0;
			Integer unit_qty = !req.getParameter("unit_qty").equals("")  ? Integer.valueOf(req.getParameter("unit_qty")) : 0;
			
			BigDecimal amount_bid = !req.getParameter("amount_bid").equals("") ? new BigDecimal(req.getParameter("amount_bid")) : new BigDecimal(0);
			BigDecimal amount_buy = !req.getParameter("amount_buy").equals("") ? new BigDecimal(req.getParameter("amount_buy")) : new BigDecimal(0);
			Integer action_taken = !req.getParameter("action_taken").equals("") ? Integer.valueOf(req.getParameter("action_taken")) : 0;
			Integer is_buy = !req.getParameter("is_buy").equals("") ? Integer.valueOf(req.getParameter("is_buy")) : 0;
			Integer is_bid= !req.getParameter("is_bid").equals("") ? Integer.valueOf(req.getParameter("is_bid")) : 0;
			BigDecimal buy_price = !req.getParameter("buy_price").equals("") ? new BigDecimal(req.getParameter("buy_price")) : new BigDecimal(0);
			Integer bidder_id = !req.getParameter("bidder_id").equals("") ? Integer.valueOf(req.getParameter("bidder_id")) : 0;
			Integer lot_increment_time = !req.getParameter("lot_increment_time").equals("") ? Integer.valueOf(req.getParameter("lot_increment_time")) : 0;
			String lot_name = req.getParameter("lot_name")!=null ? req.getParameter("lot_name") : "";
			
			Lot l = insertLotOnCreate(
						lot_no,
						lot_id,
						auction_id,
						lot_desc,
						assessment_value,
						duties,
						vat,
						unit,
						premium_rate,
						lot_type_id,
						active,
						unit_qty,
						
						amount_bid,
						amount_buy,
						action_taken,
						is_buy,
						is_bid,
						buy_price,
						bidder_id,
						lot_increment_time,
						lot_name,
						
						user_id
					);
			
			if(l!=null && l.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Lot created succeessful.");

				l = getLotById(l.getId());
				
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
				
				
				req.getSession().setAttribute("lot", l);
				
				req.setAttribute("lot", l);
				
				page ="lot.jsp";
				
			}else{
				/*
				a = new Lot();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Lot created failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("lot", l);
				
				req.setAttribute("lot", l);
				
				page ="lot-list.jsp";
				*/
			}

			

		
		}else if("viewLot".equals(action)){
			
			System.out.println("viewLot");
			
			System.out.println(req.getParameter("lotId_wip"));

			lotId_wip = req.getParameter("lotId_wip")!=null ? new BigDecimal(req.getParameter("lotId_wip")): new BigDecimal(0);
			
			System.out.println("lotId_wip : "+lotId_wip);
			
			if(lotId_wip.floatValue() > 0){
				
				
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
				
				
				
				Lot l = getLotById(lotId_wip);
				
				req.getSession().setAttribute("lot", l);

				req.setAttribute("lot", l);

				page ="lot.jsp";
				
			}

		}else if("updateLot".equals(action)){
			
			System.out.println("updateLot");
			
			auctionId_wip = req.getParameter("auctionId_wip")!=null && !"".equals(req.getParameter("auctionId_wip"))  ? new BigDecimal(req.getParameter("auctionId_wip")): new BigDecimal(0);

			lotId_wip = req.getParameter("lotId_wip")!=null && !req.getParameter("lotId_wip").equals("") ? new BigDecimal(req.getParameter("lotId_wip")): new BigDecimal(req.getSession().getAttribute("lotId-wip").toString());
			
			System.out.println("updateLot : lotId_wip "+lotId_wip);
			if(lotId_wip.floatValue() == 0){
				lotId_wip = new BigDecimal(req.getSession().getAttribute("lotId-wip").toString());
			}
			if(lotId_wip.floatValue() > 0){
				
				Lot l = getLotById(lotId_wip);
				
				req.getSession().setAttribute("lot", l);
				
				req.setAttribute("lot", l);
				
				
				UserDao ud = new UserDao();
				
				List<User> userCoordinatorList = ud.getUserListByRole(5);

				req.setAttribute("userCoordinatorList", userCoordinatorList);
				
				List<User> userBidderList = ud.getUserListByRole(2);
				
				//setLovValuesCategoryLevel(req, res);

				req.setAttribute("userBidderList", userBidderList);
				
				page ="lot-update.jsp";
				
			}

			
		}else if("saveUpdateLot".equals(action)){
			
			System.out.println("saveUpdateLot");

			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));
			lotId_wip = req.getParameter("lotId_wip")!=null ? new BigDecimal(req.getParameter("lotId_wip")) : new BigDecimal(0);

			BigDecimal lot_no = req.getParameter("lot_no")!=null ? new BigDecimal(req.getParameter("lot_no")) : new BigDecimal(0);
			BigDecimal lot_id = req.getParameter("lot_id")!=null ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			BigDecimal auction_id = req.getParameter("auction_id")!=null ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);;
			String lot_desc = req.getParameter("lot_desc")!=null ? req.getParameter("lot_desc") : "";
			BigDecimal assessment_value = !req.getParameter("assessment_value").equals("") ? new BigDecimal(req.getParameter("assessment_value")) : new BigDecimal(0);
			BigDecimal duties = !req.getParameter("duties").equals("") ? new BigDecimal(req.getParameter("duties")) : new BigDecimal(0);
			BigDecimal vat = !req.getParameter("vat").equals("") ? new BigDecimal(req.getParameter("vat")) : new BigDecimal(0);
			String unit = req.getParameter("unit")!=null ? req.getParameter("unit") : "";
			BigDecimal premium_rate = !req.getParameter("premium_rate").equals("") ? new BigDecimal(req.getParameter("premium_rate")) : new BigDecimal(0);
			Integer lot_type_id = req.getParameter("lot_type_id")!=null ? Integer.valueOf(req.getParameter("lot_type_id")) : 0;
			Integer active = req.getParameter("active")!=null ? Integer.valueOf(req.getParameter("active")) : 0;
			Integer unit_qty = req.getParameter("unit_qty")!=null ? Integer.valueOf(req.getParameter("unit_qty")) : 0;
			
			BigDecimal amount_bid = !req.getParameter("amount_bid").equals("") ? new BigDecimal(req.getParameter("amount_bid")) : new BigDecimal(0);
			BigDecimal amount_buy = !req.getParameter("amount_buy").equals("") ? new BigDecimal(req.getParameter("amount_buy")) : new BigDecimal(0);
			Integer action_taken = !req.getParameter("action_taken").equals("") ? Integer.valueOf(req.getParameter("action_taken")) : 0;
			Integer is_buy = !req.getParameter("is_buy").equals("") ? Integer.valueOf(req.getParameter("is_buy")) : 0;
			Integer is_bid= !req.getParameter("is_bid").equals("") ? Integer.valueOf(req.getParameter("is_bid")) : 0;
			BigDecimal buy_price = !req.getParameter("buy_price").equals("") ? new BigDecimal(req.getParameter("buy_price")) : new BigDecimal(0);
			Integer bidder_id = !req.getParameter("bidder_id").equals("") ? Integer.valueOf(req.getParameter("bidder_id")) : 0;
			Integer lot_increment_time = !req.getParameter("lot_increment_time").equals("") ? Integer.valueOf(req.getParameter("lot_increment_time")) : 0;
			String lot_name = req.getParameter("lot_name")!=null ? req.getParameter("lot_name") : "";
			

			Lot l = updateLotOnUpdate(
						lot_no,
						lot_id,
						auction_id,
						lot_desc,
						assessment_value,
						duties,
						vat,
						unit,
						premium_rate,
						lot_type_id,
						active,
						unit_qty,
						
						amount_bid,
						amount_buy,
						action_taken,
						is_buy,
						is_bid,
						buy_price,
						bidder_id,
						lot_increment_time,
						lot_name,
						
						user_id,
						lotId_wip
					);
			

			
			
			if(l!=null && l.getId()!=null){
				
				l = getLotById(lotId_wip);
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Lot updated succeessful.");
				
				req.getSession().setAttribute("lot", l);
				
				req.setAttribute("lot", l);
				
				page ="lot.jsp";
				
			}else{
				
				l = new Lot();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Lot update failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("lot", l);
				
				req.setAttribute("lot", l);
				
				page ="lot-list.jsp";
				
			}
			
			
		}else if("saveLotImage".equals(action)){
			
			System.out.println("saveLotImage");
			
			
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
			page= "lot.jsp";
		}
		
		
		req.getSession().setAttribute("auctionId_wip", auctionId_wip);
		
		req.setAttribute("auctionId_wip", auctionId_wip);
		
	
		
		System.out.println("Paramerters doLotManager - page : "+page);
		
		return page;
		
	}
	

	
	/*
	public Lot getLot(String lotId){
		
		Lot l = new Lot();

		LotDao ld = new LotDao();

		a = ld.getLot(lotId);
		
		return a;
		
	}	
	*/
	public Lot getLotById(BigDecimal id){
		
		Lot l = new Lot();

		LotDao ld = new LotDao();

		l = ld.getLotById(id);
		
		return l;
		
	}	
	
	public ArrayList<Lot> getLotListBySearch(String search) {
		return new LotDao().getLotListBySearch(search);
	}
	
	public ArrayList<Lot> getLotListJoinBiddingTransactionByUserId(Integer user_id){
		return new LotDao().getLotListJoinBiddingTransactionByUserId(user_id);
	}
	
	public Lot getLotByLotId(BigDecimal id){
		Lot l = new Lot();
		LotDao ld = new LotDao();
		l = ld.getLotByLotId(id);
		return l;
	}	
	
	public HashMap<BigDecimal, Lot> getLotHMByAuctionId(BigDecimal auction_id){
		
		HashMap<BigDecimal, Lot> lotHM = new HashMap<BigDecimal, Lot>();

		LotDao ld = new LotDao();

		lotHM = ld.getLotHMByAuctionId(auction_id);
		
		return lotHM;
		
	}

	public HashMap<BigDecimal, Lot> getLotHMByAuctionId_SetLotId(BigDecimal auction_id){
		
		HashMap<BigDecimal, Lot> lotHM = new HashMap<BigDecimal, Lot>();

		LotDao ld = new LotDao();

		lotHM = ld.getLotHMByAuctionId_SetLotId(auction_id);
		
		return lotHM;
		
	}
	
	/*
	public Lot getLotImageById(Integer id, String size){
		
		Lot l = new Lot();

		LotDao ld = new LotDao();

		a = ld.getLotImageById(id, size);
		
		return a;
		
	}	
	*/
	
	/*
	public int insertLotOnRegistration(String firstName, String lastName, String lotId, Integer mobileNo, String verification_email_key){
		
		int i = 0;
		
		LotDao ld = new LotDao();

		i = ld.insertLotOnRegistration(firstName, lastName, lotId, mobileNo, verification_email_key);
		
		return i;
		
	}
	*/
	public Lot insertLotOnCreate(
				BigDecimal lot_no,
				BigDecimal lot_id,
				BigDecimal auction_id,
				String lot_desc,
				BigDecimal assessment_value,
				BigDecimal duties,
				BigDecimal vat,
				String unit,
				BigDecimal premium_rate,
				Integer lot_type_id,
				Integer active,
				Integer unit_qty,
				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action_taken,
				Integer is_buy,
				Integer is_bid,
				BigDecimal buy_price,
				Integer bidder_id,
				Integer lot_increment_time,
				String lot_name,
				Integer user_id
			){
		
		Lot l = null;
		
		LotDao ld = new LotDao(req,res);

		l = ld.insertLotOnCreate(
				lot_no,
				lot_id,
				auction_id,
				lot_desc,
				assessment_value,
				duties,
				vat,
				unit,
				premium_rate,
				lot_type_id,
				active,
				unit_qty,
				amount_bid,
				amount_buy,
				action_taken,
				is_buy,
				is_bid,
				buy_price,
				bidder_id,
				lot_increment_time,
				user_id
				);
		
		return l;
		
	}
	
	public Lot insertLotOnUpload(
			BigDecimal lot_id,
			BigDecimal auction_id,
			BigDecimal lot_number,
			Integer is_available_lot,
			String lot_description,
			Integer lot_type_id,
			BigDecimal premium_rate,
			String unit,
			Integer unit_qty,
			BigDecimal vat,
			BigDecimal duties,
			BigDecimal assessment_value,
			String lot_name,
			Timestamp last_date_sync,
			Integer user_id
		){
	
	Lot l = null;
	
	LotDao ld = new LotDao(req,res);

	l = ld.insertLotOnUpload(
			lot_id,
			auction_id,
			lot_number,
			is_available_lot,
			lot_description,
			lot_type_id,
			premium_rate,
			unit,
			unit_qty,
			vat,
			duties,
			assessment_value,
			lot_name,
			last_date_sync,
				user_id
			);
	
	return l;
	
}
	
	
	
	public Lot updateLotOnUpdate(
				BigDecimal lot_no,
				BigDecimal lot_id,
				BigDecimal auction_id,
				String lot_desc,
				BigDecimal assessment_value,
				BigDecimal duties,
				BigDecimal vat,
				String unit,
				BigDecimal premium_rate,
				Integer lot_type_id,
				Integer active,
				Integer unit_qty,
				
				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action_taken,
				Integer is_buy,
				Integer is_bid,
				BigDecimal buy_price,
				Integer bidder_id,
				Integer lot_increment_time,
				String lot_name,
				
				Integer user_id,
				BigDecimal lotId_wip
			
			){
		
		Lot l = null;
		
		LotDao ld = new LotDao();
	
		l = ld.updateLotOnUpdate(
					lot_no,
					lot_id,
					auction_id,
					lot_desc,
					assessment_value,
					duties,
					vat,
					unit,
					premium_rate,
					lot_type_id,
					active,
					unit_qty,
					
					amount_bid,
					amount_buy,
					action_taken,
					is_buy,
					is_bid,
					buy_price,
					bidder_id,
					lot_increment_time,
					lot_name,
					
					user_id,
					lotId_wip
				);
		
		return l;
		
	}
	
	
	public Lot updateLotOnUpload(
			BigDecimal lot_id,
			BigDecimal auction_id,
			BigDecimal lot_number,
			Integer is_available_lot,
			String lot_description,
			Integer lot_type_id,
			BigDecimal premium_rate,
			String unit,
			Integer unit_qty,
			BigDecimal vat,
			BigDecimal duties,
			BigDecimal assessment_value,
			Timestamp last_date_sync,
			Integer user_id
		){
	
	Lot l = null;
	
	LotDao ld = new LotDao();

	l = ld.updateLotOnUpload(
			lot_id,
			auction_id,
			lot_number,
			is_available_lot,
			lot_description,
			lot_type_id,
			premium_rate,
			unit,
			unit_qty,
			vat,
			duties,
			assessment_value,
			last_date_sync,
				user_id
				
			);
	
	return l;
	
}
	
	
	public Lot updateLotTotalsOnUpload(
			BigDecimal lot_id,
			BigDecimal srp,
			BigDecimal target_price,
			BigDecimal reserve_price,
			BigDecimal assess_value,
			BigDecimal weight
		){
	
	Lot l = null;
	
	LotDao ld = new LotDao();

	l = ld.updateLotTotalsOnUpload(
			lot_id,
			srp,
			target_price,
			reserve_price,
			assess_value,
			weight
				
			);
	
	return l;
	
}
	
	public List<Lot> getLotList(){
		
		List<Lot> lList = new ArrayList<Lot>();

		LotDao ld = new LotDao();

		lList = ld.getLotList();
		
		return lList;
		
	}
	
	public ArrayList<Lot> getLotListByAuctionId(BigDecimal auction_id){
		
		ArrayList<Lot> lList = new ArrayList<Lot>();

		LotDao ld = new LotDao();

		lList = ld.getLotListByAuctionId(auction_id);
		
		return lList;
		
	}
	
	

	
	public List<Lot> getLotListByTypeAndActive(Integer lotType){
		
		List<Lot> lList = new ArrayList<Lot>();

		LotDao ld = new LotDao(req,res);

		lList = ld.getLotListByTypeAndActive(lotType);
		
		return lList;
		
	}
	
	public Lot getLotByAuctionIdAndLotNo(BigDecimal auction_id, BigDecimal lot_no) {
		return new LotDao().getLotByAuctionIdAndLotNo(auction_id, lot_no);
	}
	
	public Lot applyLotRules(Lot l) {
		AuctionManager aMngr = new AuctionManager(req,res);
		AuctionRangeManager arMngr = new AuctionRangeManager(req,res);
		LotManager lMngr = new LotManager(req,res);
		ItemManager iMngr = new ItemManager(req,res);
		LotRangeManager lrMngr = new LotRangeManager();
		
		
		Auction a = aMngr.getAuctionByAuctionId(l.getAuction_id());
		BigDecimal increment_amount = BigDecimal.ZERO;
		BigDecimal base_amount = BigDecimal.ZERO;
		
		
		if(l.getBid_count()==0) {
			//No bids on lot yet. Determine the correct starting bid amount
			if(l.getIs_bid()==1) {
				base_amount = l.getAmount_bid();
			} else if(l.getIs_buy()==1) {
				base_amount =l.getAmount_buy();
			}
			
			//Check for stupidity where they set the Amount on bid or buy 
			// if less than required starting bid!
			if(l.getStarting_bid_amount().compareTo(base_amount)>1) {
				base_amount = l.getStarting_bid_amount();
			}
			
			l.setAmount_bid_next(base_amount);
		} else {
			//There is more than one bid on lot. User bid increment rules

			//Check if there is lot level bid increment else use auction level value
			increment_amount = lrMngr.getIncrementAmountByLotId(l.getLot_id(), l.getAmount_bid());
			if(increment_amount.equals(BigDecimal.ZERO)) {
				System.out.println("Using auction bid increment for lot");
				increment_amount = arMngr.getIncrementAmountByAuctionId(a.getAuction_id(), l.getAmount_bid());
			}
			BigDecimal amount_bid_next=  increment_amount.add(l.getAmount_bid());
			
			l.setAmount_bid_next(amount_bid_next);
		}
		
		
		if(l.getEnd_date_time() == null) {
			l.setEnd_date_time(a.getEnd_date_time());
		}
		
		//check if the end time is expired
		if(l.getEnd_date_time().before(new Timestamp(System.currentTimeMillis()))) {
			l.setIs_bid(0);
			l.setIs_buy(0);
		} 
		
		return l;
	}
	


	public int updateLotSetStartingBidAmount(
			BigDecimal auction_id,
			BigDecimal starting_bid_amount,
			Integer user_id
		){
	
	LotDao ld = new LotDao();

	int i = ld.updateLotSetStartingBidAmount(
			auction_id,
			starting_bid_amount,
			user_id
			);
	
	return i;
	
	}
	
	public int updateLotSetIsBuy(
			BigDecimal auction_id,
			Integer is_buy,
			Integer user_id
		){
	
	LotDao ld = new LotDao();

	int i = ld.updateLotSetIsBuy(
			auction_id,
			is_buy,
			user_id
			);
	
	return i;
	
	}
	
	public int updateLotSetLotTotals(
			BigDecimal lot_id,
			BigDecimal reserve_price ,
			BigDecimal srp, 
			BigDecimal target_price, 
			BigDecimal assess_value,
			String bid_qualifier_price,
			Integer user_id
		){
	
	LotDao ld = new LotDao();

	int i = ld.updateLotSetLotTotals(
			lot_id,
			reserve_price ,
			srp, 
			target_price, 
			 assess_value,
			 bid_qualifier_price,
			user_id
			);
	
	return i;
	
	}
	
	
}

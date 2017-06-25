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

import hmr.com.bean.BiddingTransaction;
import hmr.com.bean.Item;
import hmr.com.bean.Lot;
import hmr.com.bean.Lov;
import hmr.com.bean.User;
import hmr.com.dao.BiddingTransactionDao;
import hmr.com.dao.LotDao;
import hmr.com.dao.UserDao;

public class BiddingTransactionManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public BiddingTransactionManager(){}
	/*
	public BiddingTransactionManager(HttpServletRequest req, HttpServletResponse res, String emailAdd){
		this.req = req;
		this.res = res;
	}
	*/
	public BiddingTransactionManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doBiddingTransactionManager(File file_small, File file, String action, BigDecimal biddingTransactionId_wip){
		String page = "index.jsp";
		
		if(action.equals("saveBiddingTransactionImage")){
			
			BiddingTransaction bt = new BiddingTransaction();
			
			//bt = updateBiddingTransactionImage(file_small, file,1,biddingTransactionId_wip);

			if(bt!=null){
				bt = getBiddingTransactionById(bt.getId());
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "BiddingTransaction images updated succeessful.");
			}else{
				bt = getBiddingTransactionById(biddingTransactionId_wip);
			}
			
			
			page = "bidding-transaction.jsp";
		}
		
		return page;
	}
	
	public String doBiddingTransactionManager(){
		String page = null;
		
		
		String action = req.getParameter("action")!=null ? req.getParameter("action") : (String)req.getSession().getAttribute("action");
		//String file_name = "";
		Integer user_id = 0;
		BigDecimal biddingTransactionId_wip = new BigDecimal(0);
		//String manager = "";
		//String userId = "";
		
		System.out.println("Paramerters doBiddingTransactionManager - start");
		System.out.println("action : "+action);
		try{
			System.out.println("biddingTransactionId_wip Session : "+Integer.valueOf(req.getSession().getAttribute("biddingTransactionId_wip").toString()) );
		}catch(Exception exx){}
		
		System.out.println("Paramerters doBiddingTransactionManager - end");
		System.out.println("");
		
		
		if("biddingTransactionList".equals(action)){
			
			System.out.println("biddingTransactionList");

			List<BiddingTransaction> btList = getBiddingTransactionList();
			
			req.setAttribute("biddingTransactionList", btList);

			page ="bidding-transaction-list.jsp";
		
		}else if("createBiddingTransaction".equals(action)){
			
			System.out.println("createBiddingTransaction");
			
			UserDao ud = new UserDao();
			
			List<User> userCoordinatorList = ud.getUserListByRole(5);

			req.setAttribute("userCoordinatorList", userCoordinatorList);

			
			List<Lot> lotList = new ArrayList<Lot>();

			LotDao ld = new LotDao(req,res);
			
			lotList = ld.getLotList();
			
			req.setAttribute("lotList", lotList);
			
			
			ud = new UserDao();
			
			List<User> userBidderList = ud.getUserListByRole(2);
		
			req.setAttribute("userBidderList", userBidderList);

			page ="bidding-transaction-create.jsp";
		
		}else if("saveBiddingTransaction".equals(action)){
			
			System.out.println("saveBiddingTransaction");

			//String userId = req.getParameter("userId")!=null ? req.getParameter("userId") : "";
			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));

			BigDecimal lot_id = !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			BigDecimal amount_bid = !req.getParameter("amount_bid").equals("") ? new BigDecimal(req.getParameter("amount_bid")) : new BigDecimal(0);
			BigDecimal amount_buy = !req.getParameter("amount_buy").equals("") ? new BigDecimal(req.getParameter("amount_buy")) : new BigDecimal(0);
			Integer action_taken = req.getParameter("action_taken")!=null && !req.getParameter("action_taken").equals("") ? Integer.valueOf(req.getParameter("action_taken")) : 0;
			Integer status = req.getParameter("status")!=null && !req.getParameter("status").equals("") ? Integer.valueOf(req.getParameter("status")) : 0;
			Integer user_id_bidder =  req.getParameter("user_id_bidder")!=null && !req.getParameter("user_id_bidder").equals("") ? Integer.valueOf(req.getParameter("user_id_bidder")) : 0;
			
			BiddingTransaction bt = insertBiddingTransactionOnCreate(
					lot_id,
					amount_bid,
					amount_buy,
					action_taken,
					status,
					user_id_bidder,
					user_id
					);
			
			if(bt!=null && bt.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "BiddingTransaction created succeessful.");

				bt = getBiddingTransactionById(bt.getId());
				
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
				
				req.getSession().setAttribute("biddingTransaction", bt);
				
				req.setAttribute("biddingTransaction", bt);
				
				page ="bidding-transaction.jsp";
				
				
				
				
			}else if("biddingTransactionBidDetails".equals(action)){
				
				page ="bidding-transaction-bid-details.jsp";
			}else{
				/*
				bt = new BiddingTransaction();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "BiddingTransaction created failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("biddingTransaction", bt);
				
				req.setAttribute("biddingTransaction", bt);
				
				page ="biddingTransaction-list.jsp";
				*/
			}

			

		
		}else if("viewBiddingTransaction".equals(action)){
			
			System.out.println("viewBiddingTransaction");

			biddingTransactionId_wip = !req.getParameter("biddingTransactionId_wip").equals("") ? new BigDecimal(req.getParameter("biddingTransactionId_wip")) : new BigDecimal(0);
			if(biddingTransactionId_wip.floatValue() > 0){
				
				//BiddingTransaction bt = getBiddingTransactionById(biddingTransactionId_wip);
				BiddingTransaction bt = getBiddingTransactionByBiddingTransactionId(biddingTransactionId_wip);
				
				setLovValuesCategoryLevel(req,res);
				setLovValuesCurrency(req, res);
				
				req.getSession().setAttribute("biddingTransaction", bt);
				
				req.setAttribute("biddingTransaction", bt);


				
				UserDao ud = new UserDao();
				
				List<User> userBidderList = ud.getUserListByRole(2);
			
				req.setAttribute("userBidderList", userBidderList);
				
				
				BiddingTransactionDao btd = new BiddingTransactionDao();
				
				List<BiddingTransaction> biddingTransactionList = btd.getBiddingTransactionList();
				
				req.setAttribute("biddingTransactionList", biddingTransactionList);

				
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
				
				
				page ="bidding-transaction.jsp";
				
			}

		}else if("updateBiddingTransaction".equals(action)){
			
			System.out.println("updateBiddingTransaction");

			biddingTransactionId_wip = req.getParameter("biddingTransactionId_wip")!=null && !req.getParameter("biddingTransactionId_wip").equals("") ? new BigDecimal(req.getParameter("biddingTransactionId_wip")): new BigDecimal(req.getSession().getAttribute("biddingTransactionId-wip").toString());
			
			System.out.println("updateBiddingTransaction : biddingTransactionId_wip "+biddingTransactionId_wip);
			if(biddingTransactionId_wip == new BigDecimal(0)){
				biddingTransactionId_wip = new BigDecimal(req.getSession().getAttribute("biddingTransactionId-wip").toString());
			}
			if(biddingTransactionId_wip.floatValue() > 0){
				
				//BiddingTransaction bt = getBiddingTransactionById(biddingTransactionId_wip);
				BiddingTransaction bt = getBiddingTransactionByBiddingTransactionId(biddingTransactionId_wip);

				req.getSession().setAttribute("biddingTransaction", bt);
				
				req.setAttribute("biddingTransaction", bt);

				UserDao ud = new UserDao();
				
				List<User> userCoordinatorList = ud.getUserListByRole(5);

				req.setAttribute("userCoordinatorList", userCoordinatorList);
				
				page ="bidding-transaction-update.jsp";
				
			}

			
		}else if("saveUpdateBiddingTransaction".equals(action)){
			
			System.out.println("saveUpdateBiddingTransaction");

			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));
			biddingTransactionId_wip = !req.getParameter("biddingTransactionId_wip").equals("") ? new BigDecimal(req.getParameter("biddingTransactionId_wip")) : new BigDecimal(0);

			BigDecimal lot_id = !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			BigDecimal amount_bid = !req.getParameter("amount_bid").equals("") ? new BigDecimal(req.getParameter("amount_bid")) : new BigDecimal(0);
			BigDecimal amount_buy = !req.getParameter("amount_buy").equals("") ? new BigDecimal(req.getParameter("amount_buy")) : new BigDecimal(0);
			Integer action_taken = req.getParameter("action_taken")!=null && !req.getParameter("action_taken").equals("") ? Integer.valueOf(req.getParameter("action_taken")) : 0;
			Integer status = req.getParameter("status")!=null && !req.getParameter("status").equals("") ? Integer.valueOf(req.getParameter("status")) : 0;
			Integer user_id_bidder =  req.getParameter("user_id_bidder")!=null && !req.getParameter("user_id_bidder").equals("") ? Integer.valueOf(req.getParameter("user_id_bidder")) : 0;
			
			
			BiddingTransaction bt = updateBiddingTransactionOnUpdate(
					 lot_id,
					 amount_bid,
					 amount_buy,
					 action_taken,
					 status,
					 user_id_bidder,
					user_id,
					biddingTransactionId_wip
					);
			

			
			
			if(bt!=null && bt.getId()!=null){
				
				//bt = getBiddingTransactionById(biddingTransactionId_wip);
				bt = getBiddingTransactionByBiddingTransactionId(biddingTransactionId_wip);
				
				setLovValuesCategoryLevel(req,res);
				setLovValuesCurrency(req, res);
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "BiddingTransaction updated succeessful.");
				
				req.getSession().setAttribute("biddingTransaction", bt);
				
				req.setAttribute("biddingTransaction", bt);
				
		
				UserDao ud = new UserDao();
				
				List<User> userBidderList = ud.getUserListByRole(2);
			
				req.setAttribute("userBidderList", userBidderList);
				
				
				BiddingTransactionDao btd = new BiddingTransactionDao();
				
				List<BiddingTransaction> biddingTransactionList = btd.getBiddingTransactionList();
				
				req.setAttribute("biddingTransactionList", biddingTransactionList);

				
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
				
				
				page ="bidding-transaction.jsp";
				
			}else{
				
				bt = new BiddingTransaction();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "BiddingTransaction update failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("biddingTransaction", bt);
				
				req.setAttribute("biddingTransaction", bt);
				
				page ="bidding-transaction-list.jsp";
				
			}
			
			
		}else if("saveBiddingTransactionImage".equals(action)){
			
			System.out.println("saveBiddingTransactionImage");
			
			
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
			setLovBiddingTransactionUserValues(req,res);
			
			page= "bidding-transaction.jsp";
		}
		
		
		req.getSession().setAttribute("biddingTransactionId_wip", biddingTransactionId_wip);
		
		req.setAttribute("biddingTransactionId_wip", biddingTransactionId_wip);

		
		System.out.println("Paramerters doBiddingTransactionManager - page : "+page);
		
		return page;
		
	}
	

	
	/*
	public BiddingTransaction getBiddingTransaction(String biddingTransactionId){
		
		BiddingTransaction bt = new BiddingTransaction();

		BiddingTransactionDao btd = new BiddingTransactionDao();

		bt = btd.getBiddingTransaction(biddingTransactionId);
		
		return bt;
		
	}	
	*/
	/*
	public BiddingTransaction getBiddingTransactionById(Integer id){
		
		BiddingTransaction bt = new BiddingTransaction();

		BiddingTransactionDao btd = new BiddingTransactionDao();

		bt = btd.getBiddingTransactionById(id);
		
		return bt;
		
	}
*/
	public BiddingTransaction getBiddingTransactionById(BigDecimal id){
		
		BiddingTransaction bt = new BiddingTransaction();

		BiddingTransactionDao btd = new BiddingTransactionDao();

		bt = btd.getBiddingTransactionById(id);
		
		return bt;
		
	}
	
	
	public BiddingTransaction getBiddingTransactionByBiddingTransactionId(BigDecimal biddingTransaction_id){
		
		BiddingTransaction bt = new BiddingTransaction();

		BiddingTransactionDao btd = new BiddingTransactionDao();

		bt = btd.getBiddingTransactionByBiddingTransactionId(biddingTransaction_id);
		
		return bt;
		
	}	
/*
	public BiddingTransaction getBiddingTransactionImageById(Integer id, String size){
		
		BiddingTransaction bt = new BiddingTransaction();

		BiddingTransactionDao btd = new BiddingTransactionDao();

		bt = btd.getBiddingTransactionImageById(id, size);
		
		return bt;
		
	}	
	*/
	
	/*
	public int insertBiddingTransactionOnRegistration(String firstName, String lastName, String biddingTransactionId, Integer mobileNo, String verification_email_key){
		
		int i = 0;
		
		BiddingTransactionDao btd = new BiddingTransactionDao();

		i = btd.insertBiddingTransactionOnRegistration(firstName, lastName, biddingTransactionId, mobileNo, verification_email_key);
		
		return i;
		
	}
	*/
	public BiddingTransaction insertBiddingTransactionOnCreate(
				BigDecimal lot_id,
				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action,
				Integer status,
				Integer user_id_bidder,
				Integer user_id
			){
		
		BiddingTransaction bt = null;
		
		BiddingTransactionDao btd = new BiddingTransactionDao(req,res);

		bt = btd.insertBiddingTransactionOnCreate(
				 lot_id,
				 amount_bid,
				 amount_buy,
				 action,
				 status,
				 user_id_bidder,
				 user_id
				);
		
		return bt;
		
	}
	
	/*
	public BiddingTransaction insertBiddingTransactionOnUpload(
			BigDecimal biddingTransaction_id,
			BigDecimal biddingTransaction_no,
			Timestamp biddingTransaction_date,
			String location,
			BigDecimal default_premium,
			String biddingTransaction_name,
			Timestamp last_date_sync,
			Integer user_id
		){
	
	BiddingTransaction bt = null;
	
	BiddingTransactionDao btd = new BiddingTransactionDao(req,res);

	bt = btd.insertBiddingTransactionOnUpload(
			biddingTransaction_id,
			biddingTransaction_no,
			biddingTransaction_date,
			location,
			default_premium,
			biddingTransaction_name,
			last_date_sync,
			user_id
			);
	
	return bt;
	
}
	*/
	public BiddingTransaction updateBiddingTransactionOnUpdate(
				BigDecimal lot_id,
				BigDecimal amount_bid,
				BigDecimal amount_buy,
				Integer action,
				Integer status,
				Integer user_id_bidder,
				Integer user_id,
				BigDecimal biddingTransactionId_wip
			
			){
		
		BiddingTransaction bt = null;
		
		BiddingTransactionDao btd = new BiddingTransactionDao();
	
		bt = btd.updateBiddingTransactionOnUpdate(
					 lot_id,
					 amount_bid,
					 amount_buy,
					 action,
					 status,
					 user_id_bidder,
					user_id,
					biddingTransactionId_wip
				);
		
		return bt;
		
	}
	
	/*
	public BiddingTransaction updateBiddingTransactionImage(
			File file_small,
			File file,
			Integer user_id,
			BigDecimal biddingTransactionId_wip
		){
	
	BiddingTransaction bt = null;
	
	BiddingTransactionDao btd = new BiddingTransactionDao();

	bt = btd.updateBiddingTransactionImage(
				file_small,
				file,
				user_id,
				biddingTransactionId_wip
			);
	
	return bt;
	
}
	*/

	
	public List<BiddingTransaction> getBiddingTransactionList(){
		
		List<BiddingTransaction> btList = new ArrayList<BiddingTransaction>();

		BiddingTransactionDao btd = new BiddingTransactionDao();

		btList = btd.getBiddingTransactionList();
		
		return btList;
		
	}
	
	/*
	public List<BiddingTransaction> getBiddingTransactionListByTypeAndActive(Integer biddingTransactionType){
		
		List<BiddingTransaction> btList = new ArrayList<BiddingTransaction>();

		BiddingTransactionDao btd = new BiddingTransactionDao(req,res);

		btList = btd.getBiddingTransactionListByTypeAndActive(biddingTransactionType);
		
		return btList;
		
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
	
	private void setLovBiddingTransactionUserValues(HttpServletRequest req, HttpServletResponse res){
		

		LovManager lovMngr = new LovManager(req, res);

		HashMap<Integer,Lov> biddingTransactionUserStatusLovHM = null;
		List<Lov> biddingTransactionUserStatusLovList = null;
	
		try {
			

			System.out.println("AUCTION-USER-STATUS-HM : " + req.getSession().getAttribute("AUCTION-USER-STATUS-HM"));
			System.out.println("AUCTION-USER-STATUS-LIST : " + req.getSession().getAttribute("AUCTION-USER-STATUS-LIST"));

			
			if(req.getSession().getAttribute("AUCTION-USER-STATUS-HM")==null ){
				biddingTransactionUserStatusLovHM = lovMngr.getLovHM("AUCTION-USER-STATUS");
				req.getSession().setAttribute("AUCTION-USER-STATUS-HM", biddingTransactionUserStatusLovHM);
			}else{
				biddingTransactionUserStatusLovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("AUCTION-USER-STATUS-HM");
				req.setAttribute("AUCTION-USER-STATUS-HM", biddingTransactionUserStatusLovHM);
			}
			
			if(req.getSession().getAttribute("AUCTION-USER-STATUS-LIST")==null ){
				biddingTransactionUserStatusLovList = lovMngr.getLovList("AUCTION-USER-STATUS");
				req.getSession().setAttribute("AUCTION-USER-STATUS-LIST", biddingTransactionUserStatusLovList);
			}else{
				biddingTransactionUserStatusLovList = (List<Lov>)req.getSession().getAttribute("AUCTION-USER-STATUS-LIST");
				req.setAttribute("AUCTION-USER-STATUS-LIST", biddingTransactionUserStatusLovList);
			}


			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}

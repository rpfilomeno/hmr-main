package hmr.com.manager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import hmr.com.bean.Lov;
import hmr.com.bean.User;
import hmr.com.bean.Auction;
import hmr.com.bean.AuctionUser;
import hmr.com.dao.LovDao;
import hmr.com.dao.UserDao;
import hmr.com.dao.AuctionDao;
import hmr.com.dao.AuctionUserDao;
import hmr.com.util.EmailUtil;
import hmr.com.util.StringUtil;

public class AuctionUserManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public AuctionUserManager(){}
	/*
	public AuctionUserManager(HttpServletRequest req, HttpServletResponse res, String emailAdd){
		this.req = req;
		this.res = res;
	}
	*/
	public AuctionUserManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doAuctionUserManager(){
		String page = null;
		String action = req.getParameter("action")!=null ? req.getParameter("action") : "";
		BigDecimal auctionId_wip = req.getParameter("auctionId_wip")!=null && !"".equals(req.getParameter("auctionId_wip"))  ? new BigDecimal(req.getParameter("auctionId_wip")): new BigDecimal(0);
		Integer auctionUserId_wip = req.getParameter("auctionUserId_wip")!=null ? Integer.valueOf(req.getParameter("auctionUserId_wip")): 0;
		Integer user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : 0;
		

		
		System.out.println("Paramerters doAuctionUserManager - start");
		System.out.println("action : "+action);
		System.out.println("Paramerters doAuctionUserManager - end");
		System.out.println("");
		
		
		if("auctionUserList".equals(action)){
			
			System.out.println("auctionUserList");

			List<AuctionUser> auList = getAuctionUserList();
			
			req.setAttribute("auctionUserList", auList);

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

			setLovAuctionUserValues(req,res);

			page ="auction-user-list.jsp";
		
		}else if("createAuctionUser".equals(action)){
			
			System.out.println("createAuctionUser");
			
			UserDao ud = new UserDao();
			
			List<User> userBidderList = ud.getUserListByRole(2);
		
			req.setAttribute("userBidderList", userBidderList);
			
			
			AuctionDao ad = new AuctionDao();
			
			List<Auction> auctionList = ad.getAuctionList();
			
			req.setAttribute("auctionList", auctionList);

			page ="auction-user-create.jsp";
		
		}else if("saveAuctionUser".equals(action)){
			
			System.out.println("saveAuctionUser");

			

			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			Integer bidder_id =!req.getParameter("bidder_id").equals("") ? Integer.valueOf(req.getParameter("bidder_id")) : 0;
			Integer status = !req.getParameter("userStatus").equals("") ? Integer.valueOf(req.getParameter("userStatus")) : 0;
			Integer active = !req.getParameter("active").equals("") ? Integer.valueOf(req.getParameter("active")) : 0;
			String company_id_no = !req.getParameter("company_id_no").equals("") ? req.getParameter("company_id_no") : "";

			AuctionUser au = insertAuctionUserOnCreate(
					auction_id,
					bidder_id,
					status,
					active,
					user_id,
					company_id_no
					);
			
			if(au!=null && au.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Auction User created succeessful.");
				
				req.getSession().setAttribute("auctionUser", au);
				
				req.setAttribute("auctionUser", au);
				
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
				
				setLovAuctionUserValues(req,res);
				
				if(au!=null && au.getAuction_id()!=null){
					
					AuctionManager aMngr = new AuctionManager();
					Auction a = aMngr.getAuctionByAuctionId(au.getAuction_id());
					req.setAttribute("auction", a);
				}
				
				page ="auction-user.jsp";
				
			}else{
				
				au = new AuctionUser();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Auction User created failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("auctionUser", au);
				
				req.setAttribute("auction-user", au);
				
				page ="auction-user-list.jsp";
				
			}

		
		}else if("viewAuctionUser".equals(action)){
			
			System.out.println("viewAuctionUser");

			
			if(auctionUserId_wip > 0){
				
				AuctionUser au = getAuctionUserById(auctionUserId_wip);
				
				req.getSession().setAttribute("auctionUser", au);
				
				req.setAttribute("auctionUser", au);
				
				if(au!=null && au.getAuction_id()!=null){
					
					AuctionManager aMngr = new AuctionManager();
					Auction a = aMngr.getAuctionByAuctionId(au.getAuction_id());
					req.setAttribute("auction", a);
				}
				
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
				
				setLovAuctionUserValues(req,res);
				
				page ="auction-user.jsp";
				
			}

		}else if("updateAuctionUser".equals(action)){


			
			
			System.out.println("updateAuctionUser "+auctionUserId_wip);
			
			if(auctionUserId_wip > 0){
				
				AuctionUser au = getAuctionUserById(auctionUserId_wip);
				
				req.getSession().setAttribute("auctionUser", au);
				
				req.setAttribute("auctionUser", au);
				
				
				UserDao ud = new UserDao();
				
				List<User> userBidderList = ud.getUserListByRole(2);
			
				req.setAttribute("userBidderList", userBidderList);
				
				
				AuctionDao ad = new AuctionDao();
				
				List<Auction> auctionList = ad.getAuctionList();
				
				req.setAttribute("auctionList", auctionList);
				
				page ="auction-user-update.jsp";
				
			}

			
		}else if("saveUpdateAuctionUser".equals(action)){
			
			System.out.println("saveUpdateAuctionUser");

			
			
			
			
			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			Integer bidder_id =!req.getParameter("bidder_id").equals("") ? Integer.valueOf(req.getParameter("bidder_id")) : 0;
			Integer status = !req.getParameter("userStatus").equals("") ? Integer.valueOf(req.getParameter("userStatus")) : 0;
			Integer active = !req.getParameter("active").equals("") ? Integer.valueOf(req.getParameter("active")) : 0;
			String company_id_no = (String) (!req.getParameter("companyIdNo").equals("") ?  req.getParameter("companyIdNo") : "");
			
			System.out.println("auctionUserId_wip "+auctionUserId_wip);
			
			
			AuctionUser au = updateAuctionUserOnUpdate(
					auction_id,
					bidder_id,
					status,
					active,
					user_id,
					new BigDecimal(auctionUserId_wip),
					company_id_no
					);
			

			
			
			if(au!=null && au.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Auction User updated succeessful.");
				
				req.getSession().setAttribute("auctionUser", au);
				
				req.setAttribute("auctionUser", au);

				
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
				
				setLovAuctionUserValues(req,res);
				
				if(au!=null && au.getAuction_id()!=null){
					
					AuctionManager aMngr = new AuctionManager();
					Auction a = aMngr.getAuctionByAuctionId(au.getAuction_id());
					req.setAttribute("auction", a);
				}				
				
				page ="auction-user.jsp";
				
			}else{
				
				au = new AuctionUser();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Auction User update failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("auctionUser", au);
				
				req.setAttribute("auctionUser", au);
				
				page ="auction-user-list.jsp";
				
			}
			
			
		}
		
		
		req.getSession().setAttribute("auctionId_wip", auctionId_wip);
		req.setAttribute("auctionId_wip", auctionId_wip);
		
		
	
		
		System.out.println("Paramerters doAuctionUserManager - page : "+page);
		
		return page;
		
	}
	

	
	

	
	public AuctionUser getAuctionUserById(Integer id){
		
		AuctionUser u = new AuctionUser();

		AuctionUserDao ud = new AuctionUserDao();

		u = ud.getAuctionUserById(id);
		
		return u;
		
	}	
	
	
	
	
	public AuctionUser insertAuctionUserOnCreate(
			BigDecimal auction_id,
			Integer bidder_id,
			Integer status,
			Integer active,
			Integer user_id,
			String company_id_no
			){
		
		AuctionUser u = null;
		
		AuctionUserDao ud = new AuctionUserDao();

		u = ud.insertAuctionUserOnCreate(
				
				auction_id,
				bidder_id,
				status,
				active,
				user_id,
				company_id_no
				
				);
		
		return u;
		
	}
	

	
	
	public AuctionUser updateAuctionUserOnUpdate(
			BigDecimal auction_id,
			Integer bidder_id,
			Integer status,
			Integer active,
			Integer user_id,
			BigDecimal auctionUserId_wip,
			String company_id_no
			){
		
		AuctionUser u = null;
		
		AuctionUserDao ud = new AuctionUserDao();
	
		u = ud.updateAuctionUserOnUpdate(
				auction_id,
				bidder_id,
				status,
				active,
				user_id,
				auctionUserId_wip,
				company_id_no
				);
		
		return u;
		
	}
	


	public List<AuctionUser> getAuctionUserList(){
		
		List<AuctionUser> uList = new ArrayList<AuctionUser>();

		AuctionUserDao ud = new AuctionUserDao();

		uList = ud.getAuctionUserList();
		
		return uList;
		
	}
	
	
	public List<AuctionUser> auctionUserListByRole(Integer role){
		
		List<AuctionUser> uList = new ArrayList<AuctionUser>();

		AuctionUserDao ud = new AuctionUserDao();

		uList = ud.getAuctionUserList();
		
		return uList;
		
	}
	
	/*
	public HashMap<Integer,AuctionUser> getAuctionUserHMByRole(Integer role) throws SQLException{

		AuctionUserDao ud = new AuctionUserDao();

		HashMap<Integer,AuctionUser> uHM = ud.getAuctionUserHMByRole(role);
		
		return uHM;
		
	}
	*/
	/*
	public List<AuctionUser> getAuctionUserListByRole(Integer role) throws SQLException{

		AuctionUserDao ud = new AuctionUserDao();

		List<AuctionUser> uList =ud.getAuctionUserListByRole(role);
		
		return uList;
		
	}
	
	*/

	
	

	

	
	
	
	
	
	/*
	public HashMap<Integer,AuctionUserLogin> getAuctionUserLoginHM(){

		AuctionUserLoginDao uld = new AuctionUserLoginDao();

		HashMap<Integer,AuctionUserLogin> hm = uld.getAuctionUserLoginHM();
		
		return hm;
		
	}
	*/
	public List<AuctionUser> getAuctionUserListByAuctionId(BigDecimal auction_id){
		
		List<AuctionUser> auList = new ArrayList<AuctionUser>();

		AuctionUserDao aud = new AuctionUserDao();

		auList = aud.getAuctionUserListByAuctionId(auction_id);
		
		return auList;
		
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
	
	
	public boolean isUserApprovedOnAuction(BigDecimal user_id, BigDecimal auction_id) {
		if(new AuctionUserDao().getAuctionUserByUserIdAndAuctionIdAndStatus(user_id, auction_id, 25) != null) return true;
		return false;
	}
	
}

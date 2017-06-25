package hmr.com.manager;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.bean.Auction;
import hmr.com.bean.AuctionRange;
import hmr.com.bean.User;
import hmr.com.dao.AuctionDao;
import hmr.com.dao.AuctionRangeDao;
import hmr.com.dao.UserDao;

public class AuctionRangeManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public AuctionRangeManager(){}

	public AuctionRangeManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}

	public String doAuctionRangeManager(){
		String page = null;

		String action = req.getParameter("action")!=null ? req.getParameter("action") : (String)req.getSession().getAttribute("action");
		//String file_name = "";
		Integer user_id = 0;
		BigDecimal auctionRangeId_wip = new BigDecimal(0);
		//String manager = "";
		//String userId = "";
		
		BigDecimal auctionId_wip = new BigDecimal(0);
		
		try{
			auctionId_wip = req.getParameter("auctionId_wip")!=null && !"".equals(req.getParameter("auctionId_wip"))  ? new BigDecimal(req.getParameter("auctionId_wip")): new BigDecimal(0);
		}catch(Exception exx){}
		
		System.out.println("Paramerters doAuctionRangeManager - start");
		System.out.println("action : "+action);
		try{
			System.out.println("auctionRangeId_wip Session : "+Integer.valueOf(req.getSession().getAttribute("auctionRangeId-wip").toString()) );
		}catch(Exception exx){}
		
		System.out.println("Paramerters doAuctionRangeManager - end");
		System.out.println("");
		
		
		if("auctionRangeList".equals(action)){
			
			System.out.println("auctionRangeList");
			
			BigDecimal auction_id = req.getParameter("auction_id")!=null && !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(req.getAttribute("auctionId-wip").toString());

			if(auction_id!=null && auction_id.doubleValue() > 0){
				
			}else{
				auction_id = new BigDecimal(req.getParameter("auctionId_wip"));
				
				AuctionDao ad = new AuctionDao();
				
				Auction a = ad.getAuctionByAuctionId(auction_id);
				
				req.getSession().setAttribute("auction", a);
				
				req.setAttribute("auction", a);
			}
			
			System.out.println("auctionRangeList auction_id "+auction_id);
			
			List<AuctionRange> aList = getAuctionRangeListByAuctionId(auction_id);
			
			req.setAttribute("auctionRangeList", aList);
			
			

			

			page ="auction-range-list.jsp";
		
		}else if("createAuctionRange".equals(action)){
			
			System.out.println("createAuctionRange");
			
			UserDao ud = new UserDao();
			
			List<User> userCoordinatorList = ud.getUserListByRole(5);

			req.setAttribute("userCoordinatorList", userCoordinatorList);

			page ="auction-range-create.jsp";
		
		}else if("saveAuctionRange".equals(action)){
			
			System.out.println("saveAuctionRange");

			//String userId = req.getParameter("userId")!=null ? req.getParameter("userId") : "";
			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));

			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			BigDecimal range_start = !req.getParameter("range_start").equals("") ? new BigDecimal(req.getParameter("range_start")) : new BigDecimal(0);
			BigDecimal range_end = !req.getParameter("range_end").equals("") ? new BigDecimal(req.getParameter("range_end")) : new BigDecimal(0);
			BigDecimal increment_amount = !req.getParameter("increment_amount").equals("") ? new BigDecimal(req.getParameter("increment_amount")) : new BigDecimal(0);
			
			
			AuctionRange ar = insertAuctionRangeOnCreate(
						auction_id,
						range_start,
						range_end,
						increment_amount,
						user_id
					);
			
			if(ar!=null && ar.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "AuctionRange created succeessful.");

				ar = getAuctionRangeById(ar.getId());
				
			
				req.getSession().setAttribute("auctionRange", ar);
				
				req.setAttribute("auctionRange", ar);
				
				page ="auction-range.jsp";
				
				
				
				
			}else if("auctionBidDetails".equals(action)){
				
				page ="auction-bid-details.jsp";
			}else{
				/*
				ar = new AuctionRange();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "AuctionRange created failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("auction", ar);
				
				req.setAttribute("auction", ar);
				
				page ="auction-list.jsp";
				*/
			}

			

		
		}else if("viewAuctionRange".equals(action)){
			
			System.out.println("viewAuctionRange");

			auctionRangeId_wip = !req.getParameter("auctionRangeId_wip").equals("") ? new BigDecimal(req.getParameter("auctionRangeId_wip")) : new BigDecimal(0);
			if(auctionRangeId_wip.floatValue() > 0){
				
				AuctionRange ar = getAuctionRangeById(auctionRangeId_wip);
				
				req.getSession().setAttribute("auctionRange", ar);
				
				req.setAttribute("auctionRange", ar);
				
				page ="auction-range.jsp";
				
			}

		}else if("updateAuctionRange".equals(action)){
			
			System.out.println("updateAuctionRange");

			auctionRangeId_wip = req.getParameter("auctionRangeId_wip")!=null && !req.getParameter("auctionRangeId_wip").equals("") ? new BigDecimal(req.getParameter("auctionRangeId_wip")): new BigDecimal(req.getSession().getAttribute("auctionRangeId-wip").toString());
			
			System.out.println("updateAuctionRange : auctionRangeId_wip "+auctionRangeId_wip);
			if(auctionRangeId_wip == new BigDecimal(0)){
				auctionRangeId_wip = new BigDecimal(req.getSession().getAttribute("auctionRangeId-wip").toString());
			}
			if(auctionRangeId_wip.floatValue() > 0){
				
				AuctionRange ar = getAuctionRangeById(auctionRangeId_wip);
				
				req.getSession().setAttribute("auctionRange", ar);
				
				req.setAttribute("auctionRange", ar);
				
				
				UserDao ud = new UserDao();
				
				List<User> userCoordinatorList = ud.getUserListByRole(5);

				req.setAttribute("userCoordinatorList", userCoordinatorList);
				
				page ="auction-range-update.jsp";
				
			}

			
		}else if("saveUpdateAuctionRange".equals(action)){
			
			System.out.println("saveUpdateAuctionRange");

			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));
			auctionRangeId_wip = !req.getParameter("auctionRangeId_wip").equals("") ? new BigDecimal(req.getParameter("auctionRangeId_wip")) : new BigDecimal(0);


			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			BigDecimal range_start = !req.getParameter("range_start").equals("") ? new BigDecimal(req.getParameter("range_start")) : new BigDecimal(0);
			BigDecimal range_end = !req.getParameter("range_end").equals("") ? new BigDecimal(req.getParameter("range_end")) : new BigDecimal(0);
			BigDecimal increment_amount = !req.getParameter("increment_amount").equals("") ? new BigDecimal(req.getParameter("increment_amount")) : new BigDecimal(0);
			
			
			AuctionRange ar = updateAuctionRangeOnUpdate(
						auction_id,
						range_start,
						range_end,
						increment_amount,
						user_id,
						auctionRangeId_wip
					);
			

			
			
			if(ar!=null && ar.getId()!=null){
				
				ar = getAuctionRangeById(auctionRangeId_wip);
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "AuctionRange updated succeessful.");
				
				req.getSession().setAttribute("auctionRange", ar);
				
				req.setAttribute("auctionRange", ar);
				
				page ="auction-range.jsp";
				
			}else{
				
				ar = new AuctionRange();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "AuctionRange update failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("auction", ar);
				
				req.setAttribute("auction", ar);
				
				page ="auction-range-list.jsp";
				
			}
			
			
		}else if("saveAuctionRangeImage".equals(action)){
			
			System.out.println("saveAuctionRangeImage");
			
			
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
			page= "auction-range.jsp";
		}
		
		
		req.getSession().setAttribute("auctionId_wip", auctionId_wip);
		
		req.setAttribute("auctionId_wip", auctionId_wip);
	
		
		System.out.println("Paramerters doAuctionRangeManager - page : "+page);
		
		return page;
		
	}
	

	
	/*
	public AuctionRange getAuctionRange(String auctionRangeId){
		
		AuctionRange ar = new AuctionRange();

		AuctionRangeDao ad = new AuctionRangeDao();

		ar = ad.getAuctionRange(auctionRangeId);
		
		return ar;
		
	}	
	*/


	public AuctionRange getAuctionRangeById(BigDecimal id){
		
		AuctionRange ar = new AuctionRange();

		AuctionRangeDao ad = new AuctionRangeDao();

		ar = ad.getAuctionRangeById(id);
		
		return ar;
		
	}	
	

	public AuctionRange insertAuctionRangeOnCreate(
				BigDecimal auction_id,
				BigDecimal range_start,
				BigDecimal range_end,
				BigDecimal increment_amount,
				Integer user_id
			){
		
		AuctionRange ar = null;
		
		AuctionRangeDao ad = new AuctionRangeDao(req,res);

		ar = ad.insertAuctionRangeOnCreate(
				auction_id,
				range_start,
				range_end,
				increment_amount,
				user_id
				);
		
		return ar;
		
	}
	
	
	public AuctionRange updateAuctionRangeOnUpdate(
				BigDecimal auction_id,
				BigDecimal range_start,
				BigDecimal range_end,
				BigDecimal increment_amount,
				Integer user_id,
				BigDecimal auctionRangeId_wip
			
			){
		
		AuctionRange ar = null;
		
		AuctionRangeDao ad = new AuctionRangeDao();
	
		ar = ad.updateAuctionRangeOnUpdate(
					auction_id,
					range_start,
					range_end,
					increment_amount,
					user_id,
					auctionRangeId_wip
				);
		
		return ar;
		
	}
	
	
	
	public List<AuctionRange> getAuctionRangeListByAuctionId(BigDecimal auction_id){
		
		List<AuctionRange> aList = new ArrayList<AuctionRange>();

		AuctionRangeDao ad = new AuctionRangeDao();

		aList = ad.getAuctionRangeListByAuctionId(auction_id);
		
		return aList;
		
	}

	public List<AuctionRange> getAuctionRangeListByAuctionId(BigDecimal auction_id, BigDecimal amount_bid){
		
		List<AuctionRange> aList = new ArrayList<AuctionRange>();

		AuctionRangeDao ad = new AuctionRangeDao();

		aList = ad.getAuctionRangeListByAuctionId(auction_id);
		
		return aList;
		
	}
	
	
}

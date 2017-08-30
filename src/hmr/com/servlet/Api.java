package hmr.com.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.bean.Auction;
import hmr.com.bean.AuctionUserBiddingMax;
import hmr.com.bean.BiddingTransaction;
import hmr.com.bean.Lot;
import hmr.com.manager.AuctionManager;
import hmr.com.manager.AuctionUserBiddingMaxManager;
import hmr.com.manager.BiddingTransactionManager;
import hmr.com.manager.LotManager;

@SuppressWarnings("serial")
public class Api extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		List<Lot> lList = new ArrayList<Lot>();
		Lot delta_lot = null;
		
		String auctionId = req.getParameter("auctionId")!=null ? (String)req.getParameter("auctionId") : "";
		String lotId = req.getParameter("lotId")!=null ? (String)req.getParameter("lotId") : "";
		String UserId = req.getParameter("UserId")!=null ? (String)req.getParameter("UserId") : "";
		Integer user_id = !"".equals(UserId) ?  Integer.parseInt(UserId) : null;
		Timestamp tsNow = new Timestamp(System.currentTimeMillis());
		
		
		AuctionManager aMngr = new AuctionManager();
		LotManager lMngr = new LotManager();
		BiddingTransactionManager btMngr = new BiddingTransactionManager();
		AuctionUserBiddingMaxManager aubmMngr = new AuctionUserBiddingMaxManager();
		
		Auction a = null;
		//List<Lot> lotList = lMngr.getActiveLotListByAuctionId(new BigDecimal(auctionId) );
		
		
		List<Lot> lotList = null;
		
		Lot l = null;
		
		if(!"".equals(lotId) && user_id!=null){
			try{
				l = lMngr.getActiveLotListByLotIdwithBidder(new BigDecimal(lotId) );
			}catch(Exception e){
				
			}
		}else if(!"".equals(auctionId) && user_id!=null){
			try{
				lotList = lMngr.getActiveLotListByAuctionIdwithBidder(new BigDecimal(auctionId) );
			}catch(Exception e){
				
			}
		}
		
		System.out.println("lotList "+lotList);
		System.out.println("API auctionId "+auctionId);
		System.out.println("API lotId "+lotId);
		System.out.println("API user_id "+user_id);
		//System.out.println("API l.getAuction_id() "+l.getAuction_id());

		
		if(!"".equals(lotId) && user_id!=null && l!=null && l.getAuction_id()!=null){
			
			auctionId = String.valueOf(l.getAuction_id());
			
			HashMap<String,BiddingTransaction> btLotIdUserIdHM = btMngr.getBiddingTransactionHMByAuctionIdSetLotIdUserId(new BigDecimal(auctionId));
			a = aMngr.getAuctionByAuctionId(new BigDecimal(auctionId));
			

			//if(hmLotId.get(lot.getLot_id())==null){
				delta_lot = lMngr.applyLotRules(l, a);
			//}
			
			
			ArrayList<AuctionUserBiddingMax> aubmUser = aubmMngr.getAuctionUserBiddingMaxListByLotIdAndUser(delta_lot.getLot_id(), user_id);
			
			//check if user is last bidder
			List<BiddingTransaction> btList = btMngr.getLatestBiddingTransactionLotId(l.getLot_id());
			if(!btList.isEmpty()){
				if (btList.get(0).getUser_id() == user_id) {
					//continue; //don't update
				}
			}
			
			//check if the end time is expired
			if(delta_lot.getEnd_date_time().before(tsNow)) {
				//continue; //don't update
			}
			
			//check one bidder per lot setting
			if(new AuctionManager().getAuctionByAuctionId(new BigDecimal(auctionId)).getOne_lot_per_bidder()==1 ){
				//user is a bidder of this lot
				if(btLotIdUserIdHM.get(delta_lot.getLot_id()+"_"+UserId)!=null){
					lList.add(delta_lot);
				}
				//continue;
			} 
			
			//check if user has max-bid for this lot
			if(!aubmUser.isEmpty()){
				lList.add(delta_lot);
				//continue;
			}
			
			
			//for everything else
			lList.add(delta_lot);
			
			
		}else if(!"".equals(auctionId) && user_id!=null && lotList!=null){
			
			//HashMap<BigDecimal,BiddingTransaction> btHM = btMngr.getLatestBiddingTransactionHMByAuctionIdSetLotId(new BigDecimal(auctionId));
			HashMap<String,BiddingTransaction> btLotIdUserIdHM = btMngr.getBiddingTransactionHMByAuctionIdSetLotIdUserId(new BigDecimal(auctionId));
			a = aMngr.getAuctionByAuctionId(new BigDecimal(auctionId));
			
			//System.out.println("user_id "+user_id);
			
			//HashMap<BigDecimal,BigDecimal> hmLotId = new HashMap<BigDecimal,BigDecimal>();

			for(Lot lot : lotList){
				
				//if(hmLotId.get(lot.getLot_id())==null){
					delta_lot = lMngr.applyLotRules(lot, a);
				//}
				
				
				ArrayList<AuctionUserBiddingMax> aubmUser = aubmMngr.getAuctionUserBiddingMaxListByLotIdAndUser(delta_lot.getLot_id(), user_id);
				
				//check if user is last bidder
				List<BiddingTransaction> btList = btMngr.getLatestBiddingTransactionLotId(lot.getLot_id());
				if(!btList.isEmpty()){
					if (btList.get(0).getUser_id() == user_id) {
						continue; //don't update
					}
				}
				
				//check if the end time is expired
				if(delta_lot.getEnd_date_time().before(tsNow)) {
					continue; //don't update
				}
				
				//check one bidder per lot setting
				if(new AuctionManager().getAuctionByAuctionId(new BigDecimal(auctionId)).getOne_lot_per_bidder()==1 ){
					//user is a bidder of this lot
					if(btLotIdUserIdHM.get(delta_lot.getLot_id()+"_"+UserId)!=null){
						lList.add(delta_lot);
					}
					continue;
				} 
				
				//check if user has max-bid for this lot
				if(!aubmUser.isEmpty()){
					lList.add(delta_lot);
					continue;
				}
				
				
				//for everything else
				lList.add(delta_lot);
				
				
			}//loop
			
		}
		
		//AuctionManager aMngr = new AuctionManager();
		
		

		
		System.out.println("API lList "+lList.size());
		
		req.setAttribute("lList", lList);
		
		req.setAttribute("auction", a);
		
		req.setAttribute("tsNow", tsNow);
		
		
		res.setContentType("application/json");
		String page = "api/bid-amount.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, res);
	}
}

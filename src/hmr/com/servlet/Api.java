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
		String UserId = req.getParameter("UserId")!=null ? (String)req.getParameter("UserId") : "";
		Integer user_id = Integer.parseInt(UserId);
		
		LotManager lMngr = new LotManager();
		BiddingTransactionManager btMngr = new BiddingTransactionManager();
		AuctionUserBiddingMaxManager aubmMngr = new AuctionUserBiddingMaxManager();
		
		List<Lot> lotList = lMngr.getActiveLotListByAuctionId(new BigDecimal(auctionId) );
		//HashMap<BigDecimal,BiddingTransaction> btHM = btMngr.getLatestBiddingTransactionHMByAuctionIdSetLotId(new BigDecimal(auctionId));
		HashMap<String,BiddingTransaction> btLotIdUserIdHM = btMngr.getBiddingTransactionHMByAuctionIdSetLotIdUserId(new BigDecimal(auctionId));
		
		
		System.out.println("user_id "+user_id);
		
		for(Lot lot : lotList){
			delta_lot = lMngr.applyLotRules(lot);
			ArrayList<AuctionUserBiddingMax> aubmUser = aubmMngr.getAuctionUserBiddingMaxListByLotIdAndUser(delta_lot.getLot_id(), user_id);
			
			//check if user is last bidder
			List<BiddingTransaction> btList = btMngr.getLatestBiddingTransactionLotId(lot.getLot_id());
			if(!btList.isEmpty()){
				if (btList.get(0).getUser_id() == user_id) {
					continue; //don't update
				}
			}
			
			//check if the end time is expired
			if(delta_lot.getEnd_date_time().before(new Timestamp(System.currentTimeMillis()))) {
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
		
		req.setAttribute("lList", lList);
		
		
		res.setContentType("application/json");
		String page = "api/bid-amount.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, res);
	}
}

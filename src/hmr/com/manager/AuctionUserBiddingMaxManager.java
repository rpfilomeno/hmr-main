package hmr.com.manager;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.bean.AuctionUser;
import hmr.com.bean.AuctionUserBiddingMax;
import hmr.com.dao.AuctionUserBiddingMaxDao;
import hmr.com.dao.AuctionUserDao;

public class AuctionUserBiddingMaxManager {
	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public AuctionUserBiddingMaxManager(){}

	public AuctionUserBiddingMaxManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doAuctionUserBiddingMaxManager(){
		String page = "index.jsp";
		//TODO
		return page;
	}
	
	public boolean insertAuctionUserBiddingMaxManager(Integer lot_id, BigDecimal amount, Integer bidder_id, Integer qty) {
		AuctionUserBiddingMaxDao aud = new AuctionUserBiddingMaxDao();
		if(aud.insertAuctionUserBiddingMax(lot_id, amount, BigDecimal.ZERO, BigDecimal.ZERO,  qty, bidder_id)>0) return true;
		return false;
	}
	
	public ArrayList<AuctionUserBiddingMax> getAuctionUserBiddingMaxListByLotId(Integer lot_id){
		
		ArrayList<AuctionUserBiddingMax> aubmList = new ArrayList<AuctionUserBiddingMax>();

		AuctionUserBiddingMaxDao aubm = new AuctionUserBiddingMaxDao();

		aubmList = aubm.getAuctionUserBiddingMaxList(lot_id);
		
		return aubmList;
		
	}
	
	public ArrayList<AuctionUserBiddingMax> getAuctionUserBiddingMaxListByLotIdAndUser(BigDecimal lot_id, Integer bidder_id){
		
		ArrayList<AuctionUserBiddingMax> aubmList = new ArrayList<AuctionUserBiddingMax>();

		AuctionUserBiddingMaxDao aubm = new AuctionUserBiddingMaxDao();

		aubmList = aubm.getAuctionUserBiddingMaxListByLotIdAndUser(lot_id, bidder_id);
		
		return aubmList;
		
	}
	
	public HashMap<String, AuctionUserBiddingMax> getAuctionUserBiddingMaxHMByAuctionIdSetLotIdAndUser(BigDecimal auction_id){
		
		HashMap<String, AuctionUserBiddingMax> aubmLotUserHM = new HashMap<String, AuctionUserBiddingMax>();

		AuctionUserBiddingMaxDao aubm = new AuctionUserBiddingMaxDao();

		aubmLotUserHM = aubm.getAuctionUserBiddingMaxHMByAuctionIdSetLotIdAndUser(auction_id);
		
		return aubmLotUserHM;
		
	}
	
	
}

package hmr.com.manager;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.dao.AuctionUserBiddingMaxDao;

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
	
	public boolean insertAuctionUserBiddingMaxManager(Integer lot_id, BigDecimal amount, Integer bidder_id) {
		AuctionUserBiddingMaxDao aud = new AuctionUserBiddingMaxDao();
		if(aud.insertAuctionUserBiddingMax(lot_id, amount, bidder_id)>0) return true;
		return false;
	}
	
	
}

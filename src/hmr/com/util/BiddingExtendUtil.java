package hmr.com.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import hmr.com.bean.Auction;
import hmr.com.bean.BiddingTransaction;
import hmr.com.bean.Lot;
import hmr.com.dao.AuctionDao;
import hmr.com.dao.BiddingTransactionDao;
import hmr.com.dao.LotDao;
import hmr.com.manager.BiddingTransactionManager;

public class BiddingExtendUtil {

    
    
    public static void main(String[] args) throws ParseException  {
    	AuctionDao aDao = new AuctionDao();
    	List<Auction> aList = aDao.getAuctionListEndingTodayActiveOpen();
        //BiddingTransactionManager btm = new BiddingTransactionManager();
    	BiddingTransactionDao btDao = new BiddingTransactionDao();
        
    	
    	for(Auction a : aList){
    		System.out.println(a.getAuction_name());
    		
    		LotDao lDao = new LotDao();
    		ArrayList<Lot> lList = lDao.getLotListByAuctionId(a.getAuction_id());
    		
    		for(Lot l : lList){
    			System.out.println(l.getLot_id() +" - "+l.getLot_name());
    			
    			HashMap<BigDecimal, BiddingTransaction> btHM = btDao.getBiddingTransactionHMByLotId(l.getLot_id());
    			BiddingTransaction bt = btHM.get(l.getLot_id());
    			if(bt!=null && bt.getDate_created()!=null){
    				
    				bt = btHM.get(l.getLot_id());
        			System.out.println(">>>>> "+bt.getDate_created() +" - "+bt.getAmount_bid()+" - "+bt.getAmount_buy()+" "+l.getEnd_date_time());
        			
        			Timestamp auction_end_date_time = a.getEnd_date_time();
        			Timestamp lot_end_date_time = l.getEnd_date_time();
        			Timestamp p_end_date_time = null;
        			/*
        			String newDateString = "2012-02-03 06:30:00.0";
        			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        			Date newDate = sdf.parse(newDateString);
        			System.out.println("new date to compare with reference date : "+newDate);
*/
        			Calendar createdBTCal = Calendar.getInstance();
        			createdBTCal.setTime(bt.getDate_created());
        			
        			Date dtNow = new Date();
        			if(lot_end_date_time!=null){
        				p_end_date_time = lot_end_date_time;
        			}else{
        				p_end_date_time = auction_end_date_time;
        			}
        			
        			Calendar cal1 = Calendar.getInstance();
        			cal1.setTime(p_end_date_time);
        			cal1.add(Calendar.MINUTE, -2);
        			System.out.println("1 reference date: "+cal1.getTime());

        			Calendar cal2 = Calendar.getInstance();
        			cal2.setTime(p_end_date_time);
        			cal2.add(Calendar.MINUTE, 2);
        			System.out.println("2 reference date: "+cal2.getTime());
        			
        			Calendar cal3 = Calendar.getInstance();
        			cal3.setTime(dtNow);
        			cal3.add(Calendar.MINUTE, 3);
        			System.out.println("3 reference date: "+cal3.getTime());
        			
        			if(createdBTCal.after(cal1) && createdBTCal.before(cal2)){
        			    System.out.println("Update Lot end_date_time and Bidding Transaction Is Extended.");
        			    
        			    if(bt.getIs_extended()==0){
        			    	
        			    	java.sql.Timestamp end_date_time = new java.sql.Timestamp(cal3.getTime().getTime());
        			    	
        			    	Integer user_id = Integer.valueOf("1");
        			    	
        			    	//BigDecimal user_id = new BigDecimal(1);
        			    	
        			    	
        			    	lDao.updateLot_End_date_time_OnBatchBiddingExtend(end_date_time, user_id, l.getId());
        			    	
        			    	btDao.updateBiddingTransaction_is_extended_OnBatchBiddingExtend(1, user_id, bt.getId());
        			    }
        			    
        			}
        			
        			
    			}

    		}
    		
    	}
    	
    }
    

}

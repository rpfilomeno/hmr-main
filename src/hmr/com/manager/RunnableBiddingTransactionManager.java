package hmr.com.manager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import hmr.com.bean.Auction;
import hmr.com.bean.AuctionUserBiddingMax;
import hmr.com.bean.BiddingTransaction;
import hmr.com.bean.Item;
import hmr.com.bean.Lot;

public class RunnableBiddingTransactionManager implements Runnable {
	   private Thread t;
	   private String threadName;
	   private Integer lot_id;
	   private BigDecimal auction_id;
	   private Integer isLast;
	   private String bid_qualifier_price;

	   public RunnableBiddingTransactionManager( String threadName, Integer lot_id){
	       this.threadName = threadName;
	       this.lot_id = lot_id;
	       System.out.println("Creating " +  threadName +" - "+lot_id  );
	   }
	   
	   RunnableBiddingTransactionManager( String threadName, BigDecimal auction_id, String bid_qualifier_price){
	       this.threadName = threadName;
	       this.auction_id = auction_id;
	       this.bid_qualifier_price = bid_qualifier_price;
	       System.out.println("Creating " +  threadName +" - "+lot_id+" - "+auction_id  );
	   }
	   
	   RunnableBiddingTransactionManager( String threadName, Integer lot_id, BigDecimal auction_id, Integer isLast){
	       this.threadName = threadName;
	       this.lot_id = lot_id;
	       this.auction_id = auction_id;
	       this.isLast = isLast;
	       System.out.println("Creating " +  threadName +" - "+lot_id+" - "+auction_id  );
	   }
	   

	   
	   public void run() {	
	      System.out.println("Running " +  threadName );
	      
    	  

	      
	      try {
	    	  if("btAutoPlaySetMax".equals(threadName) && !"".equals(lot_id) ){
	    		  
	    		  boolean cont = true;
	    		  for(int z = 0; z < 100; z ++){
	    			  
	    		  if(cont){
	    			  
	    		  
	    		  
	    		  Thread.sleep(5000);
	    		  System.out.println("btAutoPlaySetMax - Start");
	    		  
	    		    AuctionManager aucMngr = new AuctionManager();
	    		  	AuctionUserBiddingMaxManager aubmMngr = new AuctionUserBiddingMaxManager();
	    		  	ArrayList<AuctionUserBiddingMax> aubmList = aubmMngr.getAuctionUserBiddingMaxListByLotId(lot_id);
					for(AuctionUserBiddingMax aubm : aubmList){
						System.out.println(aubm.getId()+" "+aubm.getLot_id()+" "+aubm.getAmount_bid()+" "+aubm.getAmount_buy()+" "+aubm.getAmount_offer()+" "+aubm.getBidder_id());
						
						LotManager lotMngr = new LotManager();
						Lot lot = lotMngr.getLotByLotId(new BigDecimal(aubm.getLot_id()));
						
						BigDecimal increment_amount = BigDecimal.ZERO;

						LotRangeManager lrMngr = new LotRangeManager();
						AuctionRangeManager arMngr = new AuctionRangeManager();
						//Check if there is lot level bid increment else use auction level value
						increment_amount = lrMngr.getIncrementAmountByLotId(lot.getLot_id(), lot.getAmount_bid());
						if(increment_amount.equals(BigDecimal.ZERO)) {
							System.out.println("Using auction bid increment on lot");
							increment_amount = arMngr.getIncrementAmountByAuctionId(lot.getAuction_id(), lot.getAmount_bid());
						}
						BigDecimal amount_bid_next=  increment_amount.add(lot.getAmount_bid());
						lot.setAmount_bid_next(amount_bid_next);
						
						
						System.out.println(lot.getId()+" "+lot.getLot_id()+" "+lot.getAmount_bid()+" "+lot.getAmount_buy()+" "+lot.getAmount_bid_next());
						
						if(lot.getAmount_bid_next().doubleValue() < aubm.getAmount_bid().doubleValue() && aubm.getBidder_id() != lot.getBidder_id()){
							System.out.println("proceed to insert in Bidding History");
							BiddingTransactionManager btMngr = new BiddingTransactionManager();
							
							Auction auc = aucMngr.getAuctionByAuctionId(lot.getAuction_id());
					        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
							//if(auc.getStart_date_time().after(sqlDate_t) && sqlDate_t.before(lot.getEnd_date_time())){
							if(auc.getStart_date_time().after(sqlDate_t)){
								btMngr.insertBiddingTransactionMakeBidBySetMax(Integer.parseInt(lot.getLot_id().toString()) , lot.getAmount_bid_next(), aubm.getBidder_id(), aubm.getQty());
							}
							
							cont = true;
						}else{
							cont = false;
							z = 100;
						}
						
					}
					
					
		    		  
		    		  BiddingTransactionManager btMngr = new BiddingTransactionManager();
						ArrayList<BiddingTransaction> BTList = btMngr.getLatestBiddingTransactionByLotId(new BigDecimal(lot_id));
							
						
						int first = 0;
						for(BiddingTransaction bt : BTList){
							System.out.println(bt.getId()+" "+bt.getAction_taken()+" "+bt.getAmount_bid()+" "+bt.getAmount_buy()+" "+bt.getAmount_offer()+" "+bt.getStatus());
							//if(bt.getStatus()==0){
								if(first==0){
									int status = 0;
									if(bt.getAction_taken()==5){
										status = 3;
									}else{
										status = 1;
									}
									btMngr.updateBiddingTransactionStatus(status, bt.getId());
								}else{
									int status = 0;
									if(bt.getAction_taken()==5){
										status = 4;
									}else{
										status = 2;
									}
									
									btMngr.updateBiddingTransactionStatus(status, bt.getId());
								}
								first++;
								//Integer status = 0;
								//btMngr.updateBiddingTransactionStatus(status, bt.getId());
							//}
						}
						
					
					
	    		    
					System.out.println("btAutoPlaySetMax - End");
	    		  }
	    		  }
	    	  }else if("btSetStatus".equals(threadName) && !"".equals(lot_id) ){
	    		  
	    		  Thread.sleep(10000);
	    		  BiddingTransactionManager btMngr = new BiddingTransactionManager();
					ArrayList<BiddingTransaction> BTList = btMngr.getLatestBiddingTransactionByLotId(new BigDecimal(lot_id));
						
					
					int first = 0;
					for(BiddingTransaction bt : BTList){
						System.out.println(bt.getId()+" "+bt.getAction_taken()+" "+bt.getAmount_bid()+" "+bt.getAmount_buy()+" "+bt.getAmount_offer()+" "+bt.getStatus());
						//if(bt.getStatus()==0){
							if(first==0){
								int status = 0;
								if(bt.getAction_taken()==5){
									status = 3;
								}else{
									status = 1;
								}
								btMngr.updateBiddingTransactionStatus(status, bt.getId());
							}else{
								int status = 0;
								if(bt.getAction_taken()==5){
									status = 4;
								}else{
									status = 2;
								}
								
								btMngr.updateBiddingTransactionStatus(status, bt.getId());
							}
							first++;
							//Integer status = 0;
							//btMngr.updateBiddingTransactionStatus(status, bt.getId());
						//}
					}
	    		  
	    		  
	    	  }
	    	  
	    	  
	     } catch (Exception e) {
	         System.out.println("Thread " +  threadName + " interrupted.");
	     }
	      
			if(isLast!=null && isLast==1){
				
				LovManager lovMngr = new LovManager();
				try {
					Thread.sleep(40000);
					lovMngr.updateLovListValue("UPLOAD-AUCTION-PROCESS-SYNC-ALL",new Integer( 186),"0", new Integer(1));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				/*
				LovManager lovMngr = new LovManager();
				try {
					//Thread.sleep(20000);
					lovMngr.updateLovListValue("UPLOAD-AUCTION-PROCESS-SYNC-ALL",new Integer( 186),"1", new Integer(1));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
			}
	      
	      
	     System.out.println("Thread " +  threadName + " exiting.");
	   }
	   
	   public void start ()
	   {
	      System.out.println("Starting " +  threadName );
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	   
	    public static void main(String a[]){
	    	RunnableBiddingTransactionManager rbtm = new RunnableBiddingTransactionManager("btAutoPlaySetMax", Integer.parseInt("386762") );
			rbtm.start();
	    }

	}



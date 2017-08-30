package hmr.com.manager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import hmr.com.bean.Auction;
import hmr.com.bean.AuctionUserBiddingMax;
import hmr.com.bean.BiddingTransaction;
import hmr.com.bean.Item;
import hmr.com.bean.Lot;
import hmr.com.dao.AuctionDao;
import hmr.com.dao.BiddingTransactionDao;
import hmr.com.dao.LotDao;

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
	    		  
	    		  LotManager lotMngr = new LotManager();
	    		  //Lot lotex = lotMngr.getLotByLotId(new BigDecimal(lot_id));
	    		  
	    		  //Integer bidder_id = lotex.getBidder_id();

	    		  boolean cont = true;
	    		  for(int z = 0; z < 3; z ++){
	    			  
	    		  if(cont){
	    			  
	    		  
	    		  
	    		  Thread.sleep(10000);
	    		  System.out.println("btAutoPlaySetMax - Start");
	    		  
	    		    AuctionManager aucMngr = new AuctionManager();
	    		  	AuctionUserBiddingMaxManager aubmMngr = new AuctionUserBiddingMaxManager();
	    		  	ArrayList<AuctionUserBiddingMax> aubmList = aubmMngr.getAuctionUserBiddingMaxListByLotId(lot_id);
	    		  	
					for(AuctionUserBiddingMax aubm : aubmList){
						System.out.println("aubm "+aubm.getId());
						System.out.println("aubm "+aubm.getAmount_bid());
						System.out.println("aubm "+aubm.getBidder_id());

						
						Lot lot = lotMngr.getLotByLotId(new BigDecimal(aubm.getLot_id()));
						
						BigDecimal increment_amount = BigDecimal.ZERO;

						LotRangeManager lrMngr = new LotRangeManager();
						AuctionRangeManager arMngr = new AuctionRangeManager();
						
						
						System.out.println("lot.getAmount_bid() "+lot.getAmount_bid());
						System.out.println("aubm "+aubm.getBidder_id());
						
						if(lot.getAmount_bid().doubleValue() == 0){
							System.out.println("lot.getAmount_bid() "+lot.getAmount_bid());
							System.out.println("lot.getStarting_bid_amount() "+lot.getStarting_bid_amount());
							lot.setAmount_bid(lot.getStarting_bid_amount());
							BiddingTransactionManager btMngr = new BiddingTransactionManager();
							btMngr.insertBiddingTransactionMakeBidBySetMax(Integer.parseInt(lot.getLot_id().toString()) , lot.getAmount_bid(), aubm.getBidder_id(), aubm.getQty());
							
						}else{
							
							

							//Check if there is lot level bid increment else use auction level value
							increment_amount = lrMngr.getIncrementAmountByLotId(lot.getLot_id(), lot.getAmount_bid());
							
							
							
							if(increment_amount.equals(BigDecimal.ZERO)) {
								System.out.println("Using auction bid increment on lot");
								increment_amount = arMngr.getIncrementAmountByAuctionId(lot.getAuction_id(), lot.getAmount_bid());
							}
							
							System.out.println("increment_amount "+increment_amount);
							
							BigDecimal amount_bid_next=  increment_amount.add(lot.getAmount_bid());
							lot.setAmount_bid_next(amount_bid_next);
							
							System.out.println("amount_bid_next "+amount_bid_next);
							
							
							
							
							System.out.println(lot.getId()+" "+lot.getLot_id()+" "+lot.getAmount_bid()+" "+lot.getAmount_buy()+" "+lot.getAmount_bid_next());
							
							System.out.println("lot.getAmount_bid_next().doubleValue() "+lot.getAmount_bid_next().doubleValue());
							System.out.println("aubm.getAmount_bid().doubleValue( "+aubm.getAmount_bid().doubleValue());
							System.out.println("aubm.getBidder_id( "+aubm.getBidder_id());
							System.out.println("lot.getBidder_id() "+lot.getBidder_id());
							
							if(lot.getAmount_bid_next().doubleValue() < aubm.getAmount_bid().doubleValue() && aubm.getBidder_id() != lot.getBidder_id()){
								System.out.println("proceed to insert in Bidding History");
								BiddingTransactionManager btMngr = new BiddingTransactionManager();
								/*
								ArrayList<BiddingTransaction> btList = btMngr.getLatestBiddingTransactionByLotId(new BigDecimal( lot_id));
								BiddingTransaction btLast = new BiddingTransaction();
								int i = 1;
								for(BiddingTransaction bt : btList){
									
									System.out.println("bt "+bt.getUser_id()+" "+i+ " "+ lot.getBidder_id());
									
									if(i==2){
										btLast = bt;
									}
									i++;
								}
								*/
								
								
								Auction auc = aucMngr.getAuctionByAuctionId(lot.getAuction_id());
						        //java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						        java.sql.Timestamp sqlDate_t = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
								//if(auc.getStart_date_time().after(sqlDate_t) && sqlDate_t.before(lot.getEnd_date_time())){
						        //if(auc.getStart_date_time().before(sqlDate_t) && !lot.getBidder_id().equals(btLast.getUser_id())  ){
								if(auc.getStart_date_time().before(sqlDate_t)  ){
									
									BiddingTransaction bt1 = btMngr.getBiddingTransactionLatestByLotId(new BigDecimal( lot_id));
									//System.out.println("bt1 "+bt1.getUser_id()+" "+i+ " "+ lot.getBidder_id()+" "+aubm.getBidder_id());
									
									if(bt1.getUser_id().doubleValue() != aubm.getBidder_id().doubleValue()){
										btMngr.insertBiddingTransactionMakeBidBySetMax(Integer.parseInt(lot.getLot_id().toString()) , lot.getAmount_bid_next(), aubm.getBidder_id(), aubm.getQty());
									}
									
									
									
								}
								
								cont = true;
							}else{
								cont = false;
								z = 20;
							}
							
						}
						
						
					}

		    		  //if(cont){
		    			  
		    		  /*
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
									cont = false;
								}else{
									int status = 0;
									if(bt.getAction_taken()==5){
										status = 4;
									}else{
										status = 2;
									}
									
									btMngr.updateBiddingTransactionStatus(status, bt.getId());
									cont = false;
								}
								first++;
								//Integer status = 0;
								//btMngr.updateBiddingTransactionStatus(status, bt.getId());
							//}
						}
						*/
					
		    		  }
	    		    
					System.out.println("btAutoPlaySetMax - End");
	    		  }
	    		  //}
	    	  }else if("btSetStatus".equals(threadName) && !"".equals(lot_id) ){
	    		  
	    		  Thread.sleep(15000);
	    		  BiddingTransactionManager btMngr = new BiddingTransactionManager();
					ArrayList<BiddingTransaction> BTList = btMngr.getLatestBiddingTransactionByLotIdOnSetStatus(new BigDecimal(lot_id));
						
					
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
	    		  
	    		  
	    	  }else if ("btExtendTime".equals(threadName) && !"".equals(lot_id)) {
	    		  
	    		        Thread.sleep(3000);
	    		    	//String lot_id_str = args[0];
	    		    	BigDecimal lot_id_ = new BigDecimal(lot_id);
	    		    	AuctionDao aDao = new AuctionDao();
	    		    	//List<Auction> aList = aDao.getAuctionListEndingTodayActiveOpen();
	    		        //BiddingTransactionManager btm = new BiddingTransactionManager();
	    		    	BiddingTransactionDao btDao = new BiddingTransactionDao();
	    		        
	    		    	
	    		    	//for(Auction a : aList){
	    		    		//System.out.println(a.getAuction_name());
	    		    		
	    		    		LotDao lDao = new LotDao();
	    		    		//ArrayList<Lot> lList = lDao.getLotListByAuctionId(a.getAuction_id());
	    		    		
	    		    		//for(Lot l : lList){
	    		    			//System.out.println(l.getLot_id() +" - "+l.getLot_name());
	    		    			
	    		    			Lot l = lDao.getLotByLotId(lot_id_);
	    		    			Auction a = aDao.getAuctionByAuctionId(l.getAuction_id());
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
	    		        			cal1.add(Calendar.MINUTE, -1);
	    		        			System.out.println("1 reference date: "+cal1.getTime());

	    		        			Calendar cal2 = Calendar.getInstance();
	    		        			cal2.setTime(p_end_date_time);
	    		        			//cal2.add(Calendar.MINUTE, 1);
	    		        			System.out.println("2 reference date: "+cal2.getTime());
	    		        			
	    		        			Calendar cal3 = Calendar.getInstance();
	    		        			cal3.setTime(dtNow);
	    		        			cal3.add(Calendar.MINUTE, 1);
	    		        			//cal3.add(Calendar.SECOND, -dtNow.getSeconds());
	    		        			
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

	    		    		//}
	    		    		
	    		    	//}
	    		    	
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



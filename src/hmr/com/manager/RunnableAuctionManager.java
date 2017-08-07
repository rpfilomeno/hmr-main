package hmr.com.manager;

import java.math.BigDecimal;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;



import hmr.com.bean.Item;
import hmr.com.bean.Lot;

public class RunnableAuctionManager implements Runnable {
	   private Thread t;
	   private String threadName;
	   private BigDecimal lot_id;
	   private BigDecimal auction_id;
	   private Integer isLast;
	   private String bid_qualifier_price;

	   RunnableAuctionManager( String threadName, BigDecimal lot_id, BigDecimal auction_id){
	       this.threadName = threadName;
	       this.lot_id = lot_id;
	       this.auction_id = auction_id;
	       System.out.println("Creating " +  threadName +" - "+lot_id+" - "+auction_id  );
	   }
	   
	   RunnableAuctionManager( String threadName, BigDecimal auction_id, String bid_qualifier_price){
	       this.threadName = threadName;
	       this.auction_id = auction_id;
	       this.bid_qualifier_price = bid_qualifier_price;
	       System.out.println("Creating " +  threadName +" - "+lot_id+" - "+auction_id  );
	   }
	   
	   RunnableAuctionManager( String threadName, BigDecimal lot_id, BigDecimal auction_id, Integer isLast){
	       this.threadName = threadName;
	       this.lot_id = lot_id;
	       this.auction_id = auction_id;
	       this.isLast = isLast;
	       System.out.println("Creating " +  threadName +" - "+lot_id+" - "+auction_id  );
	   }
	   
	   
	   public void run() {	
	      System.out.println("Running " +  threadName );
	      
    	  

	      
	      try {
	    	  if("lotTotalsCompute".equals(threadName) && !"".equals(bid_qualifier_price) ){
	    		  
	    		    LotManager lMngr = new LotManager();
					List<Lot> BQPLotList = lMngr.getLotListByAuctionId(auction_id);
						
						for(Lot BQPLot : BQPLotList){
							
						
		    		  	try{
		    		  	//	Thread.sleep(1000);
		    		  	}catch(Exception e){
		    		  		
		    		  	}
		    		    
		    		    
		    		    ItemManager iMngr = new ItemManager();
		    		    
		    		    lot_id = BQPLot.getLot_id();
						HashMap<BigDecimal, Item>itemHM = iMngr.getItemHMByLotId(lot_id);
						
						
						BigDecimal reserve_price_total = new BigDecimal("0");
						BigDecimal srp_total = new BigDecimal("0");
						BigDecimal target_price_total = new BigDecimal("0");
						BigDecimal assess_value_total = new BigDecimal("0");
						
						Iterator itItemHM = itemHM.keySet().iterator();
						while(itItemHM.hasNext()){
							BigDecimal item_id = (BigDecimal)itItemHM.next();
							
							Item item = itemHM.get(item_id);
	
			            	if(item.getReserve_price().doubleValue()>0) {
			            		reserve_price_total = reserve_price_total.add(item.getReserve_price());
			            	}else if(item.getSrp().doubleValue()>0) {
			            		srp_total = srp_total.add(item.getSrp());
			            	}else if(item.getTarget_price().doubleValue()>0) {
			            		target_price_total = target_price_total.add(item.getTarget_price());
			            	}else if(item.getAssess_value().doubleValue()>0) {
			            		assess_value_total = assess_value_total.add(item.getAssess_value());
			            	}
							
						}
						
						if(itemHM.size()>0){
							if(reserve_price_total.add(srp_total).add(target_price_total).add(assess_value_total).doubleValue() > 0 ){
								lMngr.updateLotSetLotTotals(lot_id, reserve_price_total ,srp_total, target_price_total, assess_value_total, bid_qualifier_price, 1);
							}
						}
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

	}



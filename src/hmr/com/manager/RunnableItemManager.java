package hmr.com.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hmr.com.bean.Item;
import hmr.com.bean.Lot;

public class RunnableItemManager implements Runnable {
	   private Thread t;
	   private String threadName;
	   private BigDecimal lot_id;
	   private BigDecimal auction_id;
	   private Integer isLast;

	   RunnableItemManager( String threadName, BigDecimal lot_id, BigDecimal auction_id){
	       this.threadName = threadName;
	       this.lot_id = lot_id;
	       this.auction_id = auction_id;
	       System.out.println("Creating " +  threadName +" - "+lot_id+" - "+auction_id  );
	   }
	   
	   RunnableItemManager( String threadName, BigDecimal auction_id){
	       this.threadName = threadName;
	       this.auction_id = auction_id;
	       System.out.println("Creating " +  threadName +" - "+lot_id+" - "+auction_id  );
	   }
	   
	   RunnableItemManager( String threadName, BigDecimal lot_id, BigDecimal auction_id, Integer isLast){
	       this.threadName = threadName;
	       this.lot_id = lot_id;
	       this.auction_id = auction_id;
	       this.isLast = isLast;
	       System.out.println("Creating " +  threadName +" - "+lot_id+" - "+auction_id  );
	   }
	   
	   
	   public void run() {	
	      System.out.println("Running " +  threadName );
	      
    	  

	      
	      try {
	    	  if("lotTotalsCompute".equals(threadName)){
	    		  
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
								lMngr.updateLotSetLotTotals(lot_id, reserve_price_total ,srp_total, target_price_total, assess_value_total, 1);
							}
						}
					}
	    	  }
	    	  
	    	  
				String jsonDataItem = "";
				if(!"".equals(lot_id) && !"".equals(auction_id) && "itemStagingInsert".equals(threadName)){

					System.out.println("asdfasdfasdfsadf ");
					ItemManager iMngr = new ItemManager();
					//HashMap<BigDecimal, Item> itemHM  = iMngr.getItemHMByAuctionId_SetReferenceNo(auction_id);
					HashMap<BigDecimal, Item> itemHM  = iMngr.getItemHMByAuctionId_SetItemId(auction_id);
					
					try{
					URL urlItem = new URL("http://wsr.hmrphils.com/json/get-item-list?lot_id="+lot_id);
					//HttpURLConnection connIem = urlItem.openConnection()!=null ? (HttpURLConnection) urlItem.openConnection() : connIem;
					HttpURLConnection connIem = (HttpURLConnection) urlItem.openConnection();
					connIem.setRequestMethod("GET");
					connIem.setRequestProperty("Accept", "application/json");
					
					if (connIem.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ connIem.getResponseCode());
					}

					BufferedReader brItem = new BufferedReader(new InputStreamReader(
						(connIem.getInputStream())));

					String outputItem = "";
					System.out.println("Output from Server .... \n "+lot_id);
					while ((outputItem = brItem.readLine()) != null) {
						System.out.println(outputItem);
						jsonDataItem += outputItem + "\n";
					}

					connIem.disconnect();

				  } catch (MalformedURLException e) {

				  e.printStackTrace();

				  } catch (IOException e) {

					e.printStackTrace();

				  }
					

					// System.out.println("File Content: \n" + jsonDataobjLot);
						JSONObject objItem = new JSONObject(jsonDataItem);
						System.out.println("jsonDataItem result: " + objItem.getString("result"));
						System.out.println("jsonDataItem data: " + objItem.getJSONArray("data"));
						
						String resultItem = objItem.getString("result");
						
						if("OK".equals(resultItem) && objItem.getJSONArray("data").length() > 0){
							
				
							JSONArray objArrItem = objItem.getJSONArray("data");
							
							boolean isConn2 = false;
							
							for (int i = 0; i < objArrItem.length(); i++) {
							    JSONObject objDataItem = objArrItem.getJSONObject(i);
								//System.out.println("auction_id: " + objData.getString("auction_id"));
								//System.out.println("auction_no: " + objData.getString("auction_no"));
								//System.out.println("auction_date: " + objData.getString("auction_date"));
								//System.out.println("location: " + objData.getString("location"));
								//System.out.println("default_premium: " + objData.getString("default_premium"));

							    String item_id_s = "";
							    
								try{
									item_id_s = objDataItem.getString("item_id");
								}catch(Exception e){
									
								}
							    
							    
							    String status_id_s = "";
							    
								try{
									status_id_s = objDataItem.getString("status_id");
								}catch(Exception e){
									
								}

							    String reference_no_s = "";
							    
								try{
									reference_no_s = objDataItem.getString("reference_no");
								}catch(Exception e){
									
								}
								
							    String pullout_id_s = "";
							    
								try{
									pullout_id_s = objDataItem.getString("pullout_id");
								}catch(Exception e){
									
								}
								
							    String lot_id_s = "";
							    
								try{
									lot_id_s = objDataItem.getString("lot_id");
								}catch(Exception e){
									
								}

							    String amount_bid_s = "";
							    
								try{
									amount_bid_s = objDataItem.getString("amount_bid");
								}catch(Exception e){
									
								}
								
								
							    String reserved_price_s = "";
							    
								try{
									reserved_price_s = objDataItem.getString("reserved_price");
								}catch(Exception e){
									
								}
							    String rate_s = "";
							    
								try{
									rate_s = objDataItem.getString("rate");
								}catch(Exception e){
									
								}
							    String received_items_id_s = "";
							    
								try{
									received_items_id_s = objDataItem.getString("received_items_id");
								}catch(Exception e){
									
								}
							    String qt_remarks_s = "";
							    
								try{
									qt_remarks_s = objDataItem.getString("qt_remarks");
								}catch(Exception e){
									
								}
								
								
							    String official_receipt_id_s = "";
							    
								try{
									official_receipt_id_s = objDataItem.getString("official_receipt_id");
								}catch(Exception e){
									
								}
								
							    String assess_value_s = "";
							    
								try{
									assess_value_s = objDataItem.getString("assess_value");
								}catch(Exception e){
									
								}
							    String payment_status_s = "";
							    
								try{
									payment_status_s = objDataItem.getString("payment_status");
								}catch(Exception e){
									
								}
							    String bidder_number_id_s = "";
							    
								try{
									bidder_number_id_s = objDataItem.getString("bidder_number_id");
								}catch(Exception e){
									
								}
								
							    String product_code_s = "";
							    
								try{
									product_code_s = objDataItem.getString("product_code");
								}catch(Exception e){
									
								}
								
							    String payables_id_s = "";
							    
								try{
									payables_id_s = objDataItem.getString("payables_id");
								}catch(Exception e){
									
								}
								
							    String target_price_s = "";
							    
								try{
									target_price_s = objDataItem.getString("target_price");
								}catch(Exception e){
									
								}
								
							    String srp_s = "";
							    
								try{
									srp_s = objDataItem.getString("srp");
								}catch(Exception e){
									
								}
								
							    String consignor_id_s = "";
							    
								try{
									consignor_id_s = objDataItem.getString("consignor_id");
								}catch(Exception e){
									
								}
								
							    String description_s = "";
							    
								try{
									description_s = objDataItem.getString("description");
								}catch(Exception e){
									
								}
							    String delivery_receipt_id_s = "";
							    
								try{
									delivery_receipt_id_s = objDataItem.getString("delivery_receipt_id");
								}catch(Exception e){
									
								}
								
								String weight_s = "";
								try{
									weight_s = objDataItem.getString("weight");
								}catch(Exception e){
									
								}
								
							    
								BigDecimal item_id = new BigDecimal(0);
								try{
									item_id = new BigDecimal(item_id_s);
								}catch(Exception e){
									
								}
								
								Integer status_id = Integer.valueOf(0);
								try{
									status_id = Integer.valueOf(status_id_s);
								}catch(Exception e){
									
								}
								
								BigDecimal reference_no = new BigDecimal(0);
								try{
									reference_no = new BigDecimal(reference_no_s);
								}catch(Exception e){
									
								}
								
								Integer pullout_id = Integer.valueOf(0);
								try{
									pullout_id = Integer.valueOf(pullout_id_s);
								}catch(Exception e){
									
								}
								

								BigDecimal lot_id = new BigDecimal(0);
								try{
									lot_id = new BigDecimal(lot_id_s);
								}catch(Exception e){
									
								}
								
								BigDecimal reserved_price = new BigDecimal(0);
								try{
									reserved_price = new BigDecimal(reserved_price_s);
								}catch(Exception e){
									
								}
								
								BigDecimal rate = new BigDecimal(0);
								try{
									rate = new BigDecimal(rate_s);
								}catch(Exception e){
									
								}
								
								BigDecimal amount_bid = new BigDecimal(0);
								try{
									amount_bid = new BigDecimal(amount_bid_s);
								}catch(Exception e){
									
								}
								
								Integer received_items_id = Integer.valueOf(0);
								try{
									received_items_id = Integer.valueOf(received_items_id_s);
								}catch(Exception e){
									
								}
								
								
								
								String qt_remarks = qt_remarks_s;
								
								
								BigDecimal assess_value = new BigDecimal(0);
								try{
									assess_value = new BigDecimal(assess_value_s);
								}catch(Exception e){
									
								}
								
								
								Integer payment_status = Integer.valueOf(0);
								try{
									payment_status = Integer.valueOf(payment_status_s);
								}catch(Exception e){
									
								}

								BigDecimal bidder_number_id = new BigDecimal(0);
								try{
									bidder_number_id = new BigDecimal(bidder_number_id_s);
								}catch(Exception e){
									
								}
								
								Integer payables_id = Integer.valueOf(0);
								try{
									payables_id = Integer.valueOf(payables_id_s);
								}catch(Exception e){
									
								}
								
								BigDecimal product_code = new BigDecimal(0);
								try{
									product_code = new BigDecimal(product_code_s);
								}catch(Exception e){
									
								}
								
								BigDecimal target_price = new BigDecimal(0);
								try{
									target_price = new BigDecimal(target_price_s);
								}catch(Exception e){
									
								}
								
								BigDecimal srp = new BigDecimal(0);
								try{
									srp = new BigDecimal(srp_s);
								}catch(Exception e){
									
								}
								
								BigDecimal consignor_id = new BigDecimal(0);
								try{
									consignor_id = new BigDecimal(consignor_id_s);
								}catch(Exception e){
									
								}
								
								
								
								BigDecimal delivery_receipt_id = new BigDecimal(0);
								try{
									delivery_receipt_id = new BigDecimal(delivery_receipt_id_s);
								}catch(Exception e){
									
								}

								String description = description_s;
								
								
								
								BigDecimal weight = new BigDecimal(0);
								try{
									weight = new BigDecimal(weight_s);
								}catch(Exception e){
									
								}
								
								try{
									
									System.out.println(item_id.doubleValue()+" "+lot_id.doubleValue());
									
									if(item_id!=null && lot_id!=null && item_id.doubleValue() > 0 && lot_id.doubleValue() > 0){
										
										//AuctionManager aMngr = new AuctionManager(req,res);
										iMngr = new ItemManager();
										
										//HashMap<BigDecimal, Item> itemHM  = lMngr.getItemHMByLotId(lot_id);

										Item item = itemHM.get(item_id);
										Timestamp last_date_sync = null;
										
										
										
										try{
											last_date_sync = item.getSynched_date();
										}catch(Exception e){
											
										}
										
										System.out.println("item "+item_id+" - "+last_date_sync);
										
										ItemStagingManager isMngr = new ItemStagingManager();
										
										if(isConn2==false){
											

											isMngr.insertItemStagingOnSearch(
													item_id,
													lot_id,
													status_id,
													reference_no,
													pullout_id,
													target_price,
													reserved_price,
													rate,
													amount_bid,
													received_items_id,
													qt_remarks,
													assess_value,
													payment_status,
													bidder_number_id,
													payables_id,
													product_code,
													srp,
													consignor_id,
													description,
													delivery_receipt_id,
													weight,
													last_date_sync
													);
											
											
											isConn2 = true;

										}else{
											
											
											isMngr.insertItemStagingOnSearch2(
													item_id,
													lot_id,
													status_id,
													reference_no,
													pullout_id,
													target_price,
													reserved_price,
													rate,
													amount_bid,
													received_items_id,
													qt_remarks,
													assess_value,
													payment_status,
													bidder_number_id,
													payables_id,
													product_code,
													srp,
													consignor_id,
													description,
													delivery_receipt_id,
													weight,
													last_date_sync
													);
											
											
											isConn2 = false;
											
											Thread.sleep(2000);
											
			
											
										}
										
										
										
									}
									
									

								}catch(Exception e){
									e.printStackTrace();
								}
								
								
								
								
								
							}
							
							

							
						}
						
						//Thread.sleep(1000);
						
					}
	    	  

				
	            //Thread.sleep(1000);
	            
	     } catch (JSONException e) {
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



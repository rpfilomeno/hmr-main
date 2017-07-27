package hmr.com.manager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hmr.com.bean.Auction;
import hmr.com.bean.AuctionStaging;
import hmr.com.bean.Item;
import hmr.com.bean.ItemStaging;
import hmr.com.bean.Lot;
import hmr.com.bean.LotStaging;
import hmr.com.bean.Lov;
import hmr.com.dao.LovDao;
import hmr.com.util.EmailUtil;
import hmr.com.util.StringUtil;


public class UploadAuctionManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	public static final DateFormat HMR_DATE_FMT = new SimpleDateFormat("yyyy-MM-dd");
	
	public UploadAuctionManager(){}

	public UploadAuctionManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doUploadAuctionManager(){
		String page = null;
		String action = req.getParameter("action")!=null ? req.getParameter("action") : "";
		
		
		System.out.println("Paramerters doUploadAuctionManager - start");
		System.out.println("action : "+action);
		System.out.println("Paramerters doUploadAuctionManager - end");
		System.out.println("");
		
		
		if(action.equals("uploadAuctionSearch")){
			
			
			//String firstName = req.getParameter("firstName");
			//String lastName = req.getParameter("lastName");
			//String userId = req.getParameter("userId");
			//BigDecimal mobileNo = new BigDecimal(req.getParameter("mobileNo"));
			AuctionStagingManager asMngr = new AuctionStagingManager(req,res);
			AuctionManager aMngr = new AuctionManager();
			List<AuctionStaging> asList = asMngr.getAuctionStagingList();
			req.setAttribute("auctionStagingList", asList);
			
			page = "upload-auction-search.jsp";
			
		}else if(action.equals("searchUploadAuction")){
			
			BigDecimal auction_no = !req.getParameter("auction_no").equals("") ? new BigDecimal(req.getParameter("auction_no")) : new BigDecimal(0);
			//String auction_date = req.getParameter("auction_date")!=null ? req.getParameter("auction_date") : "";

			try {
				UploadAuctionManager uaMngr = new UploadAuctionManager(req,res);
				uaMngr.processUploadAuction(auction_no);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			AuctionStagingManager asMngr = new AuctionStagingManager(req,res);
			AuctionManager aMngr = new AuctionManager();
			
			LotStagingManager lsMngr = new LotStagingManager(req,res);
			LotManager lMngr = new LotManager();
			
			ItemStagingManager isMngr = new ItemStagingManager(req,res);
			ItemManager iMngr = new ItemManager();
			
			List<AuctionStaging> asList = asMngr.getAuctionStagingList();
			List<LotStaging> lsList = lsMngr.getLotStagingList();
			List<ItemStaging> isList = isMngr.getItemStagingList();
			
			req.setAttribute("auctionStagingList", asList);
			req.setAttribute("lotStagingList", lsList);
			req.setAttribute("itemStagingList", isList);
			
			page = "upload-auction-search.jsp";
			
		}else if(action.equals("viewUploadAuction")){

			UploadAuctionManager uaMngr = new UploadAuctionManager(req,res);
			
			AuctionStagingManager asMngr = new AuctionStagingManager(req,res);
			AuctionManager aMngr = new AuctionManager();
			
			LotStagingManager lsMngr = new LotStagingManager(req,res);
			LotManager lMngr = new LotManager();
			
			ItemStagingManager isMngr = new ItemStagingManager(req,res);
			ItemManager iMngr = new ItemManager();
			
			List<AuctionStaging> asList = asMngr.getAuctionStagingList();
			List<LotStaging> lsList = lsMngr.getLotStagingList();
			List<ItemStaging> isList = isMngr.getItemStagingList();
			
			req.setAttribute("auctionStagingList", asList);
			req.setAttribute("lotStagingList", lsList);
			req.setAttribute("itemStagingList", isList);
			
			page = "upload-auction-list.jsp";
			
		}else if(action.equals("syncAll")){
			
			
			try {
				UploadAuctionManager uaMngr = new UploadAuctionManager(req,res);
				uaMngr.processSyncAll();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			AuctionStagingManager asMngr = new AuctionStagingManager(req,res);
			AuctionManager aMngr = new AuctionManager();
			
			LotStagingManager lsMngr = new LotStagingManager(req,res);
			LotManager lMngr = new LotManager();
			
			ItemStagingManager isMngr = new ItemStagingManager(req,res);
			ItemManager iMngr = new ItemManager();
			
			List<AuctionStaging> asList = asMngr.getAuctionStagingList();
			List<LotStaging> lsList = lsMngr.getLotStagingList();
			List<ItemStaging> isList = isMngr.getItemStagingList();
			
			req.setAttribute("auctionStagingList", asList);
			req.setAttribute("lotStagingList", lsList);
			req.setAttribute("itemStagingList", isList);
			
			page = "upload-auction-list.jsp";
			
		}
		
		
		
		
	
		
		System.out.println("Paramerters doUploadAuctionManager - page : "+page);
		
		return page;
		
	}
	


	
	
	
	
	


	public static BigDecimal processUploadAuction(BigDecimal auction_no_param) throws JSONException {
		BigDecimal auction_no_ret = new BigDecimal(0);
		String jsonData = "";
		
		//BigDecimal auction_no_param = new BigDecimal("1658");
		String auction_date_param = "";
		
		
		Date auction_date_param_d = null; 
		String auction_date_param_s = null;

		    try {
		    	auction_date_param_d = INPUT_DATE_FMT.parse(auction_date_param);
		    	auction_date_param_s = HMR_DATE_FMT.format(auction_date_param_d);
		    } catch (ParseException e) {}
		
		
		  
		
		/*
        java.sql.Date start_date_time_d = null;
        java.sql.Timestamp start_date_time_t = null;
        if(auction_date_param_d!=null){
        	start_date_time_d = new java.sql.Date(start_date_time.getTime());
        	start_date_time_t = new java.sql.Timestamp(auction_date_param_d.getTime());
        }
		*/
		    
		  try {


			URL urlAuction = new URL("http://wsr.hmrphils.com/json/get-auction-list");
			HttpURLConnection connAuction = (HttpURLConnection) urlAuction.openConnection();
			connAuction.setRequestMethod("GET");
			connAuction.setRequestProperty("Accept", "application/json");
			
			if (connAuction.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ connAuction.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(connAuction.getInputStream())));

			String outputAuction;
			System.out.println("Output from Server .... \n");
			while ((outputAuction = br.readLine()) != null) {
				System.out.println(outputAuction);
				jsonData += outputAuction + "\n";
			}

			connAuction.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

		  
		  //test
		  
		  //jsonData = "{result:OK, data: [ {\"auction_id\":\"1\",\"auction_no\":\"1606\",\"auction_date\":\"2012-11-07\",\"location\":\"HMR AUCTION - BALINTAWAK\",\"default_premium\":\"15\"}] }";
		  //
		  
		  //jsonData = "{result:OK, data: [ {\"auction_id\":\"31\",\"auction_no\":\"1663\",\"auction_date\":\"2013-03-02\",\"location\":\"HMR\",\"default_premium\":\"15\"}] }";
		  int lotcount = 1;
		  
		  if(!"".equals(jsonData)){
			  
				LotStagingManager lsMngr = new LotStagingManager();
				ItemStagingManager isMngr = new ItemStagingManager();
			  
			  
				// System.out.println("File Content: \n" + jsonData);
					JSONObject obj = new JSONObject(jsonData);
					System.out.println("result: " + obj.getString("result"));
					System.out.println("data: " + obj.getJSONArray("data"));
					
					String result = obj.getString("result");
					
					if("OK".equals(result) && obj.getJSONArray("data").length() > 0){
						
						//AuctionStagingManager asMngr = new AuctionStagingManager(req,res);
						AuctionStagingManager asMngr = new AuctionStagingManager();
						
						asMngr.deleteAuctionStagingOnSearch();
						lsMngr.deleteLotStagingOnSearch();
						isMngr.deleteItemStagingOnSearch();

						JSONArray objArr = obj.getJSONArray("data");
						
						
						for (int i = 0; i < objArr.length(); i++) {
						    JSONObject objData = objArr.getJSONObject(i);
							//System.out.println("auction_id: " + objData.getString("auction_id"));
							//System.out.println("auction_no: " + objData.getString("auction_no"));
							//System.out.println("auction_date: " + objData.getString("auction_date"));
							//System.out.println("location: " + objData.getString("location"));
							//System.out.println("default_premium: " + objData.getString("default_premium"));
							
							String auction_id_s = objData.getString("auction_id");
							String auction_no_s = objData.getString("auction_no");
							String auction_date_s = objData.getString("auction_date");
							String location_s = objData.getString("location");
							String default_premium_s = objData.getString("default_premium");
							
							BigDecimal auction_id = new BigDecimal(auction_id_s);
							BigDecimal auction_no = new BigDecimal(0);
							try{
								auction_no = new BigDecimal(auction_no_s);
							}catch(Exception e){
								
							}
							//Date auction_date = new BigDecimal(auction_date_s);
							
							Date auction_date = null; 
							if(!"".equals(auction_date_s) && !"0000-00-00".equals(auction_date_s))
							{
							    try {
							    	auction_date = HMR_DATE_FMT.parse(auction_date_s);
							    } catch (ParseException e) {}
							}
							

							String location = location_s;
							BigDecimal default_premium = new BigDecimal(default_premium_s);
							
							try{
								
								if(auction_no_param!=null && auction_no.equals(auction_no_param)){
									
									auction_no_ret = auction_no;
									
									//AuctionManager aMngr = new AuctionManager(req,res);
									AuctionManager aMngr = new AuctionManager();
									Auction a = aMngr.getAuctionByAuctionId(auction_id);
									
									Timestamp last_sync_date = null;
									if(a!=null && a.getDate_sync()!=null){
										last_sync_date = a.getDate_sync();
									}
									
									
									asMngr.insertAuctionStagingOnSearch(auction_id, auction_no, auction_date, location, default_premium, last_sync_date);
								}
								
								if(auction_date_param_s!=null && auction_date_s.equals(auction_date_param_s)){
									
									//AuctionManager aMngr = new AuctionManager(req,res);
									AuctionManager aMngr = new AuctionManager();
									Auction a = aMngr.getAuctionByAuctionId(auction_id);
									
									Timestamp last_sync_date = null;
									if(a!=null && a.getDate_sync()!=null){
										last_sync_date = a.getDate_sync();
									}
									
									asMngr.insertAuctionStagingOnSearch(auction_id, auction_no, auction_date, location, default_premium, last_sync_date);
								}
								

							}catch(Exception e){
								e.printStackTrace();
							}
							
							
							
							
							
						}
						
						
						
						
						// Search all lots existing in auction stagging
						asMngr = new AuctionStagingManager();
						List<AuctionStaging> asList = asMngr.getAuctionStagingList();

						if(asList!=null && asList.size() > 0){
							
							String jsonDataLot = "";
							
							
							for(AuctionStaging as : asList){

								
								
								try{
								URL urlLot = new URL("http://wsr.hmrphils.com/json/get-lot-list?auction_id="+as.getAuction_id());
								HttpURLConnection connLot = (HttpURLConnection) urlLot.openConnection();
								connLot.setRequestMethod("GET");
								connLot.setRequestProperty("Accept", "application/json");
								
								if (connLot.getResponseCode() != 200) {
									throw new RuntimeException("Failed : HTTP error code : "
											+ connLot.getResponseCode());
								}

								BufferedReader brLot = new BufferedReader(new InputStreamReader(
									(connLot.getInputStream())));

								String outputLot = "";
								System.out.println("Output from Server .... \n");
								while ((outputLot = brLot.readLine()) != null) {
									System.out.println(outputLot);
									jsonDataLot += outputLot + "\n";
								}

								connLot.disconnect();

							  } catch (MalformedURLException e) {

							  e.printStackTrace();

							  } catch (IOException e) {

								e.printStackTrace();

							  }
								
							}
							
							

							//jsonDataLot = "{result:OK, data: [ {\"lot_id\":\"64509\",\"lot_number\":\"79\",\"auction_id\":\"110\",\"is_available_lot\":\"0\",\"lot_description\":\"CHICBOY SIGNAGE AND CANOPY\",\"date_created\":\"2014-02-06 18:37:33\",\"lot_type_id\":null,\"premium_rate\":\"15\",\"unit\":\"LOT\",\"unit_qty\":\"1\",\"bid_timestamp\":\"2014-02-13 10:24:56\",\"vat\":null,\"duties\":null,\"assessment_value\":null} ]}";
							
							
							if(!"".equals(jsonDataLot)){
								  
								  
								// System.out.println("File Content: \n" + jsonDataobjLot);
									JSONObject objLot = new JSONObject(jsonDataLot);
									System.out.println("result: " + objLot.getString("result"));
									System.out.println("data: " + objLot.getJSONArray("data"));
									
									String resultLot = objLot.getString("result");
									
									if("OK".equals(resultLot) && objLot.getJSONArray("data").length() > 0){
										
										//AuctionStagingManager asMngr = new AuctionStagingManager(req,res);
										lsMngr = new LotStagingManager();
										isMngr = new ItemStagingManager();
										
										

										JSONArray objArrLot = objLot.getJSONArray("data");
										
										
										for (int i = 0; i < objArrLot.length(); i++) {
										    JSONObject objDataLot = objArrLot.getJSONObject(i);
											//System.out.println("auction_id: " + objData.getString("auction_id"));
											//System.out.println("auction_no: " + objData.getString("auction_no"));
											//System.out.println("auction_date: " + objData.getString("auction_date"));
											//System.out.println("location: " + objData.getString("location"));
											//System.out.println("default_premium: " + objData.getString("default_premium"));

										    String lot_id_s = objDataLot.getString("lot_id");
										    String lot_number_s = objDataLot.getString("lot_number");
										    String auction_id_s = objDataLot.getString("auction_id");
										    String is_available_lot_s = objDataLot.getString("is_available_lot");
										    String lot_description_s = objDataLot.getString("lot_description");
										    String lot_type_id_s = "";
										    
										    try{
										    	lot_type_id_s = objDataLot.getString("lot_type_id") ;
										    }catch(Exception e){
										    	
										    }
										    
										    
										    String premium_rate_s = "";
										    
										    try{
										    	premium_rate_s = objDataLot.getString("premium_rate") ;
										    }catch(Exception e){
										    	
										    }
										    
										    String unit_s = "";
										    
										    try{
										    	unit_s = objDataLot.getString("unit") ;
										    }catch(Exception e){
										    	
										    }
										    
										    String unit_qty_s = "";
										    
										    try{
										    	unit_qty_s = objDataLot.getString("unit_qty") ;
										    }catch(Exception e){
										    	
										    }
										    
										    String vat_s  = "";
										    
										    try{
										    	vat_s = objDataLot.getString("vat") ;
										    }catch(Exception e){
										    	
										    }
											String duties_s = "";
										    
										    try{
										    	duties_s = objDataLot.getString("duties") ;
										    }catch(Exception e){
										    	
										    }
											String assessment_value_s  = "";
										    
										    try{
										    	assessment_value_s = objDataLot.getString("assessment_value") ;
										    }catch(Exception e){
										    	
										    }

											BigDecimal lot_id = new BigDecimal(lot_id_s);
											BigDecimal auction_id = new BigDecimal(auction_id_s);
											BigDecimal lot_number = new BigDecimal(0);
											Integer is_available_lot = Integer.valueOf(0);
											String lot_description = lot_description_s;
											Integer lot_type_id = Integer.valueOf(0);
											BigDecimal premium_rate = new BigDecimal(0);
											String unit = unit_s;
											Integer unit_qty = Integer.valueOf(0);
											
											BigDecimal vat = new BigDecimal(0); 
											BigDecimal duties  = new BigDecimal(0);
											BigDecimal assessment_value = new BigDecimal(0);
											
											try{
												lot_number = new BigDecimal(lot_number_s);
											}catch(Exception e){
												
											}
											
											try{
												is_available_lot = Integer.valueOf(is_available_lot_s);
											}catch(Exception e){
												
											}
											
											try{
												lot_type_id = Integer.valueOf(lot_type_id_s);
											}catch(Exception e){
												
											}
											
											try{
												premium_rate = new BigDecimal(premium_rate_s);
											}catch(Exception e){
												
											}
											
											try{
												unit = unit_s;
											}catch(Exception e){
												
											}
											
											try{
												unit_qty = Integer.valueOf(unit_qty_s);
											}catch(Exception e){
												
											}
											
											try{
												vat = new BigDecimal(vat_s);
											}catch(Exception e){
												
											}
											
											try{
												duties = new BigDecimal(duties_s);
											}catch(Exception e){
												
											}
											
											try{
												assessment_value = new BigDecimal(assessment_value_s);
											}catch(Exception e){
												
											}
										
											
											//Date auction_date = new BigDecimal(auction_date_s);
											/*
											Date auction_date = null; 
											if(!"".equals(auction_date_s) && !"0000-00-00".equals(auction_date_s))
											{
											    try {
											    	auction_date = HMR_DATE_FMT.parse(auction_date_s);
											    } catch (ParseException e) {}
											}
											*/

											
											try{
												
												if(auction_id!=null && !"".equals(auction_id)){
													
													//AuctionManager aMngr = new AuctionManager(req,res);
													LotManager lMngr = new LotManager();
													
													//HashMap<BigDecimal, Lot> lotHM  = lMngr.getLotHMByAuctionId(auction_id);
													
													HashMap<BigDecimal, Lot> lotHM  = lMngr.getLotHMByAuctionId_SetLotId(auction_id);
													
													
													Lot l = lotHM.get(lot_id);
													Timestamp last_date_sync = null;
													
													try{
														last_date_sync = l.getDate_sync();
													}catch(Exception e){
														
													}
													
													if(lotcount<500){
														
														lsMngr.insertLotStagingOnSearch(
																lot_id,
																auction_id,
																lot_number,
																is_available_lot,
																lot_description,
																lot_type_id,
																premium_rate,
																unit,
																unit_qty,
																vat,
																duties,
																assessment_value,
																last_date_sync
																);
														
														lotcount++;
														
													}


												}
												
												

											}catch(Exception e){
												e.printStackTrace();
											}
											
											
											
											
											
										}
										
									}
								}

							
						}
						

						List<LotStaging> lsList = lsMngr.getLotStagingList();
						//List<ItemStaging> isList = isMngr.getItemStagingList();
						//URL urlItem = null;
						//HttpURLConnection connIem = null;
						//lsList = new ArrayList<LotStaging>();
						//LotStaging addLot = new LotStaging();
						//addLot.setLot_id(new BigDecimal("64509"));
						//lsList.add(addLot) ; 
						
						if(lsList!=null && lsList.size() > 0){

							String jsonDataItem = "";

							for(LotStaging ls :lsList){
								
								RunnableItemManager rim = new RunnableItemManager("itemStagingInsert", ls.getLot_id(), ls.getAuction_id());
								rim.start();
										/*
								try{
								URL urlItem = new URL("http://wsr.hmrphils.com/json/get-item-list?lot_id="+ls.getLot_id());
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
								System.out.println("Output from Server .... \n "+ls.getLot_id());
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
								*/
								
								
							}
							//connIem.disconnect();
							
							//jsonDataItem =  "{result:OK, data: [ {\"item_id\":\"1717840\",\"status_id\":\"1\",\"reference_no\":\"6821717840\",\"pullout_id\":null,\"lot_id\":\"64509\",\"reserved_price\":\"2000\",\"rate\":\"17\",\"amount_bid\":null,\"received_items_id\":\"97970\",\"time_created\":\"2014-02-06 18:23:06\",\"qt_remarks\":null,\"official_receipt_id\":null,\"assess_value\":null,\"payment_status\":null,\"bidder_number_id\":null,\"payables_id\":null,\"product_code\":null,\"target_price\":\"0.00\",\"srp\":\"0.00\",\"created_by\":null,\"consignor_id\":\"682\",\"date_received\":\"2014-02-06\",\"description\":\"79 - CHICBOY SIGNAGE AND CANOPY ( LOT )\",\"delivery_receipt_id\":\"2933\"} ]}";
							
							//System.out.println("jsonDataItem "+jsonDataItem);
							
							
							
						}
						
						
						
					}else{
						
					}
					
					
					
					
			  
			  
		  }else{
			  //no data
		  }



			
			
			
			/*
			JSONObject objData = new JSONObject(objArr);
			System.out.println("auction_id: " + objData.getString("auction_id"));
			System.out.println("auction: " + objData.getString("auction"));
			System.out.println("auction_date: " + objData.getJSONObject("auction_date"));
			System.out.println("location: " + objData.getJSONObject("location"));
			System.out.println("default_premium: " + obj.getJSONObject("default_premium"));
			*/
		  
		  return auction_no_ret;
		  
		}
	
		

	
	
	public static void processSyncAll() throws JSONException {
		
	
		AuctionStagingManager asMngr = new AuctionStagingManager();
		AuctionManager aMngr = new AuctionManager();
		
		LotStagingManager lsMngr = new LotStagingManager();
		LotManager lMngr = new LotManager();
		
		ItemStagingManager isMngr = new ItemStagingManager();
		ItemManager iMngr = new ItemManager();
		
		List<AuctionStaging> asList = asMngr.getAuctionStagingList();
		List<LotStaging> lsList = lsMngr.getLotStagingList();
		List<ItemStaging> isList = isMngr.getItemStagingList();

		Timestamp last_date_sync = new Timestamp(new Date().getTime());
		//BigDecimal user_id = new BigDecimal(1);
		Integer user_id = Integer.valueOf(1);
		
		HashMap<BigDecimal, Item> iHM = null;
		
		if(asList!=null && asList.size() > 0){
			
			for(AuctionStaging as : asList){
				
				Auction a = aMngr.getAuctionByAuctionId(as.getAuction_id());
				if(a!=null && a.getAuction_id()!=null){
					//update auction
					aMngr.updateAuctionOnUpload(as.getAuction_id(), 
							as.getAuction_no(), as.getAuction_date(), as.getLocation(), as.getDefault_premium(), last_date_sync, user_id);
				}else{
					//insert auction
					
					String auction_name = "Auction <auction_no>";
					try{
						auction_name = "Auction "+as.getAuction_no();
					}catch(Exception ex){
						
					}
					
					//iHM = iMngr.getItemHMByAuctionId_SetReferenceNo(as.getAuction_id());
					
					Auction ai = aMngr.insertAuctionOnUpload(as.getAuction_id(), 
							as.getAuction_no(), as.getAuction_date(), as.getLocation(), as.getDefault_premium(), auction_name, last_date_sync, user_id);
					
					if(ai!=null && ai.getAuction_id()!=null){
						AuctionRangeManager arMngr = new AuctionRangeManager();
						
						arMngr.insertAuctionRangeOnCreate(as.getAuction_id(), new BigDecimal(1), new BigDecimal(100), new BigDecimal(50), user_id);
						arMngr.insertAuctionRangeOnCreate(as.getAuction_id(), new BigDecimal(101), new BigDecimal(1000), new BigDecimal(100), user_id);
						arMngr.insertAuctionRangeOnCreate(as.getAuction_id(), new BigDecimal(1001), new BigDecimal(10000), new BigDecimal(500), user_id);
						arMngr.insertAuctionRangeOnCreate(as.getAuction_id(), new BigDecimal(10001), new BigDecimal(10000000), new BigDecimal(5000), user_id);
					}
				}
				
				
				for(LotStaging ls : lsList){
					HashMap<BigDecimal, Lot> lHM = lMngr.getLotHMByAuctionId_SetLotId(as.getAuction_id());
					if(lHM.get(ls.getLot_id())!=null){
						//update lot
						lMngr.updateLotOnUpload(
								ls.getLot_id(),
								ls.getAuction_id(),
								ls.getLot_number(),
								ls.getIs_available_lot(),
								ls.getLot_description(),
								ls.getLot_type_id(),
								ls.getPremium_rate(),
								ls.getUnit(),
								ls.getUnit_qty(),
								ls.getVat(),
								ls.getDuties(),
								ls.getAssessment_value(),
								last_date_sync,
									user_id);
					}else{
						//insert lot
						
						String lot_name = "";
						if(ls.getLot_description()!=null && ls.getLot_description().indexOf(",") > 0){
							lot_name = ls.getLot_description().substring(0, ls.getLot_description().indexOf(","));
						}else{
							
							
							if(ls.getLot_description()!=null && ls.getLot_description().indexOf("-") > 0){
								lot_name = ls.getLot_description().substring(0, ls.getLot_description().indexOf("-"));
							}else{
								lot_name = ls.getLot_description();
							}
							
						}
						
						if(lot_name!=null && lot_name.indexOf("(") > 0){
							lot_name = lot_name.substring(0, ls.getLot_description().indexOf("("));
						}
						
						
						lMngr.insertLotOnUpload(
								ls.getLot_id(),
								ls.getAuction_id(),
								ls.getLot_number(),
								ls.getIs_available_lot(),
								ls.getLot_description(),
								ls.getLot_type_id(),	
								ls.getPremium_rate(),
								ls.getUnit(),
								ls.getUnit_qty(),
								ls.getVat(),
								ls.getDuties(),
								ls.getAssessment_value(),
								lot_name,
								last_date_sync,
									user_id);
						
						
					}
					
					for(ItemStaging is : isList){
						
						System.out.println("isList "+isList.size());
						
						iHM = iMngr.getItemHMByAuctionId_SetReferenceNo(ls.getAuction_id());
						if(iHM.get(is.getReference_no())!=null){
							//update item
							
							iMngr.updateItemOnUpload(
									is.getItem_id(),
									is.getLot_id(),
									is.getStatus_id(),
									is.getReference_no(),
									is.getPullout_id(),
									is.getTarget_price(),
									is.getReserve_price(),
									is.getRate(),
									is.getAmount_bid(),
									is.getReceived_items_id(),
									is.getQt_remarks(),
									is.getAssess_value(),
									is.getPayment_status(),
									is.getBidder_number_id(),
									is.getPayables_id(),
									is.getProduct_code(),
									is.getSrp(),
									is.getConsignor_id(),
									is.getDescription(),
									is.getDelivery_receipt_id(),
									ls.getAuction_id(),
									last_date_sync,
									user_id
							);
							
							
						}else{
							//insert item
							
							iMngr.insertItemOnUpload(
									is.getItem_id(),
									is.getLot_id(),
									is.getStatus_id(),
									is.getReference_no(),
									is.getPullout_id(),
									is.getTarget_price(),
									is.getReserve_price(),
									is.getRate(),
									is.getAmount_bid(),
									is.getReceived_items_id(),
									is.getQt_remarks(),
									is.getAssess_value(),
									is.getPayment_status(),
									is.getBidder_number_id(),
									is.getPayables_id(),
									is.getProduct_code(),
									is.getSrp(),
									is.getConsignor_id(),
									is.getDescription(),
									is.getDelivery_receipt_id(),
									ls.getAuction_id(),
									last_date_sync,
									user_id
							);
							
						}
					}
				
					
				}
				

			}
			
			

			
			
			
			
		}
		
		
		
		}
	
	
	
	public static void main(String[] args) throws JSONException {
		
		BigDecimal auction_no_param = new BigDecimal("2180");
		
		processUploadAuction(auction_no_param);
		processSyncAll();
	}
	
	
}

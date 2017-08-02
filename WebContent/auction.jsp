<!DOCTYPE html>
<%@ page import="hmr.com.manager.ImageManager"
         import="hmr.com.bean.Image"
         import="hmr.com.bean.User"
		 import="hmr.com.bean.Auction"
		 import="hmr.com.bean.AuctionUser"
		 import="hmr.com.bean.Lov"
		 import="hmr.com.bean.Lot"
		 import="hmr.com.bean.Item"
		 
		 import="java.util.HashMap" 
		 import="java.util.List" 
		 import="java.math.BigDecimal" 
		 import="java.util.ArrayList"  
		 import="javax.servlet.RequestDispatcher"
		 import="java.text.SimpleDateFormat"
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%
		System.out.println("asdf");
	
		String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";

		String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
		String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
		  
		  
		String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
		String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
		String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : (String)request.getSession().getAttribute("userId");
		BigDecimal mobileNo = request.getAttribute("mobileNo")!=null ? (BigDecimal)request.getAttribute("mobileNo") : null;
		  
		//IDS
		Integer user_id = request.getAttribute("user-id")!=null ? (Integer)request.getAttribute("user-id") : (Integer)request.getSession().getAttribute("user-id");
		  
		Auction auction = request.getAttribute("auction")!=null ? (Auction)request.getAttribute("auction") : (Auction)request.getSession().getAttribute("auction");
		HashMap<Integer,User> coordinatorUserHM = (HashMap<Integer,User>)request.getSession().getAttribute("COORDINATOR-USER-HM");
		
		HashMap<Integer,User> bidderUserHM = request.getAttribute("BIDDER-USER-HM")!=null ? (HashMap<Integer,User>)request.getAttribute("BIDDER-USER-HM") : (HashMap<Integer,User>)request.getSession().getAttribute("BIDDER-USER-HM");
		HashMap<Integer,Lov> auctionUserStatusHM = (HashMap<Integer,Lov>) request.getSession().getAttribute("AUCTION-USER-STATUS-HM");
		
		System.out.println(" bidderUserHM : "+bidderUserHM);
		System.out.println(" auctionUserStatusHM : "+auctionUserStatusHM);
		
		
		List<AuctionUser> auList =(ArrayList<AuctionUser>) request.getAttribute("auctionUserList");
		System.out.println("auList "+auList.size());
		
		
		System.out.println("userId : "+userId);
		System.out.println("user_id : "+user_id);
		
		
		List<Lot> lList =(ArrayList<Lot>) request.getAttribute("lotList");
		List<Item> iList =(ArrayList<Item>) request.getAttribute("itemList");
		//System.out.println("iList "+iList.size());
		//System.out.println("lList "+lList.size());
		//System.out.println("auction : "+auction.getAuction_type());
		
		/*
		List<User> uList =(ArrayList<User>) request.getAttribute("userList");
		System.out.println("ulList "+uList.size());
		*/
		
		ArrayList<Lov> userRoleList = (ArrayList<Lov>) request.getSession().getAttribute("USER-ROLE-LIST");
		ArrayList<Lov> genderList = (ArrayList<Lov>) request.getSession().getAttribute("GENDER-LIST");
		ArrayList<Lov> userStatusList = (ArrayList<Lov>) request.getSession().getAttribute("USER-STATUS-LIST");
		
		
		//ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getSession().getAttribute("USER-COORDINATOR-LIST");
		ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getAttribute("userCoordinatorList");
		HashMap<Integer,Lov> catLev1LovHM =  (HashMap<Integer,Lov>)request.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-HM");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			
	%>
    <title><%=COMPANY_NAME%></title>

	<!-- Page: auction.jsp -->

    <!-- DataTables -->
    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">

	<!-- Local CSS -->
    <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
    <link href="css/select2.min.css" type="text/css" rel="stylesheet" />

    <!-- Favicon -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="shortcut icon" href="ico/hmr-favicon.ico">

    <!-- CSS Global -->
    <link href="assets/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="assets/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="assets/plugins/fontawesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/plugins/prettyphoto/css/prettyPhoto.css" rel="stylesheet">
    <link href="assets/plugins/owl-carousel2/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="assets/plugins/owl-carousel2/assets/owl.theme.default.min.css" rel="stylesheet">
    <link href="assets/plugins/animate/animate.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="assets/css/theme.css" rel="stylesheet">
<link href="assets/css/theme-hmr.css" rel="stylesheet" id="theme-config-link">
    <!-- Head Libs -->
    
    <script src="assets/plugins/modernizr.custom.js"></script>
	
	<!-- CK Editor -->
	<!-- 
	<script src="cdn.ckeditor.com/standard-4-4-3/ckeditor.js"></script> -->
	<!-- Bootstrap WYSIHTML5 -->
	    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">


	<script>
	   function trim (el) {
		    el.value = el.value.
		       replace (/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		       replace (/[ ]{2,}/gi," ").       // replaces multiple spaces with one space 
		       replace (/\n +/,"\n");           // Removes spaces after newlines
		    return;
		}
	</script>
    <!--[if lt IE 9]>
    <script src="assets/plugins/iesupport/html5shiv.js"></script>
    <script src="assets/plugins/iesupport/respond.min.js"></script>
    <![endif]-->
</head>
<body id="home" class="wide">
<!-- PRELOADER -->
<jsp:include page="hmr-preloader.jsp" />
<!-- /PRELOADER -->

<!-- WRAPPER -->
<div class="wrapper">

    <!-- Popup: Shopping cart items -->
    <div class="modal fade popup-cart" id="popup-cart" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="container">
                <div class="cart-items">
                    <div class="cart-items-inner">
                        <div class="media">
                            <a class="pull-left" href="#"><img class="media-object item-image" src="assets/img/preview/shop/order-1s.jpg" alt=""></a>
                            <p class="pull-right item-price">$450.00</p>
                            <div class="media-body">
                                <h4 class="media-heading item-title"><a href="#">1x Standard Product</a></h4>
                                <p class="item-desc">Lorem ipsum dolor</p>
                            </div>
                        </div>
                        <div class="media">
                            <p class="pull-right item-price">$450.00</p>
                            <div class="media-body">
                                <h4 class="media-heading item-title summary">Subtotal</h4>
                            </div>
                        </div>
                        <div class="media">
                            <div class="media-body">
                                <div>
                                    <a href="#" class="btn btn-theme btn-theme-dark" data-dismiss="modal">Close</a><!--
                                    --><a href="shopping-cart.html" class="btn btn-theme btn-theme-transparent btn-call-checkout">Checkout</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /Popup: Shopping cart items -->

    <!-- HEADER -->
	<jsp:include page="hmr-header.jsp" />
    <!-- /HEADER -->

    <!-- CONTENT AREA -->
    <div class="content-area">



        <!-- PAGE -->
        
        
 	        <form action="bid" name="frm" id="frm" method="post">
		       <input type="hidden" name="manager" id="manager" value="auction-manager"/>
		       <input type="hidden" name="action" id="action" value=""/>
		       <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
		       <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
		       <input type="hidden" name="auctionId_wip" id="auctionId_wip" value="<%=auction.getAuction_id()%>"/>
		       <input type="hidden" name="auction_id" id="auction_id" value="<%=auction.getAuction_id()%>"/>
		       <input type="hidden" name="lotId_wip" id="lotId_wip" value=""/>
		       <input type="hidden" name="lot_id" id="lot_id" value=""/>
		       <input type="hidden" name="itemId_wip" id="itemId_wip" value=""/>
		       <input type="hidden" name="auctionUserId_wip" id="auctionUserId_wip" value=""/>
		       
		       
        <section class="page-section color" style="padding:15px;">
        
            <div class="container">
                <div class="row" >

                    <div class="col-sm-12">
                        <h3 class="block-title"><span>Auctions  <label>Details </label></span></h3>
                        <div id="msgDiv"></div>
 					</div>

              <%-- 	
            <div class="col-md-12">
              <div class="box box-info">
                <div class="box-header">
                  <h3 class="box-title"><label><b>Terms and Conditions : </b></label></h3>
                </div><!-- /.box-header -->
                <div class="box-body pad">
                  
                    <textarea id="editor-2" name="editor-2" rows="10" cols="80">
                                            
                    </textarea>
                  
                </div>
              </div><!-- /.box -->
              </div>		
 					--%>
					<div class="col-sm-4">
		              <div class="form-group">
		                <label><b>Auction Name : </b><%=auction.getAuction_name()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Auction Id : </b><%=auction.getAuction_id()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Auction No : </b><%=auction.getAuction_no()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Location : </b><%=auction.getLocation()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Start Date / Time : </b>
		            <%
		            	String start_date_time = "";
		            	if(auction.getStart_date_time()!=null){
		            		start_date_time = sdf.format(auction.getStart_date_time());
		            	}
		            %>	<%=start_date_time%></label>
		              </div>
		              <div class="form-group">
		                <label><b>End Date / Time : </b>
		            <%
		            	String end_date_time = "";
		            	if(auction.getEnd_date_time()!=null){
		            		end_date_time = sdf.format(auction.getEnd_date_time());
		            	}
		            %>	<%=end_date_time%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Description : </b><%=auction.getAuction_desc()%></label>
		              </div>	
					</div>
					
					
				
				<div class="col-sm-4">
		              <div class="form-group">
		              	<%
		              	String categoryLevel1 = "";
		              		if(auction.getCategory_level_1()!=null && auction.getCategory_level_1()>0){
		              			categoryLevel1 = catLev1LovHM.get(auction.getCategory_level_1()).getName();
		              		}
		              	%>
		                <label><b>Category Level 1 : </b><%=categoryLevel1%></label>
		              </div>
		            <div class="form-group">
		              <label><b>Auction Type : </b>
							<%if(auction!=null && auction.getAuction_type()==15){%>
								Online	
							<%}else if(auction!=null && auction.getAuction_type()==16){%>
								Negotiated
							<%}else{%>
								&nbsp;
							<%}%>
		              </label>		
		            </div>
				
		            <div class="form-group">
		              <label><b>Status : </b>
							<%if(auction.getStatus()==30){%>
								Open	
							<%}else if(auction.getStatus()==31){%>
								Closed
							<%}else if(auction.getStatus()==32){%>
								For Review
							<%}else{%>
								&nbsp;
							<%}%>
		              </label>
		            </div>
		           <div class="form-group">	
		             <label><b>One Lot Per Bidder : </b>
		            <%
		            	String oneLotPerBidder = "";
		            	if(auction.getOne_lot_per_bidder() == 0){
		            		oneLotPerBidder = "No";
		            	}else if(auction.getOne_lot_per_bidder() == 1){
		            		oneLotPerBidder = "Yes";
		            	}
		            %>	<%=oneLotPerBidder %>
		             
		             </label>

		            </div>
					<div class="form-group">			
		             <label><b>Active : </b>
		            <%
		            	String active = "";
		            	if(auction.getActive() == 0){
		            		active = "No";
		            	}else if(auction.getActive() == 1){
		            		active = "Yes";
		            	}
		            %>	<%=active %>
		             </label>
		            </div>
		            <div class="form-group">
		              <label><b>Visibility : </b>
							<%if(auction.getVisibility()==33){%>
								Public	
							<%}else if(auction.getVisibility()==34){%>
								Private
							<%}else{%>
								&nbsp;
							<%}%>
		              </label>
		            </div>

		              <div class="form-group">
		                <label><b>Auction Item Increment Time : </b><%=auction.getAuction_item_increment_time()%></label>
		              </div>

				</div>
		
		
		
				<div class="col-sm-4">
					<div class="form-group">			
		             <label><b>Bid Deposit : </b>
		            <%
		            	String bid_deposit = "";
		            	if(auction.getBid_deposit() == 0){
		            		bid_deposit = "No";
		            	}else if(auction.getBid_deposit() == 1){
		            		bid_deposit = "Yes";
		            	}
		            %>	
		            <%=bid_deposit%>
		             </label>
						
		            </div>
		              <div class="form-group">
		                <label><b>Bid Deposit Amount : </b><%=auction.getBid_deposit_amount()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>No of lots: </b><%=auction.getNo_of_lots()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>No of items : </b><%=auction.getNo_of_items()%></label>
		              </div>
					<div class="form-group">			
		             <label><b>Date Sync : </b>
		            <%
		            	String date_sync = "";
		            	if(auction.getDate_sync()!=null){
		            		date_sync = sdf.format(auction.getDate_sync());
		            	}
		            %>	<%=date_sync%>
		             </label>
		            </div>
		            
		            
		            
		           <div class="form-group">	
		             <label><b>One As Start Bid : </b>
		            <%
		            	String oneStartBid = "";
		            	if(auction.getOne_start_bid() == 0){
		            		oneStartBid = "No";
		            	}else if(auction.getOne_start_bid() == 1){
		            		oneStartBid = "Yes";
		            	}
		            %>	<%=oneStartBid %>
		             
		             </label>

		            </div>
		            
		           <div class="form-group">	
		             <label><b>Bid Qualifier Price : </b>
		            <%
		            	String bid_qualifier_price = "";
		            	if(auction.getBid_qualifier_price() == 0){
		            		bid_qualifier_price = "";
		            	}else if(auction.getBid_qualifier_price() == 1){
		            		bid_qualifier_price = "Reserve Price";
		            	}else if(auction.getBid_qualifier_price() == 2){
		            		bid_qualifier_price = "SRP";
		            	}else if(auction.getBid_qualifier_price() == 3){
		            		bid_qualifier_price = "Target Price";
		            	}else if(auction.getBid_qualifier_price() == 4){
		            		bid_qualifier_price = "Assess Value Price";
		            	}

		            %>	<%=bid_qualifier_price %>
		             
		             </label>

		            </div>
		            
		            
				</div>


				
				

            <div class="col-md-12">
	            <div class="form-group">
	              <label><b>Coordinator : </b> 
	              <% 
	              String coordinator = "";
	              
	              if(coordinatorUserHM.get(auction.getCoordinator())!=null){ %>
	              <%=coordinatorUserHM.get(auction.getCoordinator()).getFirst_name()%> <%=coordinatorUserHM.get(auction.getCoordinator()).getLast_name()%>
	              <% }else{ %>
	              <%=coordinator%>
	              <% } %>
	              </label>
	            </div>
			
            
              <div class="box box-info">
                <div class="box-header">
                  <h3 class="box-title"><label><b>Terms and Conditions : </b> <span id="terms_and_conditions_div"></span> </label></h3>
                </div><!-- /.box-header -->


              </div>
              
				<iframe id="my_iframe" style="display:none;"></iframe>
				
	
				
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="createAuction()">Create</a>
					</div>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="updateAuction()">Update</a>
					</div>
					<div class="col-sm-3">
					     <a class="btn btn-theme btn-block " href="#" onclick="auctionRange()">Auction Range Increment</a>
					</div>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="auctionImages()">Images</a>
					</div>
					
					<%if(auction.getVisibility()==34){%>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="auctionPrivateInvites()">Invites</a>
					</div>
					<% } %>
				
	                
	            </div>
            </div>
            </div>
        </section>
        
        
        

        <!-- PAGE -->
        <section class="page-section color" style="padding:15px;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h3 class="block-title"><span>  <label>Lots</label></span></h3>
 						

 		                <div id="tableDiv1" class="box-body table-responsive">
										
						  <!-- <div id="msgDiv"></div> -->
						  			  
		                  <table id="table1" class="table table-bordered table-striped no-margin">
		
		                    <thead>
		                      <tr>
		                        <th>Main Image</th>
		                        <th>Lot ID</th>
		                        <th>Auction ID</th>
		                        <th>Lot No</th>
		                        <th>Description</th>
		                        <th>Active</th>
		                        <th>Date Created</th>
		                        <th>Date Updated</th>
		                      </tr>
		                    </thead>
		                    <tbody>
                      <%
                      	for(Lot l : lList){
                            String date_created = "";
                            if(l.getDate_created()!=null){
                            	date_created = sdf.format(l.getDate_created());
                            }

                            String date_updated = "";
                            if(l.getDate_updated()!=null){
                            	date_updated = sdf.format(l.getDate_updated());
                            }
   
                            active = "Yes";
                            if(l.getActive()==0){
                            	active = "No";
                            }

                      %>
		                  <tr>
		                    <td width="75px">
			                    <div class="media">
								  <a class="pull-left" href="#" onclick="lotImages('<%=l.getId()%>')">
								      <img class="media-object lazy" style="width:75px; " src="" data-src="image?id=<%=l.getLot_id()%>&t=lt" alt="Click to upload image" />
								      <span class="badge badge-success pull-right" style="position: relative; top: -20px; left: -2px;">
								      	<%= new ImageManager().getImageListByLotId(l.getLot_id()).size() %> 
								      </span>
								  </a>
								</div>
		                    </td>
                            <td width="15px"><a href="#" onclick="viewLot('<%=l.getId()%>')"><%=l.getLot_id()%></a></td>
                            <td width="15px"><%=l.getAuction_id()%></td>
                            <td width="15px"><%=l.getLot_no()%></td>
                            <td><%=l.getLot_desc()%></td>
                            <td width="15px"><%=active%></td> 
                            <td width="150px"><%=date_created%></td>
                            <td width="150px"><%=date_updated%></td>
                            
		                  </tr>    
		             <%} %>         
		                          
		                          
		                    </tbody>
		
		                    <!-- 
		
		                    <tfoot>
		
		                      <tr>
		
		                        <th>Email Address</th>
		
		                        <th>Last Name</th>
		
		                        <th>First Name</th>
		
		                        <th>Active</th>
		
		                        <th>Date Created</th>
		
		                        <th>Date Updated</th>
		
		                      </tr>
		
		                    </tfoot>  -->
		
		                  </table>
		
		                  </div><!-- /.table-responsive -->

                    </div>
                    
                    <div class="col-sm-2">
                        <a class="btn btn-theme btn-block " href="#" onclick="createLot()">Create</a>
                    </div>
                    <!-- 
                    <div class="col-sm-2">
                        <a class="btn btn-theme btn-block " href="#" onclick="clearLogin()">Clear</a>
                    </div>
        --->
                    
                </div>
            </div>
        </section>
        <!-- /PAGE -->
        
        
        
        <!-- PAGE -->
        <section class="page-section color" style="padding:15px;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h3 class="block-title"><span>  <label>Items </label></span></h3>
 						

 		                <div id="tableDiv2" class="box-body table-responsive">
										
						  <!-- <div id="msgDiv"></div> -->
						  			  
		                  <table id="table2" class="table table-bordered table-striped no-margin">
		
		                    <thead>
		                      <tr>
		                      	<!--  <th>Main Image</th> -->
		                        <th>Item ID</th>
		                        <th>Auction ID</th>
		                        <th>Lot ID</th>
		                        <th>Description</th>
		                        <th>Reference No</th>
		                        <th>Target Price</th>
		                        <th>Reserve Price</th>
		                        <th>Date Created</th>
		                        <th>Date Updated</th>                  
		                      </tr>
		                    </thead>
		                    <tbody>
                      <%
                      	for(Item i : iList){
                            String date_created = "";
                            if(i.getDate_created()!=null){
                            	date_created = sdf.format(i.getDate_created());
                            }

                            String date_updated = "";
                            if(i.getDate_updated()!=null){
                            	date_updated = sdf.format(i.getDate_updated());
                            }
                            
                            //String date_registration = "";
                            //if(a.getDate_registration()!=null){
                            //	date_registration = sdf.format(a.getDate_registration());
                            //}
                            
                         
                            /*
                            String active = "Yes";
                            if(i.getActive()==0){
                            	active = "No";
                            }
                            */
                            /*
                            String status = "";
                            if(a.getStatus()==10){
                            	status = "Banned";
                            }else if(a.getStatus()==11){
                            	status = "Registered";
                            } else if(a.getStatus()==12){
                            	status = "For Email Verification";
                            }
                            */
                      %>
		                  <tr>
		                  	<td width="75px">
			                    <div class="media">
								  <a class="pull-left" href="#" onclick="itemImages('<%=i.getId()%>')">
								      <img class="media-object lazy" style="width:75px; " src="" data-src="image?id=<%=i.getId()%>&t=it" alt="Click to upload image" />
								      <span class="badge badge-success pull-right" style="position: relative; top: -20px; left: -2px;">
								      	<%= new ImageManager().getImageListByItemId(i.getId()).size() %>
								      </span>
								  </a>
								</div>
		                    </td>
                            <td width="15px"><a href="#" onclick="viewItem('<%=i.getId()%>')"><%=i.getItem_id()%></a></td>
                            <td width="15px"><%=i.getAuction_id()%></td>
                            <td width="15px"><%=i.getLot_id()%></td>
                            <td><%=i.getItem_desc()%></td>
                            <td><%=i.getReference_no()%></td>
                            <td><%=i.getTarget_price()%></td>
                            <td><%=i.getReserve_price()%></td>
                            <td width="100px"><%=date_created%></td>
                            <td width="100px"><%=date_updated%></td>
		                  </tr>    
		             <%} %>         
		                          
		                          
		                    </tbody>
		
		                    <!-- 
		
		                    <tfoot>
		
		                      <tr>
		
		                        <th>Email Address</th>
		
		                        <th>Last Name</th>
		
		                        <th>First Name</th>
		
		                        <th>Active</th>
		
		                        <th>Date Created</th>
		
		                        <th>Date Updated</th>
		
		                      </tr>
		
		                    </tfoot>  -->
		
		                  </table>
		
		                  </div><!-- /.table-responsive -->

                    </div>
                    
                    <div class="col-sm-2">
                        <a class="btn btn-theme btn-block " href="#" onclick="createItem()">Create</a>
                    </div>
        
                    
                </div>
            </div>
        </section>
        <!-- /PAGE -->
        
        <%if(auction.getVisibility()==34){%>

        <!-- PAGE -->
        <section class="page-section color" style="padding:15px;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h3 class="block-title"><span>  <label>Users </label></span></h3>

 		                <div id="tableDiv3" class="box-body table-responsive">
										
						  <!-- <div id="msgDiv"></div> -->
						  			  
		                  <table id="table3" class="table table-bordered table-striped no-margin">
		
		                    <thead>
		                      <tr>
		                        <th>Email</th>
		                        <th>Last Name</th>
		                        <th>First Name</th>
		                        <th>Active</th>
		                        <th>Status</th>
		                        <th>Auction ID</th>
		                        <th>Date Created</th>
		                        <th>Date Updated</th>
		                      </tr>
		                    </thead>
		                    <tbody>
                      <%
                      	for(AuctionUser au : auList){
                            String date_created = "";
                            if(au.getDate_created()!=null){
                            	date_created = sdf.format(au.getDate_created());
                            }

                            String date_updated = "";
                            if(au.getDate_updated()!=null){
                            	date_updated = sdf.format(au.getDate_updated());
                            }
                            
                          
                            active = "Yes";
                            if(au.getActive()==0){
                            	active = "No";
                            }
                            
    		            	String auctionUserStatus = "";
    		            	if(auctionUserStatusHM.get( au.getStatus())!=null){
    		            		auctionUserStatus = auctionUserStatusHM.get(au.getStatus()).getValue();
    		            	}
                            
                            
                            String email = "";
                            String ln = "";
                            String fn = "";
                            if(bidderUserHM.get(au.getUser_id())!=null){
                            	email = bidderUserHM.get(au.getUser_id()).getEmail_address();
                            	ln = bidderUserHM.get(au.getUser_id()).getLast_name();
                            	fn = bidderUserHM.get(au.getUser_id()).getFirst_name();
                            }
                            
                            
                      %>
		                  <tr>
                            <td><a href="#" onclick="viewAuctionUser('<%=au.getId()%>')"><%=email%></a></td>
                            <td><%=ln%></td>
                            <td><%=fn%></td>
                            <td><%=active%></td> 
                            <td><%=auctionUserStatus%></td> 
                            <td><%=au.getAuction_id()%></td> 
                            <td><%=date_created%></td>
                            <td><%=date_updated%></td>
		                  </tr>    
		             <%} %>         
		                          
		                          
		                    </tbody>
		
		                    <!-- 
		
		                    <tfoot>
		
		                      <tr>
		
		                        <th>Email Address</th>
		
		                        <th>Last Name</th>
		
		                        <th>First Name</th>
		
		                        <th>Active</th>
		
		                        <th>Date Created</th>
		
		                        <th>Date Updated</th>
		
		                      </tr>
		
		                    </tfoot>  -->
		
		                  </table>
		
		                  </div><!-- /.table-responsive -->

                    </div>
                    
                    <div class="col-sm-2">
                        <a class="btn btn-theme btn-block " href="#" onclick="createAuctionUser()">Create</a>
                    </div>
                    <!-- 
                    <div class="col-sm-2">
                        <a class="btn btn-theme btn-block " href="#" onclick="clearLogin()">Clear</a>
                    </div>
        --->
                    
                </div>
            </div>
        </section>
        <!-- /PAGE -->
        <%}%>
        
        </form>
        <!-- /PAGE -->
        
    </div>
    <!-- /CONTENT AREA -->

    <!-- FOOTER -->
	<jsp:include page="hmr-footer.jsp" />
    <!-- /FOOTER -->

    <div id="to-top" class="to-top" style="background-color: #93bcff"><i class="fa fa-angle-up"></i></div>

</div>
<!-- /WRAPPER -->

<!-- JS Local -->
<script type="text/javascript">
function createAuction(){
	document.frm.action.value="createAuction";
	document.frm.submit();
}

function updateAuction(){
	document.frm.action.value="updateAuction";
	document.frm.submit();
}


function auctionPrivateInvites(){
	document.frm.action.value="viewAuctionPrivateInvites";
	document.frm.submit();
}


function saveAuctionImage(){
	if(document.frm.myFileSmall.value=="" && document.frm.myFile.value==""){
		//document.frm.action.value="saveAuctionImage";
	}else{
		document.frm.action.value="saveAuctionImage";
		document.frm.submit();
	}
	
	//alert(document.frm.myFileSmall.value);
	
}

function auctionRange(){
	document.frm.manager.value="auction-range-manager";
	document.frm.action.value="auctionRangeList";
	document.frm.submit();
}

function auctionImages(){
	document.frm.manager.value="image-manager";
	document.frm.action.value="auctionImageUpload";
	document.frm.submit();
}

function lotImages(lotId){
	document.frm.manager.value="image-manager";
	document.frm.action.value="lotImageUpload";
	document.frm.lotId_wip.value=lotId;
	document.frm.lot_id.value=lotId;
	document.frm.submit();
}

function itemImages(itemId){
	document.frm.manager.value="image-manager";
	document.frm.action.value="itemImageUpload";
	document.frm.itemId_wip.value=itemId;
	document.frm.submit();
}



function onLoadPage(){
	//document.frm.userId.focus();	
/*	
//	if(document.frm.userId.value!=""){
//		< /%if(userId!=null){ %>
//			document.frm.pw.focus();
//		< /%}%>
//	}
*/


//alert("</%=auction.getTerms_and_conditions()%>");
//document.getElementById("terms_and_conditions").innerHTML="</%=auction.getTerms_and_conditions()%>";



//$('#terms_and_conditions').val('sadfsd');

	//document.frm..value=;
	
}


<%if(msgInfo!=null){%>
	
	var msgInfo = "<%=msgInfo%>";
	var msgbgcol = "<%=msgbgcol%>";
	var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
	msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
	msgBoxValue = msgBoxValue + '</div>';
	document.getElementById("msgDiv").innerHTML=msgBoxValue;

<%}%>


setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);

setTimeout(onLoadPage,3000);



</script>



<!-- /JS Local -->


<!-- JS Global -->
<script src="assets/plugins/jquery/jquery-1.11.1.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="assets/plugins/superfish/js/superfish.min.js"></script>
<script src="assets/plugins/prettyphoto/js/jquery.prettyPhoto.js"></script>
<script src="assets/plugins/owl-carousel2/owl.carousel.min.js"></script>
<script src="assets/plugins/jquery.sticky.min.js"></script>
<script src="assets/plugins/jquery.easing.min.js"></script>
<script src="assets/plugins/jquery.smoothscroll.min.js"></script>
<script src="assets/plugins/smooth-scrollbar.min.js"></script>
<script src="assets/plugins/jquery.lazy/jquery.lazy.min.js"></script>

<!-- JS Page Level -->
<script src="assets/js/theme.js"></script>

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/plugins/jquery.cookie.js"></script>
<!--  <script src="assets/js/theme-config.js"></script>  -->
<!--<![endif]-->

<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.js"></script>

<script src="js/jquery.datetimepicker.full.js"></script>
<script type="text/javascript" src="js/select2.full.js"></script>

<%--
    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    --%>

	<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<!-- <script src="https://cdn.ckeditor.com/4.4.3/standard/ckeditor.js"></script> -->

<script>



function createLot(){
	document.getElementById("action").value="createLot";
	document.getElementById("manager").value="lot-manager";
	document.frm.submit();
}

function viewLot(id){
	document.getElementById("action").value="viewLot";
	document.getElementById("manager").value="lot-manager";
	document.getElementById("lotId_wip").value=id;
	document.frm.submit();
}


function createItem(){
	document.getElementById("action").value="createItem";
	document.getElementById("manager").value="item-manager";
	document.frm.submit();
}


function createAuctionUser(){
	document.getElementById("action").value="createAuctionUser";
	document.getElementById("manager").value="auction-user-manager";
	document.frm.submit();
}

function viewItem(id){
	document.getElementById("action").value="viewItem";
	document.getElementById("manager").value="item-manager";
	document.getElementById("itemId_wip").value=id;
	document.frm.submit();
}

function viewAuctionUser(id){
	document.getElementById("action").value="viewAuctionUser";
	document.getElementById("manager").value="auction-user-manager";
	document.getElementById("auctionUserId_wip").value=id;
	document.frm.submit();
}
$( document ).ready(function() {
	$('.lazy').lazy({
		enableThrottle: true,
	    throttle: 250,
		onError: function(element) {
	        console.log('image "' + element[0]['currentSrc'] + '" could not be loaded');
	    },
	    afterLoad: function(element) {
	        var imageSrc = element.data('currentSrc');
	        console.log('image "' + element[0]['currentSrc'] + '" was loaded successfully');
	    }
	});
	document.getElementById("terms_and_conditions_div").innerHTML = "<%=auction.getTerms_and_conditions()!=null ? "Yes" : "No"%>";

});


</script>
<script>
/*
function Download(url) {
	//alert(url);
    document.getElementById('my_iframe').src = url;
};
*/


$(function () {
    $("#table1").DataTable({
    	"pageLength": 100,
      	"order": [[ 2, "asc" ]],
      	"lengthMenu": [[100, 50, 25, 5], [100, 50, 25, 5]]
    });
  });
  
$(function () {
    $("#table2").DataTable({
    	"pageLength": 100,
      	"order": [[ 4, "asc" ]],
      	"lengthMenu": [[100, 50, 25, 5], [100, 50, 25, 5]]
    });
  });
  
$(function () {
    $("#table3").DataTable({
    	"pageLength": 100,
      	"order": [[ 4, "asc" ]],
      	"lengthMenu": [[100, 50, 25, 5], [100, 50, 25, 5]]
    });
  });
  
  
</script>

</body>
</html>
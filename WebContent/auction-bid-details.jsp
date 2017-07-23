<!DOCTYPE html>
<%@page import="java.sql.Timestamp"%>
<%@ page import="hmr.com.bean.Auction"
		 import="hmr.com.bean.Lot"
		 import="hmr.com.bean.Image"
		 import="hmr.com.bean.Lov"
		 import="hmr.com.bean.Item"
		 import="java.util.List"  
		 import="java.math.BigDecimal"  
		 import="java.util.HashMap"  
		 import="java.text.DecimalFormat"
		 import="java.text.SimpleDateFormat"

%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%
	System.out.println("PAGE auction-bid-details.jsp");

	String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
	String COMPANY_NAME_ACRONYM = (String)request.getSession().getAttribute("COMPANY_NAME_ACRONYM");

	String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
	String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
	
	//TODO: messages displayed on page should be session based only to avoid XSS
	if(msgInfo==null) {
		msgInfo = (String)request.getSession().getAttribute("msgInfo")!=null ? (String)request.getSession().getAttribute("msgInfo") :null;
		msgbgcol = (String)request.getSession().getAttribute("msgbgcol")!=null ? (String)request.getSession().getAttribute("msgbgcol") :"";
	}
	request.getSession().removeAttribute("msgInfo");
	request.getSession().removeAttribute("msgbgcol");
	
	
	String firstName = (String)request.getSession().getAttribute("firstName");
	String lastName = (String)request.getSession().getAttribute("lastName");
	String fullName = (String)request.getSession().getAttribute("fullName");
	String userId = (String)request.getSession().getAttribute("userId");
	String aid = (String)request.getSession().getAttribute("auctionId");
	String currency = "PHP";
	
	// IDs
	Integer user_id = request.getSession().getAttribute("user-id")!=null ? (Integer)request.getSession().getAttribute("user-id") : null;
	Integer user_role_id = request.getSession().getAttribute("user-role-id")!=null ? (Integer)request.getSession().getAttribute("user-role-id") : 0;
	
	System.out.println("PAGE user_id :"+user_id);
	System.out.println("PAGE user_role_id : "+user_role_id);
	
	List<Auction> activeOnlineAuctionList = request.getAttribute("ACTIVE-ONLINE-AUCTION-LIST")!=null ? (List<Auction>)request.getAttribute("ACTIVE-ONLINE-AUCTION-LIST") : (List<Auction>)request.getSession().getAttribute("ACTIVE-ONLINE-AUCTION-LIST");
	List<Auction> activeNegotiatedAuctionList = request.getAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST")!=null ? (List<Auction>)request.getAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST") : (List<Auction>)request.getSession().getAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST");
	List<Auction> activeLiveAuctionList = request.getAttribute("ACTIVE-ONLINE-AUCTION-LIST")!=null ? (List<Auction>)request.getAttribute("ACTIVE-ONLINE-AUCTION-LIST") : (List<Auction>)request.getSession().getAttribute("ACTIVE-ONLINE-AUCTION-LIST");

	Auction auction = request.getAttribute("auction")!=null ? (Auction)request.getAttribute("auction") : (Auction)request.getSession().getAttribute("auction");
	List<Lot> lList = request.getAttribute("lList")!=null ? (List<Lot>)request.getAttribute("lList") : (List<Lot>)request.getSession().getAttribute("lList");
	HashMap<Integer, Lov> currencyLovHM  = request.getAttribute("CURRENCY-HM")!=null ? (HashMap<Integer, Lov>)request.getAttribute("CURRENCY-HM") : (HashMap<Integer, Lov>)request.getSession().getAttribute("CURRENCY-HM");
	
	//HashMap<BigDecimal, Lot> lotHM  = request.getAttribute("lotHM")!=null ? (HashMap<BigDecimal, Lot>)request.getAttribute("lotHM") : (HashMap<BigDecimal, Lot>)request.getSession().getAttribute("lotHM");
	//List<Item> iList = request.getAttribute("iList")!=null ? (List<Item>)request.getAttribute("iList") : (List<Item>)request.getSession().getAttribute("iList");

    DecimalFormat df = new DecimalFormat("#,###,##0");
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy  HH:mm");
	
	List<Image> auction_images = (List<Image>)request.getAttribute("auction_images");
	
	
	
	%>
    <title><%=COMPANY_NAME%></title>
    
    <!-- Page: auction-bid-details.jsp -->

    <!-- Favicon -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="shortcut icon" href="ico/hmr-favicon.ico">

    <!-- DataTables -->
    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">


    <!-- CSS Global -->
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="assets/plugins/fontawesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/plugins/prettyphoto/css/prettyPhoto.css" rel="stylesheet">
    <link href="assets/plugins/owl-carousel2/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="assets/plugins/owl-carousel2/assets/owl.theme.default.min.css" rel="stylesheet">
    <link href="assets/plugins/animate/animate.min.css" rel="stylesheet">
    <link href="assets/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="assets/css/theme.css" rel="stylesheet">
<link href="assets/css/theme-hmr.css" rel="stylesheet" id="theme-config-link">
    <link href="assets/plugins/jquery-ui/themes/smoothness/jquery-ui.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/plugins/gridder/jquery.gridder.min.css" />

	<!-- Page Level CSS -->
	<link rel="stylesheet" href="assets/css/gridder.css" />

    <!-- Head Libs -->
    <script src="assets/plugins/modernizr.custom.js"></script>

    <!--[if lt IE 9]>
    <script src="assets/plugins/iesupport/html5shiv.js"></script>
    <script src="assets/plugins/iesupport/respond.min.js"></script>
    <![endif]-->

   <script>
		    
    function setCountDownTimer(elementId, end_date_time){
		 // Set the date we're counting down to
		 //var countDownDate = new Date("Jan 5, 2018 15:37:25").getTime();
		
		 var countDownDate = new Date(end_date_time).getTime();
			
		 
		 // Update the count down every 1 second
		 var x = setInterval(function() {
		
		     // Get todays date and time
		     var now = new Date().getTime();
		     
		     // Find the distance between now an the count down date
		     var distance = countDownDate - now;
		     
		     // Time calculations for days, hours, minutes and seconds
		     var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		     var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		     var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		     var seconds = Math.floor((distance % (1000 * 60)) / 1000);
		     
		     // Output the result in an element with id="demo"
		     document.getElementById(elementId).innerHTML = days + "d " + hours + "h "
		     + minutes + "m " + seconds + "s ";
		     
		     // If the count down is over, write some text 
		     if (distance < 0) {
		         clearInterval(x);
		         document.getElementById(elementId).innerHTML = "EXPIRED";
		     }
		 }, 1000);
		 
    }
 </script>
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
        <section class="page-section">
            <div class="container">
            	<div class="row ">
            		<div class="col-md-12">
		                <div class="message-box">
		                    <div class="message-box-inner">
		                        <h2><%=auction.getAuction_name()%></h2>
		                    </div>
		                </div>
		                
		                <div id="msgDiv"></div>
		             </div>
               </div>
            </div>
        </section>
        <!-- /PAGE -->

        <!-- PAGE -->
        <section class="page-section no-padding-bottom">
            <div class="container">

                <div class="row ">
                <%--
                    <div class="col-md-3 sidebar">
                        <!-- widget shop categories -->
						<jsp:include page="hmr-category.jsp" />
                        <!-- /widget shop categories -->
                    </div> --%>


	                    <div class="col-md-12">
	                        <div class="tabs-wrapper content-tabs">
	                            <ul class="nav nav-tabs">
	                                <li><a href="#auction-description" data-toggle="tab">Details</a></li>
	                                <li class="active"><a href="#auction-lots" data-toggle="tab">Lots</a></li>
	                                <!-- <li><a href="#items-list" data-toggle="tab">Items</a></li> --->
	                                <li><a href="#terms-and-conditions" data-toggle="tab">Terms and Conditions</a></li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane fade" id="auction-description">


							        <section class="page-section">
							            <div class="container">
							                <div class="row">
							                    <div class="col-md-10">

							                            <div class="media" style="height: 210px;">
							                               <a class="pull-left media-link" href="#" >
							                                    <img  class="media-object" style="height: 200px; size: 200px;" src="image?id=<%=auction.getAuction_id()%>&t=at" alt="">
							                                    
							                                    <!-- <i class="fa fa-eye"></i> -->
							                                </a>
							                                <div class="media-body">
																<h4 class="media-heading"><a href="#" style="font-size: 14px; font-weight: bold; color: red"><%=auction.getAuction_desc()%></a></h4>
							                                	<div><label>LOCATION : </label> &nbsp;&nbsp; <label><%=auction.getLocation()%></label></div>
							                                    
							                                    <div><label>START : </label> &nbsp;&nbsp; <label><%=sdf.format(auction.getStart_date_time()) %></label></div>
                                    							<div><label>CLOSING : </label>&nbsp;&nbsp; <label><%=sdf.format(auction.getEnd_date_time()) %></label></div>

							                                </div>
							                            </div>

							                    </div>

							                </div>
							                
							                <div class="row">
													<div class="col-md-11">
														<div class="panel panel-default">
															<div class="panel-heading">
																<h3 class="panel-title">Gallery</h3>
															</div>
															<div class="panel-body">
																<!-- Gridder navigation -->
																<ul class="gridder">

																	<%
																		for (Image i : auction_images) {
																	%>
																	<li class="gridder-list"
																		data-griddercontent="#content<%=i.getId()%>">
																		<img style="width:150px" class="media-object" src="image?id=<%=i.getId()%>&t=t" />
																	</li>
																	<%
																		}
																	%>

																</ul>

																<!-- Gridder content -->
																<%
																	for (Image i : auction_images) {
																%>
																<div id="content<%=i.getId()%>" class="gridder-content">
																	<img class="media-object" src="image?id=<%=i.getId()%>" />
																	<div class="media-body">
																		<h4 class="media-heading">
																			<a href="#"
																				style="font-size: 14px; font-weight: bold; color: red">Image
																				#<%=i.getId()%></a>
																		</h4>
																	</div>
																</div>
																<% } %>
															</div>
															<div class="panel-footer"><em>Note: Click on image for bigger view.</em></div>
														</div>



														
													</div>
												</div>
							                    
							            </div>
							        </section>








	                                </div>
	                                <div class="tab-pane fade in active" id="auction-lots">

							        <!-- PAGE -->
							        <section class="page-section">
							            <div class="container">
							                <div class="row">
							                	<%for(Lot l : lList) {%>
							                	
							                	

							                	
							                    <div class="col-md-7">

							                            <div class="media" style="height: 210px;">
							                               <a class="pull-left media-link" href="#" onclick="viewLot('<%=l.getId()%>')">
							                                    <img  class="media-object" style="height: 200px; size: 200px;" src="image?id=<%=l.getId()%>&t=lt" alt="">
							                                    
							                                    <!-- <i class="fa fa-eye"></i> -->
							                                </a>
							                                <div class="media-body">
							                                
							                                    <h4 class="media-heading"><a href="#" style="font-size: 14px; font-weight: bold; color: red" onclick="viewLot('<%=l.getId()%>')">#<%=l.getLot_no()%>: <%=l.getLot_name()%></a></h4>
							                                  
							                                    
							                                    <div><label><%=l.getLot_desc()%></label></div>
							                                    
							                                    <div><i class="fa fa-clock-o"></i> <label id="cdTimer-<%=l.getId()%>"></label></div>
                                    							
                                    							<%
                                    								//if(currencyLovHM.get(l.getCurrency())!=null){
                                    								//	currency = currencyLovHM.get(l.getCurrency()).getValue();
                                    								//}
                                    							%>
                                    							
                                    							<div><label>Highest Bid : <%=df.format(l.getAmount_bid())%> <%=currency%></label></div>
							                                    <div><label>Asking Bid : <%=df.format(l.getAmount_bid_next())%> <%=currency%></label></div>
							                                    <div><label>Bids : <%=l.getBid_count()%></label></div>
							                                    <%-- <div><label>Bids : < /%=l.getBid_count()%></label></div> --%>
							                                   
							                                   
							                                   
							                                   
							                                </div>
							                            </div>
							                            <script>setCountDownTimer('cdTimer-<%=l.getId()%>', '<%=l.getEnd_date_time()%>')</script>
							                            
							
							                        
							                    </div>
												
												
							                    <div class="col-md-3">
							                            
							
							                            <div class="media" style="height: 210px;">
							                         
							                                <div class="media">
                                								<% if(user_id != null && user_role_id > 0){ %>
                                									<% if(l.getIs_bid() == 1){ %>
                                   	 							
		                                   	 							<% if( l.getIs_available_lot() > 0) { %>
		                                   	 								<% Integer i = l.getUnit_qty(); %>
		                                   	 								<div class="form-group">
		                                   	 								<label>Quantity:</label>
		                                   	 								<select class="form-control" id="qty_<%=l.getId()%>" name="qty_<%=l.getId()%>">
		                                   	 								<% while(i > 0) { %>
		                                   	 									<option value="<%=i%>"><%=i%> unit<% if(i>1){ %>s<%}%></option>
		                                   	 									<% i = i - 1; %>
		                                   	 								<% } %>
		                                   	 								</select>
		                                   	 								</div>
		                                   	 							<% } %>
		                                   	 							
		                                   	 							<% if(auction.getAuction_type() == 15){ %>
			                                   	 							<% if(auction.getStart_date_time().after(new Timestamp(System.currentTimeMillis())) && l.getActive()>0){ %>
			                                   	 								<a class="btn btn-theme btn-block" href="#" onclick="showPreBidForm('PRE-BID', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">PRE-BID</a>
			                                   	 							<% } else { %>
				                                   	 							<a class="btn btn-theme btn-block" href="#" onclick="submitPage('BID', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','')">BID <%=df.format(l.getAmount_bid_next())%> <%=currency%> </a>
				                                   	 							<a class="btn btn-theme btn-block" href="#" onclick="showMaxBidForm('SET-MAXIMUM-BID', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">SET MAXIMUM BID</a>
				                                   	 							
			                                   	 							<% } %>
		                                   	 							<% } else if(auction.getAuction_type() == 16){ %>
		                                   	 								<a class="btn btn-theme btn-block" href="#" onclick="showNegotiatedBidForm('NEGOTIATED', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">MAKE OFFER</a>
		                                   	 							<% } %>
                                   	 								<% }else {  } %>
                                   	 								<% if(l.getIs_buy() == 1){ %>
                                										<a class="btn btn-theme btn-block" href="#" onclick="submitPage('BUY', '<%=l.getBuy_price()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','')">BUY <%=df.format(l.getBuy_price())%> <%=currency%></a>
                                									<% }else{ } %>
                                   	 							<% }else if(user_id == null && user_role_id == 0 && (l.getIs_bid() == 1 || l.getIs_buy() == 1) ){ %>
																	<a class="btn btn-theme btn-block" href="bid?mngr=get&a=registration">REGISTER</a>
																	<a class="btn btn-theme btn-block" href="bid?mngr=get&a=login">LOGIN</a>
																<% } %>
							                                </div>
							                            </div>
				
							                    </div>
												
							                    
							                    <%} %>
							                   
							                </div>
							                    
							            </div>
							        </section>
							        <!-- /PAGE -->



	                                </div>
	                                
	                                
	                                <%-- 
									<div class="tab-pane fade in" id="items-list">

							        <!-- PAGE -->
							        <section class="page-section">
							            <div class="container">
							                <div class="row">
							                	<%for(Item i : iList) {%>
							                	
							                	
							                	
							                    <div class="col-md-7">
							                            
							
							                            <div class="media" style="height: 210px;">
							                               <a class="pull-left media-link" href="#" >
							                                    <img  class="media-object" style="height: 200px; size: 200px;" src="image?id=<%=i.getId()%>&t=i" alt="">
							                                    
							                                    <!-- <i class="fa fa-eye"></i> -->
							                                </a>
							                                <div class="media-body">
							                                
							       

							                                
							                                
							                                
							                                    <h4 class="media-heading"><a href="#" style="font-size: 14px; font-weight: bold; color: red"><%=i.getItem_desc()%></a></h4>
							                                  
							                                    
							                                    <div><label><%=i.getItem_desc()%></label></div>
							                                    
							                                    <div><i class="fa fa-clock-o"></i> <label id="cdTimer-<%=i.getId()%>"></label></div>
                                    							
                                    							<%
                                    							String currency = "PHP";
                                    								if(currencyLovHM.get(i.getCurrency())!=null){
                                    									currency = currencyLovHM.get(i.getCurrency()).getValue();
                                    								}
                                    							%>
                                    							
                                    							<div><label>Highest Bid : <%=i.getAmount_bid()%> <%=currency%></label></div>
							                                    <div><label>Asking Bid : <%=i.getAmount_bid_next()%> <%=currency%></label></div>
							                                    <div><label>Bids : <%=i.getBid_count()%></label></div>
							                                   
							                                   
							                                   
							                                   
							                                </div>
							                            </div>
							                            <script>setCountDownTimer('cdTimer-<%=i.getId()%>', '<%=auction.getEnd_date_time()%>')</script>
							                            
							
							                        
							                    </div>
												
												
							                    <div class="col-md-3">
							                            
							
							                            <div class="media" style="height: 210px;">
							                         
							                                <div class="media">
                                								<% if(user_id != null && user_role_id > 0){ %>
                                									<% if(i.getIs_bid() == 1){ %>
                                   	 							<a class="btn btn-theme btn-block" href="#" onclick="submitPage('BID', '<%=i.getAmount_bid_next()%>')">BID <%=i.getAmount_bid_next()%> <%=currency%> </a>
                                   	 							<a class="btn btn-theme btn-block" href="#" onclick="submitPage('SET-MAXIMUM-BID', '<%=i.getAmount_bid_next()%>')">SET MAXIMUM BID</a>
                                   	 								<% }else {  } %>
                                   	 								<% if(i.getIs_buy() == 1){ %>
                                								<a class="btn btn-theme btn-block" href="#" onclick="submitPage('BUY', '<%=i.getBuy_price()%>')">BUY <%=i.getBuy_price()%> <%=currency%></a>
                                									<% }else{ } %>
                                   	 							<% }else if(user_id == null && user_role_id == 0){ %>
																	<a class="btn btn-theme btn-block" href="bid?mngr=get&a=registration">REGISTER</a>
																	<a class="btn btn-theme btn-block" href="bid?mngr=get&a=login">LOGIN</a>
																<% } %>
							                                </div>
							                            </div>
				
							                    </div>
												
							                    
							                    <%} %>
							                   
							                </div>
							                    
							            </div>
							        </section>
							        <!-- /PAGE -->



	                                </div>
	                                
	                                 --%>
	                                 
	                                <div class="tab-pane fade" id="terms-and-conditions">
	                                    <p><%=auction.getTerms_and_conditions()%></p>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                
 
 



                </div>
                </div>
				</section>

        <!-- /PAGE -->



<%-- 

        <!-- PAGE -->
        <section class="page-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="thumbnail no-border no-padding thumbnail-banner size-1x3">
                            <div class="media">
                                <a class="media-link" href="#">
                                    <div class="img-bg" style="background-image: url('assets/img/preview/shop/banner-1.jpg')"></div>
                                    <div class="caption">
                                        <div class="caption-wrapper div-table">
                                        <div class="caption-inner div-cell">
                                            <h2 class="caption-title"><span>Lorem Ipsum</span></h2>
                                            <h3 class="caption-sub-title"><span>Dolor Sir Amet Percpectum</span></h3>
                                            <span class="btn btn-theme btn-theme-sm">Shop Now</span>
                                        </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="thumbnail no-border no-padding thumbnail-banner size-1x3">
                            <div class="media">
                                <a class="media-link" href="#">
                                    <div class="img-bg" style="background-image: url('image?id=53')"></div>
                                    <div class="caption text-right">
                                        <div class="caption-wrapper div-table">
                                            <div class="caption-inner div-cell">
                                                <h2 class="caption-title"><span>Lorem Ipsum</span></h2>
                                                <h3 class="caption-sub-title"><span>Dolor Sir Amet Percpectum</span></h3>
                                                <span class="btn btn-theme btn-theme-sm">Shop Now</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /PAGE -->
--%>



<%-- 
        <!-- PAGE -->
        <section class="page-section">
            <div class="container">


                <div class="tabs">
                    <ul id="tabs" class="nav nav-justified-off"><!--
                        --><li class=""><a href="#tab-1" data-toggle="tab">Featured</a></li><!--
                        --><li class="active"><a href="#tab-2" data-toggle="tab">Newest</a></li><!--
                        --><li class=""><a href="#tab-3" data-toggle="tab">Top Sellers</a></li>
                    </ul>
                </div>

                <div class="tab-content">
		
					
                    <!-- tab 1 -->
                    <div class="tab-pane fade" id="tab-1">
                        <div class="row">
                        
                        
                            <div class="col-md-3 col-sm-6">


								
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="http://localhost:8080/HMR/image?id=53">
                                            <img src="http://localhost:8080/HMR/image?id=2" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                            <img src="image?id=53" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                            <img src="image?id=53" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                            <img src="image?id=53" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- tab 2 -->
                    <div class="tab-pane fade active in" id="tab-2">
                        <div class="row">
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                            <img src="image?id=2" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                            <img src="assets/img/preview/shop/product-2.jpg" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                            <img src="image?id=2" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                            <img src="image?id=2" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- tab 3 -->
                    <div class="tab-pane fade" id="tab-3">
                        <div class="row">
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                            <img src="image?id=2" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                            <img src="image?id=1" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="image?id=2">
                                            <img src="image?id=2" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="thumbnail no-border no-padding">
                                    <div class="media">
                                        <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                            <img src="image?id=2" alt=""/>
                                            <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                        </a>
                                    </div>
                                    <div class="caption text-center">
                                        <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                        <div class="rating">
                                            <span class="star"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span><!--
                                            --><span class="star active"></span>
                                        </div>
                                        <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                        <div class="buttons">
                                            <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-icon-left" href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a><!--
                                            --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </section>
        <!-- /PAGE -->
--%>





<%--
        <!-- PAGE -->
        <section class="page-section">
            <div class="container">
                <h2 class="section-title"><span>Top Rated Products</span></h2>
                <div class="top-products-carousel">
                    <div class="owl-carousel" id="top-products-carousel">
                        <div class="thumbnail no-border no-padding">
                            <div class="media">
                                <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                    <img src="assets/img/preview/shop/top-rated-1.jpg" alt=""/>
                                    <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                </a>
                            </div>
                            <div class="caption text-center">
                                <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                <div class="rating">
                                    <span class="star"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span>
                                </div>
                                <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                <div class="buttons">
                                    <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                --><a class="btn btn-theme btn-theme-transparent" href="#">Add to Cart</a><!--
                                --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="thumbnail no-border no-padding">
                            <div class="media">
                                <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                    <img src="assets/img/preview/shop/top-rated-2.jpg" alt=""/>
                                    <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                </a>
                            </div>
                            <div class="caption text-center">
                                <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                <div class="rating">
                                    <span class="star"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span>
                                </div>
                                <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                <div class="buttons">
                                    <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                --><a class="btn btn-theme btn-theme-transparent" href="#">Add to Cart</a><!--
                                --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="thumbnail no-border no-padding">
                            <div class="media">
                                <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                    <img src="assets/img/preview/shop/top-rated-3.jpg" alt=""/>
                                    <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                </a>
                            </div>
                            <div class="caption text-center">
                                <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                <div class="rating">
                                    <span class="star"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span>
                                </div>
                                <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                <div class="buttons">
                                    <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                --><a class="btn btn-theme btn-theme-transparent" href="#">Add to Cart</a><!--
                                --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="thumbnail no-border no-padding">
                            <div class="media">
                                <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                    <img src="assets/img/preview/shop/top-rated-4.jpg" alt=""/>
                                    <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                </a>
                            </div>
                            <div class="caption text-center">
                                <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                <div class="rating">
                                    <span class="star"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span>
                                </div>
                                <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                <div class="buttons">
                                    <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                --><a class="btn btn-theme btn-theme-transparent" href="#">Add to Cart</a><!--
                                --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="thumbnail no-border no-padding">
                            <div class="media">
                                <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                    <img src="assets/img/preview/shop/top-rated-5.jpg" alt=""/>
                                    <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                </a>
                            </div>
                            <div class="caption text-center">
                                <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                <div class="rating">
                                    <span class="star"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span>
                                </div>
                                <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                <div class="buttons">
                                    <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                --><a class="btn btn-theme btn-theme-transparent" href="#">Add to Cart</a><!--
                                --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="thumbnail no-border no-padding">
                            <div class="media">
                                <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                    <img src="assets/img/preview/shop/top-rated-6.jpg" alt=""/>
                                    <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                </a>
                            </div>
                            <div class="caption text-center">
                                <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                <div class="rating">
                                    <span class="star"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span>
                                </div>
                                <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                <div class="buttons">
                                    <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                --><a class="btn btn-theme btn-theme-transparent" href="#">Add to Cart</a><!--
                                --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="thumbnail no-border no-padding">
                            <div class="media">
                                <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                    <img src="assets/img/preview/shop/top-rated-1.jpg" alt=""/>
                                    <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                </a>
                            </div>
                            <div class="caption text-center">
                                <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                <div class="rating">
                                    <span class="star"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span>
                                </div>
                                <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                <div class="buttons">
                                    <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                --><a class="btn btn-theme btn-theme-transparent" href="#">Add to Cart</a><!--
                                --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="thumbnail no-border no-padding">
                            <div class="media">
                                <a class="media-link" data-gal="prettyPhoto" href="assets/img/preview/shop/product-1-big.jpg">
                                    <img src="assets/img/preview/shop/top-rated-2.jpg" alt=""/>
                                    <span class="icon-view"><strong><i class="fa fa-eye"></i></strong></span>
                                </a>
                            </div>
                            <div class="caption text-center">
                                <h4 class="caption-title"><a href="product-details.html">Standard Product Header</a></h4>
                                <div class="rating">
                                    <span class="star"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span><!--
                                --><span class="star active"></span>
                                </div>
                                <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                <div class="buttons">
                                    <a class="btn btn-theme btn-theme-transparent btn-wish-list" href="#"><i class="fa fa-heart"></i></a><!--
                                --><a class="btn btn-theme btn-theme-transparent" href="#">Add to Cart</a><!--
                                --><a class="btn btn-theme btn-theme-transparent btn-compare" href="#"><i class="fa fa-exchange"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /PAGE -->

        <!-- PAGE -->
        <section class="page-section">
            <div class="container">
                <a class="btn btn-theme btn-title-more btn-icon-left" href="#"><i class="fa fa-file-text-o"></i>See All Posts</a>
                <h2 class="block-title"><span>Our Recent posts</span></h2>
                <div class="row">
                    <div class="col-md-6">
                        <div class="recent-post">
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/blog/recent-post-1.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <p class="media-category"><a href="#">Shoes</a> / <a href="#">Dress</a></p>
                                    <h4 class="media-heading"><a href="#">Standard Post Comment Header Here</a></h4>
                                    Fusce gravida interdum eros a mollis. Sed non lorem varius, volutpat nisl in, laoreet ante.
                                    <div class="media-meta">
                                        6th June 2014
                                        <span class="divider">/</span><a href="#"><i class="fa fa-comment"></i>27</a>
                                        <span class="divider">/</span><a href="#"><i class="fa fa-heart"></i>18</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="recent-post">
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/blog/recent-post-2.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <p class="media-category"><a href="#">Wedding</a> / <a href="#">Meeting</a></p>
                                    <h4 class="media-heading"><a href="#">Standard Post Comment Header Here</a></h4>
                                    Fusce gravida interdum eros a mollis. Sed non lorem varius, volutpat nisl in, laoreet ante.
                                    <div class="media-meta">
                                        6th June 2014
                                        <span class="divider">/</span><a href="#"><i class="fa fa-comment"></i>27</a>
                                        <span class="divider">/</span><a href="#"><i class="fa fa-heart"></i>18</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="recent-post">
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/blog/recent-post-3.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <p class="media-category"><a href="#">Children</a> / <a href="#">Kids</a></p>
                                    <h4 class="media-heading"><a href="#">Standard Post Comment Header Here</a></h4>
                                    Fusce gravida interdum eros a mollis. Sed non lorem varius, volutpat nisl in, laoreet ante.
                                    <div class="media-meta">
                                        6th June 2014
                                        <span class="divider">/</span><a href="#"><i class="fa fa-comment"></i>27</a>
                                        <span class="divider">/</span><a href="#"><i class="fa fa-heart"></i>18</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="recent-post">
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/blog/recent-post-4.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <p class="media-category"><a href="#">Man</a> / <a href="#">Accessories</a></p>
                                    <h4 class="media-heading"><a href="#">Standard Post Comment Header Here</a></h4>
                                    Fusce gravida interdum eros a mollis. Sed non lorem varius, volutpat nisl in, laoreet ante.
                                    <div class="media-meta">
                                        6th June 2014
                                        <span class="divider">/</span><a href="#"><i class="fa fa-comment"></i>27</a>
                                        <span class="divider">/</span><a href="#"><i class="fa fa-heart"></i>18</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /PAGE -->
--%>
        
        <section class="page-section">
            <div class="container">
                <h2 class="section-title"><span>Brand &amp; Clients</span></h2>
                <div class="partners-carousel">
                    <div class="owl-carousel" id="partners">
                        <div><a href="#"><img src="hmr/images/affiliate/alogo1.png" alt=""/></a></div>
                        <div><a href="#"><img src="hmr/images/affiliate/alogo2.png" alt=""/></a></div>
                        <div><a href="#"><img src="hmr/images/affiliate/alogo3.png" alt=""/></a></div>
                        <div><a href="#"><img src="hmr/images/affiliate/alogo4.png" alt=""/></a></div>
                        <div><a href="#"><img src="hmr/images/affiliate/alogo5.png" alt=""/></a></div>
                        <div><a href="#"><img src="hmr/images/affiliate/alogo6.png" alt=""/></a></div>
                        <div><a href="#"><img src="hmr/images/affiliate/alogo7.png" alt=""/></a></div>
                        <div><a href="#"><img src="hmr/images/affiliate/alogo8.png" alt=""/></a></div>
                        
                    </div>
                </div>
            </div>
        </section>
        <!-- /PAGE -->
<%--
        <!-- PAGE -->
        <section class="page-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="product-list">
                            <a class="btn btn-theme btn-title-more" href="#">See All</a>
                            <h4 class="block-title"><span>Top Sellers</span></h4>
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/shop/top-sellers-1.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">Standard Product Header</a></h4>
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div>
                                    <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                </div>
                            </div>
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/shop/top-sellers-2.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">Standard Product Header</a></h4>
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div>
                                    <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                </div>
                            </div>
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/shop/top-sellers-3.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">Standard Product Header</a></h4>
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div>
                                    <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="product-list">
                            <a class="btn btn-theme btn-title-more" href="#">See All</a>
                            <h4 class="block-title"><span>Top Accessories</span></h4>
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/shop/top-sellers-4.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">Standard Product Header</a></h4>
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div>
                                    <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                </div>
                            </div>
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/shop/top-sellers-5.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">Standard Product Header</a></h4>
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div>
                                    <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                </div>
                            </div>
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/shop/top-sellers-6.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">Standard Product Header</a></h4>
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div>
                                    <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="product-list">
                            <a class="btn btn-theme btn-title-more" href="#">See All</a>
                            <h4 class="block-title"><span>Top Newest</span></h4>
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/shop/top-sellers-7.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">Standard Product Header</a></h4>
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div>
                                    <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                </div>
                            </div>
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/shop/top-sellers-8.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">Standard Product Header</a></h4>
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div>
                                    <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                </div>
                            </div>
                            <div class="media">
                                <a class="pull-left media-link" href="#">
                                    <img class="media-object" src="assets/img/preview/shop/top-sellers-9.jpg" alt="">
                                    <i class="fa fa-plus"></i>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#">Standard Product Header</a></h4>
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div>
                                    <div class="price"><ins>$400.00</ins> <del>$425.00</del></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /PAGE -->

        <!-- PAGE -->
        <section class="page-section no-padding-top">
            <div class="container">
                <div class="row blocks shop-info-banners">
                    <div class="col-md-4">
                        <div class="block">
                            <div class="media">
                                <div class="pull-right"><i class="fa fa-gift"></i></div>
                                <div class="media-body">
                                    <h4 class="media-heading">Buy 1 Get 1</h4>
                                    Proin dictum elementum velit. Fusce euismod consequat ante.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="block">
                            <div class="media">
                                <div class="pull-right"><i class="fa fa-comments"></i></div>
                                <div class="media-body">
                                    <h4 class="media-heading">Call to Free</h4>
                                    Proin dictum elementum velit. Fusce euismod consequat ante.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="block">
                            <div class="media">
                                <div class="pull-right"><i class="fa fa-trophy"></i></div>
                                <div class="media-body">
                                    <h4 class="media-heading">Money Back!</h4>
                                    Proin dictum elementum velit. Fusce euismod consequat ante.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /PAGE -->
--%>
    </div>
    <!-- /CONTENT AREA -->

    <!-- PAGE --><!-- FOOTER -->
	<jsp:include page="hmr-footer.jsp" />
    <!-- /FOOTER -->

    <div id="to-top" class="to-top" style="background-color: #93bcff"><i class="fa fa-angle-up"></i></div>

</div>
<!-- /WRAPPER -->

<!-- JS Global -->
<script src="assets/plugins/jquery/jquery-2.0.0.min.js"></script>
<script src="assets/plugins/jquery-ui/jquery-ui-1.11.1.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="assets/plugins/superfish/js/superfish.min.js"></script>
<script src="assets/plugins/prettyphoto/js/jquery.prettyPhoto.js"></script>
<script src="assets/plugins/owl-carousel2/owl.carousel.min.js"></script>
<script src="assets/plugins/jquery.sticky.min.js"></script>
<script src="assets/plugins/jquery.easing.min.js"></script>
<script src="assets/plugins/jquery.smoothscroll.min.js"></script>
<script src="assets/plugins/smooth-scrollbar.min.js"></script>


<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>

<!-- JS Page Level -->
<script src="assets/js/theme.js"></script>
<script src="assets/plugins/gridder/jquery.gridder.min.js"></script>

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/plugins/jquery.cookie.js"></script>
<!-- <script src="assets/js/theme-config.js"></script> -->
<!--<![endif]-->

<script>
$(document).ready(function(){
    $('#myTable').dataTable();
    <%if(msgInfo!=null){%>
    var msgInfo = "<%=msgInfo%>";
    var msgbgcol = "<%=msgbgcol%>";
    var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
    msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
    msgBoxValue = msgBoxValue + '</div>';
    document.getElementById("msgDiv").innerHTML=msgBoxValue;
    <%}%>
    setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);
	
 	// Call Gridder
    $('.gridder').gridderExpander({
        scroll: true,
        scrollOffset: 30,
        scrollTo: "panel", // panel or listitem
        animationSpeed: 400,
        animationEasing: "easeInOutExpo",
        showNav: true, // Show Navigation
        nextText: "<span></span>", // Next button text
        prevText: "<span></span>", // Previous button text
        closeText: "", // Close button text
        onStart: function () {
            //Gridder Inititialized
            console.log('On Gridder Initialized...');

        },
        onContent: function () {
            //Gridder Content Loaded
        },
        onClosed: function () {
            //Gridder Closed
            console.log('On Gridder Closed...');

        }
    });
});


function viewLot(id) {
	$('input[name="manager"]').val("lot-manager");
	$('input[name="action"]').val("lotBidDetails");
	$('input[name="lotId_wip"]').val(id);
	$( "#frm" ).submit();
}

function showMaxBidForm(action, value, lot, id, qtyid) {
	$('<div id="maxbid-form" title="Set Maximum Bid"></div>').dialog({
		height: "auto",
		width: 350,
		title: "Set Maximum Bid",
		modal: true,
		open: function (event, ui) {
			var dialog_html = '<p id="validateTips">All fields are required.</p><label for="maxbid-'+ id +'">Amount</label><div class="input-group"><span class="input-group-addon">' + "<%=currency%>" + '</span>' +
			'<input type="text" name="maxbid-'+ id +'" id="maxbid-'+ id +'" placeholder="'+ value +'" class="form-control">' +
			'</div>';
			$(this).html(dialog_html);
			$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
		},
		buttons: {
			"Set Max": function(){
				value = parseFloat(parseFloat(value).toFixed(2));
				var maxbid_value = parseFloat(parseFloat($("#maxbid-"+id).val().trim()).toFixed(2));

				if(isNaN(maxbid_value)) maxbid_value = 0;
				if(maxbid_value <= value) {
					$("#validateTips" ).text( 'Amount must be greater than ' + value ).addClass( "ui-state-highlight" );
					setTimeout(function() {	$( "#validateTips" ).removeClass( "ui-state-highlight", 1500 );	}, 500 );
				} else {				
					$(this).dialog("close");
					submitPage(action, maxbid_value, lot, id, qtyid,'')
				}
			},
			Cancel: function() {
				$(this).dialog("close");
			}
		},
		close: function() {
			$("#maxbid-form").remove();
		}
	}).dialog('widget').position({ my: 'center', at: 'center', of: $(this) });
}

function showPreBidForm(action, value, lot, id, qtyid) {
	$('<div id="prebid-form" title="Pre-Bid"></div>').dialog({
		height: "auto",
		width: 350,
		title: "Pre-Bid",
		modal: true,
		open: function (event, ui) {
			var dialog_html = '<p id="validateTips">All fields are required.</p><label for="prebid-'+ id +'">Amount</label><div class="input-group"><span class="input-group-addon">' + "<%=currency%>" + '</span>' +
			'<input type="text" name="prebid-'+ id +'" id="prebid-'+ id +'" placeholder="'+ value +'" class="form-control">' +
			'</div>';
			$(this).html(dialog_html);
			$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
		},
		buttons: {
			"Pre-Bid": function(){
				value = parseFloat(parseFloat(value).toFixed(2));
				var prebid_value = parseFloat(parseFloat($("#prebid-"+id).val().trim()).toFixed(2));

				if(isNaN(prebid_value)) prebid_value = 0;
				if(prebid_value <= value) {
					$("#validateTips" ).text( 'Amount must be greater than ' + value ).addClass( "ui-state-highlight" );
					setTimeout(function() {	$( "#validateTips" ).removeClass( "ui-state-highlight", 1500 );	}, 500 );
				} else {				
					$(this).dialog("close");
					submitPage(action, prebid_value, lot, id, qtyid,'')
				}
			},
			Cancel: function() {
				$(this).dialog("close");
			}
		},
		close: function() {
			$("#prebid-form").remove();
		}
	}).dialog('widget').position({ my: 'center', at: 'center', of: $(this) });
}


function showNegotiatedBidForm(action, value, lot, id, qtyid) {
	$('<div id="negotiated-form" title="Pre-Bid"></div>').dialog({
		height: "auto",
		width: 350,
		title: "Make Offer",
		modal: true,
		open: function (event, ui) {
			var dialog_html = '<p id="validateTips">All fields are required.</p><label for="negotiated-'+ id +'">Amount</label><div class="input-group"><span class="input-group-addon">' + "<%=currency%>" + '</span>' +
			'<input type="text" name="negotiated-'+ id +'" id="negotiated-'+ id +'" placeholder="'+ value +'" class="form-control">' +
			'</div>'+
			'<label for="negotiated-note-'+ id +'">Note</label>'+
			'<textarea maxlength="50" class="form-control" rows="5" id="negotiated-note-'+ id +'"></textarea>';
			$(this).html(dialog_html);
			$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
		},
		buttons: {
			"Send Offer": function(){
				value = parseFloat(parseFloat(value).toFixed(2));
				var negotiated_value = parseFloat(parseFloat($("#negotiated-"+id).val().trim()).toFixed(2));
				var negotiated_note = $("#negotiated-note-"+id).val();
				

				if(isNaN(negotiated_value)) negotiated_value = 0;
				if(negotiated_value <= 0) {
					$("#validateTips" ).text( 'Amount must be greater than 0' ).addClass( "ui-state-highlight" );
					setTimeout(function() {	$( "#validateTips" ).removeClass( "ui-state-highlight", 1500 );	}, 500 );
				} else {				
					$(this).dialog("close");
					submitPage(action, negotiated_value, lot, id, qtyid, negotiated_note);
				}
			},
			Cancel: function() {
				$(this).dialog("close");
			}
		},
		close: function() {
			$("#negotiated-form").remove();
		}
	}).dialog('widget').position({ my: 'center', at: 'center', of: $(this) });
}

function submitPage(action, value, lot, id, qtyid, note) {
	var unit_qty = 0;
	if($("#"+qtyid+" :selected").length ) unit_qty = $("#"+qtyid+" :selected").attr('value');
	
	$('input[name="manager"]').val("bid-manager");
	$('input[name="doaction"]').val(action);
	$('input[name="amount"]').val(value);
	$('input[name="lotId"]').val(lot);
	$('input[name="lotId_wip"]').val(id);
	$('input[name="unit_qty"]').val(unit_qty);
	$('input[name="note"]').val(note);
	
	$('<div id="dialog-confirm"></div>').dialog({
		resizable: false,
	    height: "auto",
	    width: 400,
	    modal: true,
	    title: "Confirmation",
	    closeOnEscape: false,
        open: function (event, ui) {
        	var dialog_title = "Confirmation";
        	var currency_html = "<%=currency%>";
        	var dialog_html = '<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>Are you sure?</p>';
        	var unit_qty_html = "";
        	if(unit_qty>0) unit_qty_html = ' with quantity of ' + unit_qty + ' units';
        	
        	$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
      
        	if(action=="BID") {
        		dialog_title = "Bid confirmation";
        		dialog_html = '<p>You will bid ' + value +' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+
        			'<p>Are you sure?</p>';
        	}else if(action=="BUY") {
        		dialog_title = "Buy confirmation";
        		dialog_html = '<p>You will buy this lot for' + value + ' '+currency_html + unit_qty_html +'.</p>'+
        			'<p>Are you sure?</p>';
        	}else if(action=="SET-MAXIMUM-BID") {
        		dialog_title = "Set maximum bid confirmation";
        		dialog_html = '<p>You will will set your maximum bid of ' + value.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+
        			'<p>Are you sure?</p>';
        	}else if(action=="NEGOTIATED") {
        		dialog_title = "Offer bid confirmation";
        		dialog_html = '<p>You will will set your offer bid of ' + value.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+
        			'<p>Are you sure?</p>';
        	}else if(action=="PRE-BID") {
        		dialog_title = "Pre-bid confirmation";
        		dialog_html = '<p>You will will set pre-bid of ' + value.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+
        			'<p>Are you sure?</p>';
        	}

            $(this).dialog( "option", "title", dialog_title);
            $(this).html(dialog_html);
        },
        buttons: {
        	"Yes": function() {
  	          $( this ).dialog( "close" );
  	          $( "#frm" ).submit();
  	        },
            Cancel: function () {
                $(this).dialog("close");
            }
        },
		close: function() {
			$("#dialog-confirm").remove();
		}
    }).dialog('widget').position({ my: 'center', at: 'center', of: $(this) }); //end confirm dialog
}

</script>

<form action="" id="frm" name="frm" method="post">
   <input type="hidden" name="manager" id="manager" value=""/>
   <input type="hidden" name="action" id="action" value=""/>
   <input type="hidden" name="doaction" id="doaction" value=""/>
   <input type="hidden" name="lotId" id="lotId" value=""/>
   <input type="hidden" name="lotId_wip" id="lotId_wip" value=""/>
   <input type="hidden" name="unit_qty" id="unit_qty" value=""/>
   <input type="hidden" name="note" id="note" value=""/>
   <input type="hidden" name="amount" id="amount" value=""/>
   <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
   <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
</form>
</body>
</html>
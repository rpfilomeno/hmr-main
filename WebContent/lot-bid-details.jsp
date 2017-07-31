<!DOCTYPE html>
<%@ page import="hmr.com.bean.Lot"
		 import="hmr.com.bean.Auction"
		 import="hmr.com.bean.Lov"
		 import="hmr.com.bean.Item"
		 import="hmr.com.bean.Image"
		 import="hmr.com.manager.ImageManager"
		 import="java.util.List"  
		 import="java.math.BigDecimal"  
		 import="java.util.HashMap"  
		 import="java.text.DecimalFormat"
		 import="java.text.SimpleDateFormat"
		 import="java.sql.Timestamp"
		 import="hmr.com.bean.BiddingTransaction"
%>
<%
	String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
	String COMPANY_NAME_ACRONYM = (String)request.getSession().getAttribute("COMPANY_NAME_ACRONYM");

	String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
	String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
	
	
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
	
	Lot lot = request.getAttribute("lot")!=null ? (Lot)request.getAttribute("lot") : (Lot)request.getSession().getAttribute("lot");
	List<Item> items = request.getAttribute("items")!=null ? (List<Item>)request.getAttribute("items") : (List<Item>)request.getSession().getAttribute("items");
	
	Auction auction = request.getAttribute("auction")!=null ? (Auction)request.getAttribute("auction") : (Auction)request.getSession().getAttribute("auction");
	
	HashMap<Integer, Lov> currencyLovHM  = request.getAttribute("CURRENCY-HM")!=null ? (HashMap<Integer, Lov>)request.getAttribute("CURRENCY-HM") : (HashMap<Integer, Lov>)request.getSession().getAttribute("CURRENCY-HM");
	
	//HashMap<BigDecimal, Lot> lotHM  = request.getAttribute("lotHM")!=null ? (HashMap<BigDecimal, Lot>)request.getAttribute("lotHM") : (HashMap<BigDecimal, Lot>)request.getSession().getAttribute("lotHM");
	//List<Item> iList = request.getAttribute("iList")!=null ? (List<Item>)request.getAttribute("iList") : (List<Item>)request.getSession().getAttribute("iList");

	List<Image> lot_images = (List<Image>)request.getAttribute("lot_images");
	
	List<BiddingTransaction> bidding_transactions = (List<BiddingTransaction>)request.getAttribute("bidding_transactions");
	
    DecimalFormat df = new DecimalFormat("#,###,##0");
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy  HH:mm");
	
	ImageManager iMngr = new ImageManager();
	List<Image> item_images = null;
	
	String bAmount = "0";
	
	%>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

        <title><%=COMPANY_NAME%></title>
        
        <!-- Favicons -->
        <link rel="apple-touch-icon" sizes="180x180" href="assets/themes/hmr/apple-touch-icon.png">
        <link rel="icon" type="image/png" href="assets/themes/hmr/favicon-32x32.png" sizes="32x32">
        <link rel="icon" type="image/png" href="assets/themes/hmr/favicon-16x16.png" sizes="16x16">
        <link rel="manifest" href="assets/themes/hmr/manifest.json">
        <link rel="mask-icon" href="assets/themes/hmr/safari-pinned-tab.svg" color="#222222">
        <link rel="shortcut icon" href="assets/themes/hmr/favicon.ico">
        <meta name="theme-color" content="#ffffff">

    
        <meta property="og:site_name" content="HMR Auctions"/>
        <meta property="og:title" content="Product View — HMR Auctions"/>
        <meta property="og:url" content="product-view.html"/>
                <meta property="og:type" content="website" />
        
        <meta property="og:description" content=""/>
        
        <meta property="og:image" content=""/>

        <meta itemprop="name" content="Product View"/>
        <meta itemprop="url" content="product-view.html"/>
        <meta itemprop="description" content=""/>
        <meta itemprop="thumbnailUrl" content=""/>
        
        <meta name="twitter:card" content="summary_large_image"/>
        <meta name="twitter:title" content="Product View"/>
        <meta name="twitter:image" content=""/>
        <meta name="twitter:url" content="product-view.html"/>
        <meta name="twitter:text" content=""/>
        <meta name="twitter:domain" content="">

        <meta name="description" content="" />

        <link rel="stylesheet" href="assets/themes/hmr/css/bootstrap.css">
		<link rel="stylesheet" href="assets/themes/hmr/css/ionicons.min.css">
		<link rel="stylesheet" href="assets/themes/hmr/css/main.css?v=36502498">
		
		<link rel="stylesheet" href="assets/css/gridder.css" />
		<link rel="stylesheet" href="assets/plugins/jquery-ui/themes/smoothness/jquery-ui.min.css">
		
		<script src=assets/themes/hmr/js/vendor/modernizr.js></script>
		<script src=assets/themes/hmr/js/vendor/jquery-1.11.3.js></script>
		<script src=assets/themes/hmr/js/vendor/jquery-migrate-1.2.1.min.js></script>
		<script src=assets/themes/hmr/js/vendor/social.js></script>
		<script src=assets/themes/hmr/js/vendor/masonry.pkgd.min.js></script>
		<script src=assets/themes/hmr/js/vendor/owl.carousel.min.js?v=2></script>
		<script src=assets/themes/hmr/js/vendor/jquery.form.js></script>
		<script src=assets/themes/hmr/js/vendor/jquery.easing.1.3.js></script>
		<script src=assets/themes/hmr/js/bootstrap.js></script>
		<script src=assets/themes/hmr/js/vendor/bootbox.min.js></script>
		<script src=assets/themes/hmr/js/vendor/url.min.js></script>
		<script src=assets/themes/hmr/js/vendor/msis.js></script>
		<script src=assets/themes/hmr/js/vendor/jquery.lazyload.min.js></script>
		<script src=assets/themes/hmr/js/main.js?v=79186204></script>
		
		<script src="assets/plugins/jquery-ui/jquery-ui-1.11.1.min.js"></script>
		<script src="assets/plugins/gridder/jquery.gridder.min.js"></script>
		<script src="assets/plugins/jquery.bsAlerts.min.js"></script>
                
    </head>
    <body data-is-mobile="" id="c" >
    
    <input type="hidden" id="base_url" value="">
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
 
<div id="ms--main--nav">
	<nav id="main-navigation" class="navbar navbar-fixed-top">
  <div id="top-nav">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="top-nav-items nav-white visible-md visible-lg">
            
            <ul class="top-navbar">
              <li>(02)548-6962</li>
              <li>0917 548 3603</li>
              <li>
                  <a href="mailto:auction@hmrphils.com">auction@hmrphils.com</a>
              </li>
              <li>
                <a href="">Home</a>
              </li>
              <% if(fullName!=null && !"null".equals(fullName)){%>
              <li>
                <a href="bid?mngr=get&a=logout&uid=<%=userId%>">Logout</a>
              </li>
              <% } else { %>
              <li>
                <a href="bid?mngr=get&a=login">Login</a>
              </li>
              <li>
                <a href="bid?mngr=get&a=registration">Register</a>
              </li>
              <% } %>
              
            </ul>
    
            
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row gutter-0">
      <div class="col-md-2">
        <div class="site--logo">
                      <a class="logo-default" href="bid"><img src="assets/themes/hmr/img/HMR-logo-white.png" alt="HMR Auctions" class="img-responsive"></a>
                  </div>
      </div>

      <div class="col-md-10">
        <div class="row gutter-0">
          <div class="col-md-8">
            
              <form action="#" id="nav-search-form" method="post" accept-charset="utf-8" onkeypress="stopEnterSubmitting(window.event)">
                
                <div class="input-group nav-search-group">
                  <input type="text" class="form-control" id="nav-search-input" placeholder="Search for products, brands, shops">
                  <span class="input-group-btn">
                    <button class="btn btn-default nav-search-btn" type="submit"><span class="ion-ios-search-strong"></span></button>
                  </span>
                </div>

              
              </form>
          </div>
          <div class="col-md-4">
            <div id="main-nav-items" class="main-nav-items nav-white visible-md visible-lg">

              <ul class="nav navbar-nav navbar-right navbar-icons">
                <% if (fullName!=null) {%>
                <li>
                    <a href="#"><%=fullName%></a>
                </li>
                <% } %>
                <li>
                  <a href="#">
                    <span id="bag-count"></span>
                    <span class="navbar-icon ion-bag"></span>
                  </a>
                </li>
                
                
              </ul>
      
              
            </div>
          </div>
        </div>

        

      </div>
    </div>
  </div>

  <div id="sub-navigation">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <ul class="navbar-subnav">
            <li>
              <a href="">Services</a>
            </li>
            <li>
              <a href="">Gallery</a>
            </li>
            <li>
              <a href="">Contact Us</a>
            </li>
          </ul>    
        </div>
      </div>
    </div>
  </div>
</nav>




<div id="mobile-nav-trigger" class="visible-sm visible-xs">
  <div id="nav-trigger">
      <span></span>
      <span></span>
      <span></span>
  </div>
</div>

<div id="mobile-nav-wrapper">
  <div id="mobile-logo" class="site--logo">
    <a class="logo-default" href=""><img src="assets/themes/hmr/img/HMR-logo-white.png" alt="HMR Auctions" class="img-responsive"></a>
  </div>
  <div id="mobile-nav-inject"></div>
</div>




<div id="search-dialog" class="ms-dialog hide">
  <div class="ms-dialog-header">
    <a href="#" class="ms-dialog-close-btn"><span class="ion-ios-close-empty"></span></a>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-8 col-md-offset-2">
        
        <form action="" id="search-form" method="post" accept-charset="utf-8" onkeypress="stopEnterSubmitting(window.event)">
          
        <div class="search-input-wrap">
          <input type="text" class="form-control" id="search-input" placeholder="Search">
        </div>

        </form>
      </div>
    </div>
  </div>
</div>
	
</div>

	
<div id="ms--main--body">

	<div class="hmr-breadcrumb">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li>
						<a href="">Home</a>
					</li>
					<li>
						<a href="electronics-and-gadgets">Categories</a>
					</li>
					<li class="active">Product View</li>
				</ol>
			</div>
		</div>
	</div>
</div>

<section id="hmr-main-container">
	<div class="container">
		<div class="row">
		<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
			
			<div class="col-md-4 col-lg-3 visible-lg visible-md">
				<div class="side-widget">
					<div class="main-category-header">
						Gallery
					</div>
					<% for (Image i : lot_images) {	%>
						<div class="side-product-item">
							<div class="product-item">
								<a href="#">
									<div class="product-image-wrap">
										<div class="image feature-fade-in" style="background-image: url('image?id=<%=i.getId()%>&t=t')"></div>
									</div>
								</a>
							</div>
						</div>
					<% } %>
					
					
				</div>
				
			</div>
			<div class="col-md-8 col-lg-9">
				
				<div class="product-full-view-wrap">
					<div class="row">
						<div class="col-sm-7">
							<div class="full-product-picture">
								<div class="image feature-fade-in" style="background-image: url('image?id=<%=lot.getId()%>&t=lt')" ></div>
							</div>
						</div>
						<div class="col-sm-5">
							<h3 class="full-product-name"><%=lot.getLot_desc()%></h3>
							<div class="product-details">
								<%  if( lot.getIs_available_lot() > 0) { %>
								<div class="product-detail">Unit Quantity: <%=lot.getUnit_qty()%></div>
								<% } %>
								<div class="product-detail">Highest Bid: <%=df.format(lot.getAmount_bid())%> <%=currency%></div>
								<div class="product-detail">Asking Bid: <%=df.format(lot.getAmount_bid_next())%> <%=currency%></div>
								<div class="product-detail">Bids: <%=lot.getBid_count()%></div>
							</div>
							<div class="product-details">
								<% if(user_id != null && user_role_id > 0){ %>
									<% if( lot.getIs_available_lot() > 0) { %>
		                            	<% Integer i = lot.getUnit_qty(); %>
		                                <div class="form-group">
                        	 				<select class="form-control" id="qty_<%=lot.getId()%>" name="qty_<%=lot.getId()%>">
		                                   	 	<% while(i > 0) { %>
		                                   	 		<option value="<%=i%>"><%=i%> unit<% if(i>1){ %>s<%}%></option>
		                                   	 		<% i = i - 1; %>
		                                   	 	<% } %>
		                                   	</select>
		                                 </div>
		                             <% } %>
		                             
									<% if(lot.getIs_bid() == 1){ %>
										<% if(auction.getAuction_type() == 15){ %>
			                            	<% if(auction.getStart_date_time().after(new Timestamp(System.currentTimeMillis())) && lot.getActive()>0){ %>
			                                	<button class="btn btn-theme btn-block" href="#" onclick="showPreBidForm('PRE-BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>','qty_<%=lot.getId()%>')">PRE-BID</button>
			                                <% } else { %>
			                                   	<button class="btn btn-theme btn-block" href="#" onclick="submitPage('BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>','')">BID <%=df.format(lot.getAmount_bid_next())%> <%=currency%> </button>
			                                   	<button class="btn btn-theme btn-block" href="#" onclick="showMaxBidForm('SET-MAXIMUM-BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>')">SET MAXIMUM BID</button>
			                               	<% } %>
		                                <% }else if(auction.getAuction_type() == 16){ %>
		                                   	 	<button class="btn btn-theme btn-block" href="#" onclick="showNegotiatedBidForm('NEGOTIATED', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>','qty_<%=lot.getId()%>')">MAKE OFFER</button>
		                               	<% } %>
									<% } %>
									
									<% if(lot.getIs_buy() == 1){ %>
                                		<button class="btn btn-theme btn-block" href="#" onclick="submitPage('BUY', '<%=lot.getBuy_price()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>','')">BUY <%=df.format(lot.getBuy_price())%> <%=currency%></button>
                                	<% } %>
									
								<% } else { %>
									<a class="btn btn-primary btn-theme btn-block" href="bid?mngr=get&a=registration">REGISTER</a>
									<a class="btn btn-primary btn-theme btn-block" href="bid?mngr=get&a=login">LOGIN</a>
								<% } %>
							</div>
						</div>
					</div>

					<div class="clearfix top20"></div>

					<ul class="nav nav-tabs nav-tabs-2" role="tablist">
						<li role="presentation" class="active">
							<a href="#product-description-tab" aria-controls="product-description-tab" role="tab" data-toggle="tab">Description</a>
						</li>
						<li role="presentation">
							<a href="#product-delivery-tab" aria-controls="product-delivery-tab" role="tab" data-toggle="tab">Terms and Conditions</a>
						</li>
					</ul>

					<div class="clearfix"></div>

					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="product-description-tab">
							<h5>Overview</h5>
							<p><%=auction.getAuction_desc()%></p>
						</div>

						<div role="tabpanel" class="tab-pane" id="product-delivery-tab">
							<h5>Delivery</h5>
							<p><%=auction.getTerms_and_conditions()%></p>

						</div>
						
					</div>
				</div>

				<div id="product-list-wrapper">
					<ul class="nav nav-tabs nav-tabs-2" role="tablist">
						<li role="presentation" class="active">
							<a href="#online-bidding-tab" aria-controls="lots-tab" role="tab" data-toggle="tab">Items</a>
						</li>
					</ul>
					<div class="clearfix top10"></div>

					

					<div class="row gutter-10">
						<%for(Item i : items) {%>
							<div class="col-md-6 col-xs-6">
								<div class="product-item">
									<a href="#">
										<div class="product-image-wrap">
											<div class="image feature-fade-in" style="background-image: url('image?id=<%=i.getId()%>&t=it')"></div>
										</div>
										<div class="product-body">
											<h3 class="product-name">#<%=i.getReference_no()%></h3>
											<div class="product-details">
												<div class="product-detail">Description: <%=i.getItem_desc()%></div>
											</div>
										</div>
									</a>
									
								</div>
								
							</div>
						<% } %>
					</div>
					
				</div>
				
				<div class="clearfix top20"></div>

					<ul class="nav nav-tabs nav-tabs-2" role="tablist">
						<li role="presentation" class="active">
							<a href="#product-description-tab2" aria-controls="product-description-tab2" role="tab" data-toggle="tab">Bid History</a>
						</li>
					</ul>

					<div class="clearfix"></div>

					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="product-description-tab2">
							<h5>Last 5 Bids</h5>
							<table class="table">
						    	<thead>
						        	<tr>
						               	<th>ID</th>
						            	<th>Amount</th>
						                <th>Date</th>
						            </tr>
						        </thead>
						        <tbody>
						        	<% for(BiddingTransaction bidding_transaction : bidding_transactions ) { %>
						            	<tr>                     	
											<% if(bidding_transaction.getAction_taken()==1) {
						                       bAmount = (bidding_transaction.getAmount_bid().compareTo(BigDecimal.ZERO) != 0) ? df.format(bidding_transaction.getAmount_bid()) : "0.00";
						                       } else if(bidding_transaction.getAction_taken()==2){
						                       bAmount = (bidding_transaction.getAmount_buy().compareTo(BigDecimal.ZERO) != 0) ? df.format(bidding_transaction.getAmount_buy()) : "0.00";
						                       } else if(bidding_transaction.getAction_taken()==3) {
						                       bAmount = (bidding_transaction.getAmount_offer().compareTo(BigDecimal.ZERO) != 0) ? df.format(bidding_transaction.getAmount_offer()) : "0.00";  
						                  	}%>
                                            <td>
						                    	<% if(user_id == bidding_transaction.getUser_id()) { %>
						                        <div>You</div> 
						                        <% } else { %>
						                    	<div>User #<%=bidding_transaction.getUser_id() %></div>
						                        <% } %>
						                    </td>
						                   	<td class="total"><%=currency%>&nbsp;<%=bAmount%> </td>
						                    <td class="diliver-date"> <%=sdf.format(bidding_transaction.getDate_created()) %> </td>
						                 </tr>
						        	<% } %>
						        </tbody>
						    </table>
						</div>


					</div>

			</div>
		</div>
	</div>
	

	
</section>




<div class="clearfix top100"></div>
<div class="clearfix top100"></div>
<div class="clearfix top100"></div>
<div class="clearfix top100"></div>




</div>


	<section class="instagram-section">
	<div class="container-fluid">
		<!-- <div id="instagram" class="row no-gutter"></div> -->
	</div>
</section>




<div id="footer">
	<div class="container">
		<div class="footer-lv1">
			<div class="row">
				<div class="col-md-8">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-3">
									<h3 class="top10"><a href="">HMR</a></h3>
								</div>
								<div class="col-md-9">
									<div class="row">
																				<div class="col-sm-6">
											<h4 class="footer-header">Purchase</h4>
											<ul class="list-block">
												<li>
													<a href="account">My Account</a>
												</li>
												<li>
													<a href="terms-and-conditions">Terms and Conditions</a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="tarasd">
						<h4 class="footer-header">Subscribe to our Weekly Newsletter</h4>
						<form action="q/validate_email_subscribers" id="newsletter-form" method="post" accept-charset="utf-8" onkeypress="stopEnterSubmitting(window.event)">
						<div class="form-group">
							<div class="input-group">
								<input type="text" name="email" id="email" class="form-control" placeholder="Your email address">
								<span class="input-group-btn">
									<input type="submit" class="btn btn-primary" value="Join" style="width: 90px;" />
								</span>
							</div><!-- /input-group -->
							<div class="help-block"></div>
						</div>
						</form>					</div>

					<ul class="social-media-links social-media-links-inline">
						<li>
							<a target="_BLANK" href="#">
								<span class="social-icon"><img src="assets/themes/hmr/img/facebook-logo-white.png" class="img-responsive"></span>
							</a>
						</li>
						<li>
							<a target="_BLANK" href="#">
								<span class="social-icon"><img src="assets/themes/hmr/img/youtube-logo-white.png" class="img-responsive"></span>
							</a>
						</li>
						<li>
							<a target="_BLANK" href="#">
								<span class="social-icon"><img src="assets/themes/hmr/img/twitter-logo-white.png" class="img-responsive"></span>
							</a>
						</li>
						
					</ul>

					<div class="clearfix"></div>
					<div class="all-rights-reserved">
						HMR Auctions © 2017. All Rights Reserved.
					</div>

				</div>
			</div>
		</div>
	</div>
</div>


<script>

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
					submitPage(action, maxbid_value, lot, id, qtyid,'');
				}
			},
			Cancel: function() {
				$(this).dialog("close");
			}
		},
		close: function() {
			$("#maxbid-form").remove();
		}
	});
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
        		dialog_title = "Negotiated bid confirmation";
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
    }); //end confirm dialog
}

function showAlert(msgInfo, msgbgcol) {
	var priority = null;
	if(msgbgcol=="red") {
		priority = "error";
	}else if(msgbgcol=="green") {
		priority = "success";
	}else {
		priority = "warning";
	}

	$(document).trigger("add-alerts", [
		{
			"message": msgInfo,
	         "priority": priority
	    }
	]);
	
	
}

function stopEnterSubmitting(e) {
    if (e.keyCode == 13) {
        var src = e.srcElement || e.target;
        if (src.tagName.toLowerCase() != "textarea") {
            if (e.preventDefault) {
                e.preventDefault();
            } else {
                e.returnValue = false;
            }
        }
    }
}	

$(document).ready(function(){
	<%if(msgInfo!=null){%>
	var msgInfo = "<%=msgInfo%>";
	var msgbgcol = "<%=msgbgcol%>";
	showAlert(msgInfo, msgbgcol);
	<%}%>
	
});


</script>

<form action="" id="frm" name="frm" method="post" onkeypress="stopEnterSubmitting(window.event)">
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

<!DOCTYPE html>
<%@ page import="hmr.com.bean.Auction"
		 import="java.util.List"  
		 import="java.text.SimpleDateFormat"
		 import="java.math.BigDecimal"
%>
<%
	System.out.println("PAGE index.jsp");

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
	
	String imgSize="85";
	String imgHeight="85";
	
	// IDs
	Integer user_id = request.getSession().getAttribute("user-id")!=null ? (Integer)request.getSession().getAttribute("user-id") : null;
	Integer user_role_id = request.getSession().getAttribute("user-role-id")!=null ? (Integer)request.getSession().getAttribute("user-role-id") : 0;
	
	
	List<Auction> activeOnlineAuctionList = request.getAttribute("ACTIVE-ONLINE-AUCTION-LIST")!=null ? (List<Auction>)request.getAttribute("ACTIVE-ONLINE-AUCTION-LIST") : (List<Auction>)request.getSession().getAttribute("ACTIVE-ONLINE-AUCTION-LIST");
	
	List<Auction> activeNegotiatedAuctionList = request.getAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST")!=null ? (List<Auction>)request.getAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST") : (List<Auction>)request.getSession().getAttribute("ACTIVE-NEGOTIATED-AUCTION-LIST");
	
	List<Auction> activeLiveAuctionList = request.getAttribute("ACTIVE-ONLINE-AUCTION-LIST")!=null ? (List<Auction>)request.getAttribute("ACTIVE-ONLINE-AUCTION-LIST") : (List<Auction>)request.getSession().getAttribute("ACTIVE-ONLINE-AUCTION-LIST");
	
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy  HH:mm");
	
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
        <meta property="og:title" content="Home — HMR Auctions"/>
        <meta property="og:url" content=""/>
                <meta property="og:type" content="website" />
        
        <meta property="og:description" content=""/>
        
        <meta property="og:image" content="assets/themes/hmr/-800-1200."/>

        <meta itemprop="name" content="Home"/>
        <meta itemprop="url" content=""/>
        <meta itemprop="description" content=""/>
        <meta itemprop="thumbnailUrl" content="assets/themes/hmr/-800-1200."/>
        
        <meta name="twitter:card" content="summary_large_image"/>
        <meta name="twitter:title" content="Home"/>
        <meta name="twitter:image" content="assets/themes/hmr/-800-1200."/>
        <meta name="twitter:url" content=""/>
        <meta name="twitter:text" content=""/>
        <meta name="twitter:domain" content="">

        <meta name="description" content="" />

        <link rel="stylesheet" href="assets/themes/hmr/css/bootstrap.css">
        <link rel="stylesheet" href="assets/themes/hmr/css/ionicons.min.css">
        <link rel="stylesheet" href="assets/themes/hmr/css/main.css?v=89617520">
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
        <script src=assets/themes/hmr/js/main.js?v=67412309></script>
        
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
                <a href="/bid">Home</a>
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
            
              <form action="bid" id="nav-search-form" method="post" accept-charset="utf-8" onkeypress="stopEnterSubmitting(window.event)">
                
                <div class="input-group nav-search-group">
                  <input type="text" class="form-control" name="nav-search-input" id="nav-search-input" placeholder="Search for products, brands, shops">
                  <span class="input-group-btn">
                    <button class="btn btn-default nav-search-btn" type="submit"><span class="ion-ios-search-strong"></span></button>
                  </span>
                </div>
                
				<input type="hidden" name="manager" id="manager" value="search-manager"/>
   				<input type="hidden" name="action" id="action" value=""/>
              	<input type="hidden" name="userId" id="userId" value="<%=userId %>"/>
   				<input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
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
          	<% if(user_role_id==1 || user_role_id ==4) { %>
          	<li>
              <a href="bid?mngr=auction-manager&a=auctionList&uid=<%=userId %>">Administration</a>
            </li>
            <% } %>
            <li>
              <a href="#">Services</a>
            </li>
            <li>
              <a href="#">Gallery</a>
            </li>
            <li>
              <a href="#">Contact Us</a>
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
	

	
<section id="hmr-main-container">
	<div class="container">
		<div class="row gutter-10">
			<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
			<div class="col-md-4 col-lg-3">
				<div class="side-widget">
					<div id="category-wrap">
						<div class="main-category-header">
							Categories
						</div>
						<ul class="main-category-list">
							
<li>
  <a href="electronics-and-gadgets.html">Electronics and Gadgets</a>
  <ul class="sub-category-list simple-nav-list">
	<li>
		<a href="electronics-and-gadgets.html">Cameras</a>
	</li>
	<li>
		<a href="electronics-and-gadgets.html">Computer and Accessories</a>
	</li>
	<li>
		<a href="electronics-and-gadgets.html">Mobile Phone</a>
	</li>
	<li>
		<a href="electronics-and-gadgets.html">TV, Video, Audio</a>
	</li>
	<li>
		<a href="electronics-and-gadgets.html">Video Games</a>
	</li>
  </ul>
</li>
<li>
  <a href="electronics-and-gadgets.html">Furniture</a>
  <ul class="sub-category-list simple-nav-list">
	<li><a href="electronics-and-gadgets.html">Chairs</a></li>
	<li><a href="electronics-and-gadgets.html">Desks &amp; Home Office Furniture</a></li>
	<li><a href="electronics-and-gadgets.html">Dining Sets</a></li>
	<li><a href="electronics-and-gadgets.html">Dressers</a></li>
	<li><a href="electronics-and-gadgets.html">Entertainment Units</a></li>
	<li><a href="electronics-and-gadgets.html">Frames &amp Covers</a></li>
	<li><a href="electronics-and-gadgets.html">Futons</a></li>
	<li><a href="electronics-and-gadgets.html">Home Decor</a></li>
	<li><a href="electronics-and-gadgets.html">Sofas</a></li>
	<li><a href="electronics-and-gadgets.html">Tables</a></li>
	<li><a href="electronics-and-gadgets.html">TV Stands</a></li>
  </ul>
</li>
<li>
  <a href="electronics-and-gadgets.html">Industrial Equipment</a>
  <ul class="sub-category-list simple-nav-list">
	<li><a href="electronics-and-gadgets.html">Construction</a></li>
	<li><a href="electronics-and-gadgets.html">Electrical</a></li>
	<li><a href="electronics-and-gadgets.html">Machines</a></li>
	<li><a href="electronics-and-gadgets.html">Office Equipment</a></li>
	<li><a href="electronics-and-gadgets.html">Plumbing</a></li>
	<li><a href="electronics-and-gadgets.html">Power Tools</a></li>
	<li><a href="electronics-and-gadgets.html">Restaurant Equipment</a></li>
  </ul>
</li>
<li>
  <a href="electronics-and-gadgets.html">Real Estate</a>
  <ul class="sub-category-list simple-nav-list">
	<li><a href="electronics-and-gadgets.html">Agricultural</a></li>
	<li><a href="electronics-and-gadgets.html">Commercial</a></li>
	<li><a href="electronics-and-gadgets.html">Industrial</a></li>
	<li><a href="electronics-and-gadgets.html">Residential</a></li>
  </ul>
</li>
<li>
  <a href="electronics-and-gadgets.html">Vehicles</a>
  <ul class="sub-category-list simple-nav-list">
	<li><a href="electronics-and-gadgets.html">Bus</a></li>
	<li><a href="electronics-and-gadgets.html">Cars</a></li>
	<li><a href="electronics-and-gadgets.html">Commercial Trucks</a></li>
	<li><a href="electronics-and-gadgets.html">Motorcycle</a></li>
	<li><a href="electronics-and-gadgets.html">SUV</a></li>
	<li><a href="electronics-and-gadgets.html">Van and Mini Van</a></li>
  </ul>
</li>						</ul>
					</div>
				</div>
				
				<div class="side-widget">
					<h4 class="text-primary">Quick Links</h4>
					<ul class="simple-nav-list">
						<li>
							<a href="#">Services <span class="icon-right ion-chevron-right"></span></a>
						</li>
						<li>
							<a href="#">Contact Us <span class="icon-right ion-chevron-right"></span></a>
						</li>
					</ul>	
				</div>


				<div class="side-widget">
					<h4 class="text-primary">FAQ</h4>
					<ul class="simple-nav-list">
						<li>
							<a href="#">Auction Tips <span class="icon-right ion-chevron-right"></span></a>
						</li>
						<li>
							<a href="#">Basic Auction FAQs <span class="icon-right ion-chevron-right"></span></a>
						</li>
						<li>
							<a href="#">Auction Terminologies <span class="icon-right ion-chevron-right"></span></a>
						</li>
						<li>
							<a href="#">Online Auction FAQs <span class="icon-right ion-chevron-right"></span></a>
						</li>
					</ul>
				</div>
				



			</div>
			
			
			
			
			<div class="col-md-8 col-lg-9">
				<div id="landing-slider" class="owl-carousel">
					<div class="owl-slide" data-dot="1">
						<div class="container">
							<div class="row">
								<div class="col-xs-8 col-sm-7 col-lg-5">
									<div class="slide-content">
										<h1 class="slide-header">Slide 1</h1>
										<p class="slide-lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi aliquid nobis maxime sapiente, rerum, molestiae.</p>
										<div class="hero-action-btns">
											<a href="#" class="btn ms-btn btn-default hero-btn">View More <span class="left5 ion-chevron-right"></span></a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="bg-color-overlay"></div>
						<div class="image feature-fade-in" style="background-image: url('assets/themes/hmr/img/landing-pic-1.jpg')"></div>
					</div>
					<div class="owl-slide" data-dot="2">
						<div class="container">
							<div class="row">
								<div class="col-xs-8 col-sm-7 col-lg-5">
									<div class="slide-content">
										<h1 class="slide-header">Slide 2</h1>
										<p class="slide-lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi aliquid nobis maxime sapiente, rerum, molestiae.</p>
										<div class="hero-action-btns">
											<a href="#" class="btn ms-btn btn-default hero-btn">View More <span class="left5 ion-chevron-right"></span></a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="bg-color-overlay"></div>
						<div class="image feature-fade-in" style="background-image: url('assets/themes/hmr/img/landing-pic-2.jpg')"></div>
					</div>
					<div class="owl-slide" data-dot="3">
						<div class="container">
							<div class="row">
								<div class="col-xs-8 col-sm-7 col-lg-5">
									<div class="slide-content">
										<h1 class="slide-header">Slide 3</h1>
										<p class="slide-lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi aliquid nobis maxime sapiente, rerum, molestiae.</p>
										<div class="hero-action-btns">
											<a href="#" class="btn ms-btn btn-default hero-btn">View More <span class="left5 ion-chevron-right"></span></a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="bg-color-overlay"></div>
						<div class="image feature-fade-in" style="background-image: url('assets/themes/hmr/img/landing-pic-3.jpg')"></div>
					</div>
				</div>

				<div class="clearfix"></div>

			
				<section id="hmr-main-body">
					<div class="row section-row">
						<div class="col-md-12">
							
							<div role="tabpanel">
								<!-- Nav tabs -->
								<ul class="nav nav-tabs nav-tabs-2" role="tablist">
									<li role="presentation" class="active">
										<a href="#online-bidding-tab" aria-controls="online-bidding-tab" role="tab" data-toggle="tab">Online Bidding</a>
									</li>
									<li role="presentation">
										<a href="#negotiated-bids-tab" aria-controls="negotiated-bids-tab" role="tab" data-toggle="tab">Negotiated Bids</a>
									</li>
									<li role="presentation">
										<a href="#live-auction-tab" aria-controls="negotiated-bids-tab" role="tab" data-toggle="tab">Live Auctions</a>
									</li>
								</ul>
							
								<!-- Tab panes -->
								<div class="tab-content">
									<div role="tabpanel" class="tab-pane active" id="online-bidding-tab">
										<div id="online-bidding-slider" class="owl-carousel featured-product-carousel">
											<%for(Auction activeOnlineAuction : activeOnlineAuctionList) {%>
											<div class="owl-slide">
												<div class="hmr-card-wrap">
													<div class="card-image-wrap">
														<div class="image feature-fade-in owl-lazy" data-src="image?id=<%=activeOnlineAuction.getAuction_id()%>&t=at"></div>
													</div>
													<div class="card-body-wrap">
														<h3 class="card-title">
															<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeOnlineAuction.getId()%>">
																<%=activeOnlineAuction.getAuction_name()%>
															</a>
														</h3>
														<div class="card-snippet-wrap">
															Location: <%=activeOnlineAuction.getLocation()%>
														</div>
														<div class="card-snippet-wrap">
															Start: <%=sdf.format(activeOnlineAuction.getStart_date_time()) %>
														</div>
														<div class="card-snippet-wrap">
															Closing: <%=sdf.format(activeOnlineAuction.getEnd_date_time()) %>
														</div>
															
														<div class="card-action-btns">
															<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeOnlineAuction.getId()%>" class="btn btn-sm btn-warning">View Auction</a>
														</div>
													</div>
												</div>
											</div>
											<% } %>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane" id="negotiated-bids-tab">
										<div id="negotiated-bidding-slider" class="owl-carousel featured-product-carousel">
											<%for(Auction activeNegotiatedAuction : activeNegotiatedAuctionList) {%>
											<div class="owl-slide">
												<div class="hmr-card-wrap">
													<div class="card-image-wrap">
														<div class="image feature-fade-in owl-lazy" data-src="image?id=<%=activeNegotiatedAuction.getAuction_id()%>&t=at"></div>
													</div>
													<div class="card-body-wrap">
														<h3 class="card-title">
															<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeNegotiatedAuction.getId()%>">
																<%=activeNegotiatedAuction.getAuction_name()%>
															</a>
														</h3>
														<div class="card-snippet-wrap">
															<%=activeNegotiatedAuction.getLocation()%>
														</div>
														<div class="card-snippet-wrap">
															Start: <%=sdf.format(activeNegotiatedAuction.getStart_date_time()) %>
														</div>
														<div class="card-snippet-wrap">
															Closing: <%=sdf.format(activeNegotiatedAuction.getEnd_date_time()) %>
														</div>
														<div class="card-action-btns">
															<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeNegotiatedAuction.getId()%>" class="btn btn-sm btn-warning">View Auction</a>
														</div>
													</div>
												</div>
											</div>
											<% } %>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane" id="negotiated-bids-tab">
										<div id="negotiated-bidding-slider" class="owl-carousel featured-product-carousel">
											<%for(Auction activeLiveAuction : activeLiveAuctionList) {%>
											<div class="owl-slide">
												<div class="hmr-card-wrap">
													<div class="card-image-wrap">
														<div class="image feature-fade-in owl-lazy" data-src="image?id=<%=activeLiveAuction.getAuction_id()%>&t=at"></div>
													</div>
													<div class="card-body-wrap">
														<h3 class="card-title">
															<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeLiveAuction.getId()%>">
																<%=activeLiveAuction.getAuction_name()%>
															</a>
														</h3>
														<div class="card-snippet-wrap">
															<%=activeLiveAuction.getLocation()%>
														</div>
														<div class="card-snippet-wrap">
															Start: <%=sdf.format(activeLiveAuction.getStart_date_time()) %>
														</div>
														<div class="card-snippet-wrap">
															Closing: <%=sdf.format(activeLiveAuction.getEnd_date_time()) %>
														</div>
														<div class="card-action-btns">
															<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeLiveAuction.getId()%>" class="btn btn-sm btn-warning">View Auction</a>
														</div>
													</div>
												</div>
											</div>
											<% } %>
										</div>
									</div>
								</div>
							</div>

						</div>

					</div>

				</section>
			</div>
		</div>
	</div>
</section>
<div class="clearfix top50"></div>

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
													<a href="login.html">My Account</a>
												</li>
												<li>
													<a href="cart">My Basket</a>
												</li>
												<li>
													<a href="checkout">Checkout</a>
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
</body>
</html>

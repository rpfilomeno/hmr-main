<%@ page import="bizoncloudone.com.bean.UserLogin"
		 import="java.util.List"
		 import="java.util.ArrayList"
		 import="javax.servlet.RequestDispatcher" %>
<%
System.out.println("PAGE hmr-header.jsp");

String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME");
String COMPANY_NAME_ACRONYM = (String)request.getSession().getAttribute("COMPANY_NAME_ACRONYM");

String firstName = (String)request.getSession().getAttribute("firstName");
String lastName = (String)request.getSession().getAttribute("lastName");
String fullName = (String)request.getSession().getAttribute("fullName");
String userId = (String)request.getSession().getAttribute("userId");

Integer user_id = request.getSession().getAttribute("user-id")!=null ? (Integer)request.getSession().getAttribute("user-id") : null;
Integer user_role_id = request.getSession().getAttribute("user-role-id")!=null ? (Integer)request.getSession().getAttribute("user-role-id") : 0;

System.out.println("fullName : "+fullName);
%>

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

    <!-- Header top bar -->
    <div class="top-bar">
        <div class="container">
        	<!-- 
            <div class="top-bar-left">
                <ul class="list-inline">
                    <li class="icon-user"><a href="login.html"><img src="assets/img/icon-1.png" alt=""/> <span>Login</span></a></li>
                    <li class="icon-form"><a href="login.html"><img src="assets/img/icon-2.png" alt=""/> <span>Not a Member? <span class="colored">Sign Up</span></span></a></li>
                    <li><a href="mailto:support@yourdomain.com"><i class="fa fa-envelope"></i> <span>support@yourdomain.com</span></a></li>
                </ul>
            </div>
            -->
            <!-- 
            <div class="top-bar-right">
                <ul class="list-inline">
                    <li class="hidden-xs"><a href="about.html">About</a></li>
                    <li class="hidden-xs"><a href="blog.html">Blog</a></li>
                    <li class="hidden-xs"><a href="contact.html">Contact</a></li>
                    <li class="hidden-xs"><a href="faq.html">FAQ</a></li>
                    <li class="hidden-xs"><a href="wishlist.html">My Wishlist</a></li>
                    <li class="dropdown currency">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"> EURO<i class="fa fa-angle-down"></i></a>
                        <ul role="menu" class="dropdown-menu">
                            <li><a href="#"> EURO</a></li>
                            <li><a href="#"> EURO</a></li>
                            <li><a href="#"> EURO</a></li>
                        </ul>
                    </li>
                    <li class="dropdown flags">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="assets/img/flag.gif" alt=""/> Eng<i class="fa fa-angle-down"></i></a>
                        <ul role="menu" class="dropdown-menu">
                            <li><a href="#"><img src="assets/img/flag.gif" alt=""/> Eng</a></li>
                            <li><a href="#"><img src="assets/img/flag.gif" alt=""/> Eng</a></li>
                            <li><a href="#"><img src="assets/img/flag.gif" alt=""/> Eng</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            -->
            <div class="top-bar-right">
                <ul class="list-inline">
                	<% if(fullName!=null && !"null".equals(fullName)){%>
                    <li class="icon-user"><a href="#"><img src="assets/img/icon-1.png" alt=""/> <span><%=fullName%></span></a></li>
                	<% } else { %>
                	<li class="icon-user"><a href="bid?mngr=get&a=login"><img src="assets/img/icon-1.png" alt=""/> <span>Login</span></a></li>
                	<li class="icon-user"><a href="bid?mngr=get&a=registration"><img src="assets/img/icon-2.png" alt=""/> <span>Not a Member? Sign Up</span></a></li>
                	<% } %>
                    <li class="icon-user"><a href="mailto:auction@hmrphils.com"><img src="assets/img/icon-2.png" alt=""/> <span>auction@hmrphils.com</span></a></li>
                	<% if(fullName!=null && !"null".equals(fullName)){%>
                    <li class="icon-user"><a href="bid?mngr=get&a=logout&uid=<%=userId%>"><img src="assets/img/icon-4.png" alt=""/> <span>Logout</span></a></li>
                	<% } %>
                </ul>
            </div>
            
        </div>
    </div>
    <!-- /Header top bar -->

    <!-- HEADER -->
    <header class="header fixed header-logo-left">
        <div class="header-wrapper" style="background-color: #1A3664">
            <div class="container">

                <!-- Logo -->
                <div class="logo">
                    <a href="index.html"><img src="hmr/images/logo_left.jpg" alt="HMR"/></a>
                </div>
                <!-- /Logo -->

                <!-- Header search -->
                <div class="header-search">
                    <input class="form-control" type="text" placeholder="What are you looking?"/>
                    <!-- 
                    <select
                            class="selectpicker header-search-select" data-live-search="true"
                            data-toggle="tooltip" title="Select">
                        <option>All Categories </option>
                        <option>Categories </option>
                        <option>Categories </option>
                    </select>
                    -->
                    <button><i class="fa fa-search"></i></button>
                </div>
                <!-- /Header search -->
				
                <!-- Header shopping cart -->
                <div class="header-cart">
                    <div class="cart-wrapper">
                    <%-- 
                        <a href="wishlist.html" class="btn btn-theme-transparent hidden-xs hidden-sm"><i class="fa fa-heart"></i></a>
                        <a href="compare-products.html" class="btn btn-theme-transparent hidden-xs hidden-sm"><i class="fa fa-exchange"></i></a>
                        <a href="#" class="btn btn-theme-transparent" data-toggle="modal" data-target="#popup-cart"><i class="fa fa-shopping-cart"></i> <span class="hidden-xs"> 0 item(s) - $0.00 </span> <i class="fa fa-angle-down"></i></a>
                        --%>
                        <!-- Mobile menu toggle button -->
                        <a href="#" class="menu-toggle btn btn-theme-transparent" style="background-color: white"><i class="fa fa-bars"></i></a>
                        <!-- /Mobile menu toggle button -->
                    </div>
                </div>
                <!-- Header shopping cart -->
				
				<div class="header-cart" style="padding:0px; margin-left: 0px; padding-left: 0px">
	                <div class="logo">
	                	<div class="cart-wrapper">&nbsp;
	                	</div> 
	                	<%-- 
	                    <div class="cart-wrapper" style="font-size: 10px; padding:0px; margin-left: 0px; padding-left: 0px">HMR Auction Services, Inc. Sucat, Muntinlupa Main Office</div>
	                    <div class="cart-wrapper" style="font-size: 10px; padding:0px; margin-left: 0px; padding-left: 0px">KM. 21, East Service Rd., South Superhighway, Sucat, </div>
	                    <div class="cart-wrapper" style="font-size: 10px; padding:0px; margin-left: 0px; padding-left: 0px">Muntinlupa City - (02)548-6962</div> 
	                    --%>
	                </div>
				</div>

            </div>
        </div>
        <div class="navigation-wrapper" >
            <div class="container">
                <!-- Navigation -->
                <nav class="navigation closed clearfix">
                    <a href="#" class="menu-toggle-close btn"><i class="fa fa-times"></i></a>
                    <ul class="nav sf-menu">
                    	
                    	
                        <li><a href="bid?mngr=get&a=home">Home</a></li>
                        <%if(user_role_id.equals(1) || user_role_id.equals(4)){ %>
                    	<%if(user_role_id.equals(1)){ %>
                    	<li><a href="#">Dashboard</a></li>
                        <li><a href="#">Administration</a>
                            <ul>

                                <li><a href="bid?mngr=auction-manager&a=auctionList&uid=<%=userId%>">Auctions</a></li>
                                <li><a href="bid?mngr=lot-manager&a=lotList&uid=<%=userId%>">Lots</a></li>
                                <li><a href="bid?mngr=item-manager&a=itemList&uid=<%=userId%>">Items</a></li>
                                <li><a href="bid?mngr=bidding-transaction-manager&a=biddingTransactionList&uid=<%=userId%>">Bidding Transactions</a></li>
                                <li><a href="bid?mngr=upload-auction-manager&a=uploadAuctionSearch&uid=<%=userId%>">Upload Auctions</a></li>
                                <li><a href="bid?mngr=category-level-manager&a=categoryLevelList&uid=<%=userId%>">Category Level</a></li>
                                <li><a href="bid?mngr=auction-user-manager&a=auctionUserList&uid=<%=userId%>">Auction Users</a></li>
                                <li><a href="bid?mngr=user-manager&a=userList&uid=<%=userId%>">Users</a></li>
                                <li><a href="#">List Of Values</a></li>
                                <li><a href="#">Cases</a></li>
                                <li><a href="#">Auction User Bidding Max</a></li>
                                <!-- 
                                <li><a href="bid?mngr=user-manager&a=userList&uid=</ %=userId%>">Users</a></li>
                                <li><a href="bid?mngr=lov-manager&a=lovList">List Of Values</a></li>
                                <li><a href="bid?mngr=auction-manager&a=auctionList">Auctions</a></li>
                                <li><a href="bid?mngr=lot-manager&a=lotList">Lots</a></li>
                                <li><a href="bid?mngr=item-manager&a=itemList">Items</a></li>
                                 -->
                            </ul>
                        </li>
                        <%} %>
                        <%if(user_role_id.equals(1) || user_role_id.equals(4)){ %>
                        <li><a href="#">Marketing</a>
                            <ul>
                            	<li><a href="bid?mngr=auction-marketing-manager&a=auctionMarketingList">Auctions</a></li>
                                <li><a href="bid?mngr=site-content-manager&a=siteContentList">Site Contents</a></li>
                            </ul>
                        </li>
                        <li><a href="index.html">Reports</a>
                            <!-- <ul>
                                <li><a href="index.html">Users</a></li>
                                <li><a href="index-2.html">List Of Values</a></li>
                                <li><a href="index-3.html">Auctions</a></li>
                                <li><a href="index-4.html">Lots</a></li>
                                <li><a href="index-5.html">Items</a></li>
                            </ul> -->
                        </li>
                    	<%} %>
                    	<%} %>
                    	
                    	<%if(user_role_id.equals(2)){%>
                    	<li><a href="bid?mngr=auction-manager&a=privateAuctionList&uid=<%=userId%>">My Private Biddings</a></li>
                    	<li><a href="#">My Bids</a></li>
                    	<%} %>
                    	<li><a href="#">Online Biddings</a></li>
                    	<li><a href="#">Negotiated Bids</a></li>
                    	<li><a href="#">Live Auctions</a></li>
                    	<!-- <li><a href="#">Wines</a></li> -->
                    	<!-- <li><a href="#">Services</a></li> -->
                    	<!-- <li><a href="#">Gallery</a></li> -->
                    	<!-- 
                        <li class="active"><a href="index.html">Homepage</a>
                            <ul>
                                <li><a href="index.html">Homepage 1</a></li>
                                <li><a href="index-2.html">Homepage 2</a></li>
                                <li><a href="index-3.html">Homepage 3</a></li>
                                <li><a href="index-4.html">Homepage 4</a></li>
                                <li><a href="index-5.html">Homepage 5</a></li>
                                <li><a href="index-6.html">Homepage 6</a></li>
                                <li><a href="index-7.html">Homepage 7</a></li>
                                <li><a href="index-8.html">Homepage 8</a></li>
                                <li><a href="index-9.html">Homepage 9</a></li>
                            </ul>
                        </li>
                        -->
                        <%---
                        <li><a href="category.html">Shop</a>
                            <ul>
                                <li><a href="category.html">Shop Sidebar Left</a></li>
                                <li><a href="category-right.html">Shop Sidebar Right</a></li>
                                <li><a href="category-list.html">Shop List View</a></li>
                                <li><a href="product-details.html">Product Page</a></li>
                            </ul>
                        </li>
                        
                        <li><a href="#">Blog</a>
                            <ul>
                                <li><a href="blog.html">Blog Sidebar Left </a></li>
                                <li><a href="blog-right.html">Blog Sidebar Right</a></li>
                                <li><a href="blog-post.html">Blog Single Post</a></li>
                            </ul>
                        </li>
                        <li><a href="portfolio.html">Portfolio</a>
                            <ul>
                                <li><a href="portfolio.html">Portfolio 3 columns</a></li>
                                <li><a href="portfolio-4col.html">Portfolio 4 columns</a></li>
                                <li><a href="portfolio-alt.html">Portfolio Alternate</a></li>
                                <li><a href="portfolio-single.html">Portfolio Single</a></li>
                            </ul>
                        </li>
                        <li class="megamenu"><a href="#">Features</a>
                            <ul>
                                <li class="row">
                                    <div class="col-md-2">
                                        <h4 class="block-title"><span>Womens</span></h4>
                                        <ul>
                                            <li><a href="#">Dresses</a></li>
                                            <li><a href="#">Rompers & Jumpsuits</a></li>
                                            <li><a href="#">Bodysuits</a></li>
                                            <li><a href="#">Shirts & Blouses</a></li>
                                            <li><a href="#">Coats & Jackets</a></li>
                                            <li><a href="#">Blazers</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-md-2">
                                        <h4 class="block-title"><span>Mens</span></h4>
                                        <ul>
                                            <li><a href="#">T-Shirts & Vests</a></li>
                                            <li><a href="#">Sweaters & Cardigans</a></li>
                                            <li><a href="#">Hoodies & Sweats</a></li>
                                            <li><a href="#">Coats & Jackets</a></li>
                                            <li><a href="#">Shirts</a></li>
                                            <li><a href="#">Shorts</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-md-2">
                                        <h4 class="block-title"><span>Pages</span></h4>
                                        <ul>
                                            <li><a href="shortcodes.html"><strong>Shortcodes</strong></a></li>
                                            <li><a href="typography.html"><strong>Typography</strong></a></li>
                                            <li><a href="coming-soon.html"><strong>Coming soon</strong></a></li>
                                            <li><a href="error-page.html"><strong>404 Page</strong></a></li>
                                            <li><a href="about.html"><strong>About</strong></a></li>
                                            <li><a href="login.html"><strong>Login</strong></a></li>
                                        </ul>
                                    </div>
                                    <div class="col-md-3">
                                        <h4 class="block-title"><span>Header styles</span></h4>
                                        <ul>
                                            <li><a href="index-style-1.html"><strong>Header style 1</strong></a></li>
                                            <li><a href="index-style-2.html"><strong>Header style 2</strong></a></li>
                                            <li><a href="index-style-3.html"><strong>Header style 3</strong></a></li>
                                            <li><a href="index-style-4.html"><strong>Header style 4</strong></a></li>
                                            <li><a href="index-style-5.html"><strong>Header style 5</strong></a></li>
                                        </ul>
                                    </div>
                                    
                                    <div class="col-md-3">
                                        <div class="product-list">
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
                                </li>
                            </ul>
                        </li>
                        --%>
                        <!-- 
                        <li><a href="category.html">Men</a></li>
                        <li><a href="category.html">Women</a></li>
                        <li><a href="category.html">Kids</a></li>
                        <li><a href="category.html">New</a></li>
                        <li class="sale"><a href="category.html">Sale</a></li>
                        <li><a href="contact.html">Contact</a></li>
                        -->
                    </ul>
                </nav>
                <!-- /Navigation -->
            </div>
        </div>
    </header>
    <!-- /HEADER -->


      <% System.out.println("End main-header : "); %>
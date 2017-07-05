<!DOCTYPE html>
<%@ page import="hmr.com.bean.Auction"
		 import="java.util.List"  
		 import="java.text.SimpleDateFormat"
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
    <title><%=COMPANY_NAME%></title>

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
    <link href="assets/plugins/countdown/jquery.countdown.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="assets/css/theme.css" rel="stylesheet">
<link href="assets/css/theme-hmr.css" rel="stylesheet" id="theme-config-link">

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
        <section class="page-section no-padding-bottom">
            <div class="container">

                <div class="row main-slider-row">

                    <div class="col-md-9 slider">
                    
                    <div id="msgDiv"></div>
                    
                        <div class="main-slider">
                            <div class="owl-carousel" id="main-slider">
                            
                                <!-- Slide 1 -->
                                <div class="item slide1">
                                    <img class="slide-img" src="hmr/images/slider/slide-1-sub.jpg" alt=""/>
                                    <!--  <div class="caption">
                                        <div class="container">
                                            <div class="div-table">
                                                <div class="div-cell">
                                                
                                                    <div class="caption-content">
                                                        <h2 class="caption-title">The Biggest</h2>
                                                        <h3 class="caption-subtitle">Sale</h3>
                                                        <p class="caption-text">
                                                            <a class="btn btn-theme" href="#">Bid Now</a>
                                                        </p>
                                                    </div> 
                                                </div>
                                            </div>
                                        </div>
                                    </div> -->
                                </div>
                                <!-- /Slide 1 -->

                                <!-- Slide 2 -->
                                <div class="item slide2">
                                    <img class="slide-img" src="hmr/images/slider/slide-2-sub.jpg" alt=""/>
                                    <!-- 
                                    <div class="caption">
                                        <div class="container">
                                            <div class="div-table">
                                            
                                                <div class="div-cell">
                                                    <div class="caption-content">
                                                        <h2 class="caption-title">New Arrivals On Sale</h2>
                                                        <h3 class="caption-subtitle"><span>Summer Collection</span></h3>
                                                        <div class="price">
                                                            <span>$</span><ins>49</ins>
                                                            <span>$</span><del>86</del>
                                                        </div>
                                                        <p class="caption-text">
                                                            <a class="btn btn-theme" href="#">Shop this item Now</a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> -->
                                </div>
                                <!-- /Slide 2 -->

                                <!-- Slide 3 -->
                                <div class="item slide3">
                                    <img class="slide-img" src="hmr/images/slider/slide-3-sub.jpg" alt=""/>
                                    <!-- 
                                    <div class="caption">
                                        <div class="container">
                                            <div class="div-table">
                                                <div class="div-cell">
                                                    <div class="caption-content">
                                                        <h2 class="caption-title">New Arrivals On Sale</h2>
                                                        <h3 class="caption-subtitle"><span>Summer Collection</span></h3>
                                                        <p class="caption-text">
                                                            <a class="btn btn-theme" href="#">Shop this item Now</a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> -->
                                </div>
                            
                            
<%-- 
                                <!-- Slide 1 -->
                                <div class="item slide1">
                                    <img class="slide-img" src="assets/img/preview/slider/slide-3-sub.jpg" alt=""/>
                                    <div class="caption">
                                        <div class="container">
                                            <div class="div-table">
                                                <div class="div-cell">
                                                    <div class="caption-content">
                                                        <h2 class="caption-title">The Biggest</h2>
                                                        <h3 class="caption-subtitle">Sale</h3>
                                                        <p class="caption-text">
                                                            <a class="btn btn-theme" href="#">Bid Now</a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Slide 1 -->

                                <!-- Slide 2 -->
                                <div class="item slide2 alt">
                                    <img class="slide-img" src="assets/img/preview/slider/slide-3-sub.jpg" alt=""/>
                                    <div class="caption">
                                        <div class="container">
                                            <div class="div-table">
                                                <div class="div-cell">
                                                    <div class="caption-content">
                                                        <h2 class="caption-title">New Arrivals On Sale</h2>
                                                        <h3 class="caption-subtitle"><span>Summer Collection</span></h3>
                                                        <div class="price">
                                                            <span>$</span><ins>49</ins>
                                                            <span>$</span><del>86</del>
                                                        </div>
                                                        <p class="caption-text">
                                                            <a class="btn btn-theme" href="#">Shop this item Now</a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Slide 2 -->

                                <!-- Slide 3 -->
                                <div class="item slide3 dark">
                                    <img class="slide-img" src="assets/img/preview/slider/slide-3a-sub.jpg" alt=""/>
                                    <div class="caption">
                                        <div class="container">
                                            <div class="div-table">
                                                <div class="div-cell">
                                                    <div class="caption-content">
                                                        <h2 class="caption-title">New Arrivals On Sale</h2>
                                                        <h3 class="caption-subtitle"><span>Summer Collection</span></h3>
                                                        <p class="caption-text">
                                                            <a class="btn btn-theme" href="#">Shop this item Now</a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Slide 3 -->
--%>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 sidebar">
                        <!-- widget shop categories -->
						<jsp:include page="hmr-category.jsp" />
                        <!-- /widget shop categories -->
                    </div>

                </div>

            </div>
        </section>
        <!-- /PAGE -->



        <!-- PAGE -->
        <section class="page-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="product-list">
                            <a class="btn btn-theme btn-title-more" href="#">See All</a>
                            <h4 class="block-title"><span>Online Biddings</span></h4>
                            <%for(Auction activeOnlineAuction : activeOnlineAuctionList) {%>

                            <div class="media">
                               <a class="pull-left media-link" href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeOnlineAuction.getId()%>">
                                    <!-- <img class="media-object" src="image?id=< / %=activeOnlineAuction.getId()%>&s=s" alt=""> -->
                                    <img  class="media-object" style="height: <%=imgHeight%>px; size: <%=imgSize%>px;" src="image?id=<%=activeOnlineAuction.getAuction_id()%>&t=at" alt="">
                                    <!-- <i class="fa fa-eye"></i> -->
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeOnlineAuction.getId()%>" style="font-size: 14px; font-weight: bold; color: red;"><%=activeOnlineAuction.getAuction_name()%></a></h4>
                                    <%--
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div> --%>
                                    <div><label><%=activeOnlineAuction.getLocation()%></label></div>
                                    <%-- <div><label>Closing : <% /=sdf.format(activeOnlineAuction.getEnd_date_time()) %></label></div> --%>
                                    <div><i class="fa fa-clock-o"></i> <label id="cdTimer-<%=activeOnlineAuction.getId()%>"></label></div>
                                    <script>setCountDownTimer('cdTimer-<%=activeOnlineAuction.getId()%>', '<%=activeOnlineAuction.getEnd_date_time()%>')</script>
                                    <%-- <div class="price"><ins><%=activeOnlineAuction.getNo_of_items()%></ins></div> --%>
                                </div>
                            </div>
                            <%} %>

                        </div>
                    </div>
					<div class="col-md-4">
                        <div class="product-list">
                            <a class="btn btn-theme btn-title-more" href="#">See All</a>
                            <h4 class="block-title"><span>Negotiated Bids</span></h4>
                            <%for(Auction activeNegotiatedAuction : activeNegotiatedAuctionList) {%>

                            <div class="media">
                               <a class="pull-left media-link" href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeNegotiatedAuction.getId()%>">
                                    <!-- <img class="media-object" src="image?id=< / %=activeNegotiatedAuction.getId()%>&s=s" alt=""> -->
                                    <img  class="media-object" style="height: <%=imgHeight%>px; size: <%=imgSize%>px;" src="image?id=<%=activeNegotiatedAuction.getAuction_id()%>&t=at" alt="">
                                    
                                    
                                    <!-- <i class="fa fa-eye"></i> -->
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="#" style="font-size: 14px; font-weight: bold; color: red;"><%=activeNegotiatedAuction.getAuction_name()%></a></h4>
                                    <%--
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div> --%>
                                    <div><label><%=activeNegotiatedAuction.getLocation()%></label></div>
                                    <%-- <div><label>Closing : <% /=sdf.format(activeOnlineAuction.getEnd_date_time()) %></label></div> --%>
                                    <div><i class="fa fa-clock-o"></i> <label id="cdTimer-<%=activeNegotiatedAuction.getId()%>"></label></div>
                                    <script>setCountDownTimer('cdTimer-<%=activeNegotiatedAuction.getId()%>', '<%=activeNegotiatedAuction.getEnd_date_time()%>')</script>
                                    <%-- <div class="price"><ins><%=activeOnlineAuction.getNo_of_items()%></ins></div> --%>
                                </div>
                            </div>
                            
                            <%} %>

                        </div>
                        
                        
                        
                    </div>
					<div class="col-md-4">
                        <div class="product-list">
                            <a class="btn btn-theme btn-title-more" href="#">See All</a>
                            <h4 class="block-title"><span>Live Auctions</span></h4>
                            <%for(Auction activeNegotiatedAuction : activeNegotiatedAuctionList) {%>

                            <div class="media">
                               <a class="pull-left media-link" href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeNegotiatedAuction.getId()%>">
                                    <!-- <img class="media-object" src="image?id=< / %=activeNegotiatedAuction.getId()%>&s=s" alt=""> -->
                                    <img  class="media-object" style="height: <%=imgHeight%>px; size: <%=imgSize%>px;" src="image?id=<%=activeNegotiatedAuction.getAuction_id()%>&t=at" alt="">
                                    
                                    <!-- <i class="fa fa-eye"></i> -->
                                </a>
                                <div class="media-body">	
                                    <h4 class="media-heading"><a href="#" style="font-size: 14px; font-weight: bold; color: red;"><%=activeNegotiatedAuction.getAuction_name()%></a></h4>
                                    <%--
                                    <div class="rating">
                                        <span class="star"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span><!--
                                        --><span class="star active"></span>
                                    </div> --%>
                                    <div><label><%=activeNegotiatedAuction.getLocation()%></label></div>
                                    <%-- <div><label>Closing : <% /=sdf.format(activeOnlineAuction.getEnd_date_time()) %></label></div> --%>
                                    <div><i class="fa fa-clock-o"></i> <label id="cdTimerla-<%=activeNegotiatedAuction.getId()%>"></label></div>
                                    <script>setCountDownTimer('cdTimerla-<%=activeNegotiatedAuction.getId()%>', '<%=activeNegotiatedAuction.getEnd_date_time()%>')</script>
                                    <%-- <div class="price"><ins><%=activeOnlineAuction.getNo_of_items()%></ins></div> --%>
                                </div>
                            </div>
                            
                            <%} %>

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
        <!-- PAGE -->
        <section class="page-section">
            <div class="container">
                <div class="message-box">
                    <div class="message-box-inner">
                        <h2>Wines</h2>
                    </div>
                </div>
            </div>
        </section>
        <!-- /PAGE -->




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
        <!-- PAGE -->
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

    <!-- FOOTER -->
	<jsp:include page="hmr-footer.jsp" />
    <!-- /FOOTER -->

    <div id="to-top" class="to-top" style="background-color: #93bcff"><i class="fa fa-angle-up"></i></div>

</div>
<!-- /WRAPPER -->

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

<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>

<!-- JS Page Level -->
<script src="assets/plugins/countdown/jquery.plugin.min.js"></script>
<script src="assets/plugins/countdown/jquery.countdown.min.js"></script>
<script src="assets/js/theme.js"></script>


<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/plugins/jquery.cookie.js"></script>
<!-- <script src="assets/js/theme-config.js"></script> -->
<!--<![endif]-->

<script>

function onLoadPage(){
	
}


$(function () {
    $("#example1").DataTable({
      	"order": [[ 4, "desc" ]]
    });
  });
</script>
<script>
$(document).ready(function(){
    $('#myTable').dataTable();
});

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

<form action="bid" name="frm" action="post">
   <input type="hidden" name="manager" id="manager" value=""/>
   <input type="hidden" name="action" id="action" value=""/>
   <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
   <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
</form>
</body>
</html>
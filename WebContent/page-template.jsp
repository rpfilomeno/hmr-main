<!DOCTYPE html>
<%@ page import="hmr.com.bean.Auction" import="hmr.com.bean.Lot"
	import="hmr.com.bean.Image" import="hmr.com.bean.Lov"
	import="hmr.com.bean.Item" import="java.util.List"
	import="java.math.BigDecimal" import="java.util.HashMap"
	import="java.text.DecimalFormat" import="java.text.SimpleDateFormat"%>
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
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="shortcut icon" href="ico/hmr-favicon.ico">

<!-- DataTables -->
<link rel="stylesheet"
	href="plugins/datatables/dataTables.bootstrap.css">


<!-- CSS Global -->
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="assets/plugins/bootstrap-select/css/bootstrap-select.min.css"
	rel="stylesheet">
<link href="assets/plugins/fontawesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="assets/plugins/prettyphoto/css/prettyPhoto.css"
	rel="stylesheet">
<link href="assets/plugins/owl-carousel2/assets/owl.carousel.min.css"
	rel="stylesheet">
<link
	href="assets/plugins/owl-carousel2/assets/owl.theme.default.min.css"
	rel="stylesheet">
<link href="assets/plugins/animate/animate.min.css" rel="stylesheet">
<link href="assets/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">

<!-- Theme CSS -->
<link href="assets/css/theme.css" rel="stylesheet">
<link href="assets/css/theme-hmr.css" rel="stylesheet"
	id="theme-config-link">
<link
	href="assets/plugins/jquery-ui/themes/smoothness/jquery-ui.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="assets/plugins/gridder/jquery.gridder.min.css" />

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
			<section class="page-section">
			<div class="container">
			<div class="row">
				<div class="col-md-7">
					<div class="media" style="height: 210px;">
						<a class="pull-left media-link" href="#"> <img
							class="media-object" style="height: 200px; size: 200px;"
							src="image?id=<%=lot.getId()%>&t=lt" alt=""> <!-- <i class="fa fa-eye"></i> -->
						</a>
						<div class="media-body">
							<h4 class="media-heading">
								<a href="#"
									style="font-size: 14px; font-weight: bold; color: red"><%=lot.getLot_desc()%></a>
							</h4>
							<%
								if (lot.getIs_available_lot() > 0) {
							%>
							<div>
								<label>UNIT QUANTITY : <%=lot.getUnit_qty()%></label>
							</div>
							<%
								}
							%>
							<div>
								<label>HIGHEST BID : <%=df.format(lot.getAmount_bid())%>
									<%=currency%></label>
							</div>
							<div>
								<label>ASKING BID : <%=df.format(lot.getAmount_bid_next())%>
									<%=currency%></label>
							</div>
							<div>
								<label>BIDS : <%=lot.getBid_count()%></label>
							</div>
							<div>
								<i class="fa fa-clock-o"></i> <label
									id="cdTimer-<%=lot.getId()%>"></label>
							</div>
						</div>
					</div>
					<script>setCountDownTimer('cdTimer-<%=lot.getId()%>', '<%=lot.getEnd_date_time()%>
						')
					</script>
				</div>

				<div class="col-md-3">


					<div class="media" style="height: 210px;">

						<div class="media">
							<%
								if (user_id != null && user_role_id > 0) {
							%>
							<%
								if (lot.getIs_bid() == 1) {
							%>

							<%
								if (lot.getIs_available_lot() > 0) {
							%>
							<%
								Integer i = lot.getUnit_qty();
							%>
							<div class="form-group">
								<label>Quantity:</label> <select class="form-control"
									id="qty_<%=lot.getId()%>" name="qty_<%=lot.getId()%>">
									<%
										while (i > 0) {
									%>
									<option value="<%=i%>"><%=i%> unit<%
										if (i > 1) {
									%>s<%
										}
									%>
									</option>
									<%
										i = i - 1;
									%>
									<%
										}
									%>
								</select>
							</div>
							<%
								}
							%>

							<a class="btn btn-theme btn-block" href="#"
								onclick="submitPage('BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>')">BID
								<%=df.format(lot.getAmount_bid_next())%> <%=currency%>
							</a> <a class="btn btn-theme btn-block" href="#"
								onclick="showMaxBidForm('SET-MAXIMUM-BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>')">SET
								MAXIMUM BID</a>

							<%
								} else {
									}
							%>
							<%
								if (lot.getIs_buy() == 1) {
							%>
							<a class="btn btn-theme btn-block" href="#"
								onclick="submitPage('BUY', '<%=lot.getBuy_price()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>')">BUY
								<%=df.format(lot.getBuy_price())%> <%=currency%></a>
							<%
								} else {
									}
							%>
							<%
								} else if (user_id == null && user_role_id == 0 && (lot.getIs_bid() == 1 || lot.getIs_buy() == 1)) {
							%>
							<a class="btn btn-theme btn-block"
								href="bid?mngr=get&a=registration">REGISTER</a> <a
								class="btn btn-theme btn-block" href="bid?mngr=get&a=login">LOGIN</a>
							<%
								}
							%>
						</div>
					</div>

				</div>
			</div>
			</div>
			</section>
			<!-- /PAGE -->


			<section class="page-section">
				<div class="container">
					<h2 class="section-title">
						<span>Brand &amp; Clients</span>
					</h2>
					<div class="partners-carousel">
						<div class="owl-carousel" id="partners">
							<div>
								<a href="#"><img src="hmr/images/affiliate/alogo1.png"
									alt="" /></a>
							</div>
							<div>
								<a href="#"><img src="hmr/images/affiliate/alogo2.png"
									alt="" /></a>
							</div>
							<div>
								<a href="#"><img src="hmr/images/affiliate/alogo3.png"
									alt="" /></a>
							</div>
							<div>
								<a href="#"><img src="hmr/images/affiliate/alogo4.png"
									alt="" /></a>
							</div>
							<div>
								<a href="#"><img src="hmr/images/affiliate/alogo5.png"
									alt="" /></a>
							</div>
							<div>
								<a href="#"><img src="hmr/images/affiliate/alogo6.png"
									alt="" /></a>
							</div>
							<div>
								<a href="#"><img src="hmr/images/affiliate/alogo7.png"
									alt="" /></a>
							</div>
							<div>
								<a href="#"><img src="hmr/images/affiliate/alogo8.png"
									alt="" /></a>
							</div>

						</div>
					</div>
				</div>
			</section>
			<!-- /PAGE -->



		</div>
		<!-- /CONTENT AREA -->

		<!-- PAGE -->
		<!-- FOOTER -->
		<jsp:include page="hmr-footer.jsp" />
		<!-- /FOOTER -->

		<div id="to-top" class="to-top" style="background-color: #93bcff">
			<i class="fa fa-angle-up"></i>
		</div>

	</div>
	<!-- /WRAPPER -->

	<!-- JS Global -->
	<script src="assets/plugins/jquery/jquery-2.0.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/jquery-ui-1.11.1.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
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
		 
		});
		
	</script>

	<form action="" id="frm" name="frm" method="post">
		<input type="hidden" name="manager" id="manager" value="" /> <input
			type="hidden" name="action" id="action" value="" /> <input
			type="hidden" name="doaction" id="doaction" value="" /> <input
			type="hidden" name="lotId" id="lotId" value="" /> <input
			type="hidden" name="lotId_wip" id="lotId_wip" value="" /> <input
			type="hidden" name="unit_qty" id="unit_qty" value="" /> <input
			type="hidden" name="amount" id="amount" value="" /> <input
			type="hidden" name="userId" id="userId" value="<%=userId%>" /> <input
			type="hidden" name="user-id" id="user-id" value="<%=user_id%>" />
	</form>
</body>
</html>
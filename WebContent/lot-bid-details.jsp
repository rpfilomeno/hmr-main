<!DOCTYPE html>
<%@ page import="hmr.com.bean.Lot"
		 import="hmr.com.bean.Auction"
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
	System.out.println("PAGE lot-bid-details.jsp");

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

    DecimalFormat df = new DecimalFormat("#,###,##0");
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
        <section class="page-section">
            <div class="container">
            	<div class="row ">
            		<div class="col-md-12">
		                <div class="message-box">
		                    <div class="message-box-inner">
		                        <h2><%=lot.getLot_name()%></h2>
		                    </div>
		                </div>
		                
		                <div id="msgDiv"></div>
		             </div>
		             <%--
		             <div class="col-md-12">   
						<div>
		                   <div class="media">
		                       <a href="#">
		                           <img style="height: 200px; size: 200px" src="image?id=<%=auction.getId()%>" alt=""/> 
		                       </a>
		                   </div>
		               </div>
	               </div> --%>
               </div>
            </div>
        </section>
        <!-- /PAGE -->

        <!-- PAGE -->
        <section class="page-section no-padding-bottom">
            <div class="container">

                <div class="row ">
	                    <div class="col-md-12">
	                        <div class="tabs-wrapper content-tabs">
	                            <ul class="nav nav-tabs">
	                                <li class="active"><a href="#auction-description" data-toggle="tab">Details</a></li>
	                                <li><a href="#auction-lots" data-toggle="tab">Items</a></li>
	                                <li><a href="#terms-and-conditions" data-toggle="tab">Terms and Conditions</a></li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane fade in active" id="auction-description">


							        <section class="page-section">
							            <div class="container">
							                <div class="row">
							                	
							                	
							                	
							                	
							                    <div class="col-md-7">
							                            
							
							                            <div class="media" style="height: 210px;">
							                               <a class="pull-left media-link" href="#" >
							                                    <img  class="media-object" style="height: 200px; size: 200px;" src="image?id=<%=lot.getAuction_id()%>&t=a" alt="">
							                                    
							                                    <!-- <i class="fa fa-eye"></i> -->
							                                </a>
							                                <div class="media-body">
																<h4 class="media-heading"><a href="#" style="font-size: 14px; font-weight: bold; color: red"><%=lot.getLot_desc()%></a></h4>
							                                	<%  if( lot.getIs_available_lot() > 0) { %>
							                                	<div><label>UNIT QUANTITY : <%=lot.getUnit_qty()%></label></div>
							                                	<% } %>
							                                	<div><label>HIGHEST BID : <%=df.format(lot.getAmount_bid())%> <%=currency%></label></div>
							                                	<div><label>ASKING BID : <%=df.format(lot.getAmount_bid_next())%> <%=currency%></label></div>
							                                    <div><label>BIDS : <%=lot.getBid_count()%></label></div>
																<div><i class="fa fa-clock-o"></i> <label id="cdTimer-<%=lot.getId()%>"></label></div>
							                                </div>
							                            </div>
							                            <script>setCountDownTimer('cdTimer-<%=lot.getId()%>', '<%=lot.getEnd_date_time()%>')</script>
							                    </div>
							                    
							                    <div class="col-md-3">
							                            
							
							                            <div class="media" style="height: 210px;">
							                         
							                                <div class="media">
                                								<% if(user_id != null && user_role_id > 0){ %>
                                									<% if(lot.getIs_bid() == 1){ %>
                                   	 							
                                   	 							<%  if( lot.getIs_available_lot() > 0) { %>
                                   	 								<% Integer i = lot.getUnit_qty(); %>
                                   	 								<div class="form-group">
                                   	 								<label>Quantity:</label>
                                   	 								<select class="form-control" id="qty_<%=lot.getId()%>" name="qty_<%=lot.getId()%>">
                                   	 								<% while(i > 0) { %>
                                   	 									<option value="<%=i%>"><%=i%> unit<% if(i>1){ %>s<%}%></option>
                                   	 									<% i = i - 1; %>
                                   	 								<% } %>
                                   	 								</select>
                                   	 								</div>
                                   	 							<% } %>
                                   	 							
                                   	 							<a class="btn btn-theme btn-block" href="#" onclick="submitPage('BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>')">BID <%=df.format(lot.getAmount_bid_next())%> <%=currency%> </a>
                                   	 							<a class="btn btn-theme btn-block" href="#" onclick="submitPage('SET-MAXIMUM-BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_no()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>')">SET MAXIMUM BID</a>
                                   	 								<% }else {  } %>
                                   	 								<% if(lot.getIs_buy() == 1){ %>
                                								<a class="btn btn-theme btn-block" href="#" onclick="submitPage('BUY', '<%=lot.getBuy_price()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>')">BUY <%=df.format(lot.getBuy_price())%> <%=currency%></a>
                                									<% }else{ } %>
                                   	 							<% }else if(user_id == null && user_role_id == 0 && (lot.getIs_bid() == 1 || lot.getIs_buy() == 1) ){ %>
																	<a class="btn btn-theme btn-block" href="bid?mngr=get&a=registration">REGISTER</a>
																	<a class="btn btn-theme btn-block" href="bid?mngr=get&a=login">LOGIN</a>
																<% } %>
							                                </div>
							                            </div>
				
							                    </div>
							                </div>
						                    
							            </div>
							        </section>

	                                </div>
	                                <div class="tab-pane fade" id="auction-lots">

							        <!-- PAGE -->
							        <section class="page-section">
							            <div class="container">
							                <div class="row">
							                	<%for(Item i : items) {%>

							                    <div class="col-md-7">

							                            <div class="media" style="height: 210px;">
							                               <a class="pull-left media-link" href="#">
							                                    <img  class="media-object" style="height: 200px; size: 200px;" src="image?id=<%=lot.getId()%>&t=i" alt="">
							                                    
							                                    <!-- <i class="fa fa-eye"></i> -->
							                                </a>
							                                <div class="media-body">
							                                
							                                    <h4 class="media-heading"><a href="#" style="font-size: 14px; font-weight: bold; color: red">#<%=i.getReference_no()%></a></h4>
							                                    <div><label><%=i.getItem_desc()%></label></div>
							                                </div>
							                            </div>

							                    </div>
							                    <%} %>
							                   
							                </div>
							                    
							            </div>
							        </section>
							        <!-- /PAGE -->
	                                </div>
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

    </div>
    <!-- /CONTENT AREA -->

    <!-- PAGE --><!-- FOOTER -->
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

function viewLot(id) {
	$('input[name="manager"]').val("lot-manager");
	$('input[name="action"]').val("lotBidDetails");
	$('input[name="lotId_wip"]').val(id);
	$( "#frm" ).submit();
}

function submitPage(action, value, lot, id, qtyid) {
	var unit_qty = $("#"+qtyid+" :selected").attr('value');
	$('input[name="manager"]').val("bid-manager");
	$('input[name="doaction"]').val(action);
	$('input[name="amount"]').val(value);
	$('input[name="lotId"]').val(lot);
	$('input[name="lotId_wip"]').val(id);
	$('input[name="unit_qty"]').val(unit_qty);
	$( "#frm" ).submit();
}

</script>

<form action="" id="frm" name="frm" method="post">
   <input type="hidden" name="manager" id="manager" value=""/>
   <input type="hidden" name="action" id="action" value=""/>
   <input type="hidden" name="doaction" id="doaction" value=""/>
   <input type="hidden" name="lotId" id="lotId" value=""/>
   <input type="hidden" name="lotId_wip" id="lotId_wip" value=""/>
   <input type="hidden" name="unit_qty" id="unit_qty" value=""/>
   <input type="hidden" name="amount" id="amount" value=""/>
   <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
   <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
</form>
</body>
</html>
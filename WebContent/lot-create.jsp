<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="hmr.com.bean.Auction"
		 import="hmr.com.bean.Lov"
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
		String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";

		String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
		String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
		  
		  
		String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
		String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
		String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : (String)request.getSession().getAttribute("userId");
		//Integer mobileNo = request.getAttribute("mobileNo")!=null ? (Integer)request.getAttribute("mobileNo") : null;
		  
		  
		//IDS
		Integer user_id = request.getAttribute("user-id")!=null ? (Integer)request.getAttribute("user-id") : (Integer)request.getSession().getAttribute("user-id");

		BigDecimal auctionId_wip = request.getAttribute("auctionId_wip")!=null ? (BigDecimal)request.getAttribute("auctionId_wip") : null;
		  
		
		System.out.println("userId : "+userId);
		System.out.println("user_id : "+user_id);
		/*
		List<User> uList =(ArrayList<User>) request.getAttribute("userList");
		System.out.println("ulList "+uList.size());
		*/
		
		ArrayList<Lov> userRoleList = (ArrayList<Lov>) request.getSession().getAttribute("USER-ROLE-LIST");
		ArrayList<Lov> genderList = (ArrayList<Lov>) request.getSession().getAttribute("GENDER-LIST");
		ArrayList<Lov> userStatusList = (ArrayList<Lov>) request.getSession().getAttribute("USER-STATUS-LIST");
		
		
		//ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getSession().getAttribute("USER-COORDINATOR-LIST");
		ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getAttribute("userCoordinatorList");
		ArrayList<User> userBidderList = (ArrayList<User>) request.getAttribute("userBidderList");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			
	%>
    <title><%=COMPANY_NAME%></title>



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
		       <input type="hidden" name="manager" id="manager" value="lot-manager"/>
		       <input type="hidden" name="action" id="action" value=""/>
		       <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
		       <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
		       <input type="hidden" name="auctionId_wip" id="auctionId_wip" value="<%=auctionId_wip%>"/>
		       <input type="hidden" name="lotId_wip" id="lotId_wip" value=""/>
		       
        <section class="page-section color" style="padding:15px;">
        
            <div class="container">
                <div class="row" >

                    <div class="col-sm-12">
                        <h3 class="block-title"><span>Lots  <label>create </label></span></h3>
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
		                <label><b>Auction Id : </b></label>
						<input type="text" class="form-control" placeholder="AUCTION ID" value=""  id="auction_id" name="auction_id" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              
		              <div class="form-group">
		                <label><b>Lot Id : </b></label>
						<input type="text" class="form-control" placeholder="LOT ID" value=""  id="lot_id" name="lot_id" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              
		              <div class="form-group">
		                <label><b>Lot No : </b></label>
						<input type="text" class="form-control" placeholder="LOT NO" value=""  id="lot_no" name="lot_no" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>

		              <div class="form-group">
		                <label><b>Lot Name : </b></label>
						<input type="text" class="form-control" placeholder="LOT NAME" value=""  id="lot_name" name="lot_name" onchange="return trim(this)" autocomplete="off" maxlength="100"/>
		              </div>	


		              <div class="form-group">
		                <label><b>Description : </b></label>
						<input type="text" class="form-control" placeholder="DESCRIPTION" value=""  id="lot_desc" name="lot_desc" onchange="return trim(this)" autocomplete="off" maxlength="400"/>
		              </div>	
					</div>
					
					
				
				<div class="col-sm-4">
					<div class="form-group">			
		             	<label><b>Assessment Value : </b></label>
						<input type="text" class="form-control" placeholder="ASSESSMENT VALUE" value=""  id="assessment_value" name="assessment_value" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		           </div>
		           
					<div class="form-group">			
		             	<label><b>Duties : </b></label>
						<input type="text" class="form-control" placeholder="DUTIES" value=""  id="duties" name="duties" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		           </div>
		           
					<div class="form-group">			
		             	<label><b>VAT : </b></label>
						<input type="text" class="form-control" placeholder="VAT" value=""  id="vat" name="vat" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		           </div>
		           
					<div class="form-group">			
		             	<label><b>PREMIUM RATE : </b></label>
						<input type="text" class="form-control" placeholder="PREMIUM RATE" value=""  id="premium_rate" name="premium_rate" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		           </div>
		           
					<div class="form-group">			
		             <label><b>Active : </b></label>
						<select class="form-control" id="active" name="active"><option value="">ACTIVE</option><option value="1">Yes</option><option value="0">No</option></select>
		            </div>
					<div class="form-group">			
		             	<label><b>UNIT : </b></label>
						<input type="text" class="form-control" placeholder="UNIT" value=""  id="unit" name="unit" onchange="return trim(this)" autocomplete="off" maxlength="50"/>
		           </div>
					<div class="form-group">			
		             	<label><b>UNIT QTY : </b></label>
						<input type="text" class="form-control" placeholder="UNIT QTY" value=""  id="unit_qty" name="unit_qty" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		           	</div>
		              <div class="form-group">
		                <label><b>Lot Type Id : </b></label>
						<input type="text" class="form-control" placeholder="LOT TYPE ID" value=""  id="lot_type_id" name="lot_type_id" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		      </div>

				<div class="col-sm-4">	

		              
					<div class="form-group">
		             <label><b>For Bid : </b></label>
						<select class="form-control" id="is_bid" name="is_bid"><option value="">BID</option><option value="1">Yes</option><option value="0">No</option></select>
		            </div>
		            <div class="form-group">
		             <label><b>For Buy : </b></label>
						<select class="form-control" id="is_buy" name="is_buy"><option value="">BUY</option><option value="1">Yes</option><option value="0">No</option></select>
		            </div>

		              <div class="form-group">
		                <label><b>Lot Increment Time : </b></label>
		                <select class="form-control" id="lot_increment_time" name="lot_increment_time">
								<option value="0">LOT INCREMENT TIME</option>
							<%
							for(int i=0; i < 60; i++){%> 
								<option value="<%=i%>"><%=i%></option>
							<%}%> 
					  </select>
		              </div>
		              
			        <div class="form-group">
		                <label><b>Amount Bid : </b></label>
						<input type="text" class="form-control" placeholder="AMOUNT BID" value=""  id="amount_bid" name="amount_bid" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		                <label><b>Amount Buy : </b></label>
						<input type="text" class="form-control" placeholder="AMOUNT BUY" value=""  id="amount_buy" name="amount_buy" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		             <label><b>Transaction Won : </b></label>
						<select class="form-control" id="action_taken" name="action_taken"><option value="0">TRANSACTION WON</option><option value="1">Bid</option><option value="2">Buy</option></select>
		            </div>
		              <div class="form-group">
		                <label><b>Buy Price : </b></label>
						<input type="text" class="form-control" placeholder="BUY PRICE" value=""  id="buy_price" name="buy_price" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		                <label><b>Starting Bid Amount : </b></label>
						<input type="text" class="form-control" placeholder="STARTING BID AMOUNT" value=""  id="starting_bid_amount" name="starting_bid_amount" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		                <label><b>End Date / Time : </b></label>
		                <input type="text" class="form-control" placeholder="END DATE / TIME" id="end_date_time" name="end_date_time"/>
		              </div>
				</div>


            <div class="col-md-12">
            
				
	            <div class="form-group">
	              <label><b>Bidder : </b></label>
					<select class="userBidderSel form-control" id="bidder_id" name="bidder_id" style="height: 40px; size: 25px;">
							<option value="0">BIDDER</option>
						<%for(User u : userBidderList){%> 
							<option value="<%=u.getId()%>"><%=u.getFirst_name()%> <%=u.getLast_name()%> (<%=u.getEmail_address()%>)</option>
						<%}%> 
					</select>
	            </div>
			

              </div>
				
				<div class="col-sm-12">
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="saveLot()">Save</a>
					</div>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="clearLot()">Clear</a>
					</div>
					<%if(auctionId_wip!=null && auctionId_wip.doubleValue() > 0){%>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="viewAuction(<%=auctionId_wip%>)">Auction</a>
					</div>
					<%} %>
				</div>
					

            </div>
        </section>
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



function onLoadPage(){
	//document.frm.userId.focus();	
/*	
//	if(document.frm.userId.value!=""){
//		< /%if(userId!=null){ %>
//			document.frm.pw.focus();
//		< /%}%>
//	}
*/
	if(document.frm.auctionId_wip.value!="" && document.frm.auctionId_wip.value!="0"){
		document.frm.auction_id.value = document.frm.auctionId_wip.value;	
	}

	
}

function validateSave(){
	var isSave = true;

	var auction_id = document.getElementById("auction_id").value;
	var lot_id = document.getElementById("lot_id").value;
	var lot_no = document.getElementById("lot_no").value;
	var lot_desc = document.getElementById("lot_desc").value;
	var assessment_value = document.getElementById("assessment_value").value;
	var duties = document.getElementById("duties").value;
	var vat = document.getElementById("vat").value;
	var premium_rate = document.getElementById("premium_rate").value; 
	var active = document.getElementById("active").value; 
	var unit = document.getElementById("unit").value;
	var unit_qty = document.getElementById("unit_qty").value;
	var lot_type_id = document.getElementById("lot_type_id").value; 

	var msgInfo = "";
	var msgbgcol = "";

	if(auction_id==""){
		msgInfo = "AUCTION ID IS REQUIRED.";
		msgbgcol = "red";
		document.frm.auction_id.focus();
		isSave = false;
	}else if(lot_id==""){
		msgInfo = "LOT ID IS REQUIRED.";
		msgbgcol = "red";
		document.frm.lot_id.focus();
		isSave = false;
	}else if(lot_no==""){
		msgInfo = "LOT NO IS REQUIRED.";
		msgbgcol = "red";
		document.frm.lot_no.focus();
		isSave = false;
	}else if(lot_desc==""){
		msgInfo = "DESCRIPTION  IS REQUIRED.";
		msgbgcol = "red";
		document.frm.lot_desc.focus();
		isSave = false;
	}else if(assessment_value==""){
		msgInfo = "ASSESSMENT VALUE IS REQUIRED.";
		msgbgcol = "red";
		document.frm.assessment_value.focus();
		isSave = false;
	}else if(duties==""){
		msgInfo = "DUTIES  IS REQUIRED.";
		msgbgcol = "red";
		document.frm.duties.focus();
		isSave = false;
	}else if(vat ==""){
		msgInfo = "VAT IS REQUIRED.";
		msgbgcol = "red";
		document.frm.vat.focus();
		isSave = false;
	}else if(premium_rate==""){
		msgInfo = "PREMIUM RATE IS REQUIRED.";
		msgbgcol = "red";
		document.frm.premium_rate.focus();
		isSave = false;
	}else if(active==""){
		msgInfo = "ACTIVE IS REQUIRED.";
		msgbgcol = "red";
		document.frm.active.focus();
		isSave = false;
	}else if(unit==""){
		msgInfo = "UNIT IS REQUIRED.";
		msgbgcol = "red";
		document.frm.unit.focus();
		isSave = false;
	}else if(unit_qty==""){
		msgInfo = "UNIT QTY IS REQUIRED.";
		msgbgcol = "red";
		document.frm.unit_qty.focus();
		isSave = false;
	}else if(lot_type_id==""){
		msgInfo = "LOT TYPE ID IS REQUIRED.";
		msgbgcol = "red";
		document.frm.lot_type_id.focus();
		isSave = false;
	}

	if(isSave==false){
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
	}
	return isSave;
}

function clearLot(){
	
	document.getElementById("lot_no").value="";
	document.getElementById("lot_id").value="";
	document.getElementById("auction_id").value="";
	document.getElementById("lot_desc").value="";
	document.getElementById("assessment_value").value="0";
	document.getElementById("duties").value="0";
	document.getElementById("vat").value="0";
	document.getElementById("unit").value="";
	document.getElementById("premium_rate").value="0";
	document.getElementById("lot_type_id").value="0";
	document.getElementById("active").value="";
	document.getElementById("unit_qty").value="0";
	
	document.getElementById("amount_bid").value="0";
	document.getElementById("amount_buy").value="0";
	document.getElementById("action_taken").value="";
	document.getElementById("is_buy").value="0";
	document.getElementById("is_bid").value="1";
	document.getElementById("buy_price").value="0";
	document.getElementById("lot_increment_time").value="0";
	document.getElementById("lot_name").value="";
	document.getElementById("bidder_id").value="0";

	$(document).ready(function() {
		$('#bidder_id').select2().val(0);
		document.getElementById("bidder_id").value="0";
		//$('#coordinator').select2().val(0);
	});
	
	
	

}

function saveLot(){
	if(validateSave()){
		document.frm.action.value="saveLot";
		document.frm.submit();
	}
	setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);
	//	alert('s');
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

function viewAuction(id){
	document.getElementById("action").value="viewAuction";
	document.getElementById("manager").value="auction-manager";
	document.getElementById("auctionId_wip").value=id;
	document.frm.submit();
}

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
	<script src="https://cdn.ckeditor.com/4.4.3/standard/ckeditor.js"></script>
	
	<!-- Bootstrap WYSIHTML5 -->
	    <!-- bootstrap wysihtml5 - text editor -->
    
    <script src="https://cdn.ckeditor.com/4.4.3/standard/ckeditor.js"></script>
	<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>

<script>

$(document).ready(function() {

	   $('#end_date_time').datetimepicker({
			formatTime:'H:i',
			formatDate:'d.m.Y',
			//defaultDate:'8.12.1986', // it's my birthday
			defaultDate:'+03.01.1970', // it's my birthday
			defaultTime:'12:00',
			timepickerScrollbar:true
		});
	
	
	
	
 	    $("#lot_no").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });


 	    $("#lot_id").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });


 	    $("#auction_id").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    



 	    $("#assessment_value").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    
 	    $("#duties").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    
 	  
 	    $("#vat").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });

 	    
 	    
 	    $("#premium_rate").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    
 	    $("#lot_type_id").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    
 	    $("#unit_qty").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });


 	    $("#amount_bid").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    
 	    $("#amount_buy").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    
 	    $("#buy_price").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    
 	    $("#starting_bid_amount").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    
 	   
 	    
 	});	

	$(document).ready(function() {
		  $(".userBidderSel").select2();
	});
/*
	$(document).ready(function() {
		  $(".userCoordinatorSel").select2();
	});
 */
</script>
</body>
</html>
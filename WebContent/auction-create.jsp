<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="hmr.com.bean.Auction"
		 import="hmr.com.bean.Lov"
		 import="java.util.List" 
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
		  
		
		System.out.println("userId : "+userId);
		System.out.println("user_id : "+user_id);
		/*
		List<User> uList =(ArrayList<User>) request.getAttribute("userList");
		System.out.println("ulList "+uList.size());
		*/
		
		ArrayList<Lov> userRoleList = (ArrayList<Lov>) request.getSession().getAttribute("USER-ROLE-LIST");
		ArrayList<Lov> genderList = (ArrayList<Lov>) request.getSession().getAttribute("GENDER-LIST");
		ArrayList<Lov> userStatusList = (ArrayList<Lov>) request.getSession().getAttribute("USER-STATUS-LIST");
		ArrayList<Lov> catLev1LovList =  (ArrayList<Lov>)request.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-LIST");
		
		//ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getSession().getAttribute("USER-COORDINATOR-LIST");
		ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getAttribute("userCoordinatorList");
		
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
		       <input type="hidden" name="manager" id="manager" value="auction-manager"/>
		       <input type="hidden" name="action" id="action" value=""/>
		       <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
		       <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
		       <input type="hidden" name="auctionId_wip" id="auctionId_wip" value=""/>
		       
        <section class="page-section color" style="padding:15px;">
        
            <div class="container">
                <div class="row" >

                    <div class="col-sm-12">
                        <h3 class="block-title"><span>Auctions  <label>create </label></span></h3>
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
		                <label><b>Auction Name : </b></label>
						<input type="text" class="form-control" placeholder="AUCTION NAME" value=""  id="auction_name" name="auction_name" onchange="return trim(this)" autocomplete="off" maxlength="100"/>
		              </div>
		              <div class="form-group">
		                <label><b>Auction Id : </b></label>
						<input type="text" class="form-control" placeholder="AUCTION ID" value=""  id="auction_id" name="auction_id" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		                <label><b>Auction No : </b></label>
						<input type="text" class="form-control" placeholder="AUCTION NO" value=""  id="auction_no" name="auction_no" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		                <label><b>Location : </b></label>
						<input type="text" class="form-control" placeholder="LOCATION" value=""  id="location" name="location" onchange="return trim(this)" autocomplete="off" maxlength="100"/>
		              </div>
		              <div class="form-group">
		                <label><b>Start Date / Time : </b></label>
		                <input type="text" class="form-control" placeholder="START DATE / TIME" id="start_date_time" name="start_date_time" readonly="readonly"/>
		              </div>
		              <div class="form-group">
		                <label><b>End Date / Time : </b></label>
		                <input type="text" class="form-control" placeholder="END DATE / TIME" id="end_date_time" name="end_date_time" readonly="readonly"/>
		              </div>
		              <div class="form-group">
		                <label><b>Description : </b></label>
						<input type="text" class="form-control" placeholder="DESCRIPTION" value=""  id="auction_desc" name="auction_desc" onchange="return trim(this)" autocomplete="off" maxlength="400"/>
		              </div>	
					</div>
					
					
				
				<div class="col-sm-4">
		              <div class="form-group">
		                <label><b>Category Level 1 : </b></label>
		                <select class="form-control" id="category_level_1" name="category_level_1">
								<option value="0">CATEGORY LEVEL 1</option>
							<%for(Lov l :catLev1LovList){%> 
								<option value="<%=l.getId()%>"><%=l.getValue()%></option>
							<%}%> 
					  </select>
		              </div>
				
		            <div class="form-group">
		              <label><b>Auction Type : </b></label>
						<select class="auctionTypeSel form-control" id="auction_type" name="auction_type">
								<option value="0">AUCTION TYPE</option>
 								<option value="15">Online</option>
 								<option value="16">Negotiated</option>
						</select>
		            </div>
				
		            <div class="form-group">
		              <label><b>Status : </b></label>
						<select class="auctionStatusSel form-control" id="auctionStatus" name="auctionStatus">
								<option value="0">STATUS</option>
								<option value="30">Open</option>
								<option value="31">Closed</option>
								<option value="32">For Review</option>
						</select>
					</div>
					<div class="form-group">	
		             <label><b>One Lot Per Bidder : </b></label>
						<select class="form-control" id="one_lot_per_bidder" name="one_lot_per_bidder"><option value="">ONE LOT PER BIDDER</option><option value="1">Yes</option><option value="0">No</option></select>
		            
		            </div>
				
					<div class="form-group">			
		             <label><b>Active : </b></label>
						<select class="form-control" id="active" name="active"><option value="">ACTIVE</option><option value="1">Yes</option><option value="0">No</option></select>
		            </div>
		            <div class="form-group">
		              <label><b>Visibility : </b></label>
						<select class="form-control" id="visibility" name="visibility">
								<option value="0">VISIBILITY</option>
								<option value="33">Public</option>
								<option value="34">Private</option>
						</select>
		            </div>

		              <div class="form-group">
		                <label><b>Auction Item Increment Time : </b></label>
		                <select class="form-control" id="auction_item_increment_time" name="auction_item_increment_time">
								<option value="">AUCTION ITEM INCREMENT TIME</option>
							<%
							 
							for(int i=0; i < 60; i++){%> 
								<option value="<%=i%>"><%=i%></option>
							<%}%> 
					  </select>
		              </div>

				</div>
		
		
		
				<div class="col-sm-4">
					<div class="form-group">			
		             <label><b>Bid Deposit : </b></label>
						<select class="form-control" id="bid_deposit" name="bid_deposit"><option value="">BID DEPOSIT</option><option value="1">Yes</option><option value="0">No</option></select>
		            </div>
		              <div class="form-group">
		                <label><b>Bid Deposit Amount : </b></label>
						<input type="text" class="form-control" placeholder="BID DEPOSIT AMOUNT" value=""  id="bid_deposit_amount" name="bid_deposit_amount" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		                <label><b>No of lots: </b></label>
						<input type="text" class="form-control" placeholder="NO OF LOTS" value=""  id="no_of_lots" name="no_of_lots" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		                <label><b>No of items : </b></label>
						<input type="text" class="form-control" placeholder="NO OF ITEMS" value=""  id="no_of_items" name="no_of_items" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
					<div class="form-group">			
		             <label><b>Date Sync : </b></label>
						<input type="text" class="form-control" placeholder="Date Sync" id="date_sync" name="date_sync" readonly="readonly"/>
		            </div>
		            
					<div class="form-group">	
		             <label><b>One As Start Bid : </b></label>
						<select class="form-control" id="one_start_bid" name="one_start_bid">
						<option value="">ONE AS START BID</option>
						<option value="1">Yes</option>
						<option value="0">No</option></select>
		            </div>
					<div class="form-group">	
		             <label><b>Bid Qualifier Price : </b></label>
						<select class="form-control" id="bid_qualifier_price" name="bid_qualifier_price">
						<option value="0">BID QUALIFIER PRICE</option>
						<option value="1">Reserve Price</option>
						<option value="2">SRP</option>
						<option value="3">Target Price</option>
						<option value="4">Assess Value Price</option>
						</select>
		            
		            </div>
				</div>


				
				

            <div class="col-md-12">
            
				
	            <div class="form-group">
	              <label><b>Coordinator : </b></label>
					<select class="userCoordinatorSel form-control" id="coordinator" name="coordinator" style="height: 40px; size: 25px;">
							<option value="0">COORDINATOR</option>
						<%for(User u : userCoordinatorList){%> 
							<option value="<%=u.getId()%>"><%=u.getFirst_name()%> <%=u.getLast_name()%> 	(<%=u.getEmail_address()%>)</option>
						<%}%> 
					</select>
	            </div>
			
            
              <div class="box box-info">
                <div class="box-header">
                  <h3 class="box-title"><label><b>Terms and Conditions : </b></label></h3>
                </div><!-- /.box-header -->
                <div class="box-body pad">
                  
                    <textarea id="terms_and_conditions" name="terms_and_conditions" rows="10" cols="80">
                                            
                    </textarea>
                  
                </div>
              </div><!-- /.box -->
              </div>

				<div class="col-sm-12">
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="saveAuction()">Save</a>
					</div>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="clearAuction()">Clear</a>
					</div>
				</div>
					

                    <!-- 
                    <div class="col-sm-2">
                        <a class="btn btn-theme btn-block " href="#" onclick="clearLogin()">Clear</a>
                    </div>
        --->
                    
	                
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
}

function validateSave(){
	var isSave = true;
	

	var auction_name = document.getElementById("auction_name").value;
	var auction_id = document.getElementById("auction_id").value;
	var auction_no = document.getElementById("auction_no").value;
	var location = document.getElementById("location").value;
	var start_date_time = document.getElementById("start_date_time").value;
	var end_date_time = document.getElementById("end_date_time").value;
	var auction_desc = document.getElementById("auction_desc").value;
	var category_level_1 = document.getElementById("category_level_1").value;
	var auction_type = document.getElementById("auction_type").value;
	//var coordinator = document.getElementById("coordinator").value; 
	var auctionStatus = document.getElementById("auctionStatus").value; 
	var active = document.getElementById("active").value; 
	var visibility = document.getElementById("visibility").value;
	var auction_item_increment_time = document.getElementById("auction_item_increment_time").value;
	var bid_deposit = document.getElementById("bid_deposit").value; 
	var bid_deposit_amount = document.getElementById("bid_deposit_amount").value;
	var no_of_lots = document.getElementById("no_of_lots").value;
	var no_of_items = document.getElementById("no_of_items").value;
	var date_sync = document.getElementById("date_sync").value;
	var coordinator = document.getElementById("coordinator").value;
	
	var msgInfo = "";
	var msgbgcol = "";

	if(auction_name==""){
		msgInfo = "AUCTION NAME IS REQUIRED.";
		msgbgcol = "red";
		document.frm.auction_name.focus();
		isSave = false;
	}else if(auction_id==""){
		msgInfo = "AUCTION ID IS REQUIRED.";
		msgbgcol = "red";
		document.frm.auction_id.focus();
		isSave = false;
	}else if(auction_no==""){
		msgInfo = "AUCTION NO IS REQUIRED.";
		msgbgcol = "red";
		document.frm.auction_no.focus();
		isSave = false;
	}else if(location==""){
		msgInfo = "LOCATION IS REQUIRED.";
		msgbgcol = "red";
		document.frm.location.focus();
		isSave = false;
	}else if(start_date_time==""){
		msgInfo = "START DATE / TIME IS REQUIRED.";
		msgbgcol = "red";
		document.frm.start_date_time.focus();
		isSave = false;
	}else if(end_date_time==""){
		msgInfo = "END DATE / TIME IS REQUIRED.";
		msgbgcol = "red";
		document.frm.end_date_time.focus();
		isSave = false;
	}else if(auction_desc==""){
		msgInfo = "DESCRIPTION IS REQUIRED.";
		msgbgcol = "red";
		document.frm.auction_desc.focus();
		isSave = false;
	}else if(category_level_1==""){
		msgInfo = "CATEGORY LEVEL 1 IS REQUIRED.";
		msgbgcol = "red";
		document.frm.category_level_1.focus();
		isSave = false;
	}else if(auction_type==0){
		msgInfo = "AUCTION TYPE IS REQUIRED.";
		msgbgcol = "red";
		document.frm.auction_type.focus();
		isSave = false;
	}else if(auctionStatus=="0"){
		msgInfo = "STATUS IS REQUIRED.";
		msgbgcol = "red";
		document.frm.auctionStatus.focus();
		isSave = false;
	}else if(active==""){
		msgInfo = "ACTIVE IS REQUIRED.";
		msgbgcol = "red";
		document.frm.active.focus();
		isSave = false;
	}else if(visibility=="0"){
		msgInfo = "VISIBILITY IS REQUIRED.";
		msgbgcol = "red";
		document.frm.visibility.focus();
		isSave = false;
	}else if(auction_item_increment_time==""){
		msgInfo = "AUCTION ITEM INCREMENT IS REQUIRED.";
		msgbgcol = "red";
		document.frm.auction_item_increment_time.focus();
		isSave = false;
	}else if(coordinator=="" || coordinator=="0"){
		msgInfo = "COORDINATOR IS REQUIRED.";
		msgbgcol = "red";
		document.frm.coordinator.focus();
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

function clearAuction(){
	document.getElementById("auction_name").value="";
	document.getElementById("auction_id").value="";
	document.getElementById("auction_no").value="";
	document.getElementById("location").value="";
	document.getElementById("start_date_time").value="";
	document.getElementById("end_date_time").value="";
	document.getElementById("auction_desc").value="";
	document.getElementById("category_level_1").value=0;
	document.getElementById("auction_type").value=0;
	//document.getElementById("coordinator").value="";
	document.getElementById("auctionStatus").value="";
	document.getElementById("active").value="";
	document.getElementById("visibility").value="";
	document.getElementById("auction_item_increment_time").value="";
	document.getElementById("bid_deposit").value="";
	document.getElementById("bid_deposit_amount").value="";
	document.getElementById("no_of_lots").value="";
	document.getElementById("no_of_items").value="";
	document.getElementById("date_sync").value="";
	//document.getElementById("coordinator").selecti
	document.getElementById("terms_and_conditions").value="";
	
	document.getElementById("one_lot_per_bidder").value = "0";
	document.getElementById("one_start_bid").value = "0";
	document.getElementById("bid_qualifier_price").value = "0";
	
	$(document).ready(function() {
		$('#coordinator').select2().val(0);
		document.getElementById("coordinator").value="0";
		//$('#coordinator').select2().val(0);
	});
	
	
}

function saveAuction(){
	if(validateSave()){
		document.frm.action.value="saveAuction";
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

$('#start_date_time').datetimepicker({
	formatTime:'H:i',
	formatDate:'d.m.Y',
	//defaultDate:'8.12.1986', // it's my birthday
	defaultDate:'+03.01.1970', // it's my birthday
	defaultTime:'12:00',
	timepickerScrollbar:false
});

$('#end_date_time').datetimepicker({
	formatTime:'H:i',
	formatDate:'d.m.Y',
	//defaultDate:'8.12.1986', // it's my birthday
	defaultDate:'+03.01.1970', // it's my birthday
	defaultTime:'12:00',
	timepickerScrollbar:false
});

$('#date_sync').datetimepicker({
	formatTime:'H:i',
	formatDate:'d.m.Y',
	//defaultDate:'8.12.1986', // it's my birthday
	defaultDate:'+03.01.1970', // it's my birthday
	defaultTime:'12:00',
	timepickerScrollbar:false
});
/*
$(function () {
    $("#example1").DataTable({
      	"order": [[ 4, "desc" ]],
      	"lengthMenu": [[5, 25, 50, 100], [5, 25, 50, 100]]
    });
  });
 */ 
 
 /*
 
*/


$(document).ready(function() {
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


 	    $("#auction_no").keydown(function (e) {
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


 	    $("#bid_deposit_amount").keydown(function (e) {
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


 	    $("#no_of_lots").keydown(function (e) {
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
 	    
 	    $("#no_of_items").keydown(function (e) {
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
	 $(function () {
	     // Replace the <textarea id="editor1"> with a CKEditor
	     // instance, using default configuration.
	     CKEDITOR.replace('terms_and_conditions');
	     //bootstrap WYSIHTML5 - text editor
	     $(".textarea").wysihtml5();
	   });
	 /*
	 $(function () {
	     // Replace the <textarea id="editor1"> with a CKEditor
	     // instance, using default configuration.
	     CKEDITOR.replace('editor-2');
	     //bootstrap WYSIHTML5 - text editor
	     $(".textarea").wysihtml5();
	   });
	 */
 });
 
	$(document).ready(function() {
		  $(".userCoordinatorSel").select2();
	});
 
</script>
</body>
</html>
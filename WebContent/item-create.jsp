<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="hmr.com.bean.Auction"
		 import="hmr.com.bean.Lot"
		 import="hmr.com.bean.Item"
		 import="hmr.com.bean.Lov"
		 import="java.math.BigDecimal"
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
		  
		BigDecimal auctionId_wip = request.getAttribute("auctionId_wip")!=null && !"".equals(request.getAttribute("auctionId_wip")) ? (BigDecimal)request.getAttribute("auctionId_wip") : new BigDecimal(0);
		  
		
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
		ArrayList<Lov> catLev2LovList =  (ArrayList<Lov>)request.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-LIST");
		ArrayList<Lov> catLev3LovList =  (ArrayList<Lov>)request.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-LIST");
		
		
		//ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getSession().getAttribute("USER-COORDINATOR-LIST");
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
		       <input type="hidden" name="manager" id="manager" value="item-manager"/>
		       <input type="hidden" name="action" id="action" value=""/>
		       <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
		       <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
		       <input type="hidden" name="auctionId_wip" id="auctionId_wip" value="<%=auctionId_wip%>"/>
		       <input type="hidden" name="itemId_wip" id="itemId_wip" value=""/>
		       <input type="hidden" name="sync_status" id="sync_status" value="0"/>
		       
		       
        <section class="page-section color" style="padding:15px;">
        
            <div class="container">
                <div class="row" >

                    <div class="col-sm-12">
                        <h3 class="block-title"><span>Items  <label>create </label></span></h3>
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
 					
 					
 					
 					
 					<%--
 
	
			Integer  = !req.getParameter("category_level_1").equals("") ? Integer.valueOf(req.getParameter("category_level_1")) : 0;
			Integer category_level_2 = !req.getParameter("category_level_2").equals("") ? Integer.valueOf(req.getParameter("category_level_2")) : 0;
			Integer category_level_3 = !req.getParameter("category_level_3").equals("") ? Integer.valueOf(req.getParameter("category_level_3")) : 0;
			
 					
 					
 					
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
		                <label><b>Item Id : </b></label>
						<input type="text" class="form-control" placeholder="ITEM ID" value=""  id="item_id" name="item_id" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		                <label><b>Reference No : </b></label>
						<input type="text" class="form-control" placeholder="REFERENCE NO" value=""  id="reference_no" name="reference_no" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              
		              <div class="form-group">
		                <label><b>Target Price : </b></label>
						<input type="text" class="form-control" placeholder="TARGET PRICE" value=""  id="target_price" name="target_price" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              
		              <div class="form-group">
		                <label><b>Reserve Price : </b></label>
						<input type="text" class="form-control" placeholder="RESERVE PRICE" value=""  id="reserve_price" name="reserve_price" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>

		              <div class="form-group">
		                <label><b>Description : </b></label>
						<input type="text" class="form-control" placeholder="DESCRIPTION" value=""  id="item_desc" name="item_desc" onchange="return trim(this)" autocomplete="off" maxlength="100"/>
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
		                <label><b>Item Increment Time : </b></label>
		                <select class="form-control" id="item_increment_time" name="item_increment_time">
								<option value="0">ITEM INCREMENT TIME</option>
							<%
							for(int i=0; i < 60; i++){%> 
								<option value="<%=i%>"><%=i%></option>
							<%}%> 
					  </select>
		              </div>
		              
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
		                <label><b>Category Level 2 : </b></label>
		                <select class="form-control" id="category_level_2" name="category_level_2">
								<option value="0">CATEGORY LEVEL 2</option>
							<%for(Lov l :catLev2LovList){%> 
								<option value="<%=l.getId()%>"><%=l.getValue()%></option>
							<%}%> 
					  </select>
		              </div>
		              
		              <div class="form-group">
		                <label><b>Category Level 3 : </b></label>
		                <select class="form-control" id="category_level_3" name="category_level_3">
								<option value="0">CATEGORY LEVEL 3</option>
							<%for(Lov l :catLev3LovList){%> 
								<option value="<%=l.getId()%>"><%=l.getValue()%></option>
							<%}%> 
					  </select>
		              </div>
		              

				</div>
		
		
		
				<div class="col-sm-4">		
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
						<select class="form-control" id="action_taken" name="action_taken"><option value="">TRANSACTION WON</option><option value="1">Bid</option><option value="0">Buy</option></select>
		            </div>
		              <div class="form-group">
		                <label><b>Buy Price : </b></label>
						<input type="text" class="form-control" placeholder="BUY PRICE" value=""  id="buy_price" name="buy_price" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
				</div>


				
				

            <div class="col-md-12">
            
				
	            <div class="form-group">
	              <label><b>Bidder : </b></label>
					<select class="userBidderSel form-control" id="bidder_id" name="bidder_id" style="height: 40px; size: 25px;">
							<option value="0">BIDDER</option>
						<%for(User u : userBidderList){%> 
							<option value="<%=u.getId()%>"><%=u.getFirst_name()%> <%=u.getLast_name()%></option>
						<%}%> 
					</select>
	            </div>
			

              </div>

				<div class="col-sm-12">
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="saveItem()">Save</a>
					</div>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="clearItem()">Clear</a>
					</div>
				
					<%if(auctionId_wip!=null && auctionId_wip.doubleValue() > 0){%>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="viewAuction(<%=auctionId_wip%>)">Auction</a>
					</div>
					<%} %>
					
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

	if(document.frm.auctionId_wip.value!="" && document.frm.auctionId_wip.value!="0"){
		document.frm.auction_id.value = document.frm.auctionId_wip.value;	
	}
}

function validateSave(){
	var isSave = true;
	

	var auction_id = document.getElementById("auction_id").value;
	var lot_id = document.getElementById("lot_id").value;
	var item_id = document.getElementById("item_id").value;
	var reference_no = document.getElementById("reference_no").value;
	var target_price = document.getElementById("target_price").value;
	var reserve_price = document.getElementById("reserve_price").value;
	var item_desc = document.getElementById("item_desc").value;
	var is_buy = document.getElementById("is_buy").value; 
	var is_bid = document.getElementById("is_bid").value;
	var item_increment_time = document.getElementById("item_increment_time").value;
	var category_level_1 = document.getElementById("category_level_1").value;
	var category_level_2 = document.getElementById("category_level_2").value;
	var category_level_3 = document.getElementById("category_level_3").value;
	var amount_bid = document.getElementById("amount_bid").value=0;
	var amount_buy = document.getElementById("amount_buy").value=0;
	var action_taken = document.getElementById("action_taken").value="";
	var buy_price = document.getElementById("buy_price").value=0;
	var bidder_id = document.getElementById("bidder_id").value;
	
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
	}else if(item_id==""){
		msgInfo = "ITEM ID IS REQUIRED.";
		msgbgcol = "red";
		document.frm.item_id.focus();
		isSave = false;
	}else if(reference_no==""){
		msgInfo = "REFERENCE NO IS REQUIRED.";
		msgbgcol = "red";
		document.frm.reference_no.focus();
		isSave = false;
	}else if(target_price==""){
		msgInfo = "TARGET PRICE IS REQUIRED.";
		msgbgcol = "red";
		document.frm.target_price.focus();
		isSave = false;
	}else if(reserve_price==""){
		msgInfo = "RESERVE PRICE IS REQUIRED.";
		msgbgcol = "red";
		document.frm.reserve_price.focus();
		isSave = false;
	}else if(item_desc==0){
		msgInfo = "DESCRIPTION IS REQUIRED.";
		msgbgcol = "red";
		document.frm.item_desc.focus();
		isSave = false;
	}else if(is_bid==""){
		msgInfo = "FOR BID IS REQUIRED.";
		msgbgcol = "red";
		document.frm.is_bid.focus();
		isSave = false;
	}else if(is_buy==""){
		msgInfo = "FOR BUY IS REQUIRED.";
		msgbgcol = "red";
		document.frm.is_buy.focus();
		isSave = false;
	}else if(item_increment_time==""){
		msgInfo = "ITEM INCREMENT IS REQUIRED.";
		msgbgcol = "red";
		document.frm.item_increment_time.focus();
		isSave = false;
	}else if(category_level_1==""){
		msgInfo = "CATEGORY LEVEL 1 IS REQUIRED.";
		msgbgcol = "red";
		document.frm.category_level_1.focus();
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

function clearItem(){
	document.getElementById("auction_id").value="";
	document.getElementById("lot_id").value="";
	document.getElementById("item_id").value="";
	document.getElementById("reference_no").value="";
	document.getElementById("target_price").value=0; 
	document.getElementById("reserve_price").value=0; 
	document.getElementById("item_desc").value="";
	document.getElementById("is_buy").value=0; 
	document.getElementById("is_bid").value=0;
	document.getElementById("item_increment_time").value=0;
	document.getElementById("category_level_1").value="";
	document.getElementById("category_level_2").value="";
	document.getElementById("category_level_3").value="";
	document.getElementById("amount_bid").value=0;
	document.getElementById("amount_buy").value=0;
	document.getElementById("action_taken").value="";
	document.getElementById("buy_price").value=0;
	
	var bidder_id = document.getElementById("bidder_id").value=0;
	
	$(document).ready(function() {
		$('#bidder_id').select2().val(0);
		document.getElementById("bidder_id").value="0";
		//$('#coordinator').select2().val(0);
	});
	
	
}

function saveItem(){
	if(validateSave()){
		document.frm.action.value="saveItem";
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
/*
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

*/
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


 	    $("#item_id").keydown(function (e) {
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


 	    $("#reference_no").keydown(function (e) {
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
 	    
 	    $("#target_price").keydown(function (e) {
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
 	    
 	    $("#reserve_price").keydown(function (e) {
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
 	  
 	});	

 $(document).ready(function() {
	 /*
	 $(function () {
	     // Replace the <textarea id="editor1"> with a CKEditor
	     // instance, using default configuration.
	     CKEDITOR.replace('terms_and_conditions');
	     //bootstrap WYSIHTML5 - text editor
	     $(".textarea").wysihtml5();
	   });
	 */
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
		  $(".userBidderSel").select2();
	});
 
</script>
</body>
</html>
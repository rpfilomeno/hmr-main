<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="hmr.com.bean.Auction"
		 import="java.util.List" 
		 import="java.util.ArrayList"  
		 import="javax.servlet.RequestDispatcher"
		 import="java.text.SimpleDateFormat"
		 import="hmr.com.manager.ImageManager"
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%
		System.out.println("PAGE auction-list.jsp");
	
		String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";

		String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
		String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
		  
		  
		String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
		String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
		String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : (String)request.getSession().getAttribute("userId");
		//Integer mobileNo = request.getAttribute("mobileNo")!=null ? (Integer)request.getAttribute("mobileNo") : null;
		  
		  
		//IDS
		Integer user_id = request.getAttribute("user-id")!=null ? (Integer)request.getAttribute("user-id") : (Integer)request.getSession().getAttribute("user-id");
		  
		
		List<Auction> aList =(ArrayList<Auction>) request.getAttribute("auctionList");
		System.out.println("ulList "+aList.size());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		
		System.out.println("PAGE asdfasdf.jsp");
			
	%>
    <title><%=COMPANY_NAME%></title>



    <!-- DataTables -->
    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">


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
        <section class="page-section color" style="padding:15px;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h3 class="block-title"><span>Auctions  <label>create, list, update, delete </label></span></h3>
 						

 		                <div id="tableDiv1" class="box-body table-responsive">
										
						  <div id="msgDiv"></div>
						  			  
		                  <table id="table1" class="table table-bordered table-striped no-margin">
		
		                    <thead>
		                      <tr>
		                        <th>Main Image</th>
		                        <th>Auction ID</th>
		                        <th>Auction No</th>
		                        <th>Auction Name</th>
		                        <th>Location</th>
		                        <th>Start Date/Time</th>
		                        <th>Close Date/Time</th>
		                        <th>Auction Type</th>
		                        <th>No of Lots</th>
		                        <th>No of Items</th>
		                        <th>Active</th>
		                        <th>Status</th>
		                        <th>Date Created</th>
		                        <th>Date Sync</th>
		                      </tr>
		                    </thead>
		                    <tbody>
                      <%
                      	for(Auction a : aList){
                            String date_created = "";
                            if(a.getDate_created()!=null){
                            	date_created = sdf.format(a.getDate_created());
                            }

                            String date_updated = "";
                            if(a.getDate_updated()!=null){
                            	date_updated = sdf.format(a.getDate_updated());
                            }
                            
                            //String date_registration = "";
                            //if(a.getDate_registration()!=null){
                            //	date_registration = sdf.format(a.getDate_registration());
                            //}
                            
                            

                            String start_date_time = "";
                            if(a.getStart_date_time()!=null){
                            	start_date_time = sdf.format(a.getStart_date_time());
                            }
                            
                            String end_date_time = "";
                            if(a.getEnd_date_time()!=null){
                            	end_date_time = sdf.format(a.getEnd_date_time());
                            }
                            
                            String date_sync = "";
                            if(a.getDate_sync()!=null){
                            	date_sync = sdf.format(a.getDate_sync());
                            }
                            
                            
                            String active = "Yes";
                            if(a.getActive()==0){
                            	active = "No";
                            }
                            
                            String status = "";
                            if(a.getStatus()==10){
                            	status = "Banned";
                            }else if(a.getStatus()==11){
                            	status = "Registered";
                            } else if(a.getStatus()==12){
                            	status = "For Email Verification";
                            }
                      %>
		                  <tr>
		                  	<td width="75px">
			                    <div class="media">
								  <a class="pull-left" href="#" onclick="auctionImages('<%=a.getAuction_id()%>')">
								      <img class="media-object lazy" style="width:75px; " src="" data-src="image?id=<%=a.getAuction_id()%>&t=at" alt="Click to upload image" />
								      <span class="badge badge-success pull-right" style="position: relative; top: -20px; left: -2px;">
								      	<%= new ImageManager().getImageListByAuctionId(a.getAuction_id()).size() %>
								      </span>
								  </a>
								</div>
		                    </td>
                            <td width="15px"><a href="#" onclick="view('<%=a.getAuction_id()%>')"><%=a.getAuction_id()%></a></td>
                            <td width="15px"><%=a.getAuction_no()%></td>
                            <td width="15px"><%=a.getAuction_name()%></td>
                            <td><%=a.getLocation()%></td>
                            <td width="200px"><%=start_date_time%></td>
                            <td width="200px"><%=end_date_time%></td>
                                                        <td>
                            
                            
                            <%if(a!=null && a.getAuction_type()==15){%>
								Online	
							<%}else if(a!=null && a.getAuction_type()==16){%>
								Negotiated
							<%}else if(a!=null && a.getAuction_type()==185){%>
								Live
							<%}else{%>
								&nbsp;
							<%}%>
                            
                            
                            
                            </td>
                            <td><%=a.getNo_of_lots()%></td>
                            <td><%=a.getNo_of_items()%></td>
                            <td><%=active%></td> 
                            <td><%=status%></td> 
                            <td width="200px"><%=date_created%></td>
                            <td width="200px"><%=date_sync%></td>
                            
		                  </tr>    
		             <%} %>         
		                          
		                          
		                    </tbody>
		
		                    <!-- 
		
		                    <tfoot>
		
		                      <tr>
		
		                        <th>Email Address</th>
		
		                        <th>Last Name</th>
		
		                        <th>First Name</th>
		
		                        <th>Active</th>
		
		                        <th>Date Created</th>
		
		                        <th>Date Updated</th>
		
		                      </tr>
		
		                    </tfoot>  -->
		
		                  </table>
		
		                  </div><!-- /.table-responsive -->

                    </div>
                    
                    <div class="col-sm-2">
                        <a class="btn btn-theme btn-block " href="#" onclick="create()">Create</a>
                    </div>
                    <!-- 
                    <div class="col-sm-2">
                        <a class="btn btn-theme btn-block " href="#" onclick="clearLogin()">Clear</a>
                    </div>
        --->
                    
                </div>
            </div>
        </section>
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
/*
function validateLogin(){
	var isLogin = true;
	if(document.frm.userId.value==""){
		var msgInfo = "User Name or Email is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.userId.focus();
		isLogin = false;
	}else if(document.frm.pw.value==""){
		var msgInfo = "Password is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.pw.focus();
		isLogin = false;
	}
	return isLogin;
}

function clearLogin(){
	document.frm.userId.value="";
	document.frm.pw.value="";
	document.frm.userId.focus();
}

function submitPage(){
	if(validateLogin()){
		document.frm.submit();
	}
	setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);
}
*/
<%if(msgInfo!=null){%>
	
	var msgInfo = "<%=msgInfo%>";
	var msgbgcol = "red";
	var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
	msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
	msgBoxValue = msgBoxValue + '</div>';
	document.getElementById("msgDiv").innerHTML=msgBoxValue;

<%}%>


setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);

setTimeout(onLoadPage,3000);

function auctionImages(auctionId){
	document.frm.manager.value="image-manager";
	document.frm.action.value="auctionImageUpload";
	document.frm.auction_id.value=auctionId;
	document.frm.auctionId_wip.value=auctionId;
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
<script src="assets/plugins/jquery.lazy/jquery.lazy.min.js"></script>

<!-- JS Page Level -->
<script src="assets/js/theme.js"></script>

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/plugins/jquery.cookie.js"></script>
<!-- <script src="assets/js/theme-config.js"></script> -->
<!--<![endif]-->

<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.js"></script>


<script>

$( document ).ready(function() {
	$('.lazy').lazy({
		enableThrottle: true,
	    throttle: 250,
		onError: function(element) {
	        console.log('image "' + element[0]['currentSrc'] + '" could not be loaded');
	    },
	    afterLoad: function(element) {
	        var imageSrc = element.data('currentSrc');
	        console.log('image "' + element[0]['currentSrc'] + '" was loaded successfully');
	    }
	});
});

$(function () {
    $("#table1").DataTable({
    	"pageLength": 100,
      	"order": [[ 11, "desc" ]],
      	"lengthMenu": [[100, 50, 25, 5], [100, 50, 25, 5]]
    });
  });
  

function create(){
	document.getElementById("action").value="createAuction";
	document.frm.submit();
}

function view(id){
	document.getElementById("action").value="viewAuction";
	document.getElementById("auctionId_wip").value=id;
	document.frm.submit();
}
/*
function resizeTable() {
    var w = window.outerWidth;
    var h = window.outerHeight;
    var txt = "Window size: width=" + w + ", height=" + h;
    //alert(w);
    if(w > 800){
    	//document.getElementById("tableDiv1").style = "overflow-x:hidden";
    }else{
    	//document.getElementById("tableDiv1").style = "";
    }
    
}
*/
//resizeTable();
</script>

    <form action="bid" name="frm" id="frm" method="post">
       <input type="hidden" name="manager" id="manager" value="auction-manager"/>
       <input type="hidden" name="action" id="action" value=""/>
       <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
       <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
       <input type="hidden" name="auctionId_wip" id="auctionId_wip" value=""/>
       <input type="hidden" name="auction_id" id="auction_id" value=""/>
    </form>
</body>
</html>
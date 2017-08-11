<!DOCTYPE html>
<%@ page import="hmr.com.manager.ImageManager"
         import="hmr.com.bean.Image"
         import="hmr.com.bean.User"
		 import="hmr.com.bean.Auction"
		 import="hmr.com.bean.AuctionUser"
		 import="hmr.com.bean.Lov"
		 import="hmr.com.bean.Lot"
		 import="hmr.com.bean.Item"
		 
		 import="java.util.HashMap" 
		 import="java.util.List" 
		 import="java.math.BigDecimal" 
		 import="java.util.ArrayList"  
		 import="javax.servlet.RequestDispatcher"
		 import="java.text.SimpleDateFormat"
		 import="hmr.com.manager.AuctionManager"
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%
		System.out.println("asdf");
	
		String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";

		String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
		String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
		  
		  
		String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
		String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
		String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : (String)request.getSession().getAttribute("userId");
		BigDecimal mobileNo = request.getAttribute("mobileNo")!=null ? (BigDecimal)request.getAttribute("mobileNo") : null;
		  
		  
		//IDS
		Integer user_id = request.getAttribute("user-id")!=null ? (Integer)request.getAttribute("user-id") : (Integer)request.getSession().getAttribute("user-id");
		  
		Auction auction = request.getAttribute("auction")!=null ? (Auction)request.getAttribute("auction") : (Auction)request.getSession().getAttribute("auction");
		HashMap<Integer,User> coordinatorUserHM = (HashMap<Integer,User>)request.getSession().getAttribute("COORDINATOR-USER-HM");
		
		HashMap<Integer,User> bidderUserHM = request.getAttribute("BIDDER-USER-HM")!=null ? (HashMap<Integer,User>)request.getAttribute("BIDDER-USER-HM") : (HashMap<Integer,User>)request.getSession().getAttribute("BIDDER-USER-HM");
		HashMap<Integer,Lov> auctionUserStatusHM = (HashMap<Integer,Lov>) request.getSession().getAttribute("AUCTION-USER-STATUS-HM");
		
		System.out.println(" bidderUserHM : "+bidderUserHM);
		System.out.println(" auctionUserStatusHM : "+auctionUserStatusHM);
		
		
		List<AuctionUser> auList =(ArrayList<AuctionUser>) request.getAttribute("auctionUserList");
		List<AuctionUser> auListPending = new ArrayList<AuctionUser>();
		List<AuctionUser> auListRegistered = new ArrayList<AuctionUser>();
		List<AuctionUser> auListDisqualified = new ArrayList<AuctionUser>();
		
		for(AuctionUser au : auList){
			if( au.getStatus() == 26 ) {
				auListPending.add(au);
			}else if( au.getStatus() == 25 ){
				auListRegistered.add(au);
			}else if( au.getStatus() == 28 ){
				auListDisqualified.add(au);
			}
		}

		List<Lot> lList =(ArrayList<Lot>) request.getAttribute("lotList");
		List<Item> iList =(ArrayList<Item>) request.getAttribute("itemList");
		
		ArrayList<Lov> userRoleList = (ArrayList<Lov>) request.getSession().getAttribute("USER-ROLE-LIST");
		ArrayList<Lov> genderList = (ArrayList<Lov>) request.getSession().getAttribute("GENDER-LIST");
		ArrayList<Lov> userStatusList = (ArrayList<Lov>) request.getSession().getAttribute("USER-STATUS-LIST");
		
		
		//ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getSession().getAttribute("USER-COORDINATOR-LIST");
		ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getAttribute("userCoordinatorList");
		HashMap<Integer,Lov> catLev1LovHM =  (HashMap<Integer,Lov>)request.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-HM");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			
	%>
    <title><%=COMPANY_NAME%></title>

	<!-- Page: auction-private.jsp -->

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
<link href="assets/plugins/jquery-ui/themes/smoothness/jquery-ui.min.css" rel="stylesheet">
    <!-- Head Libs -->
    
    <script src="assets/plugins/modernizr.custom.js"></script>
	
	<!-- CK Editor -->
	<!-- 
	<script src="cdn.ckeditor.com/standard-4-4-3/ckeditor.js"></script> -->
	<!-- Bootstrap WYSIHTML5 -->
	    <!-- bootstrap wysihtml5 - text editor -->
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

    

    <!-- HEADER -->
	<jsp:include page="hmr-header.jsp" />
    <!-- /HEADER -->

    <!-- CONTENT AREA -->
    <div class="content-area">



        <!-- PAGE -->
        
        
 	        <form action="bid" name="frm" id="frm" method="post">
		       <input type="hidden" name="manager" id="manager" value="auction-user-manager"/>
		       <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
		       <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
		       <input type="hidden" name="auctionId_wip" id="auctionId_wip" value="<%=auction.getAuction_id()%>"/>
		       <input type="hidden" name="auction_id" id="auction_id" value="<%=auction.getAuction_id()%>"/>
		       <input type="hidden" name="action" id="action" value=""/>
		       <input type="hidden" name="auctionUserId_wip" id="auctionUserId_wip" value=""/>
		     </form>
		       
		       
        <section class="page-section color" style="padding:15px;">
        
            <div class="container">
                <div class="row" >

                    <div class="col-sm-12">
                        <h3 class="block-title"><span>Auctions  <label>Details </label></span></h3>
                        <div id="msgDiv"></div>
 					</div>


					<div class="col-sm-4">
		              <div class="form-group">
		                <label><b>Auction Name : </b><%=auction.getAuction_name()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Auction Id : </b><%=auction.getAuction_id()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Auction No : </b><%=auction.getAuction_no()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Location : </b><%=auction.getLocation()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Start Date / Time : </b>
		            <%
		            	String start_date_time = "";
		            	if(auction.getStart_date_time()!=null){
		            		start_date_time = sdf.format(auction.getStart_date_time());
		            	}
		            %>	<%=start_date_time%></label>
		              </div>
		              <div class="form-group">
		                <label><b>End Date / Time : </b>
		            <%
		            	String end_date_time = "";
		            	if(auction.getEnd_date_time()!=null){
		            		end_date_time = sdf.format(auction.getEnd_date_time());
		            	}
		            %>	<%=end_date_time%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Description : </b><%=auction.getAuction_desc()%></label>
		              </div>	
					</div>
					
					
				
				<div class="col-sm-4">
		              <div class="form-group">
		              	<%
		              	String categoryLevel1 = "";
		              		if(auction.getCategory_level_1()!=null && auction.getCategory_level_1()>0){
		              			categoryLevel1 = catLev1LovHM.get(auction.getCategory_level_1()).getName();
		              		}
		              	%>
		                <label><b>Category Level 1 : </b><%=categoryLevel1%></label>
		              </div>
		            <div class="form-group">
		              <label><b>Auction Type : </b>
							<%if(auction!=null && auction.getAuction_type()==15){%>
								Online	
							<%}else if(auction!=null && auction.getAuction_type()==16){%>
								Negotiated
							<%}else{%>
								&nbsp;
							<%}%>
		              </label>		
		            </div>
				
		            <div class="form-group">
		              <label><b>Status : </b>
							<%if(auction.getStatus()==30){%>
								Open	
							<%}else if(auction.getStatus()==31){%>
								Closed
							<%}else if(auction.getStatus()==32){%>
								For Review
							<%}else{%>
								&nbsp;
							<%}%>
		              </label>
		            </div>
		           <div class="form-group">	
		             <label><b>One Lot Per Bidder : </b>
		            <%
		            	String oneLotPerBidder = "";
		            	if(auction.getOne_lot_per_bidder() == 0){
		            		oneLotPerBidder = "No";
		            	}else if(auction.getOne_lot_per_bidder() == 1){
		            		oneLotPerBidder = "Yes";
		            	}
		            %>	<%=oneLotPerBidder %>
		             
		             </label>

		            </div>
					<div class="form-group">			
		             <label><b>Active : </b>
		            <%
		            	String active = "";
		            	if(auction.getActive() == 0){
		            		active = "No";
		            	}else if(auction.getActive() == 1){
		            		active = "Yes";
		            	}
		            %>	<%=active %>
		             </label>
		            </div>
		            <div class="form-group">
		              <label><b>Visibility : </b>
							<%if(auction.getVisibility()==33){%>
								Public	
							<%}else if(auction.getVisibility()==34){%>
								Private
							<%}else{%>
								&nbsp;
							<%}%>
		              </label>
		            </div>

		              <div class="form-group">
		                <label><b>Auction Item Increment Time : </b><%=auction.getAuction_item_increment_time()%></label>
		              </div>

				</div>
		
		
		
				<div class="col-sm-4">
					<div class="form-group">			
		             <label><b>Bid Deposit : </b>
		            <%
		            	String bid_deposit = "";
		            	if(auction.getBid_deposit() == 0){
		            		bid_deposit = "No";
		            	}else if(auction.getBid_deposit() == 1){
		            		bid_deposit = "Yes";
		            	}
		            %>	
		            <%=bid_deposit%>
		             </label>
						
		            </div>
		              <div class="form-group">
		                <label><b>Bid Deposit Amount : </b><%=auction.getBid_deposit_amount()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>No of lots: </b><%=auction.getNo_of_lots()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>No of items : </b><%=auction.getNo_of_items()%></label>
		              </div>
					<div class="form-group">			
		             <label><b>Date Sync : </b>
		            <%
		            	String date_sync = "";
		            	if(auction.getDate_sync()!=null){
		            		date_sync = sdf.format(auction.getDate_sync());
		            	}
		            %>	<%=date_sync%>
		             </label>
		            </div>
		            
		            
		            
		           <div class="form-group">	
		             <label><b>One As Start Bid : </b>
		            <%
		            	String oneStartBid = "";
		            	if(auction.getOne_start_bid() == 0){
		            		oneStartBid = "No";
		            	}else if(auction.getOne_start_bid() == 1){
		            		oneStartBid = "Yes";
		            	}
		            %>	<%=oneStartBid %>
		             
		             </label>

		            </div>
		            
		           <div class="form-group">	
		             <label><b>Bid Qualifier Price : </b>
		            <%
		            	String bid_qualifier_price = "";
		            	if(auction.getBid_qualifier_price() == 0){
		            		bid_qualifier_price = "";
		            	}else if(auction.getBid_qualifier_price() == 1){
		            		bid_qualifier_price = "Reserve Price";
		            	}else if(auction.getBid_qualifier_price() == 2){
		            		bid_qualifier_price = "SRP";
		            	}else if(auction.getBid_qualifier_price() == 3){
		            		bid_qualifier_price = "Target Price";
		            	}else if(auction.getBid_qualifier_price() == 4){
		            		bid_qualifier_price = "Assess Value Price";
		            	}

		            %>	<%=bid_qualifier_price %>
		             
		             </label>

		            </div>
		            

		            
		            
				</div>


				
				

            <div class="col-md-12">
	            <div class="form-group">
	              <label><b>Coordinator : </b> 
	              <% 
	              String coordinator = "";
	              
	              if(coordinatorUserHM.get(auction.getCoordinator())!=null){ %>
	              <%=coordinatorUserHM.get(auction.getCoordinator()).getFirst_name()%> <%=coordinatorUserHM.get(auction.getCoordinator()).getLast_name()%>
	              <% }else{ %>
	              <%=coordinator%>
	              <% } %>
	              </label>
	            </div>
	            

           </div>
           
           <div class="col-md-12">
	            <div class="form-group">
	            	<label><b>Invite URL : </b></label>
			            <%
			            String url = request.getRequestURL().toString();
			            String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
			            String token = new AuctionManager().generateAuctionPrivateToken(auction.getId());
			            %>
			            <%=baseURL %>bid?mngr=get&a=private-invite&aid=<%=token %>
	            </div>
	        </div>
           
            </div>
            </div>
        </section>
        
        
        
        <!-- PAGE -->
        <section class="page-section color" style="padding:15px;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h3 class="block-title"><span>  <label>Pending Users</label></span></h3>

 		                <div id="tableDiv3" class="box-body table-responsive">
										
						  <!-- <div id="msgDiv"></div> -->
						  			  
		                  <table id="table1" class="table table-bordered table-striped no-margin">
		
		                    <thead>
		                      <tr>
		                        <th>Email</th>
		                        <th>Last Name</th>
		                        <th>First Name</th>
		                        <th>Active</th>
		                        <th>Status</th>
		                        <th>Auction ID</th>
		                        <th>Date Created</th>
		                        <th>Date Updated</th>
		                        <th>Company ID</th>
		                        <th>Approve?</th>
		                        
		                      </tr>
		                    </thead>
		                    <tbody>
                      <% for(AuctionUser au : auListPending){
                            String date_created = "";
                            if(au.getDate_created()!=null){
                            	date_created = sdf.format(au.getDate_created());
                            }

                            String date_updated = "";
                            if(au.getDate_updated()!=null){
                            	date_updated = sdf.format(au.getDate_updated());
                            }
                            
                          
                            active = "Yes";
                            if(au.getActive()==0){
                            	active = "No";
                            }
                            
    		            	String auctionUserStatus = "";
    		            	if(auctionUserStatusHM.get( au.getStatus())!=null){
    		            		auctionUserStatus = auctionUserStatusHM.get(au.getStatus()).getValue();
    		            	}
                            
                            
                            String email = "";
                            String ln = "";
                            String fn = "";
                            if(bidderUserHM.get(au.getUser_id())!=null){
                            	email = bidderUserHM.get(au.getUser_id()).getEmail_address();
                            	ln = bidderUserHM.get(au.getUser_id()).getLast_name();
                            	fn = bidderUserHM.get(au.getUser_id()).getFirst_name();
                            }
                            
                            String company_id_no = au.getCompany_id_no() != null ? au.getCompany_id_no() : "";
                            
                            
                      %>
		                  <tr>
                            <td><a href="#" onclick="viewAuctionUser('<%=au.getId()%>')"><%=au.getEmail()%></a></td>
                            <td><%=au.getLastname()%></td>
                            <td><%=au.getFirstname()%></td>
                            <td><%=active%></td> 
                            <td><%=auctionUserStatus%></td> 
                            <td><%=au.getAuction_id()%></td> 
                            <td><%=date_created%></td>
                            <td><%=date_updated%></td>
                            <td><%=company_id_no%></td>
                            <td>
	                            <div class="input-group">
	  								<div class="input-group-btn">
		                            	<button type="button" class="btn btn-labeled btn-success btn-xs" onclick="userAction('USER-APPROVE', '<%=email%>','<%=au.getId()%>', '<%=fn %> <%=ln %>')">
		                					<span class="btn-label"><i class="glyphicon glyphicon-ok"></i></span>&nbsp;Yes</button>
		            					<button type="button" class="btn btn-labeled btn-danger btn-xs" onclick="userAction('USER-REJECT', '<%=email%>','<%=au.getId()%>', '<%=fn %> <%=ln %>')">
		                					<span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>&nbsp;No</button>
	                            	</div>
	                            </div>
                            </td>
		                  </tr>    
		             <%} %>         
		                          
		                          
		                    </tbody>
		
		                    		
		                  </table>
		
		                  </div><!-- /.table-responsive -->

                    </div>
                    
                  
                    
                </div>
            </div>
        </section>
        <!-- /PAGE -->
        
        


        <!-- PAGE -->
        <section class="page-section color" style="padding:15px;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h3 class="block-title"><span>  <label>Registered Users </label></span></h3>

 		                <div id="tableDiv3" class="box-body table-responsive">
										
						  <!-- <div id="msgDiv"></div> -->
						  			  
		                  <table id="table2" class="table table-bordered table-striped no-margin">
		
		                    <thead>
		                      <tr>
		                        <th>Email</th>
		                        <th>Last Name</th>
		                        <th>First Name</th>
		                        <th>Active</th>
		                        <th>Status</th>
		                        <th>Auction ID</th>
		                        <th>Date Created</th>
		                        <th>Date Updated</th>
		                        <th>Company ID</th>
		                        <th>Option</th>
		                      </tr>
		                    </thead>
		                    <tbody>
                      <% for(AuctionUser au : auListRegistered){
                            String date_created = "";
                            if(au.getDate_created()!=null){
                            	date_created = sdf.format(au.getDate_created());
                            }

                            String date_updated = "";
                            if(au.getDate_updated()!=null){
                            	date_updated = sdf.format(au.getDate_updated());
                            }
                            
                          
                            active = "Yes";
                            if(au.getActive()==0){
                            	active = "No";
                            }
                            
    		            	String auctionUserStatus = "";
    		            	if(auctionUserStatusHM.get( au.getStatus())!=null){
    		            		auctionUserStatus = auctionUserStatusHM.get(au.getStatus()).getValue();
    		            	}
                            
                            
                            String email = "";
                            String ln = "";
                            String fn = "";
                            if(bidderUserHM.get(au.getUser_id())!=null){
                            	email = bidderUserHM.get(au.getUser_id()).getEmail_address();
                            	ln = bidderUserHM.get(au.getUser_id()).getLast_name();
                            	fn = bidderUserHM.get(au.getUser_id()).getFirst_name();
                            }
                            
                            String company_id_no = au.getCompany_id_no() != null ? au.getCompany_id_no() : "";
                            
                            
                      %>
		                  <tr>
                            <td><a href="#" onclick="viewAuctionUser('<%=au.getId()%>')"><%=au.getEmail()%></a></td>
                            <td><%=au.getLastname()%></td>
                            <td><%=au.getFirstname()%></td>
                            <td><%=active%></td> 
                            <td><%=auctionUserStatus%></td> 
                            <td><%=au.getAuction_id()%></td> 
                            <td><%=date_created%></td>
                            <td><%=date_updated%></td>
                            <td><%=company_id_no %></td>
                            <td>
	                            <div class="input-group">
	  								<div class="input-group-btn">
		                            	<button type="button" class="btn btn-labeled btn-warning btn-xs" onclick="userAction('USER-APPROVE-TO-PENDING', '<%=email%>','<%=au.getId()%>', '<%=fn %> <%=ln %>')">
		                					<span class="btn-label"><i class="glyphicon glyphicon-repeat"></i></span>&nbsp;Reset</button>
	                            	</div>
	                            </div>
                            </td>
		                  </tr>    
		             <%} %>         
		                          
		                          
		                    </tbody>
		
		                    		
		                  </table>
		
		                  </div><!-- /.table-responsive -->

                    </div>
                    
                </div>
            </div>
        </section>
        <!-- /PAGE -->
        
        <!-- PAGE -->
        <section class="page-section color" style="padding:15px;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h3 class="block-title"><span>  <label>Rejected Users </label></span></h3>

 		                <div id="tableDiv3" class="box-body table-responsive">
										
						  <!-- <div id="msgDiv"></div> -->
						  			  
		                  <table id="table3" class="table table-bordered table-striped no-margin">
		
		                    <thead>
		                      <tr>
		                        <th>Email</th>
		                        <th>Last Name</th>
		                        <th>First Name</th>
		                        <th>Active</th>
		                        <th>Status</th>
		                        <th>Auction ID</th>
		                        <th>Date Created</th>
		                        <th>Date Updated</th>
		                        <th>Company ID</th>
		                        <th>Option</th>
		                      </tr>
		                    </thead>
		                    <tbody>
                      <% for(AuctionUser au : auListDisqualified){
                            String date_created = "";
                            if(au.getDate_created()!=null){
                            	date_created = sdf.format(au.getDate_created());
                            }

                            String date_updated = "";
                            if(au.getDate_updated()!=null){
                            	date_updated = sdf.format(au.getDate_updated());
                            }
                            
                          
                            active = "Yes";
                            if(au.getActive()==0){
                            	active = "No";
                            }
                            
    		            	String auctionUserStatus = "";
    		            	if(auctionUserStatusHM.get( au.getStatus())!=null){
    		            		auctionUserStatus = auctionUserStatusHM.get(au.getStatus()).getValue();
    		            	}
                            
                            
                            String email = "";
                            String ln = "";
                            String fn = "";
                            if(bidderUserHM.get(au.getUser_id())!=null){
                            	email = bidderUserHM.get(au.getUser_id()).getEmail_address();
                            	ln = bidderUserHM.get(au.getUser_id()).getLast_name();
                            	fn = bidderUserHM.get(au.getUser_id()).getFirst_name();
                            }
                            
                            String company_id_no = au.getCompany_id_no() != null ? au.getCompany_id_no() : "";
                            
                            
                      %>
		                  <tr>
                            <td><a href="#" onclick="viewAuctionUser('<%=au.getId()%>')"><%=au.getEmail()%></a></td>
                            <td><%=au.getLastname()%></td>
                            <td><%=au.getFirstname()%></td>
                            <td><%=active%></td> 
                            <td><%=auctionUserStatus%></td> 
                            <td><%=au.getAuction_id()%></td> 
                            <td><%=date_created%></td>
                            <td><%=date_updated%></td>
                            <td><%=company_id_no %></td>
                            <td>
	                            <div class="input-group">
	  								<div class="input-group-btn">
		                            	<button type="button" class="btn btn-labeled btn-warning btn-xs" onclick="userAction('USER-REJECTED-TO-PENDING', '<%=email%>','<%=au.getId()%>', '<%=fn %> <%=ln %>')">
		                					<span class="btn-label"><i class="glyphicon glyphicon-repeat"></i></span>&nbsp;Reset</button>
	                            	</div>
	                            </div>
                            </td>
		                  </tr>    
		             <%} %>         
		                          
		                          
		                    </tbody>
		
		                    		
		                  </table>
		
		                  </div><!-- /.table-responsive -->

                    </div>
                    
                    <div class="col-sm-2">
                        <a class="btn btn-theme btn-block " href="bid?mngr=auction-manager&a=auctionList&uid=<%=userId %>">Auctions</a>
                    </div>

                    
                </div>
            </div>
        </section>
        <!-- /PAGE -->
      
        
        <!-- /PAGE -->
        
    </div>
    <!-- /CONTENT AREA -->

    <!-- FOOTER -->
	<jsp:include page="hmr-footer.jsp" />
    <!-- /FOOTER -->

    <div id="to-top" class="to-top" style="background-color: #93bcff"><i class="fa fa-angle-up"></i></div>

</div>
<!-- /WRAPPER -->



<!-- /JS Local -->


<!-- JS Global -->
<script src="assets/plugins/jquery/jquery-2.0.0.min.js"></script>
<script src="assets/plugins/jquery-ui/jquery-ui-1.11.1.min.js"></script>
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
<!--<![endif]-->

<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.js"></script>

<script src="js/jquery.datetimepicker.full.js"></script>
<script type="text/javascript" src="js/select2.full.js"></script>

<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>


<script>

function userAction(action, email, user_Id, userName) {

	$('input[name="manager"]').val("auction-manager");
	$('input[name="action"]').val(action);
	$('input[name="auctionUserId_wip"]').val(user_Id);

	
	$('<div id="dialog-confirm"></div>').dialog({
		resizable: false,
	    height: "auto",
	    width: 400,
	    modal: true,
	    title: "Confirmation",
	    closeOnEscape: false,
        open: function (event, ui) {
        	var dialog_title = "Confirmation";
        	var currency_html = "PHP";
        	var dialog_html = '<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>Are you sure?</p>';

        	
        	$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
      
        	if(action=="USER-APPROVE") {
        		dialog_title = "Approve confirmation";
        		dialog_html = '<p>You are approving the request of '+ userName +' &lt;'+email+'&gt;'+' as bidder for this private auction.</p>'+
        			'<p>Are you sure?</p>';
        	}else if(action=="USER-REJECT") {
        		dialog_title = "Reject confirmation";
        		dialog_html = '<p>You are rejecting the request of '+ userName +' &lt;'+email+'&gt;'+' as bidder for this private auction.</p>'+
        			'<p>Are you sure?</p>';
        	}else if(action=="USER-APPROVE-TO-PENDING") {
        		dialog_title = "Reset confirmation";
        		dialog_html = '<p>You are resetting the approval of '+ userName +' &lt;'+email+'&gt;'+' as bidder for this private auction.</p>'+
        		    '<p><strong>This user will no longer be able to bid on this auction!</strong></p>' +
        			'<p>Are you sure?</p>';
        	}else if(action=="USER-REJECTED-TO-PENDING") {
        		dialog_title = "Reset confirmation";
        		dialog_html = '<p>You are resetting the rejection of '+ userName +' &lt;'+email+'&gt;'+' as bidder for this private auction.</p>'+
        			'<p><strong>After reset, you can then approve this user as a bidder!</strong></p>' +
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
    }).dialog('widget').position({ my: 'center', at: 'center', of: $(this) }); //end confirm dialog
}




 $(document).ready(function() {
	 <% if(msgInfo!=null){ %>
		
		var msgInfo = "<%=msgInfo%>";
		var msgbgcol = "<%=msgbgcol%>";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;

		<% }%>


	setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);


	
	$("#table1").DataTable({
    	"pageLength": 100,
      	"order": [[ 1, "asc" ]],
      	"lengthMenu": [[100, 50, 25, 5], [100, 50, 25, 5]]
    });
    $("#table2").DataTable({
    	"pageLength": 100,
      	"order": [[ 1, "asc" ]],
      	"lengthMenu": [[100, 50, 25, 5], [100, 50, 25, 5]]
    });
    $("#table3").DataTable({
    	"pageLength": 100,
      	"order": [[ 1, "asc" ]],
      	"lengthMenu": [[100, 50, 25, 5], [100, 50, 25, 5]]
    });

 });
	

  
</script>

</body>
</html>
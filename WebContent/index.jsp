<!DOCTYPE html>
<%@ page import="hmr.com.bean.Auction"
		 import="java.util.List"  
		 import="java.text.SimpleDateFormat"
		 import="java.math.BigDecimal"
		 import="java.sql.Timestamp"
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
	
	List<Auction> activeLiveAuctionList = request.getAttribute("ACTIVE-LIVE-AUCTION-LIST")!=null ? (List<Auction>)request.getAttribute("ACTIVE-LIVE-AUCTION-LIST") : (List<Auction>)request.getSession().getAttribute("ACTIVE-LIVE-AUCTION-LIST");
	
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm");
	SimpleDateFormat sdfTimer = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
%>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <jsp:include page="includes/header-meta.jsp"></jsp:include>
                
    </head>
    <body data-is-mobile="" id="c" >
    
    <input type="hidden" id="base_url" value="">
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

<jsp:include page="includes/header.jsp"></jsp:include>

	
<div id="ms--main--body">
	

	
<section id="hmr-main-container">
	<div class="container">
		<div class="row gutter-10">
			<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
			
			<div class="col-md-4 col-lg-3">
				<jsp:include page="includes/side-widget.jsp"></jsp:include>
			</div>
			
			<div class="col-md-8 col-lg-9">
				<jsp:include page="includes/index-slider.jsp"></jsp:include>
				
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
										<a href="#negotiated-bids-tab" aria-controls="negotiated-bids-tab" role="tab" data-toggle="tab">Buy Now</a>
									</li>
									<li role="presentation">
										<a href="#live-bids-tab" aria-controls="live-bids-tab" role="tab" data-toggle="tab">Live Auctions</a>
									</li>
								</ul>
							
								<!-- Tab panes -->
								<div class="tab-content">
									<div role="tabpanel" class="tab-pane active" id="online-bidding-tab">
										<div id="online-bidding-slider" class="owl-carousel featured-product-carousel">
											<%for(Auction activeOnlineAuction : activeOnlineAuctionList) {%>
											<div class="owl-slide">
												<div class="hmr-card-wrap">
													<div class="card-image-wrap ribbon-wrap">
														<div class="image feature-fade-in owl-lazy" data-src="image?id=<%=activeOnlineAuction.getAuction_id()%>&t=at"></div>
														<% if(activeOnlineAuction.getVisibility()==34) {%>
														<div class="ribbon">Private</div>
														<% } %>
				 									</div>
													<div class="card-body-wrap">
														<h3 class="card-title">
															<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeOnlineAuction.getId()%>">
																<%=activeOnlineAuction.getAuction_name()%>
															</a>
														</h3>

														<div class="card-snippet-wrap">
															<%=activeOnlineAuction.getAuction_desc() %>
														</div>
														<div class="card-snippet-wrap">
															Location: <%=activeOnlineAuction.getLocation()%>
														</div>
														<% if(activeOnlineAuction.getStart_date_time().after(new Timestamp(System.currentTimeMillis()))) {  %>
															<div class="card-snippet-wrap">
																Start: <%=sdf.format(activeOnlineAuction.getStart_date_time()) %>
															</div>
															<div class="card-snippet-wrap">
																End: <%=sdf.format(activeOnlineAuction.getEnd_date_time()) %>
															</div>
															<div class="card-snippet-wrap">
																Upcoming Bidding
															</div>
															<div class="card-snippet-wrap">
																<div class="countdown" id="timer-<%=activeOnlineAuction.getId() %>" 
																	data-startdate="<%=sdfTimer.format(activeOnlineAuction.getStart_date_time())%>" 
																	data-enddate="<%=sdfTimer.format(activeOnlineAuction.getEnd_date_time()) %>">Starts
																</div>
															</div>
														<% }else if (activeOnlineAuction.getEnd_date_time().after(new Timestamp(System.currentTimeMillis()))) { %>
															<div class="card-snippet-wrap">
																Closing: <%=sdf.format(activeOnlineAuction.getEnd_date_time()) %>
															</div>
															<div class="card-snippet-wrap">
																Accepting Bids
															</div>
															<div class="card-snippet-wrap">
															Ends
																<div class="countdown" id="timer-<%=activeOnlineAuction.getId()%>" 
																	data-startdate="<%=sdfTimer.format(activeOnlineAuction.getStart_date_time()) %>" 
																	data-enddate="<%=sdfTimer.format(activeOnlineAuction.getEnd_date_time()) %>">
																</div>
															</div>
														<% } else { %>
															<div class="card-snippet-wrap">
																Completed
															</div>
														<% } %>
															
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
													<div class="card-image-wrap ribbon-wrap">
														<div class="image feature-fade-in owl-lazy" data-src="image?id=<%=activeNegotiatedAuction.getAuction_id()%>&t=at"></div>
														<% if(activeNegotiatedAuction.getVisibility()==34) {%>
														<div class="ribbon">Private</div>
														<% } %>
													</div>
													<div class="card-body-wrap">
														<h3 class="card-title">
															<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeNegotiatedAuction.getId()%>">
																<%=activeNegotiatedAuction.getAuction_name()%>
															</a>
														</h3>
														<div class="card-snippet-wrap">
															<%=activeNegotiatedAuction.getAuction_desc() %>
														</div>
														<div class="card-snippet-wrap">
															<%=activeNegotiatedAuction.getLocation()%>
														</div>
														
														<% if(activeNegotiatedAuction.getStart_date_time().after(new Timestamp(System.currentTimeMillis()))) {  %>
															<div class="card-snippet-wrap">
																Start: <%=sdf.format(activeNegotiatedAuction.getStart_date_time()) %>
															</div>
															<div class="card-snippet-wrap">
																Upcoming Bidding
															</div>
															<div class="card-snippet-wrap">
																<div class="countdown" id="timer-<%=activeNegotiatedAuction.getId() %>" 
																	data-startdate="<%=sdfTimer.format(activeNegotiatedAuction.getStart_date_time()) %>" 
																	data-enddate="<%=sdfTimer.format(activeNegotiatedAuction.getEnd_date_time()) %>">
																</div>
															</div>
																
														<% }else if (activeNegotiatedAuction.getEnd_date_time().after(new Timestamp(System.currentTimeMillis()))) { %>
															<div class="card-snippet-wrap">
																Closing: <%=sdf.format(activeNegotiatedAuction.getEnd_date_time()) %>
															</div>
															<div class="card-snippet-wrap">
																Accepting Bids
															</div>
															<div class="card-snippet-wrap">
																<div class="countdown" id="timer-<%=activeNegotiatedAuction.getId() %>" 
																	data-startdate="<%=sdfTimer.format(activeNegotiatedAuction.getStart_date_time()) %>" 
																	data-enddate="<%=sdfTimer.format(activeNegotiatedAuction.getEnd_date_time()) %>">
																</div>
															</div>
														<% } else { %>
															<div class="card-snippet-wrap">
																Completed
															</div>
														<% } %>
														
														<div class="card-action-btns">
															<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeNegotiatedAuction.getId()%>" class="btn btn-sm btn-warning">View Auction</a>
														</div>
													</div>
												</div>
											</div>
											<% } %>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane" id="live-bids-tab">
										<div id="live-bidding-slider" class="owl-carousel featured-product-carousel">
											<%for(Auction activeLiveAuction : activeLiveAuctionList) {%>
											<div class="owl-slide">
												<div class="hmr-card-wrap">
													<div class="card-image-wrap ribbon-wrap">
														<div class="image feature-fade-in owl-lazy" data-src="image?id=<%=activeLiveAuction.getAuction_id()%>&t=at"></div>
														<% if(activeLiveAuction.getVisibility()==34) { %>
														<div class="ribbon">Private</div>
														<% } %>
													</div>
													<div class="card-body-wrap">
														<h3 class="card-title">
															<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId%>&aid=<%=activeLiveAuction.getId()%>">
																<%=activeLiveAuction.getAuction_name()%>
															</a>
														</h3>
														<div class="card-snippet-wrap">
															<%=activeLiveAuction.getAuction_desc() %>
														</div>
														<div class="card-snippet-wrap">
															<%=activeLiveAuction.getLocation()%>
														</div>
														
														<% if(activeLiveAuction.getStart_date_time().after(new Timestamp(System.currentTimeMillis()))) {  %>
															<div class="card-snippet-wrap">
																Start: <%=sdf.format(activeLiveAuction.getStart_date_time()) %>
															</div>
															<div class="card-snippet-wrap">
																Upcoming Bidding
															</div>
															<div class="card-snippet-wrap">
																<div class="countdown" id="timer-<%=activeLiveAuction.getId() %>" 
																	data-startdate="<%=sdfTimer.format(activeLiveAuction.getStart_date_time()) %>" 
																	data-enddate="<%=sdfTimer.format(activeLiveAuction.getEnd_date_time()) %>">
																</div>
															</div>
														<% }else if (activeLiveAuction.getEnd_date_time().after(new Timestamp(System.currentTimeMillis()))) { %>
															<div class="card-snippet-wrap">
																Closing: <%=sdf.format(activeLiveAuction.getEnd_date_time()) %>
															</div>
															<div class="card-snippet-wrap">
																Accepting Bids
															</div>
															<div class="card-snippet-wrap">
																<div class="countdown" id="timer-<%=activeLiveAuction.getId() %>" 
																	data-startdate="<%=sdfTimer.format(activeLiveAuction.getStart_date_time()) %>" 
																	data-enddate="<%=sdfTimer.format(activeLiveAuction.getEnd_date_time()) %>">
																</div>
															</div>
														<% } else { %>
															<div class="card-snippet-wrap">
																Completed
															</div>
														<% } %>
														
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

<jsp:include page="includes/footer.jsp"></jsp:include>
<jsp:include page="includes/footer-meta.jsp"></jsp:include>


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
	
	$('.countdown').each(function() {
		var $this = $(this);
		
		var sDate = $(this).data('startdate');
		var eDate = $(this).data('enddate');
		var startDate = new Date(sDate);
		var endDate  = new Date(eDate);
		var today = new Date();
		var targetDate;
		  
		if(today < startDate) {
			targetDate = $(this).data('startdate');
		} else if (today <= endDate) {
			targetDate = $(this).data('enddate');
		} 
		$this.countdown(targetDate, function(event) {
			var totalHours = event.offset.totalDays * 24 + event.offset.hours;
			if(totalHours < 24) {
				$(this).html(event.strftime(totalHours + ' hr %M min %S sec'));
			} else {
				$this.html(event.strftime('%D days %H:%M:%S'));
			}
		});
	});

	
});





</script>
</body>
</html>

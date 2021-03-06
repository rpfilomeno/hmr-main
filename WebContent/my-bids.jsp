<!DOCTYPE html>
<%@page import="hmr.com.manager.LotManager"%>
<%@page import="hmr.com.manager.BiddingTransactionManager"%>
<%@page import="hmr.com.manager.AuctionManager"%>
<%@ page import="hmr.com.bean.Auction"
		 import="hmr.com.bean.Lot"
		 import="hmr.com.bean.Image"
		 import="hmr.com.bean.Lov"
		 import="hmr.com.bean.Item"
		 import="hmr.com.bean.BiddingTransaction"
		 import="java.util.List"  
		 import="java.math.BigDecimal"  
		 import="java.util.HashMap"  
		 import="java.text.DecimalFormat"
		 import="java.text.SimpleDateFormat"
		 import="java.sql.Timestamp"
		 import="java.math.BigDecimal"

%>
<%
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

	List<Lot> lList = request.getAttribute("lList")!=null ? (List<Lot>)request.getAttribute("lList") : (List<Lot>)request.getSession().getAttribute("lList");
	HashMap<Integer, Lov> currencyLovHM  = request.getAttribute("CURRENCY-HM")!=null ? (HashMap<Integer, Lov>)request.getAttribute("CURRENCY-HM") : (HashMap<Integer, Lov>)request.getSession().getAttribute("CURRENCY-HM");
	
	//HashMap<BigDecimal, Lot> lotHM  = request.getAttribute("lotHM")!=null ? (HashMap<BigDecimal, Lot>)request.getAttribute("lotHM") : (HashMap<BigDecimal, Lot>)request.getSession().getAttribute("lotHM");
	//List<Item> iList = request.getAttribute("iList")!=null ? (List<Item>)request.getAttribute("iList") : (List<Item>)request.getSession().getAttribute("iList");

    DecimalFormat df = new DecimalFormat("#,###,##0");
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy  HH:mm");
	
	
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
	






	<div class="hmr-breadcrumb">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li>
						<a href="">Home</a>
					</li>
				</ol>
			</div>
		</div>
	</div>
</div>

<section id="hmr-main-container">
	<div class="container">
		<div class="row">
			<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
			
			<div class="col-md-4 col-lg-3">
				<jsp:include page="includes/side-widget.jsp"></jsp:include>
			
			</div>
			
			<div class="col-md-8 col-lg-9">
				
				<div id="product-list-wrapper">
					<ul class="nav nav-tabs nav-tabs-2" role="tablist">
						<li role="presentation" class="active">
							<a href="#online-bidding-tab" aria-controls="lots-tab" role="tab" data-toggle="tab">My Bids</a>
						</li>
					</ul>
					
					<ul class="nav nav-tabs nav-tabs-2" role="tablist">
						<li role="presentation" class="">
							<a href="#checkout-tab" aria-controls="lots-tab" role="tab" data-toggle="tab">My Checkout</a>
						</li>
					</ul>
					<div class="clearfix top10"></div>

					<div class="row gutter-10">
						<% 
						Integer x =1; 
						Auction auction = null;
						BigDecimal trapOneLotPerBidder= BigDecimal.ZERO;
						BiddingTransactionManager btMngr = new BiddingTransactionManager();
						%>
						<%for(Lot l : lList) {%>
							<%  
							auction = new AuctionManager().getAuctionByAuctionId(l.getAuction_id()); 
							trapOneLotPerBidder= BigDecimal.ZERO;
							
							//get the last bidder
							List<BiddingTransaction> btList = btMngr.getLatestBiddingTransactionLotId(l.getLot_id());
							if(!btList.isEmpty()){
								l.setLastBidder(btList.get(0).getUser_id());
							}
							
							//get all lots on same auction
							List<Lot> lotList = new LotManager().getLotListByAuctionId(auction.getAuction_id());
							for(Lot lot : lotList){
								if(btMngr.hasBiddingTransactionByLotIdAndUserId(lot.getLot_id(), user_id)){
									if(trapOneLotPerBidder.compareTo(BigDecimal.ZERO)==0)trapOneLotPerBidder = lot.getLot_id();
								}
							}
							
							%>
							<div class="col-md-6 col-xs-12 grid-item">
								<div class="product-item">
										<div class="product-image-wrap">
											<a href="#" onclick="viewLot('<%=l.getId()%>')">
											<img style="width:100%" class="lazy" data-original="image?id=<%=l.getId()%>&t=l">
											</a>
										</div>
										<div class="clearfix top10"></div>
										<div class="product-body">
											<% if(user_id != null && user_role_id > 0){ %>
												<% if(l.getIsFav() == 1){ %>
													<div class="glyphicon glyphicon-heart favorite active" onclick="favs('UNFAV','<%=l.getLot_id()%>','<%=l.getId()%>','<%=auction.getId() %>')"></div>
												<% } else { %>
													<div class="glyphicon glyphicon-heart favorite" onclick="favs('FAV','<%=l.getLot_id()%>','<%=l.getId()%>','<%=auction.getId() %>')"></div>
												<% } %>
											<% } %>
											<div>
											<h3 class="product-name">#<%=l.getLot_no()%> : <%=l.getLot_name()%></h3>
											</div>
											<div class="product-details">
												<% if (auction.getAuction_type()== 15) { %>
													<div class="product-detail">Description: <%=l.getLot_desc()%></div>
												<%if(l.getAmount_bid().doubleValue() >=  1 ){%>
												<div class="product-detail product-price">Asking Price: <%=df.format(l.getAmount_bid_next())%> <%=currency%></div>
												<%}else{%>
												<div class="product-detail product-price">Asking Price: <%=df.format(l.getStarting_bid_amount())%> <%=currency%></div>
												<%}%>
													<div class="product-detail">Highest Bid: <%=df.format(l.getAmount_bid())%> <%=currency%></div>
													<div class="product-detail">Bids: <%=l.getBid_count()%></div>
												<% } else if (auction.getAuction_type() == 16) { %>
													<div class="product-detail">Description: <%=l.getLot_desc()%></div>
													<div class="product-detail product-price">Buy Price: <%=df.format(l.getBuy_price())%> <%=currency%></div>
													<div class="product-detail">Highest Offer: <%=df.format(l.getAmount_bid())%> <%=currency%></div>
													<div class="product-detail">Offers: <%=l.getBid_count()%></div>
												<% } %>
											</div>
												
										</div>
										<div class="clearfix top10"></div>
										
										<div class="">
											<% if(user_id != null && user_role_id > 0){ %>
												<% if( 	(trapOneLotPerBidder.compareTo(BigDecimal.ZERO) ==0 && auction.getOne_lot_per_bidder()==1) || 
														(trapOneLotPerBidder.compareTo(l.getLot_id()) == 0 && auction.getOne_lot_per_bidder()==1) ||
														auction.getOne_lot_per_bidder()==0) 
												{ %>
					                                <% if( l.getIs_available_lot() > 0) { %>
							                            <% Integer i = l.getUnit_qty(); %>
									                    <div class="form-group">
									                        <select class="form-control" id="qty_<%=l.getId()%>" name="qty_<%=l.getId()%>">
									                            <% while(i > 0) { %>
							                                   		<option value="<%=i%>"><%=i%> unit<% if(i>1){ %>s<%}%></option>
							                                   	 	<% i = i - 1; %>
							                                   	<% } %>
							                                </select>
							                            </div>
							                        <% } %>
						                        	<div class="form-group">
							                        <% if(l.getLastBidder()==null || !l.getLastBidder().equals(user_id) ){  %>
						                                <% if(l.getIs_bid() == 1){ %>
								                            <% if(auction.getAuction_type() == 15){ %>
									                            <% if(auction.getStart_date_time().after(new Timestamp(System.currentTimeMillis())) && l.getActive()>0){ %>
									                                <button class="btn btn-primary btn-block" onclick="showPreBidForm('PRE-BID', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">PRE-BID</button>
									                            <% } else { %>
									                            	<%if(l.getAmount_bid().doubleValue() > 0){ %>
										                            <button class="btn btn-primary btn-block" onclick="submitPage('BID', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','')">BID <%=df.format(l.getAmount_bid_next())%> <%=currency%></button>
										                            <%}else if(l.getAmount_bid().doubleValue() == 0){ %>
										                            <button class="btn btn-primary btn-block" onclick="submitPage('BID', '<%=l.getStarting_bid_amount()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','')">BID <%=df.format(l.getStarting_bid_amount())%> <%=currency%></button>
										                             <% } %>
										                            <button class="btn btn-primary btn-block" onclick="showMaxBidForm('SET-MAXIMUM-BID', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">SET MAX BID</button>
						                            				<%if(auction.getAuction_id().equals(new BigDecimal("797")) || auction.getAuction_id().equals(new BigDecimal("804"))){  %>
										                            	<div class="product-detail product-price" style="font-size: 12px; font-weight: bold;">* All prices are subject to 12% VAT</div>
										                            <% } %>
									                            <% } %>
								                            <% } else if(auction.getAuction_type() == 16){ %>
								                                <button class="btn btn-primary btn-block" onclick="showNegotiatedBidForm('NEGOTIATED', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">MAKE OFFER</button>
								                            <% } %>
						                                <% }%>
						                                <% if(l.getIs_buy() == 1){ %>
						                                	<button class="btn btn-primary btn-block" onclick="submitPage('BUY', '<%=l.getBuy_price()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','')">BUY <%=df.format(l.getBuy_price())%> <%=currency%></button>
						                                <% }%> 
						                            <% } else if(l.getLastBidder()!=null || l.getLastBidder().equals(user_id) ){  %>
						                            	<% if(l.getIs_bid() == 1){ %>
						                            		<button class="btn btn-primary btn-block" >YOU BID <%=df.format(l.getAmount_bid())%></button>
						                            		<button class="btn btn-primary btn-block" onclick="showMaxBidForm('SET-MAXIMUM-BID', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">SET MAX BID</button>
						                            				<%if(auction.getAuction_id().equals(new BigDecimal("797")) || auction.getAuction_id().equals(new BigDecimal("804"))){  %>
										                            	<div class="product-detail product-price" style="font-size: 12px; font-weight: bold;">* All prices are subject to 12% VAT</div>
										                            <% } %>
						                            	<% } %>  
						                            	<% if(l.getIs_buy() == 1){ %>
						                            		<button class="btn btn-primary btn-block" >YOUR OFFER</button>
						                            	<% } %>
						                            <% } %>
						                               
					                                </div>
					                            <% } else { %>
					                            		
					                                <% if( l.getIs_available_lot() > 0) { %>
							                            <% Integer i = l.getUnit_qty(); %>
									                    <div class="form-group">
									                        <select class="form-control" id="qty_<%=l.getId()%>" name="qty_<%=l.getId()%>">
									                            <% while(i > 0) { %>
							                                   		<option value="<%=i%>"><%=i%> unit<% if(i>1){ %>s<%}%></option>
							                                   	 	<% i = i - 1; %>
							                                   	<% } %>
							                                </select>
							                            </div>
							                        <% } %>
						                        	<div class="form-group">
							                        <% if(l.getLastBidder()==null || !l.getLastBidder().equals(user_id) ){  %>
						                                <% if(l.getIs_bid() == 1){ %>
								                            <% if(auction.getAuction_type() == 15){ %>
									                            <% if(auction.getStart_date_time().after(new Timestamp(System.currentTimeMillis())) && l.getActive()>0){ %>
									                                <button class="btn btn-primary btn-block" onclick="showAlertPage('BID-ONE-LOT', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">PRE-BID</button>
									                            <% } else { %>
									                            	<%if(l.getAmount_bid().doubleValue() > 0){ %>
										                            <button class="btn btn-primary btn-block" onclick="showAlertPage('BID-ONE-LOT', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','')">BID <%=df.format(l.getAmount_bid_next())%> <%=currency%></button>
										                            <%}else if(l.getAmount_bid().doubleValue() == 0){ %>
										                            <button class="btn btn-primary btn-block" onclick="showAlertPage('BID-ONE-LOT', '<%=l.getStarting_bid_amount()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','')">BID <%=df.format(l.getStarting_bid_amount())%> <%=currency%></button>
										                             <% } %>
										                            <button class="btn btn-primary btn-block" onclick="showAlertPage('BID-ONE-LOT', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">SET MAX BID</button>
						                            				<%if(auction.getAuction_id().equals(new BigDecimal("797")) || auction.getAuction_id().equals(new BigDecimal("804"))){  %>
										                            	<div class="product-detail product-price" style="font-size: 12px; font-weight: bold;">* All prices are subject to 12% VAT</div>
										                            <% } %>
									                            <% } %>
								                            <% } else if(auction.getAuction_type() == 16){ %>
								                                <button class="btn btn-primary btn-block" onclick="showAlertPage('BID-ONE-LOT', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">MAKE OFFER</button>
								                            <% } %>
						                                <% }%>
						                                <% if(l.getIs_buy() == 1){ %>
						                                	<button class="btn btn-primary btn-block" onclick="showAlertPage('BID-ONE-LOT', '<%=l.getBuy_price()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','')">BUY <%=df.format(l.getBuy_price())%> <%=currency%></button>
						                                <% }%> 
						                            <% } else if(l.getLastBidder()!=null || l.getLastBidder().equals(user_id) ){  %>
						                            	<% if(l.getIs_bid() == 1){ %>
						                            		<button class="btn btn-primary btn-block" >YOU BID <%=df.format(l.getAmount_bid())%></button>
						                            		<button class="btn btn-primary btn-block" onclick="showAlertPage('BID-ONE-LOT', '<%=l.getAmount_bid_next()%>','<%=l.getLot_id()%>','<%=l.getId()%>','qty_<%=l.getId()%>','qty_<%=l.getId()%>')">SET MAX BID</button>
						                            				<%if(auction.getAuction_id().equals(new BigDecimal("797")) || auction.getAuction_id().equals(new BigDecimal("804"))){  %>
										                            	<div class="product-detail product-price" style="font-size: 12px; font-weight: bold;">* All prices are subject to 12% VAT</div>
										                            <% } %>
						                            	<% } %>  
						                            	<% if(l.getIs_buy() == 1){ %>
						                            		<button class="btn btn-primary btn-block" >YOUR OFFER</button>
						                            	<% } %>
						                            <% } %>
					                            	</div>
					                            <% } %>
					                        <% }else if(user_id == null && user_role_id == 0 && (l.getIs_bid() == 1 || l.getIs_buy() == 1) ){ %>
												<a class="btn btn-primary btn-block" href="bid?mngr=get&a=registration">REGISTER</a>
												<a class="btn btn-primary btn-block" href="bid?mngr=get&a=login">LOGIN</a>
											<% } %>
     										<% if(l.getEnd_date_time().before(new Timestamp(System.currentTimeMillis())) && l.getActive()>0){ %>
				                            	<%if(auction.getAuction_id().equals(new BigDecimal("797")) || auction.getAuction_id().equals(new BigDecimal("804"))){  %>
				                            	<% if(l.getBid_count() > 0){%>
				                                <button class="btn btn-primary btn-block btn-warning" onclick="viewLot('<%=l.getId()%>')">FOR VALIDATION</button>
				                            	<% }else { %>
				                            	<button class="btn btn-primary btn-block" onclick="viewLot('<%=l.getId()%>')">NO SALE</button>	
				                            		<% } %>
				                            	<% } %>
				                            <% }  %>
											<div class="clearfix top10"></div>
										</div>
										<div class="clearfix top10"></div>
								</div>
								<div class="clearfix top10"></div>
								
							</div>
							<% if ((x % 2) == 0) {%>
								<div class="clearfix top10"></div>
							<% } %>
							<% x++; %>
						<% } %>
					</div>
					
				</div>

			</div>
		</div>
	</div>
</section>

<div class="clearfix top100"></div>
<div class="clearfix top100"></div>
<div class="clearfix top100"></div>
<div class="clearfix top100"></div>




</div>

<jsp:include page="includes/footer.jsp"></jsp:include>
<jsp:include page="includes/footer-meta.jsp"></jsp:include>


<script>

function forceNumeric(){
    var $input = $(this);
    $input.val($input.val().replace(/[^\d]+/g,''));
}

function viewLot(id) {
	$('input[name="manager"]').val("lot-manager");
	$('input[name="action"]').val("lotBidDetails");
	$('input[name="lotId_wip"]').val(id);
	$( "#frm" ).submit();
}

function showMaxBidForm(action, value, lot, id, qtyid) {
	$('<div id="maxbid-form" title="Set Maximum Bid"></div>').dialog({
		height: "auto",
		width: 350,
		title: "Set Maximum Bid",
		modal: true,
		open: function (event, ui) {
			var dialog_html = '<p id="validateTips">All fields are required.</p><label for="maxbid-'+ id +'">Amount</label><div class="input-group"><span class="input-group-addon">' + "<%=currency%>" + '</span>' +
			'<input type="text" name="maxbid-'+ id +'" id="maxbid-'+ id +'" placeholder="'+ value +'" class="form-control">' +
			'</div>';
			$(this).html(dialog_html);
			$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
		},
		buttons: {
			"Set Max": function(){
				value = parseFloat(parseFloat(value).toFixed(2));
				var maxbid_value = parseFloat(parseFloat($("#maxbid-"+id).val().trim()).toFixed(2));

				if(isNaN(maxbid_value)) maxbid_value = 0;
				if(maxbid_value <= value) {
					$("#validateTips" ).text( 'Amount must be greater than ' + value ).addClass( "ui-state-highlight" );
					setTimeout(function() {	$( "#validateTips" ).removeClass( "ui-state-highlight", 1500 );	}, 500 );
				} else {				
					$(this).dialog("close");
					submitPage(action, maxbid_value, lot, id, qtyid,'')
				}
			},
			Cancel: function() {
				$(this).dialog("close");
			}
		},
		close: function() {
			$("#maxbid-form").remove();
		}
	}).dialog('widget').position({ my: 'center', at: 'center', of: $(this) });
}

function showPreBidForm(action, value, lot, id, qtyid) {
	$('<div id="prebid-form" title="Pre-Bid"></div>').dialog({
		height: "auto",
		width: 350,
		title: "Pre-Bid",
		modal: true,
		open: function (event, ui) {
			var dialog_html = '<p id="validateTips">All fields are required.</p><label for="prebid-'+ id +'">Amount</label><div class="input-group"><span class="input-group-addon">' + "<%=currency%>" + '</span>' +
			'<input type="text" name="prebid-'+ id +'" id="prebid-'+ id +'" placeholder="'+ value +'" class="form-control">' +
			'</div>';
			$(this).html(dialog_html);
			$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
		},
		buttons: {
			"Pre-Bid": function(){
				value = parseFloat(parseFloat(value).toFixed(2));
				var prebid_value = parseFloat(parseFloat($("#prebid-"+id).val().trim()).toFixed(2));

				if(isNaN(prebid_value)) prebid_value = 0;
				if(prebid_value <= value) {
					$("#validateTips" ).text( 'Amount must be greater than ' + value ).addClass( "ui-state-highlight" );
					setTimeout(function() {	$( "#validateTips" ).removeClass( "ui-state-highlight", 1500 );	}, 500 );
				} else {				
					$(this).dialog("close");
					submitPage(action, prebid_value, lot, id, qtyid,'')
				}
			},
			Cancel: function() {
				$(this).dialog("close");
			}
		},
		close: function() {
			$("#prebid-form").remove();
		}
	}).dialog('widget').position({ my: 'center', at: 'center', of: $(this) });
}


function showNegotiatedBidForm(action, value, lot, id, qtyid) {
	$('<div id="negotiated-form" title="Pre-Bid"></div>').dialog({
		height: "auto",
		width: 350,
		title: "Make Offer",
		modal: true,
		open: function (event, ui) {
			var dialog_html = '<p id="validateTips">All fields are required.</p><label for="negotiated-'+ id +'">Amount</label><div class="input-group"><span class="input-group-addon">' + "<%=currency%>" + '</span>' +
			'<input type="number" name="negotiated-'+ id +'" id="negotiated-'+ id +'" placeholder="'+ value +'" class="form-control">' +
			'</div>'+
			'<label for="negotiated-note-'+ id +'">Note</label>'+
			'<textarea maxlength="50" class="form-control" rows="5" id="negotiated-note-'+ id +'"></textarea>';
			$(this).html(dialog_html);
			$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
		},
		buttons: {
			"Send Offer": function(){
				value = parseFloat(parseFloat(value).toFixed(2));
				var negotiated_value = parseFloat(parseFloat($("#negotiated-"+id).val().trim()).toFixed(2));
				var negotiated_note = $("#negotiated-note-"+id).val();
				

				if(isNaN(negotiated_value)) negotiated_value = 0;
				if(negotiated_value <= 0) {
					$("#validateTips" ).text( 'Amount must be greater than 0' ).addClass( "ui-state-highlight" );
					setTimeout(function() {	$( "#validateTips" ).removeClass( "ui-state-highlight", 1500 );	}, 500 );
				} else {				
					$(this).dialog("close");
					submitPage(action, negotiated_value, lot, id, qtyid, negotiated_note);
				}
			},
			Cancel: function() {
				$(this).dialog("close");
			}
		},
		close: function() {
			$("#negotiated-form").remove();
		}
	}).dialog('widget').position({ my: 'center', at: 'center', of: $(this) });
}

function favs(action,lot,id,auctionId){
	$('input[name="manager"]').val("bid-manager");
	$('input[name="doaction"]').val(action);
	$('input[name="amount"]').val(0);
	$('input[name="lotId"]').val(lot);
	$('input[name="lotId_wip"]').val(id);
	$('input[name="unit_qty"]').val(1);
	$('input[name="note"]').val(0);
	$('input[name="auction-id"]').val(auctionId);
	$( "#frm" ).submit();
}

function submitPage(action, value, lot, id, qtyid, note) {
	var unit_qty = 1;
	if($("#"+qtyid+" :selected").length ) unit_qty = $("#"+qtyid+" :selected").attr('value');
	
	$('input[name="manager"]').val("bid-manager");
	$('input[name="doaction"]').val(action);
	$('input[name="amount"]').val(value);
	$('input[name="lotId"]').val(lot);
	$('input[name="lotId_wip"]').val(id);
	$('input[name="unit_qty"]').val(unit_qty);
	$('input[name="note"]').val(note);
	
	$('<div id="dialog-confirm"></div>').dialog({
		resizable: false,
	    height: "auto",
	    width: 400,
	    modal: true,
	    title: "Confirmation",
	    closeOnEscape: false,
        open: function (event, ui) {
        	var amount = 0;
        	var dialog_title = "Confirmation";
        	var currency_html = "<%=currency%>";
        	var dialog_html = '<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>Are you sure?</p>';
        	var unit_qty_html = "";
        	if(unit_qty>1) unit_qty_html = ' with quantity of ' + unit_qty + ' units';
        	var aggreement_html = "";
        	<% if(trapOneLotPerBidder.compareTo(BigDecimal.ZERO)==0) { %>
        	aggreement_html = "<p>By clicking confirm, you agree to the terms and condition of this auction.</p>"
        	<%} %>

        	
        	$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
      
        	if(action=="BID") {
        		dialog_title = "Bid confirmation";
        		amount = parseFloat(value) * parseInt(unit_qty);
        		dialog_html = '<p>You will bid ' + 	amount.toFixed(2)  +' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="BUY") {
        		dialog_title = "Buy confirmation";
        		amount = parseFloat(value);
        		dialog_html = '<p>You will buy this lot for ' + amount.toFixed(2) + ' '+currency_html + unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="SET-MAXIMUM-BID") {
        		dialog_title = "Set maximum bid confirmation";
        		amount = parseFloat(value);
        		dialog_html = '<p>You will will set your maximum bid of ' + amount.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="NEGOTIATED") {
        		dialog_title = "Offer bid confirmation";
        		amount = parseFloat(value);
        		dialog_html = '<p>You will will set your offer bid of ' + amount.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="PRE-BID") {
        		dialog_title = "Pre-bid confirmation";
        		amount = parseFloat(value);
        		dialog_html = '<p>You will will set pre-bid of ' + amount.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="BID-ONE-LOT") {
        		dialog_title = "One Lot Per Bidder";
        		amount = parseFloat(value);
        		dialog_html = '<p>You are allowed to bid one lot only.</p>'+ 
        		aggreement_html;
        	}

            $(this).dialog( "option", "title", dialog_title);
            $(this).html(dialog_html);
        }, 
        buttons: {
        	"Confirm": function() {
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


function showAlertPage(action, value, lot, id, qtyid, note) {
	var unit_qty = 1;
	if($("#"+qtyid+" :selected").length ) unit_qty = $("#"+qtyid+" :selected").attr('value');
	
	$('input[name="manager"]').val("bid-manager");
	$('input[name="doaction"]').val(action);
	$('input[name="amount"]').val(value);
	$('input[name="lotId"]').val(lot);
	$('input[name="lotId_wip"]').val(id);
	$('input[name="unit_qty"]').val(unit_qty);
	$('input[name="note"]').val(note);
	
	$('<div id="dialog-confirm"></div>').dialog({
		resizable: false,
	    height: "auto",
	    width: 400,
	    modal: true,
	    title: "Confirmation",
	    closeOnEscape: false,
        open: function (event, ui) {
        	var amount = 0;
        	var dialog_title = "Confirmation";
        	var currency_html = "<%=currency%>";
        	var dialog_html = '<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>Are you sure?</p>';
        	var unit_qty_html = "";
        	if(unit_qty>1) unit_qty_html = ' with quantity of ' + unit_qty + ' units';
        	var aggreement_html = "";
        	
        	$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
      
        	if(action=="BID") {
        		dialog_title = "Bid confirmation";
        		amount = parseFloat(value) * parseInt(unit_qty);
        		dialog_html = '<p>You will bid ' + 	amount.toFixed(2)  +' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="BUY") {
        		dialog_title = "Buy confirmation";
        		amount = parseFloat(value);
        		dialog_html = '<p>You will buy this lot for ' + amount.toFixed(2) + ' '+currency_html + unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="SET-MAXIMUM-BID") {
        		dialog_title = "Set maximum bid confirmation";
        		amount = parseFloat(value);
        		dialog_html = '<p>You will will set your maximum bid of ' + amount.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="NEGOTIATED") {
        		dialog_title = "Offer bid confirmation";
        		amount = parseFloat(value);
        		dialog_html = '<p>You will will set your offer bid of ' + amount.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="PRE-BID") {
        		dialog_title = "Pre-bid confirmation";
        		amount = parseFloat(value);
        		dialog_html = '<p>You will will set pre-bid of ' + amount.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="BID-ONE-LOT") {
        		dialog_title = "One Lot Per Bidder";
        		amount = parseFloat(value);
        		dialog_html = '<p>You are allowed to bid one lot only.</p>'+ 
        		aggreement_html;
        	}

            $(this).dialog( "option", "title", dialog_title);
            $(this).html(dialog_html);
        }, 
        buttons: {
        	"OK": function() {
  	          $( this ).dialog( "close" );
  	        }
        },
		close: function() {
			$("#dialog-confirm").remove();
		}
    }).dialog('widget').position({ my: 'center', at: 'center', of: $(this) }); //end confirm dialog
}

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

jQuery(window).on('load', function(){
	<%if(msgInfo!=null){%>
	var msgInfo = "<%=msgInfo%>";
	var msgbgcol = "<%=msgbgcol%>";
	showAlert(msgInfo, msgbgcol);
	<%}%>
	
	
	
	
	$('.lazy').lazyload({
		threshold : 200,
		onError: function(element) {
	        console.log('image "' + element[0]['currentSrc'] + '" could not be loaded');
	    },
	    afterLoad: function(element) {
	        var imageSrc = element.data('currentSrc');
	        console.log('image "' + element[0]['currentSrc'] + '" was loaded successfully');
	    },
	    load: function(element){

        }
	})
	
	$('body').on('propertychange input', 'input[type="number"]', forceNumeric);
	

});

</script>


<form action="" id="frm" name="frm" method="post" onkeypress="stopEnterSubmitting(window.event)">
   <input type="hidden" name="manager" id="manager" value=""/>
   <input type="hidden" name="action" id="action" value=""/>
   <input type="hidden" name="doaction" id="doaction" value=""/>
   <input type="hidden" name="lotId" id="lotId" value=""/>
   <input type="hidden" name="lotId_wip" id="lotId_wip" value=""/>
   <input type="hidden" name="unit_qty" id="unit_qty" value=""/>
   <input type="hidden" name="note" id="note" value=""/>
   <input type="hidden" name="amount" id="amount" value=""/>
   <input type="hidden" name="userId" id="userId" value="<%=userId %>"/>
   <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
   <input type="hidden" name="auction-id" id="auction-id" value="<%=auction.getId()%>"/>
</form>



</body>
</html>

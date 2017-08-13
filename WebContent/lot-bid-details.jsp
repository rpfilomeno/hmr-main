<!DOCTYPE html>
<%@ page import="hmr.com.bean.Lot"
		 import="hmr.com.bean.Auction"
		 import="hmr.com.bean.Lov"
		 import="hmr.com.bean.Item"
		 import="hmr.com.bean.Image"
		 import="hmr.com.manager.ImageManager"
		 import="java.util.List"  
		 import="java.math.BigDecimal"  
		 import="java.util.HashMap"  
		 import="java.text.DecimalFormat"
		 import="java.text.SimpleDateFormat"
		 import="java.sql.Timestamp"
		 import="hmr.com.bean.BiddingTransaction"
%>
<%
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

	BigDecimal trapOneLotPerBidder = request.getAttribute("trapOneLotPerBidder")!=null ? (BigDecimal)request.getAttribute("trapOneLotPerBidder") : BigDecimal.ZERO;
	List<Image> lot_images = (List<Image>)request.getAttribute("lot_images");
	List<BiddingTransaction> bidding_transactions = (List<BiddingTransaction>)request.getAttribute("bidding_transactions");
	
    DecimalFormat df = new DecimalFormat("#,###,##0");
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy  HH:mm:ss");
	
	ImageManager iMngr = new ImageManager();
	List<Image> item_images = null;
	
	String bAmount = "0";
	
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
					<li>
						<a href="bid?mngr=get&a=auctionBidDetails&uid=<%=userId %>&aid=<%=auction.getId() %>"><%=auction.getAuction_name() %></a>
					</li>
					<li class="active"><%=lot.getLot_name() %></li>
				</ol>
			</div>
		</div>
	</div>
</div>

<section id="hmr-main-container">
	<div class="container">
		<div class="row">
		<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
			
			<div class="col-md-4 col-lg-3 visible-lg visible-md">
				<div class="side-widget">
					<div class="main-category-header">
						Gallery
					</div>
					<div class="side-product-item">
							<div class="product-item gallery">
								<div id="lot-gallery">
								<% for (Image i : lot_images) {	%>
									<a href="image?id=<%=i.getId()%>" title="Image #<%=i.getId()%>">
										<img style="width:100%; padding: 3px;" class="lazy" data-original="image?id=<%=i.getId()%>&t=t" alt="Image #<%=i.getId()%>">
									</a>
			
								<% } %>
								</div>
							</div>
					</div>
					
					
					
					
				</div>
				
			</div>
			<div class="col-md-8 col-lg-9">
				
				<div class="product-full-view-wrap">
					<div class="row">
						<div class="col-sm-7">
							<div style="margin-top:15px;">
								<img style="width:100%" class="lazy" data-original="image?id=<%=lot.getId()%>&t=l" >
							</div>
						</div>
						<div class="col-sm-5"> 
							<div>
							<% if(user_id != null && user_role_id > 0){ %>
								<% if(lot.getIsFav() == 1){ %>
									<div class="glyphicon glyphicon-heart favorite active" onclick="favs('UNFAV','<%=lot.getLot_id()%>','<%=lot.getId()%>')"></div>
								<% } else { %>
									<div class="glyphicon glyphicon-heart favorite" onclick="favs('FAV','<%=lot.getLot_id()%>','<%=lot.getId()%>')"></div>
								<% } %>
							<% } %>
							<div><h3 class="full-product-name"><%=lot.getLot_name()%></h3></div>
							</div>
							<div class="product-details">
								<%  if( lot.getIs_available_lot() > 0) { %>
								<div class="product-detail">Unit Quantity: <%=lot.getUnit_qty()%></div>
								<% } %>
								<% if (auction.getAuction_type()== 15) { %>
									<div class="product-detail">Highest Bid: <%=df.format(lot.getAmount_bid())%> <%=currency%></div>
									<%if(lot.getAmount_bid().doubleValue() >=  1 ){%>
									<div class="product-detail">Asking Bid: <%=df.format(lot.getAmount_bid_next())%> <%=currency%></div>
									<%}else{%>
									<div class="product-detail">Asking Bid: <%=df.format(lot.getStarting_bid_amount())%> <%=currency%></div>
									<%}%>
									
									
									<div class="product-detail">Bids: <%=lot.getBid_count()%></div>
								<% } else if (auction.getAuction_type() == 16) { %>
									<div class="product-detail">Buy Price: <%=df.format(lot.getBuy_price())%> <%=currency%></div>
									<div class="product-detail">Highest Offer: <%=df.format(lot.getAmount_bid())%> <%=currency%></div>
									<div class="product-detail">Offers: <%=lot.getBid_count()%></div>
								<% } %>
							</div>
							<div class="product-details">
								<% if(user_id != null && user_role_id > 0){ %>
									<% if( 	(trapOneLotPerBidder.compareTo(BigDecimal.ZERO) ==0 && auction.getOne_lot_per_bidder()==1) || 
											(trapOneLotPerBidder.compareTo(lot.getLot_id()) == 0 && auction.getOne_lot_per_bidder()==1) ||
											auction.getOne_lot_per_bidder()==0) 
									{ %>
										<% if( lot.getIs_available_lot() > 0) { %>
			                            	<% Integer i = lot.getUnit_qty(); %>
			                                <div class="form-group">
	                        	 				<select class="form-control" id="qty_<%=lot.getId()%>" name="qty_<%=lot.getId()%>">
			                                   	 	<% while(i > 0) { %>
			                                   	 		<option value="<%=i%>"><%=i%> unit<% if(i>1){ %>s<%}%></option>
			                                   	 		<% i = i - 1; %>
			                                   	 	<% } %>
			                                   	</select>
			                                 </div>
			                             <% } %>
			                             
			                             <% if(lot.getLastBidder()==null || !lot.getLastBidder().equals(user_id) ){  %>
			                             
											<% if(lot.getIs_bid() == 1){ %>
												<% if(auction.getAuction_type() == 15){ %>
					                            	<% if(auction.getStart_date_time().after(new Timestamp(System.currentTimeMillis())) && lot.getActive()>0){ %>
					                                	<button class="btn btn-primary btn-block" href="#" onclick="showPreBidForm('PRE-BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>','qty_<%=lot.getId()%>')">PRE-BID</button>
					                                <% } else { %>
					                                	<%if(lot.getAmount_bid().doubleValue() > 0){ %>
						                                   	<button class="btn btn-primary btn-block" href="#" onclick="submitPage('BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>','')">BID <%=df.format(lot.getAmount_bid_next())%> <%=currency%> </button>
						                               	<%}else if(lot.getAmount_bid().doubleValue() == 0){ %>
						                                   	<button class="btn btn-primary btn-block" href="#" onclick="submitPage('BID', '<%=lot.getStarting_bid_amount()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>','')">BID <%=df.format(lot.getStarting_bid_amount())%> <%=currency%> </button>
						                                   	
					                                	<%}%>
					                                   	<button class="btn btn-primary btn-block" href="#" onclick="showMaxBidForm('SET-MAXIMUM-BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>')">SET MAX</button>
					                               	<% } %>
				                                <% }else if(auction.getAuction_type() == 16){ %>
				                                   	 	<button class="btn btn-primary btn-block" href="#" onclick="showNegotiatedBidForm('NEGOTIATED', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>','qty_<%=lot.getId()%>')">MAKE OFFER</button>
				                               	<% } %>
											<% } %>
											
											<% if(lot.getIs_buy() == 1){ %>
		                                		<button class="btn btn-primary btn-block" href="#" onclick="submitPage('BUY', '<%=lot.getBuy_price()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>','')">BUY <%=df.format(lot.getBuy_price())%> <%=currency%></button>
		                                	<% } %>
		                                <% } else { %>
						                	<% if(lot.getIs_bid() == 1){ %>
						                    	<button class="btn btn-primary btn-block" >YOU BID <%=lot.getAmount_bid() %></button>
						                    	<button class="btn btn-primary btn-block" href="#" onclick="showMaxBidForm('SET-MAXIMUM-BID', '<%=lot.getAmount_bid_next()%>','<%=lot.getLot_id()%>','<%=lot.getId()%>','qty_<%=lot.getId()%>')">SET MAX</button>
						                    <% } else if(lot.getIs_buy() == 1){ %>
						                        <button class="btn btn-primary btn-block" >YOUR OFFER</button>
						                    <% } %>
						               <% } %>
									<% } %>
								<% } else { %>
									<a class="btn btn-primary btn-block" href="bid?mngr=get&a=registration">REGISTER</a>
									<a class="btn btn-primary btn-block" href="bid?mngr=get&a=login">LOGIN</a>
								<% } %>
							</div>
						</div>
					</div>
 
					<div class="clearfix" ></div>
					</div><div class="row">


					<ul class="nav nav-tabs nav-tabs-2" role="tablist">
						<li role="presentation" class="active">
							<a href="#product-description-tab" aria-controls="product-description-tab" role="tab" data-toggle="tab">Description</a>
						</li>
						<li role="presentation">
							<a href="#product-delivery-tab" aria-controls="product-delivery-tab" role="tab" data-toggle="tab">Terms and Conditions</a>
						</li>
						<li role="presentation" class="">
							<a href="#product-description-tab2" aria-controls="product-description-tab2" role="tab" data-toggle="tab">Bid History</a>
						</li>
					</ul>

					<div class="clearfix"></div>

					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="product-description-tab">
							<p><%=lot.getLot_desc()%></p>
						</div>

						<div role="tabpanel" class="tab-pane" id="product-delivery-tab">
							<p><%=auction.getTerms_and_conditions()%></p>

						</div>
						
						<div role="tabpanel" class="tab-pane" id="product-description-tab2">
							<h5>Bids</h5>
							<table class="table">
						    	<thead>
						        	<tr>
						               	<th>ID</th>
						            	<th>Amount</th>
						                <th>Date</th>
						            </tr>
						        </thead>
						        <tbody>
						        	<% for(BiddingTransaction bidding_transaction : bidding_transactions ) { %>
						            	<tr>  
						            	
						            	
						            	<% if(bidding_transaction.getAction_taken()==1 || bidding_transaction.getAction_taken()==5){
						            		bAmount = (bidding_transaction.getAmount_bid().compareTo(BigDecimal.ZERO) != 0) ? df.format(bidding_transaction.getAmount_bid()) : "0.00";
						            	} else if(bidding_transaction.getAction_taken()==2){
						            		bAmount = (bidding_transaction.getAmount_buy().compareTo(BigDecimal.ZERO) != 0) ? df.format(bidding_transaction.getAmount_buy()) : "0.00";
						            	} else if(bidding_transaction.getAction_taken()==3){
						            		bAmount = (bidding_transaction.getAmount_offer().compareTo(BigDecimal.ZERO) != 0) ? df.format(bidding_transaction.getAmount_offer()) : "0.00";
						            	}
						            	
						            	    %>              	
						            	    <%-- bAmount = (bidding_transaction.getAmount_bid().compareTo(BigDecimal.ZERO) != 0) ? df.format(bidding_transaction.getAmount_bid()) : "0.00"; --%>
						                    <%-- bAmount = (bidding_transaction.getAmount_buy().compareTo(BigDecimal.ZERO) != 0) ? df.format(bidding_transaction.getAmount_buy()) : "0.00"; --%>
						                    <%-- bAmount = (bidding_transaction.getAmount_offer().compareTo(BigDecimal.ZERO) != 0) ? df.format(bidding_transaction.getAmount_offer()) : "0.00"; --%>
                                            <td>
						                    	<% if(user_id.equals(bidding_transaction.getUser_id()) ) { %>
						                        <div>You</div> 
						                        <% } else { %>
						                    	<div>User #<%=bidding_transaction.getUser_id() %></div>
						                        <% } %>
						                    </td>
						                   	<td class="total"><%=currency%>&nbsp;<%=bAmount%> </td>
						                    <td class="diliver-date"> <%=sdf.format(bidding_transaction.getDate_created()) %> </td>
						                 </tr>
						        	<% } %>
						        </tbody>
						    </table>
						</div>
						
					</div>
				</div>


<%--
				<div id="product-list-wrapper">
					<ul class="nav nav-tabs nav-tabs-2" role="tablist">
						<li role="presentation" class="active">
							<a href="#online-bidding-tab" aria-controls="lots-tab" role="tab" data-toggle="tab">Items</a>
						</li>
					</ul>
					<div class="clearfix top10"></div>

					

					<div class="row grid" id="d">
						<% Integer x =1; %>
						<%for(Item i : items) {%>
							<div class="col-md-6 col-xs-12 grid-item">
								<div class="product-item">
									<!--
										<div class="product-image-wrap">
											<img style="width:100%;" class="lazy" data-original="image?id=</%=i.getId()%>&t=it">
										</div> 
										<div class="clearfix top10"></div> -->
										<div class="product-body">
											
											<h3 class="product-name">Item #<%=i.getReference_no()%></h3>
											<div class="product-details">
												<div class="product-detail">Description: <%=i.getItem_desc()%></div>
											</div>
										</div>
										
										<div class="clearfix top10"></div>
											
										<%  item_images = iMngr.getImageListByItemId(i.getId()); %>

										<div id="item-gallery-<%=i.getId() %>">
										<% for (Image ii : item_images) { %>
											
												<div class="col-md-3 col-xs-6" style="padding:3px">
													<a href="image?id=<%=ii.getId()%>" title="Image #<%=ii.getId()%>">
														<img style="width:100%; padding-top:3px" class="lazy" data-original="image?id=<%=ii.getId()%>&t=t" alt="Image #<%=ii.getId()%>" />
													</a>
												</div>

										<% } %>
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
					
				</div> --%>
				
				<div class="clearfix top20"></div>

					

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
	$('<div id="maxbid-form" title="Set Max"></div>').dialog({
		height: "auto",
		width: 350,
		title: "Set Max",
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
					submitPage(action, maxbid_value, lot, id, qtyid,'');
				}
			},
			Cancel: function() {
				$(this).dialog("close");
			}
		},
		close: function() {
			$("#maxbid-form").remove();
		}
	});
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

function favs(action,lot,id){
	$('input[name="manager"]').val("bid-manager");
	$('input[name="doaction"]').val(action);
	$('input[name="amount"]').val(0);
	$('input[name="lotId"]').val(lot);
	$('input[name="lotId_wip"]').val(id);
	$('input[name="unit_qty"]').val(1);
	$('input[name="note"]').val(0);
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
        	aggreement_html = "<p>By reading this you are agreeing on the terms and conditions of this auction.</p>"
        	<%} %>
        	
        	$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
      
        	if(action=="BID") {
        		dialog_title = "Bid confirmation";
        		amount = parseFloat(value) * parseInt(unit_qty);
        		dialog_html = '<p>You will bid ' + amount.toFixed(2) +' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="BUY") {
        		dialog_title = "Buy confirmation";
        		amount =  parseFloat(value);
        		dialog_html = '<p>You will buy this lot for ' + amount.toFixed(2) + ' '+currency_html + unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="SET-MAXIMUM-BID") {
        		amount =  parseFloat(value);
        		dialog_title = "Set max bid confirmation";
        		dialog_html = '<p>You will will set your max bid of ' + amount.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+
        		aggreement_html;
        	}else if(action=="NEGOTIATED") {
        		amount =  parseFloat(value);
        		dialog_title = "Negotiated bid confirmation";
        		dialog_html = '<p>You will will set your offer bid of ' + amount.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
        		aggreement_html;
        	}else if(action=="PRE-BID") {
        		amount =  parseFloat(value);
        		dialog_title = "Pre-bid confirmation";
        		dialog_html = '<p>You will will set pre-bid of ' + amount.toFixed(2) + ' '+currency_html+' for this lot'+ unit_qty_html +'.</p>'+ 
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
    }); //end confirm dialog
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
	    }
	})
	

	
	
	document.getElementById('lot-gallery').onclick = function (event) {
	    event = event || window.event;
	    var target = event.target || event.srcElement,
	        link = target.src ? target.parentNode : target,
	        options = {index: link, event: event},
	        links = this.getElementsByTagName('a');
	    blueimp.Gallery(links, options);
	};
	
	<%for(Item i : items) {%>
	<%  item_images = iMngr.getImageListByItemId(i.getId()); %>
		<% if(item_images.size() > 0) { %>
		 
		document.getElementById("item-gallery-<%=i.getId() %>").onclick = function (event) {
		    event = event || window.event;
		    var target = event.target || event.srcElement,
		        link = target.src ? target.parentNode : target,
		        options = {index: link, event: event},
		        links = this.getElementsByTagName('a');
		    blueimp.Gallery(links, options);
		};
		<% } %>
	<% } %>
	
	$('body').on('propertychange input', 'input[type="number"]', forceNumeric);
	

	
});


</script>

<div id="blueimp-gallery" class="blueimp-gallery">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">&lsaquo;</a>
    <a class="next">&rsaquo;</a>
    <a class="close">&times;</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>	


<form action="" id="frm" name="frm" method="post" onkeypress="stopEnterSubmitting(window.event)">
   <input type="hidden" name="manager" id="manager" value=""/>
   <input type="hidden" name="action" id="action" value=""/>
   <input type="hidden" name="doaction" id="doaction" value=""/>
   <input type="hidden" name="lotId" id="lotId" value=""/>
   <input type="hidden" name="lotId_wip" id="lotId_wip" value=""/>
   <input type="hidden" name="unit_qty" id="unit_qty" value=""/>
   <input type="hidden" name="note" id="note" value=""/>
   <input type="hidden" name="amount" id="amount" value=""/>
   <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
   <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
</form>

</body>
</html>

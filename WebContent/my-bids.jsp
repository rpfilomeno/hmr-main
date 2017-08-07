<!DOCTYPE html>
<%@ page import="hmr.com.bean.Auction"
		 import="hmr.com.bean.Lot"
		 import="hmr.com.bean.Image"
		 import="hmr.com.bean.Lov"
		 import="hmr.com.bean.Item"
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
					<div class="clearfix top10"></div>

					<div class="row gutter-10">
						<%for(Lot l : lList) {%>
						<div class="col-md-6 col-xs-12">
							<div class="product-item">
								
									<div class="product-image-wrap">
										<a href="#" onclick="viewLot('<%=l.getId()%>')">
										<img style="width:100%" class="lazy" data-original="image?id=<%=l.getId()%>&t=lt">
										</a>
									</div>
									<div class="product-body">
										<h3 class="product-name">#<%=l.getLot_no()%> : <%=l.getLot_name()%></h3>
										<div class="product-details">
											<div class="product-detail">Description: <%=l.getLot_desc()%></div>
											<div class="product-detail">Highest Bid: <%=df.format(l.getAmount_bid())%> <%=currency%></div>
											<div class="product-detail">Bids: <%=l.getBid_count()%></div>

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
</section>

<div class="clearfix top100"></div>
<div class="clearfix top100"></div>
<div class="clearfix top100"></div>
<div class="clearfix top100"></div>




</div>

<jsp:include page="includes/footer.jsp"></jsp:include>
<jsp:include page="includes/footer-meta.jsp"></jsp:include>


<script>
function viewLot(id) {
	$('input[name="manager"]').val("lot-manager");
	$('input[name="action"]').val("lotBidDetails");
	$('input[name="lotId_wip"]').val(id);
	$( "#frm" ).submit();
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
	
	document.getElementById('auction-gallery').onclick = function (event) {
	    event = event || window.event;
	    var target = event.target || event.srcElement,
	        link = target.src ? target.parentNode : target,
	        options = {index: link, event: event},
	        links = this.getElementsByTagName('a');
	    blueimp.Gallery(links, options);
	};
	
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
   <input type="hidden" name="userId" id="userId" value="<%=userId %>"/>
   <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
</form>


</body>
</html>

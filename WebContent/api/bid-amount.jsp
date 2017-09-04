<%@ page import="hmr.com.bean.Auction"
         import="hmr.com.bean.Lot"
		 import="java.math.BigDecimal"
		 import="java.util.List"
		 import="java.util.Date"
		 import="java.sql.Timestamp"
		 import="java.text.SimpleDateFormat" 
  
%>
<%
List<Lot> lList = request.getAttribute("lList")!=null ? (List<Lot>)request.getAttribute("lList") : null;
Auction auction = request.getAttribute("auction")!=null ? (Auction) request.getAttribute("auction") : null;
Timestamp tsNow = request.getAttribute("tsNow")!=null ? (Timestamp) request.getAttribute("tsNow") : null;

tsNow = new Timestamp(new Date().getTime());

System.out.println("PAGE tsNow : "+tsNow);

Integer lotSize = lList.size();
Integer i = 0;
String bid = "0.00";
String curbid = "0.00";
String bidStatus = "";
SimpleDateFormat sdfTimer = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
String endDT = "";
String nowDT = "";

nowDT = sdfTimer.format(new Date().getTime());
%>

[
	<% for(Lot l : lList) { %>
  	<%	i++; 
  		if(l.getAmount_bid().doubleValue() > 0){ 
  			bid = String.format( "%.2f", l.getAmount_bid_next().doubleValue());
  		}else if(l.getAmount_bid().doubleValue() == 0){
  			bid = String.format( "%.2f",l.getStarting_bid_amount().doubleValue());
  		} else {
  			bid = String.format( "%.2f",l.getAmount_bid().doubleValue());
  		}
  		
  		if(l.getAmount_bid().doubleValue() > 0){
  		curbid = String.format( "%.2f",l.getAmount_bid().doubleValue());
  		}else if(l.getAmount_bid().doubleValue() == 0){
  			curbid = String.format( "%.2f",l.getStarting_bid_amount().doubleValue());
  		}
  		
  		
  		System.out.println("Bid Am "+l.getEnd_date_time());
  		
  		if(l.getEnd_date_time()!=null){
  			endDT = sdfTimer.format(l.getEnd_date_time());
  		}else{
  			endDT = sdfTimer.format(auction.getEnd_date_time());
  		}
  		System.out.println("endDT "+endDT);
  		
  		
  		if(l.getEnd_date_time()!=null && l.getEnd_date_time().after(tsNow) && l.getIs_bid() > 0){
  			bidStatus = "BID";
  		}else if(l.getEnd_date_time()==null && auction.getEnd_date_time().after(tsNow) && l.getIs_bid() > 0){
  			bidStatus = "BID";
  		}else if(l.getEnd_date_time()!=null && l.getEnd_date_time().before(tsNow) && l.getIs_bid() > 0){
  		//(lot.getEnd_date_time().before(new Timestamp(System.currentTimeMillis())) 
  			System.out.println("aaaaaaaaaaaaa "+l.getEnd_date_time());
  				bidStatus = "BID";
  			if(l.getBid_count() > 0){
  				bidStatus = "SOLD";
  			}else if(l.getBid_count() == 0){
  				bidStatus = "NO SALE";
  			}
  		}else if(l.getEnd_date_time()==null && auction.getEnd_date_time().before(tsNow) && l.getIs_bid() > 0){
  			System.out.println("asdf");
  			
  			bidStatus = "BID";
  			if(l.getBid_count() > 0){
  				bidStatus = "SOLD";
  			}else if(l.getBid_count() == 0){
  				bidStatus = "NO SALE";
  			}
  			if(auction.getAuction_id().equals(new BigDecimal("797")) || 
  					auction.getAuction_id().equals(new BigDecimal("804"))){ 
  				bidStatus = "FOR VALIDATION";
  			}else{
  				
  			}
  			
  		}
  	%>
	{
	
	  "id": "<%=l.getLot_id() %>",
      "name": "<%=l.getLot_name() %>",
      "bid": "<%=bid %>",
      "bidcnt": "<%=l.getBid_count() %>",
      "curbid": "<%=curbid%>",
      "bidder": "<%=l.getBidder_id()%>",
      "endDT": "<%=endDT%>",
      "bidStatus" : "<%=bidStatus%>",
      "nowDT" : "<%=nowDT%>"
      
      
      
	}<% if(i < lotSize){ %>,<% } %>
	
	
    <% } %>
]


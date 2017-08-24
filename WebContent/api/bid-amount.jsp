<%@ page import="hmr.com.bean.Lot"
		 import="java.math.BigDecimal"
		 import="java.util.List"
  
%>
<%
List<Lot> lList = request.getAttribute("lList")!=null ? (List<Lot>)request.getAttribute("lList") : null;
Integer lotSize = lList.size();
Integer i = 0;
String bid = "0.00";
String curbid = "0.00";
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
  	%>
	{
	  "id": "<%=l.getLot_id() %>",
      "name": "<%=l.getLot_name() %>",
      "bid": "<%=bid %>",
      "bidcnt": "<%=l.getBid_count() %>",
      "curbid": "<%=curbid%>",
      "bidder": "<%=l.getBidder_id()%>"
      
	}<% if(i < lotSize){ %>,<% } %>
    <% } %>
]


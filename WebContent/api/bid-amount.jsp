<%@ page import="hmr.com.bean.Lot"
		 import="java.math.BigDecimal"
		 import="java.util.List"
  
%>
<%
List<Lot> lList = request.getAttribute("lList")!=null ? (List<Lot>)request.getAttribute("lList") : null;
Integer lotSize = lList.size();
Integer i = 0;
Double bid = 0.00;
%>

[
	<% for(Lot l : lList) { %>
  	<%	i++; 
  		if(l.getAmount_bid().doubleValue() > 0){ 
  			bid = l.getAmount_bid_next().doubleValue();
  		}else if(l.getAmount_bid().doubleValue() == 0){
  			bid = l.getStarting_bid_amount().doubleValue();
  		} else {
  			bid = l.getAmount_bid().doubleValue();
  		}
  	%>
	{
	  "id": "<%=l.getLot_id() %>",
      "name": "<%=l.getLot_name() %>",
      "bid": "<%=bid %>"
	}<% if(i < lotSize){ %>,<% } %>
    <% } %>
]


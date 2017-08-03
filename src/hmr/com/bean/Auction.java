package hmr.com.bean;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;


public class Auction extends SystemBean {

	BigDecimal id;
	String auction_name;
	BigDecimal auction_no;
	String location;
	BigDecimal bid_deposit_amount;
	Timestamp start_date_time;
	Timestamp end_date_time;
	String auction_desc;
	String terms_and_conditions;
	Integer coordinator;
	Integer visibility;
	Integer auction_item_closing;
	Integer auction_type;
	BigDecimal auction_id;
	Integer no_of_lots;
	Integer no_of_items;
	Integer auction_item_increment_time;
	Integer bid_deposit;
	Timestamp date_sync;
	Integer active;
	Integer status;
	InputStream imageInputStream;
	byte[] imageBytes;
	byte[] imageSmallBytes;
	Integer currency;
	Integer category_level_1;
	BigDecimal default_premium;
	Integer one_lot_per_bidder;
	Integer one_start_bid;
	Integer bid_qualifier_price;
	String token;
	Integer auto_send_post_notification;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getAuction_no() {
		return auction_no;
	}
	public void setAuction_no(BigDecimal auction_no) {
		this.auction_no = auction_no;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getStart_date_time() {
		return start_date_time;
	}
	public void setStart_date_time(Timestamp start_date_time) {
		this.start_date_time = start_date_time;
	}
	public Timestamp getEnd_date_time() {
		return end_date_time;
	}
	public void setEnd_date_time(Timestamp end_date_time) {
		this.end_date_time = end_date_time;
	}
	public String getAuction_desc() {
		return escapeHTML(auction_desc);
	}
	public void setAuction_desc(String auction_desc) {
		this.auction_desc = auction_desc;
	}
	public String getTerms_and_conditions() {
		return terms_and_conditions;
	}
	public void setTerms_and_condition(String terms_and_conditions) {
		this.terms_and_conditions = terms_and_conditions;
	}
	public Integer getCoordinator() {
		return coordinator;
	}
	public void setCoordinator(Integer coordinator) {
		this.coordinator = coordinator;
	}
	public Integer getVisibility() {
		return visibility;
	}
	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}
	public Integer getAuction_item_closing() {
		return auction_item_closing;
	}
	public void setAuction_item_closing(Integer auction_item_closing) {
		this.auction_item_closing = auction_item_closing;
	}
	public Integer getAuction_type() {
		return auction_type;
	}
	public void setAuction_type(Integer auction_type) {
		this.auction_type = auction_type;
	}
	public BigDecimal getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(BigDecimal auction_id) {
		this.auction_id = auction_id;
	}
	public Integer getNo_of_lots() {
		return no_of_lots;
	}
	public void setNo_of_lots(Integer no_of_lots) {
		this.no_of_lots = no_of_lots;
	}
	public Integer getNo_of_items() {
		return no_of_items;
	}
	public void setNo_of_items(Integer no_of_items) {
		this.no_of_items = no_of_items;
	}
	public Integer getAuction_item_increment_time() {
		return auction_item_increment_time;
	}
	public void setAuction_item_increment_time(Integer auction_item_increment_time) {
		this.auction_item_increment_time = auction_item_increment_time;
	}
	public Integer getBid_deposit() {
		return bid_deposit;
	}
	public void setBid_deposit(Integer bid_deposit) {
		this.bid_deposit = bid_deposit;
	}
	public Timestamp getDate_sync() {
		return date_sync;
	}
	public void setDate_sync(Timestamp date_sync) {
		this.date_sync = date_sync;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public InputStream getImageInputStream() {
		return imageInputStream;
	}
	public void setImageInputStream(InputStream imageInputStream) {
		this.imageInputStream = imageInputStream;
	}
	public void setTerms_and_conditions(String terms_and_conditions) {
		this.terms_and_conditions = terms_and_conditions;
	}
	public byte[] getImageBytes() {
		return imageBytes;
	}
	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}
	public byte[] getImageSmallBytes() {
		return imageSmallBytes;
	}
	public void setImageSmallBytes(byte[] imageSmallBytes) {
		this.imageSmallBytes = imageSmallBytes;
	}
	public String getAuction_name() {
		return auction_name;
	}
	public void setAuction_name(String auction_name) {
		this.auction_name = auction_name;
	}
	public BigDecimal getBid_deposit_amount() {
		return bid_deposit_amount;
	}
	public void setBid_deposit_amount(BigDecimal bid_deposit_amount) {
		this.bid_deposit_amount = bid_deposit_amount;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Integer getCategory_level_1() {
		return category_level_1;
	}
	public void setCategory_level_1(Integer category_level_1) {
		this.category_level_1 = category_level_1;
	}
	public BigDecimal getDefault_premium() {
		return default_premium;
	}
	public void setDefault_premium(BigDecimal default_premium) {
		this.default_premium = default_premium;
	}
	public Integer getOne_lot_per_bidder() {
		return one_lot_per_bidder;
	}
	public void setOne_lot_per_bidder(Integer one_lot_per_bidder) {
		this.one_lot_per_bidder = one_lot_per_bidder;
	}
	public Integer getOne_start_bid() {
		return one_start_bid;
	}
	public void setOne_start_bid(Integer one_start_bid) {
		this.one_start_bid = one_start_bid;
	}
	public Integer getBid_qualifier_price() {
		return bid_qualifier_price;
	}
	public void setBid_qualifier_price(Integer bid_qualifier_price) {
		this.bid_qualifier_price = bid_qualifier_price;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	private static String escapeHTML(String s) {
		
		if(s!=null){
			
		    StringBuilder out = new StringBuilder(Math.max(16, s.length()));
		    for (int i = 0; i < s.length(); i++) {
		        char c = s.charAt(i);
		        if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&') {
		            out.append("&#");
		            out.append((int) c);
		            out.append(';');
		        } else {
		            out.append(c);
		        }
		    }
		    return out.toString();
			
		}else{
			return null;
		}
	}
	public Integer getAuto_send_post_notification() {
		return auto_send_post_notification;
	}
	public void setAuto_send_post_notification(Integer auto_send_post_notification) {
		this.auto_send_post_notification = auto_send_post_notification;
	}
}

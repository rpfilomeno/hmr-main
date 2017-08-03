package hmr.com.bean;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;


public class Item extends SystemBean {

	BigDecimal id;
	BigDecimal lot_id;
	BigDecimal item_id;
	BigDecimal auction_id;
	BigDecimal reference_no;
	BigDecimal target_price;
	BigDecimal reserve_price;
	BigDecimal amount_bid;
	BigDecimal amount_buy;
	Integer action_taken;
	Integer is_buy;
	Integer is_bid;
	BigDecimal buy_price;
	Integer bidder_id;
	Integer sync_status;
	Integer item_increment_time;
	String item_desc;
	Integer category_level_1;
	Integer category_level_2;
	Integer category_level_3;
	BigDecimal amount_bid_next;
	Integer bid_count;
	Integer currency;
	Timestamp synched_date;
	BigDecimal weight;
	
	
	InputStream imageInputStream;
	byte[] imageBytes;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getLot_id() {
		return lot_id;
	}
	public void setLot_id(BigDecimal lot_id) {
		this.lot_id = lot_id;
	}
	public BigDecimal getItem_id() {
		return item_id;
	}
	public void setItem_id(BigDecimal item_id) {
		this.item_id = item_id;
	}
	public BigDecimal getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(BigDecimal auction_id) {
		this.auction_id = auction_id;
	}
	public BigDecimal getReference_no() {
		return reference_no;
	}
	public void setReference_no(BigDecimal reference_no) {
		this.reference_no = reference_no;
	}
	public BigDecimal getTarget_price() {
		return target_price;
	}
	public void setTarget_price(BigDecimal target_price) {
		this.target_price = target_price;
	}
	public BigDecimal getReserve_price() {
		return reserve_price;
	}
	public void setReserve_price(BigDecimal reserve_price) {
		this.reserve_price = reserve_price;
	}
	public BigDecimal getAmount_bid() {
		return amount_bid;
	}
	public void setAmount_bid(BigDecimal amount_bid) {
		this.amount_bid = amount_bid;
	}
	public BigDecimal getAmount_buy() {
		return amount_buy;
	}
	public void setAmount_buy(BigDecimal amount_buy) {
		this.amount_buy = amount_buy;
	}
	public Integer getAction_taken() {
		return action_taken;
	}
	public void setAction_taken(Integer action_taken) {
		this.action_taken = action_taken;
	}
	public Integer getIs_buy() {
		return is_buy;
	}
	public void setIs_buy(Integer is_buy) {
		this.is_buy = is_buy;
	}
	public Integer getIs_bid() {
		return is_bid;
	}
	public void setIs_bid(Integer is_bid) {
		this.is_bid = is_bid;
	}
	public BigDecimal getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(BigDecimal buy_price) {
		this.buy_price = buy_price;
	}
	public Integer getBidder_id() {
		return bidder_id;
	}
	public void setBidder_id(Integer bidder_id) {
		this.bidder_id = bidder_id;
	}
	public Integer getSync_status() {
		return sync_status;
	}
	public void setSync_status(Integer sync_status) {
		this.sync_status = sync_status;
	}
	public Integer getItem_increment_time() {
		return item_increment_time;
	}
	public void setItem_increment_time(Integer item_increment_time) {
		this.item_increment_time = item_increment_time;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	public Integer getCategory_level_1() {
		return category_level_1;
	}
	public void setCategory_level_1(Integer category_level_1) {
		this.category_level_1 = category_level_1;
	}
	public Integer getCategory_level_2() {
		return category_level_2;
	}
	public void setCategory_level_2(Integer category_level_2) {
		this.category_level_2 = category_level_2;
	}
	public Integer getCategory_level_3() {
		return category_level_3;
	}
	public void setCategory_level_3(Integer category_level_3) {
		this.category_level_3 = category_level_3;
	}
	public InputStream getImageInputStream() {
		return imageInputStream;
	}
	public void setImageInputStream(InputStream imageInputStream) {
		this.imageInputStream = imageInputStream;
	}
	public byte[] getImageBytes() {
		return imageBytes;
	}
	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}
	public BigDecimal getAmount_bid_next() {
		return amount_bid_next;
	}
	public void setAmount_bid_next(BigDecimal amount_bid_next) {
		this.amount_bid_next = amount_bid_next;
	}
	public Integer getBid_count() {
		return bid_count;
	}
	public void setBid_count(Integer bid_count) {
		this.bid_count = bid_count;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Timestamp getSynched_date() {
		return synched_date;
	}
	public void setSynched_date(Timestamp synched_date) {
		this.synched_date = synched_date;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}


}

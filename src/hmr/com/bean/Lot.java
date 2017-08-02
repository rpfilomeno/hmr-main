package hmr.com.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Lot extends SystemBean {

	BigDecimal id;
	String lot_name;
	BigDecimal lot_no;
	BigDecimal lot_id;
	BigDecimal auction_id;
	String lot_desc;
	BigDecimal assessment_value;
	BigDecimal duties;
	BigDecimal vat;
	String unit;
	BigDecimal premium_rate;
	Integer lot_type_id;
	Integer active;
	Integer unit_qty;
	BigDecimal amount_bid;
	BigDecimal amount_buy;
	Integer action_taken;
	Integer is_buy;
	Integer is_bid;
	BigDecimal buy_price;
	Integer bidder_id;
	Integer lot_increment_time;
	BigDecimal amount_bid_next;
	Integer currency;
	Integer bid_count;
	Timestamp end_date_time;
	
	BigDecimal srp_total;
	BigDecimal target_price_total;
	BigDecimal reserve_price_total;
	BigDecimal assess_value_total;
	Timestamp date_sync;
	Integer is_available_lot;
	
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getLot_no() {
		return lot_no;
	}
	public void setLot_no(BigDecimal lot_no) {
		this.lot_no = lot_no;
	}
	public BigDecimal getLot_id() {
		return lot_id;
	}
	public void setLot_id(BigDecimal lot_id) {
		this.lot_id = lot_id;
	}
	public BigDecimal getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(BigDecimal auction_id) {
		this.auction_id = auction_id;
	}
	public String getLot_desc() {
		return lot_desc;
	}
	public void setLot_desc(String lot_desc) {
		this.lot_desc = lot_desc;
	}
	public BigDecimal getAssessment_value() {
		return assessment_value;
	}
	public void setAssessment_value(BigDecimal assessment_value) {
		this.assessment_value = assessment_value;
	}
	public BigDecimal getDuties() {
		return duties;
	}
	public void setDuties(BigDecimal duties) {
		this.duties = duties;
	}
	public BigDecimal getVat() {
		return vat;
	}
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getPremium_rate() {
		return premium_rate;
	}
	public void setPremium_rate(BigDecimal premium_rate) {
		this.premium_rate = premium_rate;
	}
	public Integer getLot_type_id() {
		return lot_type_id;
	}
	public void setLot_type_id(Integer lot_type_id) {
		this.lot_type_id = lot_type_id;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public Integer getUnit_qty() {
		return unit_qty;
	}
	public void setUnit_qty(Integer unit_qty) {
		this.unit_qty = unit_qty;
	}
	public String getLot_name() {
		return lot_name;
	}
	public void setLot_name(String lot_name) {
		this.lot_name = lot_name;
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
	public Integer getLot_increment_time() {
		return lot_increment_time;
	}
	public void setLot_increment_time(Integer lot_increment_time) {
		this.lot_increment_time = lot_increment_time;
	}
	public BigDecimal getAmount_bid_next() {
		if(amount_bid_next==null) return BigDecimal.ZERO;
		return amount_bid_next;
	}
	public void setAmount_bid_next(BigDecimal amount_bid_next) {
		this.amount_bid_next = amount_bid_next;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Integer getBid_count() {
		return bid_count;
	}
	public void setBid_count(Integer bid_count) {
		this.bid_count = bid_count;
	}
	public void setEnd_date_time(Timestamp end_date_time) {
		this.end_date_time = end_date_time;
	}
	public Timestamp getEnd_date_time() {
		return end_date_time;
	}
	public BigDecimal getSrp_total() {
		return srp_total;
	}
	public void setSrp_total(BigDecimal srp_total) {
		this.srp_total = srp_total;
	}
	public BigDecimal getTarget_price_total() {
		return target_price_total;
	}
	public void setTarget_price_total(BigDecimal target_price_total) {
		this.target_price_total = target_price_total;
	}
	public BigDecimal getReserve_price_total() {
		return reserve_price_total;
	}
	public void setReserve_price_total(BigDecimal reserve_price_total) {
		this.reserve_price_total = reserve_price_total;
	}
	public BigDecimal getAssess_value_total() {
		return assess_value_total;
	}
	public void setAssess_value_total(BigDecimal assess_value_total) {
		this.assess_value_total = assess_value_total;
	}
	public Timestamp getDate_sync() {
		return date_sync;
	}
	public void setDate_sync(Timestamp date_sync) {
		this.date_sync = date_sync;
	}
	public Integer getIs_available_lot() {
		return is_available_lot;
	}
	public void setIs_available_lot(Integer is_available_lot) {
		this.is_available_lot = is_available_lot;
	}

}

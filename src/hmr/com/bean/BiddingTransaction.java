package hmr.com.bean;


import java.math.BigDecimal;

public class BiddingTransaction extends SystemBean {

	BigDecimal id;
	BigDecimal lot_id;
	BigDecimal amount_bid;
	BigDecimal amount_buy;
	BigDecimal amount_offer;
	Integer action_taken;
	Integer status;
	Integer user_id;
	Integer is_extended;
	Integer qty;
	String note_offer;
	Integer auction_id;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
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

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public BigDecimal getLot_id() {
		return lot_id;
	}
	public void setLot_id(BigDecimal lot_id) {
		this.lot_id = lot_id;
	}
	public Integer getAction_taken() {
		return action_taken;
	}
	public void setAction_taken(Integer action_taken) {
		this.action_taken = action_taken;
	}
	public Integer getIs_extended() {
		return is_extended;
	}
	public void setIs_extended(Integer is_extended) {
		this.is_extended = is_extended;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getNote_offer() {
		return note_offer;
	}
	public void setNote_offer(String note_offer) {
		this.note_offer = note_offer.substring(0,160);
	}
	public BigDecimal getAmount_offer() {
		return amount_offer;
	}
	public void setAmount_offer(BigDecimal amount_offer) {
		this.amount_offer = amount_offer;
	}
	public Integer getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(Integer auction_id) {
		this.auction_id = auction_id;
	}

}

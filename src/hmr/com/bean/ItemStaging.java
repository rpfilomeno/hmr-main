package hmr.com.bean;


import java.math.BigDecimal;
import java.sql.Timestamp;


public class ItemStaging extends SystemBean {

	
	BigDecimal item_id;
	BigDecimal lot_id;
	Integer status_id;
	BigDecimal reference_no;
	Integer pullout_id;
	BigDecimal target_price;
	BigDecimal reserve_price;
	BigDecimal rate;
	BigDecimal amount_bid;
	Integer received_items_id;
	String qt_remarks;
	BigDecimal assess_value;
	Integer payment_status;
	BigDecimal bidder_number_id;
	Integer payables_id;
	BigDecimal product_code;
	BigDecimal srp;
	BigDecimal consignor_id;
	String description;
	BigDecimal delivery_receipt_id;
	BigDecimal weight;
	Timestamp last_date_sync;
	
	
	public BigDecimal getItem_id() {
		return item_id;
	}
	public void setItem_id(BigDecimal item_id) {
		this.item_id = item_id;
	}
	public BigDecimal getLot_id() {
		return lot_id;
	}
	public void setLot_id(BigDecimal lot_id) {
		this.lot_id = lot_id;
	}
	public Integer getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}
	public BigDecimal getReference_no() {
		return reference_no;
	}
	public void setReference_no(BigDecimal reference_no) {
		this.reference_no = reference_no;
	}
	public Integer getPullout_id() {
		return pullout_id;
	}
	public void setPullout_id(Integer pullout_id) {
		this.pullout_id = pullout_id;
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
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getAmount_bid() {
		return amount_bid;
	}
	public void setAmount_bid(BigDecimal amount_bid) {
		this.amount_bid = amount_bid;
	}
	public Integer getReceived_items_id() {
		return received_items_id;
	}
	public void setReceived_items_id(Integer received_items_id) {
		this.received_items_id = received_items_id;
	}
	public String getQt_remarks() {
		return qt_remarks;
	}
	public void setQt_remarks(String qt_remarks) {
		this.qt_remarks = qt_remarks;
	}
	public BigDecimal getAssess_value() {
		return assess_value;
	}
	public void setAssess_value(BigDecimal assess_value) {
		this.assess_value = assess_value;
	}
	public Integer getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(Integer payment_status) {
		this.payment_status = payment_status;
	}
	public BigDecimal getBidder_number_id() {
		return bidder_number_id;
	}
	public void setBidder_number_id(BigDecimal bidder_number_id) {
		this.bidder_number_id = bidder_number_id;
	}
	public Integer getPayables_id() {
		return payables_id;
	}
	public void setPayables_id(Integer payables_id) {
		this.payables_id = payables_id;
	}
	public BigDecimal getProduct_code() {
		return product_code;
	}
	public void setProduct_code(BigDecimal product_code) {
		this.product_code = product_code;
	}
	public BigDecimal getSrp() {
		return srp;
	}
	public void setSrp(BigDecimal srp) {
		this.srp = srp;
	}
	public BigDecimal getConsignor_id() {
		return consignor_id;
	}
	public void setConsignor_id(BigDecimal consignor_id) {
		this.consignor_id = consignor_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getDelivery_receipt_id() {
		return delivery_receipt_id;
	}
	public void setDelivery_receipt_id(BigDecimal delivery_receipt_id) {
		this.delivery_receipt_id = delivery_receipt_id;
	}
	public Timestamp getLast_date_sync() {
		return last_date_sync;
	}
	public void setLast_date_sync(Timestamp last_date_sync) {
		this.last_date_sync = last_date_sync;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

}

package hmr.com.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LotStaging extends SystemBean {

	BigDecimal auction_id;
	BigDecimal lot_id;
	BigDecimal lot_number;
	Integer is_available_lot;
	String lot_description;
	Integer lot_type_id;
	BigDecimal premium_rate;
	String unit;
	Integer unit_qty;
	BigDecimal vat;
	BigDecimal duties;
	BigDecimal assessment_value;
	Timestamp last_date_sync;
	
	public BigDecimal getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(BigDecimal auction_id) {
		this.auction_id = auction_id;
	}
	public BigDecimal getLot_id() {
		return lot_id;
	}
	public void setLot_id(BigDecimal lot_id) {
		this.lot_id = lot_id;
	}
	public BigDecimal getLot_number() {
		return lot_number;
	}
	public void setLot_number(BigDecimal lot_number) {
		this.lot_number = lot_number;
	}
	public Integer getIs_available_lot() {
		return is_available_lot;
	}
	public void setIs_available_lot(Integer is_available_lot) {
		this.is_available_lot = is_available_lot;
	}
	public String getLot_description() {
		return lot_description;
	}
	public void setLot_description(String lot_description) {
		this.lot_description = lot_description;
	}
	public Integer getLot_type_id() {
		return lot_type_id;
	}
	public void setLot_type_id(Integer lot_type_id) {
		this.lot_type_id = lot_type_id;
	}
	public BigDecimal getPremium_rate() {
		return premium_rate;
	}
	public void setPremium_rate(BigDecimal premium_rate) {
		this.premium_rate = premium_rate;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getUnit_qty() {
		return unit_qty;
	}
	public void setUnit_qty(Integer unit_qty) {
		this.unit_qty = unit_qty;
	}
	public BigDecimal getVat() {
		return vat;
	}
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	public BigDecimal getDuties() {
		return duties;
	}
	public void setDuties(BigDecimal duties) {
		this.duties = duties;
	}
	public BigDecimal getAssessment_value() {
		return assessment_value;
	}
	public void setAssessment_value(BigDecimal assessment_value) {
		this.assessment_value = assessment_value;
	}
	public Timestamp getLast_date_sync() {
		return last_date_sync;
	}
	public void setLast_date_sync(Timestamp last_date_sync) {
		this.last_date_sync = last_date_sync;
	}
	
	
}

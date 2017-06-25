package hmr.com.bean;

import java.math.BigDecimal;

public class AuctionRange extends SystemBean {

	BigDecimal id;
	BigDecimal auction_id;
	BigDecimal range_start;
	BigDecimal range_end;
	BigDecimal increment_amount;
	Integer auction_level;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(BigDecimal auction_id) {
		this.auction_id = auction_id;
	}
	public BigDecimal getRange_start() {
		return range_start;
	}
	public void setRange_start(BigDecimal range_start) {
		this.range_start = range_start;
	}
	public BigDecimal getRange_end() {
		return range_end;
	}
	public void setRange_end(BigDecimal range_end) {
		this.range_end = range_end;
	}
	public BigDecimal getIncrement_amount() {
		return increment_amount;
	}
	public void setIncrement_amount(BigDecimal increment_amount) {
		this.increment_amount = increment_amount;
	}
	public Integer getAuction_level() {
		return auction_level;
	}
	public void setAuction_level(Integer auction_level) {
		this.auction_level = auction_level;
	}

}

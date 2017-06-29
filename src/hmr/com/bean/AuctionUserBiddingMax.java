package hmr.com.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AuctionUserBiddingMax extends SystemBean {
	BigDecimal id;
	Integer lot_id;
	BigDecimal amount;
	BigDecimal auction_id;
	Timestamp date_created;
	Timestamp date_updated;
	Integer updated_by;
	Integer created_by;
	Integer bidder_id;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public Integer getLot_id() {
		return lot_id;
	}
	public void setLot_id(Integer lot_id) {
		this.lot_id = lot_id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(BigDecimal auction_id) {
		this.auction_id = auction_id;
	}
	public Timestamp getDate_created() {
		return date_created;
	}
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}
	public Timestamp getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(Timestamp date_updated) {
		this.date_updated = date_updated;
	}
	public Integer getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}
	public Integer getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}
	public Integer getBidder_id() {
		return bidder_id;
	}
	public void setBidder_id(Integer bidder_id) {
		this.bidder_id = bidder_id;
	}
	

}

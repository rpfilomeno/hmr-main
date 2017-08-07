package hmr.com.bean;

import java.math.BigDecimal;


public class AuctioUserWatchlist extends SystemBean {
	BigDecimal id;
	BigDecimal auction_id;
	BigDecimal lot_id;
	Integer user_id;
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
	public BigDecimal getLot_id() {
		return lot_id;
	}
	public void setLot_id(BigDecimal lot_id) {
		this.lot_id = lot_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

}

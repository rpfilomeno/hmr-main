package hmr.com.bean;

import java.io.InputStream;
import java.math.BigDecimal;


public class Image extends SystemBean {

	BigDecimal id;
	BigDecimal auction_id;
	BigDecimal lot_id;
	BigDecimal item_id;
	InputStream imageInputStream;
	Integer active;
	byte[] imageBytes;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getItem_id() {
		return item_id;
	}
	public void setItem_id(BigDecimal item_id) {
		this.item_id = item_id;
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
	public BigDecimal getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(BigDecimal auction_id) {
		this.auction_id = auction_id;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public BigDecimal getLot_id() {
		return lot_id;
	}
	public void setLot_id(BigDecimal lot_id) {
		this.lot_id = lot_id;
	}

}

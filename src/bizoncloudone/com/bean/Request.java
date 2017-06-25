package bizoncloudone.com.bean;

import java.util.Date;

public class Request {

	Integer id;
	Integer user_id;
	Integer status;
	String access_key;
	Date date_created;
	Date date_updated;
	Integer updated_by;
	Integer created_by;
	Boolean email_tag;
	Integer verify_code;
	
	String sms_msg;
	String sms_code;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	public Date getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(Date date_updated) {
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
	public String getAccess_key() {
		return access_key;
	}
	public void setAccess_key(String access_key) {
		this.access_key = access_key;
	}
	public Boolean getEmail_tag() {
		return email_tag;
	}
	public void setEmail_tag(Boolean email_tag) {
		this.email_tag = email_tag;
	}
	public Integer getVerify_code() {
		return verify_code;
	}
	public void setVerify_code(Integer verify_code) {
		this.verify_code = verify_code;
	}
	public String getSms_msg() {
		return sms_msg;
	}
	public void setSms_msg(String sms_msg) {
		this.sms_msg = sms_msg;
	}
	public String getSms_code() {
		return sms_code;
	}
	public void setSms_code(String sms_code) {
		this.sms_code = sms_code;
	}

}

package hmr.com.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class User extends SystemBean {

	Integer id;
	String last_name;
	String first_name;
	String pass_word;	
	BigDecimal mobile_no_1;
	BigDecimal mobile_no_2;
	Integer gender;
	Integer role;
	Integer bidder_no;
	Integer reserve_bidder_no;
	String company;
	Integer status;
	Integer active;
	String email_address;
	BigDecimal landline_no;
	Integer news_letter;
	Timestamp news_letter_registration_date;
	Timestamp date_registration;
	String verification_email_key;
	Timestamp date_password_change;
	Date birth_date;
	Integer showChangePasswordNextLogin;
	String new_password;
	
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public Integer getShowChangePasswordNextLogin() {
		return showChangePasswordNextLogin;
	}
	public void setShowChangePasswordNextLogin(Integer showChangePasswordNextLogin) {
		this.showChangePasswordNextLogin = showChangePasswordNextLogin;
	}
	public Timestamp getDate_password_change() {
		return date_password_change;
	}
	public void setDate_password_change(Timestamp date_password_change) {
		this.date_password_change = date_password_change;
	}
	public String getVerification_email_key() {
		return verification_email_key;
	}
	public void setVerification_email_key(String verification_email_key) {
		this.verification_email_key = verification_email_key;
	}
	public Timestamp getDate_registration() {
		return date_registration;
	}
	public void setDate_registration(Timestamp date_registration) {
		this.date_registration = date_registration;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getPass_word() {
		return pass_word;
	}
	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getBidder_no() {
		return bidder_no;
	}
	public void setBidder_no(Integer bidder_no) {
		this.bidder_no = bidder_no;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	public BigDecimal getMobile_no_1() {
		return mobile_no_1;
	}
	public void setMobile_no_1(BigDecimal mobile_no_1) {
		this.mobile_no_1 = mobile_no_1;
	}
	public BigDecimal getMobile_no_2() {
		return mobile_no_2;
	}
	public void setMobile_no_2(BigDecimal mobile_no_2) {
		this.mobile_no_2 = mobile_no_2;
	}
	public Integer getReserve_bidder_no() {
		return reserve_bidder_no;
	}
	public void setReserve_bidder_no(Integer reserve_bidder_no) {
		this.reserve_bidder_no = reserve_bidder_no;
	}
	public BigDecimal getLandline_no() {
		return landline_no;
	}
	public void setLandline_no(BigDecimal landline_no) {
		this.landline_no = landline_no;
	}
	public Integer getNews_letter() {
		return news_letter;
	}
	public void setNews_letter(Integer news_letter) {
		this.news_letter = news_letter;
	}
	public Timestamp getNews_letter_registration_date() {
		return news_letter_registration_date;
	}
	public void setNews_letter_registration_date(Timestamp news_letter_registration_date) {
		this.news_letter_registration_date = news_letter_registration_date;
	}
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
}

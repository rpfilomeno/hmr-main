package hmr.com.bean;

public class UserAddress extends SystemBean {

	Integer id;
	Integer user_id;
	String address_line_1;
	String baranggay;
	String province;
	String city;	
	String country;
	Integer address_type;
	Integer postal_code;
	
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
	public String getAddress_line_1() {
		if(address_line_1 == null) address_line_1 = "";
		return address_line_1;
	}
	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}
	public String getBaranggay() {
		if(baranggay == null)baranggay ="";
		return baranggay;
	}
	public void setBaranggay(String baranggay) {
		this.baranggay = baranggay;
	}
	public String getCity() {
		if(city == null)city="";
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		if (country ==null) country ="";
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getAddress_type() {
		return address_type;
	}
	public void setAddress_type(Integer address_type) {
		this.address_type = address_type;
	}
	public Integer getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(Integer postal_code) {
		this.postal_code = postal_code;
	}
	public String getProvince() {
		if(province==null)province="";
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	
}

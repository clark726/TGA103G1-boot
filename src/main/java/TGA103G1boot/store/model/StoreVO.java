package TGA103G1boot.store.model;

import java.io.Serializable;
import java.util.List;

import TGA103G1boot.common.Result;

public class StoreVO extends Result implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer store_id;
	private String account;
	private String name;
	private String password;
	private String phone;
	private String email;
	private String address;
	private String lng;
	private String lat;
	private Integer theme_id;
	private String dayoff;
	private String work_open;
	private String work_end;
	private String produce;
	private List<Object> imgs;
	private byte[] img;
	private String imgstr;
	private String storetype;

	private String storeproduce;
	private String product_name;
	private Integer product_price;
	private Integer product_id;
	private Integer img_id;
	private Integer productStatus;
	private String jwts;

	public StoreVO() {
	}

	public StoreVO(Integer store_id, String account, String name, String password, String phone, String email,
			String address, String lng, String lat, Integer theme_id, String dayoff, String work_open, String work_end,
			String produce) {
		super();
		this.store_id = store_id;
		this.account = account;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.lng = lng;
		this.lat = lat;
		this.theme_id = theme_id;
		this.dayoff = dayoff;
		this.work_open = work_open;
		this.work_end = work_end;
		this.produce = produce;
	}

	
	public String getJwts() {
		return jwts;
	}

	public void setJwts(String jwts) {
		this.jwts = jwts;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getImg_id() {
		return img_id;
	}

	public void setImg_id(Integer img_id) {
		this.img_id = img_id;
	}

	public String getStoretype() {
		return storetype;
	}

	public void setStoretype(String storetype) {
		this.storetype = storetype;
	}

	public String getStoreproduce() {
		return storeproduce;
	}

	public void setStoreproduce(String storeproduce) {
		this.storeproduce = storeproduce;
	}

	public String getImgstr() {
		return imgstr;
	}

	public void setImgstr(String imgstr) {
		this.imgstr = imgstr;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public List<Object> getImgs() {
		return imgs;
	}

	public void setImgs(List<Object> imgs) {
		this.imgs = imgs;
	}

	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Integer getTheme_id() {
		return theme_id;
	}

	public void setTheme_id(Integer theme_id) {
		this.theme_id = theme_id;
	}

	public String getDayoff() {
		return dayoff;
	}

	public void setDayoff(String dayoff) {
		this.dayoff = dayoff;
	}

	public String getWork_open() {
		return work_open;
	}

	public void setWork_open(String work_open) {
		this.work_open = work_open;
	}

	public String getWork_end() {
		return work_end;
	}

	public void setWork_end(String work_end) {
		this.work_end = work_end;
	}

	public String getProduce() {
		return produce;
	}

	public void setProduce(String produce) {
		this.produce = produce;
	}

	@Override
	public String toString() {
		return "StoreVO [store_id=" + store_id + ", account=" + account + ", name=" + name + ", password=" + password
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", lng=" + lng + ", lat=" + lat
				+ ", theme_id=" + theme_id + ", dayoff=" + dayoff + ", work_open=" + work_open + ", work_end="
				+ work_end + ", produce=" + produce + "]";
	}

}

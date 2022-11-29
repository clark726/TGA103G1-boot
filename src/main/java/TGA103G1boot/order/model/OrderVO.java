package TGA103G1boot.order.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import TGA103G1boot.common.Result;

public class OrderVO extends Result implements Serializable {
    private Integer order_id;
    private Integer store_id;
    private Integer member_id;
    private Integer price;
    private Date date;
    private Integer method;
    private Integer status;
    private Integer pay_method;
    private String name;
    private String address;
    private String phone;
    private String note;
    private String account;
    private List<OrderSmallVO> orderSmallVO;
    
    public OrderVO() {
    }

    
    @Override
    public String toString() {
        return "OrderVO{" +
                "order_id=" + order_id +
                ", store_id=" + store_id +
                ", member_id=" + member_id +
                ", price=" + price +
                ", date=" + date +
                ", method=" + method +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public OrderVO(Integer order_id, Integer store_id, Integer member_id, Integer price, Date date, Integer method, Integer status, String name, String address, String phone, String note) {
        this.order_id = order_id;
        this.store_id = store_id;
        this.member_id = member_id;
        this.price = price;
        this.date = date;
        this.method = method;
        this.status = status;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.note = note;
    }

    
    


	public List<OrderSmallVO> getOrderSmallVO() {
		return orderSmallVO;
	}

	public void setOrderSmallVO(List<OrderSmallVO> orderSmallVO) {
		this.orderSmallVO = orderSmallVO;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getPay_method() {
		return pay_method;
	}

	public void setPay_method(Integer pay_method) {
		this.pay_method = pay_method;
	}

	public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

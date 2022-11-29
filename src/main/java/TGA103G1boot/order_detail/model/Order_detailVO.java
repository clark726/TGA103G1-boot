package TGA103G1boot.order_detail.model;

import java.io.Serializable;
import java.time.LocalDate;

import TGA103G1boot.common.Result;

public class Order_detailVO extends Result implements Serializable {
	private Integer order_detail_id;
    private Integer order_id;
    private Integer product_id;
    private Integer amount;
    private String product_name;
    private Integer status;
    //
    private Integer onePrice;
	private Integer total;
    private String storeName;
    private String orderName;
    private Integer method;
    private String address;
    private String phone;
	private LocalDate date;
  
   
	
	public Order_detailVO(Integer order_detail_id, String storeName, String orderName, String address, String phone,
			String product_name, Integer onePrice, Integer amount, Integer total, Integer method, Integer status,
			LocalDate date) {
		this.order_detail_id = order_detail_id;
		this.storeName = storeName;
		this.orderName = orderName;
		this.address = address;
		this.phone = phone;
		this.product_name = product_name;
		this.onePrice = onePrice;
		this.amount = amount;
		this.total = total;
		this.method = method;
		this.status = status;
		this.date = date;
	}
	
	
	public Integer getOrder_detail_id() {
		return order_detail_id;
	}


	public void setOrder_detail_id(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
	}


	public Integer getOnePrice() {
		return onePrice;
	}


	public void setOnePrice(Integer onePrice) {
		this.onePrice = onePrice;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getOrderName() {
		return orderName;
	}


	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}


	public Integer getMethod() {
		return method;
	}


	public void setMethod(Integer method) {
		this.method = method;
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


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public Order_detailVO() {
    	
    }
    
	
    public Order_detailVO(Integer order_id, Integer product_id, Integer amount) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.amount = amount;
	}

    
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}




package TGA103G1boot.order.model;

import java.io.Serializable;

public class OrderSmallVO implements Serializable {

	    private Integer storeId;
	    private Integer price;
	    private Integer count;
	    private String name;
	    private Integer productId;
	    private Integer allPrice;
	   
	    
	    public OrderSmallVO() {}


		
		@Override
		public String toString() {
			return "OrderSmallVO [storeId=" + storeId + ", price=" + price + ", count=" + count + ", name=" + name
					+ ", productId=" + productId + ", allPrice=" + allPrice + "]";
		}

		public Integer getAllPrice() {
			return allPrice;
		}



		public void setAllPrice(Integer allPrice) {
			this.allPrice = allPrice;
		}



		public Integer getStoreId() {
			return storeId;
		}

		public void setStoreId(Integer storeId) {
			this.storeId = storeId;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getProductId() {
			return productId;
		}

		public void setProductId(Integer productId) {
			this.productId = productId;
		}
	    
}

package TGA103G1boot.product.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import TGA103G1boot.product_img.Product_imgVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product" , catalog = "barjarjo")
public class ProductVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer product_id;
	private String name;
	private Integer price;
	private Integer store_id;
	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;
	private Integer type_id;
	private Integer stock;
	@Column(insertable = false)
	private Integer status;
	@Column(insertable = false)
	private LocalDateTime date;
	@Transient
	private List<Object> imgs;
	@Lob
	@Transient
	private Object img;
	@OneToMany
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private List<Product_imgVO> product_imgVO;
	
	
	
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public List<Object> getImgs() {
		return imgs;
	}
	public void setImgs(List<Object> imgs) {
		this.imgs = imgs;
	}
	public Object getImg() {
		return img;
	}
	public void setImg(Object img) {
		this.img = img;
	}
	public List<Product_imgVO> getProduct_imgVO() {
		return product_imgVO;
	}
	public void setProduct_imgVO(List<Product_imgVO> product_imgVO) {
		this.product_imgVO = product_imgVO;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

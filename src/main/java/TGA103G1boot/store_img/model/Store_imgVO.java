package TGA103G1boot.store_img.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import TGA103G1boot.common.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store_img", catalog = "barjarjo")
public class Store_imgVO extends Result implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer img_id;
	@Column(name = "store_id")
	private Integer storeId;
	@Column(insertable = false)
	private Timestamp date;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private String img;
	@Column(name = "status" , columnDefinition = "VARCHAR")
	private Integer status1;

	@Transient
	private String firstImg;
	@Transient
	private String secondImg;
	@Transient
	private String meanuImg;
	@Transient
	private String meanuImg2;
	@Transient
	private String imgstr;
	@Transient
	@Lob
	private byte[] ingbyte;
	@Transient
	private String account;
	@Transient
	private String dayoff;
	@Transient
	private String work_open;
	@Transient
	private String work_end;
	@Transient
	private String produce;
	@Transient
	private String name;

	public Store_imgVO(Integer img_id, Integer storeId, Timestamp date, String img) {
		super();
		this.img_id = img_id;
		this.storeId = storeId;
		this.date = date;
		this.img = img;
	}

	@Override
	public String toString() {
		return "Store_imgVO [img_id=" + img_id + ", storeId=" + storeId + ", date=" + date + ", img=" + img
				+ ", status1=" + status1 + ", firstImg=" + firstImg + ", secondImg=" + secondImg + ", meanuImg="
				+ meanuImg + ", meanuImg2=" + meanuImg2 + ", imgstr=" + imgstr + ", ingbyte=" + Arrays.toString(ingbyte)
				+ ", account=" + account + ", dayoff=" + dayoff + ", work_open=" + work_open + ", work_end=" + work_end
				+ ", produce=" + produce + ", name=" + name + "]";
	}

}

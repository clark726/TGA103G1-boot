package TGA103G1boot.order_detail.model;

import java.util.List;

public interface Order_detailDAO {

	public boolean insert(Integer order_id , Integer product_id , Integer amount);

	public boolean update(Order_detailVO obj);

	public boolean delete(Integer order_id, Integer product_id);

	public Order_detailVO findByPrimaryKey(Integer id);

	public List<Order_detailVO> getAllByOrderId(Integer order_id);
	
	public void updateStatus(Integer order_id , Integer status);

	public List<Order_detailVO>getAllBymember(Integer member_id);
	
	public List<Order_detailVO> getOneOrderDetail(Integer order_id);
}

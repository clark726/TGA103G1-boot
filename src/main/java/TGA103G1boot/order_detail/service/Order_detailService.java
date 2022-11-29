package TGA103G1boot.order_detail.service;

import java.util.List;

import TGA103G1boot.order_detail.model.Order_detailVO;

public interface Order_detailService {

	public boolean insert(Order_detailVO obj);

	public boolean update(Order_detailVO obj);

	public boolean delete(Integer order_id, Integer product_id);

	public Order_detailVO findByPrimaryKey(Integer id);

	public List<Order_detailVO> getAllByOrderId(Integer order_id);
	
	public Order_detailVO updateStatus(Order_detailVO vo);

	public List<Order_detailVO>getAllBymember(Integer integer);
	
	public List<Order_detailVO>getOneOrderDetail(Integer order_id);
}

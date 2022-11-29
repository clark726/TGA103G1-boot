package TGA103G1boot.order.model;

import java.util.List;

public interface OrderDAO {
	public List<OrderVO> getAllByStoreAccount(String account);

	public OrderVO getOrderByOrderId(String account ,Integer order_id);
	
	public List<OrderVO> getOrderBySataus(String account ,Integer status);
		
	public Integer insert(OrderVO obj);

	public boolean update(OrderVO obj);

	public boolean delete(Integer order_id);
}

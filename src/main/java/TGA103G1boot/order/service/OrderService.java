package TGA103G1boot.order.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import TGA103G1boot.order.model.OrderVO;

public interface OrderService {

	public List<OrderVO> getAllByStoreAccount(String account);

	public OrderVO getOrderByOrderId(OrderVO vo);

	public List<OrderVO> getOrderBySataus(String account ,Integer status);
	
	public OrderVO insert(OrderVO obj) throws UnsupportedEncodingException;

	public boolean update(OrderVO obj);

	public boolean delete(Integer order_id);
	
	public OrderVO ecPay(OrderVO orderVO) throws UnsupportedEncodingException;
}

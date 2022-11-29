package TGA103G1boot.order_detail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TGA103G1boot.order_detail.model.Order_detailDAO;
import TGA103G1boot.order_detail.model.Order_detailVO;
import TGA103G1boot.order_detail.service.Order_detailService;

@Service
public class Order_detailServiceImpl implements Order_detailService {
	@Autowired
	private Order_detailDAO orderDetailDao;

	@Override
	public boolean insert(Order_detailVO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Order_detailVO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer order_id, Integer product_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order_detailVO findByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_detailVO> getAllByOrderId(Integer order_id) {
		return orderDetailDao.getAllByOrderId(order_id);
	}

	@Override
	public Order_detailVO updateStatus(Order_detailVO vo) {
		Integer orderId = vo.getOrder_id();
		Integer status = vo.getStatus();

		if (orderId != null && status != null) {
			orderDetailDao.updateStatus(orderId, status);
			vo.setSuccessful(true);
		}
		return vo;
	}

	// 新增會員訂單明細
	@Override
	public List<Order_detailVO> getAllBymember(Integer member_id) {

		return orderDetailDao.getAllBymember(member_id);
	}

	@Override
	public List<Order_detailVO> getOneOrderDetail(Integer order_id) {

		return orderDetailDao.getOneOrderDetail(order_id);
	}

}

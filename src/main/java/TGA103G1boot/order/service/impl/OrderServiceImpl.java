package TGA103G1boot.order.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TGA103G1boot.order.model.OrderDAO;
import TGA103G1boot.order.model.OrderSmallVO;
import TGA103G1boot.order.model.OrderVO;
import TGA103G1boot.order.service.OrderService;
import TGA103G1boot.order_detail.model.Order_detailDAO;
import TGA103G1boot.product.model.ProductDAO;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private Order_detailDAO order_detailDAO;
	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<OrderVO> getAllByStoreAccount(String account) {
		return orderDao.getAllByStoreAccount(account);
	}

	@Override
	public OrderVO getOrderByOrderId(OrderVO orderVO) {
		String account = orderVO.getAccount();
		Integer order_id = orderVO.getOrder_id();
		// 搜尋沒有值
		OrderVO backOrderVO = orderDao.getOrderByOrderId(account, order_id);

		if (backOrderVO.getMember_id() == null) {
			orderVO.setSuccessful(false);
			orderVO.setMessage("沒有此訂單");
			return orderVO;
		} else {
			backOrderVO.setSuccessful(true);
			return backOrderVO;
		}

	}

	@Override
	public List<OrderVO> getOrderBySataus(String account, Integer status) {
		return orderDao.getOrderBySataus(account, status);
	}

	@Override
	public OrderVO insert(OrderVO obj) throws UnsupportedEncodingException {
		List<OrderSmallVO> list = obj.getOrderSmallVO();

		// 以storeId當成key做分類
		Map<Integer, List<OrderSmallVO>> map = new HashMap();
//		for (int x = 0; x < list.size(); x++) {
//			OrderSmallVO orderSmallVO = list.get(x);
//			if (map.containsKey(orderSmallVO.getStoreId())) {
//				ArrayList<OrderSmallVO> newlist2 = (ArrayList<OrderSmallVO>) map.get(orderSmallVO.getStoreId());
//				newlist2.add(orderSmallVO);
//			} else {
//				List<OrderSmallVO> newlist = new ArrayList<>();
//				newlist.add(orderSmallVO);
//				map.put(orderSmallVO.getStoreId(), newlist);
//			}
//		}
		// 以stream API方式
		map = list.stream().collect(Collectors.groupingBy(OrderSmallVO::getStoreId));

		// 算出商品總和放到第一個商品的allprice
//		for (Map.Entry<Integer, List<OrderSmallVO>> entry : map.entrySet()) {
//			int total = 0;
//			for (int x = 0; x < entry.getValue().size(); x++) {
//				total += entry.getValue().get(x).getAllPrice();
//				if (x == entry.getValue().size() - 1) {
//					entry.getValue().get(0).setAllPrice(total);
//				}
//			}
//		}
		// 以stream API方式
		for (List<OrderSmallVO> listvo : map.values()) {
			int total = listvo.stream().mapToInt(e -> e.getPrice() * e.getCount()).sum();
			listvo.get(0).setAllPrice(total);
		}

		Integer orderId = null;
		for (Map.Entry<Integer, List<OrderSmallVO>> entry : map.entrySet()) {
			obj.setStore_id(entry.getKey());
			obj.setPrice(entry.getValue().get(0).getAllPrice());
			orderId = orderDao.insert(obj);
			for (int i = 0; i < entry.getValue().size(); i++) {
				Integer amount = entry.getValue().get(i).getCount();
				Integer productId = entry.getValue().get(i).getProductId();
				order_detailDAO.insert(orderId, productId, amount);
				productDAO.updateStatus(productId, amount);
			}
		}
//		----------------------------綠界------------------------------------
		return obj;
	}

	@Override
	public boolean update(OrderVO obj) {
		return false;
	}

	@Override
	public boolean delete(Integer order_id) {
		return false;
	}

	@Override
	public OrderVO ecPay(OrderVO orderVO) throws UnsupportedEncodingException {
		return orderVO;

	}

}

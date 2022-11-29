package TGA103G1boot.order_detail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import TGA103G1boot.common.LocalDateAdapter;
import TGA103G1boot.order_detail.model.Order_detailVO;
import TGA103G1boot.order_detail.service.Order_detailService;
import TGA103G1boot.order_detail.service.impl.Order_detailServiceImpl;

@WebServlet("/GetOrderDetail")
public class GetOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
	private Order_detailService orderDetailSvc = new Order_detailServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Order_detailVO vo = _gson.fromJson(request.getReader().readLine(), Order_detailVO.class);
		Integer order_id = vo.getOrder_id();
		
		List<Order_detailVO> list = orderDetailSvc.getAllByOrderId(order_id);
		response.setContentType("application/json");
		try(PrintWriter pw = response.getWriter()){
			pw.print(_gson.toJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package TGA103G1boot.order.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import TGA103G1boot.order.model.OrderVO;
import TGA103G1boot.order.service.OrderService;
import TGA103G1boot.order.service.impl.OrderServiceImpl;

@WebServlet("/AddOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson _gson = new Gson();
	private OrderService orderSvc = new OrderServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		OrderVO orderVO = _gson.fromJson(request.getReader().readLine(), OrderVO.class);
//		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("userid");
//		Integer memberId = memberVO.getMember_id();
//		orderVO.setMember_id(memberId);
		OrderVO backVO = orderSvc.insert(orderVO);
		if (backVO.getMessage() != null) {
			orderVO.setSuccessful(true);
		} else {
			orderVO.setSuccessful(false);
			orderVO.setMessage("請聯絡店家");
		}
		response.setContentType("application/json");
		try (PrintWriter pw = response.getWriter()) {
			pw.print(new GsonBuilder().create().toJson(backVO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package TGA103G1boot.order_detail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

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

@WebServlet("/UpdateStatus")
public class UpdateStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Order_detailService OrderDetailSvc = new Order_detailServiceImpl();
	private Gson _gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Order_detailVO vo = _gson.fromJson(request.getReader().readLine(), Order_detailVO.class);
		
		OrderDetailSvc.updateStatus(vo);
		
		response.setContentType("application/json");
		try(PrintWriter pw = response.getWriter()){
			pw.print(_gson.toJson(vo));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

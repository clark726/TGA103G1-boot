package TGA103G1boot.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*" , maxAge = 3600 )
@RestController
@RequestMapping("/api")
public class StoreLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@GetMapping({ "/StoreLogout" })
	public  void storeLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
	}

}

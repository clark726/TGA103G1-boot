package TGA103G1boot.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TGA103G1boot.store.model.StoreVO;
import TGA103G1boot.store.service.StoreService;
import TGA103G1boot.store.service.impl.StoreServiceImpl;
@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StoreUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private StoreService storeSvc = new StoreServiceImpl();

	@PostMapping("/StoreUpdate")
	public ResponseEntity<Object> storeUpdate(@RequestBody StoreVO storeVO, HttpServletRequest request)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		StoreVO storevoId = (StoreVO) session.getAttribute("storeId");
		Integer storeId = storevoId.getStore_id();
		storeVO.setStore_id(storeId);
		storeSvc.updateStore(storeVO);
		return new ResponseEntity<Object>(storeVO, HttpStatus.OK);
	}

}

package TGA103G1boot.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TGA103G1boot.common.JwtUtil;
import TGA103G1boot.store.model.StoreVO;
import TGA103G1boot.store.service.StoreService;
import TGA103G1boot.store.service.impl.StoreServiceImpl;
import io.jsonwebtoken.Claims;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StoreUpdate {
	private static final long serialVersionUID = 1L;
	@Autowired
	private StoreService storeSvc = new StoreServiceImpl();

	@PostMapping("/StoreUpdate")
	public ResponseEntity<Object> storeUpdate(@RequestBody StoreVO storeVO,
			@RequestHeader(value = "Authorization") String jwt) throws ServletException, IOException {
		Claims claims = JwtUtil.getClaimsFromToken(jwt);
		Integer storeId = Integer.valueOf(claims.getSubject());
		storeVO.setStore_id(storeId);
		storeSvc.updateStore(storeVO);
		return new ResponseEntity<Object>(storeVO, HttpStatus.OK);
	}

}

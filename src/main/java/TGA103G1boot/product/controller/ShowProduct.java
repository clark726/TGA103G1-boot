package TGA103G1boot.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TGA103G1boot.common.JwtUtil;
import TGA103G1boot.product.model.ProductVO;
import TGA103G1boot.product.service.ProductService;
import TGA103G1boot.store.model.StoreVO;
import TGA103G1boot.store.service.StoreService;
import io.jsonwebtoken.Claims;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ShowProduct {
	@Autowired
	private ProductService productSvc;
	@Autowired
	private StoreService storeSvc;

	@GetMapping({ "/ShowProduct" })
	protected ResponseEntity<Object> showProduct(@RequestHeader(value="Authorization") String token) {
		Claims claims = JwtUtil.getClaimsFromToken(token);
		List<ProductVO> Productlist = productSvc.ShowStoreProduct(claims.getSubject());
//		StoreVO storeId = storeSvc.findStoreAccount(store.getAccount());
//		session.setAttribute("storeId", storeId);
//		session.setAttribute("Productlist", Productlist);
		return new ResponseEntity<Object>(Productlist, HttpStatus.OK);
	}

}

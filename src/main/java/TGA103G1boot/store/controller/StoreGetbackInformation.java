package TGA103G1boot.store.controller;

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
import TGA103G1boot.store.model.StoreVO;
import TGA103G1boot.store_img.model.Store_imgVO;
import TGA103G1boot.store_img.service.Store_imgService;
import io.jsonwebtoken.Claims;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StoreGetbackInformation {
	@Autowired
	private Store_imgService storeImgSvc;

	@GetMapping({ "/StoreGetbackInformation" })
	public ResponseEntity<Object> storeGetbackInformation(@RequestHeader(value = "Authorization") String token) {
		System.out.println(token);
		Claims claims = JwtUtil.getClaimsFromToken(token);
		List<Store_imgVO> list = storeImgSvc.getbackInformation(claims.getSubject());
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

}

package TGA103G1boot.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TGA103G1boot.product.model.ProductVO;
import TGA103G1boot.product.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class GetProductByTypeId {
	@Autowired
	private ProductService productSvc;

	@GetMapping(path = { "/GetProductByTypeId" })
	protected ResponseEntity<Object> getProductByTypeId() {
		List<ProductVO> list = productSvc.getAll();
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	@PostMapping(path = "/GetProductByTypeId")
	protected ResponseEntity<Object> getProductByTypeId(@RequestBody ProductVO productVO) {
		Integer type_id = productVO.getType_id();
		List<ProductVO> list = productSvc.getProductBytypeId(type_id);
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

}



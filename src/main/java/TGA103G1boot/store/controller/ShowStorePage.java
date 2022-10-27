package TGA103G1boot.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import TGA103G1boot.product_img.Product_imgService;
import TGA103G1boot.store.model.StoreVO;
import TGA103G1boot.store.service.StoreService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ShowStorePage {
	@Autowired
	private StoreService storeSvc;
	@Autowired
	private Product_imgService imgService;

	@GetMapping({ "/ShowStorePage" })
	public byte[] showStorePage(@RequestParam("action") String action, @RequestParam("product_id") Integer product_id) {
		return imgService.findByProductID(product_id);

	}

	@PostMapping({ "/ShowStorePage" })
	protected ResponseEntity<Object> showStorePage(@RequestBody StoreVO storeVO) {

		Integer store_id = storeVO.getStore_id();
		List<StoreVO> list = storeSvc.findStorepageByStoreId(store_id);
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
}

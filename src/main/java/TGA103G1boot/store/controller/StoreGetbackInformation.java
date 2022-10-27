package TGA103G1boot.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TGA103G1boot.store_img.model.Store_imgVO;
import TGA103G1boot.store_img.service.Store_imgService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StoreGetbackInformation {
	@Autowired
	private Store_imgService storeImgSvc;

	@PostMapping({ "/StoreGetbackInformation" })
	public ResponseEntity<Object> storeGetbackInformation(@RequestBody Store_imgVO store_imgVO) {
		List<Store_imgVO> list = storeImgSvc.getbackInformation(store_imgVO.getAccount());
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

}

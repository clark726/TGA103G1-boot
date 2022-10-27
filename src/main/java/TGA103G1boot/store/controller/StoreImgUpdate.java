package TGA103G1boot.store.controller;

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
import TGA103G1boot.store_img.model.Store_imgVO;
import TGA103G1boot.store_img.service.Store_imgService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class StoreImgUpdate {
	@Autowired
	private Store_imgService storeImgSvc;

	@PostMapping({ "/StoreImgUpdate" })
	public ResponseEntity<Object> storeImgUpdate(@RequestBody Store_imgVO storeimg, HttpSession session) {
		StoreVO storevoId = (StoreVO) session.getAttribute("storeId");
		Integer storeId = storevoId.getStore_id();
		storeimg.setStoreId(storeId);
		storeImgSvc.insert(storeimg);
		return new ResponseEntity<Object>(storeimg, HttpStatus.OK);
	}

}

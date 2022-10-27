package TGA103G1boot.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TGA103G1boot.store.model.StoreVO;
import TGA103G1boot.store.service.StoreService;
@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StoreUpdateInfromation {
	@Autowired
	private StoreService storeSvc;

	@RequestMapping({ "/StoreUpdateInfromation" })
	public ResponseEntity<Object> storeUpdateInfromation(@RequestBody StoreVO storeVO) {

		StoreVO backStoreVO = storeSvc.updateStoreInformation(storeVO);
		return new ResponseEntity<Object>(backStoreVO, HttpStatus.OK);
	}

}

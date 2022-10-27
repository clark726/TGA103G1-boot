package TGA103G1boot.store.controller;

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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ShowStoreInformation {
	@Autowired
	private StoreService StoreSvc;

	@PostMapping({ "/ShowStoreInformation" })
	public ResponseEntity<Object> showStoreInformation(@RequestBody StoreVO storeVO) {

		String account = storeVO.getAccount();
		StoreVO storevo = StoreSvc.findStoreAccount(account);
		return new ResponseEntity<Object>(storevo, HttpStatus.OK);
	}

}

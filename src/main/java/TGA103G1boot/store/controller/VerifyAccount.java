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

@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class VerifyAccount {
	@Autowired
	private StoreService StoreSvc;

	@RequestMapping({ "/VerifyAccount" })
	public ResponseEntity<Object> verifyAccount(@RequestBody StoreVO store) {
		// 判斷是否重複
		if (StoreSvc.findStoreAccount(store.getAccount()) != null) {
			store.setSuccessful(false);
			store.setMessage("帳號重複");
		} else {
			store.setSuccessful(true);
			store.setMessage("此帳號可以使用");
		}
		return new ResponseEntity<Object>(store, HttpStatus.OK);
	}

}

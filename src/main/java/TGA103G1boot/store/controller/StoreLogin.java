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
public class StoreLogin {
	@Autowired
	private StoreService StoreSvc;

	@PostMapping("/StoreLogin")
	public ResponseEntity<Object> storeLogin(@RequestBody StoreVO store) {
		StoreSvc.login(store);
		String jwsts = StoreSvc.verifyUser(store.getAccount());
		store.setJwts(jwsts);
		return new ResponseEntity<Object>(store, HttpStatus.OK);
	}
}

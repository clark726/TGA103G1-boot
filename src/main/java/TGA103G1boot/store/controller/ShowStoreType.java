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

import TGA103G1boot.store.model.StoreVO;
import TGA103G1boot.store.service.StoreService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ShowStoreType {
	@Autowired
	private StoreService storeSvc;

	@PostMapping({ "/ShowStoreType" })
	protected ResponseEntity<Object> showStoreType(@RequestBody StoreVO storeVO) {

		List<StoreVO> list = storeSvc.findStoreFrontpageBythemeId(storeVO.getTheme_id());
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

}

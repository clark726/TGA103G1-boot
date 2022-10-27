package TGA103G1boot.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TGA103G1boot.store.model.StoreVO;
import TGA103G1boot.store.service.impl.StoreServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class ChangeTheme {

	@Autowired
	private StoreServiceImpl svc;

	@RequestMapping({ "/ChangeTheme" })
	public ResponseEntity<Object> changeTheme(@RequestBody StoreVO storeVO) {
		Integer theme_id = storeVO.getTheme_id();
		if (theme_id == 4) {
			List<StoreVO> list = svc.getAllTheme();
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			List<StoreVO> list = svc.getTheme(theme_id);
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}

}

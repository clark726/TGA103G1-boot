package TGA103G1boot.store.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TGA103G1boot.store.model.StoreDAO;
import TGA103G1boot.store.model.StoreVO;
import TGA103G1boot.store.service.StoreService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreDAO storedao;

	public boolean addStore(StoreVO storevo) {

		if (storedao.findStoreAccount(storevo.getAccount()) == null) {
			storedao.insert(storevo);
			return true;
		}
		return false;

	}

	@Override
	public StoreVO storeSumit(StoreVO vo) {

		String phone = vo.getPhone();
		String email = vo.getEmail();
		String emailRex = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
		String phoneRex = "\\d{10}";
		String address = vo.getAddress();

		if ("".equals(address.trim())) {
			vo.setMessage("地址請勿空白");
			vo.setSuccessful(false);
			return vo;
		}
		if ("".equals(phone.trim())) {
			vo.setMessage("電話請勿空白");
			vo.setSuccessful(false);
			return vo;
		} else if (!phone.trim().matches(phoneRex)) {
			vo.setMessage("電話請輸入正確");
			vo.setSuccessful(false);
			return vo;
		}
		if ("".equals(email)) {
			vo.setMessage("Email請勿空白");
			vo.setSuccessful(false);
			return vo;
		} else if (!email.trim().matches(emailRex)) {
			vo.setMessage("Email請符合格式");
			vo.setSuccessful(false);
			return vo;
		}

		if (!(storedao.findStoreAccount(vo.getAccount()) == null)) {
			vo.setSuccessful(false);
			vo.setMessage("重複帳號");
			return vo;
		}

		storedao.insert(vo);
		vo.setSuccessful(true);
		return vo;

	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addStore2(StoreVO storevo) {
		storedao.insert(storevo);
	}

	public StoreVO updateStore(StoreVO store) {

		String dayoff = store.getDayoff();
		String produce = store.getProduce();
		String work_open = store.getWork_open();
		String work_end = store.getWork_end();

		if ("".equals(dayoff)) {
			store.setMessage("公休請勿空白");
			store.setSuccessful(false);
			return store;
		}
		if ("".equals(produce)) {
			store.setMessage("介紹請勿空白");
			store.setSuccessful(false);
			return store;
		}

		storedao.updateProduce(store);
		store.setSuccessful(true);
		return store;
	}

	public void deleteStore(Integer store_id) {
		storedao.delete(store_id);
	}

	public StoreVO getOneStore(Integer store_id) {
		return storedao.findByPrimaryKey(store_id);
	}

	public List<StoreVO> getAllStore() {
		return storedao.getAll();
	}

	public StoreVO login(StoreVO vo) {

		String account = vo.getAccount();
		String password = vo.getPassword();

		if ("".equals(account)) {
			vo.setMessage("帳號未輸入");
			vo.setSuccessful(false);
			return vo;
		}
		if ("".equals(password)) {
			vo.setMessage("密碼未輸入");
			vo.setSuccessful(false);
			return vo;
		}
		if (storedao.Login(account, password) == null) {
			vo.setSuccessful(false);
			vo.setMessage("帳號或密碼錯誤！");
			return vo;
		} else {
			vo.setSuccessful(true);
		}

		return vo;
	}

	public List<StoreVO> findStorepageByStoreId(Integer store_id) {
		return storedao.findStorepageByStoreId(store_id);
	}

	@Override
	public StoreVO findStoreAccount(String account) {
		return storedao.findStoreAccount(account);
	}

	@Override
	public List<StoreVO> findStoreFrontpageBythemeId(Integer themeId) {

		return storedao.findStoreFrontpageBythemeId(themeId);
	}

	@Override
	public StoreVO updateStoreInformation(StoreVO vo) {
		String phone = vo.getPhone();
		String email = vo.getEmail();
		String emailRex = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
		String phoneRex = "\\d{10}";

		if ("".equals(phone.trim())) {
			vo.setMessage("電話請勿空白");
			vo.setSuccessful(false);
			return vo;
		} else if (!phone.trim().matches(phoneRex)) {
			vo.setMessage("電話請輸入正確");
			vo.setSuccessful(false);
			return vo;
		}
		if ("".equals(email)) {
			vo.setMessage("Email請勿空白");
			vo.setSuccessful(false);
			return vo;
		} else if (!email.trim().matches(emailRex)) {
			vo.setMessage("Email請符合格式");
			vo.setSuccessful(false);
			return vo;
		}

		storedao.updateStoreInformation(vo);
		vo.setSuccessful(true);
		return vo;
	}

	// MAP
	public List<StoreVO> getAllTheme() {
		return storedao.getAll();
	}

	public List<StoreVO> getTheme(Integer theme_id) {
		return storedao.getByTheme(theme_id);
	}

	public String verifyUser(String userAcct) {
		/*
		 * 0000 login success 0001 wrong email 0002 wrong useracct 0003 wrong passwd
		 * 0004 acct locked
		 */
		Optional<StoreVO> user = Optional.of(storedao.findStoreAccount(userAcct));
		String result = "9999";
		if (user.isPresent()) {
			if (user.get().getAccount().equals(userAcct)) {
				// 新增了以下這段：
				Date expireDate =
						// 設定30min過期
						new Date(System.currentTimeMillis() + 30 * 60 * 1000);
				String jwtToken = Jwts.builder().setSubject(userAcct) // 我以email當subject
						.setExpiration(expireDate)
						// MySecret是自訂的私鑰，HS512是自選的演算法，可以任意改變
						.signWith(SignatureAlgorithm.HS512, "MySecret").compact();
				// 直接將JWT傳回
				result = jwtToken;
			} else {
				result = "0002";
			}
		} else {
			result = "0001";
		}
		return result;
	}

}

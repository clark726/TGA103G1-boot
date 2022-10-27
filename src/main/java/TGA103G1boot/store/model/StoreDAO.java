package TGA103G1boot.store.model;

import java.util.List;

public interface StoreDAO {

	public boolean insert(StoreVO storeVo);
	public void update(StoreVO storeVo);
	public void delete(Integer store_id);
	public StoreVO findByPrimaryKey(Integer store_id);
	public List<StoreVO> getAll();
	public StoreVO findStoreAccount(String account);
	public StoreVO Login(String account , String password);
	public void updateProduce(StoreVO storevo);
	public List<StoreVO> findStoreFrontpageBythemeId(Integer themeId);
	public List<StoreVO> findStorepageByStoreId(Integer store_id);
	public void updateStoreInformation(StoreVO storeVO);
	//MAP
	public List<StoreVO> getAllTheme();
	public List<StoreVO> getByTheme(Integer theme_id);
}

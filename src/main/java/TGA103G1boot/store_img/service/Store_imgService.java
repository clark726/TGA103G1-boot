package TGA103G1boot.store_img.service;

import java.util.List;

import TGA103G1boot.store_img.model.Store_imgVO;

public interface Store_imgService {

	public Store_imgVO insert(Store_imgVO img);
	public void update(Store_imgVO img);
	public List<Store_imgVO> getbackInformation(String account);
	public List<Store_imgVO> findStorepageImgByStoreId(Integer store_id);
}

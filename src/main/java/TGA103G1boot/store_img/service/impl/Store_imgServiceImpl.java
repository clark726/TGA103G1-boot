package TGA103G1boot.store_img.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TGA103G1boot.store_img.model.Store_imgDAO;
import TGA103G1boot.store_img.model.Store_imgVO;
import TGA103G1boot.store_img.service.Store_imgService;

@Service
@Transactional
public class Store_imgServiceImpl implements Store_imgService {
	@Autowired
	private Store_imgDAO storeimgdao;

	@Override
	public Store_imgVO insert(Store_imgVO img) {

		Store_imgVO imgmain = new Store_imgVO();
		imgmain.setImg(img.getImg());
		imgmain.setStoreId(img.getStoreId());
		imgmain.setStatus1(1);
		// 看是否重複
		if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 1) == null && img.getImg() == null) {
			img.setSuccessful(false);
			img.setMessage("初次更新請完整選擇圖片");
			return img;
		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 1) != null && img.getImg() != null) {
			storeimgdao.update(imgmain);

		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 1) == null && img.getImg() != null) {
			storeimgdao.insert(imgmain);

		}

		Store_imgVO imgfirst = new Store_imgVO();
		imgfirst.setImg(img.getFirstImg());
		imgfirst.setStoreId(img.getStoreId());
		imgfirst.setStatus1(2);
		// 看是否重複
		if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 2) == null && img.getFirstImg() == null) {
			img.setSuccessful(false);
			img.setMessage("初次更新請完整選擇圖片");
			return img;
		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 2) != null && img.getFirstImg() != null) {
			storeimgdao.update(imgfirst);

		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 2) == null && img.getFirstImg() != null) {
			storeimgdao.insert(imgfirst);

		}

		Store_imgVO imgsecond = new Store_imgVO();
		imgsecond.setImg(img.getSecondImg());
		imgsecond.setStoreId(img.getStoreId());
		imgsecond.setStatus1(3);
		// 看是否重複
		if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 3) == null && img.getSecondImg() == null) {
			img.setSuccessful(false);
			img.setMessage("初次更新請完整選擇圖片");
			return img;
		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 3) != null && img.getSecondImg() != null) {
			storeimgdao.update(imgsecond);

		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 3) == null && img.getSecondImg() != null) {
			storeimgdao.insert(imgsecond);

		}

		Store_imgVO meanu1 = new Store_imgVO();
		meanu1.setImg(img.getMeanuImg());
		meanu1.setStoreId(img.getStoreId());
		meanu1.setStatus1(4);
		// 看是否重複
		if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 4) == null && img.getMeanuImg() == null) {
			img.setSuccessful(false);
			img.setMessage("初次更新請完整選擇圖片");
			return img;
		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 4) != null && img.getMeanuImg() != null) {
			storeimgdao.update(meanu1);

		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 4) == null && img.getMeanuImg() != null) {
			storeimgdao.insert(meanu1);

		}

		Store_imgVO meanu2 = new Store_imgVO();
		meanu2.setImg(img.getMeanuImg2());
		meanu2.setStoreId(img.getStoreId());
		meanu2.setStatus1(5);
		// 看是否重複
		if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 5) == null && img.getMeanuImg2() == null) {
			img.setSuccessful(false);
			img.setMessage("初次更新請完整選擇圖片");
			return img;
		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 5) != null && img.getMeanuImg2() != null) {
			storeimgdao.update(meanu2);

		} else if (storeimgdao.findImgByStoreIdandSratus(img.getStoreId(), 5) == null && img.getMeanuImg2() != null) {
			storeimgdao.insert(meanu2);

		}

		img.setSuccessful(true);
		return img;

	}

	@Override
	public List<Store_imgVO> getbackInformation(String account) {
		return storeimgdao.getbackInformation(account);
	}

	@Override
	public List<Store_imgVO> findStorepageImgByStoreId(Integer store_id) {

		return storeimgdao.findStorepageImgByStoreId(store_id);
	}

	@Override
	public void update(Store_imgVO img) {

	}

}

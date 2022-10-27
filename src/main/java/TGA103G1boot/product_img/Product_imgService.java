package TGA103G1boot.product_img;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Product_imgService {
	@Autowired
	private Product_imgDAO productimgdao;

	public Product_imgVO addimg(Integer product_id, byte[] img) {

		Product_imgVO imgvo = new Product_imgVO();
		imgvo.setProduct_id(product_id);
		imgvo.setImg(img);

		productimgdao.insert(imgvo);

		return imgvo;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addimg(Product_imgVO imgvo) {
		productimgdao.insert(imgvo);
	}

	public void delete(Integer img_id) {
		productimgdao.delete(img_id);
	}

	public List<Product_imgVO> getAll() {
		List<Product_imgVO> list = productimgdao.getAll();
		for (Product_imgVO list2 : list) {
			String base64Str = Base64.getEncoder().encodeToString(list2.getImg());
			Product_imgVO imgvo = new Product_imgVO();
			imgvo.setImgobj(base64Str);
			list.add(imgvo);
		}
		return list;
	}

	public byte[] findByProductID(Integer product_id) {
		return productimgdao.findByProductID(product_id).getImg();
	}

	public void update(Product_imgVO vo) {

		productimgdao.update(vo);
	}

}

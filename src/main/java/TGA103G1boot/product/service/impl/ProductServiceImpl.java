package TGA103G1boot.product.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TGA103G1boot.product.model.ProductDAO;
import TGA103G1boot.product.model.ProductVO;
import TGA103G1boot.product.service.ProductService;
import TGA103G1boot.product_img.Product_imgDAO;
import TGA103G1boot.product_img.Product_imgVO;
import TGA103G1boot.store.model.StoreDAO;
import TGA103G1boot.store.model.StoreVO;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productdao;
	@Autowired
	private Product_imgDAO imgdao;
	@Autowired
	private StoreDAO storedao;

	public ProductVO addProduct(ProductVO productVO, List<Product_imgVO> imgList) {

		Integer product_id = productdao.insert(productVO);
		for (Product_imgVO imgVO : imgList) {
			imgVO.setProduct_id(product_id);
			imgdao.insert(imgVO);
		}
		return productVO;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addProduct(ProductVO productvo) {
		productdao.insert(productvo);
	}

	public ProductVO updatProduct(ProductVO productvo ,List<Product_imgVO> imgList ) {

	
		Integer product_id = productdao.update(productvo);
		for (Product_imgVO imgVO : imgList) {
			imgVO.setProduct_id(product_id);
			imgdao.update(imgVO);
		}
		
		return productvo;
	}

	public void delete(Integer product_id) {
		productdao.delete(product_id);
	}


	public ProductVO getOneProduct(Integer product_id) {
		return productdao.findByPrimaryKey(product_id);
	}
	
	@Override
	public List<ProductVO> ShowStoreProduct(String account) {
		StoreVO store = storedao.findStoreAccount(account);
		List<ProductVO> list = productdao.ShowStoreProduct(store.getStore_id());
		return list;
	}

	
	// object 轉 byte[]
	public byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = productdao.getAll();
		for (ProductVO vo : list) {
			String base64Str = Base64.getEncoder().encodeToString(toByteArray(vo.getImg()));
			vo.setImg((String)base64Str);
		}

		return list;
	}

	@Override
	public boolean updateStatus(Integer id, Integer status) {
		return productdao.updateStatus(id,status);
	}

	@Override
	public List<ProductVO> getProductBytypeId(Integer type_id) {
		return productdao.getProductBytypeId(type_id);
	}
}

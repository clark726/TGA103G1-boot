package TGA103G1boot.product.service;

import java.util.List;

import TGA103G1boot.product.model.ProductVO;
import TGA103G1boot.product_img.Product_imgVO;

public interface ProductService {

	public ProductVO addProduct(ProductVO productVO, List<Product_imgVO> imgList);
	//Spring MVC
	public void addProduct(ProductVO productvo);
	public ProductVO updatProduct(ProductVO productvo ,List<Product_imgVO> imgList );
	public void delete(Integer product_id);
	public ProductVO getOneProduct(Integer product_id);
	public List<ProductVO> ShowStoreProduct(String account);
	public List<ProductVO> getAll();
	public boolean updateStatus(Integer id,Integer status);
	public List<ProductVO> getProductBytypeId(Integer type_id);
}

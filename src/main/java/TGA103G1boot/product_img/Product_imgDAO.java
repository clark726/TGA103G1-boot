package TGA103G1boot.product_img;

import java.util.List;

public interface Product_imgDAO {

	public void insert(Product_imgVO img);
	public void update(Product_imgVO img);
	public void delete(Integer img_id);
	public Product_imgVO findByProductID(Integer prd_id);
	public List<Product_imgVO>getAll();
	
	
}

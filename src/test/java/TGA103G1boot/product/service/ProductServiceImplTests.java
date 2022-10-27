package TGA103G1boot.product.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import TGA103G1boot.product.model.ProductVO;
import TGA103G1boot.product.service.impl.ProductServiceImpl;

@SpringBootTest
public class ProductServiceImplTests  {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Test
	public void selectCustomer() {
		ProductVO productVO = productServiceImpl.getOneProduct(125);
		System.out.println(productVO.getName());
	}

}

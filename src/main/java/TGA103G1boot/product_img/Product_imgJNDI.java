package TGA103G1boot.product_img;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class Product_imgJNDI implements Product_imgDAO {

	@PersistenceContext
	Session session = null;

	public Session getSession() {
		return session;
	}

	@Override
	public void insert(Product_imgVO img) {

		try {
			this.getSession().persist(img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Product_imgVO product_imgVO) {

		try {
			this.getSession().createQuery("Update Product_imgVO set img = :img where product_id = :product_id")
					.setParameter("img", product_imgVO.getImg())
					.setParameter("product_id", product_imgVO.getProduct_id()).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer img_id) {

		try {
			Product_imgVO productVO = this.getSession().load(Product_imgVO.class, img_id);
			this.getSession().remove(productVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product_imgVO findByProductID(Integer prd_id) {

		try {
			Query<Product_imgVO> query = this.getSession()
					.createQuery("From Product_imgVO where product_id = :product_id", Product_imgVO.class)
					.setParameter("product_id", prd_id);
			Product_imgVO vo = query.uniqueResult();
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Product_imgVO> getAll() {
		try {
			Query<Product_imgVO> query = this.getSession().createQuery("From Product_imgVO ", Product_imgVO.class);
			List<Product_imgVO> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

package TGA103G1boot.product.model;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJNDI implements ProductDAO {

	@PersistenceContext
	Session session = null;

	public Session getSession() {
		return session;
	}

	@Override
	public Integer insert(ProductVO productVO) {
		this.getSession().persist(productVO);
		return productVO.getProduct_id();

	}

	@Override
	public Integer update(ProductVO newProductVO) {
		try {
			ProductVO product = this.getSession().load(ProductVO.class, newProductVO.getProduct_id());

			product.setName(newProductVO.getName());
			product.setPrice(newProductVO.getPrice());
			product.setStore_id(newProductVO.getStore_id());
			product.setDescription(newProductVO.getDescription());
			product.setType_id(newProductVO.getType_id());
			product.setStock(newProductVO.getStock());
			product.setStatus(newProductVO.getStatus());
			return product.getProduct_id();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void delete(Integer product_id) {

		try {
			ProductVO productVO = this.getSession().load(ProductVO.class, product_id);
			this.getSession().remove(productVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ProductVO findByPrimaryKey(Integer product_id) {

		try {
			Query<ProductVO> query3 = this.getSession()
					.createQuery("From ProductVO where product_id = :product_id", ProductVO.class)
					.setParameter("product_id", product_id);
			ProductVO vo = query3.uniqueResult();
			return vo;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProductVO> ShowStoreProduct(Integer store_id) {
		try {
			Query<ProductVO> query3 = this.getSession()
					.createQuery("From ProductVO where store_id = :store_id", ProductVO.class)
					.setParameter("store_id", store_id);
			List<ProductVO> list3 = query3.list();
			return list3;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProductVO> getAll() {
		try {
			Query<ProductVO> query = this.getSession().createQuery("From ProductVO", ProductVO.class);
			List<ProductVO> productVO = query.list();

			return productVO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateStatus(Integer id, Integer status) {
		int row = 0;
		try {
			row = this.getSession().createQuery("Update ProductVO set status = :status where product_id = :product_id")
					.setParameter("status", status).setParameter("product_id", id).executeUpdate();
			return row != 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<ProductVO> getProductBytypeId(Integer type_id) {
		try {
			Query<ProductVO> query = this.getSession()
					.createQuery("From ProductVO where type_id = :type_id", ProductVO.class)
					.setParameter("type_id", type_id);
			List<ProductVO> list = query.list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

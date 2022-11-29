package TGA103G1boot.order_detail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Order_detailJNDI implements Order_detailDAO {
	@Autowired
	private static DataSource ds;

	@Override
	public boolean update(Order_detailVO obj) {
		int rowCount = 0;
		String sql = "Update order_detail set amount = ? where order_id = ? and product_id = ? ;";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, obj.getAmount());
			ps.setInt(2, obj.getOrder_id());
			ps.setInt(3, obj.getProduct_id());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount == 1;
	}

	@Override
	public boolean delete(Integer order_id, Integer product_id) {
		int rowCount = 0;
		String sql = "Delete from order_detail where order_id = ? and product_id = ?;";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, order_id);
			ps.setInt(2, product_id);

			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount == 1;
	}

	@Override
	public Order_detailVO findByPrimaryKey(Integer id) {
		Order_detailVO od = null;

		String sql = "select order_id, product_id, amount " + " from order_detail where order_id = ? ;";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer order_id = rs.getInt(1);
				Integer product_id = rs.getInt(2);
				Integer amount = rs.getInt(3);
				od = new Order_detailVO(order_id, product_id, amount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return od;
	}

	@Override
	public List<Order_detailVO> getAllByOrderId(Integer order_id) {
		List<Order_detailVO> list = new ArrayList<Order_detailVO>();

		String sql = "select d.order_id , d.product_id , p.name product_name , d.amount , d.status  from product p\n"
				+ "	join(SELECT o.order_id , o.status , d.amount , d.product_id FROM `order` o\n"
				+ "				join order_detail d\n" + "				on o.order_id = d.order_id) d\n"
				+ "	on p.product_id = d.product_id\n" + "where d.order_id = ?;";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, order_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Order_detailVO vo = new Order_detailVO();
				vo.setOrder_id(rs.getInt("order_id"));
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setAmount(rs.getInt("amount"));
				vo.setStatus(rs.getInt("status"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void updateStatus(Integer order_id, Integer status) {
		String sql = "UPDATE `order` SET `status` = ? WHERE (`order_id` = ?);";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, status);
			ps.setInt(2, order_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 新增訂單明細

	@Override
	public List<Order_detailVO> getAllBymember(Integer member_id) {
		String sql = "SELECT o.order_id,o.status status,o.price total,s.name stordName,o.`date` date FROM `order` o "
				+ "	join member m " + " on m.member_id = o.member_id " + " join store s  "
				+ " on s.store_id = o.store_id " + " where m.member_id = ?;";
		List<Order_detailVO> list = new ArrayList<Order_detailVO>();
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, member_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order_detailVO od = new Order_detailVO();
				od.setOrder_id(rs.getInt("order_id"));
				od.setStoreName(rs.getString("stordName"));
				od.setTotal(rs.getInt("total"));
				od.setStatus(rs.getInt("status"));
				;
				od.setDate(rs.getObject("date", LocalDate.class));
				list.add(od);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Order_detailVO> getOneOrderDetail(Integer order_id) {
		String sql = "SELECT o.order_id orderId,s.name storeName,o.`name` orderName,o.address address,o.phone phone,p.`name` productName, "
				+ " p.price onePrice,od.amount amount,o.price Total,o.method method,o.status `status`,o.`date` `date` FROM `order` o "
				+ " join store s  " + " on s.store_id = o.store_id " + " join order_detail od "
				+ " on o.order_id = od.order_id " + " join product p " + "	on p.product_id = od.product_id "
				+ "	where o.order_id = ?;";
		List<Order_detailVO> list = new ArrayList<Order_detailVO>();
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, order_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order_detailVO od = new Order_detailVO();
				od.setOrder_id(rs.getInt("orderId"));
				od.setStoreName(rs.getString("storeName"));
				od.setOrderName(rs.getString("orderName"));
				od.setAddress(rs.getString("address"));
				od.setPhone(rs.getString("phone"));
				od.setProduct_name(rs.getString("productName"));
				od.setOnePrice(rs.getInt("onePrice"));
				od.setAmount(rs.getInt("amount"));
				od.setTotal(rs.getInt("total"));
				od.setMethod(rs.getInt("method"));
				od.setStatus(rs.getInt("status"));
				od.setDate(rs.getObject("date", LocalDate.class));
				list.add(od);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insert(Integer order_id, Integer product_id, Integer amount) {
		int rowCount = 0;
		String sql = "insert into order_detail(order_id, product_id, amount) values(? ,? ,?);";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, order_id);
			ps.setInt(2, product_id);
			ps.setInt(3, amount);

			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount == 1;
	}
}

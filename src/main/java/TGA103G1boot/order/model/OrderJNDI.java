package TGA103G1boot.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.dialect.identity.GetGeneratedKeysDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderJNDI implements OrderDAO {
	@Autowired
	private static DataSource ds;

	@Override
	public List<OrderVO> getAllByStoreAccount(String account) {
		List<OrderVO> orderVOS = new ArrayList<>();
		String sql = "SELECT order_id , member_id, price, method, status , pay_method , o.name , o.address , date , o.phone , note , s.account FROM `order` o\n"
				+ "	join store s\n" + "    on o.store_id = s.store_id\n" + "where s.account = ?;";
		try (Connection conn = ds.getConnection(); PreparedStatement ppst = conn.prepareStatement(sql)) {
			ppst.setString(1, account);
			ResultSet rs = ppst.executeQuery();
			while (rs.next()) {
				OrderVO orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getInt("order_id"));
				orderVO.setMember_id(rs.getInt("member_id"));
				orderVO.setPrice(rs.getInt("price"));
				orderVO.setMethod(rs.getInt("method"));
				orderVO.setStatus(rs.getInt("status"));
				orderVO.setPay_method(rs.getInt("pay_method"));
				orderVO.setName(rs.getString("name"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setDate(rs.getDate("date"));
				orderVO.setPhone(rs.getString("phone"));
				orderVO.setNote(rs.getString("note"));
				orderVOS.add(orderVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderVOS;
	}

	@Override
	public OrderVO getOrderByOrderId(String account, Integer order_id) {
		OrderVO orderVO = new OrderVO();
		String sql = "SELECT order_id , member_id, price, method, status , pay_method , o.name , o.address , date , o.phone , note , s.account FROM `order` o\n"
				+ "	join store s\n" + "    on o.store_id = s.store_id\n" + "where s.account = ? and order_id = ?;";
		try (Connection conn = ds.getConnection(); PreparedStatement ppst = conn.prepareStatement(sql)) {
			ppst.setString(1, account);
			ppst.setInt(2, order_id);
			ResultSet rs = ppst.executeQuery();
			while (rs.next()) {
				orderVO.setOrder_id(rs.getInt("order_id"));
				orderVO.setMember_id(rs.getInt("member_id"));
				orderVO.setPrice(rs.getInt("price"));
				orderVO.setMethod(rs.getInt("method"));
				orderVO.setStatus(rs.getInt("status"));
				orderVO.setPay_method(rs.getInt("pay_method"));
				orderVO.setName(rs.getString("name"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setDate(rs.getDate("date"));
				orderVO.setPhone(rs.getString("phone"));
				orderVO.setNote(rs.getString("note"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderVO;
	}

	@Override
	public List<OrderVO> getOrderBySataus(String account, Integer status) {
		List<OrderVO> orderVOS = new ArrayList<>();
		String sql = "SELECT order_id , member_id, price, method, status , pay_method , o.name , o.address , date , o.phone , note , s.account FROM `order` o\n"
				+ "	join store s\n" + "    on o.store_id = s.store_id\n" + "where s.account = ? and status = ?;";
		try (Connection conn = ds.getConnection(); PreparedStatement ppst = conn.prepareStatement(sql)) {
			ppst.setString(1, account);
			ppst.setInt(2, status);
			ResultSet rs = ppst.executeQuery();
			while (rs.next()) {
				OrderVO orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getInt("order_id"));
				orderVO.setMember_id(rs.getInt("member_id"));
				orderVO.setPrice(rs.getInt("price"));
				orderVO.setMethod(rs.getInt("method"));
				orderVO.setStatus(rs.getInt("status"));
				orderVO.setPay_method(rs.getInt("pay_method"));
				orderVO.setName(rs.getString("name"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setDate(rs.getDate("date"));
				orderVO.setPhone(rs.getString("phone"));
				orderVO.setNote(rs.getString("note"));
				orderVOS.add(orderVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderVOS;
	}

	@Override
	public Integer insert(OrderVO obj) {
		int rows = -1;
		String sql = "INSERT INTO `order` (`store_id`, `member_id`, `price`, `method`, `name`, `address`, `phone`,`note` , pay_method) VALUES (?, ?, ?, ?, ?, ?, ?,?,?);";
		try (Connection conn = ds.getConnection();
				PreparedStatement ppst = conn.prepareStatement(sql, new String[] { "order_id" })) {
			ppst.setObject(1, obj.getStore_id());
			ppst.setObject(2, obj.getMember_id());
			ppst.setObject(3, obj.getPrice());
			ppst.setObject(4, obj.getMethod());
			ppst.setObject(5, obj.getName());
			ppst.setObject(6, obj.getAddress());
			ppst.setObject(7, obj.getPhone());
			ppst.setObject(8, obj.getNote());
			ppst.setObject(9, obj.getPay_method());
			rows = ppst.executeUpdate();

			ResultSet rs = ppst.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(OrderVO obj) {
		int rows = 0;
		String sql = "UPDATE `order` set `store_id`=?,`member_id`=?,`price`=?,`method`=?,`name`=?,`address`=?,`phone`=?,`note`=? where `order_id`=?;";
		try (Connection conn = ds.getConnection(); PreparedStatement ppst = conn.prepareStatement(sql)) {
			ppst.setObject(1, obj.getStore_id());
			ppst.setObject(2, obj.getMember_id());
			ppst.setObject(3, obj.getPrice());
			ppst.setObject(4, obj.getMethod());
			ppst.setObject(5, obj.getName());
			ppst.setObject(6, obj.getAddress());
			ppst.setObject(7, obj.getPhone());
			ppst.setObject(8, obj.getNote());
			ppst.setObject(9, obj.getOrder_id());
			rows = ppst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows != 0;
	}

	@Override
	public boolean delete(Integer order_id) {
		int rows = 0;
		String sql = "delete from member_forum_img where member_forum_img_id = ?;";
		try (Connection conn = ds.getConnection();
				PreparedStatement ppst = conn.prepareStatement("delete from `order_detail` where order_id = 1;");
				PreparedStatement ppst2 = conn.prepareStatement(sql)) {
			ppst.setObject(1, order_id);
			ppst.executeUpdate();
			rows = ppst2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows != 0;
	}

}

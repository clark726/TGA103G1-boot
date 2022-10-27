package TGA103G1boot.store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StoreJNDI implements StoreDAO {
	@Autowired
	private DataSource ds;

	@Override
	public boolean insert(StoreVO storeVo) {
		String sql = "insert into store( account , name , password , phone , email , address  , theme_id , lat , lng)\n"
				+ "values( ? , ? , ?, ?, ? ,? ,? ,? ,?);";
		int i = 0;
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, storeVo.getAccount());
			ps.setString(2, storeVo.getName());
			ps.setString(3, storeVo.getPassword());
			ps.setString(4, storeVo.getPhone());
			ps.setString(5, storeVo.getEmail());
			ps.setString(6, storeVo.getAddress());
			ps.setInt(7, storeVo.getTheme_id());
			ps.setString(8, storeVo.getLat());
			ps.setString(9, storeVo.getLng());

			i = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i != 0;
	}

	@Override
	public void update(StoreVO storeVo) {
		String sql = "update store set account = ? , name = ?, password = ? , phone = ?, email = ? , address = ?  , lng = ? , lat = ? , theme_id = ? , dayoff = ? , work_open = ? , work_end = ? , produce = ?\n"
				+ "where store_id = ? ;";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, storeVo.getAccount());
			ps.setString(2, storeVo.getName());
			ps.setString(3, storeVo.getPassword());
			ps.setString(4, storeVo.getPhone());
			ps.setString(5, storeVo.getEmail());
			ps.setString(6, storeVo.getAddress());
			ps.setString(7, storeVo.getLng());
			ps.setString(8, storeVo.getLat());
			ps.setInt(9, storeVo.getTheme_id());
			ps.setString(10, storeVo.getDayoff());
			ps.setString(11, storeVo.getWork_open());
			ps.setString(12, storeVo.getWork_end());
			ps.setString(13, storeVo.getProduce());
			ps.setInt(14, storeVo.getStore_id());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateProduce(StoreVO storeVo) {
		String sql = "update store set dayoff = ? , work_open = ? , work_end = ? , produce = ?\n"
				+ "where store_id = ? ;";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, storeVo.getDayoff());
			ps.setString(2, storeVo.getWork_open());
			ps.setString(3, storeVo.getWork_end());
			ps.setString(4, storeVo.getProduce());
			ps.setInt(5, storeVo.getStore_id());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer store_id) {
		String sql = "delete from store where store_id = ?";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, store_id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public StoreVO findByPrimaryKey(Integer store_id) {

		String sql = "select account , name , password , phone , email , address , lng , lat , theme_id , dayoff , work_open , work_end , produce\n"
				+ "from store\n" + "where store_id = ?;";
		StoreVO store = null;

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, store_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				store = new StoreVO();
				store.setAccount(rs.getString("account"));
				store.setName(rs.getString("name"));
				store.setPassword(rs.getString("password"));
				store.setPhone(rs.getString("phone"));
				store.setEmail(rs.getString("email"));
				store.setAddress(rs.getString("address"));
				store.setLng(rs.getString("lng"));
				store.setLat(rs.getString("lat"));
				store.setTheme_id(rs.getInt("theme_id"));
				store.setDayoff(rs.getString("dayoff"));
				store.setWork_open(rs.getString("work_open"));
				store.setWork_end(rs.getString("work_end"));
				store.setProduce(rs.getString("produce"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return store;
	}

	@Override
	public List<StoreVO> getAll() {

		List<StoreVO> list = new ArrayList<>();
		StoreVO store = null;
		String sql = "select store_id , account , name , password , phone , email , address , lng , lat , theme_id , dayoff , work_open , work_end , produce\n"
				+ "from store";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				store = new StoreVO();
				store.setStore_id(rs.getInt("store_id"));
				store.setAccount(rs.getString("account"));
				store.setName(rs.getString("name"));
				store.setPassword(rs.getString("password"));
				store.setPhone(rs.getString("phone"));
				store.setEmail(rs.getString("email"));
				store.setAddress(rs.getString("address"));
				store.setLng(rs.getString("lng"));
				store.setLat(rs.getString("lat"));
				store.setTheme_id(rs.getInt("theme_id"));
				store.setDayoff(rs.getString("dayoff"));
				store.setWork_open(rs.getString("work_open"));
				store.setWork_end(rs.getString("work_end"));
				store.setProduce(rs.getString("produce"));
				list.add(store);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public StoreVO findStoreAccount(String account) {
		String sql = "select store_id ,account , name , password , phone , email , address , lng , lat , theme_id , dayoff , work_open , work_end , produce\n"
				+ "from store where account = ?;";

		StoreVO store = null;

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, account);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				store = new StoreVO();
				store.setStore_id(rs.getInt("store_id"));
				store.setAccount(rs.getString("account"));
				store.setName(rs.getString("name"));
				store.setPassword(rs.getString("password"));
				store.setPhone(rs.getString("phone"));
				store.setEmail(rs.getString("email"));
				store.setAddress(rs.getString("address"));
				store.setLng(rs.getString("lng"));
				store.setLat(rs.getString("lat"));
				store.setTheme_id(rs.getInt("theme_id"));
				store.setDayoff(rs.getString("dayoff"));
				store.setWork_open(rs.getString("work_open"));
				store.setWork_end(rs.getString("work_end"));
				store.setProduce(rs.getString("produce"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return store;
	}

	@Override
	public StoreVO Login(String account, String password) {

		String sql = "select account , name , password , phone , email , address , lng , lat , theme_id , dayoff , work_open , work_end , produce from store\n"
				+ "where account =  ? and password = ?;";

		StoreVO store = null;

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, account);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				store = new StoreVO();
				store.setAccount(rs.getString("account"));
				store.setName(rs.getString("name"));
				store.setPassword(rs.getString("password"));
				store.setPhone(rs.getString("phone"));
				store.setEmail(rs.getString("email"));
				store.setAddress(rs.getString("address"));
				store.setLng(rs.getString("lng"));
				store.setLat(rs.getString("lat"));
				store.setTheme_id(rs.getInt("theme_id"));
				store.setDayoff(rs.getString("dayoff"));
				store.setWork_open(rs.getString("work_open"));
				store.setWork_end(rs.getString("work_end"));
				store.setProduce(rs.getString("produce"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return store;
	}

	@Override
	public List<StoreVO> findStoreFrontpageBythemeId(Integer theme_id) {

		String sql = "select * from store_theme s\n"
				+ " join (SELECT DISTINCT  s.store_id , s.name , s.theme_id , i.img , status  FROM store s \n"
				+ "		left join store_img i\n" + "    on s.store_id = i.store_id) t\n"
				+ "    on t.theme_id = s.theme_id\n" + "    where t.status = 1 and s.theme_id = ?;";

		List<StoreVO> list = new ArrayList<StoreVO>();
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, theme_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				StoreVO store = new StoreVO();
				store.setStore_id(rs.getInt("store_id"));
				store.setName(rs.getString("name"));
				store.setImgstr(new String(rs.getBytes("img")));
				store.setStoretype(rs.getString("theme_name"));
				store.setStoreproduce(rs.getString("introduce"));
				list.add(store);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<StoreVO> findStorepageByStoreId(Integer store_id) {

		String sql = "select s.store_id, s.name store_name , s.address, s.dayoff, s.work_open, s.work_end, s.produce, p.name product_name, p.price product_price, p.product_id , p.status from store s\n"
				+ "	left join product p\n" + "    on s.store_id = p.store_id\n" + "where s.store_id = ?";
		List<StoreVO> list = new ArrayList<StoreVO>();

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, store_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				StoreVO store = new StoreVO();
				store.setStore_id(rs.getInt("store_id"));
				store.setName(rs.getString("store_name"));
				store.setAddress(rs.getString("address"));
				store.setDayoff(rs.getString("dayoff"));
				store.setWork_open(rs.getString("work_open"));
				store.setWork_end(rs.getString("work_end"));
				store.setProduce(rs.getString("produce"));
				store.setProduct_name(rs.getString("product_name"));
				store.setProduct_price(rs.getInt("product_price"));
				store.setProduct_id(rs.getInt("product_id"));
				store.setProductStatus(rs.getInt("status"));
				list.add(store);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public void updateStoreInformation(StoreVO storeVo) {
		String sql = "UPDATE `store` SET `phone` = ?, `email` = ? WHERE (`account` = ?);";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, storeVo.getPhone());
			ps.setString(2, storeVo.getEmail());
			ps.setString(3, storeVo.getAccount());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<StoreVO> getAllTheme() {

		List<StoreVO> list = new ArrayList<>();
		StoreVO store = null;
		String sql = "select store_id  , name , phone , email , address , theme_id , dayoff , work_open , work_end , produce "
				+ "from store where theme_id=?";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				store = new StoreVO();
				store.setStore_id(rs.getInt("store_id"));
				store.setName(rs.getString("name"));
				store.setPhone(rs.getString("phone"));
				store.setEmail(rs.getString("email"));
				store.setAddress(rs.getString("address"));
				store.setTheme_id(rs.getInt("theme_id"));
				store.setDayoff(rs.getString("dayoff"));
				store.setWork_open(rs.getString("work_open"));
				store.setWork_end(rs.getString("work_end"));
				store.setProduce(rs.getString("produce"));
				list.add(store);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<StoreVO> getByTheme(Integer theme_id) {
		List<StoreVO> list = new ArrayList<>();
		StoreVO store = null;
		String sql = "select store_id  , name , phone , email , address , theme_id , dayoff , work_open , work_end , produce "
				+ "from store where theme_id=?";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, theme_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				store = new StoreVO();
				store.setStore_id(rs.getInt("store_id"));
				store.setName(rs.getString("name"));
				store.setPhone(rs.getString("phone"));
				store.setEmail(rs.getString("email"));
				store.setAddress(rs.getString("address"));
				store.setTheme_id(rs.getInt("theme_id"));
				store.setDayoff(rs.getString("dayoff"));
				store.setWork_open(rs.getString("work_open"));
				store.setWork_end(rs.getString("work_end"));
				store.setProduce(rs.getString("produce"));
				list.add(store);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

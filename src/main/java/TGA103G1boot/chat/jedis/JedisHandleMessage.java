package TGA103G1boot.chat.jedis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String sender, String receiver) {
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);

		jedis.close();
	}

	public static Set<String> getHistoryUser(String username) {

		Jedis jedis = pool.getResource();
		Set<String> keys = jedis.keys(username + "*");
		Set<String> allKeys = new HashSet<String>();
		for (String key : keys) {
			String newkey = key.substring(0, key.indexOf(":"));
			String finalkey = key.substring(newkey.length() + 1, key.length());
			allKeys.add(finalkey);
		}
		jedis.close();
		return allKeys;
	}

//	public static void main(String[] args) {
//		Jedis jedis = pool.getResource();
//		Set<String> keys = jedis.keys("TGA103" + "*");
//		for (String key : keys) {
//			String newkey = key.substring(0, key.indexOf(":"));
//			String finalkey = key.substring(newkey.length() + 1, key.length());
//			System.out.println("完整 = " + key);
//			System.out.println(finalkey);
//		}
//		jedis.close();
//	}

}

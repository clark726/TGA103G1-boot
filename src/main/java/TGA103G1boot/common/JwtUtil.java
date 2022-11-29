package TGA103G1boot.common;

import java.io.Serializable;
import java.sql.Date;
import java.util.Optional;

import TGA103G1boot.store.model.StoreVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil implements Serializable{
	private static final long serialVersionUID = 1L;

	public static Claims getClaimsFromToken(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey( "MySecret" )
	                .parseClaimsJws( token )
	                .getBody();
	        return claims;
	    }
	
	public static String createJwt(String userAcct) {
		/*
		 * 0000 login success 0001 wrong email 0002 wrong useracct 0003 wrong passwd
		 * 0004 acct locked
		 */
				// 新增了以下這段：
				Date expireDate =
						// 設定30min過期
						new Date(System.currentTimeMillis() + 30 * 60 * 1000);
				String jwtToken = Jwts.builder().setSubject(userAcct) // 我以email當subject
						.setExpiration(expireDate)
						// MySecret是自訂的私鑰，HS512是自選的演算法，可以任意改變
						.signWith(SignatureAlgorithm.HS512, "MySecret").compact();
				// 直接將JWT傳回
		return jwtToken;
	}
}

package TGA103G1boot.common;

import java.io.Serializable;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtil implements Serializable{
	private static final long serialVersionUID = 1L;

	public static Claims getClaimsFromToken(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey( "MySecret" )
	                .parseClaimsJws( token )
	                .getBody();
	        return claims;
	    }
}

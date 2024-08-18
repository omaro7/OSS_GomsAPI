package kr.co.goms.web.oss.core.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;


@Component
public class JwtTokenProvider {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	//todo AmsProperties에서 getJwtSecretKey();
	private static final String secretKey = "393a64b6cf32";
		
	// 1시간 단위
	public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60;
	
	// token으로 사용자 id 조회
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getId);
	}
	
	// token으로 사용자 속성정보 조회
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	// 모든 token에 대한 사용자 속성정보 조회
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
	
	 //토큰 생성
    public String createToken(Authentication authentication, String account, String role) {
    	
    	authentication.getPrincipal();
    	
        Claims claims = Jwts.claims().setSubject(account);
        claims.put("rules", role);
        claims.put("userDTO", authentication);
        Date now = new Date();
        return Jwts.builder()
        		.setSubject(account)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1))// 1시간
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
    
    // 토큰에 담겨있는 유저 email 획득
    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    // Authorization Header를 통해 인증을 한다.
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
    
    
	// 토근 만료 여부 체크
	/*
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	*/
	
	// 토큰 만료일자 조회
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	// id를 입력받아 accessToken 생성
	public String generateAccessToken(String id) {
		return generateAccessToken(id, new HashMap<>());
	}
	
	// id, 속성정보를 이용해 accessToken 생성
	public String generateAccessToken(String id, Map<String, Object> claims) {
		return doGenerateAccessToken(id, claims);
	}
	
	// JWT accessToken 생성
	private String doGenerateAccessToken(String id, Map<String, Object> claims) {
		String accessToken = Jwts.builder()
				.setClaims(claims)
				.setId(id)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1))// 1시간
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
		
		return accessToken;
	}
	
	// id를 입력받아 accessToken 생성
	public String generateRefreshToken(String id) {
		return doGenerateRefreshToken(id);
	}
	
	// JWT accessToken 생성
	private String doGenerateRefreshToken(String id) {
		String refreshToken = Jwts.builder()
				.setId(id)
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 5)) // 5시간
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
		
		return refreshToken;
	}
	
	// id를 입력받아 accessToken, refreshToken 생성
	public Map<String, String> generateTokenSet(String id) {
		return generateTokenSet(id, new HashMap<>());
	}
	
	// id, 속성정보를 이용해 accessToken, refreshToken 생성
	public Map<String, String> generateTokenSet(String id, Map<String, Object> claims) {
		return doGenerateTokenSet(id, claims);
	}
	
	// JWT accessToken, refreshToken 생성
	private Map<String, String> doGenerateTokenSet(String id, Map<String, Object> claims) {
		Map<String, String> tokens = new HashMap<String, String>();
		
		String accessToken = Jwts.builder()
				.setClaims(claims)
				.setId(id)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1))// 1시간
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
		
		String refreshToken = Jwts.builder()
				.setId(id)
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 5)) // 5시간
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
		
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		return tokens;
	}
	
	// JWT refreshToken 만료체크 후 재발급
	public void reGenerateRefreshToken(String id) throws Exception {
		log.info("[reGenerateRefreshToken] refreshToken 재발급 요청");
		
	}
	
	// 토근 검증
	public boolean validateToken(String token) {
		try {
			//Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
	
    // 토큰 만료 여부
    public boolean validateExpirateToken(String token) {
        try {
            // Bearer 검증
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                return false;
            } else {
                token = token.split(" ")[1].trim();
            }
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            // 만료되었을 시 false
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
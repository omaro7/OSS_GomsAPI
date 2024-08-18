package kr.co.goms.web.oss.core.security.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

import kr.co.goms.web.oss.core.constant.AuthoritiesConstant;
import kr.co.goms.web.oss.core.security.service.GomsGlobal;
import kr.co.goms.web.oss.core.security.service.impl.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for Spring Security.
 */
@Slf4j
public final class SecurityUtils {

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;

    public static final String AUTHORITIES_KEY = "auth";
    
    private SecurityUtils() {}

    /**
     * AMS Gobal Value 가져오기
     * Global Value는 사용자가 로그인시에 토큰에 저장되는 공통 정보
     * @return AMS Global 정보
     */
    public static GomsGlobal getAmsGlobal() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return extractAmsGlobal(securityContext.getAuthentication());
    }

    private static GomsGlobal extractAmsGlobal(Authentication authentication) {
    	UserDetailsImpl gomsUser = (UserDetailsImpl) authentication.getPrincipal();
        return gomsUser.getGomsGlobal();
    }

    /**
     * 현재 로그인 사용자 아이디 가져오기
     * @return 로그인 사용자 아이디
     */
    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipalName(securityContext.getAuthentication()));
    }

    private static String extractPrincipalName(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 현재 로그인 사용자 정보 가져오기
     * @return 로그인 사용자 정보
     */
    public static Optional<UserDetails> getCurrentUserInfo() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable((UserDetails) extractPrincipal(securityContext.getAuthentication()));
    }

    private static Object extractPrincipal(Authentication authentication) {
        return authentication.getPrincipal();
    }

    /**
     * 현재 사용자 JWT Token 가져오기
     * @return 현재 사용자 JWT Token
     */
    public static Optional<String> getCurrentUserJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
            .filter(authentication -> authentication.getCredentials() instanceof String)
            .map(authentication -> (String) authentication.getCredentials());
    }

    /**
     * 사용자가 인증 되었는지 확인
     * @return true/false
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && getAuthorities(authentication).noneMatch(AuthoritiesConstant.ANONYMOUS ::equals);
    }

    /**
     * 사용자가 특정 권한을 갖고 있는지 확인
     * @param authorities 권한 목록
     * @return 하나라도 있으면 true, 없으면 false
     */
    public static boolean hasCurrentUserAnyOfAuthorities(String... authorities) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && getAuthorities(authentication).anyMatch(authority -> Arrays.asList(authorities).contains(authority));
    }

    /**
     * 사용자가 특정 권한을 갖고 있는지 확인
     * @param authority 권한
     * @return 권한이 있으면 true, 없으면 false
     */
    public static boolean hasCurrentUserThisAuthority(String authority) {
        return hasCurrentUserAnyOfAuthorities(authority);
    }

    private static Stream<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
    }
}

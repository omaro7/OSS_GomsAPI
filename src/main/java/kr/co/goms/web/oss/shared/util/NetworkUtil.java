package kr.co.goms.web.oss.shared.util;

import jakarta.servlet.http.HttpServletRequest;

public class NetworkUtil {

	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("x-Forworded-For");
		ip = request.getRemoteAddr();
		return ip;
	}
}

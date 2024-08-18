package kr.co.goms.web.oss.core.api;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.goms.web.oss.core.constant.GomsConstant;
import kr.co.goms.web.oss.core.constant.GomsConstant.AmsConfig;
import kr.co.goms.web.oss.core.constant.GomsErrorCode;
import kr.co.goms.web.oss.core.exception.GomsException;
import kr.co.goms.web.oss.core.security.util.SecurityUtils;
import kr.co.goms.web.oss.management.GomsEnvironment;
import kr.co.goms.web.oss.shared.util.GomsBizUtils;
import kr.co.goms.web.oss.shared.util.GomsCacheUtils;
import kr.co.goms.web.oss.shared.util.NetworkUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ApiContext implements Serializable{

	private static final long serialVersionUID = 7007893535148087441L;
	private String amsSystemId = "";
	private String amsSystemIdUCase = "";
	private final String serviceStartTime = "yyyyMMddHHmmssSSS";

	@Setter
	private HttpServletRequest httpRequest = null;

	@Setter
	private HttpServletResponse httpResponse = null;

	@Setter
	private GomsStreamDataContainer streamData = null;	//kafka message
	
	private String uriPath = "";
	private String serviceClassName = "";
	private String serviceMethodName = "";

	@Setter
	private Exception exception = null;	
	
	private String guid;
	private String originalGuid;

	private String clientIp = "";
	private String userId = "";
	private String userInfo = "";
	private String token = "";
	
	private String screenNo = "";
	
	public ApiContext(ApiContext context) {
		if(context != null) {
			this.amsSystemId = context.getAmsSystemId();
			this.amsSystemIdUCase = context.getAmsSystemIdUCase();
			
			this.httpRequest = context.getHttpRequest();
			this.httpResponse = context.getHttpResponse();
			this.streamData = context.getStreamData();
			this.uriPath = context.getUriPath();
			this.serviceClassName = context.getServiceClassName();
			this.serviceMethodName = context.getServiceMethodName();
			this.exception = context.getException();
			
			this.guid = context.getGuid();
			this.originalGuid = context.getOriginalGuid();
			this.clientIp = context.getClientIp();
			this.userId = context.getUserId();
			this.userInfo = context.getUserInfo();
			this.token = context.getToken();
		
			
		}else {
			this.amsSystemId = ApiStatics.getAmsBizUtils().getEnv().getAmsSystemId();
			this.amsSystemIdUCase = this.amsSystemId.toUpperCase();
			this.guid = getGuid();
		}
	}
	
	public ApiContext(final HttpServletRequest request, final HttpServletResponse response, final Object serviceMethodHandler) {
		
		GomsBizUtils amsBizUtils = ApiStatics.getAmsBizUtils();
		
		request.setAttribute("api-context", this);
		
		this.httpRequest = request;
		this.httpResponse = response;

		try {
			amsSystemId = amsBizUtils.getEnv().getAmsSystemId();
			amsSystemIdUCase = amsSystemId.toUpperCase();
		}catch(Exception e) {
			
		}
		
		this.initApiContextFromHttpRequest(request, response, serviceMethodHandler);
		request.setAttribute(GomsConstant.ApiHttpConfig.HTTP_ATTR_API_CONTEXT_KEY, serviceMethodHandler);
		
	}
	
	public ApiContext(final GomsStreamDataContainer amsStreamData, final ProceedingJoinPoint jointPoint) {
		
		if(ApiStatics.getAmsBizUtils() == null) {
			throw new GomsException(GomsErrorCode.ERROR_SYS_99, "시스템 로딩이 완료되지 않아 잠시후 재시도 부탁드립니다.");
		}
		
		GomsBizUtils amsBizUtils = ApiStatics.getAmsBizUtils();
		this.streamData = amsStreamData;
		
		this.amsSystemId = amsBizUtils.getEnv().getAmsSystemId();
		this.amsSystemIdUCase = amsSystemId.toUpperCase();
		
		this.initApiContextFromStreamMessage(amsStreamData, jointPoint);
		
		if(ApiStatics.getAmsBizUtils().getCache() == null) {
			throw new GomsException(GomsErrorCode.ERROR_SYS_99, "시스템 로딩이 완료되지 않아 잠시후 재시도 부탁드립니다.");
		}else {
			GomsCacheUtils amsCacheUtil = ApiStatics.getAmsBizUtils().getCache();
			amsCacheUtil.putValue(GomsConstant.CacheConfig.STREAM_MESSAGE_CONTEXT_CACHE_NAME, contextCacheKey(), this);
		}
	}
	
	public static ApiContext getContext() {
		if(RequestContextHolder.getRequestAttributes() != null) {
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			return (ApiContext)requestAttributes.getAttribute(GomsConstant.ApiHttpConfig.HTTP_ATTR_API_CONTEXT_KEY, 0);
		}else {
			GomsCacheUtils examCacheUtils = ApiStatics.getAmsBizUtils().getCache();
			ApiContext context = null;
			
			try {
				context = examCacheUtils.getValue(GomsConstant.CacheConfig.STREAM_MESSAGE_CONTEXT_CACHE_NAME 
						, contextCacheKey()
						, ApiContext.class);
			}catch(Exception e) {
				throw new GomsException(GomsErrorCode.ERROR_SYS_99, "Context 정보가 없습니다.");
			}
			return context;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String contextCacheKey() {
		return Thread.currentThread().getId() + "@" + GomsEnvironment.getLocalMacAddress() + ApiStatics.getAmsBizUtils().getEnv().getProperty("server-ip");
	}
	
	public static ApiContext cloneCachableApiContext(ApiContext context) {
		ApiContext newContext = new ApiContext(context);
		newContext.setHttpRequest(null);
		newContext.setHttpResponse(null);
		newContext.setStreamData(null);
		
		return newContext;
	}
	
	public String newGuid() {
		return "EXAM-" + amsSystemIdUCase + "-" + UUID.randomUUID().toString().toUpperCase(); 
	}
	
	/**
	 * 호출된 프로그래밍 Simple Class명을 조회한다.
	 * <br/>
	 * @return
	 */
	public String getServiceClassSimpleName() {
	    if (this.serviceClassName.contains("_")) {
	        int simpleNamePosition = this.serviceClassName.lastIndexOf("_") + 1;
	        return this.serviceClassName.substring(simpleNamePosition);
	    } else {
	        return this.serviceClassName;
	    }
	}
	
	/**
	 * Context전송 캐시에 삭제한다.
	 * <br/>
	 * HttpRequest를 계속 사용하는 경우 @ Stream에서나, Async 프로그램에서만 사용
	 * <br/>
	 * @return
	 */
	public void clearContextCache() {
		GomsCacheUtils amsCacheUtils = ApiStatics.getAmsBizUtils().getCache();
	    try {
	        amsCacheUtils.evict(GomsConstant.CacheConfig.STREAM_MESSAGE_CONTEXT_CACHE_NAME, contextCacheKey());
	    } catch (Exception e) {
	    }
	}

	/**
	 * 현재 Context를 캐시에 저장한다.
	 * <br/>
	 * @return
	 */
	public void saveContextToCache() {
		GomsCacheUtils amsCacheUtils = ApiStatics.getAmsBizUtils().getCache();
	    amsCacheUtils.putValue(GomsConstant.CacheConfig.STREAM_MESSAGE_CONTEXT_CACHE_NAME, contextCacheKey(), this);
	}
	
	/** Http Request와 Context정보 초기화 */
	private void initApiContextFromHttpRequest(HttpServletRequest request, HttpServletResponse response, Object serviceMethodHandler) {
		GomsBizUtils amsBizUtils = ApiStatics.getAmsBizUtils();

	    //-----------------------------------------------------------
	    // Request URI
	    this.uriPath = request.getServletPath();

	    // 호출 서비스 Method명
	    if (serviceMethodHandler != null && serviceMethodHandler instanceof HandlerMethod) {
	        HandlerMethod h = (HandlerMethod)serviceMethodHandler;
	        this.serviceClassName = h.getBean().getClass().getName();
	        this.serviceMethodName = h.getMethod().getName();
	    }

	    //-----------------------------------------------------------
	    // GUID 처리...
	    // GUID가 이미 설정된 경우
	    this.guid = (String)request.getAttribute(GomsConstant.ApiHttpConfig.HTTP_HEADER_KEY_GUID);

	    // GUID가 설정되지 않은 경우에 신규발급하여 Response에도 추가
	    if (!StringUtils.hasText(this.guid)) {
	        this.guid = "AMS-" + amsSystemIdUCase + "-" + UUID.randomUUID().toString().toUpperCase();
	        request.setAttribute(GomsConstant.ApiHttpConfig.HTTP_HEADER_KEY_GUID, this.guid);
	    }
	    // 응답 Header에 GUID Response에도 추가
	    response.setHeader(GomsConstant.ApiHttpConfig.HTTP_HEADER_KEY_GUID, this.guid);

	    // 내부 시스템간 호출 프로세스 경우의 호출 요청한 시스템의 GUID
	    String originGuid = "";
	    try {
	        originGuid = request.getHeader(GomsConstant.ApiHttpConfig.HTTP_HEADER_INTERNAL_CALL_ORIGIN_GUID);
	    } catch (Exception e) {}

	    if (StringUtils.hasText(originGuid)) {
	        this.originalGuid = originGuid;
	    }

	    // TODO 필요한 경우 요청 URI재정의. 필요에 따라 개성요소를 추가한다.
	    if (request.getServletPath().startsWith("/api/")) {
	        this.screenNo = request.getServletPath().substring(5);
	    } else {
	        this.screenNo = request.getServletPath();
	    }
		
		// Client IP
		if (amsBizUtils.getEnv().isLocalDev()) {
		    // 로컬인경우 서버주소를 임의로 설정
		    this.clientIp = GomsEnvironment.getLocalIp();
		} else {
		    String httpClientIp = NetworkUtil.getClientIP(request);
		    if (StringUtils.hasText(httpClientIp)) {
		        this.clientIp = httpClientIp;
		    } else {
		        this.clientIp = "unknown";
		    }
		}
	
		// 사용자명
		try {
		    Optional<String> optUserId = SecurityUtils.getCurrentUserLogin();
		    this.userId = optUserId.isPresent() ? optUserId.get() : null;
		} catch (Exception e) {}
	
		// 사용자정보
		try {
		    Optional<UserDetails> optAmsUser = SecurityUtils.getCurrentUserInfo();
		    this.userInfo = optAmsUser.isPresent() ? optAmsUser.get().toString() : null;
		} catch (Exception e) {}
	
		// 인증토큰
		try {
		    Optional<String> optToken = SecurityUtils.getCurrentUserJWT();
		    this.token = optToken.isPresent() ? optToken.get() : "";
		} catch (Exception e) {}
	
	}
	
	/**
	 * Http Request를 Context정보 초기화
	 */
	private void initApiContextFromStreamMessage(GomsStreamDataContainer streamData, ProceedingJoinPoint jointPoint) {

		//----------------------------------- 서비스 호출 정보-----------------------------------
		this.uriPath = "STREAM MESSAGE [" + streamData.getPublisher() + " - " + streamData.getEventId() + "]";
	
		//----------------------------------- 호출 서비스 Method정보-----------------------------------
		if (jointPoint != null && jointPoint instanceof ProceedingJoinPoint) {
		    ProceedingJoinPoint pjp = (ProceedingJoinPoint)jointPoint;
		    this.serviceClassName = pjp.getSignature().getDeclaringTypeName();
		    this.serviceMethodName = pjp.getSignature().getName();
		}
	
		//----------------------------------- GUID 처리-----------------------------------
		amsSystemIdUCase = amsSystemIdUCase == null ? "aaa" : amsSystemIdUCase;
		this.guid = AmsConfig.GUID_PREFIX + "-" + amsSystemIdUCase + "-" + UUID.randomUUID().toString().toUpperCase();
	
		// 내부 시스템간 호출을 통해 호출된 경우 원천 시스템의 GUID
		if (StringUtils.hasText(streamData.getGuid())) {
		    this.originalGuid = streamData.getGuid();
		} 
		else {
		    this.originalGuid = "";
		}
	
		//----------------------------------- 거래정보-----------------------------------
		this.screenNo = ""; // 화면정보 없음
	
		//----------------------------------- 사용자정보 (N/A)-----------------------------------
		// Client IP
		this.clientIp = "";
		this.userId = "";
		this.userInfo = null;
		this.token = "";
	}
	
}

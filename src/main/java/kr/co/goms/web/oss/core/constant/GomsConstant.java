package kr.co.goms.web.oss.core.constant;

public interface GomsConstant {
	
	public static final String SPRING_PROFILE_API_DOCS = "api-docs";
	
	public class EnvMode {
		public static final String LOCAL_DEV = "local";
		public static final String DEV = "dev";
		public static final String STG = "stg";
		public static final String PROD = "prod";
	}

	public class CacheConfig{
		public static final String METHOD_API_CACHE_NAME = "API.METHOD";
		public static final String APP_SETTING_CACHE_NAME = "API.APP_SETTING";
		public static final String STREAM_MESSAGE_CONTEXT_CACHE_NAME = "API.STREAM_MESSAGE_CONTEXT";
		public static final String CACHE_GROUP_CONFIG_KEY = "API.CACHE_GROUP";
	}
	
	public class ApiHttpConfig{
		public static final String HTTP_HEADER_KEY_GUID = "x-goms-guid";
		public static final String HTTP_ATTR_API_PATH_KEY = "org.springframework.wet.util.UrlPathHelper.PATH";
		public static final String HTTP_ATTR_API_CONTEXT_KEY = "goms-api-context";
		public static final String HTTP_ATTR_WAVE_AUTHRIZATION_WAVED = "x-check-authorization-waved-flag";
		public static final String HTTP_HEADER_INTERNAL_CALL_ORIGIN_SYSTEM = "x-internal-call-origin-system";
		public static final String HTTP_HEADER_INTERNAL_CALL_ORIGIN_GUID = "x-internal-call-origin-guid";
	}
	
	public class AmsConfig{
		public static final String GUID_PREFIX = "GOMS";
		public static final String SYSTEM_ID_KEY = "goms.clientapp.name";
	}
	
}

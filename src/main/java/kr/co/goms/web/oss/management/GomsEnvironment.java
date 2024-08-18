package kr.co.goms.web.oss.management;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import kr.co.goms.web.oss.core.constant.GomsConstant;
import kr.co.goms.web.oss.core.constant.GomsConstant.CacheConfig;
import kr.co.goms.web.oss.core.constant.GomsErrorCode;
import kr.co.goms.web.oss.core.security.exception.GomsException;

@Component
@Transactional
@EnableCaching
public class GomsEnvironment {

	private final Environment mEnv;
	private final Set<String> mCurrentProfile;
	private final String mAmsSystemId;

	public final static String APP_SETTING_ENV_PATH = "exam-config-app-settting";
	public final static String APP_SECTION_ENV_PATH = "exam-config-app-section";
	
	public GomsEnvironment(Environment env) {
		this.mEnv = env;
		this.mAmsSystemId = env.getProperty(GomsConstant.AmsConfig.SYSTEM_ID_KEY, String.class);
		this.mCurrentProfile = new HashSet<>();
		
		if(env.getActiveProfiles().length > 0) {
			for(String sProfile:env.getActiveProfiles()) {
				this.mCurrentProfile.add(sProfile);
			}
		}else {
			for(String sProfile:env.getDefaultProfiles()) {
				this.mCurrentProfile.add(sProfile);
			}
		}
	}
	
	public Environment getEnvironment() {
		return mEnv;
	}
	
	public Set<String> getCurrentProfile(){
		return mCurrentProfile;
	}

	public boolean isDev() {
		if(mCurrentProfile.contains(GomsConstant.EnvMode.DEV) || mCurrentProfile.contains(GomsConstant.EnvMode.LOCAL_DEV) ) {
			return true;
		}else {
			return false;
		}
	}	
	public boolean isLocalDev() {
		if(mCurrentProfile.contains(GomsConstant.EnvMode.LOCAL_DEV) ) {
			return true;
		}else {
			return false;
		}
	}
	public boolean isProd() {
		if(mCurrentProfile.contains(GomsConstant.EnvMode.PROD) ) {
			return true;
		}else {
			return false;
		}
	}

	public boolean isStg() {
		if(mCurrentProfile.contains(GomsConstant.EnvMode.STG) ) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean containProfile(String profile) {
		return mCurrentProfile.contains(profile);
	}
	

	public boolean hasProperty(String key) {
		return mEnv.containsProperty(key);
	}
	public String getProperty(String key) {
		return mEnv.getProperty(key, String.class);
	}
	public <T>T getProperty(String key, Class<T> classType) {
		return mEnv.getProperty(key, classType);
	}
	
	public String getAppSettingBySearchKey(String key) {
		return getAppSettingBySearchKey(key, String.class);
	}
	public <T>T getAppSettingBySearchKey(String key, Class<T> classType) {
		String appSettingKey = APP_SETTING_ENV_PATH + "." + key;
		T val = mEnv.getProperty(appSettingKey, classType);
		if(Boolean.class == classType) {
			val = (T)(val == null? Boolean.FALSE:val);
		}
		return val;
	}
	
	public Integer getAppSettingByRegistryType(String registryGb, String pType) {
		String searchKey = this.getAppSettingSearchKey(registryGb, pType);
		if(!StringUtils.hasLength(searchKey)) {
			throw new GomsException(GomsErrorCode.ERROR_SYS_99, this.concat("NO AppSettings key for [" + registryGb + ", " + pType));
		}
		Integer keyValue = 0;
		
		keyValue = this.getAppSettingBySearchKey(searchKey, Integer.class);
		if(keyValue==null) {
			if("MaxRow".equals(searchKey)) {
				keyValue = 300;
			}else {
				keyValue = 0;
			}
		}
		return keyValue;
	}
	
	@Cacheable(cacheNames = CacheConfig.APP_SETTING_CACHE_NAME, key="'appSetting$$('.concat(#registryGb	).concat('##').concat(#type)")
	public String getAppSettingSearchKey(String registryGb, String pType) {
		String searchKey = "";
		
		if(!StringUtils.hasText(pType)) {
			searchKey = registryGb;
		}
		else
		{
			if("SqlLogging".equals(registryGb)) {
				if("L".equals(pType)) {
					searchKey = "LogLevel";
				}
			}else if("Marking".equals(registryGb)) {
				if("M".equals(pType)) {
					searchKey = "MrdPnoMarkLevel";
				}else if("AM".equals(pType)) {
					searchKey = "MrdActMarkLevel";
				}else if("PW".equals(pType)) {
					searchKey = "WgPnoMarkLevel";
				}else if("AW".equals(pType)) {
					searchKey = "WgActMarkLevel";
				}
			}else if("MrdControlling".equals(registryGb)) {
				if("P".equals(pType)) {
					searchKey = "MrdPrintLevel";
				}else if("D".equals(pType)) {
					searchKey = "MrdDownLevel";
				}
			}else if("WgControlling".equals(registryGb)) {
				if("C".equals(pType)) {
					searchKey = "MgDragLevel";
				}else if("D".equals(pType)) {
					searchKey = "WgDownLevel";
				}
			}
		}
		return searchKey;
	}
	
	public String getAppSectionSearchKey(String key) {
		return getAppSectionSearchKey(key, String.class);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAppSectionSearchKey(String searchKey, Class<T> clz) {
		String appSectionKey = APP_SECTION_ENV_PATH + "." + searchKey;
		
		T val = mEnv.getProperty(appSectionKey, clz);
		
		if(Boolean.class == clz) {
			val = (T)(val == null?Boolean.FALSE:val);
		}
		return val;
	}
	
	public String getAPServerHost() {
		if(isLocalDev()) {
			return "localhost";
		}else {
			return getLocalIp();
		}
	}
	
	public String getAmsSystemId() {
		return this.mAmsSystemId;
	}
	
	public String getAmsApplicationName() {
		return this.mEnv.getProperty("spring.application.name");
	}
	
	private String concat(Object... params) {
		if(params.length > 2) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < params.length; i++) {
				sb = sb.append(params[i]);
			}
			return sb.toString();
		}else {
			String rtn = "";
			for(int i = 0; i < params.length; i++) {
				rtn = rtn + params[i];
			}
			return rtn;
		}
	}
	
	public static String getLocalMacAddress() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface  ni = NetworkInterface.getByInetAddress(ip);
			
			byte[] mac = ni.getHardwareAddress();
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], i<(mac.length-1)?"-":""));
			}
			return sb.toString();
		}catch(UnknownHostException e) {
			return "unknown";
		}catch(SocketException e) {
			return "unknown";
		}
	}
	
	public static String getLocalIp() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			return ip.getHostAddress();
		}catch(UnknownHostException e) {
			return "unknown";
		}
	}
}

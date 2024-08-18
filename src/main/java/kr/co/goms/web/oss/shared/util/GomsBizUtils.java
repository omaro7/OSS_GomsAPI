package kr.co.goms.web.oss.shared.util;

import org.springframework.context.annotation.Configuration;

import kr.co.goms.web.oss.core.api.ApiStatics;
import kr.co.goms.web.oss.management.GomsEnvironment;

@Configuration
public class GomsBizUtils {

	private final GomsEnvironment mEnv;
	private final GomsCacheUtils mCache;
	
	
	public GomsBizUtils(GomsEnvironment env, GomsCacheUtils cache) {
		this.mEnv = env;
		this.mCache = cache;
		
		ApiStatics.setAmsBizUtils(this);
	}
	
	public GomsEnvironment getEnv() {
		return this.mEnv;
	}
	
	public GomsCacheUtils getCache() {
		return this.mCache;
	}
}

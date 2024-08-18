package kr.co.goms.web.oss.shared.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.stereotype.Component;

import com.hazelcast.cache.HazelcastCacheManager;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import kr.co.goms.web.oss.core.constant.GomsConstant;
import kr.co.goms.web.oss.management.GomsEnvironment;

@Component
@SuppressWarnings("unused")
public class GomsCacheUtils {

	private final Logger log = LoggerFactory.getLogger(GomsCacheUtils.class);
	
	private final GomsEnvironment env;
	private final CacheManager cacheManager;
	
	private final String cacheGroupName;
	
	public GomsCacheUtils(GomsEnvironment env, CacheManager cacheManager) {
		this.env = env;
		this.cacheManager = cacheManager;
		
		this.cacheGroupName = env.getProperty(GomsConstant.CacheConfig.CACHE_GROUP_CONFIG_KEY, String.class);
		
	}
	
	public String getCurrentCacheGroup() {
		return this.cacheGroupName;
	}
	
	public CacheManager getCacheManager() {
		return this.cacheManager;
	}
	
	public Cache getCache(String cacheName) {
		return this.cacheManager.getCache(cacheGroupName + "$$" + cacheName);
	}
	
	public IMap<Object, Object> getCacheContants(Cache cache){
		HazelcastInstance hazelcastInstance = Hazelcast.getHazelcastInstanceByName("hazelcastInstance");
		IMap<Object, Object> nativeCache= hazelcastInstance.getMap(cache.getName());
		return nativeCache;
	}
	
	public IMap<Object, Object> getCacheContants(String cacheName){
		return getCacheContants(this.getCache(cacheName));
	}
	
	public IMap<Object, Object> getCacheContentsByOriginalName(String cacheName){
		Cache cache = this.cacheManager.getCache(cacheName);
		return getCacheContants(cache);
	}
	
	public <T> T getValue(Cache cache, String key, Class<T> cls){
		return cache.get(key, cls);
	}
	
	public <T> T getValue(String cacheName, String key, Class<T> cls){
		Cache cache = getCache(cacheName);
		return this.getValue(cache, key, cls);
	}
	
	public Object getValue(Cache cache, String key) {
		ValueWrapper v = cache.get(key);
		if(v == null) {
			return null;
		}
		else {
			return v.get();
		}
	}
	
	public Object getValue(String cacheName, String key) {
		Cache cache = getCache(cacheName);
		return this.getValue(cache, key);
	}
	
	public void putValue(Cache cache, String cacheKey, Object value) {
		cache.put(cacheKey, value);
	}
	
	public void putValue(String cacheName, String cacheKey, Object value) {
		Cache cache = getCache(cacheName);
		this.putValue(cache, cacheKey, value);
	}

	public void putValue(String cacheName, String cacheKey, Object value, long ttl, TimeUnit ttlTimeUnit) {
		HazelcastInstance hazelcastInstance = Hazelcast.getHazelcastInstanceByName("hazelcastInstance");
		IMap<Object, Object> nativeCache= hazelcastInstance.getMap(cacheName);
		nativeCache.put(cacheKey, value, ttl, ttlTimeUnit);
	}
	
	//캐시삭제
	public void evict(Cache cache, String key) {
		cache.evictIfPresent(key);
	}
	
	public void evict(String cacheName, String key) {
		Cache cache = getCache(cacheName);
		cache.evictIfPresent(key);
	}
	
	//캐시초기화
	public void clear(Cache cache) {
		cache.clear();
	}
	
	public void clear(String cacheName) {
		Cache cache = getCache(cacheName);
		cache.clear();
	}
	
	public void clearAll(boolean forceAll) {
		String[] cacheNames = cacheManager.getCacheNames().toArray(new String[0]);
		for(String cacheName : cacheNames) {
			if(!forceAll && !cacheName.startsWith(cacheGroupName)) {
				continue;
			}
			Cache cache = getCache(cacheName);
			cache.clear();
		}
	}
	
	public Map<String, Integer> getCacheNames(String prefix){
		Map<String, Integer> rtn = new HashMap<String, Integer>();
		String[] cacheNames = cacheManager.getCacheNames().toArray(new String[0]);
		
		if(prefix == null) { prefix = "";}
		for(String cacheName : cacheNames) {
			if(prefix.length() > 0 && !cacheName.startsWith(prefix)) {
				continue;
			}
			Cache cache = getCache(cacheName);
			
			HazelcastInstance hazelcastInstance = Hazelcast.getHazelcastInstanceByName("hazelcastInstance");
			IMap<Object, Object> nativeCache= hazelcastInstance.getMap(cacheName);
			
			if(nativeCache != null && nativeCache.size() > 0) {
				rtn.put((String)cacheName, nativeCache.size());
			}
		}
		return rtn;
	}
	
	public Map<String, Integer> getCacheName(){
		return this.getCacheNames("");
	}
}

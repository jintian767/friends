package com.wsx.friends.core.ehcache;

import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;

import com.wsx.friends.core.model.CoreCache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 本地缓存的加载和使用
 * @author wangshuaixin
 *
 */
public class EhcacheManagerUtil {
	
	public static final String CORE_CACHE = "coreCache";
	public static final String LANGUAGE_CACHE = "languageCache";
	
	private static EhcacheManagerUtil managerUtil = new EhcacheManagerUtil();
	
	private static CacheManager cacheManager;
	
	private EhcacheManagerUtil() {
	}
	
	public static EhcacheManagerUtil getInstance() {
		return managerUtil;
	}
	
	private static CacheManager getCacheManager() {
		if (null == cacheManager) {
			ClassPathResource pathResource = null;
			InputStream inputStream = null;
			try {
				pathResource = new ClassPathResource("conf/ehcache.xml");
				inputStream = pathResource.getInputStream();
				cacheManager = CacheManager.create(inputStream);
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if (null != inputStream) {
					try {
						inputStream.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}
		return cacheManager;
	}
	
	public static void setCoreCache(String key, CoreCache coreCache) {
		Element element = new Element(key, coreCache);
		if (!getCacheManager().cacheExists(CORE_CACHE)) {
			getCacheManager().addCache(CORE_CACHE);
		}
		getCacheManager().getCache(CORE_CACHE).put(element);
	}
	
	public static void removeCoreCache(String key) {
		getCacheManager().getCache(CORE_CACHE).remove(key);
	}
	
	public static CoreCache getCoreCache(String key) {
		Cache cache = getCoreCache();
		String cacheKey = key;
		Element element = cache.get(cacheKey);
		if (null != element) {
			return (CoreCache) getCoreCache().get(key).getValue();
		}
			
		return null;
	}

	private static Cache getCoreCache() {
		return getCacheManager().getCache(CORE_CACHE);
	}

}

package mud.server;

import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import com.allen_sauer.gwt.log.client.Log;

public class RoomManager {
	private Cache cache;

	private RoomManager() {
		try {
			CacheFactory cacheFactory = CacheManager.getInstance()
					.getCacheFactory();
			cache = cacheFactory.createCache(Collections.emptyMap());
		} catch (CacheException e) {
			Log.fatal("Unable to create cache", e);
		}
	}

	private static RoomManager singleton = new RoomManager();

	public static RoomManager get() {
		return singleton;
	}

}

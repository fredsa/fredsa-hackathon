package mud.server;

import java.util.Collections;
import java.util.HashMap;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import mud.client.model.Room;

import com.allen_sauer.gwt.log.client.Log;

public class RoomManager {
  private static final Object KEY_ROOM_TO_PLAYER_HASHMAP = "KEY_ROOM_TO_PLAYER_HASHMAP";
  private Cache cache;

  private RoomManager() {
    try {
      CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
      cache = cacheFactory.createCache(Collections.emptyMap());
    } catch (CacheException e) {
      Log.fatal("Unable to create cache", e);
    }
  }

  private static RoomManager singleton = new RoomManager();

  public static RoomManager get() {
    return singleton;
  }

  public Room getRoom(String playerId) {
    String roomId = getRoomId(playerId);
    return (Room) cache.get(roomId);
  }

  private String getRoomId(String playerId) {
    HashMap<String, String> map = (HashMap<String, String>) cache.get(KEY_ROOM_TO_PLAYER_HASHMAP);
    if (map == null) {
      map = new HashMap<String, String>();
      cache.put(KEY_ROOM_TO_PLAYER_HASHMAP, map);
    }
    return map.get(playerId);
  }
}

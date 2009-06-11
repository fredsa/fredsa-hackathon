package mud.server;

import java.util.Collections;
import java.util.HashMap;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import mud.client.model.Character;
import mud.client.model.Player;
import mud.client.model.Room;

import com.allen_sauer.gwt.log.client.Log;

public class MudManager {
  private static final Object KEY_ROOM_TO_PLAYER_HASHMAP = "KEY_ROOM_TO_PLAYER_HASHMAP";
  private static final String KEY_GLOBAL_ROOM = null;
  private Cache cache;

  private MudManager() {
    try {
      CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
      cache = cacheFactory.createCache(Collections.emptyMap());
    } catch (CacheException e) {
      Log.fatal("Unable to create cache", e);
    }
  }

  private static MudManager singleton = new MudManager();

  public static MudManager get() {
    return singleton;
  }

  public Room getRoom(String playerId) {
    String roomId = getRoomId(playerId);
    Room room = (Room) cache.get(roomId);
    if (room == null) {
      room = Room.createRoom("A new room");
      cache.put(room.getId(), room);
    }
    return room;
  }

  private String getRoomId(String playerId) {
    HashMap<String, String> map = (HashMap<String, String>) cache.get(KEY_ROOM_TO_PLAYER_HASHMAP);
    if (map == null) {
      map = new HashMap<String, String>();
      cache.put(KEY_ROOM_TO_PLAYER_HASHMAP, map);
    }
    return map.get(playerId);
  }

  public Character getCharacter(String characterId) {
    return (Character) cache.get(characterId);
  }

  private Character createPlayer() {
    Room room = getRoom(KEY_GLOBAL_ROOM);
    Character player = Player.createJedi("anon", 100);
    room.addCharacter(player);
    return player;
  }

}

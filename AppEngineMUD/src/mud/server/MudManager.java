package mud.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Collection;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import mud.client.model.Character;
import mud.client.model.Player;
import mud.client.model.Room;

import com.allen_sauer.gwt.log.client.Log;

public class MudManager {
  private static final String KEY_ROOM_TO_PLAYER_HASHMAP = "KEY_ROOM_TO_PLAYER_HASHMAP";
  private static String GLOBAL_ROOM_ID = "GLOBAL_ROOM_ID";
  private Cache cache;

  private MudManager() {
    try {
      CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
      cache = cacheFactory.createCache(Collections.emptyMap());
    } catch (CacheException e) {
      Log.fatal("Unable to create cache", e);
    }
    globalRoomId = ((String) cache.get(GLOBAL_ROOM_ID));
    if (globalRoomId == null) {
      Room room = Room.createRoom("Space Port");
      globalRoomId = room.getId();
      cache.put(globalRoomId, room);
      cache.put(GLOBAL_ROOM_ID, globalRoomId);
    }
  }

  private static MudManager singleton = new MudManager();
  private static String globalRoomId;

  public static MudManager get() {
    return singleton;
  }

  public Room getRoomByPlayerId(String playerId) {
    String roomId = getRoomIdByPlayerId(playerId);
    return getRoomByRoomId(roomId);
  }

  private String getRoomIdByPlayerId(String playerId) {
    HashMap<String, String> map = getRoomToPlayerMap();
    return map.get(playerId);
  }

  private HashMap<String, String> getRoomToPlayerMap() {
    HashMap<String, String> map = (HashMap<String, String>) cache.get(KEY_ROOM_TO_PLAYER_HASHMAP);
    if (map == null) {
      map = new HashMap<String, String>();
      cache.put(KEY_ROOM_TO_PLAYER_HASHMAP, map);
    }
    return map;
  }

  public Character getCharacter(String characterId) {
    return (Character) cache.get(characterId);
  }

  public Character createPlayer() {
    Room room = getRoomByRoomId(globalRoomId);
    Character player = Player.createJedi("anon", 100);

    HashMap<String, String> map = getRoomToPlayerMap();
    room.addCharacter(player);
    map.put(room.getId(), player.getId());
    cache.put(KEY_ROOM_TO_PLAYER_HASHMAP, map);

    cache.put(GLOBAL_ROOM_ID, room);
    return player;
  }

  private Room getRoomByRoomId(String roomId) {
    Room room = (Room) cache.get(roomId);
    if (room == null) {
      room = Room.createRoom("A new room");
      cache.put(room.getId(), room);
    }
    return room;
  }

}

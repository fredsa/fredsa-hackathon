package mud.client.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;
import java.io.Serializable;

import mud.client.util.TimeIdSource;

/**
 * Created by IntelliJ IDEA. User: teejae Date: Jun 11, 2009 Time: 12:27:53 PM To change this
 * template use File | Settings | File Templates.
 */
public class Room implements HasId, Serializable {

  private final String uniqueId;
  private final Set<String> connectedRoomIdSet = new HashSet<String>();
  private final Set<String> characterIdSet = new HashSet<String>();
  private final String description;

  private Room(String uniqueId, String description) {
    this.uniqueId = uniqueId;
    this.description = description;
  }

  public static Room createRoom(String description) {
    return new Room(TimeIdSource.getInstance().getNewId(), description);
  }

  public void connectToRoom(Room room) {
    connectedRoomIdSet.add(room.getId());
  }

  public void disconnectFromRoom(Room room) {
    connectedRoomIdSet.remove(room.getId());
  }

  public void removeCharacter(Character character) {
    characterIdSet.remove(character.getId());
  }

  public Set<String> getConnectedRoomIdSet() {
    return Collections.unmodifiableSet(connectedRoomIdSet);
  }

  public Set<String> getCharacterIdSet() {
    return Collections.unmodifiableSet(characterIdSet);
  }

  public void addCharacter(Character character) {
    characterIdSet.add(character.getId());
  }

  public String getId() {
    return uniqueId;
  }
}

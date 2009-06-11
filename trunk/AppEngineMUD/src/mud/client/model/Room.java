package mud.client.model;

import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

import mud.client.util.MemcacheIdSource;

/**
 * Created by IntelliJ IDEA. User: teejae Date: Jun 11, 2009 Time: 12:27:53 PM To change this
 * template use File | Settings | File Templates.
 */
public class Room implements Serializable {

  private final String uniqueId;
  private final Set<Room> connectedRooms = new HashSet<Room>();
  private final Set<Character> characterSet = new HashSet<Character>();

  private Room(String uniqueId) {
    this.uniqueId = uniqueId;
  }

  public Room createRoom() {
    return new Room(MemcacheIdSource.getInstance().getNewId());
  }

  public void connectToRoom(Room room) {
    connectedRooms.add(room);
  }

  public void disconnectFromRoom(Room room) {
    connectedRooms.remove(room);
  }

  public void removeFromRoom(Character character) {
    characterSet.remove(character);
  }

  public void addToRoom(Character character) {
    characterSet.add(character);
  }
  
}

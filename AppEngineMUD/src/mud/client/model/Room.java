package mud.client.model;

import java.util.Set;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA. User: teejae Date: Jun 11, 2009 Time: 12:27:53 PM To change this
 * template use File | Settings | File Templates.
 */
public class Room {

  private final Set<Room> connectedRooms = new HashSet<Room>();
  private final Set<Character> characterSet = new HashSet<Character>();

  private Room() {
  }

  public Room createRoom() {
    return new Room();
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

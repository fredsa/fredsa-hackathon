package mud.server;

import java.util.Collection;
import java.util.LinkedList;

import mud.client.RpcService;
import mud.client.action.Action;
import mud.client.action.GetNewPlayerAction;
import mud.client.action.GetNewPlayerResponse;
import mud.client.action.Response;
import mud.client.action.TypedAction;
import mud.client.action.TypedResponse;
import mud.client.model.Character;
import mud.client.model.Room;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

  MudManager mgr = MudManager.get();

  private Collection<Character> getCharacters(Collection<String> characterIds) {
    Collection<Character> characters = new LinkedList<Character>();
    for (String characterId : characterIds) {
      characters.add(mgr.getCharacter(characterId));
    }

    return characters;
  }

  public TypedResponse execute(TypedAction action) {
    String text = action.getText().trim() + " ";
    int pos = (text).indexOf(" ");
    String cmd = text.substring(0, pos);
    text = text.substring(pos + 1).trim();
    Character player = action.getPlayer();
    final Room room = mgr.getRoomByPlayerId(player.getId());
    final Collection<Character> characters = getCharacters(room.getCharacterIdSet());

    StringBuffer outputBuffer = new StringBuffer();
    if ("say".equals(cmd) || "'".equals(cmd)) {
      String playerQuote = player.getName() + " said: " + text;
      broadcastMessage(characters, playerQuote);
    } else if ("hit".equals(cmd)) {
      String otherPlayerName = text;

      boolean successful = false;
      for (Character character : characters) {
        if (otherPlayerName.equals(character.getName())) {
          Character otherPlayer = character;
          otherPlayer.hitBy(player);

          broadcastMessage(characters, player.getName() + " hit " + text);
          successful = true;
          break;
        }
      }

      if (!successful) {
        broadcastMessage(characters, player.getName() + " tried to hit " + otherPlayerName
            + ", but that person is not here");
      }
    } else if ("look".equals(cmd)) {
      outputBuffer.append(room.getDescription());
      for (Character character : characters) {
        outputBuffer.append("\n- " + character.getName());
      }
    } else {
      outputBuffer.append("what?! Unknown command: " + text);
    }

    persistCharacters(characters);

    player = mgr.getCharacter(player.getId());
    for (String m : player.getUnreadMessages()) {
      outputBuffer.append(m);
    }
    player.clearUnreadMessages();
    mgr.persistCharacter(player);

    return new TypedResponse(outputBuffer.toString());
  }

  private void broadcastMessage(Collection<Character> characters, String message) {
    for (Character character : characters) {
      character.addMessage(message);
    }
  }

  private void persistCharacters(Collection<Character> characters) {
    for (Character character : characters) {
      mgr.persistCharacter(character);
    }
  }

  public GetNewPlayerResponse execute(GetNewPlayerAction action) {
    return new GetNewPlayerResponse(mgr.createPlayer(action.getName()));
  }

  public <T extends Response> T execute(Action action) {
    if (action instanceof TypedAction) {
      return (T) execute((TypedAction) action);
    } else if (action instanceof GetNewPlayerAction) {
      return (T) execute((GetNewPlayerAction) action);
    }
    return null;
  }
}

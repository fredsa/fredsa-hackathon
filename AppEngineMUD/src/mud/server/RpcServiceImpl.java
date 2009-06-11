package mud.server;

import mud.client.RpcService;
import mud.client.action.Action;
import mud.client.action.GetNewPlayerAction;
import mud.client.action.GetNewPlayerResponse;
import mud.client.action.Response;
import mud.client.action.TypedAction;
import mud.client.action.TypedResponse;
import mud.client.model.*;
import mud.client.model.Character;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.Collection;
import java.util.LinkedList;

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
    final Character player = action.getPlayer();

    StringBuffer outputBuffer = new StringBuffer();
    if ("say".equals(cmd) || "'".equals(cmd)) {
      String playerQuote = player.getName() + " said: " + text;
      Room room = mgr.getRoomByPlayerId(player.getId());
      for (Character character : getCharacters(room.getCharacterIdSet())) {
        character.addMessage(playerQuote);
      }
    } else if ("hit".equals(cmd)) {
      outputBuffer.append(player.getName() + " hit " + text);
    } else if ("look".equals(cmd)) {
      Room room = mgr.getRoomByPlayerId(player.getId());
      outputBuffer.append(room.getDescription());
      for (Character character : getCharacters(room.getCharacterIdSet())) {
        outputBuffer.append("\n- " + character.getName());
      }
    } else {
      outputBuffer.append("what?! Unknown command: " + text);
    }

    outputBuffer.append(player.getUnreadMessages());

    return new TypedResponse(outputBuffer.toString());
  }

  public GetNewPlayerResponse execute(GetNewPlayerAction action) {
    return new GetNewPlayerResponse(mgr.createPlayer());
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

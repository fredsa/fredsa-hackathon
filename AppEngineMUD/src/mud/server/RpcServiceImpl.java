package mud.server;

import mud.client.RpcService;
import mud.client.action.Action;
import mud.client.action.GetNewPlayerAction;
import mud.client.action.GetNewPlayerResponse;
import mud.client.action.Response;
import mud.client.action.TypedAction;
import mud.client.action.TypedResponse;
import mud.client.model.Room;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

  MudManager mgr = MudManager.get();

  public TypedResponse execute(TypedAction action) {
    String text = action.getText().trim() + " ";
    int pos = (text).indexOf(" ");
    String cmd = text.substring(0, pos);
    text = text.substring(pos + 1).trim();
    if ("say".equals(cmd) || "'".equals(cmd)) {
      return new TypedResponse(action.getPlayer().getName() + " said: " + text);
    } else if ("hit".equals(cmd)) {
      return new TypedResponse(action.getPlayer().getName() + " hit " + text);
    } else if ("look".equals(cmd)) {
      Room room = mgr.getRoom(action.getPlayer().getId());
      String output = room.getDescription();
      for (String characterId : room.getCharacterIdSet()) {
        output += "\n- " + mgr.getCharacter(characterId).getName();
      }
      return new TypedResponse(output);
    } else {
      return new TypedResponse("what?! Unknown command: " + text);
    }
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

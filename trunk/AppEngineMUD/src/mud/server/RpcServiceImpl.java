package mud.server;

import mud.client.RpcService;
import mud.client.action.Action;
import mud.client.action.GetNewPlayerAction;
import mud.client.action.GetNewPlayerResponse;
import mud.client.action.Response;
import mud.client.action.TypedAction;
import mud.client.action.TypedResponse;
import mud.client.model.Player;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

  RoomManager mgr = RoomManager.get();

  public TypedResponse execute(TypedAction action) {
    String text = action.getText().trim() + " ";
    int pos = (text).indexOf(" ");
    String cmd = text.substring(0, pos);
    text = text.substring(pos + 1).trim();
    return new TypedResponse(action.getPlayer().getName() + " [" + cmd + "] " + text);
  }

  public GetNewPlayerResponse execute(GetNewPlayerAction action) {
    return new GetNewPlayerResponse(Player.createJedi("anon", 100));
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

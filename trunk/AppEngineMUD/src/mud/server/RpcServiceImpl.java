package mud.server;

import mud.client.RpcService;
import mud.client.action.Action;
import mud.client.action.Response;
import mud.client.action.TypedAction;
import mud.client.action.TypedResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

	RoomManager mgr = RoomManager.get();

	public TypedResponse execute(TypedAction action) {
		return new TypedResponse("You said/did: " + action.getText());
	}

	public <T extends Response> T execute(Action action) {
		if (action instanceof TypedAction) {
			return (T) execute((TypedAction) action);
		}
		return null;
	}
}

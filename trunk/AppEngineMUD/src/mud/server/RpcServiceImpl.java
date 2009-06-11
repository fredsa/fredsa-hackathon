package mud.server;

import mud.client.Action;
import mud.client.Response;
import mud.client.RpcService;
import mud.client.TypedAction;
import mud.client.TypedResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

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

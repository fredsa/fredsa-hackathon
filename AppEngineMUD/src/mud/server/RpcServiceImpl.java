package mud.server;

import mud.client.Action;
import mud.client.RpcService;
import mud.client.Response;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements
		RpcService {
	public <T extends Response> T execute(Action action) {
		return null;
	}
}

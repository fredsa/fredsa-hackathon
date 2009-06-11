package mud.client;

import mud.client.action.Action;
import mud.client.action.Response;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc")
public interface RpcService extends RemoteService {
	<T extends Response> T execute(Action action);
}

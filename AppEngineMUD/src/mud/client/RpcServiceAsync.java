package mud.client;

import mud.client.action.Action;
import mud.client.action.Response;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RpcServiceAsync {
	<T extends Response> void execute(Action<T> action,
			AsyncCallback<T> callback);
}

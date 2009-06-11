package mud.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RpcServiceAsync {
	<T extends Response> void execute(Action<T> action,
			AsyncCallback<T> callback);
}

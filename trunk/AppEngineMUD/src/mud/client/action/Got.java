package mud.client.action;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class Got<T extends Response> implements AsyncCallback<T> {

	public void onFailure(Throwable caught) {
		Log.error(this.getClass().getName(), caught);
	}

}

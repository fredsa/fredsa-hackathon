package mud.client;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class GotTypedResponse implements AsyncCallback<TypedResponse> {

	public void onFailure(Throwable caught) {
		Log.error("GotTypedResponse", caught);
	}

	public void onSuccess(TypedResponse result) {
		String text = result.getText();
		if (text == null) {
			text = "";
		}
		got(text);
	}

	abstract void got(String text);
}

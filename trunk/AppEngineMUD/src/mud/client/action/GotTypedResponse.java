package mud.client.action;


public abstract class GotTypedResponse extends Got<TypedResponse> {

	public void onSuccess(TypedResponse result) {
		String text = result.getText();
		if (text == null) {
			text = "";
		}
		got(text);
	}

	public abstract void got(String text);
}

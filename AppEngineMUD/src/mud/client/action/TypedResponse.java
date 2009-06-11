package mud.client.action;

public class TypedResponse implements Response {
	private String responseText;

	private TypedResponse() {
	}

	public TypedResponse(String responseText) {
		this.responseText = responseText;
	}

	public String getText() {
		return responseText;
	}

}

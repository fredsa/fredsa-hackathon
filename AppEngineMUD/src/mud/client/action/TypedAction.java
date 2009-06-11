package mud.client.action;

public class TypedAction implements Action<TypedResponse> {

	private String text;

	private TypedAction() {
	}

	public TypedAction(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}

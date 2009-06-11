package mud.client.action;

import mud.client.model.Character;

public class GetNewPlayerResponse implements Response {
	private Character character;

	private GetNewPlayerResponse() {
	}

	public GetNewPlayerResponse(Character character) {
		this.character = character;
	}

	public Character getCharacter() {
		return character;
	}

}

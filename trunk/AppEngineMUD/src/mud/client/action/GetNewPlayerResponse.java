package mud.client.action;

import mud.client.model.Player;

public class GetNewPlayerResponse implements Response {
	private final Player player;

	public GetNewPlayerResponse(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
}

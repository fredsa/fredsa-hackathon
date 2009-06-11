package mud.client.action;

import mud.client.model.Character;

public class TypedAction implements Action<TypedResponse> {

  private String text;
  private Character player;

  private TypedAction() {
  }

  public TypedAction(Character player, String text) {
    this.player = player;
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public Character getPlayer() {
    return player;
  }

}

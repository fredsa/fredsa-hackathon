package mud.client.action;

import mud.client.model.Character;

public abstract class GotPlayer extends Got<GetNewPlayerResponse> {

  public void onSuccess(GetNewPlayerResponse result) {
    got(result.getCharacter());
  }

  public abstract void got(Character character);

}

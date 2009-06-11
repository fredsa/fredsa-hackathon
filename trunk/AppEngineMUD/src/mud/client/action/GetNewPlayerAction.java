package mud.client.action;

public class GetNewPlayerAction implements Action<GetNewPlayerResponse> {
  private String name;

  private GetNewPlayerAction() {
  }

  public GetNewPlayerAction(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}

package mud.client.model;

/**
 * Created by IntelliJ IDEA. User: teejae Date: Jun 11, 2009 Time: 12:28:56 PM To change this
 * template use File | Settings | File Templates.
 */
public class Player implements Character {
  private final String name;
  private final LifeStyle lifeStyle;
  private int hitPoints;

  private Player(String name, int hitPoints, LifeStyle lifeStyle) {
    this.name = name;
    this.hitPoints = hitPoints;
    this.lifeStyle = lifeStyle;
  }

  public static Character createMortal(String name, int hitPoints) {
    return new Player(name, hitPoints, Character.LifeStyle.MORTAL);
  }

  public static Character createJedi(String name, int hitPoints) {
    return new Player(name, hitPoints, Character.LifeStyle.JEDI);
  }

  public String getName() {
    return name;
  }

  public int getHitPoints() {
    return hitPoints;
  }

  public LifeStyle getLifeStyle() {
    return lifeStyle;
  }

  public void addHitPoints(int hitPoints) {
    this.hitPoints += hitPoints;
  }

  public void removeHitPoints(int hitPoints) {
    this.hitPoints -= hitPoints;
  }
}

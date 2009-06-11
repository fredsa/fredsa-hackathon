package mud.client.model;

import java.io.Serializable;

import mud.client.util.TimeIdSource;

/**
 * Created by IntelliJ IDEA. User: teejae Date: Jun 11, 2009 Time: 1:46:43 PM To change this
 * template use File | Settings | File Templates.
 */
public class NonPlayerCharacter implements Character, Serializable {
  private static final String OGRE_NAME = "ogre";
  private static final int OGRE_HIT_POINTS = 20;

  private final String uniqueId;

  private final String name;
  private final LifeStyle lifeStyle;
  private int hitPoints;

  private NonPlayerCharacter(String uniqueId, String name, int hitPoints, LifeStyle lifeStyle) {
    this.uniqueId = uniqueId;
    this.name = name;
    this.hitPoints = hitPoints;
    this.lifeStyle = lifeStyle;
  }

  public static Character createOgre() {
    return new NonPlayerCharacter(TimeIdSource.getInstance().getNewId(), OGRE_NAME,
        OGRE_HIT_POINTS, Character.LifeStyle.MORTAL);
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

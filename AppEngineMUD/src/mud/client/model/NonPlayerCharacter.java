package mud.client.model;

import java.io.Serializable;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

import mud.client.util.TimeIdSource;

/**
 * Created by IntelliJ IDEA. User: teejae Date: Jun 11, 2009 Time: 1:46:43 PM To change this
 * template use File | Settings | File Templates.
 */
public class NonPlayerCharacter implements Character, Serializable {
  private static final List<String> EMPTY_LIST = new LinkedList<String>();
  private static final String OGRE_NAME = "ogre";
  private static final int OGRE_HIT_POINTS = 20;
  private static final int OGRE_STRENGTH = 5;

  private final String uniqueId;
  private final int strength;
  private final String name;
  private final LifeStyle lifeStyle;
  private int hitPoints;

  private NonPlayerCharacter(String uniqueId, String name, int hitPoints, int strength,
      LifeStyle lifeStyle) {
    this.uniqueId = uniqueId;
    this.name = name;
    this.hitPoints = hitPoints;
    this.strength = strength;
    this.lifeStyle = lifeStyle;
  }

  public static Character createOgre() {
    return new NonPlayerCharacter(TimeIdSource.getInstance().getNewId(), OGRE_NAME,
        OGRE_HIT_POINTS, OGRE_STRENGTH, Character.LifeStyle.MORTAL);
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
    this.hitPoints = Math.max(0, this.hitPoints - hitPoints);
  }

  public void hitBy(Character character) {
    this.removeHitPoints(character.getStrength());
  }

  public int getStrength() {
    return strength;
  }

  public void addMessage(String message) {
  }

  public List<String> getUnreadMessages() {
    return Collections.unmodifiableList(getUnreadMessages());
  }

  public void clearUnreadMessages() {
  }

  public String getId() {
    return uniqueId;
  }
}

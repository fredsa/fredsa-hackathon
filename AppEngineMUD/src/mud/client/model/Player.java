package mud.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import mud.client.util.TimeIdSource;

/*
 * * Created by IntelliJ IDEA. User: teejae Date: Jun 11, 2009 Time: 12:28:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player implements Character, Serializable {
  private static final int JEDI_STRENGTH = 7;
  private static final int MORTAL_STRENGTH = 3;

  private static final int MORTAL_HIT_POINTS = 30;
  private static final int JEDI_HIT_POINTS = MORTAL_HIT_POINTS;

  private /*final*/ String uniqueId;

  private /*final*/ int strength;
  private /*final*/ String name;
  private /*final*/ LifeStyle lifeStyle;
  private /*final*/ List<String> unreadMessages = new ArrayList<String>();
  private int hitPoints;

  /**
   * Required for GWT RPC
   */
  private Player() {
  }

  private Player(String uniqueId, String name, int hitPoints, int strength, LifeStyle lifeStyle) {
    this.uniqueId = uniqueId;
    this.name = name;
    this.hitPoints = hitPoints;
    this.strength = strength;
    this.lifeStyle = lifeStyle;
  }

  public static Character createMortal(String name) {
    return createMortal(name, MORTAL_HIT_POINTS);
  }

  public static Character createMortal(String name, int hitPoints) {
    return new Player(TimeIdSource.getInstance().getNewId(), name, hitPoints, MORTAL_STRENGTH,
        Character.LifeStyle.MORTAL);
  }

  public static Character createJedi(String name) {
    return createJedi(name, JEDI_HIT_POINTS);
  }

  public static Character createJedi(String name, int hitPoints) {
    return new Player(TimeIdSource.getInstance().getNewId(), name, hitPoints, JEDI_STRENGTH,
        Character.LifeStyle.JEDI);
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
    if ((lifeStyle == LifeStyle.JEDI) && (character.getLifeStyle() != LifeStyle.JEDI)) {
      // jedi is invincible
      return;
    } else {
      removeHitPoints(character.getStrength());
    }
  }

  public int getStrength() {
    return strength;
  }

  public void addMessage(String message) {
    unreadMessages.add(message);
  }

  public List<String> getUnreadMessages() {
    return Collections.unmodifiableList(unreadMessages);
  }

  public void clearUnreadMessages() {
    unreadMessages.clear();
  }

  public String getId() {
    return uniqueId;
  }
}

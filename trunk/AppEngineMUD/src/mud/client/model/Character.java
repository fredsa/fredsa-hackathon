package mud.client.model;

/**
 * Created by IntelliJ IDEA. User: teejae Date: Jun 11, 2009 Time: 1:34:42 PM To change this
 * template use File | Settings | File Templates.
 */
public interface Character {

  String getName();

  int getHitPoints();

  LifeStyle getLifeStyle();

  void addHitPoints(int hitPoints);

  void removeHitPoints(int hitPoints);

  public enum LifeStyle {MORTAL, JEDI}
}

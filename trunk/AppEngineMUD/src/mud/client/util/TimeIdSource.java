package mud.client.util;

/**
 * Created by IntelliJ IDEA. User: teejae Date: Jun 11, 2009 Time: 2:14:27 PM To change this
 * template use File | Settings | File Templates.
 */
public class TimeIdSource implements IdSource {
  private static final TimeIdSource TIME_ID_SOURCE = new TimeIdSource();

  public static IdSource getInstance() {
    return TIME_ID_SOURCE;
  }

  public String getNewId() {
    return String.valueOf(System.currentTimeMillis());
  }
}

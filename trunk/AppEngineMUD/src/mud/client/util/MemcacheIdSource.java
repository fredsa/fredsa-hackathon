package mud.client.util;

/**
 * Created by IntelliJ IDEA. User: teejae Date: Jun 11, 2009 Time: 2:14:27 PM To change this
 * template use File | Settings | File Templates.
 */
public class MemcacheIdSource implements IdSource {
  private static final MemcacheIdSource MEMCACHE_ID_SOURCE = new MemcacheIdSource();
  public static IdSource getInstance() {
    return MEMCACHE_ID_SOURCE;
  }

  public String getNewId() {
    return String.valueOf(System.currentTimeMillis());
  }
}

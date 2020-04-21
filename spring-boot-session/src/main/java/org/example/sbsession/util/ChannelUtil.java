package org.example.sbsession.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelUtil {

    /**
     * username -> Channel 的映射集合
     */
    private static final Map<String, Object> attributes = new ConcurrentHashMap<>();

    public static void bindUser(String user, Object channel) {
        attributes.put(user, channel);
    }

    public static void unBindUser(String user) {
        attributes.remove(user);
    }

    public static Object getChannel(String username) {
        return attributes.get(username);
    }


}

package org.example.commons.redis.util;

import org.springframework.stereotype.Component;

@Component
public class RedisCommonUtil {
    /**
     * 用于组装多字段的key
     *
     * @param objs
     * @return String
     */
    public static String assembleKey(Object... objs) {
        String key = "";
        if (null != objs) {
            String sign = "_";
            int length = objs.length;
            int i = 0;
            while (i < length) {
                if (null != objs[i]) {
                    key += objs[i].toString();
                } else {
                    key += "";
                }
                i++;
                if (i < length) {
                    key += sign;
                }
            }
        }
        return key;
    }
}

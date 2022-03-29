package org.hamburger.boot.starter.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class GlobalContext {

    private final static TransmittableThreadLocal<Map<String, String>> context = new TransmittableThreadLocal<>();

    public static void put(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        Map<String, String> map;
        if (context.get() != null) {
            map = context.get();
            map.put(key, value);
        } else {
            map = new ConcurrentHashMap<>();
            map.put(key, value);
            context.set(map);
        }
    }

    public static String get(String key) {
        if (context.get() != null) {
            return context.get().get(key.toLowerCase());
        }
        return null;
    }
}

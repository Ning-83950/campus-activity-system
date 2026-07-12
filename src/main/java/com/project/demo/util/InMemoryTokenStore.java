package com.project.demo.util;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 进程内 Token 存储，替代 Redis，使项目在无外部缓存时也能运行（用于 Railway 演示部署）。
 */
@Component
public class InMemoryTokenStore {

    private final Map<String, Entry> store = new ConcurrentHashMap<>();

    private static final class Entry {
        final Object value;
        final long expireAt;
        Entry(Object value, long expireAt) {
            this.value = value;
            this.expireAt = expireAt;
        }
    }

    public void set(String key, Object value, Duration duration) {
        long ttl = (duration == null) ? 0 : duration.toMillis();
        long exp = (ttl > 0) ? System.currentTimeMillis() + ttl : Long.MAX_VALUE;
        store.put(key, new Entry(value, exp));
    }

    public Object get(String key) {
        Entry e = store.get(key);
        if (e == null) {
            return null;
        }
        if (e.expireAt < System.currentTimeMillis()) {
            store.remove(key);
            return null;
        }
        return e.value;
    }

    public void delete(String key) {
        store.remove(key);
    }
}

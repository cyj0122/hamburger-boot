package org.hamburger.boot.core.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class TraceIdGenerator {
    private static final String PROCESS_UUID;
    static {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        PROCESS_UUID = uuid.substring(uuid.length() - 7);
    }

    private TraceIdGenerator() {
    }

    public synchronized static String generate() {
        return System.currentTimeMillis() + PROCESS_UUID + String.format("%08d", ThreadLocalRandom.current().nextInt(99999999));
    }
}

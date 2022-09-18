package com.edu.ulab.app.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorUtil {
    private static final AtomicLong ID = new AtomicLong(1);

    public static Long generateId() {
        return ID.getAndIncrement();
    }
}


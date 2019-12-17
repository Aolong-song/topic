package com.example.topic.util;

import java.util.UUID;

/**
 * @author 宋澳龙
 */
public class UUIDUtils {
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}

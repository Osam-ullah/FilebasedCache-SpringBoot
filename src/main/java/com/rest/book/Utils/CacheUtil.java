package com.rest.book.Utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CacheUtil {
    @Autowired
    private ObjectMapper objectMapper;

    private static final String CACHE_FILE = "src\\main\\java\\com\\rest\\book\\Utils\\Cache.json";

    public void put(String key, Object value) {
        Map<String, Object> cacheMap = readCacheFile();
        cacheMap.put(key, value);
        writeCacheFile(cacheMap);
    }

    public Object get(String key) {
        Map<String, Object> cacheMap = readCacheFile();
        return cacheMap.get(key);
    }

    public void remove(String key) {
        Map<String, Object> cacheMap = readCacheFile();
        cacheMap.remove(key);
        writeCacheFile(cacheMap);
    }

    private Map<String, Object> readCacheFile() {
        File file = new File(CACHE_FILE);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new HashMap<>();
    }

    private void writeCacheFile(Map<String, Object> cacheMap) {
        try {
            objectMapper.writeValue(new File(CACHE_FILE), cacheMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

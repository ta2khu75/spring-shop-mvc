package dev.ta2khu75.java5assignment.services.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ta2khu75.java5assignment.services.RedisService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void clear() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Override
    public <T> void saveList(String key, List<T> value) throws JsonProcessingException {
        String jsonObjectList = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, jsonObjectList);
    }

    @Override
    public <T> List<T> getList(String key, Class<T> c) throws JsonProcessingException {
        String jsonObjectList = (String) redisTemplate.opsForValue().get(key);
        return jsonObjectList == null ? null
                : objectMapper.readValue(jsonObjectList, new TypeReference<ArrayList<T>>() {
                });
        // if error can use follow code
        // return jsonObjectList==null? null : objectMapper.readValue(jsonObjectList,
        // objectMapper.getTypeFactory().constructCollectionType(List.class,
        // c));
    }

    @Override
    public <T> void save(String key, T value) throws JsonProcessingException {
        String jsonObject = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, jsonObject);
    }

    @Override
    public <T> T get(String key, Class<T> c) throws JsonProcessingException {
        String jsonObject = (String) redisTemplate.opsForValue().get(key);
        return jsonObject == null ? null : objectMapper.readValue(jsonObject, c);
    }

    @Override
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}

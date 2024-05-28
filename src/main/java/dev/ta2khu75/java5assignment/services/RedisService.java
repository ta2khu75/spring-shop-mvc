package dev.ta2khu75.java5assignment.services;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface RedisService {
    void clear();
    <T> void saveList(String key, List<T> value) throws JsonProcessingException;
    <T> List<T> getList(String key, Class<T> t) throws JsonMappingException, JsonProcessingException;
    <T> void save(String key, T value) throws JsonProcessingException;
    <T> T get(String key, Class<T> t) throws JsonMappingException, JsonProcessingException, ClassNotFoundException;
    // void saveItem(String key);
}
package dev.ta2khu75.java5assignment.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonProcessingException;
public interface RedisService {
    void clear();
    void deleteKey(String key);
    <T> void saveList(String key, List<T> value) throws JsonProcessingException;
    <T> List<T> getList(String key, Class<T> c) throws JsonProcessingException;
    <T> void save(String key, T value) throws JsonProcessingException;
    <T> T get(String key, Class<T> c) throws JsonProcessingException;
    <T> void savePage(String key, Page<T> value) throws JsonProcessingException;
    <T> Page<T> getPage(String key, Class<T> c) throws JsonProcessingException;
}

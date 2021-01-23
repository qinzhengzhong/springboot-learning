package com.allan.springbootredis.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Auther: qinzhengzhong
 * @Date: 2021/1/16 15:28
 * @Description:
 */
@Service
public class RedisSortServerImpl implements RedisSortServer{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onClick(String key, String name, Double score) {
        redisTemplate.boundZSetOps(key).incrementScore(name,score);
    }

    @Override
    public Set<String> getSort(String key, Long start, Long end) {
        return redisTemplate.boundZSetOps(key).range(start, end);
    }

    @Override
    public Double getScore(String key, String name) {
        return redisTemplate.boundZSetOps(key).score(name);
    }
}

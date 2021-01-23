package com.allan.springbootredis.server;

import java.util.Set;

/**
 * @Auther: qinzhengzhong
 * @Date: 2021/1/16 15:27
 * @Description:
 */
public interface RedisSortServer {
    /**
     * 点击功能，修改排名,每点击一次，排名分数+1
     * @param key
     * @param name
     * @param score
     */
    void onClick(String key,String name,Double score);

    /**
     * 获取所有排名
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<String> getSort(String key, Long start, Long end);

    /**
     * 获取单个的排名分数
     * @param key
     * @param name
     * @return
     */
    Double getScore(String key,String name);
}

package com.netease.urs.chain.api;


import com.netease.urs.chain.exceptions.ValueExistsException;
import com.netease.urs.chain.exceptions.ValueNotExistsException;
import com.netease.urs.chain.support.NodeResult;

import java.util.List;
import java.util.Map;

/**
 * 值栈
 *
 * Created by liuxin on 2016/10/11.
 */
public interface ValueStack {


    Map<String, Object> getContext();

    /**
     * 获得执行结果栈
     *
     * @return
     */
    List<NodeResult> getResultStack();

    /**
     * 获得上一个Node执行的Result
     *
     * @return
     */
    NodeResult getPreResult();

    void setPreResult(NodeResult result);

    /**
     * 获取context value
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 获取context value, 如果不存在会抛出异常
     *
     * @return
     * @throws ValueNotExistsException
     */
    Object getIfExists(String key) throws ValueNotExistsException;

    /**
     * 设置context value
     *
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 设置context value, 如果值已经存在则抛出异常
     *
     * @param key
     * @param value
     * @throws ValueExistsException
     */
    void setIfExists(String key, Object value) throws ValueExistsException;

    /**
     * 获得context value, 中String的值
     *
     * @param key
     * @return
     */
    String getAsString(String key);

    /**
     * 获得context value, 中String的值, 如果值不存在则抛出异常
     *
     * @param key
     * @return
     * @throws ValueNotExistsException
     */
    String getAsStringIfExists(String key) throws ValueNotExistsException;

    /**
     * 值是否存在
     *
     * @param key
     * @return
     */
    boolean contains(String key);

    /**
     * 查找类型匹配的值, 如果存在则返回最先匹配到的
     *
     * @param type 查找类型
     * @return 匹配的值
     */
    <T> T getByType(Class<T> type);

}

package com.netease.urs.chain.api;

import com.netease.urs.chain.support.NodeResult;

import java.util.Map;

/**
 * 业务节点, 处理具体的业务逻辑
 *
 * @author hzliuxin1 Created on 2017/11/28 0028
 */
public interface Node {
    /**
     * 处理业务
     *
     * @param valueStack 值栈
     * @return
     */
    NodeResult doNode(ValueStack valueStack) throws Exception;

    /**
     * 业务节点名称
     */
    String getName();

    /**
     * 获得流向
     */
    Map<String, String> getForwards();
}

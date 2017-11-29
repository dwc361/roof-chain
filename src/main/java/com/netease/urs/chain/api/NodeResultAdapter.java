package com.netease.urs.chain.api;

import com.netease.urs.chain.support.NodeResult;

/**
 * 节点方法返回适配器
 * <p>
 * 当节点方法返回的结果不为{@link String}或者{@link NodeResult}时,
 * 将节点方法返回的结果转化成决定责任流转的{@link NodeResult}
 * </p>
 * <p>
 * 节点方法所在类实现该接口即可
 * </p>
 */
public interface NodeResultAdapter {
    /**
     * 将具体的业务方法返回转化成节点返回
     *
     * @param result 业务方法返回
     * @return 节点返回
     */
    NodeResult convertResult(Object result);
}

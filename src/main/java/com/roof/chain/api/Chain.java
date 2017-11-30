package com.roof.chain.api;


/**
 * 一个chain对应一个工作流, 负责流转
 * 流程中的每一步骤由{@link Node}组成
 * @author hzliuxin1 Created on 2017/11/25 0025
 */
public interface Chain {
    Object doChain(ValueStack valueStack) throws Exception;
}
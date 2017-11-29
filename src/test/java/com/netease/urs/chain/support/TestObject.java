package com.netease.urs.chain.support;

import com.netease.urs.chain.api.ValueStackParam;


public class TestObject {

    public void testMethod(int intParam, Long longParam, NodeResult nodeResult, @ValueStackParam(value = "aa", required = true) String alias) {

    }
}

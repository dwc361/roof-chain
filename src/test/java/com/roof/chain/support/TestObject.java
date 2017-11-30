package com.roof.chain.support;

import com.roof.chain.api.ValueStackParam;


public class TestObject {

    public void testMethod(int intParam, Long longParam, NodeResult nodeResult, @ValueStackParam(value = "aa", required = true) String alias) {

    }
}

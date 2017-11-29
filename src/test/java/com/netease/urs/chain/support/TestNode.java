package com.netease.urs.chain.support;

import com.netease.urs.chain.api.ValueStack;

public class TestNode {

    public String method1(ValueStack valueStack) {
        if (valueStack != null) {
            System.out.println(valueStack);
        }
        System.out.println("invoke method1");
        return "success";
    }

    public String method2(String name) {
        System.out.println("invoke method2");
        return "success";
    }

    public String doNode(long long1) {
        System.out.println("invoke doNode");
        return "success";
    }

    public String method3(TestObject testObject) {
        if (testObject != null) {
            System.out.println(testObject);
        }
        System.out.println("invoke end");
        return "end";
    }
}

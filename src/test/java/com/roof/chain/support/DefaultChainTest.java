package com.roof.chain.support;

import com.roof.chain.api.Chain;
import com.roof.chain.api.ValueStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:spring-chain-test.xml"})
public class DefaultChainTest {
    private Chain chain;

    @Test
    public void testChain() {
        Class cls = GenericChain.class;
        Method method = BeanUtils.findMethodWithMinimalParameters(cls, "doChain");
        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
        for (String paramName : paramNames) {
            System.out.println(paramName);
        }
    }

    @Test
    public void testChain2() throws Exception {
        ValueStack valueStack = new GenericValueStack();
        valueStack.set("name", "myname");
        valueStack.set("long1", 1L);
        valueStack.set("testObject", new TestObject());
        chain.doChain(valueStack);
    }

    @Autowired
    public void setChain(@Qualifier("testChain1") Chain chain) {
        this.chain = chain;
    }
}
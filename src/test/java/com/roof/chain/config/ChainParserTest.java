package com.roof.chain.config;

import com.roof.chain.api.Chain;
import com.roof.chain.support.GenericValueStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liuxin
 * @since 2018/3/16 0016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:spring-chain-test2.xml"})
public class ChainParserTest {
    private Chain chain;

    @Test
    public void test() throws Throwable {
        System.out.println(chain);
        chain.doChain(new GenericValueStack());
    }

    @Autowired
    public void setChain(@Qualifier("testChain") Chain chain) {
        this.chain = chain;
    }
}
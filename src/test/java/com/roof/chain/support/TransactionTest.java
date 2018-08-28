package com.roof.chain.support;

import com.roof.chain.api.Chain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liuxin
 * @since 2018/8/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:spring-db-mybatis.xml", "classpath*:spring-chain-test2.xml"})
public class TransactionTest {
    private Chain chain;

    @Test
    public void testTransaction() throws Exception {
        System.out.println(chain);
        chain.doChain(new GenericValueStack());
    }

    @Autowired
    public void setChain(@Qualifier("testChain") Chain chain) {
        this.chain = chain;
    }
}

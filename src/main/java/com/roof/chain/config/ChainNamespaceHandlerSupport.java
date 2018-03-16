package com.roof.chain.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author hzliuxin1
 * @since 2018/3/14 0014
 */
public class ChainNamespaceHandlerSupport extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("chain",
                new ChainParser());
    }
}

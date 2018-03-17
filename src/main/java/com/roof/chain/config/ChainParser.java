package com.roof.chain.config;

import com.roof.chain.api.Chain;
import com.roof.chain.support.GenericChain;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

/**
 * @author liuxin
 * @since 2018/3/15 0015
 */
public class ChainParser extends AbstractSingleBeanDefinitionParser {
    private Class<? extends Chain> chainClass = GenericChain.class;

    @Override
    protected Class<?> getBeanClass(Element element) {
        return chainClass;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        String startNodeName = element.getAttribute("startNodeName");
        builder.addPropertyValue("startNodeName", startNodeName);
        Element childElements = DomUtils.getChildElementByTagName(element, "nodes");
        NodesParser nodesParser = new NodesParser();
        BeanDefinition beanDefinition = nodesParser.parse(childElements, parserContext);
        builder.addPropertyValue("nodes", beanDefinition);
    }

}

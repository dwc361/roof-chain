package com.roof.chain.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxin
 * @since 2018/3/16 0016
 */
public class NodesParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected boolean shouldGenerateId() {
        return true;
    }

    @Override
    protected Class<?> getBeanClass(Element element) {
        return ArrayList.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        List<Element> childElements = DomUtils.getChildElementsByTagName(element, "node");
        ManagedList<BeanDefinition> nodes = new ManagedList<BeanDefinition>();
        for (Element childElement : childElements) {
            BeanDefinition parse;
            String ref = childElement.getAttribute("ref");
            if (StringUtils.isNotEmpty(ref)) {
                parse = parserContext.getRegistry().getBeanDefinition(ref);
            } else {
                NodeParser nodeParser = new NodeParser();
                parse = nodeParser.parse(childElement, parserContext);
            }
            nodes.add(parse);
        }
        builder.addConstructorArgValue(nodes);
    }
}

package com.roof.chain.config;

import com.roof.chain.support.NodeFactoryBean;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @since 2018/3/16 0016
 */
public class NodeParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return NodeFactoryBean.class;
    }

    @Override
    protected boolean shouldGenerateId() {
        return true;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        String name = element.getAttribute("name");
        String methodName = element.getAttribute("methodName");
        builder.addPropertyValue("name", name);
        builder.addPropertyValue("methodName", methodName);

        Element targetElement = DomUtils.getChildElementByTagName(element, "target");
        Element beanElement = DomUtils.getChildElementByTagName(targetElement, "bean");

        BeanDefinitionHolder beanDefinitionHolder = parserContext.getDelegate().parseBeanDefinitionElement(
                beanElement, builder.getBeanDefinition());
        parserContext.getDelegate().decorateBeanDefinitionIfRequired(beanElement, beanDefinitionHolder);
        builder.addPropertyValue("target", beanDefinitionHolder);

        Element forwardsElement = DomUtils.getChildElementByTagName(element, "forwards");
        if (forwardsElement == null) {
            return;
        }
        List<Element> forwardElements = DomUtils.getChildElementsByTagName(forwardsElement, "forward");
        if (forwardElements == null || forwardElements.size() == 0) {
            return;
        }
        Map<String, String> forwardsMap = new HashMap<String, String>();
        for (Element forwardElement : forwardElements) {
            forwardsMap.put(forwardElement.getAttribute("next"), forwardElement.getAttribute("to"));
        }
        builder.addPropertyValue("forwards", forwardsMap);

    }
}

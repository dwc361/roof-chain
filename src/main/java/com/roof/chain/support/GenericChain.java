package com.roof.chain.support;

import com.roof.chain.api.Chain;
import com.roof.chain.api.Node;
import com.roof.chain.api.ValueStack;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一般的chain实现
 * <p>
 * Created by hzliuxin1 on 17/8/6.
 */
public class GenericChain implements Chain, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericChain.class);
    private Map<String, Node> nodeMap = new HashMap<String, Node>();
    private List<Node> nodes;
    private String startNodeName;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (nodes == null) {
            return;
        }
        for (Node node : nodes) {
            nodeMap.put(node.getName(), node);
        }
    }

    public Object doChain(ValueStack valueStack) throws Exception {
        Node nextNode = nodeMap.get(startNodeName);
        NodeResult nodeResult = null;
        while (nextNode != null) {
            nodeResult = nextNode.doNode(valueStack);
            valueStack.setPreResult(nodeResult);
            if (nodeResult == null) {
                return null;
            }
            if (nextNode.getForwards() == null) {
                return nodeResult;
            }
            String nextForward = nextNode.getForwards().get(nodeResult.getNext());
            if (StringUtils.isEmpty(nextForward)) {
                return nodeResult;
            }
            nextNode = nodeMap.get(nextForward);
        }
        return nodeResult;
    }

    public String getStartNodeName() {
        return startNodeName;
    }

    public void setStartNodeName(String startNodeName) {
        this.startNodeName = startNodeName;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}

package com.roof.chain.support;

import com.roof.chain.api.MethodParamDescriptor;
import com.roof.chain.api.Node;
import com.roof.chain.api.NodeResultAdapter;
import com.roof.chain.api.ValueStack;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 处理节点代理工厂Bean, 将业务方法包装成处理节点
 * @author liuxin
 */
public class NodeFactoryBean implements FactoryBean<Node>, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(NodeFactoryBean.class);
    private static final String DEFAULT_METHOD_NAME = "doNode";

    private String name;
    private Object target;
    private String methodName;

    private MethodParamDescriptor[] methodParameters;
    private FastMethod method;
    private Map<String, String> forwards;
    private Node instance;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(target, "target cannot null");
        if (StringUtils.isEmpty(methodName)) {
            methodName = DEFAULT_METHOD_NAME;
        }
        if (StringUtils.isEmpty(name)) {
            name = StringUtils.uncapitalize(target.getClass().getSimpleName());
        }
        MethodParser methodParser = new MethodParser(target.getClass(), methodName);
        methodParameters = methodParser.getMethodParameters();
        method = methodParser.getFastMethod();
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new NodeProxy();
                }
            }
        }
    }

    @Override
    public Node getObject() throws Exception {
        return instance;
    }

    @Override
    public Class<?> getObjectType() {
        return Node.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Map<String, String> getForwards() {
        return forwards;
    }

    public void setForwards(Map<String, String> forwards) {
        this.forwards = forwards;
    }

    /**
     * 业务方法的节点代理
     */
    public class NodeProxy implements Node, NodeResultAdapter {

        @Override
        public NodeResult doNode(ValueStack valueStack) {
            Object[] params = getParams(methodParameters, valueStack);
            try {
                Object methodReturn = method.invoke(target, params);
                if (ClassUtils.isAssignable(target.getClass(), NodeResultAdapter.class)) {
                    NodeResultAdapter nodeResultAdapter = (NodeResultAdapter) target;
                    return nodeResultAdapter.convertResult(methodReturn);
                } else {
                    return convertResult(methodReturn);
                }
            } catch (InvocationTargetException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return null;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Map<String, String> getForwards() {
            return forwards;
        }

        private Object[] getParams(MethodParamDescriptor[] methodParameters, ValueStack valueStack) {
            Object[] result = new Object[methodParameters.length];
            for (int i = 0; i < methodParameters.length; i++) {
                MethodParamDescriptor methodParameterDescriptor = methodParameters[i];
                Object val = null;
                if (StringUtils.isNotEmpty(methodParameterDescriptor.getAlias())) {
                    val = valueStack.get(methodParameterDescriptor.getAlias());
                }
                if (val == null) {
                    val = valueStack.get(methodParameterDescriptor.getName());
                }
                if (val == null
                        && ClassUtils.isAssignable(methodParameterDescriptor.getType(), ValueStack.class)) {
                    val = valueStack;
                }
                if (val == null) {
                    val = valueStack.getByType(methodParameterDescriptor.getType());
                }
                if (methodParameterDescriptor.isRequired() && val == null) {
                    throw new IllegalArgumentException(methodParameterDescriptor.getName() + "can not null");
                }
                result[i] = val;
            }
            return result;
        }

        @Override
        public NodeResult convertResult(Object result) {
            if (result == null) {
                return null;
            }
            if (result instanceof String) {
                return new NodeResult((String) result);
            }
            if (result instanceof NodeResult) {
                return (NodeResult) result;
            }
            return new NodeResult(result.toString());
        }
    }
}

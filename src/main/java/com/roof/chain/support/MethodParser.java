package com.roof.chain.support;

import com.roof.chain.api.MethodParamDescriptor;
import com.roof.chain.api.ValueStackParam;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 方法解析
 *
 * @author hzliuxin1 Created on 2017/11/25 0025
 */
public class MethodParser {

    private Method method;
    private Class<?> type;
    private Class<?>[] parameterTypes;
    private String[] paramNames;
    private Annotation[][] parameterAnnotations;

    public MethodParser(Object target, String methodName) {
        Assert.notNull(target, "target can not be null");
        Assert.notNull(methodName, "methodName can not be null");
        if (target instanceof Advised) {
            Advised advised = (Advised) target;
            type = advised.getTargetClass();
        } else {
            type = target.getClass();

        }
        this.method = BeanUtils.findMethodWithMinimalParameters(type, methodName);
        init();
    }

    private void init() {
        parameterTypes = method.getParameterTypes();
        paramNames = getParameterNames(method);
        parameterAnnotations = method.getParameterAnnotations();

    }

    private String[] getParameterNames(Method method) {
        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        return parameterNameDiscoverer.getParameterNames(method);
    }

    /**
     * 获得方法所有参数的描述
     *
     * @return 方法参数描述
     */
    public MethodParamDescriptor[] getMethodParameters() {
        MethodParamDescriptor[] methodParameters = new MethodParamDescriptor[parameterTypes.length];
        for (int i = 0; i < paramNames.length; i++) {
            MethodParamDescriptor methodParameter = new MethodParamDescriptor();
            methodParameter.setIndex(i);
            methodParameter.setName(paramNames[i]);
            methodParameter.setType(parameterTypes[i]);
            methodParameter.setAnnotations(parameterAnnotations[i]);
            parseValueStackParam(methodParameter);
            methodParameters[i] = methodParameter;
        }
        return methodParameters;
    }

    private void parseValueStackParam(MethodParamDescriptor methodParameter) {
        for (Annotation annotation : methodParameter.getAnnotations()) {
            if (annotation instanceof ValueStackParam) {
                ValueStackParam methodParam = (ValueStackParam) annotation;
                methodParameter.setAlias(methodParam.value());
                methodParameter.setRequired(methodParam.required());
            }
        }
    }

    /**
     * 获取FastMethod
     */
    public FastMethod getFastMethod() {
        FastClass fastClass = FastClass.create(type);
        return fastClass.getMethod(method);
    }

}

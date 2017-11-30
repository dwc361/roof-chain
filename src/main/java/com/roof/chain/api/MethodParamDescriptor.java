package com.roof.chain.api;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * 方法参数描述
 *
 * @author hzliuxin1 Created on 2017/11/25 0025
 */
public class MethodParamDescriptor {
    protected int index; //序列号
    protected String name; //参数名称
    protected String alias; //参数别名
    protected boolean required; // 参数是否必须
    protected Class<?> type;//参数类型
    protected Annotation[] annotations;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotation[] annotations) {
        this.annotations = annotations;
    }

    @Override
    public String toString() {
        return "MethodParamDescriptor{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", required=" + required +
                ", type=" + type +
                ", annotations=" + Arrays.toString(annotations) +
                '}';
    }
}

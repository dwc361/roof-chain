package com.roof.chain.support;

import com.roof.chain.api.ValueStack;
import com.roof.chain.exceptions.ValueExistsException;
import com.roof.chain.exceptions.ValueNotExistsException;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * chain中每个节点之间传递的一些上下文环境
 *
 * @author hzliuxin1
 */
public class GenericValueStack implements ValueStack {
    private Map<String, Object> context = new HashMap<String, Object>(); //上下文环境
    private NodeResult result;
    private List<NodeResult> results = new ArrayList<NodeResult>();
    private static final Class[] SIMPLE_CLASS = new Class[]{};

    @Override
    public List<NodeResult> getResultStack() {
        return results;
    }

    @Override
    public NodeResult getPreResult() {
        return result;
    }

    @Override
    public void setPreResult(NodeResult result) {
        if (result != null) {
            results.add(result);
        }
        this.result = result;
    }

    @Override
    public Object get(String key) {
        return context.get(key);
    }

    @Override
    public Object getIfExists(String key) throws ValueNotExistsException {
        if (!contains(key)) throw new ValueNotExistsException();
        return get(key);
    }

    @Override
    public void set(String key, Object value) {
        context.put(key, value);
    }

    @Override
    public void setIfExists(String key, Object value) throws ValueExistsException {
        if (contains(key)) throw new ValueExistsException();
        set(key, value);
    }

    @Override
    public boolean contains(String key) {
        return context.containsKey(key);
    }


    @Override
    public String getAsString(String key) {
        return ObjectUtils.toString(get(key));
    }

    @Override
    public String getAsStringIfExists(String key) throws ValueNotExistsException {
        if (!contains(key)) throw new ValueNotExistsException();
        return getAsString(key);
    }

    @Override
    public <T> T getByType(Class<T> type) {
        if (BeanUtils.isSimpleProperty(type)) {
            return null;
        }
        for (Object val : context.values()) {
            if (val == null) {
                return null;
            }
            if (ClassUtils.isAssignable(val.getClass(), type)) {
                return (T) val;
            }
        }
        return null;
    }


    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

}

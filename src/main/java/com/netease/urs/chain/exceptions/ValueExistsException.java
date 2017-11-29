package com.netease.urs.chain.exceptions;

/**
 * 值已经存在，并且当前插入方法不允许重复
 *
 * @author liuxin 2011-9-26
 * @version 1.0 ValueExistsException.java liuxin 2011-9-26
 */
public class ValueExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public ValueExistsException() {
        super();
    }


    public ValueExistsException(String message) {
        super(message);
    }


}

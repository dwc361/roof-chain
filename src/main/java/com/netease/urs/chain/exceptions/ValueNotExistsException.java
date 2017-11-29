package com.netease.urs.chain.exceptions;

/**
 * 当获取值的时候，值不存在
 *
 * @author liuxin 2011-9-26
 * @version 1.0 ValueNotExistsException.java liuxin 2011-9-26
 */
public class ValueNotExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public ValueNotExistsException() {
        super();
    }


    public ValueNotExistsException(String message) {
        super(message);
    }


}

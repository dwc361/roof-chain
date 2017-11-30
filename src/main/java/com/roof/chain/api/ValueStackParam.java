package com.roof.chain.api;

import java.lang.annotation.*;

/**
 * 方法参数注解
 *
 * @author hzliuxin1 Created on 2017/11/25 0025
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValueStackParam {
    /**
     * 参数别名,用于指定在ValueStack中的名称
     */
    String value() default "";

    /**
     * 参数是否必须
     */
    boolean required() default false;

}

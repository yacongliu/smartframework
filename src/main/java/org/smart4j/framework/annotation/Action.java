package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action 方法注解
 *
 * @author yacongliu on 2017/6/17.
 * @since v1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    /**
     *  @Action 注解作用于方法，并保留在运行时环境中
     */
    /**
     * 请求类型与路径
     * @return
     */
    String value();

}

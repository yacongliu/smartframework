package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yacongliu on 2017/6/17.
 * @since v1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    /**
     * 1. ElementType.TYPE: 作用目标是接口、类、枚举、注解
     * 2. RetentionPolicy.RUNTIME：注解会在class字节码文件中存在，在运行时可以通过反射获取到
     */
}

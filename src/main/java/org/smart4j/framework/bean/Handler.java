package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息(处理对象)
 *
 * @author yacongliu on 2017/6/18.
 * @since v1.0.0
 */
public class Handler {
    /**
     * Controller类
     */
    private Class<?> controllerClass;

    /**
     * Action 方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}

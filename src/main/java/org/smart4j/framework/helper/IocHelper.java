package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.utils.ArrayUtil;
import org.smart4j.framework.utils.CollectionUtil;
import org.smart4j.framework.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * DI(依赖注入)助手类
 *
 * @author yacongliu on 2017/6/18.
 * @since v1.0.0
 */
public final class IocHelper {
    /*
    1. 我们在Controller中定义Service成员变量，然后再Controller的Action方法中调用Service的成员变量，使用@Inject注入Service实例.
    2. 不是通过开发者 new 的方式来实例化，而是通过框架本身来进行实例化，这种实例化过程被称为IOC(控制反转)，控制不是开发者来决定，而是反传给了框架.
    3. 一般地，也将控制反转成为DI（依赖注入），理解为将某个类需要依赖的成员注入到这个类中

    #如何实现依赖注入呢？
    1. 通过BeanHelper 获取所有的Bean Map (是一个Map(Class<?>,Object)类与实例对象的映射关系).
    2. 遍历Bean Map 分别取出Bean类与Bean 实例，进而通过反射获取类中的成员变量.
    3. 继续遍历这些成员变量，在循环中判断当前成员变量是否带有@Inject注解，若带有该注解，则从Bean Map 中根据Bean 类获取Bean 实例.
    4. 通过ReflectionUtil#setField方法修改当前成员变量的值.
     */
    static {
        //获取所有的Bean类与Bean 实例的映射关系（简称Bean Map）
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            // 遍历 Bean Map  推荐使用 尤其是容量时，可以同时获取key value
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取Bean 类声明的所有成员变量（简称 Bean Field）
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    // 遍历Bean Field
                    for (Field beanField : beanFields) {
                        //判断当前Bean Field 是否带有@Inject 注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            // 在Bean Map 中获取Bean Field 对应的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                // 通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}

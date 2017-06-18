package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.utils.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手类
 *
 * @author yacongliu on 2017/6/17.
 * @since v1.0.0
 */
public final class ClassHelper {
    /*
    1. 由于在smart.properties 配置文件中制定了应用的基础包名，通过ClassUtil加载的类都需要基于该基础包名.
    2. 所以有必要提供一个类操作助手类，让它分别获取应用包名下的所有类，所有Controller类，Service类.
    3. 此外，我们可以将带有@Controller @Service 注解的类所产生的对象，让Smart框架进行管理Bean.
     */
    /**
     * 定义类集合（存放所加载的类）
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        //App 基础包名
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取APP基础包名下的所有类
     *
     * @return
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取APP中的Service类
     *
     * @return
     */
    public static Set<Class<?>> getServiceClassSet() {
        HashSet<Class<?>> calssSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Service.class)) {
                calssSet.add(cls);
            }
        }
        return calssSet;
    }

    /**
     * 获取APP中的Controllers类
     *
     * @return
     */
    public static Set<Class<?>> getControllerClassSet() {
        HashSet<Class<?>> calssSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                calssSet.add(cls);
            }
        }
        return calssSet;
    }

    /**
     * 获取应用包名下所有 Bean 类（包括：Service、Controller 等）
     *
     * @return
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

    /**
     * 获取应用包名下某父类（或接口）的所有子类（或实现类）
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下带有某注解的所有类
     *
     * @param annotationClass
     * @return
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(annotationClass)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

}

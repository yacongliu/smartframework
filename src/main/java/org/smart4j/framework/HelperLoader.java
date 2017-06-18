package org.smart4j.framework;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.utils.ClassUtil;

/**
 * 加载Helper类(即加载他们各自Helper类中的静态代码块)
 *
 * @author yacongliu on 2017/6/18.
 * @since v1.0.0
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {ClassHelper.class, BeanHelper.class, IocHelper.class, Controller.class};
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }

}

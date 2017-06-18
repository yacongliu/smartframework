package org.smart4j.framework.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 *
 * @author yacongliu on 2017/6/18.
 * @since v1.0.0
 */
public final class ArrayUtil {
    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

}

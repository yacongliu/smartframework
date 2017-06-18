package org.smart4j.framework.bean;

import org.smart4j.framework.utils.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 *
 * @author yacongliu on 2017/6/18.
 * @since v1.0.0
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }


    /**
     * 根据参数名获取long型参数值
     *
     * @param name
     * @return
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 获取所有字段信息
     *
     * @return Map<String,Object>
     */
    public Map<String, Object> getMap() {
        return paramMap;
    }
}

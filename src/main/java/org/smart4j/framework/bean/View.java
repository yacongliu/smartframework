package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 视图对象
 *
 * @author yacongliu on 2017/6/18.
 * @since v1.0.0
 */
public class View {
    /**
     * 视图View 是可以包含模型数据的
     */

    /**
     * 视图路径
     */
    private String path;
    /**
     * 模型数据
     */
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}

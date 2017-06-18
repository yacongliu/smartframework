package org.smart4j.framework.bean;

/**
 * 数据对象
 * 框架会将该对象写到HttpServletResponse
 *
 * @author yacongliu on 2017/6/18.
 * @since v1.0.0
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}

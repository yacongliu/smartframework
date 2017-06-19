package org.smart4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作工具类
 *
 * @author yacongliu on 2017/6/19.
 * @since v1.0.0
 */
public final class StreamUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     *
     * @param is
     * @return String
     */
    public static String getString(InputStream is) {
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        try {
            line = reader.readLine();
            while (line != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("GET STRING FAILURE",e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}

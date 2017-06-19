package org.smart4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码解码操作工具类
 *
 * @author yacongliu on 2017/6/19.
 * @since v1.0.0
 */
public final class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * URL编码
     *
     * @param String source
     * @return String
     */
    public static String encodeURL(String source) {
        String target = "";
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("ENCODE url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * URL 解码
     *
     * @param source
     * @return String
     */
    public static String decodeURL(String source) {
        String target = "";
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("DECODE url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }


}

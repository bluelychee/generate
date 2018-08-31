package com.xiaozhi.common.util;

import com.xiaozhi.common.exception.RunException;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件公共入口
 */
public class ConfigUtil {

    private static Properties prop = new Properties();
    private static final String path = "properties/init.properties";

    static {
        try {
            prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RunException("load此路径下的配置文件失败：" + path, e);
        }
    }

    /**
     * 根据key得到一个String值：
     */
    public static String getStringValue(String key) {
        String str = prop.getProperty(key);
        if (str != null) {
            str.trim();
        }
        return str;
    }

    public static Properties getProp() {
        return prop;
    }

    /**
     * 根据key得到一个int值：
     */
    public static int getIntValue(String key) {
        return StringUtils.parseInt(prop.getProperty(key), true);
    }

    public static int getIntValue(String key, int defaultValue) {
        try {
            return StringUtils.parseInt(prop.getProperty(key), true);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}

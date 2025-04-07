package com.jmeter.utils
/**
 * com.jmeter.utils.EnvUtils 类用于获取 JMeter 参数和系统环境变量。
 * 提供两种方法：获取 JMeter 参数和获取系统环境变量。
 */
class EnvUtils {

    /**
     * 获取 JMeter 参数值。
     * @param props JMeter 提供的 Properties 对象
     * @param key 参数的键
     * @param defaultVal 默认值（如果没有找到参数时返回的默认值）
     * @return 返回参数值，如果没有找到则返回默认值
     */
    static String getJMeterParam(def props, String key, String defaultVal = "") {
        return props.get(key) ?: defaultVal
    }

    /**
     * 获取系统环境变量的值。
     * @param key 环境变量的键
     * @param defaultVal 默认值（如果没有找到环境变量时返回的默认值）
     * @return 返回环境变量值，如果没有找到则返回默认值
     */
    static String getSysEnv(String key, String defaultVal = "") {
        return System.getenv(key) ?: defaultVal
    }
}

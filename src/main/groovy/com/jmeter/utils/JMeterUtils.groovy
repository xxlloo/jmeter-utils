package com.jmeter.utils
/**
 * com.jmeter.utils.JMeterUtils 类提供了一些 JMeter 特有的工具方法，
 * 用于获取响应数据、设置全局属性等操作。
 * 注意：需要在调用时传入 JMeter 绑定中的隐式变量，例如 prev、props。
 */
class JMeterUtils {

    /**
     * 获取当前请求的响应内容。
     * @param prev JMeter 提供的 SampleResult 对象
     * @return 返回当前请求的响应数据，以字符串形式表示
     */
    static String getResponseText(def prev) {
        return prev.getResponseDataAsString()
    }

    /**
     * 设置一个全局属性值。
     * @param props JMeter 提供的 Properties 对象
     * @param key 属性的键
     * @param val 属性的值
     */
    static void setGlobalProp(def props, String key, String val) {
        props.put(key, val)
    }

    /**
     * 获取当前 JMeter 测试计划的属性值。
     * @param props JMeter 提供的 Properties 对象
     * @param key 属性的键
     * @param defaultVal 如果属性不存在时返回的默认值
     * @return 返回属性值，如果不存在则返回默认值
     */
    static String getGlobalProp(def props, String key, String defaultVal = "") {
        return props.get(key) ?: defaultVal
    }

    /**
     * 获取当前请求的响应状态码。
     * @param prev JMeter 提供的 SampleResult 对象
     * @return 返回响应的状态码
     */
    static int getResponseStatusCode(def prev) {
        return prev.getResponseCodeAsInt()
    }

    /**
     * 设置当前请求的响应数据（用于模拟响应）。
     * @param prev JMeter 提供的 SampleResult 对象
     * @param data 要设置的响应数据（可以是字符串、JSON等）
     */
    static void setResponseData(def prev, String data) {
        prev.setResponseData(data, "UTF-8")
    }

    /**
     * 获取当前请求的响应时间。
     * @param prev JMeter 提供的 SampleResult 对象
     * @return 返回响应时间（毫秒）
     */
    static long getResponseTime(def prev) {
        return prev.getTime()
    }

    /**
     * 获取当前请求的响应头信息。
     * @param prev JMeter 提供的 SampleResult 对象
     * @return 返回响应头信息（以 Map 的形式）
     */
    static Map getResponseHeaders(def prev) {
        def headers = prev.getResponseHeaders()
        def result = [:]
        // 遍历响应头，转换为 Map
        headers.each { header ->
            def parts = header.split(": ", 2)
            if (parts.length == 2) {
                result[parts[0]] = parts[1]
            }
        }
        return result
    }
}

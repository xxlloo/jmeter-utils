package com.jmeter.utils
/**
 * com.jmeter.utils.TimeUtils 类提供了一些常用的时间处理工具方法。
 * 包括获取当前时间戳、时间格式化以及日期转换等操作。
 */
class TimeUtils {

    /**
     * 获取当前时间的毫秒数（自1970年1月1日起的毫秒数）。
     * @return 当前时间的毫秒时间戳
     */
    static long nowMillis() {
        return System.currentTimeMillis()
    }

    /**
     * 获取当前时间的秒数（自1970年1月1日起的秒数）。
     * @return 当前时间的秒时间戳
     */
    static long nowSeconds() {
        return System.currentTimeMillis() / 1000
    }

    /**
     * 获取当前时间并按照指定的格式进行格式化。
     * 默认格式为 "yyyy-MM-dd HH:mm:ss"。
     * @param pattern 格式化模式，默认为 "yyyy-MM-dd HH:mm:ss"
     * @return 格式化后的当前时间字符串
     */
    static String formatNow(String pattern = "yyyy-MM-dd HH:mm:ss") {
        return new Date().format(pattern)
    }

    /**
     * 将指定的日期对象按指定的格式进行格式化。
     * 默认格式为 "yyyy-MM-dd HH:mm:ss"。
     * @param date 需要格式化的日期对象
     * @param pattern 格式化模式，默认为 "yyyy-MM-dd HH:mm:ss"
     * @return 格式化后的日期字符串
     */
    static String format(Date date, String pattern = "yyyy-MM-dd HH:mm:ss") {
        return date.format(pattern)
    }

    /**
     * 获取当前时间的日期对象。
     * @return 当前时间的 Date 对象
     */
    static Date nowDate() {
        return new Date()
    }

    /**
     * 将毫秒时间戳转换为日期对象。
     * @param millis 毫秒时间戳
     * @return 对应的 Date 对象
     */
    static Date fromMillis(long millis) {
        return new Date(millis)
    }

    /**
     * 将指定的日期字符串按指定格式解析为 Date 对象。
     * @param dateStr 日期字符串
     * @param pattern 格式化模式
     * @return 解析后的 Date 对象
     */
    static Date parse(String dateStr, String pattern = "yyyy-MM-dd HH:mm:ss") {
        def sdf = new java.text.SimpleDateFormat(pattern)
        return sdf.parse(dateStr)
    }

    /**
     * 获取两个日期之间的时间差（单位：毫秒）。
     * @param start 起始时间
     * @param end 结束时间
     * @return 时间差（毫秒）
     */
    static long timeDifference(Date start, Date end) {
        return end.getTime() - start.getTime()
    }

    /**
     * 获取当前时间的 UNIX 时间戳（秒）。
     * @return UNIX 时间戳（秒）
     */
    static long unixTimestamp() {
        return System.currentTimeMillis() / 1000
    }
}

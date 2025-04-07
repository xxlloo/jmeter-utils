package com.jmeter.utils
/**
 * com.jmeter.utils.RandomUtils 类提供了一些生成随机数据的工具方法，
 * 包括 UUID、随机字符串、手机号、随机整数等。
 */
class RandomUtils {

    /**
     * 生成一个随机的 UUID（通用唯一标识符）。
     * UUID 是一个 128 位长的数字，用于唯一标识信息。
     * @return 一个随机生成的 UUID 字符串
     */
    static String uuid() {
        return UUID.randomUUID().toString()
    }

    /**
     * 生成一个随机字符串，字符集包括小写字母、大写字母和数字。
     * 默认长度为 8 个字符，可以指定长度。
     * @param len 字符串的长度，默认值为 8
     * @return 一个随机生成的字符串
     */
    static String randomString(int len = 8) {
        def chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..len).collect { chars[new Random().nextInt(chars.size())] }.join()
    }

    /**
     * 生成一个随机的中国手机号（11位）。
     * 生成的手机号以 "13" 开头，后面随机生成8位数字。
     * @return 一个随机生成的中国手机号
     */
    static String randomPhone() {
        return "13" + (100000000 + new Random().nextInt(899999999)).toString()
    }

    /**
     * 生成一个指定范围内的随机整数。
     * @param min 随机数的最小值
     * @param max 随机数的最大值
     * @return 介于 min 和 max 之间的随机整数
     */
    static int randomInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min
    }

    /**
     * 生成一个随机的十六进制字符串。
     * 十六进制字符包括0-9和a-f，总长度为 len。
     * @param len 十六进制字符串的长度，默认值为 8
     * @return 一个随机生成的十六进制字符串
     */
    static String randomHex(int len = 8) {
        def chars = ('0'..'9') + ('a'..'f')
        return (1..len).collect { chars[new Random().nextInt(chars.size())] }.join()
    }

    /**
     * 生成一个随机的浮点数。
     * 返回一个介于 min 和 max 之间的浮点数。
     * @param min 随机数的最小值
     * @param max 随机数的最大值
     * @return 介于 min 和 max 之间的随机浮点数
     */
    static float randomFloat(float min, float max) {
        return min + new Random().nextFloat() * (max - min)
    }

    /**
     * 生成一个随机的布尔值。
     * 返回 true 或 false 的随机值。
     * @return 随机的布尔值
     */
    static boolean randomBoolean() {
        return new Random().nextBoolean()
    }

    /**
     * 生成一个随机的时间戳。
     * 返回一个介于当前时间和指定时间范围内的随机时间戳（毫秒）。
     * @param minDate 起始时间，毫秒表示
     * @param maxDate 结束时间，毫秒表示
     * @return 介于 minDate 和 maxDate 之间的随机时间戳
     */
    static long randomTimestamp(long minDate, long maxDate) {
        return minDate + (long) (new Random().nextDouble() * (maxDate - minDate))
    }
}

package com.jmeter.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * com.jmeter.utils.CommonUtils 类提供在 JMeter Groovy 脚本中常用的辅助工具方法，
 * 包括日志记录、临时变量存储和线程休眠。
 * 
 * 常用于：
 * - 打印调试信息到 JMeter 控制台/日志
 * - 存储和读取测试过程中的中间变量（如Token、ID等）
 * - 脚本执行过程中的等待控制
 */
class CommonUtils {

    /**
     * 日志记录器，用于记录日志信息到 JMeter 的日志文件中。
     */
    private static final Logger log = LoggerFactory.getLogger(CommonUtils.class)
    
    /**
     * 用于存储中间变量的静态Map，生命周期为脚本运行期间。
     * 注意：这不是 JMeter 的全局变量或线程变量，仅在 Groovy 类中使用。
     */
    private static final Map<String, String> vars = new HashMap<>()

    /**
     * 打印一条 INFO 级别的日志信息到 JMeter 的日志输出。
     *
     * @param msg 要打印的日志内容，例如 "接口请求成功"
     */
    static void log(String msg) {
        log.info("[JMeter] " + msg)
    }

    /**
     * 存储一个变量到内部变量表中，可用于不同 Sampler 脚本之间共享变量（在同一个线程中）。
     *
     * @param key 变量名，例如 "token"
     * @param val 变量值，例如 "eyJhbGciOiJIUzI1NiIsInR..."
     */
    static void setVar(String key, String val) {
        vars.put(key, val)
    }

    /**
     * 获取先前通过 setVar 存储的变量值。
     *
     * @param key 变量名
     * @return 变量值，如果不存在返回 null
     */
    static String getVar(String key) {
        return vars.get(key)
    }

    /**
     * 让当前线程休眠指定的时间，可用于接口之间模拟等待或节流。
     *
     * @param millis 要休眠的时间（毫秒），例如 1000 表示休眠 1 秒
     * @throws InterruptedException 如果线程在睡眠期间被中断
     */
    static void sleep(int millis) {
        Thread.sleep(millis)
    }

    /**
    * 判断变量是否存在。
    * @param key 变量名
    * @return 如果存在返回 true，否则返回 false
    */
    static boolean hasVar(String key) {
        return vars.containsKey(key)
    }
}


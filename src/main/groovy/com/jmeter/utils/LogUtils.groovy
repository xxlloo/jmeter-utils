package com.jmeter.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * com.jmeter.utils.LogUtils 类提供了用于记录不同级别日志的方法，帮助开发者快速记录应用的日志信息。
 * 目前支持信息日志、警告日志和错误日志。
 * 另外，还提供了记录调试日志和跟踪日志的功能，适用于不同的调试和生产场景。
 */
class LogUtils {
    /**
     * 日志记录器，用于记录日志信息。
     */
    private static final Logger log = LoggerFactory.getLogger(LogUtils.class)

    /**
     * 记录一条信息日志。
     * @param msg 要记录的消息
     */
    static void info(String msg) {
        log.info("[INFO] " + msg)
    }

    /**
     * 记录一条警告日志。
     * @param msg 要记录的消息
     */
    static void warn(String msg) {
        log.warn("[WARN] " + msg)
    }

    /**
     * 记录一条错误日志。
     * @param msg 要记录的消息
     */
    static void error(String msg) {
        log.error("[ERROR] " + msg)
    }

    /**
     * 记录一条调试日志，仅在调试模式下使用。
     * @param msg 要记录的调试信息
     */
    static void debug(String msg) {
        log.debug("[DEBUG] " + msg)
    }

    /**
     * 记录一条跟踪日志，适用于细粒度的调试信息，记录程序执行的详细步骤。
     * @param msg 要记录的跟踪信息
     */
    static void trace(String msg) {
        log.trace("[TRACE] " + msg)
    }

    /**
     * 记录一条带异常堆栈信息的错误日志。
     * @param msg 要记录的消息
     * @param exception 异常对象，用于记录详细堆栈信息
     */
    static void errorWithException(String msg, Throwable exception) {
        log.error("[ERROR] " + msg, exception)
    }

    /**
     * 记录一条带上下文信息的日志。
     * @param msg 要记录的消息
     * @param context 额外的上下文信息（例如：用户ID、请求ID等）
     */
    static void logWithContext(String msg, String context) {
        log.info("[INFO] " + msg + " Context: " + context)
    }

    /**
     * 记录一条日志并设置自定义标签。
     * @param msg 要记录的消息
     * @param tag 自定义标签，便于标识不同类型的日志
     */
    static void logWithTag(String msg, String tag) {
        log.info("[${tag}] " + msg)
    }
}

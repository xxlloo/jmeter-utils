package com.jmeter.utils

/**
 * com.jmeter.utils.RetryUtils 类提供了一个重试机制，用于在失败时自动重试执行某个代码块。
 * 可以通过设置重试次数来保证在出现临时故障时代码块能够重新执行。
 */
class RetryUtils {

    /**
     * 执行指定次数的重试，直到成功为止。
     * 如果执行失败，则会等待一段时间后重试，最多重试指定的次数。
     * @param times 最大重试次数
     * @param block 要执行的代码块，通常是一个闭包（Closure）
     * @return 如果代码块成功执行，则返回代码块的返回值
     * @throws Exception 如果达到最大重试次数后仍然失败，则抛出异常
     */
    static def retry(int times, Closure block) {
        int attempt = 0
        while (attempt < times) {
            try {
                return block()  // 执行传入的代码块
            } catch (e) {
                attempt++
                CommonUtils.log("重试第 ${attempt} 次：${e.message}")  // 记录日志
                Thread.sleep(500)  // 等待500毫秒后重试
            }
        }
        throw new Exception("重试${times}次后依旧失败")  // 达到最大重试次数后抛出异常
    }

    /**
     * 执行指定次数的重试，允许自定义每次重试的等待时间。
     * @param times 最大重试次数
     * @param delay 每次重试之间的等待时间（单位：毫秒）
     * @param block 要执行的代码块，通常是一个闭包（Closure）
     * @return 如果代码块成功执行，则返回代码块的返回值
     * @throws Exception 如果达到最大重试次数后仍然失败，则抛出异常
     */
    static def retryWithDelay(int times, long delay, Closure block) {
        int attempt = 0
        while (attempt < times) {
            try {
                return block()  // 执行传入的代码块
            } catch (e) {
                attempt++
                CommonUtils.log("重试第 ${attempt} 次：${e.message}")  // 记录日志
                Thread.sleep(delay)  // 等待自定义的延迟时间
            }
        }
        throw new Exception("重试${times}次后依旧失败")  // 达到最大重试次数后抛出异常
    }

    /**
     * 执行指定次数的重试，并且每次重试的间隔时间会递增（例如：指数退避）。
     * @param times 最大重试次数
     * @param initialDelay 初始等待时间（单位：毫秒）
     * @param factor 等待时间增长因子
     * @param block 要执行的代码块，通常是一个闭包（Closure）
     * @return 如果代码块成功执行，则返回代码块的返回值
     * @throws Exception 如果达到最大重试次数后仍然失败，则抛出异常
     */
    static def retryWithExponentialBackoff(int times, long initialDelay, double factor, Closure block) {
        int attempt = 0
        long delay = initialDelay
        while (attempt < times) {
            try {
                return block()  // 执行传入的代码块
            } catch (e) {
                attempt++
                CommonUtils.log("重试第 ${attempt} 次：${e.message}")  // 记录日志
                Thread.sleep(delay)  // 按照指数增长的延迟进行重试
                delay = (long)(delay * factor)  // 增加延迟时间
            }
        }
        throw new Exception("重试${times}次后依旧失败")  // 达到最大重试次数后抛出异常
    }

    /**
     * 使用最大重试次数和延迟时间来执行代码块，带有回调机制以处理成功或失败后的操作。
     * @param times 最大重试次数
     * @param block 要执行的代码块，通常是一个闭包（Closure）
     * @param onSuccess 回调函数，当代码块成功执行时调用
     * @param onFailure 回调函数，当代码块失败时调用
     * @return 如果代码块成功执行，则返回代码块的返回值
     * @throws Exception 如果达到最大重试次数后仍然失败，则抛出异常
     */
    static def retryWithCallbacks(int times, Closure block, Closure onSuccess, Closure onFailure) {
        int attempt = 0
        while (attempt < times) {
            try {
                def result = block()  // 执行传入的代码块
                onSuccess(result)  // 执行成功回调
                return result
            } catch (e) {
                attempt++
                CommonUtils.log("重试第 ${attempt} 次：${e.message}")  // 记录日志
                onFailure(e)  // 执行失败回调
                Thread.sleep(500)  // 等待500毫秒后重试
            }
        }
        throw new Exception("重试${times}次后依旧失败")  // 达到最大重试次数后抛出异常
    }
}

package com.jmeter.utils

class PerformanceMonitor {

    // 记录请求开始时间
    static long startTime() {
        return System.currentTimeMillis()  // 获取当前时间戳
    }

    // 记录请求结束时间
    static long endTime() {
        return System.currentTimeMillis()  // 获取当前时间戳
    }

    // 打印吞吐量（单位：请求数/秒）
    static void printThroughput(long startTime, long endTime, int requestCount) {
        long duration = endTime - startTime  // 计算总时长
        long throughput = (requestCount * 1000) / duration  // 计算吞吐量
        println "Throughput: $throughput requests/sec"  // 输出吞吐量
    }
}

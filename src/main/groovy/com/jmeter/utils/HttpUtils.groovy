package com.jmeter.utils

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.entity.StringEntity
import org.apache.http.client.methods.HttpPost

/**
 * com.jmeter.utils.HttpUtils 类提供了 HTTP 请求相关的工具方法，
 * 包括解析 HTTP 响应头、发送 POST 请求和 GET 请求的功能。
 */
class HttpUtils {

    /**
     * 解析原始的 HTTP 头信息。
     * 将原始的头部字符串转换为一个 Map，其中每个键值对对应一个 header。
     * @param rawHeaders 原始的 HTTP 头信息字符串（格式为 key: value 的形式，每行一个 header）
     * @return 返回一个包含所有 header 键值对的 Map
     */
    static Map parseHeaders(String rawHeaders) {
        // 按行拆分原始头部字符串
        def lines = rawHeaders.split("\\r?\\n")
        def result = [:]
        
        // 遍历每一行，拆分为 header 的 key 和 value
        lines.each {
            def parts = it.split(": ", 2)
            // 如果该行包含有效的 header（key 和 value），则将其加入 Map
            if (parts.length == 2) {
                result[parts[0]] = parts[1]
            }
        }
        return result
    }

    /**
     * 发送一个 HTTP POST 请求，传递 JSON 数据。
     * @param url 请求的目标 URL
     * @param json 需要发送的 JSON 数据
     * @param headers 可选的 HTTP 请求头，默认为空 Map
     * @return 返回服务器响应的内容（作为字符串）
     */
    static String postJson(String url, String json, Map<String, String> headers = [:]) {
        // 创建默认的 HTTP 客户端
        def client = HttpClients.createDefault()
        
        // 创建一个 HTTP POST 请求
        def post = new HttpPost(url)
        
        // 设置请求体（JSON 数据），并指定字符编码为 UTF-8
        post.setEntity(new StringEntity(json, "UTF-8"))
        
        // 设置默认的 Content-Type 头为 application/json
        post.setHeader("Content-Type", "application/json")
        
        // 设置额外的自定义请求头
        headers.each { k, v -> post.setHeader(k, v) }

        // 执行请求并获取响应
        def resp = client.execute(post)
        
        // 读取响应内容并返回
        return resp.getEntity().getContent().getText("UTF-8")
    }

    /**
     * 发送一个 HTTP GET 请求。
     * @param url 请求的目标 URL
     * @param headers 可选的 HTTP 请求头，默认为空 Map
     * @return 返回服务器响应的内容（作为字符串）
     */
    static String get(String url, Map<String, String> headers = [:]) {
        // 创建默认的 HTTP 客户端
        def client = HttpClients.createDefault()
        
        // 创建一个 HTTP GET 请求
        def get = new HttpGet(url)
        
        // 设置默认的请求头
        headers.each { k, v -> get.setHeader(k, v) }
        
        // 执行请求并获取响应
        def resp = client.execute(get)
        
        // 读取响应内容并返回
        return resp.getEntity().getContent().getText("UTF-8")
    }
}

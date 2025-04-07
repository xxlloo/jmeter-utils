package com.jmeter.utils

import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

/**
 * com.jmeter.utils.JsonUtils 类提供了一些常用的 JSON 处理方法，
 * 包括解析 JSON 字符串、格式化 JSON 输出、获取指定路径的值、合并 JSON 对象等。
 */
class JsonUtils {

    /**
     * 将 JSON 字符串解析为 Map 对象。
     * @param jsonStr 要解析的 JSON 字符串
     * @return 返回解析后的 Map 对象
     */
    static Map parse(String jsonStr) {
        return new JsonSlurper().parseText(jsonStr)
    }

    /**
     * 将对象转换为格式化的 JSON 字符串。
     * @param data 要转换的对象
     * @return 返回格式化的 JSON 字符串
     */
    static String stringify(Object data) {
        return new JsonBuilder(data).toPrettyString()
    }

    /**
     * 根据路径从 JSON 字符串中获取值。
     * 支持类似 "key1.key2.key3" 的路径。
     * @param jsonStr 要查询的 JSON 字符串
     * @param path JSON 路径，支持多级路径（如："user.address.city"）
     * @return 返回路径对应的值
     */
    static Object getValue(String jsonStr, String path) {
        def json = parse(jsonStr)
        path.split("\\.").each { key ->
            json = json[key]
        }
        return json
    }

    /**
     * 检查 JSON 字符串中是否包含指定路径的值。
     * @param jsonStr 要查询的 JSON 字符串
     * @param path JSON 路径，支持多级路径
     * @return 如果路径存在，则返回 true；否则返回 false
     */
    static boolean contains(String jsonStr, String path) {
        try {
            def value = getValue(jsonStr, path)
            return value != null
        } catch (Exception e) {
            return false
        }
    }

    /**
     * 合并两个 JSON 对象。
     * 将第二个 JSON 对象的内容合并到第一个 JSON 对象中。
     * @param json1 第一个 JSON 对象
     * @param json2 第二个 JSON 对象
     * @return 返回合并后的 JSON 对象
     */
    static Map merge(Map json1, Map json2) {
        json1.putAll(json2)
        return json1
    }

    /**
     * 删除 JSON 中的某个路径。
     * 例如，删除 "user.address.city" 这个路径的值。
     * @param json 要删除路径的 JSON 对象
     * @param path 要删除的路径
     * @return 返回删除指定路径后的 JSON 对象
     */
    static Map removePath(Map json, String path) {
        def keys = path.split("\\.")
        def lastKey = keys.pop()
        def parent = keys.inject(json) { acc, key -> acc[key] }
        parent.remove(lastKey)
        return json
    }

    /**
     * 更新 JSON 中某个路径的值。
     * 例如，更新 "user.address.city" 路径的值为 "New York"。
     * @param json 要更新的 JSON 对象
     * @param path 要更新的路径
     * @param newValue 新值
     * @return 返回更新后的 JSON 对象
     */
    static Map updateValue(Map json, String path, Object newValue) {
        def keys = path.split("\\.")
        def lastKey = keys.pop()
        def parent = keys.inject(json) { acc, key -> acc[key] }
        parent[lastKey] = newValue
        return json
    }

    /**
     * 将 JSON 字符串转换为对应的对象类型。
     * 可以将 JSON 字符串转换为具体的 Groovy 对象（例如：List、Map、对象等）。
     * @param jsonStr 要转换的 JSON 字符串
     * @param type 要转换成的目标类型（如：Map、List）
     * @return 返回转换后的对象
     */
    static Object fromJson(String jsonStr, Class type) {
        def slurper = new JsonSlurper()
        return slurper.parseText(jsonStr, type)
    }
}

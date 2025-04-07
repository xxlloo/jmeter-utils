package com.jmeter.utils
/**
 * 断言工具类 - 用于在 JMeter Groovy 脚本中进行常见的断言判断
 * 使用示例：
 *   com.jmeter.utils.AssertUtils.assertContains(response, "成功")
 *   com.jmeter.utils.AssertUtils.assertEquals(200, statusCode)
 *   com.jmeter.utils.AssertUtils.assertNotNull(jsonData, "返回数据为空")
 */
class AssertUtils {

    /**
     * 断言响应体中包含指定关键字
     * @param body 响应字符串（如 responseDataAsString）
     * @param keyword 要匹配的关键字
     */
    static void assertContains(String body, String keyword) {
        assert body.contains(keyword): "响应中未包含关键字: ${keyword}"
    }

    /**
     * 断言两个对象相等
     * @param expected 预期值
     * @param actual 实际值
     */
    static void assertEquals(Object expected, Object actual) {
        assert expected == actual: "断言失败: 预期=${expected}, 实际=${actual}"
    }

    /**
     * 断言对象不为 null
     * @param obj 被断言对象
     * @param msg 异常提示信息（可选）
     */
    static void assertNotNull(Object obj, String msg = "对象不能为空") {
        assert obj != null: msg
    }

    // 断言响应状态码是否与预期一致
    static void assertStatusCode(int expectedStatus, int actualStatus) {
        if (expectedStatus != actualStatus) {
            throw new AssertionError("Expected status: $expectedStatus, but got: $actualStatus")  // 如果不一致，抛出断言错误
        }
    }

    // 断言响应中是否包含指定的字段
    static void assertResponseContains(String responseBody, String expectedField) {
        if (!responseBody.contains(expectedField)) {
            throw new AssertionError("Response does not contain expected field: $expectedField")  // 如果不包含，抛出断言错误
        }
    }

    // 断言响应时间是否小于最大时间
    static void assertResponseTime(long expectedMaxTime, long actualTime) {
        if (actualTime > expectedMaxTime) {
            throw new AssertionError("Response time $actualTime exceeds expected max time of $expectedMaxTime ms")
            // 如果超过最大时间，抛出断言错误
        }
    }


}
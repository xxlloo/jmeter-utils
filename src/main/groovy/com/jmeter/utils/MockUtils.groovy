package com.jmeter.utils
/**
 * com.jmeter.utils.MockUtils 类提供了一些用于生成常见的假数据的方法，例如生成随机身份证号码。
 * 这些方法可以用于单元测试、性能测试等场景中生成假数据。
 */
class MockUtils {

    /**
     * 生成一个随机的身份证号码（18位）。
     * 生成的身份证号符合中国身份证号码的基本规则。
     * @return 一个随机生成的合法身份证号码
     */
    static String randomIdCard() {
        // 基础部分，前17位从 "11010119900101" 开始，随机生成3位
        def base = "11010119900101" + RandomUtils.randomInt(100, 999).toString()
        // 计算并附加校验位
        return base + calculateCheckDigit(base)
    }

    /**
     * 根据身份证前17位数字计算校验码（第18位）。
     * 计算规则是：通过加权和对11取余，得到对应的校验码。
     * @param id17 前17位身份证号码
     * @return 计算得到的校验码（字符）
     */
    static String calculateCheckDigit(String id17) {
        // 各位的权重值
        def weights = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]
        // 对应的校验码表
        def checkCode = ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"]
        // 计算加权和
        def sum = id17.collect { it as int }.withIndex().sum { val, idx -> val * weights[idx] }
        // 返回对应校验码
        return checkCode[sum % 11]
    }

    /**
     * 生成一个随机手机号（11位）。
     * 生成的手机号符合中国的手机号规则：以 "1" 开头，第二位是3,4,5,6,7,8,9 中的一个。
     * @return 一个随机生成的手机号
     */
    static String randomPhoneNumber() {
        def prefix = "1" // 手机号以 1 开头
        def secondDigit = RandomUtils.randomInt(3, 9) // 第二位是 3-9 的随机数
        def remaining = (1..9).collect { RandomUtils.randomInt(0, 9) }.join('') // 后9位随机生成
        return prefix + secondDigit + remaining
    }

    /**
     * 生成一个随机的姓名（包含中文字符）。
     * 姓名由一个随机的中文姓和名字组成。
     * @return 随机生成的姓名
     */
    static String randomName() {
        def surnames = ["赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈"]
        def name = ["俊", "涛", "明", "洋", "伟", "超", "鹏", "亮", "强", "磊"]
        def surname = surnames[RandomUtils.randomInt(0, surnames.size() - 1)]
        def givenName = name[RandomUtils.randomInt(0, name.size() - 1)]
        return surname + givenName
    }

    /**
     * 生成一个随机的日期，格式为 yyyy-MM-dd
     * @return 随机生成的日期字符串
     */
    static String randomDate() {
        def year = RandomUtils.randomInt(1950, 2025)
        def month = RandomUtils.randomInt(1, 12)
        def day = RandomUtils.randomInt(1, 28) // 保证日期不溢出
        return String.format("%04d-%02d-%02d", year, month, day)
    }

    /**
     * 生成一个随机的邮箱地址。
     * 邮箱地址由一个随机的字母组成，后跟 "@example.com"。
     * @return 随机生成的邮箱地址
     */
    static String randomEmail() {
        def randomChars = (1..10).collect { ('a'..'z')[(RandomUtils.randomInt(0, 25))] }.join('')
        return randomChars + "@example.com"
    }

    /**
     * 生成一个随机的地址（简单模拟），包括省、市和区。
     * 地址由一个省、市和区组成，提供一些常见的选项。
     * @return 随机生成的地址
     */
    static String randomAddress() {
        def provinces = ["北京", "上海", "广东", "江苏", "浙江"]
        def cities = ["北京", "上海", "广州", "南京", "杭州"]
        def districts = ["朝阳区", "浦东新区", "天河区", "玄武区", "西湖区"]
        def province = provinces[RandomUtils.randomInt(0, provinces.size() - 1)]
        def city = cities[RandomUtils.randomInt(0, cities.size() - 1)]
        def district = districts[RandomUtils.randomInt(0, districts.size() - 1)]
        return province + city + district
    }
}

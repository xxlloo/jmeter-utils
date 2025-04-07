package com.jmeter.utils

/**
 * com.jmeter.utils.FileUtils 类用于处理文件操作。
 * 提供写入文件和读取文件的功能。
 */
class FileUtils {

    /**
     * 向指定路径的文件写入内容。
     * @param path 文件路径
     * @param content 要写入的内容
     * @param append 是否以追加模式写入，默认值为 true（即追加内容到文件末尾）
     */
    static void write(String path, String content, boolean append = true) {
        // 创建一个 File 对象，调用 withWriter 方法来写入内容
        // 使用指定的字符编码（UTF-8）来写入，如果 append 为 true，则为追加模式，否则为覆盖模式
        new File(path).withWriter(append ? "UTF-8" : false) { it.writeLine(content) }
    }

    /**
     * 读取文件的所有行，并返回一个列表。
     * @param path 文件路径
     * @return 返回一个包含文件每一行内容的列表
     */
    static List<String> readLines(String path) {
        // 使用 readLines 方法读取文件中的每一行，并返回列表
        return new File(path).readLines()
    }
}

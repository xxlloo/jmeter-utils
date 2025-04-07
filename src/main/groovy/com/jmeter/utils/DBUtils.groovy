package com.jmeter.utils

import groovy.sql.Sql

/**
 * com.jmeter.utils.DBUtils 数据库工具类
 *
 * 提供在 JMeter Groovy 脚本中访问数据库的便捷方法，
 * 包括连接、查询、更新和预编译 SQL 操作（防止 SQL 注入）。
 */
class DBUtils {

    /**
     * 创建数据库连接
     *
     * @param jdbcUrl JDBC 连接字符串，例如：jdbc:mysql://localhost:3306/test
     * @param user 数据库用户名
     * @param pass 数据库密码
     * @param driver JDBC 驱动类（可选，默认是 MySQL 8.x）
     * @return Sql 连接对象
     */
    static Sql connect(String jdbcUrl, String user, String pass, String driver = "com.mysql.cj.jdbc.Driver") {
        return Sql.newInstance(jdbcUrl, user, pass, driver)
    }

    /**
     * 普通查询（无参数），返回结果集
     *
     * @param sqlText 查询语句
     * @param conn Sql 连接
     * @return List<Map> 结果集
     */
    static List<Map> query(String sqlText, Sql conn) {
        return conn.rows(sqlText)
    }

    /**
     * 带参数查询，使用预编译防止 SQL 注入
     *
     * @param sqlText 查询语句，使用 ? 占位符，例如：SELECT * FROM user WHERE id = ?
     * @param params 参数列表，例如：[1]
     * @param conn Sql 连接
     * @return List<Map> 结果集
     */
    static List<Map> queryWithParams(String sqlText, List params, Sql conn) {
        return conn.rows(sqlText, params)
    }

    /**
     * 查询一条记录
     *
     * @param sqlText SQL 查询语句
     * @param conn Sql 连接
     * @return Map 首行数据或 null
     */
    static Map queryOne(String sqlText, Sql conn) {
        def rows = conn.rows(sqlText)
        return rows.isEmpty() ? null : rows[0]
    }

    /**
     * 查询一条记录（带参数）
     *
     * @param sqlText 查询语句，使用 ? 占位符
     * @param params 参数列表
     * @param conn Sql 连接
     * @return Map 第一行结果或 null
     */
    static Map queryOneWithParams(String sqlText, List params, Sql conn) {
        def rows = conn.rows(sqlText, params)
        return rows.isEmpty() ? null : rows[0]
    }

    /**
     * 查询单个列值
     *
     * @param sqlText 查询语句
     * @param column 列名
     * @param conn Sql 连接
     * @return Object 列的值或 null
     */
    static Object queryColumn(String sqlText, String column, Sql conn) {
        def row = queryOne(sqlText, conn)
        return row != null ? row[column] : null
    }

    /**
     * 查询单个列值（带参数）
     *
     * @param sqlText 查询语句
     * @param column 列名
     * @param params 参数列表
     * @param conn Sql 连接
     * @return Object 列的值或 null
     */
    static Object queryColumnWithParams(String sqlText, String column, List params, Sql conn) {
        def row = queryOneWithParams(sqlText, params, conn)
        return row != null ? row[column] : null
    }

    /**
     * 执行更新/插入/删除（无参数）
     *
     * @param sqlText SQL 语句
     * @param conn Sql 连接
     * @return int 受影响的行数
     */
    static int executeUpdate(String sqlText, Sql conn) {
        return conn.executeUpdate(sqlText)
    }

    /**
     * 执行更新/插入/删除（带参数）
     *
     * @param sqlText SQL 语句，使用 ? 占位符
     * @param params 参数列表
     * @param conn Sql 连接
     * @return int 受影响的行数
     */
    static int executeUpdateWithParams(String sqlText, List params, Sql conn) {
        return conn.executeUpdate(sqlText, params)
    }

    /**
     * 关闭连接，释放资源
     *
     * @param conn Sql 连接对象
     */
    static void close(Sql conn) {
        if (conn != null) {
            conn.close()
        }
    }
}

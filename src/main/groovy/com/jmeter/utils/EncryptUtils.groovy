package com.jmeter.utils

import java.security.*
import javax.crypto.*
import javax.crypto.spec.*

/**
 * com.jmeter.utils.EncryptUtils 加密工具类
 *
 * 提供常见的加密方法，包括：
 * - MD5、SHA256 摘要算法
 * - HMAC-SHA256 签名算法
 * - Base64 编码与解码
 * - URL 编码与解码
 * - AES 加解密（支持 CBC 和 GCM 模式）
 */
class EncryptUtils {

    /**
     * 计算输入字符串的 MD5 摘要值
     * @param input 输入字符串
     * @return MD5 摘要值（32位小写十六进制字符串）
     */
    static String md5(String input) {
        def md = MessageDigest.getInstance("MD5")
        md.update(input.bytes)
        return md.digest().encodeHex().toString()
    }

    /**
     * 计算输入字符串的 SHA256 摘要值
     * @param input 输入字符串
     * @return SHA256 摘要值（64位小写十六进制字符串）
     */
    static String sha256(String input) {
        def sha = MessageDigest.getInstance("SHA-256")
        sha.update(input.bytes)
        return sha.digest().encodeHex().toString()
    }

    /**
     * 使用 HMAC-SHA256 算法对数据进行签名
     * @param data 待签名的数据
     * @param key 用于签名的密钥
     * @return 签名后的字符串（Base64 编码）
     */
    static String hmacSha256(String data, String key) {
        def secretKey = new SecretKeySpec(key.bytes, "HmacSHA256")
        def mac = Mac.getInstance("HmacSHA256")
        mac.init(secretKey)
        def result = mac.doFinal(data.bytes)
        return Base64.encoder.encodeToString(result)
    }

    /**
     * 对输入字符串进行 Base64 编码
     * @param input 输入字符串
     * @return Base64 编码后的字符串
     */
    static String base64Encode(String input) {
        return Base64.encoder.encodeToString(input.bytes)
    }

    /**
     * 对 Base64 编码的字符串进行解码
     * @param input Base64 编码的字符串
     * @return 解码后的字符串
     */
    static String base64Decode(String input) {
        return new String(Base64.decoder.decode(input))
    }

    /**
     * 对输入字符串进行 URL 编码（使用 UTF-8 编码）
     * @param input 输入字符串
     * @return URL 编码后的字符串
     */
    static String urlEncode(String input) {
        return URLEncoder.encode(input, "UTF-8")
    }

    /**
     * 对 URL 编码的字符串进行解码（使用 UTF-8 编码）
     * @param input URL 编码的字符串
     * @return 解码后的字符串
     */
    static String urlDecode(String input) {
        return URLDecoder.decode(input, "UTF-8")
    }

    // ---------- AES CBC 模式 ----------

    /**
     * 使用 AES-CBC 模式对数据进行加密
     * @param plainText 明文数据
     * @param key 密钥（16字节）
     * @param iv 初始向量（16字节）
     * @return 加密后的 Base64 编码字符串
     */
    static String aesEncrypt(String plainText, String key, String iv) {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        // 对密钥进行自动补齐（保证长度为16字节）
        SecretKeySpec keySpec = new SecretKeySpec(padKey(key, 16), "AES")
        // 对 IV 进行自动补齐（保证长度为16字节）
        IvParameterSpec ivSpec = new IvParameterSpec(padKey(iv, 16))
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        byte[] encrypted = cipher.doFinal(plainText.bytes)
        return Base64.encoder.encodeToString(encrypted)
    }

    /**
     * 使用 AES-CBC 模式对密文进行解密
     * @param cipherText Base64 编码的密文数据
     * @param key 密钥（16字节）
     * @param iv 初始向量（16字节）
     * @return 解密后的明文字符串
     */
    static String aesDecrypt(String cipherText, String key, String iv) {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        // 对密钥进行自动补齐（保证长度为16字节）
        SecretKeySpec keySpec = new SecretKeySpec(padKey(key, 16), "AES")
        // 对 IV 进行自动补齐（保证长度为16字节）
        IvParameterSpec ivSpec = new IvParameterSpec(padKey(iv, 16))
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        byte[] decrypted = cipher.doFinal(Base64.decoder.decode(cipherText))
        return new String(decrypted)
    }

    // ---------- AES GCM 模式 ----------

    /**
     * 使用 AES-GCM 模式对数据进行加密
     * @param plainText 明文数据
     * @param key 密钥（16字节）
     * @param iv 初始向量（12字节，推荐长度）
     * @return 加密后的 Base64 编码字符串
     */
    static String aesGcmEncrypt(String plainText, String key, String iv) {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding")
        // 对密钥进行自动补齐（保证长度为16字节）
        SecretKeySpec keySpec = new SecretKeySpec(padKey(key, 16), "AES")
        // GCM 模式使用 12 字节的 IV
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, padKey(iv, 12)) // 128 bits auth tag
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec)
        byte[] cipherText = cipher.doFinal(plainText.bytes)
        return Base64.encoder.encodeToString(cipherText)
    }

    /**
     * 使用 AES-GCM 模式对密文进行解密
     * @param cipherText Base64 编码的密文数据
     * @param key 密钥（16字节）
     * @param iv 初始向量（12字节，推荐长度）
     * @return 解密后的明文字符串
     */
    static String aesGcmDecrypt(String cipherText, String key, String iv) {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding")
        // 对密钥进行自动补齐（保证长度为16字节）
        SecretKeySpec keySpec = new SecretKeySpec(padKey(key, 16), "AES")
        // GCM 模式使用 12 字节的 IV
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, padKey(iv, 12))
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec)
        byte[] decrypted = cipher.doFinal(Base64.decoder.decode(cipherText))
        return new String(decrypted)
    }

    /**
     * 自动补齐密钥或 IV 到指定长度，不足时用 0 补齐，超过时截断
     * @param key 密钥或 IV
     * @param length 目标长度（通常 16 字节）
     * @return 补齐后的字节数组
     */
    private static byte[] padKey(String key, int length) {
        byte[] keyBytes = key.bytes
        if (keyBytes.length == length) return keyBytes
        byte[] padded = new byte[length]
        System.arraycopy(keyBytes, 0, padded, 0, Math.min(keyBytes.length, length))
        return padded
    }
}

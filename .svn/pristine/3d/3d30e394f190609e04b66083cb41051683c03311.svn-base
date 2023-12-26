package com.example.mytest2023.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 加密辅助类
 *
 * @author KingYoung
 */
public class EncryptHelper {

    /**
     * MD5加密
     *
     * @param strOrg 要加密的字符串
     * @return 加密后的字符串
     */
    public static String md5Encrypt(String strOrg) {
        StringBuffer buf = new StringBuffer("");
        StringBuffer result = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(strOrg.getBytes());
            byte b[] = md.digest();

            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        for (int j = 0; j < 16; j++) {
            result.append(buf.substring(j * 2, j * 2 + 2) + "-");
        }

        return result.substring(0, result.length() - 1);
    }

    /**
     * Des加密(可兼容C#，objective-c)
     *
     * @param encryptString 要加密的字符串
     * @param encryptKey 密钥
     * @return
     * @throws Exception
     */
    public static String desEncrypt(String encryptString, String encryptKey) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(encryptKey.getBytes());
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return Base64.base64Encode(encryptedData);
    }

    /**
     * Des解密(可兼容C#，objective-c)
     *
     * @param decryptString 要解密的字符串
     * @param decryptKey 密钥
     * @return
     * @throws Exception
     */
    public static String desDecrypt(String decryptString, String decryptKey) throws Exception {
        byte[] byteMi = Base64.base64Decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(decryptKey.getBytes());
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData);
    }

    /**
     * base64加密
     *
     * @param str 要加密的字符串
     * @return
     */
    public static String encodeBase64(String str) {
        byte[] data = str.getBytes();
        return Base64.base64Encode(data);
    }

    /**
     * base64解码
     *
     * @param str 要解码的字符串
     * @return
     */
    public static String decodeBase64(String str) {
        byte[] data = Base64.base64Decode(str);
        return new String(data);
    }

    /**
     * base64解码
     *
     * @param str 要解码的字符串
     */
    public static String decodeBase64(String str, String encode) {
        byte[] data = Base64.base64Decode(str);
        try {
            return new String(data, encode);
        } catch (UnsupportedEncodingException ex) {
            return new String(data);
        }
    }
}

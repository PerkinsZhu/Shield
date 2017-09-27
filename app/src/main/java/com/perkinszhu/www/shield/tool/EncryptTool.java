package com.perkinszhu.www.shield.tool;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-25
 * Time: 17:51
 */
public class EncryptTool {
    private static String TAG = EncryptTool.class.getName();
    private static String key;
    private static String ivParameter = "9ae3814cd72b650f";

    public static void setKey(String tempKey) {
        if (tempKey.length() < 16) {
            key = formateKey(tempKey);
        }
        if (tempKey.length() > 16) {
            key = tempKey.substring(0, 16);
        }
        if (tempKey.length() == 16) {
            key = tempKey;
        }
    }

    private static String formateKey(String key) {
        key = key + key;
        if (key.length() < 16) {
            return formateKey(key);
        }
        return key.substring(0, 16);
    }

    // 加密
    public static String encrypt(String sSrc) {
        if (key == null) {
            throw new RuntimeException("秘钥缺失！");
        }
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            result = new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    // 解密
    public static String decrypt(String sSrc) {
        if (key == null) {
            throw new RuntimeException("秘钥缺失！");
        }
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

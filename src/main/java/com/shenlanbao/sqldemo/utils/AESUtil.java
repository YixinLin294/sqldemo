package com.shenlanbao.sqldemo.utils;

import org.apache.ibatis.annotations.Param;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESUtil {

    private static final String ASE_KEY = "ae5944123b7101f01a52c8c4d7252eda";

    public static String Encrypt(String sSrc) throws Exception {
        return Encrypt(sSrc, ASE_KEY);
    }

    public static String Decrypt(String sSrc) throws Exception {
        return Decrypt(sSrc, ASE_KEY);
    }

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null){
            System.out.println("Key为空");
            return null;
        }
        SecretKeySpec sKeySpec = new SecretKeySpec(sKey.getBytes(), "AES");

        Cipher cipher;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return Base64.getEncoder().encodeToString(encrypted);
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.println("Key为空");
                return null;
            }

            SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes(), "AES");

            Cipher cipher;
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}

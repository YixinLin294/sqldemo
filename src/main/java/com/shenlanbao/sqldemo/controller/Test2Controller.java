package com.shenlanbao.sqldemo.controller;

import com.shenlanbao.sqldemo.utils.AESUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test2Controller {

    @PostMapping("/aes_encrypt")
    public String aesEncrypt(@RequestBody String original) {
        try {
            String encrypt = AESUtil.Encrypt(original);
            return encrypt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/aes_decrypt")
    public String aesDecrypt(@RequestBody String encrypt) {
        try {
            String original = AESUtil.Decrypt(encrypt);
            return original;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.shenlanbao.sqldemo.controller;

import com.shenlanbao.sqldemo.mq.ProviderService;
import com.shenlanbao.sqldemo.service.TestService;
import com.shenlanbao.sqldemo.utils.AESUtil;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class Test2Controller {

    private TestService testService;

    @GetMapping("/test_async")
    public void testAsync() {
        testService.testAsync();
        System.out.println("afterAsync");
    }

    @GetMapping("/rabbitmq")
    public void rabbitmqTest(@RequestParam String msg) {
        ProviderService providerService = new ProviderService();
        providerService.sendMsgForHelloWorldQueue(msg);
    }
}

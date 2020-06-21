package com.lp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther lp
 * @date 2020/6/21 0021 12:48
 */
@RestController
public class ServiceController {
    @LpReference
    private IOrderService iOrderService;

    @LpReference
    private TestService testService;

    @GetMapping("/ser")
    public String ser(){
        return iOrderService.queryOrderList();
    }

    @GetMapping("/test")
    public String test(){
        return testService.say();
    }
}

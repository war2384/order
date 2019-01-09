package com.imooc.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by war on 2019/1/7.
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

//    //设置超时时间为3秒
//    @HystrixCommand(commandProperties = {
//        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled" , value = "true"),//设置熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" , value = "10"),//请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" , value = "10000"),//休眠时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" , value = "60"),//错误率
    })
//    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8090/product/listForOrder", Arrays.asList("157875196366160022"),String.class);
    }

    public String fallback(){
        return "太拥挤了，请稍后再试试！";
    }

    public String defaultFallback(){
        return "默认提示：太拥挤了，请稍后再试试！";
    }

}

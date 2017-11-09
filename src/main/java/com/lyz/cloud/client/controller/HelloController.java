package com.lyz.cloud.client.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HelloController {
	 @RequestMapping("/hello/{fallback}")
	    @HystrixCommand(fallbackMethod="helloFallbackMethod")/*调用方式失败后调用helloFallbackMethod*/
	    public String hello(@PathVariable("fallback") String fallback){
	        if("1".equals(fallback)){
	            throw new RuntimeException("...");
	        }
	        return "hello";
	    }

	    public String helloFallbackMethod(String fallback){
	        return "fallback 参数值为:"+fallback;
	    }
}

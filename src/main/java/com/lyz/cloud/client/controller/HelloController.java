package com.lyz.cloud.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HelloController {
	
	 private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	 @RequestMapping("/hello/{fallback}")
	    @HystrixCommand(fallbackMethod="helloFallbackMethod")/*调用方式失败后调用helloFallbackMethod*/
	    public String hello(@PathVariable("fallback") String fallback){
	        if("1".equals(fallback)){
	            throw new RuntimeException("...");
	        }
	        logger.info("日志2：服务提供者查找服务");
	        return "hello";
	    }

	    public String helloFallbackMethod(String fallback){
	    	logger.info("日志3：服务提响应请求");
	        return "fallback 参数值为:"+fallback;
	    }
}

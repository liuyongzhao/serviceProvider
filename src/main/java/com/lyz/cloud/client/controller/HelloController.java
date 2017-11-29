package com.lyz.cloud.client.controller;

import org.apache.log4j.Logger;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HelloController {
	private static Logger logger = Logger.getLogger(HelloController.class);
	@Autowired
    private DiscoveryClient provider;
	@RequestMapping("/hello/{fallback}")
    @HystrixCommand(fallbackMethod="helloFallbackMethod")/*调用方式失败后调用helloFallbackMethod*/
    public String hello(@PathVariable("fallback") String fallback){
        if("1".equals(fallback)){
            throw new RuntimeException("...");
        }
        ServiceInstance instance = provider.getLocalServiceInstance();
        logger.info(" 日志2************"+"/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + "hello"  );
        return "hello";
    }

    public String helloFallbackMethod(String fallback){
    	logger.info("日志3**************fallback为:"+fallback);
        return "fallback 参数值为:"+fallback;
    }
}

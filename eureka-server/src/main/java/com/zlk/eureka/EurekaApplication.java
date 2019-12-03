package com.zlk.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.logging.Logger;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    private static Logger logger = Logger.getLogger(EurekaApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
        logger.info("Eureka启动成功");
    }

}

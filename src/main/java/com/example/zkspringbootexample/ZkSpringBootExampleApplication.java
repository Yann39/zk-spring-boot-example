package com.example.zkspringbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yann39
 * @since nov 2018
 */
@SpringBootApplication
@Controller
@ImportResource("classpath:metainfo/zk/zkscopes-config.xml")
public class ZkSpringBootExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkSpringBootExampleApplication.class, args);
    }

    @GetMapping("/admin")
    public String admin() {
        return "login-admin";
    }
}

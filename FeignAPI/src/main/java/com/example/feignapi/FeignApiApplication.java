package com.example.feignapi;

import com.example.feignapi.client.UserClient;
import com.example.feignapi.dto.UserResponse;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableFeignClients
public class FeignApiApplication {

    @Autowired
    UserClient client;

    @GetMapping("/findaAllUsers")
    public List<UserResponse> getUsers() {
        return client.getUsers();
    }

    public static void main(String[] args) {
        SpringApplication.run(FeignApiApplication.class, args);
    }

}

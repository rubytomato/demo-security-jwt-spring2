package com.example.demo.controller;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "user")
@Slf4j
public class UserController {

    @GetMapping
    public String greeting(@AuthenticationPrincipal(expression = "user") User user) {
        log.info("access user : {}", user.toString());
        return "hello " + user.getName();
    }

    @GetMapping(path = "echo/{message}")
    public String getEcho(@PathVariable(name = "message") String message) {
        return message.toUpperCase();
    }

    @PostMapping(path = "echo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String postEcho(@RequestBody Map<String, String> message) {
        return message.toString();
    }

}

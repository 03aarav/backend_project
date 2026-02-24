package com.example.demo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Test {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public String sayhello(){
        return "hello";
    }
}

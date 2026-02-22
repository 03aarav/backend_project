package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired UserRepository repo;
    @Autowired PasswordEncoder encoder;
    @Autowired JwtUtil jwt;

    @PostMapping("/register")
    public String register(@RequestBody User user){

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        repo.save(user);
        return "Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User request){

        User user = repo.findByUserName(request.getUserName()).orElseThrow();

        if(encoder.matches(request.getPassword(), user.getPassword())){
            return jwt.generateToken(user.getUserName());
        }

        throw new RuntimeException("Invalid Credentials");
    }
}


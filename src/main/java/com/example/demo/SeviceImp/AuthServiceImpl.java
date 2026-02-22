//package com.example.demo.SeviceImp;
//
//import com.example.demo.DTO.AuthRequest;
//import com.example.demo.DTO.AuthResponse;
//import com.example.demo.DTO.RegisterRequest;
//import com.example.demo.Model.User;
//import com.example.demo.Repository.UserRepository;
//import com.example.demo.Security.JWTUtil;
//import com.example.demo.Service.AuthService;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JWTUtil jwtUtil;
//
//    @Override
//    public AuthResponse register(RegisterRequest request) {
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new RuntimeException("Email already registered.");
//        }
//
//        if (!request.getPassword().equals(request.getConfirmPassword())) {
//            throw new RuntimeException("Passwords do not match.");
//        }
//
//        User user = User.builder()
//                .email(request.getEmail())
//                .userPassword(passwordEncoder.encode(request.getPassword()))
//                .build();
//
//        userRepository.save(user);
//
//        String token = jwtUtil.generateToken(user.getEmail());
//        return new AuthResponse("Registration successful", token);
//    }
//
//    @Override
//    public AuthResponse login(AuthRequest request) {
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found."));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getUserPassword())) {
//            throw new RuntimeException("Invalid credentials.");
//        }
//
//        String token = jwtUtil.generateToken(user.getEmail());
//        return new AuthResponse("Login successful", token);
//    }
//}

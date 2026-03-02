package com.example.demo.SeviceImp;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getRole() == null){
            user.setRole("ROLE_USER");
        }

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String id, User updatedUser) {

        return userRepository.findById(id)
                .map(existingUser -> {

                    existingUser.setUserName(updatedUser.getUserName());
                    existingUser.setEmail(updatedUser.getEmail());

                    if(updatedUser.getPassword() != null){
                        existingUser.setPassword(
                                passwordEncoder.encode(updatedUser.getPassword())
                        );
                    }

                    existingUser.setPhoneNo(updatedUser.getPhoneNo());
                    existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
                    existingUser.setRole(updatedUser.getRole());
                    existingUser.setLanguage(updatedUser.getLanguage());
                    existingUser.setNationality(updatedUser.getNationality());
                    existingUser.setInterests(updatedUser.getInterests());
                    existingUser.setAddress(updatedUser.getAddress());

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByPhoneNo(String phoneNo) {
        return userRepository.findByPhoneNo(phoneNo);
    }
}

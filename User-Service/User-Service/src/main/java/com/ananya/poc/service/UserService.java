package com.ananya.poc.service;
import com.ananya.poc.User.Users;
import com.ananya.poc.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Users createUser(Users user) {
        return userRepository.save(user);
    }
    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }
}

package com.example.demo;

import com.example.demo.Entities.User;
import com.example.demo.Utils.BcryptUtil;
import com.example.demo.Utils.JwtUtil;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final BcryptUtil bcryptUtil;
    private final JwtUtil jwtUtil;
    public UserService(UserRepo userRepo, BcryptUtil bcryptUtil, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.bcryptUtil = bcryptUtil;
        this.jwtUtil = jwtUtil;
    }

    public void insertUser(@Valid @Size(min = 3, max = 255) String username, @Valid @Size(min=10, max = 255) String email, String pass) {
        // refazer pra validar os erros dos parametro la.
        // usar try-catch na inserçao e no update no controller.
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        String hash = bcryptUtil.hash(pass);
        user.setPass(hash);
        userRepo.save(user);
    }

    public HashMap<String, String> authenticateUser(String email, String pass) {
        Optional<User> user = userRepo.findUserByEmail(email);
        HashMap<String, String> response = new HashMap<>();
        if (user.isEmpty()) {
            response.put("Error", "404");
            response.put("Message", "User not found.");
            return response;
        }
        if (!bcryptUtil.compare(pass, user.get().getEmail())) {
            response.put("Error", "401");
            response.put("Message", "Incorrect password.");
            return response;
        }
        String token = jwtUtil.sign(user.get().getId());
        response.put("Token", token);
        return response;
    }

    public Optional<User> findUserById(Long id) {
        return userRepo.findById(id);
    }

    public boolean updateUsername(Long id, String newname) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            return false;
        }
        user.get().setName(newname);
        userRepo.save(user.get());
        return true;
    }


}

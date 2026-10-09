package com.example.demo.Services;

import com.example.demo.Entities.Rating;
import com.example.demo.Entities.User;
import com.example.demo.Repositories.RatingRepository;
import com.example.demo.Repositories.UserRepo;
import com.example.demo.Utils.BcryptUtil;
import com.example.demo.Utils.JwtUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final BcryptUtil bcryptUtil;
    private final JwtUtil jwtUtil;
    private final RatingRepository ratingRepo;
    public UserService(UserRepo userRepo, BcryptUtil bcryptUtil, JwtUtil jwtUtil, RatingRepository ratingRepo) {
        this.userRepo = userRepo;
        this.ratingRepo = ratingRepo;
        this.bcryptUtil = bcryptUtil;
        this.jwtUtil = jwtUtil;
    }

    public void insertUser(@Valid @Size(min = 3, max = 255) String username, @Valid @Size(min=10, max = 255) String email, String pass) {
        // refazer pra validar os erros dos parametro la.
        // usar try-catch na inserçao e no update no controller.
        String hash = bcryptUtil.hash(pass);
        userRepo.save(new User(username, hash, email));
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

    public void updateUsername(User user, String newname) throws IllegalArgumentException {
        if (newname.length() < 4) {
            throw new IllegalArgumentException("Username cannot be less than 4 characters long.");
        }
        user.setName(newname);
        userRepo.save(user);
    }
    // melhorar dps adicionar casos de exceçao etc
    public void rateARestaurant(String cnpj, Long user_id, double value) throws NullPointerException {
        Rating rating = new Rating(cnpj, user_id, value);
        ratingRepo.save(rating);
    }
}

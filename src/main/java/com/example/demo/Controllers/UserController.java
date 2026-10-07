package com.example.demo.Controllers;

import com.example.demo.LoginDTO;
import com.example.demo.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        HashMap<String, String> hm = userService.authenticateUser(loginDTO.email(), loginDTO.pass());
        if (hm.get("Token").isBlank()) {
            return ResponseEntity.status(Integer.getInteger(hm.get("Error"))).body(hm.get("Message"));
        }
        ResponseCookie responseCookie = ResponseCookie.from("authToken", hm.get("Token")).httpOnly(true).maxAge(60*60*24*3).sameSite("strict").domain("/").build();
        return ResponseEntity.ok("User logged in!");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid LoginDTO loginDTO) {
        userService.insertUser(loginDTO.name(), loginDTO.email(), loginDTO.pass());
        return ResponseEntity.ok().build();
    }
}

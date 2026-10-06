package com.example.demo.Utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BcryptUtil {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BcryptUtil(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String hash(String txt) {
        return bCryptPasswordEncoder.encode(txt);
    }

    public boolean compare(String txt, String hash) {
        return bCryptPasswordEncoder.matches(txt, hash);
    }
}

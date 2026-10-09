package com.example.demo.DTOs;

import jakarta.annotation.Nullable;

public record LoginDTO(@Nullable String name, String email, String pass) {

}

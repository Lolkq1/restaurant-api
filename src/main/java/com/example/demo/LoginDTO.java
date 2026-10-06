package com.example.demo;

import jakarta.annotation.Nullable;

public record LoginDTO(@Nullable String name, String email, String pass) {

}

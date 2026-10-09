package com.example.demo.DTOs;

import jakarta.annotation.Nullable;

public record UserDTO(@Nullable Long id, String name, String email) {
}

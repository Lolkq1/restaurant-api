package com.example.demo.DTOs;

import jakarta.annotation.Nullable;

public record RestaurantDTO(String name, String cnpj, @Nullable String local, @Nullable Integer phoneNumber, @Nullable Float avgRating, Long owner_id) {
}

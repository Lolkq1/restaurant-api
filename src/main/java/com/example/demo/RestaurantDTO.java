package com.example.demo;

import jakarta.annotation.Nullable;

public record RestaurantDTO(String name, String cnpj, @Nullable String local, @Nullable int phoneNumber, @Nullable float avgRating, Long owner_id) {
}

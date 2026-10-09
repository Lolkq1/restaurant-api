package com.example.demo.Repositories;

import com.example.demo.Entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepo extends JpaRepository<Restaurant, String> {
    public Optional<Restaurant> findAllByOwnerId(Long id);
    public Optional<Restaurant> findByCnpj(String cnpj);
}
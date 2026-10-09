package com.example.demo.Repositories;

import com.example.demo.Entities.IdRestaurantRating;
import com.example.demo.Entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, IdRestaurantRating> {
    public List<Optional<Rating>> findAllByUser_id(Long id);
    public List<Optional<Rating>> findAllByCnpj(String cnpj);
}

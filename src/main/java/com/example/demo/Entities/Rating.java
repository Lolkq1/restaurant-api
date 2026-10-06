package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@IdClass(IdRestaurantRating.class)
public class Rating {
    @Id
    @Getter
    @Setter
    private String res_cnpj;

    @Getter
    @Setter
    @Id
    private Long user_id;

    @Setter
    @Getter
    @NotBlank
    private BigDecimal rating;
}

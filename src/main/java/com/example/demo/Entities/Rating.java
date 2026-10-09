package com.example.demo.Entities;

import jakarta.persistence.*;
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
    private double rating;

    @ManyToOne
    @JoinTable(name = "user")
    @JoinColumn(name = "user_id",
    referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinTable(name = "restaurant")
    @JoinColumn(name = "res_cnpj",
    referencedColumnName = "cnpj")
    private Restaurant restaurant;

    public Rating() {

    }

    public Rating(String res_cnpj, Long user_id, double rating) {
        this.res_cnpj = res_cnpj;
        this.user_id = user_id;
        this.rating = rating;
    }
}

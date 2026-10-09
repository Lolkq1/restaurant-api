package com.example.demo.Entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;


public class IdRestaurantRating implements Serializable {
    private String res_cnpj;
    private Long user_id;

    public IdRestaurantRating() {

    }

    public IdRestaurantRating(String res_cnpj, Long user_id) {
        this.res_cnpj = res_cnpj;
        this.user_id = user_id;
    }
}

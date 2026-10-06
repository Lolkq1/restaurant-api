package com.example.demo.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "rating"
    )
    private List<Restaurant> ratedRestaurants;

    @ManyToMany
    @JoinTable(
            name = "reservation"
    )
    private List<Restaurant> reservationsByUser;

    @OneToMany(mappedBy = "owner")
    private List<Restaurant> ownedRestaurants = new ArrayList<>();

    @Getter
    @Email
    @Size(min = 40)
    @NotBlank
    @Setter
    @Column(unique = true)
    private String email;

    @Getter
    @NotBlank
    @Size(min = 4)
    @Setter
    private String name;

    @Getter
    @Setter
    @NotBlank
    private String pass;
}

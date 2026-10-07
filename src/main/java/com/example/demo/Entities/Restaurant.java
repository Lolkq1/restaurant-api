package com.example.demo.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @Setter
    @Getter
    private User owner;


    @Id
    @CNPJ(message = "Invalid CNPJ.")
    @Setter
    @Getter
    private String cnpj;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RTable> tables = new ArrayList<>();

    @Getter
    @Setter
    private String name;

    @Setter
    @Getter
    private String local;

    @Setter
    @Getter
    @Column(length = 11)
    @Size(max = 11, min = 11, message = "Invalid length.")
    private String phone_number;

    @Setter
    @Getter
    @Column(name = "avg_rating")
    private BigDecimal avgRating;
}

package com.example.demo.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.ArrayList;
import java.util.List;

@Entity
@IdClass(IdRestaurantTable.class)
public class RTable {
    @Id
    @Setter
    @Getter
    @Column(length = 14, name = "res_cnpj")
    @CNPJ
    private String resCnpj;

    @Id
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    @NotBlank
    private Long size;

    @OneToMany(mappedBy = "rtable", orphanRemoval = true)
    List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "res_cnpj", nullable = false)
    private Restaurant restaurant;
}

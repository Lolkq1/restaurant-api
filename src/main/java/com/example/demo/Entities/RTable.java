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

    @OneToMany(mappedBy = "rTable", orphanRemoval = true)
    // explicacao pq eu mesmo esqueci ai lembrei agr: ta one-to-many pq uma mesa pode ser reservada varias vezes dps da primeira vez,
    // ja que eu nao pretendo apagar as reservas apenas deixa-las como inativas entao pra nao dar problema fica assim.
    List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "res_cnpj", nullable = false)
    private Restaurant restaurant;
}

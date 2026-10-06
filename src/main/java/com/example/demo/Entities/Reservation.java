package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@IdClass(IdRestaurantReservation.class)
public class Reservation {
    @Id
    @Setter
    @Getter
    @Column(name = "res_cnpj")
    private String resCnpj;

    @Id
    @Setter
    @Getter
    private Long id;

    @Id
    @Setter
    @Getter
    @Column(name = "user_id")
    private Long userId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rtable_id",
        referencedColumnName = "id"
    )
    private RTable rTable;

    @Setter
    @Getter
    private Boolean active;

    @Setter
    @Getter
    @Column(name = "scheduled_to")
    private LocalDateTime dateTime;
}

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

    // explicacao pq eu mesmo esqueci ai lembrei agr: ta many-to-one pq uma mesa pode ser reservada varias vezes dps da primeira vez,
    // ja que eu nao pretendo apagar as reservas apenas deixa-las como inativas entao pra nao dar problema fica assim.
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

    public Reservation() {

    }

    public Reservation(LocalDateTime dateTime, Long userId, Long id, String resCnpj, RTable rTable) {
        this.dateTime = dateTime;
        this.userId = userId;
        this.id = id;
        this.resCnpj = resCnpj;
        this.rTable = rTable;
        this.active = true;
    }
}

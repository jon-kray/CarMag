package ru.ecosystem.carsale.app.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "EVENTS")
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "TRANSMISSION_TYPE")
    @Enumerated(EnumType.STRING)
    private String transmissionType;

    @Column(name = ("MARK"))
    private String mark;

    @Column(name = "SELL_DATE")
    private LocalDate sellDate;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PHOTO")
    private byte[] photo;

    @Column(name = "price")
    private String price;
}

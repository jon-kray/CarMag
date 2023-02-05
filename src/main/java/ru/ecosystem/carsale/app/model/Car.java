package ru.ecosystem.carsale.app.model;

import lombok.*;
import ru.ecosystem.carsale.app.dto.enums.TransmissionType;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "cars")
@Entity
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "TRANSMISSION_TYPE")
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    @Column(name = ("MARK"))
    private String mark;



}

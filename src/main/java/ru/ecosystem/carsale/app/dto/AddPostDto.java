package ru.ecosystem.carsale.app.dto;

import lombok.*;
import ru.ecosystem.carsale.app.dto.enums.SellStatus;
import ru.ecosystem.carsale.app.dto.enums.TransmissionType;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddPostDto {

    private String model;

    private String mark;

    private TransmissionType transmissionType;

    private String price;

    private String description;

    private byte[] photo;

    private LocalDateTime created;

    private SellStatus sellStatus;
}

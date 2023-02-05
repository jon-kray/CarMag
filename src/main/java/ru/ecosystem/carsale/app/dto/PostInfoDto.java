package ru.ecosystem.carsale.app.dto;


import lombok.*;
import ru.ecosystem.carsale.app.dto.enums.SellStatus;
import ru.ecosystem.carsale.app.dto.enums.TransmissionType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostInfoDto {

    private long id = -1L;

    private String model;

    private String mark;

    private TransmissionType transmissionType;

    private String price;

    private LocalDateTime created;

    private SellStatus sellStatus;
}

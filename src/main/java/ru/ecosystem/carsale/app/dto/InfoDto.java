package ru.ecosystem.carsale.app.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class InfoDto {

    private String description;

    private String photoBase64;
}

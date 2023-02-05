package ru.ecosystem.carsale.app.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpDto {

    private String username;

    private String password;
}

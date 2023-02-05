package ru.ecosystem.carsale.app.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignInDto {

    private String username;

    private String password;
}

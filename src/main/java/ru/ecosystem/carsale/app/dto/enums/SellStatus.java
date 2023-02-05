package ru.ecosystem.carsale.app.dto.enums;


import lombok.Getter;

public enum SellStatus {

    SALES("Продано"),
    ON_SALE("В продаже");

    @Getter
    private final String named;

    SellStatus(String named) {
        this.named = named;
    }
}

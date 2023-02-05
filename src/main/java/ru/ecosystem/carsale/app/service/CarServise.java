package ru.ecosystem.carsale.app.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ecosystem.carsale.app.repository.interfases.CarRepository;

@Service
@RequiredArgsConstructor
public class CarServise {

    public final CarRepository carRepository;
}

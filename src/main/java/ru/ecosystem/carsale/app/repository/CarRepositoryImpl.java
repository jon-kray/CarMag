package ru.ecosystem.carsale.app.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.ecosystem.carsale.app.repository.interfases.CarRepository;

@Repository
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository {

    private final SessionFactory sessionFactory;


}

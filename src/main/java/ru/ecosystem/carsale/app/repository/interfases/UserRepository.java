package ru.ecosystem.carsale.app.repository.interfases;

import org.springframework.stereotype.Repository;
import ru.ecosystem.carsale.app.model.User;

public interface UserRepository {

    User findByUsername(String username);

    User findById(long id);

    void save(User user);

}

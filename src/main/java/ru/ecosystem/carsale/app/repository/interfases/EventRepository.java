package ru.ecosystem.carsale.app.repository.interfases;

import org.springframework.stereotype.Repository;
import ru.ecosystem.carsale.app.model.Event;

public interface EventRepository {

    public void saveAll(Iterable<Event> event);
}

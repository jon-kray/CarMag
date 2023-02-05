package ru.ecosystem.carsale.app.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ecosystem.carsale.app.model.Event;
import ru.ecosystem.carsale.app.repository.interfases.EventRepository;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveAll(Iterable<Event> event) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            event.forEach(session::save);
            session.getTransaction().commit();
        }
    }
}

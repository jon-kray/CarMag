package ru.ecosystem.carsale.app.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ecosystem.carsale.app.model.User;
import ru.ecosystem.carsale.app.repository.interfases.UserRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;


    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var query = session.createQuery("FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            var rsl = query.getSingleResult();
            session.getTransaction().commit();
            return rsl;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var query = session.createQuery("FROM User u WHERE u.id = :id", User.class);
            query.setParameter("id", id);
            var rsl = query.getSingleResult();
            session.getTransaction().commit();
            return rsl;
        }
    }

    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }
}

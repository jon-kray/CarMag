package ru.ecosystem.carsale.app.repository;


import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ecosystem.carsale.app.dto.enums.SellStatus;
import ru.ecosystem.carsale.app.model.Post;
import ru.ecosystem.carsale.app.model.User;
import ru.ecosystem.carsale.app.repository.interfases.PostRepository;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class PostRepositoryImpl implements PostRepository {

    private final SessionFactory sessionFactory;


    @Transactional(readOnly = true)
    public List<Post> getPostsByUser(long userId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var query = session.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.posts WHERE u.id = :userId", User.class);
            query.setParameter("userId", userId);
            var rsl = query.getSingleResult().getPosts();
            session.getTransaction().commit();
            return rsl;
        }
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var query = session.createQuery("FROM Post", Post.class);
            var rsl = query.getResultList();
            session.getTransaction().commit();
            return rsl;
        }
    }

    @Override
    public void savePost(Post post) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(post);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateStatus(long postId, SellStatus sellStatus) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var query = session.createQuery("UPDATE Post p SET p.sellStatus = :status WHERE p.id = :id");
            query.setParameter("status", sellStatus);
            query.setParameter("id", postId);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Post getPostById(long postId) {
        try (Session session = sessionFactory.openSession()) {
            var em = session.getEntityManagerFactory().createEntityManager();
            EntityGraph<?> graph = em.createEntityGraph("post-entity-graph-info");
            TypedQuery<Post> q = em.createQuery("SELECT p FROM Post p WHERE p.id = :id", Post.class);
            q.setParameter("id", postId);
            q.setHint("javax.persistence.fetchgraph", graph);
            return q.getSingleResult();
        }
    }

    @Override
    public List<Post> getSalesCars() {
        try (Session session = sessionFactory.openSession()) {
            var em = session.getEntityManagerFactory().createEntityManager();
            EntityGraph<?> graph = em.createEntityGraph("post-entity-graph-event");
            TypedQuery<Post> q = em.createQuery("FROM Post p WHERE p.sellStatus = :status", Post.class);
            q.setParameter("status", SellStatus.SALES);
            q.setHint("javax.persistence.fetchgraph", graph);
            return q.getResultList();
        }
    }

    @Override
    public List<Post> getPostsByMarkCar(String mark) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var query = session.createQuery("SELECT p FROM Post p WHERE LOWER(p.car.mark) = :mark", Post.class);
            query.setParameter("mark", mark.toLowerCase());
            var rsl = query.getResultList();
            session.getTransaction().commit();
            return rsl;
        }
    }

    @Override
    public void deletePosts(Iterable<Post> posts) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            posts.forEach(session::delete);
            session.getTransaction().commit();
        }
    }
}

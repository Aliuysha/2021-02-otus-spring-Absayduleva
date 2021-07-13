package ru.otus.library.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            em.flush();
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a " +
                "from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public List<Author> findByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a " +
                "from Author a where a.name = :name", Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String name) {
        Author author = em.find(Author.class, id);
        author.setName(name);
        em.merge(author);
    }

    @Override
    public void deleteById(long id) {
        Author author = em.find(Author.class, id);
        em.remove(author);
    }
}

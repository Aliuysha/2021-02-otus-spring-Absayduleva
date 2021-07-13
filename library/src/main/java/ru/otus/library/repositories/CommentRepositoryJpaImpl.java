package ru.otus.library.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            em.flush();
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c " +
                "from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public List<Comment> findByText(String text) {
        TypedQuery<Comment> query = em.createQuery("select c " +
                "from Comment c where c.text = :text", Comment.class);
        query.setParameter("text", text);
        return query.getResultList();
    }

    @Override
    public void updateTextById(long id, String text) {
        Comment comment = em.find(Comment.class, id);
        comment.setText(text);
        em.merge(comment);
    }

    @Override
    public void deleteById(long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }
}

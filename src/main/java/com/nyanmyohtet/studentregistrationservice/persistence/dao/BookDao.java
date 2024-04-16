package com.nyanmyohtet.studentregistrationservice.persistence.dao;

import com.nyanmyohtet.studentregistrationservice.persistence.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public List<Book> getAllBooks() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public List<Book> getBooksByAuthor(String author) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
    }

    public List<Book> getBooksByTitleJPQL(String title) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class)
                .setParameter("title", title)
                .getResultList();
    }

    public List<Book> getBooksByTitleHQL(String title) {
        Query query = entityManager.createQuery("FROM Book b WHERE b.title = :title");
        query.setParameter("title", title);
        return query.getResultList();
    }
}

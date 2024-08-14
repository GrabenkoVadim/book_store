package mate.academy.book_store.repository.impl;

import mate.academy.book_store.exception.DataProcessingException;
import mate.academy.book_store.model.Book;
import mate.academy.book_store.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private static SessionFactory sessionFactory;

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(book);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DataProcessingException("Can't save book" + book, e);
        } finally {
            if (session != null) session.close();
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Book", Book.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find all books", e);
        }
    }
}

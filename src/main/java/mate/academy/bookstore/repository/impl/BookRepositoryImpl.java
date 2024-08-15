package mate.academy.bookstore.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import mate.academy.bookstore.exception.DataProcessingException;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Autowired
    private final EntityManagerFactory entityManagerFactory;

    public BookRepositoryImpl(EntityManagerFactory entityManagerFactory, BookMapper bookMapper) {
        this.entityManagerFactory = entityManagerFactory;
        this.bookMapper = bookMapper;
    }

    private final BookMapper bookMapper;

    @Override
    public Book save(Book book) {
        EntityTransaction transaction = null;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new DataProcessingException("Can't save book " + book, e);
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find all books", e);
        }
    }

    @Override
    public BookDto getBookById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Book book = entityManager.find(Book.class, id);
            if (book == null) {
                throw new EntityNotFoundException("Can't find book with id " + id);
            }
            return bookMapper.toDto(book);
        } catch (Exception e) {
            throw new EntityNotFoundException("Can't find book with id " + id, e);
        }

    }

    @Override
    public Book createBook(CreateBookRequestDto bookDto) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Book book = new Book();
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setIsbn(bookDto.getIsbn());
            book.setPrice(bookDto.getPrice());
            book.setCoverImage(bookDto.getCoverImage());
            return save(book);
        } catch (Exception e) {
            throw new EntityNotFoundException("Can't create book " + bookDto, e);
        }
    }
}

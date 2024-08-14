package mate.academy.book_store.repository;

import mate.academy.book_store.exception.DataProcessingException;
import mate.academy.book_store.model.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAll();
}

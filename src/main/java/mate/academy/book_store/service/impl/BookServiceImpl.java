package mate.academy.book_store.service.impl;

import mate.academy.book_store.model.Book;
import mate.academy.book_store.repository.BookRepository;
import mate.academy.book_store.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private static BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}

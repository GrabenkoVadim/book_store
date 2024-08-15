package mate.academy.bookstore.repository;

import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import mate.academy.bookstore.model.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);
    List<Book> getAll();
    BookDto getBookById(Long id);
    Book createBook(CreateBookRequestDto bookDto);
}

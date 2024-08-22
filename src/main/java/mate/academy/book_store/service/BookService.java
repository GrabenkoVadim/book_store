package mate.academy.book_store.service;

import mate.academy.book_store.dto.BookDto;
import mate.academy.book_store.dto.BookSearchParametersDto;
import mate.academy.book_store.dto.CreateBookRequestDto;

import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    BookDto updateBook(Long id, CreateBookRequestDto bookDto);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParametersDto searchParametersDto);
}

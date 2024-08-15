package mate.academy.bookstore.service.impl;

import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.BookRepository;
import mate.academy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository
                .getAll()
                .stream()
                .map(bookMapper::toDto).toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public Book createBook(CreateBookRequestDto bookDto) {
        Book book = bookMapper.toModel(bookDto);
        return bookRepository.save(book);
    }
}

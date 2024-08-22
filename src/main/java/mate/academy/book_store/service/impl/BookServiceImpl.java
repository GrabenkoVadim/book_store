package mate.academy.book_store.service.impl;

import mate.academy.book_store.dto.BookDto;
import mate.academy.book_store.dto.BookSearchParametersDto;
import mate.academy.book_store.dto.CreateBookRequestDto;
import mate.academy.book_store.exception.EntityNotFoundException;
import mate.academy.book_store.mapper.BookMapper;
import mate.academy.book_store.model.Book;
import mate.academy.book_store.repository.BookRepository;
import mate.academy.book_store.repository.specifications.bookSpecifications.BookSpecificationBuilder;
import mate.academy.book_store.service.BookService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, BookSpecificationBuilder bookSpecificationBuilder) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookSpecificationBuilder = bookSpecificationBuilder;
    }

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository
                .findAll()
                .stream()
                .map(bookMapper::toDto).toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    @Override
    public List<BookDto> search(BookSearchParametersDto searchParametersDto) {
        Specification<Book> specification = bookSpecificationBuilder.build(searchParametersDto);
        return bookRepository.findAll(specification)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto updateBook(Long id, CreateBookRequestDto bookDto) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setPrice(bookDto.getPrice());
            book.setCoverImage(bookDto.getCoverImage());
            book.setIsbn(bookDto.getIsbn());
            Book updatedBook = bookRepository.save(book);
            return bookMapper.toDto(updatedBook);
        }).orElseThrow(() -> new EntityNotFoundException("Book with id "
                + id + " not found"));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}

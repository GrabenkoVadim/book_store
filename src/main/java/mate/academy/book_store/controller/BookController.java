package mate.academy.book_store.controller;

import jakarta.validation.Valid;
import mate.academy.book_store.dto.BookDto;
import mate.academy.book_store.dto.BookSearchParametersDto;
import mate.academy.book_store.dto.CreateBookRequestDto;
import mate.academy.book_store.exception.EntityNotFoundException;
import mate.academy.book_store.exception.SpecificationException;
import mate.academy.book_store.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDto findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public List<BookDto> searchBooks(BookSearchParametersDto searchParametersDto) {
        if (searchParametersDto != null) {
            return bookService.search(searchParametersDto);
        }
        throw new SpecificationException("Invalid search parameters");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto save(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody @Valid CreateBookRequestDto bookDto) {
        BookDto existingBook = bookService.findById(id);
        if (existingBook != null) {
            return bookService.updateBook(id, bookDto);
        }
        throw new EntityNotFoundException("Book with id " + id + " not found");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}

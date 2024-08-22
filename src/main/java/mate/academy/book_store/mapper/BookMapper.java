package mate.academy.book_store.mapper;

import mate.academy.book_store.config.MapperConfig;
import mate.academy.book_store.dto.BookDto;
import mate.academy.book_store.dto.CreateBookRequestDto;
import mate.academy.book_store.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookDto);
}

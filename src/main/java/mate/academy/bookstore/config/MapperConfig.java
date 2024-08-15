package mate.academy.bookstore.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
@org.mapstruct.MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "mate.academy.book_store.mapper.impl"
)
public class MapperConfig {

}

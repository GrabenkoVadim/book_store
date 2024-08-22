package mate.academy.book_store.repository.specifications.bookSpecifications;

import lombok.RequiredArgsConstructor;
import mate.academy.book_store.dto.BookSearchParametersDto;
import mate.academy.book_store.model.Book;
import mate.academy.book_store.repository.specifications.SpecificationBuilder;
import mate.academy.book_store.repository.specifications.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationBuilder<T> implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    public BookSpecificationBuilder(SpecificationProviderManager<Book> bookSpecificationProviderManager) {
        this.bookSpecificationProviderManager = bookSpecificationProviderManager;
    }

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> spec = Specification.where(null);
        if (searchParameters.titles() != null && searchParameters.titles().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("title").getSpecification(searchParameters.titles()));
        }
        if (searchParameters.authors() != null && searchParameters.authors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("author").getSpecification(searchParameters.authors()));
        }
        if (searchParameters.prices() != null && searchParameters.prices().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("price").getSpecification(searchParameters.prices()));
        }
        return spec;
    }
}

package mate.academy.book_store.repository.specifications.bookSpecifications;

import mate.academy.book_store.exception.SpecificationException;
import mate.academy.book_store.model.Book;
import mate.academy.book_store.repository.specifications.SpecificationProvider;
import mate.academy.book_store.repository.specifications.SpecificationProviderManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookSpecificationProviderManagerImpl<T> implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    public BookSpecificationProviderManagerImpl(List<SpecificationProvider<Book>> bookSpecificationProviders) {
        this.bookSpecificationProviders = bookSpecificationProviders;
    }

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst().orElseThrow(
                        () -> new SpecificationException("Can't find correct specification provider for key " + key));
    }
}

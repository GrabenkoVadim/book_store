package mate.academy.book_store.repository.specifications.bookSpecifications;

import mate.academy.book_store.model.Book;
import mate.academy.book_store.repository.specifications.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public Specification<Book> getSpecification(Object[] params) {
        return (root, query, criteriaBuilder) -> root.get("price").in(Arrays.stream(params).toArray());
    }

    @Override
    public String getKey() {
        return "price";
    }
}

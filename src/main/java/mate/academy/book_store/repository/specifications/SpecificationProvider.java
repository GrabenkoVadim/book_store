package mate.academy.book_store.repository.specifications;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    Specification<T> getSpecification(Object[] params);

    String getKey();
}

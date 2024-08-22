package mate.academy.book_store.dto;

import java.math.BigDecimal;

public record BookSearchParametersDto(BigDecimal[] prices, String[] titles, String[] authors) {
}

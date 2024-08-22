package mate.academy.book_store.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;

@Data
public class CreateBookRequestDto {
    @NotNull
    @Size(min = 2, max = 50)
    private String title;
    @NotNull
    @Size(min = 2, max = 50)
    private String author;
//    @ISBN(type = ISBN.Type.ANY)
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private String coverImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}

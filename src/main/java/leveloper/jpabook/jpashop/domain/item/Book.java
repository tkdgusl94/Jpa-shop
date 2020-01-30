package leveloper.jpabook.jpashop.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@NoArgsConstructor
@Setter
public class Book extends Item {
    private String author;
    private String isbn;

    @Builder
    public Book(String name, int price, int stockQuantity, String author, String isbn) {
        super.setName(name);
        super.setPrice(price);
        super.setStockQuantity(stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}

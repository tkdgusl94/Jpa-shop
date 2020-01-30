package leveloper.jpabook.jpashop.controller.form;

import leveloper.jpabook.jpashop.domain.item.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public Book toEntity(){
        return Book.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .author(author)
                .isbn(isbn)
                .build();
    }
}

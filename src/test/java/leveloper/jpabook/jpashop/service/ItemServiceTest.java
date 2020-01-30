package leveloper.jpabook.jpashop.service;

import leveloper.jpabook.jpashop.domain.item.Book;
import leveloper.jpabook.jpashop.domain.item.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Test
    public void 상품목록조회() throws Exception{
        //given
        String name = "book";
        int price = 10000;
        int stockQuantity = 10;
        String author = "jpa";
        String isbn = "123";

        Book book = Book.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .author(author)
                .isbn(isbn)
                .build();

        itemService.saveItem(book);

        //when
        List<Item> items = itemService.findItems();

        //then
        assertEquals(name, items.get(0).getName());
    }
}

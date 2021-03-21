package cz.upce.eshop;

import cz.upce.eshop.entity.Order;
import cz.upce.eshop.entity.OrderHasProduct;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.entity.StaneEnum;
import cz.upce.eshop.repository.OrderHasProductRepository;
import cz.upce.eshop.repository.OrderRepository;
import cz.upce.eshop.repository.ProductRepository;
import cz.upce.eshop.service.ShoppingCartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class ShoppingCartTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Test
    void AddToShoppingCartTest() {
        Product product = new Product();
        product.setName("MyProduct");
        product.setPrice(200);
        product.setCount(10);
        productRepository.save(product);
        List<Product> all = productRepository.findAll();

        Long productId = all.get(0).getId();

        shoppingCartService.add(productId);


        Assertions.assertEquals(1, shoppingCartService.getCart().size());
        Assertions.assertTrue(shoppingCartService.getCart().containsKey(all.get(0)));
        Assertions.assertEquals(1, shoppingCartService.getCart().get(all.get(0)));

        shoppingCartService.add(productId);
        Assertions.assertEquals(2, shoppingCartService.getCart().get(all.get(0)));

        shoppingCartService.add(productId);
        Assertions.assertEquals(3, shoppingCartService.getCart().get(all.get(0)));

        shoppingCartService.remove(productId);
        Assertions.assertEquals(2, shoppingCartService.getCart().get(all.get(0)));

        shoppingCartService.remove(productId);
        Assertions.assertEquals(1, shoppingCartService.getCart().get(all.get(0)));

        shoppingCartService.remove(productId);
        Assertions.assertNull(shoppingCartService.getCart().get(all.get(0)));
    }
}

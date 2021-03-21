package cz.upce.eshop;

import cz.upce.eshop.entity.Order;
import cz.upce.eshop.entity.OrderHasProduct;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.entity.StaneEnum;
import cz.upce.eshop.repository.OrderHasProductRepository;
import cz.upce.eshop.repository.OrderRepository;
import cz.upce.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderHasProductRepository orderHasProductRepository;

    @Test
    void makeOrderTest() {
        Product product = new Product();
        product.setName("MyProduct");
        product.setPrice(200);
        product.setCount(10);
        productRepository.save(product);
        System.out.println(product);

        Assertions.assertEquals(1, productRepository.findAll().size());

        Order order = new Order();
        order.setState(StaneEnum.NEW);
        orderRepository.save(order);

        Assertions.assertEquals(1, orderRepository.findAll().size());

        OrderHasProduct orderHasProduct = new OrderHasProduct();
        orderHasProduct.setProduct(product);
        orderHasProduct.setOrder(order);
        orderHasProduct.setAmount(5);
        orderHasProductRepository.save(orderHasProduct);

        Assertions.assertEquals(orderHasProductRepository.findAll().size(), 1);
        Assertions.assertEquals(orderHasProduct.getTotalPrice(), product.getPrice() * orderHasProduct.getAmount());
    }
}

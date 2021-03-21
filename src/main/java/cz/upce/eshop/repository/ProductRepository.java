package cz.upce.eshop.repository;

import cz.upce.eshop.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"productInOrders"})
    Product findProductByNameContains(String contains);

    List<Product> findProductsByIdBetween(Long start, Long finish);
}

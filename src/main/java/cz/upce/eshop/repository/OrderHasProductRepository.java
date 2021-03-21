package cz.upce.eshop.repository;

import cz.upce.eshop.entity.Order;
import cz.upce.eshop.entity.OrderHasProduct;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHasProductRepository extends JpaRepository<OrderHasProduct, Long> {

}

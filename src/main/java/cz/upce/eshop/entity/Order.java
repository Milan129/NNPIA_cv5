package cz.upce.eshop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "order_form")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StaneEnum state;

    @OneToMany(mappedBy = "id")
    private Set<OrderHasProduct> orderHasProducts;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", state=" + state +
                ", orderHasProducts=" + orderHasProducts +
                '}';
    }

    public Set<OrderHasProduct> getOrderHasProducts() {
        return orderHasProducts;
    }

    public void setOrderHasProducts(Set<OrderHasProduct> orderHasProducts) {
        this.orderHasProducts = orderHasProducts;
    }

    public StaneEnum getState() {
        return state;
    }

    public void setState(StaneEnum state) {
        this.state = state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

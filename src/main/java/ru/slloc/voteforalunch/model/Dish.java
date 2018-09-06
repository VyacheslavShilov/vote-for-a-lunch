package ru.slloc.voteforalunch.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishs", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id"}, name = "dishs_unique_name_restaurant_id_idx")})
public class Dish extends AbstractNamedEntity implements HavinId {
    public static final int START_DISH_SEQ = 1000;

    @Id
    @SequenceGenerator(name = "DISHS_SEQ", sequenceName = "DISHS_SEQ", allocationSize = 1, initialValue = START_DISH_SEQ)
    //    @Column(name = "id", unique = true, nullable = false, columnDefinition = "integer default nextval('global_seq')")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISHS_SEQ")

//  See https://hibernate.atlassian.net/browse/HHH-3718 and https://hibernate.atlassian.net/browse/HHH-12034
//  Proxy initialization when accessing its identifier managed now by JPA_PROXY_COMPLIANCE setting
    private Integer id;

    /*@Column(name = "date_time", columnDefinition = "date_tim default now()")
    @NotNull
    private Date dateTime = new Date();*/

    @Column(name = "price", columnDefinition = "price")
    @Range(min = 0, max = 10000)
    private int price;

    @Column(name = "restaurant_id", columnDefinition = "restaurant_id")
    @NotNull
    private int restaurantId;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    public Dish() {
    }

    public Dish(Dish d) {
        this(d.getId(), d.getName(), d.getPrice(), d.getRestaurantId(), d.isEnabled());
    }

    public Dish(Integer id, String name, @Range(min = 0, max = 10000) int price, @NotNull int restaurantId, boolean enabled) {
        super(name);
        this.id = id;
        this.price = price;
        this.restaurantId = restaurantId;
        this.enabled = enabled;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dish{");
        sb.append("price=").append(price);
        sb.append(", restaurantId=").append(restaurantId);
        sb.append(", enabled=").append(enabled);
        sb.append(", name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

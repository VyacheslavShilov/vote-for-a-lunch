package ru.slloc.voteforalunch.model;

import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames ={"date_time", "restaurant_id"}, name = "menu_unique_restaurant_date_idx")})
public class Menu implements HavinId{
    public static final int START_MENU_SEQ = 1000;

    @Id
    @SequenceGenerator(name = "MENUS_SEQ", sequenceName = "MENUS_SEQ", allocationSize = 1, initialValue = START_MENU_SEQ)
    //    @Column(name = "id", unique = true, nullable = false, columnDefinition = "integer default nextval('global_seq')")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENUS_SEQ")
    private Integer id;

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NotNull
    private Restaurant restaurant;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")//, cascade = CascadeType.REMOVE, orphanRemoval = true)

    @ManyToMany
    @JoinTable(name = "menu_dishes",
            joinColumns=@JoinColumn(name="menu_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="dish_id", referencedColumnName="id"))
    @OrderBy("name DESC")
    protected List<Dish> dishes;

    public Menu(){}

    public Menu(Menu m){
        this(m.getId(), m.getDateTime(), m.getRestaurant());
    }

    public Menu(Integer id, @NotNull LocalDateTime dateTime, @NotNull Restaurant restaurant) {
        this.id = id;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public void setId(Integer id) {
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        Menu that = (Menu) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Menu{");
        sb.append("id=").append(id);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", restaurant=").append(restaurant);
        sb.append(", dishes=").append(dishes);
        sb.append('}');
        return sb.toString();
    }
}

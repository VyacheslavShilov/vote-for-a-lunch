package ru.slloc.voteforalunch.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "uniqueConstraints_unique_name_idx")})
public class Restaurant extends AbstractNamedEntity implements HavinId {
    public static final int START_RESTAURANT_SEQ = 1000;

    @Id
    @SequenceGenerator(name = "RESTAURANTS_SEQ", sequenceName = "RESTAURANTS_SEQ", allocationSize = 1, initialValue = START_RESTAURANT_SEQ)
    //    @Column(name = "id", unique = true, nullable = false, columnDefinition = "integer default nextval('global_seq')")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESTAURANTS_SEQ")

//  See https://hibernate.atlassian.net/browse/HHH-3718 and https://hibernate.atlassian.net/browse/HHH-12034
//  Proxy initialization when accessing its identifier managed now by JPA_PROXY_COMPLIANCE setting
    private Integer id;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;



    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.isEnabled());
    }


    public Restaurant(Integer id, String name, boolean enabled) {
        super(name);
        this.id = id;
        this.enabled = enabled;
    }

    public Restaurant(Integer id, String name) {
        super(name);
        this.id = id;
        this.enabled = true;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("name DESC")
    protected List<Dish> dishes;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        Restaurant that = (Restaurant) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}

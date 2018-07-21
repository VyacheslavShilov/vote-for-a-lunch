package ru.slloc.voteforalunch.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dishs", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id"}, name = "dishs_unique_name_restaurant_id_idx")})
public class Dish extends AbstractNamedEntity {


}

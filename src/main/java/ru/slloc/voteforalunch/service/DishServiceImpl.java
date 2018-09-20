package ru.slloc.voteforalunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.slloc.voteforalunch.model.Dish;
import ru.slloc.voteforalunch.repository.DishRepository;
import ru.slloc.voteforalunch.util.exception.NotFoundException;

import java.util.List;

import static ru.slloc.voteforalunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService{

    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public List<Dish> getMenu(int restaurantId) {
        return repository.getMenu(restaurantId);
    }

    @Override
    public Dish update(Dish dish, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    @Override
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurantId);
    }
}

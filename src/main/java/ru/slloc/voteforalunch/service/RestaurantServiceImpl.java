package ru.slloc.voteforalunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.slloc.voteforalunch.model.Restaurant;
import ru.slloc.voteforalunch.model.Vote;
import ru.slloc.voteforalunch.repository.RestaurantRepository;
import ru.slloc.voteforalunch.repository.VoteRepository;
import ru.slloc.voteforalunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.slloc.voteforalunch.util.ValidationUtil.checkNotFound;
import static ru.slloc.voteforalunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.restaurantRepository = repository;
    }




    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @Override
    public Restaurant getByName(String name) throws NotFoundException {
        Assert.notNull(name, "name must not be null");
        return checkNotFound(restaurantRepository.getByName(name), "name=" + name);
    }

    @Override
    public List<Restaurant> getWinner(LocalDate date) throws NotFoundException {
        Assert.notNull(date, "date must not be null");

        List<Vote> votes = voteRepository.getAllForDate(date);
        if (votes.isEmpty()) throw new NotFoundException("There are no voices");

        Map<Integer, Integer> restaurantAndVotesMap = new HashMap<>();
        int maxVotes = 0;

        for (Vote v: votes   ) {
            Integer restaurantId = v.getRestaurant().getId();

            if (restaurantAndVotesMap.containsKey(restaurantId)) {
                restaurantAndVotesMap.put(restaurantId, restaurantAndVotesMap.get(restaurantId) + 1);
                if (restaurantAndVotesMap.get(restaurantId) > maxVotes) {
                    maxVotes = restaurantAndVotesMap.get(restaurantId);}
            } else {
                restaurantAndVotesMap.put(restaurantId, 1);
                if (restaurantAndVotesMap.get(restaurantId) > maxVotes) {
                    maxVotes = restaurantAndVotesMap.get(restaurantId);}
            }
        }

        List<Restaurant> winnerRestaurantList = new ArrayList<>();

        for(Map.Entry<Integer, Integer> pair: restaurantAndVotesMap.entrySet()){
            if (pair.getValue() == maxVotes) winnerRestaurantList.add(restaurantRepository.get(pair.getKey()));
        }

        return winnerRestaurantList;
    }


    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }
}

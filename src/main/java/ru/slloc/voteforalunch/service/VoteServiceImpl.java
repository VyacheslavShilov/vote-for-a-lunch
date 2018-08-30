package ru.slloc.voteforalunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.slloc.voteforalunch.model.Vote;
import ru.slloc.voteforalunch.repository.VoteRepository;
import ru.slloc.voteforalunch.util.exception.NotFoundException;

import java.util.List;

import static ru.slloc.voteforalunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Vote update(Vote vote, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(vote, userId), vote.getId());
    }

    @Override
    public Vote create(Vote vote, int userId) {
        Assert.notNull(vote, "meal must not be null");
        return repository.save(vote, userId);
    }

//    @Override
//    public Vote getWithUser(int id, int userId) {
//        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
//    }
}

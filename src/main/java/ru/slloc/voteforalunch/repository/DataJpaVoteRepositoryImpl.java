package ru.slloc.voteforalunch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.slloc.voteforalunch.model.Vote;

import java.util.List;

public class DataJpaVoteRepositoryImpl implements VoteRepository{

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Vote save(Vote vote, int userId) {
        if(!vote.isNew() && get(vote.getId(), userId) == null){
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }
}

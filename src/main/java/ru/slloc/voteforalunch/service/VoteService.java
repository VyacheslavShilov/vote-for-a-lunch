package ru.slloc.voteforalunch.service;

import ru.slloc.voteforalunch.model.Vote;
import ru.slloc.voteforalunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    //getBetveen - topJava

    List<Vote> getAll(int userId);

    List<Vote> getAllForDate(LocalDate date);

    Vote update(Vote vote, int userId) throws NotFoundException;

    Vote create(Vote vote, int userId);



  //  Vote getWithUser(int id, int userId);
}

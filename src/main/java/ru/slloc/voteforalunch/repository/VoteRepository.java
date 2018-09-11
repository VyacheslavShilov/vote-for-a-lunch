package ru.slloc.voteforalunch.repository;

import ru.slloc.voteforalunch.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    // null if updated vote do not belong to userId
    Vote save(Vote vote, int userId);

    // false if vote do not belong to userId
    boolean delete(int id, int userId);

    //null if vote do not belong to userId
    Vote get(int id, int userId);

    // ORDERED dateTime desc
    List<Vote> getAll(int userId);

    List<Vote> getAllForDate(LocalDate date);

    // ORDERED dateTime desc
   // List<Vote> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}

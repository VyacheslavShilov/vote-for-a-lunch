package ru.slloc.voteforalunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.slloc.voteforalunch.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Vote save(Vote item);

    @Query("SELECT m FROM Vote m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Vote> getAll(@Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v FROM Vote v WHERE v.dateTime BETWEEN :startDate AND :endDate")
    List<Vote> getAllForDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT m from Vote m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Vote> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT m FROM Vote m JOIN FETCH m.user WHERE m.id = ?1 and m.user.id = ?2")
    Vote getWithUser(int id, int userId);


}

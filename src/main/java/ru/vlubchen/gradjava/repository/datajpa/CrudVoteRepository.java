package ru.vlubchen.gradjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vlubchen.gradjava.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Vote m WHERE m.user.id=:userId ORDER BY m.day DESC")
    List<Vote> getAll(@Param("userId") int userId);

    @Query("SELECT m FROM Vote m WHERE m.restaurant.id=:restaurantId AND m.day=:day")
    List<Vote> getByRestaurantInDay(@Param("restaurantId") int restaurantId, @Param("day") LocalDate day);
}
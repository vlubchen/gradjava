package ru.vlubchen.gradjava.repository;

import ru.vlubchen.gradjava.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    // null if not found, when updated
    Vote save(Vote vote, int userId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int id, int userId);

    // null if not found
    List<Vote> getByRestaurantInDay(int restaurantId, LocalDate day);

    // ORDERED date asc
    List<Vote> getAll(int userId);
}
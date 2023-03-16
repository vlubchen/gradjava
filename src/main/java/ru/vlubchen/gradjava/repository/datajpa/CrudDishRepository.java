package ru.vlubchen.gradjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vlubchen.gradjava.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Dish u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT d from Dish d WHERE d.day = :day ORDER BY d.id DESC")
    List<Dish> getByDay(@Param("day") LocalDate day);
}
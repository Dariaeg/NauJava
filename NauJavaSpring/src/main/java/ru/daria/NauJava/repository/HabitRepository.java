package ru.daria.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.daria.NauJava.entities.Habit;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUserId(Long userId);

    List<Habit> findByActiveTrueAndUserId(Long userId);

    List<Habit> findByActiveTrueAndCompletedTrueAndUserId(Long userId);

    @Modifying
    @Query("UPDATE Habit h SET h.active = :active WHERE h.id = :id")
    void updateActiveStatus(@Param("id") Long id, @Param("active") Boolean active);

    @Query("SELECT h FROM Habit h WHERE " +
            "(:categoryId IS NULL OR h.category.id = :categoryId) AND " +
            "h.user.id = :userId AND h.active = true")
    List<Habit> findActiveByCategoryAndUser(
            @Param("categoryId") Long categoryId,
            @Param("userId") Long userId);

    @Query("SELECT h FROM Habit h WHERE " +
            "(:categoryId IS NULL OR h.category.id = :categoryId) AND " +
            "LOWER(h.name) LIKE LOWER(CONCAT('%', :word, '%'))")
    List<Habit> findByCategoryIdAndNameContaining(
            @Param("categoryId") Long categoryId,
            @Param("word") String word);

    @Query("SELECT h FROM Habit h WHERE h.currentCount >= h.targetCount")
    List<Habit> findByCurrentCountGreaterThanEqualTargetCount();
}
package ru.daria.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.daria.NauJava.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
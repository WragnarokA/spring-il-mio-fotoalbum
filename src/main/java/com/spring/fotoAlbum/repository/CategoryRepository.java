package com.spring.fotoAlbum.repository;

import com.spring.fotoAlbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByOrderByName();

    boolean existsByName(String name);
}

package com.spring.fotoAlbum.repository;

import com.spring.fotoAlbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
//    List<Photo> findByNameContainingIgnoreCaseOrDescriptionContaining(String nameKeyword, String descriptionKeyword);
}

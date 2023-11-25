package com.spring.fotoAlbum.repository;

import com.spring.fotoAlbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByTitleContainingIgnoreCaseOrDescriptionContaining(String titleKeyword, String descriptionKeyword);
}

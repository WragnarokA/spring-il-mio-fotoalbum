package com.spring.fotoAlbum.service;

import com.spring.fotoAlbum.exceptions.PhotoNotFoundException;
import com.spring.fotoAlbum.model.Photo;
import com.spring.fotoAlbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    //metodo che restituisce tutte le pizze
    public List<Photo> getPhotoList(Optional<String> search) {

        if (search.isPresent()) {
            return photoRepository.findByTitleContainingIgnoreCaseOrDescriptionContaining(search.get(), search.get());
        } else {
            return photoRepository.findAll();
        }
    }

    public List<Photo> getPhotoList() {
        return photoRepository.findAll();
    }

    // metodo che restituisce una pizza se non la trova solleva un'eccezione
    public Photo getPhotoById(Integer id) throws PhotoNotFoundException {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PhotoNotFoundException("Pizza with " + id + " not found");
        }
    }

    // metodo per creare una pizza
    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    // Metodo per modificare un nuova pizza
    public Photo editPhoto(Photo photo) throws PhotoNotFoundException {
        Photo photoToEdit = getPhotoById(photo.getId());
        photoToEdit.setTitle(photo.getTitle());
        photoToEdit.setDescription(photo.getDescription());
        photoToEdit.setUrl(photo.getUrl());
        return photoRepository.save(photoToEdit);
    }

    //metodo che fa la Delete
    public void deletePhotoById(Integer id) {
        photoRepository.deleteById(id);
    }

    // Metodo che prende in ingresso un Pageable e restituisce la Page delle Pizze
    public Page<Photo> getPage(Pageable pageable) {
        return photoRepository.findAll(pageable);
    }


}













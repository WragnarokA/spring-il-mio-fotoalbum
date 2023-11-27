package com.spring.fotoAlbum.api;

import com.spring.fotoAlbum.exceptions.PhotoNotFoundException;
import com.spring.fotoAlbum.model.Photo;
import com.spring.fotoAlbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/photos")
@CrossOrigin
public class PhotoResController {
    @Autowired
    private PhotoService photoService;

    //endpoint per la lista di tutte le photo
    @GetMapping
    public List<Photo> index(@RequestParam Optional<String> search) {
        return photoService.getPhotoList(search);
    }

    //endpoint per i dettagli della photo presso dal id
    @GetMapping("/{id}")
    public Photo details(@PathVariable Integer id) {
        try {
            return photoService.getPhotoById(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //endpoint per creare una nuova photo
    @PostMapping
    public Photo create(@Valid @RequestBody Photo photo) {
        photo.setId(null);
        try {
            return photoService.createPhoto(photo);
        } catch (Exception e) {
            // Puoi gestire l'eccezione qui se necessario
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    //endpoint per modificare una photo
    @PutMapping("/{id}")
    public Photo edit(@PathVariable Integer id, @Valid @RequestBody Photo photo) {
        photo.setId(id);
        try {
            return photoService.editPhoto(photo);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //endpoint per la Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            Photo photoToDelete = photoService.getPhotoById(id);
            photoService.deletePhotoById(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //endpoint per la paginazione
    @GetMapping("/page/v2")
    public Page<Photo> pagedIndexV2(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        return photoService.getPage(pageable);
    }
}

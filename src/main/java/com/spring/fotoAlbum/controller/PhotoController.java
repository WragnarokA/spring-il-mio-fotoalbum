package com.spring.fotoAlbum.controller;

import com.spring.fotoAlbum.controller.exceptions.PhotoNotFoundException;
import com.spring.fotoAlbum.model.Photo;
import com.spring.fotoAlbum.repository.PhotoRepository;
import com.spring.fotoAlbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public String index(Model model) {
        List<Photo> photoList = photoRepository.findAll();
        model.addAttribute("photoList", photoList);

        return "photos/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Photo> result = photoRepository.findById(id);
        try {
            Photo photo = photoService.getPhotoById(id);
            model.addAttribute("photo", photo);
            return "/photos/show";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }


}



















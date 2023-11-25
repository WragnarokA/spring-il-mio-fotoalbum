package com.spring.fotoAlbum.controller;

import com.spring.fotoAlbum.exceptions.PhotoNotFoundException;
import com.spring.fotoAlbum.model.Photo;
import com.spring.fotoAlbum.repository.PhotoRepository;
import com.spring.fotoAlbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String index(@RequestParam Optional<String> search, Model model) {
        List<Photo> photoList;
        model.addAttribute("photoList", photoService.getPhotoList(search));
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

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("photo", new Photo());
//        model.addAttribute("ingredientList", ingredientService.getAll());
        return "/photos/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("ingredientList", ingredientService.getAll());
            return "/photos/form";
        }
        Photo savePhoto = photoRepository.save(formPhoto);
        return "redirect:/photos/show/" + savePhoto.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            //Dal id recupero i dati  della pizza
            model.addAttribute("photo", photoService.getPhotoById(id));
            return "/photos/form";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id " + id + " not found");
        }

    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("ingredientList", ingredientService.getAll());
            return "/photos/form";
        }
        try {
            Photo savePhoto = photoService.editPhoto(formPhoto);
            return "redirect:/photos/show/" + savePhoto.getId();
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    private String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // recupero la pizza con quell'id
        Photo photoDelete = photoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // se esiste la eliminiamo
        photoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Photo " + photoDelete.getTitle() + " delete!");
        return "redirect:/photos";
    }

}



















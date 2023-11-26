package com.spring.fotoAlbum.controller;

import com.spring.fotoAlbum.exceptions.CategoryNameUniqueException;
import com.spring.fotoAlbum.model.Category;
import com.spring.fotoAlbum.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categoriesList", categoryService.getAll());
        model.addAttribute("categoriesObj", new Category());
        return "category/index";

    }

    @PostMapping
    public String doSave(@Valid @ModelAttribute("categoriesObj") Category formCategory, BindingResult bindingResult, Model model) {
        //valido l'ingrediente
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoriesList", categoryService.getAll());
            return "category/index";
        }
        //salvo il nuovo ingrediente
        try {
            categoryService.save(formCategory);
            return "redirect:/categories";
        } catch (CategoryNameUniqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A category with name " + e.getMessage() + " already exists");
        }
    }
}

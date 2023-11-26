package com.spring.fotoAlbum.service;

import com.spring.fotoAlbum.exceptions.CategoryNameUniqueException;
import com.spring.fotoAlbum.model.Category;
import com.spring.fotoAlbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findByOrderByName();
    }

    public Category save(Category category) throws CategoryNameUniqueException {
        //vefico che non esista
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryNameUniqueException(category.getName());
        }
        //trasformo il nome in LowerCase
        category.setName(category.getName().toLowerCase());
        //salvo su DB
        return categoryRepository.save(category);
    }
}

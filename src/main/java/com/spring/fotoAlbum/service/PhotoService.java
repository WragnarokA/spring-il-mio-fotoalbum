package com.spring.fotoAlbum.service;

import com.spring.fotoAlbum.controller.exceptions.PhotoNotFoundException;
import com.spring.fotoAlbum.model.Photo;
import com.spring.fotoAlbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    //metodo che restituisce tutte le pizze
//    public List<Photo> getPhotoList(Optional<String> search) {
//
//        if (search.isPresent()) {
//            return photoRepository.findByNameContainingIgnoreCaseOrDescriptionContaining(search.get(), search.get());
//        } else {
//            return photoRepository.findAll();
//        }
//    }

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

//    // metodo per creare una pizza
//    public Pizza createPizza(Pizza pizza) {
//        return pizzaRepository.save(pizza);
//    }
//
//
//    // Metodo per modificare un nuova pizza
//
//    public Pizza editPizza(Pizza pizza) throws PizzaNotFoundException {
//        Pizza pizzaToEdit = getPizzaById(pizza.getId());
//        pizzaToEdit.setName(pizza.getName());
//        pizzaToEdit.setDescription(pizza.getDescription());
//        pizzaToEdit.setUrl(pizza.getUrl());
//        pizzaToEdit.setPrezzo(pizza.getPrezzo());
//        pizzaToEdit.setIngredients(pizza.getIngredients());
//
//        return pizzaRepository.save(pizzaToEdit);
//    }
//
//    //metodo che fa la Delete
//    public void deletePizzaById(Integer id) {
//        pizzaRepository.deleteById(id);
//    }
//
//    // Metodo che prende in ingresso un Pageable e restituisce la Page delle Pizze
//    public Page<Pizza> getPage(Pageable pageable) {
//        return pizzaRepository.findAll(pageable);
//    }


}













package com.spring.fotoAlbum.controller;

import com.spring.fotoAlbum.model.User;
import com.spring.fotoAlbum.repository.UserRepository;
import com.spring.fotoAlbum.security.DataBaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String index(Authentication authentication, Model model) {

        DataBaseUserDetails principal = (DataBaseUserDetails) authentication.getPrincipal();
        User loggedUser = userRepository.findById(principal.getId()).get();
        model.addAttribute(loggedUser.getFirstName());
        model.addAttribute(loggedUser.getLastName());
        return "users/index";
    }
}

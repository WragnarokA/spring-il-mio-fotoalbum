package com.spring.fotoAlbum.repository;

import com.spring.fotoAlbum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    public Optional<User> findByEmail(String email);

}

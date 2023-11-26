package com.spring.fotoAlbum.exceptions;

public class CategoryNameUniqueException extends RuntimeException {
    public CategoryNameUniqueException(String message) {
        super(message);
    }
}

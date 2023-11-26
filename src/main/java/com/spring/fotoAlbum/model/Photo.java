package com.spring.fotoAlbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "photos")
public class Photo {
    //Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3)
    @Size(max = 255)
    private String title;

    @Lob
    @NotBlank(message = "Description must not be blank")
    @Size(min = 3)
    private String description;

    @Lob
    @NotBlank(message = "Image must not be blank")
    @Size(min = 3)
    private String url;

    private boolean visibile;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;

    //*****CONSTRUCTOR*****

    //*****GETTER AND SETTER****


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}

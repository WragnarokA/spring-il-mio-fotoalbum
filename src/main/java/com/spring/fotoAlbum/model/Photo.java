package com.spring.fotoAlbum.model;

import jakarta.persistence.*;

@Entity
@Table(name = "photos")
public class Photo {
    //Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    @Lob
    private String description;
    @Lob
    private String url;
    private boolean visibile;
//    private List<Categories> categories;

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
}

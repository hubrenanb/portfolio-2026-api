package com.renan.profile.core_api.model; 

import jakarta.persistence.*; 


@Entity

public class Project {

    
    @Id
    
    
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    private String title;
    private String description;
    private String imageUrl;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
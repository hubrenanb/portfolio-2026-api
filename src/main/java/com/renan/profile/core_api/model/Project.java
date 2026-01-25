package com.renan.profile.core_api.model; 

import jakarta.persistence.*; 

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Column(columnDefinition = "TEXT") 
    private String description;
    
    
    @Column(columnDefinition = "TEXT") 
    private String imageUrl;
    
    private String tags;   
    private String status; 
    
    private String link;   
    
    // --- GETTERS E SETTERS ---
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
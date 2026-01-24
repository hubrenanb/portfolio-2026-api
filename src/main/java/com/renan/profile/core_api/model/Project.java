package com.renan.profile.core_api.model; 

import jakarta.persistence.*; 

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    // MUDANÇA: columnDefinition = "TEXT" permite textos gigantes (sem limite de 255)
    @Column(columnDefinition = "TEXT") 
    private String description;
    
    private String imageUrl;
    
    private String tags;   // Ex: "Java, React, SQL"
    private String status; // Ex: "Concluído", "Em Desenvolvimento"
    
    // --- NOVO CAMPO ---
    private String link;   // Ex: "https://github.com/usuario/projeto"
    
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

    // Getter e Setter do Link
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
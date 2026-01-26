package com.renan.profile.core_api.controller;

import com.renan.profile.core_api.model.Project;
import com.renan.profile.core_api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") 
public class ProjectController {

    @Autowired
    private ProjectService projectService; // Agora usamos o Service, não o Repository

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam(value = "tags", required = false) String tags,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        
        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setTags(tags);
        project.setStatus(status);
        project.setLink(link);

        Project savedProject = projectService.saveOrUpdateProject(project, file);
        return ResponseEntity.ok(savedProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam(value = "tags", required = false) String tags,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        
        // Busca o projeto existente primeiro
        Project existingProject = projectService.findById(id);

        // Atualiza os dados
        existingProject.setTitle(title);
        existingProject.setDescription(description);
        existingProject.setTags(tags);
        existingProject.setStatus(status);
        existingProject.setLink(link);

        // O Service decide se atualiza a imagem ou mantém a antiga (se file for null)
        Project updatedProject = projectService.saveOrUpdateProject(existingProject, file);
        
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
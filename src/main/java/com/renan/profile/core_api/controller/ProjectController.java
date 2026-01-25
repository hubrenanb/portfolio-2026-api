package com.renan.profile.core_api.controller;

import com.renan.profile.core_api.model.Project;
import com.renan.profile.core_api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") 
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    public Project createProject(
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

       
        if (file != null && !file.isEmpty()) {
            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
            // Adiciona o prefixo para o navegador entender que é uma imagem
            String imageString = "data:" + file.getContentType() + ";base64," + base64Image;
            project.setImageUrl(imageString);
        }

        return projectRepository.save(project);
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
        
        return projectRepository.findById(id)
                .map(project -> {
                    project.setTitle(title);
                    project.setDescription(description);
                    project.setTags(tags);
                    project.setStatus(status);
                    project.setLink(link);

                    if (file != null && !file.isEmpty()) {
                        try {
                            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
                            String imageString = "data:" + file.getContentType() + ";base64," + base64Image;
                            project.setImageUrl(imageString);
                        } catch (IOException e) {
                            throw new RuntimeException("Erro ao processar imagem", e);
                        }
                    }
                    return ResponseEntity.ok(projectRepository.save(project));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    projectRepository.delete(project);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
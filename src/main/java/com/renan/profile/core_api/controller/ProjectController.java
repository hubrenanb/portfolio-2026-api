package com.renan.profile.core_api.controller;

import com.renan.profile.core_api.model.Project;
import com.renan.profile.core_api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") 
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    public ProjectController() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível criar o diretório de uploads.", ex);
        }
    }

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
            @RequestParam(value = "link", required = false) String link, // NOVO
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        
        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setTags(tags);
        project.setStatus(status);
        project.setLink(link); // SALVANDO LINK

        if (file != null) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            project.setImageUrl("http://localhost:8080/uploads/" + fileName);
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
            @RequestParam(value = "link", required = false) String link, // NOVO
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        
        return projectRepository.findById(id)
                .map(project -> {
                    project.setTitle(title);
                    project.setDescription(description);
                    project.setTags(tags);
                    project.setStatus(status);
                    project.setLink(link); // ATUALIZANDO LINK

                    if (file != null) {
                        try {
                            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                            Path targetLocation = this.fileStorageLocation.resolve(fileName);
                            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                            project.setImageUrl("http://localhost:8080/uploads/" + fileName);
                        } catch (IOException e) {
                            e.printStackTrace();
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
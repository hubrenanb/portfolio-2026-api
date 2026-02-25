package com.renan.profile.core_api.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.renan.profile.core_api.model.Project;
import com.renan.profile.core_api.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveOrUpdateProject(Project project, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
            String imageString = "data:" + file.getContentType() + ";base64," + base64Image;
            project.setImageUrl(imageString);
        } else if (project.getId() != null) {
            // Mantém a imagem atual se for uma edição sem novo arquivo
            Project existing = findById(project.getId());
            if (project.getImageUrl() == null) {
                project.setImageUrl(existing.getImageUrl());
            }
        }
        return projectRepository.save(project);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
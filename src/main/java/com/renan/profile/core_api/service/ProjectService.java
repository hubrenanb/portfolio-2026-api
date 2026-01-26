package com.renan.profile.core_api.service;

import com.renan.profile.core_api.model.Project;
import com.renan.profile.core_api.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    // Método unificado para Criar ou Atualizar
    public Project saveOrUpdateProject(Project project, MultipartFile file) throws IOException {
        
        // Se vier um arquivo, converte para Base64 (Lógica blindada para o Render)
        if (file != null && !file.isEmpty()) {
            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
            String imageString = "data:" + file.getContentType() + ";base64," + base64Image;
            project.setImageUrl(imageString);
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
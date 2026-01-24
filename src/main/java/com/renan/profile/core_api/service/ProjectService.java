package com.renan.profile.core_api.service;

import com.renan.profile.core_api.model.Project;
import com.renan.profile.core_api.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile; // Import Novo
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.io.IOException; // Import Novo
import java.nio.file.*;     // Import Novo (Para lidar com pastas)
import java.util.List;
import java.util.UUID;      // Import Novo (Para gerar ID aleatório)

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveProject(Project project) {
        if (project.getTitle() == null || project.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O titulo do projeto é obrigatório");
        }
        return projectRepository.save(project);
    }

    // --- MÉTODO NOVO INSERIDO AQUI ---
    public Project updateProject(Long id, String title, String description, MultipartFile file) throws IOException {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        project.setTitle(title);
        project.setDescription(description);

        // LÓGICA INTELIGENTE: Só troca a imagem se o usuário enviou uma nova
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            Path path = Paths.get("uploads");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.copy(file.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            project.setImageUrl("http://localhost:8080/uploads/" + fileName);
        }

        return projectRepository.save(project);
    }
    // ---------------------------------

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
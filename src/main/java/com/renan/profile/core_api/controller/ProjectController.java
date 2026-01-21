package com.renan.profile.core_api.controller;

import com.renan.profile.core_api.model.Project;
import com.renan.profile.core_api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects") // A rota base será: localhost:8080/api/projects
public class ProjectController {

    // Injeção de Dependência: O Spring "entrega" o repositório pronto pra gente usar
    @Autowired
    private ProjectRepository projectRepository;

    // 1. POST: Salvar um novo projeto
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        // O @RequestBody pega o JSON que você enviar e transforma em um objeto Java
        return projectRepository.save(project); // Salva no banco e retorna o item salvo
    }

    // 2. GET: Listar todos os projetos
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll(); // Vai no banco e traz tudo
    }

    // Delete
    
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }
}
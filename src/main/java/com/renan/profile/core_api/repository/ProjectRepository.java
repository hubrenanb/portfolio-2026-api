package com.renan.profile.core_api.repository;

import com.renan.profile.core_api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Essa interface herda poderes prontos: save, delete, findAll, findById...
    // Não precisa escrever SQL nenhum!
}
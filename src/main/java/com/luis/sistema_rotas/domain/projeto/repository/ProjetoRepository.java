package com.luis.sistema_rotas.domain.projeto.repository;

import com.luis.sistema_rotas.domain.projeto.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

    List<Projeto> findProjetoByUsuarioId(UUID id);
}

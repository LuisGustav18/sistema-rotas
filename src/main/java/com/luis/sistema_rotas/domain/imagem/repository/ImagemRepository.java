package com.luis.sistema_rotas.domain.imagem.repository;

import com.luis.sistema_rotas.domain.imagem.entity.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, UUID> {

    List<Imagem> findImagemByRotaId(UUID id);
}
